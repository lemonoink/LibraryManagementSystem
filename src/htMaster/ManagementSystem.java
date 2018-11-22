package htMaster;

import java.io.*;
import java.util.*;

// 管理系统类
public class ManagementSystem {
	ArrayList<Member> member = new ArrayList<Member>();
	ArrayList<Product> product = new ArrayList<Product>();
	ArrayList<Record> record = new ArrayList<Record>();

	// 读取文件
	public boolean readFile(String fileName) {
	    File file=new File(".\\" + fileName);  
	    if(!file.exists()) {  
	        try {  
	            file.createNewFile();  
	        } catch (IOException e) {   
	            e.printStackTrace();
	            System.out.println(fileName + "文件创建失败！");
	            return false;
	        }  
	    }
		try {
			FileReader fileReader = new FileReader(".\\" + fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			switch (fileName) {
			case "member.txt":  // 会员信息
				bufferedReader.readLine();
				break;
			case "product.txt":  // 商品信息
			case "record.txt":  // 借阅记录
				bufferedReader.readLine();
				bufferedReader.readLine();
				break;
			default:
				System.out.println("文件信息有误！");
				return false;
			}
			String str;
			while ((str = bufferedReader.readLine()) != null) { 
				parseFile(str, fileName);
			}
			bufferedReader.close();
			fileReader.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(fileName + "文件读取失败！");
			return false;
		}
	}
	
	// 解析文件
	public boolean parseFile(String str, String fileName) {
		switch (fileName) {
		case "member.txt":  // 会员信息
			String[] memberStr = str.split("#");
			Member memberTmp = new Member(memberStr[0],memberStr[1],memberStr[2]);
			member.add(memberTmp);
			break;
		case "product.txt":  // 商品信息
			String[] productStr = str.split("#");
			Product productTmp;
			if(productStr[0].equals("Book")) {
				productTmp = new Book(productStr[1],productStr[2],Integer.parseInt(productStr[3]),productStr[4],productStr[5]);
			}
			else {
				productTmp = new DVD(productStr[1],productStr[2],Integer.parseInt(productStr[3]),productStr[4]);
			}
			product.add(productTmp);
			break;
		case "record.txt":  // 借阅记录
			String[] recordStr = str.split("#");
			Product productTmp2;
			String date;
			if(recordStr[0].equals("Book")) {
				productTmp2 = new Book(recordStr[4],recordStr[5],Integer.parseInt(recordStr[6]),recordStr[7],recordStr[8]);
				date = recordStr[9];
			}
			else {
				productTmp2 = new DVD(recordStr[4],recordStr[5],Integer.parseInt(recordStr[6]),recordStr[7]);
				date = recordStr[8];
			}
			Member memberTmp2 = new Member(recordStr[0],recordStr[1],recordStr[2]);
			Record recordTmp = new Record(productTmp2,memberTmp2);
			record.add(recordTmp);
			break;
		default:
			System.out.println("文件信息有误！");
			return false;
		}
		return true;
	}
	
	// 写入文件
	public boolean writeFile(String fileName) {
		try {  // 异常处理
			FileReader fileReader = new FileReader(".\\" + fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			Writer writer = new FileWriter(new File(".\\" + fileName)); 
			String str;
			while ((str = bufferedReader.readLine()) != null) {
				writer.write("");  // 清空文件内容
			}
			switch (fileName) {
			case "member.txt":  // 会员信息
				writer.write("编号#姓名#电话\r\n");
				for(int i=0; i<member.size(); i++) {
					if(member.get(i)!=null) {
						writer.write(member.get(i).write() + "\r\n");
					}
				}
				break;
			case "product.txt":  // 商品信息
				writer.write("Book#编号#名称#数量#作者#ISBN\r\n");
				writer.write("DVD#编号#名称#数量#ISRC\r\n");
				for(int i=0; i<product.size(); i++) {
					if(product.get(i)!=null) {
						writer.write(product.get(i).write() + "\r\n");
					}
				}
				break;
			case "record.txt":  // 借阅记录
				writer.write("编号#姓名#电话#Book#编号#名称#数量#作者#ISBN#借出时间\r\n");
				writer.write("编号#姓名#电话#DVD#编号#名称#数量#ISRC#借出时间\r\n");
				for(int i=0; i<record.size(); i++) {
					if(product.get(i)!=null) {
						writer.write(record.get(i).write() + "\r\n");
					}
				}
				break;
			default:
				System.out.println("文件信息有误！");
				return false;
			}
			bufferedReader.close();
			fileReader.close();
			writer.close();
			return true;
		} catch (IOException e) { 
			e.printStackTrace(); 
			System.out.println(fileName + "文件读取失败！");
			return false;
		}
	}
	
	// 根据编号或姓名查找会员----tag: 1为根据编号, 2为根据姓名----
	public int searchMember(String search,int tag) {
		if(tag == 1) {
			for(int i=0; i<member.size(); i++) {
				if(member.get(i).getNumber().equals(search)) {
					return i;
				}
			}
			return -1;
		}
		else if(tag == 2) {
			for(int i=0; i<member.size(); i++) {
				if(member.get(i).getName().equals(search)) {
					return i;
				}
			}
			return -1;
		}
		else{
			System.out.println("searchMember函数tag标识有误!");
			return -1;
		}
	}
	
	// 根据名称查找图书或DVD
	public int[] searchProduct(String searchName) {
		ArrayList<Integer> tmp = new ArrayList<Integer>();
		for(int i=0; i<product.size(); i++) {
			if(product.get(i).name.equals(searchName)) {
				tmp.add(i);
			}
		}
		if(tmp.size()==0) {
			return null;
		}
		else {
			int[] index = new int[tmp.size()];
			for(int i = 0;i<tmp.size();i++){
				index[i] = tmp.get(i);
			}
			return index;
		}
	}
	
	// 新增会员
	public boolean newMember(String number, String name, String phoneNumber) {
		Member tmp = new Member(number, name, phoneNumber);
		member.add(tmp);
		return true;
	}
	
	// 删除会员(通过姓名)
	public boolean delMember(String name) {
		int index = searchMember(name,2);
		if(index==-1) {
			return false;
		}
		System.out.println("删除的会员信息为：");
		member.get(index).toString();
		member.remove(index);
		return true;
	}
	
	// 修改会员电话号码
	public boolean changeMemberPhone(String name,String phoneNumber) {
		int index = searchMember(name,2);
		if(index==-1) {
			return false;
		}
		member.get(index).setPhoneNumber(phoneNumber);
		return true;
	}
	
	// 通过编号或姓名查询电话----tag: 1为根据编号, 2为根据姓名----
	public String getMemberPhone(String search,int tag) {
		int index = searchMember(search,tag);
		if(index==-1) {
			return null;
		}
		return member.get(index).getPhoneNumber();
	}
	
	// 通过编号或姓名打印信息----tag: 1为根据编号, 2为根据姓名----
	public String getMemberInfo(String search,int tag) {
		int index = searchMember(search,tag);
		if(index==-1) {
			return null;
		}
		return member.get(index).toString();
	}
	
	// 新增商品----图书----
	public boolean newProduct(String attributeNumber, String name, int number, String author, String ISBN) {
		Product tmp = new Book(attributeNumber, name, number, author, ISBN);
		product.add(tmp);
		return true;
	}
	// ----DVD----
	public boolean newProduct(String attributeNumber, String name, int number, String ISRC) {
		Product tmp = new DVD(attributeNumber, name, number, ISRC);
		product.add(tmp);
		return true;
	}
	
	// 借商品
	public boolean borrowProduct(String borrowName, int personIndex) {
		Scanner s = new Scanner(System.in);
		int[] nrr = searchProduct(borrowName);
		if(nrr.length==0) {
			System.out.println("不存在此商品！");
			return false;
		}
		else if(nrr.length==1) {
			System.out.println("商品信息为：");
			System.out.println(product.get(nrr[0]).toString());
			if(product.get(nrr[0]).borrow()) {
				Record tmp = new Record(product.get(nrr[0]),member.get(personIndex));
				record.add(tmp);
				return true;
			}
			else {
				System.out.println("商品数量不足！");
				return false;
			}
		}
		else {
			System.out.println("商品信息为：");
			for(int i=0;i<nrr.length;i++) {
				System.out.println((i+1) + "、" + product.get(nrr[i]).toString());
			}
			System.out.println("请选择要借的商品( 1 ~ " + nrr.length + " )");
			int n = s.nextInt();
			n--;
			if(product.get(nrr[n]).borrow()) {
				Record tmp = new Record(product.get(nrr[n]),member.get(personIndex));
				record.add(tmp);
				return true;
			}
			else {
				System.out.println("商品数量不足！");
				return false;
			}
		}
	}
	
	// 还商品
	public boolean returnProduct(String returnName) {
		int i;
		int f = 0;
		for(i=0;i<record.size();i++) {
			if(record.get(i).product.name.equals(returnName)) {
				record.get(i).product.returnP();
				record.remove(i);
				f=1;
				return true;
			}
		}
		System.out.println("不存在此借阅记录！");
		return false;
	}
	
	// 显示所有会员信息
	public String showMember() {
		String str = "";
		for(Member tmp : member) {
			str += tmp.toString();
			str += "\n";
		}
		return str;
	}
	
	// 显示所有商品信息
	public String showProduct() {
		String str = "";
		for(Product tmp : product) {
			str += tmp.toString();
			str += "\n";
		}
		return str;
	}
	
	// 显示所有借阅信息
	public String showRecord() {
		String str = "";
		for(Record tmp : record) {
			str += tmp.toString();
			str += "\n";
		}
		return str;
	}
	
	// 会员操作菜单
	public void memberMenu() {
		System.out.println("------------------------------");
		System.out.println("|\t选择要对会员的操作\t     |");
		System.out.println("------------------------------");
		System.out.println("|\t1.新增会员\t\t     |");
		System.out.println("|\t2.删除会员\t\t     |");
		System.out.println("|\t3.修改会员电话号码\t     |");
		System.out.println("|\t4.通过编号查询电话\t     |");
		System.out.println("|\t5.通过编号打印信息\t     |");
		System.out.println("|\t6.通过姓名查询电话\t     |");
		System.out.println("|\t7.通过姓名打印信息\t     |");
		System.out.println("|\t8.返回主菜单\t     |");
		System.out.println("------------------------------");
	}
	
	// 主菜单
	public void menu() {
		System.out.println("------------------------------");
		System.out.println("|\t图书馆借阅管理系统\t     |");
		System.out.println("------------------------------");
		System.out.println("|\t1.会员操作\t\t     |");
		System.out.println("|\t2.新增商品\t\t     |");
		System.out.println("|\t3.借书或DVD\t     |");
		System.out.println("|\t4.还书或DVD\t     |");
		System.out.println("|\t5.显示所有会员信息\t     |");
		System.out.println("|\t6.显示所有商品信息\t     |");
		System.out.println("|\t7.显示所有借阅信息\t     |");
		System.out.println("|\t8.退出程序\t\t     |");
		System.out.println("------------------------------");
	}
}
