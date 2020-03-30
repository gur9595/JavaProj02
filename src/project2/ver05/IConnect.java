package project2.ver05;

public interface IConnect {
	String ORACLE_DIVER="oracle.jdbc.OracleDriver";
	String ORACLE_URL="jdbc:oracle:thin://@localhost:1521:orcl";
	
	void connect(String user, String pass);
	void close();
	String scanValue(String title);
}
