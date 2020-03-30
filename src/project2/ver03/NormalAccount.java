package project2.ver03;

public class NormalAccount extends Account{
	int interest;
	public NormalAccount(String accNum, String name, int money,int interest) {
		super(accNum, name, money);
		this.interest= interest;
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
