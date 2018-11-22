package htMaster;
// 图书类
public class Book extends Product {
	private String author;  // 作者
	private String ISBN;  // ISBN号
	
	public Book(String attributeNumber, String name, int number, String author, String ISBN) {
		this.attributeNumber = attributeNumber;
		this.name = name;
		this.number = number;
		this.author = author;
		this.ISBN = ISBN;
	}
	
	// 借书：书的数量大于0，数量-1，返回true; 否则，返回false
	public boolean borrow() {
		if(number>0) {
			number--;
			return true;
		}
		else {
			return false;
		}
	}
	
	// 还书：书的数量+1
	public void returnP() {
		number++;
	}
	
	// 返回写入文件的数据
	public String write() {
		return "Book#" + attributeNumber + "#" + name + "#" + number + "#" + author + "#" + ISBN;
	}
	
	// 返回书类的信息
	public String toString() {
		return "Book[ 编号：" + attributeNumber + "  名称：" + name + "  数量：" + number + "  作者：" + author + "  ISBN：" + ISBN + " ]";
	}
}
