package htMaster;

import java.io.*;
import java.util.*;

// ����ϵͳ��
public class ManagementSystem {
	ArrayList<Member> member = new ArrayList<Member>();
	ArrayList<Product> product = new ArrayList<Product>();
	ArrayList<Record> record = new ArrayList<Record>();

	// ��ȡ�ļ�
	public boolean readFile(String fileName) {
	    File file=new File(".\\" + fileName);  
	    if(!file.exists()) {  
	        try {  
	            file.createNewFile();  
	        } catch (IOException e) {   
	            e.printStackTrace();
	            System.out.println(fileName + "�ļ�����ʧ�ܣ�");
	            return false;
	        }  
	    }
		try {
			FileReader fileReader = new FileReader(".\\" + fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			switch (fileName) {
			case "member.txt":  // ��Ա��Ϣ
				bufferedReader.readLine();
				break;
			case "product.txt":  // ��Ʒ��Ϣ
			case "record.txt":  // ���ļ�¼
				bufferedReader.readLine();
				bufferedReader.readLine();
				break;
			default:
				System.out.println("�ļ���Ϣ����");
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
			System.out.println(fileName + "�ļ���ȡʧ�ܣ�");
			return false;
		}
	}
	
	// �����ļ�
	public boolean parseFile(String str, String fileName) {
		switch (fileName) {
		case "member.txt":  // ��Ա��Ϣ
			String[] memberStr = str.split("#");
			Member memberTmp = new Member(memberStr[0],memberStr[1],memberStr[2]);
			member.add(memberTmp);
			break;
		case "product.txt":  // ��Ʒ��Ϣ
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
		case "record.txt":  // ���ļ�¼
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
			System.out.println("�ļ���Ϣ����");
			return false;
		}
		return true;
	}
	
	// д���ļ�
	public boolean writeFile(String fileName) {
		try {  // �쳣����
			FileReader fileReader = new FileReader(".\\" + fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			Writer writer = new FileWriter(new File(".\\" + fileName)); 
			String str;
			while ((str = bufferedReader.readLine()) != null) {
				writer.write("");  // ����ļ�����
			}
			switch (fileName) {
			case "member.txt":  // ��Ա��Ϣ
				writer.write("���#����#�绰\r\n");
				for(int i=0; i<member.size(); i++) {
					if(member.get(i)!=null) {
						writer.write(member.get(i).write() + "\r\n");
					}
				}
				break;
			case "product.txt":  // ��Ʒ��Ϣ
				writer.write("Book#���#����#����#����#ISBN\r\n");
				writer.write("DVD#���#����#����#ISRC\r\n");
				for(int i=0; i<product.size(); i++) {
					if(product.get(i)!=null) {
						writer.write(product.get(i).write() + "\r\n");
					}
				}
				break;
			case "record.txt":  // ���ļ�¼
				writer.write("���#����#�绰#Book#���#����#����#����#ISBN#���ʱ��\r\n");
				writer.write("���#����#�绰#DVD#���#����#����#ISRC#���ʱ��\r\n");
				for(int i=0; i<record.size(); i++) {
					if(product.get(i)!=null) {
						writer.write(record.get(i).write() + "\r\n");
					}
				}
				break;
			default:
				System.out.println("�ļ���Ϣ����");
				return false;
			}
			bufferedReader.close();
			fileReader.close();
			writer.close();
			return true;
		} catch (IOException e) { 
			e.printStackTrace(); 
			System.out.println(fileName + "�ļ���ȡʧ�ܣ�");
			return false;
		}
	}
	
	// ���ݱ�Ż��������һ�Ա----tag: 1Ϊ���ݱ��, 2Ϊ��������----
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
			System.out.println("searchMember����tag��ʶ����!");
			return -1;
		}
	}
	
	// �������Ʋ���ͼ���DVD
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
	
	// ������Ա
	public boolean newMember(String number, String name, String phoneNumber) {
		Member tmp = new Member(number, name, phoneNumber);
		member.add(tmp);
		return true;
	}
	
	// ɾ����Ա(ͨ������)
	public boolean delMember(String name) {
		int index = searchMember(name,2);
		if(index==-1) {
			return false;
		}
		System.out.println("ɾ���Ļ�Ա��ϢΪ��");
		member.get(index).toString();
		member.remove(index);
		return true;
	}
	
	// �޸Ļ�Ա�绰����
	public boolean changeMemberPhone(String name,String phoneNumber) {
		int index = searchMember(name,2);
		if(index==-1) {
			return false;
		}
		member.get(index).setPhoneNumber(phoneNumber);
		return true;
	}
	
	// ͨ����Ż�������ѯ�绰----tag: 1Ϊ���ݱ��, 2Ϊ��������----
	public String getMemberPhone(String search,int tag) {
		int index = searchMember(search,tag);
		if(index==-1) {
			return null;
		}
		return member.get(index).getPhoneNumber();
	}
	
	// ͨ����Ż�������ӡ��Ϣ----tag: 1Ϊ���ݱ��, 2Ϊ��������----
	public String getMemberInfo(String search,int tag) {
		int index = searchMember(search,tag);
		if(index==-1) {
			return null;
		}
		return member.get(index).toString();
	}
	
	// ������Ʒ----ͼ��----
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
	
	// ����Ʒ
	public boolean borrowProduct(String borrowName, int personIndex) {
		Scanner s = new Scanner(System.in);
		int[] nrr = searchProduct(borrowName);
		if(nrr.length==0) {
			System.out.println("�����ڴ���Ʒ��");
			return false;
		}
		else if(nrr.length==1) {
			System.out.println("��Ʒ��ϢΪ��");
			System.out.println(product.get(nrr[0]).toString());
			if(product.get(nrr[0]).borrow()) {
				Record tmp = new Record(product.get(nrr[0]),member.get(personIndex));
				record.add(tmp);
				return true;
			}
			else {
				System.out.println("��Ʒ�������㣡");
				return false;
			}
		}
		else {
			System.out.println("��Ʒ��ϢΪ��");
			for(int i=0;i<nrr.length;i++) {
				System.out.println((i+1) + "��" + product.get(nrr[i]).toString());
			}
			System.out.println("��ѡ��Ҫ�����Ʒ( 1 ~ " + nrr.length + " )");
			int n = s.nextInt();
			n--;
			if(product.get(nrr[n]).borrow()) {
				Record tmp = new Record(product.get(nrr[n]),member.get(personIndex));
				record.add(tmp);
				return true;
			}
			else {
				System.out.println("��Ʒ�������㣡");
				return false;
			}
		}
	}
	
	// ����Ʒ
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
		System.out.println("�����ڴ˽��ļ�¼��");
		return false;
	}
	
