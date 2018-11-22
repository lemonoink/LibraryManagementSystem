package htMaster;

import java.text.SimpleDateFormat;
import java.util.Date;

// 借阅信息类
public class Record {
	protected Product product;
	protected Member member;
	protected String date;
	
	public Record(Product product,Member member) {
		this.product = product;
		this.member = member;
		Date day=new Date();    
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = df.format(day);
		this.date = time;
	}
	
	// 返回写入文件的数据
	public String write() {
		return member.write() + "#" + product.write() + "#" + date;
	}
	
	// 返回书类的信息
	public String toString() {
		return "{ " + member.toString() + "\n " + product.toString() + "\n 时间为：" + date + "}";
	}
}
