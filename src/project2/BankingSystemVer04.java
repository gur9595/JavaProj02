package project2;

import project2.ver04.AccountManager;
import project2.ver04.MenuSelectException;

public class BankingSystemVer04 {

	public static void main(String[] args) throws MenuSelectException {
		AccountManager accountManager =new AccountManager();
		accountManager.showMenu();
	}
}
