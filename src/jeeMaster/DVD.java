package jeeMaster;
// DVD¿‡
public class DVD extends Product {
	private String ISRC;
	private static int num = 0;
	
	public DVD(String attributeNumber, String name, int number, String ISRC) {
		this.attributeNumber = attributeNumber;
		this.name = name;
		this.number = number;
		this.ISRC = ISRC;
		num++;
	}
	
	
	
}
