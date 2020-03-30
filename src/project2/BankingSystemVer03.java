package project2;

import project2.ver03.AccountManager;
import project2.ver03.MenuSelectException;

public class BankingSystemVer03 {

	public static void main(String[] args) throws MenuSelectException {
		AccountManager accountManager =new AccountManager(50);
		accountManager.showMenu();
	}
}
