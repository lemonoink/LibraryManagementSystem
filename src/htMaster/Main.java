package htMaster;

import java.util.Scanner;

// ������
public class Main {
	static ManagementSystem sys = new ManagementSystem();
	
	// �Ի�Ա�Ĳ���
	public static void memberOperation() {
		Scanner s = new Scanner(System.in);
		boolean judge = true;
		while(judge) {
			sys.memberMenu();
			int menuChoose = s.nextInt();
			switch(menuChoose) {
			case 1:  // ������Ա
				System.out.println("�����������Ļ�Ա���");
				String number1 = s.next();
				System.out.println("�����������Ļ�Ա����");
				String name1 = s.next();
				System.out.println("�����������Ļ�Ա�ֻ���");
				String phoneNumber1 = s.next();
				if(sys.newMember(number1, name1, phoneNumber1)) {
					System.out.println("��ӳɹ���");
				}
				break;
			case 2:  // ɾ����Ա
				System.out.println("������Ҫɾ���Ļ�Ա����");
				String name2 = s.next();
				if(sys.delMember(name2)) {
					System.out.println("ɾ���ɹ���");
				}
				else {
					System.out.println("�����ڴ˻�Ա��");
				}
				break;
			case 3:  // �޸Ļ�Ա�绰����
				System.out.println("�������Ա����");
				String name3 = s.next();
				System.out.println("�������µĵ绰����");
				String phoneNumber3 = s.next();
				if(sys.changeMemberPhone(name3, phoneNumber3)) {
					System.out.println("�޸ĳɹ���");
				}
				else {
					System.out.println("�����ڴ˻�Ա���޸�ʧ�ܣ�");
				}
				break;
			case 4:  // ͨ����Ų�ѯ�绰
				System.out.println("������Ҫ��ѯ�ı��");
				String number4 = s.next();
				String phoneNumber4 = sys.getMemberPhone(number4,1);
				if(phoneNumber4==null) {
					System.out.println("�����ڴ˻�Ա��");
				}
				else {
					System.out.println("�˻�Ա�ĵ绰����Ϊ��"+phoneNumber4);
				}
				break;
			case 5:  // ͨ����Ŵ�ӡ��Ϣ
				System.out.println("������Ҫ��ѯ�ı��");
				String number5 = s.next();
				String information5 = sys.getMemberInfo(number5,1);
				if(information5==null) {
					System.out.println("�����ڴ˻�Ա��");
				}
				else {
					System.out.println(information5);
				}
				break;
			case 6:  // ͨ��������ѯ�绰
				System.out.println("������Ҫ��ѯ������");
				String number6 = s.next();
				String phoneNumber6 = sys.getMemberPhone(number6,2);
				if(phoneNumber6==null) {
					System.out.println("�����ڴ˻�Ա��");
				}
				else {
					System.out.println("�˻�Ա�ĵ绰����Ϊ��"+phoneNumber6);
				}
				break;
			case 7:  // ͨ��������ӡ��Ϣ
				System.out.println("������Ҫ��ѯ������");
				String number7 = s.next();
				String information7 = sys.getMemberInfo(number7,2);
				if(information7==null) {
					System.out.println("�����ڴ˻�Ա��");
				}
				else {
					System.out.println(information7);
				}
				break;
			case 8:  // �������˵�
				judge = false;
				break;
			default:
				System.out.println("��������");
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		boolean judge = true;
		if(!sys.readFile("member.txt")) {
			judge = false;
		}	
		if(!sys.readFile("product.txt")) {
			judge = false;
		}
		if(!sys.readFile("record.txt")) {
			judge = false;
		}
		while(judge) {
			sys.menu();
			int menuChoose = s.nextInt();
			switch(menuChoose) {
			case 1:  // ��Ա����
				memberOperation();
				break;
			case 2:  // ������Ʒ
				System.out.println("������������Ʒ����, 1��ͼ��, 2��DVD");
				int f = s.nextInt();
				if(f==1) {
					System.out.println("����������ͼ����");
					String attributeNumber = s.next();
					System.out.println("����������ͼ������");
					String name = s.next();
					System.out.println("����������ͼ������");
					int number = s.nextInt();
					System.out.println("����������ͼ������");
					String author = s.next();
					System.out.println("����������ͼ��ISBN��");
					String ISBN = s.next();
					if(sys.newProduct(attributeNumber, name, number, author, ISBN)) {
						System.out.println("��ӳɹ���");
					}
				}
				else if(f==2) {
					System.out.println("����������DVD���");
					String attributeNumber = s.next();
					System.out.println("����������DVD����");
					String name = s.next();
					System.out.println("����������DVD����");
					int number = s.nextInt();
					System.out.println("����������DVD��ISRC��");
					String ISRC = s.next();
					if(sys.newProduct(attributeNumber, name, number, ISRC)) {
						System.out.println("��ӳɹ���");
					}
				}
				else {
					System.out.println("��������");
				}
				break;
			case 3:  // �����DVD
				System.out.println("�������Ա��");
				String borrowPerson = s.next();
				int index = sys.searchMember(borrowPerson,2);
				if(index==-1) {
					System.out.println("�����ڴ˻�Ա��");
					break;
				}
				System.out.println("������Ҫ�����Ʒ��");
				String borrowName = s.next();
				if(sys.borrowProduct(borrowName,index)) {
					System.out.println("��ɹ���");
				}
				break;
			case 4:  // �����DVD
				System.out.println("������Ҫ������Ʒ��");
				String returnName = s.next();
				
				if(sys.returnProduct(returnName)) {
					System.out.println("���ɹ���");
				}
				break;
			case 5:  // ��ʾ���л�Ա��Ϣ
				System.out.println(sys.showMember());
				break;
			case 6:  // ��ʾ������Ʒ��Ϣ
				System.out.println(sys.showProduct());
				break;
			case 7:  // ��ʾ���н�����Ϣ
				System.out.println(sys.showRecord());
				break;
			case 8:  // �˳�����
				judge = false;
				break;
			default:
				System.out.println("��������");
				break;
			}
		}
		sys.writeFile("member.txt");
		sys.writeFile("product.txt");
		sys.writeFile("record.txt");
	}

}


