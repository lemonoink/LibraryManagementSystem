package htMaster;
// ��Ա��
public class Member {
	private String number;  // ���
	private String name;  // ����
	private String phoneNumber;  // �绰

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
		return "��Ա[ ��ţ�" + getNumber() + "  ������" + getName() + "  �绰��" + getPhoneNumber() + " ]";
	}
}