	// ��ʾ���л�Ա��Ϣ
	public String showMember() {
		String str = "";
		for(Member tmp : member) {
			str += tmp.toString();
			str += "\n";
		}
		return str;
	}
	
	// ��ʾ������Ʒ��Ϣ
	public String showProduct() {
		String str = "";
		for(Product tmp : product) {
			str += tmp.toString();
			str += "\n";
		}
		return str;
	}
	
	// ��ʾ���н�����Ϣ
	public String showRecord() {
		String str = "";
		for(Record tmp : record) {
			str += tmp.toString();
			str += "\n";
		}
		return str;
	}
	
	// ��Ա�����˵�
	public void memberMenu() {
		System.out.println("------------------------------");
		System.out.println("|\tѡ��Ҫ�Ի�Ա�Ĳ���\t     |");
		System.out.println("------------------------------");
		System.out.println("|\t1.������Ա\t\t     |");
		System.out.println("|\t2.ɾ����Ա\t\t     |");
		System.out.println("|\t3.�޸Ļ�Ա�绰����\t     |");
		System.out.println("|\t4.ͨ����Ų�ѯ�绰\t     |");
		System.out.println("|\t5.ͨ����Ŵ�ӡ��Ϣ\t     |");
		System.out.println("|\t6.ͨ��������ѯ�绰\t     |");
		System.out.println("|\t7.ͨ��������ӡ��Ϣ\t     |");
		System.out.println("|\t8.�������˵�\t     |");
		System.out.println("------------------------------");
	}
	
	// ���˵�
	public void menu() {
		System.out.println("------------------------------");
		System.out.println("|\tͼ��ݽ��Ĺ���ϵͳ\t     |");
		System.out.println("------------------------------");
		System.out.println("|\t1.��Ա����\t\t     |");
		System.out.println("|\t2.������Ʒ\t\t     |");
		System.out.println("|\t3.�����DVD\t     |");
		System.out.println("|\t4.�����DVD\t     |");
		System.out.println("|\t5.��ʾ���л�Ա��Ϣ\t     |");
		System.out.println("|\t6.��ʾ������Ʒ��Ϣ\t     |");
		System.out.println("|\t7.��ʾ���н�����Ϣ\t     |");
		System.out.println("|\t8.�˳�����\t\t     |");
		System.out.println("------------------------------");
	}
}
