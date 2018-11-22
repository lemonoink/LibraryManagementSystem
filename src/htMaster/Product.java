package htMaster;
// 商品类
public abstract class Product {
	protected String attributeNumber;  // 编号
	protected String name;  // 名称
	protected int number;  // 数量
	
	public abstract String write();
	
	public abstract boolean borrow();
	
	public abstract void returnP();
}
