package project2.ver04;

public class HighCreditAccount extends Account implements CustomSpecialRate{
	int interest;
	String credit_rating;
	public HighCreditAccount(String accNum, String name, int money, int interest, String credit_rating) {
		super(accNum, name, money);
		this.interest=interest;
		this.credit_rating=credit_rating;
		}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((credit_rating == null) ? 0 : credit_rating.hashCode());
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
		System.out.println("기본이자: " + interest);
		System.out.println("신용등급: " + credit_rating);
	}
	
	@Override
	public int interest_rate(int imoney) {
		int rate=0;
		switch (credit_rating) {
		case "A":
			rate=A;
			break;
		case "B":
			rate=B;
			break;
		case "C":
			rate=C;
			break;
		default:
			break;
		}
		return money+(money*interest/100)+money*rate/100+imoney;
	}
}
