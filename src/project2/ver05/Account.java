package project2.ver05;

import java.sql.SQLException;
import java.util.Scanner;

public class Account implements MenuChoice{


	// 메뉴출력
	public void showMenu() {
		int selectNum;
		Scanner scan= new Scanner(System.in);
		while(true) {

			System.out.println("1. 계좌계설");
			System.out.println("2. 입금");
			System.out.println("3. 출금");
			System.out.println("4. 전체계좌정보출력");
			System.out.println("5. 프로그램 종료");
			System.out.print("선택: ");
			selectNum = scan.nextInt();
			scan.nextLine();

			switch(selectNum) {
			case MAKE:
				makeAccount();
				break;
			case DEPOSIT:
				depositMoney();
				break;
			case WITHDRAW:
				withdrawMoney();
				break;
			case INQUIRE:
				showAccInfo();
				break;
			case EXIT:
				System.out.println("프로그램을 종료합니다.");
				return;
			}
		} 
	}

	// 계좌개설을 위한 함수
	public void makeAccount() {
		String accNum;
		String name;
		int money;

		Scanner scan= new Scanner(System.in);

		IConnectImpl connectImpl =new IConnectImpl();
		connectImpl.connect("kosmo", "1234");
		try {
			String query= "insert into banking_tb values(?,?,?)";
			connectImpl.psmt = connectImpl.con.prepareStatement(query);

			System.out.println("***신규계좌개설***");
			System.out.println("계좌번호: ");
			accNum = scan.nextLine();
			System.out.println("고객이름: ");
			name = scan.nextLine(); 
			System.out.println("잔고: ");
			money = scan.nextInt();
			System.out.println("계좌계설이 완료되었습니다.");

			connectImpl.psmt.setString(1, accNum);
			connectImpl.psmt.setString(2, name);
			connectImpl.psmt.setInt(3, money);

			int affected= connectImpl.psmt.executeUpdate();
			System.out.println(affected + "행이 입력되었습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectImpl.close();
		}

	}

	// 입    금
	public void depositMoney() {
		Scanner scan= new Scanner(System.in);
		String search_accNum;
		int deposit_mon;

		IConnectImpl connectImpl =new IConnectImpl();
		connectImpl.connect("kosmo", "1234");
		try {
			String query ="update banking_tb set MONEY=MONEY+?  where accNum=?";

			connectImpl.psmt = connectImpl.con.prepareStatement(query);

			System.out.println("***입금***");
			System.out.println("계좌번호와 입금할 금액을 입력하세요");

			System.out.println("계좌번호: ");
			search_accNum=scan.nextLine();

			System.out.println("입금액: ");
			deposit_mon= scan.nextInt();

			connectImpl.psmt.setInt(1, deposit_mon);
			connectImpl.psmt.setString(2, search_accNum);

			System.out.println("입금이 완료되었습니다.");

			int affected= connectImpl.psmt.executeUpdate();
			System.out.println(affected + "행이 입력되었습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 출    금
	public void withdrawMoney() {
		Scanner scan= new Scanner(System.in);
		String search_accNum;
		int withdraw_mon;

		IConnectImpl connectImpl =new IConnectImpl();
		connectImpl.connect("kosmo", "1234");

		try {
			String query ="update banking_tb set MONEY=MONEY-?  where accNum=?";

			connectImpl.psmt = connectImpl.con.prepareStatement(query);

			System.out.println("***출금***");
			System.out.println("계좌번호와 입금할 금액을 입력하세요");
			System.out.println("계좌번호: ");
			search_accNum=scan.nextLine();
			System.out.println("출금액: ");
			withdraw_mon= scan.nextInt();

			connectImpl.psmt.setInt(1, withdraw_mon);
			connectImpl.psmt.setString(2,search_accNum);

			System.out.println("출금이 완료되었습니다.");

			int affected= connectImpl.psmt.executeUpdate();
			System.out.println(affected + "행이 입력되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// 전체계좌정보출력
	public void showAccInfo() {
		IConnectImpl connectImpl =new IConnectImpl();
		connectImpl.connect("kosmo", "1234");

		try {
			String query= "select * from banking_tb ";
			connectImpl.psmt = connectImpl.con.prepareStatement(query);
			connectImpl.rs=connectImpl.psmt.executeQuery();

			while (connectImpl.rs.next()) {
				String accNum = connectImpl.rs.getString(1);
				String name = connectImpl.rs.getString(2);
				int money= connectImpl.rs.getInt(3);

				System.out.println("***계좌번호출력***");
				System.out.println("------------------------");
				System.out.println("계좌번호: "+accNum);
				System.out.println("고객이름: "+name);
				System.out.println("잔고: "+money);
				System.out.println("------------------------");
			}
			System.out.println("전체계좌정보 출력이 완료되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
