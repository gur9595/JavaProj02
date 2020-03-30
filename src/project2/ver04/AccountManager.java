package project2.ver04;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;


public class AccountManager implements MenuChoice, CustomSpecialRate{
	HashSet<Account> accSet = new HashSet<Account>();

	// 메뉴출력
	public void showMenu() throws MenuSelectException{
		int selectNum;
		Scanner scan= new Scanner(System.in);
		readAccount();
		while(true) {
			try {
				System.out.println("1. 계좌계설");
				System.out.println("2. 입금");
				System.out.println("3. 출금");
				System.out.println("4. 전체계좌정보출력");
				System.out.println("5. 프로그램 종료");
				System.out.print("선택: ");
				selectNum = scan.nextInt();
				scan.nextLine();

				if(selectNum<1||selectNum>5) {
					MenuSelectException menuSelectException =new MenuSelectException();
					throw menuSelectException;
				}

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
					saveAccount();
					return;
				}				
			} catch (InputMismatchException e) {
				System.out.println("문자입력했음요 1~5숫자만 입력");
				scan.nextLine();
			}
		} 
	}

	// 계좌개설을 위한 함수
	public void makeAccount() {
		int selectNum;
		String accNum;
		String name;
		int money;
		int interest;
		String credit_rating;

		Scanner scan= new Scanner(System.in);

		System.out.println("1. 보통계좌   2. 신용신뢰계좌");
		System.out.println("선택: ");
		selectNum = scan.nextInt();
		scan.nextLine();

		if(selectNum == 1) {
			System.out.println("***신규계좌개설***");
			System.out.println("계좌번호: ");
			accNum = scan.nextLine();
			checking(accNum);

			System.out.println("고객이름: ");
			name = scan.nextLine(); 

			System.out.println("잔고: ");
			money = scan.nextInt();
			scan.nextLine();

			System.out.println("기본이자: ");
			interest = scan.nextInt();
			scan.nextLine();

			NormalAccount account =new NormalAccount(accNum, name, money,interest);
			accSet.add(account);

			System.out.println("계좌계설이 완료되었습니다.");
		}else if(selectNum ==2) {
			System.out.println("***신규계좌개설***");
			System.out.println("계좌번호: ");
			accNum = scan.nextLine();
			checking(accNum);

			System.out.println("고객이름: ");
			name = scan.nextLine(); 

			System.out.println("잔고: ");
			money = scan.nextInt();
			scan.nextLine();

			System.out.println("기본이자: ");
			interest = scan.nextInt();
			scan.nextLine();

			System.out.println("신용등급: ");
			credit_rating = scan.nextLine();

			HighCreditAccount account =new HighCreditAccount(accNum, name, money,interest,credit_rating);
			accSet.add(account);
		}
	}
	// 입금 예외처리
	public boolean depositMoney_ex(int deposit_mon,boolean check) {
		check=true;
		if(deposit_mon<0) {
			System.out.println("금액에는 음수를 입력할수 없습니다.");
			check=false;
		}
		if(deposit_mon%500!=0) {
			System.out.println("입금액은 500단위로 가능합니다.");
			check=false;
		}
		return check;
	}
	// 입    금
	public void depositMoney() {
		Scanner scan= new Scanner(System.in);
		String search_accNum;
		int deposit_mon;
		boolean check=true;

		try {
			System.out.println("***입금***");
			System.out.println("계좌번호와 입금할 금액을 입력하세요");
			System.out.println("계좌번호: ");
			search_accNum=scan.nextLine();

			System.out.println("입금액: ");
			deposit_mon= scan.nextInt();
			scan.nextLine();

			Iterator<Account> itr =accSet.iterator();
			while (itr.hasNext()) {
				Account account = itr.next();
				if(search_accNum.equals(account.accNum)) {
					if(depositMoney_ex(deposit_mon,check)==true) {

						account.money=account.interest_rate(deposit_mon);
						System.out.println("입금이 완료되었습니다.");

					}
				}else if(depositMoney_ex(deposit_mon,check)==false) {
					return;
				}
			}


		} catch (InputMismatchException e) {
			System.out.println("문자를 입려할수없습니다");
		}
	}

	// 출    금
	public void withdrawMoney() {
		Scanner scan= new Scanner(System.in);
		String search_accNum;
		int withdraw_mon;
		String check;

		System.out.println("***출금***");
		System.out.println("계좌번호와 입금할 금액을 입력하세요");
		System.out.println("계좌번호: ");
		search_accNum=scan.nextLine();

		System.out.println("출금액: ");
		withdraw_mon= scan.nextInt();
		scan.nextLine();

		Iterator<Account> itr= accSet.iterator();
		while (itr.hasNext()) {
			Account account = itr.next();

			if(withdraw_mon<0) {
				System.out.println("음수는 안됩니다.");
				return;
			}else {

				if(search_accNum.equals(account.accNum)) {

					if(account.money<withdraw_mon) {
						System.out.println("잔고가 부족합니다. 금액전체를 출금할까요?(Yes / No)");
						check = scan.nextLine();
						if(check.equals("Yes")) {
							account.money=0;
							System.out.println("전체출금완료");
						}else if(check.equals(check)) {
							return;
						}
					}

					if(withdraw_mon%1000!=0) {
						System.out.println("출금은 1000원 단위로만 출금하세요");
						return;
					}

					account.money=account.money-withdraw_mon;
					System.out.println("출금이 완료되었습니다.");						

				}

			}
		}


	}

	// 전체계좌정보출력
	public void showAccInfo() {
		System.out.println("***계좌번호출력***");
		for (Account account : accSet) {
			System.out.println("------------------------");
			account.showinfo();
			System.out.println("------------------------");
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}

	//계좌번호 중복체크
	public void checking(String iname) {
		int num = 0;
		Scanner scan =new Scanner(System.in);
		Iterator<Account> itr= accSet.iterator();

		while (itr.hasNext()) {
			Account account = itr.next();
			if(iname.equals(account.name)) {
				System.out.println("계좌 중복이야~ 1.덮쓰  2.돌아가기");
				num= scan.nextInt();
				if(num==1) {
					itr.remove();
					System.out.println("덮쓰 완료");
				}else if(num==2) {
					makeAccount();
				}
			}

		}
	}

	public void saveAccount() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("src/project2/ver04/Account.obj"));
			Iterator<Account> itr = accSet.iterator();
			while (itr.hasNext()) {
				Account account = itr.next();
				out.writeObject(account);
			}
			out.close();
		} catch (Exception e) {
			System.out.println("저장안대용~");
			e.printStackTrace();
		} 
	}

	public void readAccount() {
		try {
			ObjectInputStream in =new ObjectInputStream(
					new FileInputStream("src/project2/ver04/Account.obj"));
			while (true) {
				Account account =(Account)in.readObject();
				if(account == null) break;
				accSet.add(account);
			}
		} catch (Exception e) {
			
		}
	}

}


























