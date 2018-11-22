package htMaster;

import java.text.SimpleDateFormat;
import java.util.Date;

// ������Ϣ��
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
	
	// ����д���ļ�������
	public String write() {
		return member.write() + "#" + product.write() + "#" + date;
	}
	
	// �����������Ϣ
	public String toString() {
		return "{ " + member.toString() + "\n " + product.toString() + "\n ʱ��Ϊ��" + date + "}";
	}
}
