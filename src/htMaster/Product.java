package htMaster;
// ��Ʒ��
public abstract class Product {
	protected String attributeNumber;  // ���
	protected String name;  // ����
	protected int number;  // ����
	
	public abstract String write();
	
	public abstract boolean borrow();
	
	public abstract void returnP();
}
