package project2.ver05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class IConnectImpl implements IConnect{

	public PreparedStatement psmt;
	public Connection con;
	public Statement stmt;
	public ResultSet rs;

	public IConnectImpl() {
		
	}
	
	public IConnectImpl(String user, String pass) {
		System.out.println("IConnectImpl 인자생성자 호출");
		try {
			//드라이버로드
			Class.forName(ORACLE_DIVER);
			//DB연결
			connect(user, pass);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
	}
	
	@Override
	public void connect(String user, String pass) {
		try {
			con= DriverManager.getConnection(ORACLE_URL,user,pass);
		} catch (SQLException e) {
			System.out.println("데이터베이스 연결 오류");
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		try {
			if(con!=null) con.close();
			if(psmt!=null) psmt.close();
			if(rs!=null) rs.close();

		} catch (Exception e) {
			System.out.println("자원 반납시 오류 발생");
			e.printStackTrace();
		}

	}
	
	@Override
	public String scanValue(String title) {
		Scanner scan = new Scanner(System.in);
		System.out.println(title+"을(를) 입력(exit->종료): ");
		String inputStr =scan.nextLine();
		
		if("EXIT".equalsIgnoreCase(inputStr)) {
			System.out.println("프로그램을 종료합니다.");
			close();
			System.exit(0);
		}
		
		return inputStr;
	}

}
