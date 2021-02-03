package dbsupport;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
public class DBSupport {
	 public static Connection establishConnection() throws SQLException {
	        Connection connect = null;
	        Properties connectProps = new Properties();
	        connectProps.put("dbms", "mysql");
	        connectProps.put("user", "root");
	        connectProps.put("password", "toor");
	        connectProps.put("useSSL", "false");

	        String C = "jdbc:mysql://localhost:3306/";

	        connect = (Connection) DriverManager.getConnection(C, connectProps);
	        return connect;
	    }

}
