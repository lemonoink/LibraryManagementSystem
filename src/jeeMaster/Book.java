package jeeMaster;
// Õº È¿‡
public class Book extends Product {
	private String author;
	private String ISBN;
	private static int num = 0;
	
	public Book(String attributeNumber, String name, int number, String author, String ISBN) {
		this.attributeNumber = attributeNumber;
		this.name = name;
		this.number = number;
		this.author = author;
		this.ISBN = ISBN;
		num++;
	}
	
	
	
}
