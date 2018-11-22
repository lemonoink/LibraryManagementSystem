package htMaster;
// DVD类
public class DVD extends Product {
	private String ISRC;  // ISRC号
	
	public DVD(String attributeNumber, String name, int number, String ISRC) {
		this.attributeNumber = attributeNumber;
		this.name = name;
		this.number = number;
		this.ISRC = ISRC;
	}
	
	// 借DVD：DVD的数量大于0，数量-1，返回true; 否则，返回false
	public boolean borrow() {
		if(number>0) {
			number--;
			return true;
		}
		else {
			return false;
		}
	}
	
	// 还DVD：DVD的数量+1
	public void returnP() {
		number++;
	}
	
	// 返回写入文件的数据
	public String write() {
		return "DVD#" + attributeNumber + "#" + name + "#" + number + "#" + ISRC;
	}
	
	// 返回书类的信息
	public String toString() {
		return "DVD[ 编号：" + attributeNumber + "  名称：" + name + "  数量：" + number + "  ISRC：" + ISRC + " ]";
	}
}
