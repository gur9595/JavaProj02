package game;

import java.util.Objects;
import java.util.Scanner;

import project2.ver05.Account;

public class PuzzleGame {

	String[][] arr= { {"1","2","3"} , 
			{"4","5","6"} , 
			{"7","8","X"} };

	String[][] arr2={ {"1","2","3"} , 
			{"4","5","6"} , 
			{"7","8","X"} };

	int xix=2,xjx=2;

	public void Shuffle() {
		String xx;
		Scanner scan =new Scanner(System.in);

		for(int i=0; i<3; i++) {
			for(int j=0; j<3;j++) {
				System.out.printf(arr[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("---------------------------------------");
		// 셔플
		int aa=1;
		while(aa<10) {
			try {
				int randNum=(int)(Math.random()*4);
				if(randNum==0) {
					//findX();
					xx=arr[xix][xjx];
					arr[xix][xjx]=arr[xix][xjx+1];
					arr[xix][xjx+1]=xx;
					xjx++;
					aa++;
				}else if(randNum==1) {
					//findX();
					xx=arr[xix][xjx];
					arr[xix][xjx]=arr[xix][xjx-1];
					arr[xix][xjx-1]=xx;
					xjx--;
					aa++;
				}else if(randNum==2) {
					//findX();
					xx=arr[xix][xjx];
					arr[xix][xjx]=arr[xix+1][xjx];
					arr[xix+1][xjx]=xx;
					xix++;
					aa++;
				}else if(randNum==3) {
					//findX();
					xx=arr[xix][xjx];
					arr[xix][xjx]=arr[xix-1][xjx];
					arr[xix-1][xjx]=xx;
					xix--;
					aa++;
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				aa++;
			}
			
		}
		for(int i=0; i<3; i++) {
			for(int j=0; j<3;j++) {
				System.out.printf(arr[i][j]+" ");
			}
			System.out.println();
		}


	}

	public void moveX() {
		String select, xx;
		Scanner scan= new Scanner(System.in);
		//이동하기
		while(true) {
			System.out.println("[이동] a:Left d:Right w:Up s:Down");
			System.out.println("[종료] x:Exit");
			System.out.println("키를 입력해주세요 :");
			select = scan.nextLine();
			try {
				switch(select) {
				case "a":
					//findX();

					xx=arr[xix][xjx];
					arr[xix][xjx]=arr[xix][xjx+1];
					arr[xix][xjx+1]=xx;
					nowX();
					xjx++;
					break;
				case "d":
					//findX();
 
					xx=arr[xix][xjx];
					arr[xix][xjx]=arr[xix][xjx-1];
					arr[xix][xjx-1]=xx;
					nowX();
					xjx--;
					break;

				case "w":
					//findX();

					xx=arr[xix][xjx];
					arr[xix][xjx]=arr[xix+1][xjx];
					arr[xix+1][xjx]=xx;
					nowX();
					xix++;
					break;

				case "s":
					//findX();

					xx=arr[xix][xjx];
					arr[xix][xjx]=arr[xix-1][xjx];
					arr[xix-1][xjx]=xx;
					nowX();
					xix--;
					break;

				case "x":
					return;
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("************");
				System.out.println("***이동불가***");
				System.out.println("************");
				nowX();
			}

			restart();
		}

	}

//	public void findX() {
//
//		for(int i=0; i<3; i++) {
//			for(int j=0; j<3;j++) {
//				if(arr[i][j].equals("X")) {
//					xix=i;
//					xjx=j;
//				}
//			}
//		}
//	}

	public void nowX() {
		for(int i=0; i<3; i++) {
			for(int j=0; j<3;j++) {
				System.out.printf(arr[i][j]+" ");
			}
			System.out.println();
		}
	}

	public void restart() {
		if(Objects.deepEquals(arr, arr2)) {
			System.out.println("==^^정답입니다^^==");
			for(int i=0; i<3; i++) {
				for(int j=0; j<3;j++) {
					System.out.printf(arr[i][j]+" ");
				}
				System.out.println();
			}
			System.out.println("재시작하겠습니까? (y 누르면 재시작, 나머지는 종료)");
			Scanner scan= new Scanner(System.in);
			String yy;
			yy=scan.nextLine();
			if(yy.equals("y")) {
				Shuffle();
				moveX();
			}else {
				Account account =new Account();
				account.showMenu();
			}
			
		}
		

	}
	
	public void startGame() {
		Shuffle();
		moveX();
	}

	public static void main(String[] args){
		PuzzleGame game =new PuzzleGame();
		game.startGame();
	}
}














