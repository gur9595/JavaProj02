package project2.ver02;

public class Account{
	
	String accNum;
	String name;
	int money;
	
	public Account(String accNum, String name, int money) {
		
		this.accNum = accNum;
		this.name = name;
		this.money = money;
	}
	
	public void showinfo() {
		System.out.println("계좌번호: "+accNum);
		System.out.println("이름: "+name);
		System.out.println("잔액: "+money);
	}
	public int interest_rate(int imoney) {
		return imoney;
	}
	
}
