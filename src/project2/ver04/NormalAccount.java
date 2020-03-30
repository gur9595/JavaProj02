package project2.ver04;

public class NormalAccount extends Account{
	int interest;
	public NormalAccount(String accNum, String name, int money,int interest) {
		super(accNum, name, money);
		this.interest= interest;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + interest;
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



	@Override
	public void showinfo() {
		
		super.showinfo();
		System.out.println("기본이자: "+interest);
	}
	
	@Override
	public int interest_rate(int imoney) {
		
		return money+(money*interest/100)+imoney;
	}
}
