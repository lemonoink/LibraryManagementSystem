package htMaster;
// DVD��
public class DVD extends Product {
	private String ISRC;  // ISRC��
	
	public DVD(String attributeNumber, String name, int number, String ISRC) {
		this.attributeNumber = attributeNumber;
		this.name = name;
		this.number = number;
		this.ISRC = ISRC;
	}
	
	// ��DVD��DVD����������0������-1������true; ���򣬷���false
	public boolean borrow() {
		if(number>0) {
			number--;
			return true;
		}
		else {
			return false;
		}
	}
	
	// ��DVD��DVD������+1
	public void returnP() {
		number++;
	}
	
	// ����д���ļ�������
	public String write() {
		return "DVD#" + attributeNumber + "#" + name + "#" + number + "#" + ISRC;
	}
	
	// �����������Ϣ
	public String toString() {
		return "DVD[ ��ţ�" + attributeNumber + "  ���ƣ�" + name + "  ������" + number + "  ISRC��" + ISRC + " ]";
	}
}
