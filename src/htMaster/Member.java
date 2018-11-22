package htMaster;
// 会员类
public class Member {
	private String number;  // 编号
	private String name;  // 姓名
	private String phoneNumber;  // 电话

	public Member(String number, String name, String phoneNumber) {
		this.setNumber(number);
		this.setName(name);
		this.setPhoneNumber(phoneNumber);
	}
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String write() {
		return getNumber() + "#" + getName() + "#" + getPhoneNumber();
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String toString() {
		return "会员[ 编号：" + getNumber() + "  姓名：" + getName() + "  电话：" + getPhoneNumber() + " ]";
	}
}
