package infra;

import java.util.List;
import java.util.Scanner;

import model.Author;
import model.Book;
import model.Title;

public class Menu {
	
	int menu;
	
	

	public Menu() {
		super();
	}


	public void InitializeMenu() {
		Scanner scanner = new Scanner(System.in);
		Scanner scannerString = new Scanner(System.in);
		
		Book book;
		Author author;
		Title title;
		
		 
		do { 
				System.out.println(showMenu());
				menu = scanner.nextInt();
				
				switch (menu) {
				case 1:
					DAO<Object> dao = new DAO<>();
					
					System.out.println("Type the title: ");
					String titleScan = scannerString.nextLine();
					System.out.println("Type the Author: ");
					String authorScan = scannerString.nextLine();
					System.out.println("Type the price: ");
					double price = scanner.nextDouble();
					
					author = new Author(authorScan);
					title = new Title(titleScan, author);
					book = new Book(title, price);
					
					dao.openTransaction()
						.include(author)
						.include(title)
						.include(book)
						.closeTransaction()
						.close();
					
					break;
				case 2:
					DAO<Book> dao2 = new DAO<>(Book.class);
					List<Book> ids = dao2.getAllFromClass();
					
					System.out.println("======== Books in your collection ========\n");
					
					for (Book id : ids) {
						System.out.print("\t" + id.getId() + "\t\t");
						
					}
					dao2.close();
					
					DAO<Title> dao3 = new DAO<>(Title.class);
					List<Title> names = dao3.getAllFromClass();
					
					System.out.println();
					for (Title name : names) {
						System.out.print(name.getName() + "\t");
						
					}
					dao3.close();
					
					DAO<Author> dao4 = new DAO<>(Author.class);
					List<Author> authors = dao4.getAllFromClass();
					
					System.out.println();
					for (Author authorV : authors) {
						System.out.print(authorV.getName() + "\t\t");
						
					}
					dao4.close();
					
					DAO<Book> dao5 = new DAO<>(Book.class);
					List<Book> prices = dao5.getAllFromClass();
					
					System.out.println();
					for (Book priceV : prices) {
						System.out.print("$" + priceV.getPrice() + "\t\t\t");
						
					}
					dao5.close();
					
					break;
				//case 3:
					
					//String search1 = "";
					
					//System.out.println("Type the genre: \n");
					//search1 = scannerString.nextLine();
					//System.out.println(Collection.searchGenre(search1));
					
					//break;
				//case 4:
					
					//double initalValue, finalValue = 0;
					
					//System.out.println("Type the minimum price range: ");
					//initalValue = scanner.nextInt();
					//System.out.println("Type the maximum price range: ");
					//finalValue = scanner.nextInt();
					
					//System.out.println(Collection.searchPrice(initalValue,finalValue));
					
					//break;
				case 5:
					
					@SuppressWarnings("unused") String search4 = "";
					
					System.out.println("Type the book title you want to remove: ");
					search4 = scannerString.nextLine();
					
					//System.out.println(Collection.remove(search4));
					
					break;
				case 6:
					
					//System.out.println(Collection.calculateCollectionTotal());
					
					break;
				case 7:
					
					System.out.println("See you later!");
					
					break;
				default:
					System.out.println("Invalid option");
				}
		   
		} while (menu != 7); 
			
		scanner.close();
		scannerString.close();
		
	}
		

	public String showMenu() {
	
		return "\n\n======== LIBRARY MENU ========\n\n"
			+ "1. ADD NEW BOOK\n"
			+ "2. SHOW THE CATALOG\n"
			+ "3. SEARCH BOOKS BY GENRE\n"
			+ "4. SEARCH BOOK BY PRICE\n"
			+ "5. REMOVE BOOK\n"
			+ "6. CALCULATE COLLECTION VALUE\n"
			+ "7. LOG OUT FROM MENU\n\n"
			+ "Type you option:  \n" ;
	}
}




