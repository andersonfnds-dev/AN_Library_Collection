package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Title {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "cod_title")
	private Long id;
	
	private String name;
			
	@ManyToOne
	private Author author;
	
	@OneToMany 
	private List<Book> books;

	public Title() {
		super();
	}

	public Title(String name, Author author) {
		super();
		this.name = name;
		this.author = author;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String nome) {
		this.name = nome;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

}
