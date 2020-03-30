package project2.ver04;

import java.io.Serializable;

public class Account implements Serializable{
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accNum == null) ? 0 : accNum.hashCode());
		result = prime * result + money;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Account account = (Account)obj;
		if(account.accNum.equals(this.accNum)) {
			return true;
		}else {
			return false;
		}
	}
	
	
	
}
