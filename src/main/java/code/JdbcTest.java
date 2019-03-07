package code;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;


public class JdbcTest {

	private  static Connection getConnnet() {

		String url = "jdbc:mysql://192.168.1.32:3306/online?useUnicode=true&characterEncoding=UTF-8";
		String username = "root";
		String password = "0bee89b07a248e27c83";
		try {
			Connection con = DriverManager.getConnection(url, username, password);
			return con;
		} catch (SQLException se) {
			System.out.println("数据库连接失败！");
			se.printStackTrace();
			return null;
		}

	}
	
	public static void excuteSql(String sql){
		Connection connnet = getConnnet();
		try {
			Statement stmt = connnet.createStatement() ;
			stmt.executeUpdate(sql);
			stmt.close();
			connnet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}   
 
	}

	
	public static void excuteBatchSql(List<String> sqls){
		Connection connnet = getConnnet();
		try {
			Statement stmt = connnet.createStatement() ;
			for (String sql : sqls) {
				stmt.addBatch(sql);
			}
			int[] rows = stmt.executeBatch();  
		    System.out.println("Row count:" + Arrays.toString(rows)); 
			stmt.close();
			connnet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}   
 
	}
}
