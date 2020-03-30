package project2.ver01;

import java.util.Scanner;

public class Account implements MenuChoice{
	
	private Account[] accounts;
	private int numA;
	String accNum;
	String name;
	int money;
	
	public Account(int num) {
		accounts = new Account[num];
		numA=0;
	}
	
	public Account(String accNum, String name, int money) {
		
		this.accNum = accNum;
		this.name = name;
		this.money = money;
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
		String accNum;
		String name;
		int money;
		
		Scanner scan= new Scanner(System.in);
		
		System.out.println("***신규계좌개설***");
		System.out.println("계좌번호: ");
		accNum = scan.nextLine();
		System.out.println("고객이름: ");
		name = scan.nextLine(); 
		System.out.println("잔고: ");
		money = scan.nextInt();
		System.out.println("계좌계설이 완료되었습니다.");
		
		Account account =new Account(accNum, name, money);
		accounts[numA++]= account;
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
				accounts[i].money=accounts[i].money+deposit_mon;
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
			System.out.println("계좌번호: "+accounts[i].accNum);
			System.out.println("고객이름: "+accounts[i].name);
			System.out.println("잔고: "+accounts[i].money);
			System.out.println("------------------------");
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}
}
