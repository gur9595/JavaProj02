package project2.ver04;

public class MenuSelectException extends Exception{
	public MenuSelectException() {
		System.out.println("1~5 숫자만 입력해용");
		AccountManager accountManager =new AccountManager();
		try {
			accountManager.showMenu();
		} catch (MenuSelectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
