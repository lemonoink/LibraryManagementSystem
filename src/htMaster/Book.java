package htMaster;
// ͼ����
public class Book extends Product {
	private String author;  // ����
	private String ISBN;  // ISBN��
	
	public Book(String attributeNumber, String name, int number, String author, String ISBN) {
		this.attributeNumber = attributeNumber;
		this.name = name;
		this.number = number;
		this.author = author;
		this.ISBN = ISBN;
	}
	
	// ���飺�����������0������-1������true; ���򣬷���false
	public boolean borrow() {
		if(number>0) {
			number--;
			return true;
		}
		else {
			return false;
		}
	}
	
	// ���飺�������+1
	public void returnP() {
		number++;
	}
	
	// ����д���ļ�������
	public String write() {
		return "Book#" + attributeNumber + "#" + name + "#" + number + "#" + author + "#" + ISBN;
	}
	
	// �����������Ϣ
	public String toString() {
		return "Book[ ��ţ�" + attributeNumber + "  ���ƣ�" + name + "  ������" + number + "  ���ߣ�" + author + "  ISBN��" + ISBN + " ]";
	}
}
