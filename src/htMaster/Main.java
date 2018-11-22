package htMaster;

import java.util.Scanner;

// 测试类
public class Main {
	static ManagementSystem sys = new ManagementSystem();
	
	// 对会员的操作
	public static void memberOperation() {
		Scanner s = new Scanner(System.in);
		boolean judge = true;
		while(judge) {
			sys.memberMenu();
			int menuChoose = s.nextInt();
			switch(menuChoose) {
			case 1:  // 新增会员
				System.out.println("请输入新增的会员编号");
				String number1 = s.next();
				System.out.println("请输入新增的会员姓名");
				String name1 = s.next();
				System.out.println("请输入新增的会员手机号");
				String phoneNumber1 = s.next();
				if(sys.newMember(number1, name1, phoneNumber1)) {
					System.out.println("添加成功！");
				}
				break;
			case 2:  // 删除会员
				System.out.println("请输入要删除的会员姓名");
				String name2 = s.next();
				if(sys.delMember(name2)) {
					System.out.println("删除成功！");
				}
				else {
					System.out.println("不存在此会员！");
				}
				break;
			case 3:  // 修改会员电话号码
				System.out.println("请输入会员姓名");
				String name3 = s.next();
				System.out.println("请输入新的电话号码");
				String phoneNumber3 = s.next();
				if(sys.changeMemberPhone(name3, phoneNumber3)) {
					System.out.println("修改成功！");
				}
				else {
					System.out.println("不存在此会员，修改失败！");
				}
				break;
			case 4:  // 通过编号查询电话
				System.out.println("请输入要查询的编号");
				String number4 = s.next();
				String phoneNumber4 = sys.getMemberPhone(number4,1);
				if(phoneNumber4==null) {
					System.out.println("不存在此会员！");
				}
				else {
					System.out.println("此会员的电话号码为："+phoneNumber4);
				}
				break;
			case 5:  // 通过编号打印信息
				System.out.println("请输入要查询的编号");
				String number5 = s.next();
				String information5 = sys.getMemberInfo(number5,1);
				if(information5==null) {
					System.out.println("不存在此会员！");
				}
				else {
					System.out.println(information5);
				}
				break;
			case 6:  // 通过姓名查询电话
				System.out.println("请输入要查询的姓名");
				String number6 = s.next();
				String phoneNumber6 = sys.getMemberPhone(number6,2);
				if(phoneNumber6==null) {
					System.out.println("不存在此会员！");
				}
				else {
					System.out.println("此会员的电话号码为："+phoneNumber6);
				}
				break;
			case 7:  // 通过姓名打印信息
				System.out.println("请输入要查询的姓名");
				String number7 = s.next();
				String information7 = sys.getMemberInfo(number7,2);
				if(information7==null) {
					System.out.println("不存在此会员！");
				}
				else {
					System.out.println(information7);
				}
				break;
			case 8:  // 返回主菜单
				judge = false;
				break;
			default:
				System.out.println("输入有误！");
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
			case 1:  // 会员操作
				memberOperation();
				break;
			case 2:  // 新增商品
				System.out.println("请输入新增商品种类, 1、图书, 2、DVD");
				int f = s.nextInt();
				if(f==1) {
					System.out.println("请输入新增图书编号");
					String attributeNumber = s.next();
					System.out.println("请输入新增图书名称");
					String name = s.next();
					System.out.println("请输入新增图书数量");
					int number = s.nextInt();
					System.out.println("请输入新增图书作者");
					String author = s.next();
					System.out.println("请输入新增图书ISBN号");
					String ISBN = s.next();
					if(sys.newProduct(attributeNumber, name, number, author, ISBN)) {
						System.out.println("添加成功！");
					}
				}
				else if(f==2) {
					System.out.println("请输入新增DVD编号");
					String attributeNumber = s.next();
					System.out.println("请输入新增DVD名称");
					String name = s.next();
					System.out.println("请输入新增DVD数量");
					int number = s.nextInt();
					System.out.println("请输入新增DVD的ISRC号");
					String ISRC = s.next();
					if(sys.newProduct(attributeNumber, name, number, ISRC)) {
						System.out.println("添加成功！");
					}
				}
				else {
					System.out.println("输入有误！");
				}
				break;
			case 3:  // 借书或DVD
				System.out.println("请输入会员名");
				String borrowPerson = s.next();
				int index = sys.searchMember(borrowPerson,2);
				if(index==-1) {
					System.out.println("不存在此会员！");
					break;
				}
				System.out.println("请输入要借的商品名");
				String borrowName = s.next();
				if(sys.borrowProduct(borrowName,index)) {
					System.out.println("借成功！");
				}
				break;
			case 4:  // 还书或DVD
				System.out.println("请输入要还的商品名");
				String returnName = s.next();
				
				if(sys.returnProduct(returnName)) {
					System.out.println("还成功！");
				}
				break;
			case 5:  // 显示所有会员信息
				System.out.println(sys.showMember());
				break;
			case 6:  // 显示所有商品信息
				System.out.println(sys.showProduct());
				break;
			case 7:  // 显示所有借阅信息
				System.out.println(sys.showRecord());
				break;
			case 8:  // 退出程序
				judge = false;
				break;
			default:
				System.out.println("输入有误！");
				break;
			}
		}
		sys.writeFile("member.txt");
		sys.writeFile("product.txt");
		sys.writeFile("record.txt");
	}

}


