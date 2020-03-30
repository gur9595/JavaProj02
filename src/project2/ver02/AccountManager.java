package project2.ver02;

import java.util.Scanner;

public class AccountManager implements MenuChoice, CustomSpecialRate{

	private Account[] accounts;
	private int numA;
	
	public AccountManager(int num) {
		accounts = new Account[num];
		numA=0;

	}

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

	// 입    금
	public void depositMoney() {
		Scanner scan= new Scanner(System.in);
		String search_accNum;
		int deposit_mon;

		System.out.println("***입금***");
		System.out.println("계좌번호와 입금할 금액을 입력하세요");
		System.out.println("계좌번호: ");
		search_accNum=scan.nextLine();
		System.out.println("입금액: ");
		deposit_mon= scan.nextInt();
		for(int i=0 ; i<numA ; i++) {
			if(search_accNum.compareTo(accounts[i].accNum)==0) {
				accounts[i].money=accounts[i].interest_rate(deposit_mon);
			}
		}
		System.out.println("입금이 완료되었습니다.");
	}

	// 출    금
	public void withdrawMoney() {
		Scanner scan= new Scanner(System.in);
		String search_accNum;
		int withdraw_mon;

		System.out.println("***출금***");
		System.out.println("계좌번호와 입금할 금액을 입력하세요");
		System.out.println("계좌번호: ");
		search_accNum=scan.nextLine();
		System.out.println("출금액: ");
		withdraw_mon= scan.nextInt();
		for(int i=0 ; i<numA ; i++) {
			if(search_accNum.compareTo(accounts[i].accNum)==0) {
				accounts[i].money=accounts[i].money-withdraw_mon;
			}
		}
		System.out.println("출금이 완료되었습니다.");
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
