import java.sql.DriverManager;
import java.sql.Connection;

//Class is responsible for connecting to MYSQL database
public class DB {
	static final String userName = "******";
	static final String password = "******";

	 public static Connection getConnection() { 
		 System.out.println("Connecting to database....");
		 Connection conn = null;
		 try { 
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 conn = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/ballen15db?serverTimezone=EST", userName, password);
			 System.out.println("Database Connected!");
		 } catch(Exception e) { 
			 System.out.println(e);
		 }
		 return conn;
	 }
}
