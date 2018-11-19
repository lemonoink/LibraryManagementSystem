package jeeMaster;
// ª·‘±¿‡
public class Member {
	private String attributeNumber;
	private String name;
	private String phoneNumber;
	private static int num = 0;
	
	public Member(String attributeNumber, String name, String phoneNumber) {
		this.attributeNumber = attributeNumber;
		this.name = name;
		this.phoneNumber = phoneNumber;
		num++;
	}
	
	
	
}
