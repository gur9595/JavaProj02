package project2.ver02;

public class HighCreditAccount extends Account implements CustomSpecialRate{
	int interest;
	String credit_rating;
	public HighCreditAccount(String accNum, String name, int money, int interest, String credit_rating) {
		super(accNum, name, money);
		this.interest=interest;
		this.credit_rating=credit_rating;
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
