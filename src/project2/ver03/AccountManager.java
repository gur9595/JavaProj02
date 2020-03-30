package project2.ver03;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AccountManager implements MenuChoice, CustomSpecialRate{
	
	private Account[] accounts;
	private int numA;

	public AccountManager(int num) {
		accounts = new Account[num];
		numA=0;
	}

	// 메뉴출력
	public void showMenu() throws MenuSelectException{
		int selectNum;
		Scanner scan= new Scanner(System.in);
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

			System.out.println("고객이름: ");
			name = scan.nextLine(); 

			System.out.println("잔고: ");
			money = scan.nextInt();
			scan.nextLine();

			System.out.println("기본이자: ");
			interest = scan.nextInt();
			scan.nextLine();

			NormalAccount account =new NormalAccount(accNum, name, money,interest);
			accounts[numA++]= account;

			System.out.println("계좌계설이 완료되었습니다.");
		}else if(selectNum ==2) {
			System.out.println("***신규계좌개설***");
			System.out.println("계좌번호: ");
			accNum = scan.nextLine();

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
			accounts[numA++]= account;
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

			if(depositMoney_ex(deposit_mon,check)==true) {
				for(int i=0 ; i<numA ; i++) {
					if(search_accNum.compareTo(accounts[i].accNum)==0) {
						accounts[i].money=accounts[i].interest_rate(deposit_mon);
						System.out.println("입금이 완료되었습니다.");
					}
				}
			}else if(depositMoney_ex(deposit_mon,check)==false) {
				return;
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
		if(withdraw_mon<0) {
			System.out.println("음수는 안됩니다.");
			return;
		}else {
			for(int i=0 ; i<numA ; i++) {
				if(search_accNum.compareTo(accounts[i].accNum)==0) {
					
					if(accounts[i].money<withdraw_mon) {
						System.out.println("잔고가 부족합니다. 금액전체를 출금할까요?(Yes / No)");
						check = scan.nextLine();
						if(check.equals("Yes")) {
							accounts[i].money=0;
							System.out.println("전체출금완료");
						}else if(check.equals(check)) {
							return;
						}
					}
					
					if(withdraw_mon%1000!=0) {
						System.out.println("출금은 1000원 단위로만 출금하세요");
						return;
					}
					
					accounts[i].money=accounts[i].money-withdraw_mon;
					System.out.println("출금이 완료되었습니다.");						

				}
			}			
		}

	}

	// 전체계좌정보출력
	public void showAccInfo() {
		System.out.println("***계좌번호출력***");
		for (int i=0; i<numA; i++) {
			System.out.println("------------------------");
			accounts[i].showinfo();
			System.out.println("------------------------");
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}
}
