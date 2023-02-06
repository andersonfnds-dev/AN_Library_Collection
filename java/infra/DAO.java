package infra;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class DAO <E>{
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Class<E> classe;
	
	static {
		try {
			emf = Persistence.createEntityManagerFactory("an_library_collection");
		} catch (Exception e) {
			// logar -> log4j
		}
	}
	
	public DAO() {
		this(null);
	}
	
	public DAO(Class<E> classe) {
		this.classe = classe;
		em = emf.createEntityManager();
	}
	
	public DAO<E> openTransaction() {
		em.getTransaction().begin();
		return this;
	}
	
	public DAO<E> closeTransaction() {
		em.getTransaction().commit();
		return this;
	}
	
	public DAO<E> include(E entity) {
		em.persist(entity);;
		return this;
	}
	
	public DAO<E> includeComplete(E entity) {
		return this.openTransaction().include(entity).closeTransaction();
	}
	
	public List<E> getAll() {
		return this.getAll(10, 0);
	}
	
	public List<E> getAllFromClass() {
		return this.getAllFromClass(10, 0);
	}
	
	public E getById(Object id) {
		return em.find(classe, id);
	}
	
	public List<E> getAllFromClass(int qtde, int deslocamento) {
		if(classe == null) {
			throw new UnsupportedOperationException("Classe nula.");
		}
		
		String jpql = "select e from " + classe.getName() + " e";
		TypedQuery<E> query = em.createQuery(jpql, classe);
		query.setMaxResults(qtde);
		query.setFirstResult(deslocamento);
		return query.getResultList();
	}
	
	public ArrayList<E> getAll(int qtde, int displacement) {
		if(classe == null) {
			throw new UnsupportedOperationException("Null Class.");
		}
		
		String jpql = "SELECT b.id, t.name, a.name, b.price "
				+ "FROM Book b " 
				+ "JOIN Title t " 
				+ "ON b.title = t.id " 
				+ "JOIN Author a " 
				+ "ON t.author = a.id";
		TypedQuery<E> query = em.createQuery(jpql, classe);
		query.setMaxResults(qtde);
		query.setFirstResult(displacement);
		return (ArrayList<E>) query.getResultList();
	}
	
	public List<E> query(String queryName, Object... params){
		TypedQuery<E> query = em.createNamedQuery(queryName, classe);
		
		for (int i = 0; i < params.length; i += 2) {
			query.setParameter(params[i].toString(), params[i + 1]);
		}
		
		return (ArrayList<E>) query.getResultList();
	}
	
	public E queryOneObject(String queryName, Object... params) {
		List<E> lista = query(queryName, params);
		return lista.isEmpty() ? null : lista.get(0);
	}
	
	public void close() {
		em.close();
	}

}
