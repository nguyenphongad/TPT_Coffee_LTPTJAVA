package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	public static Connection con = null;
	private static ConnectDB instance = new ConnectDB();

	public static ConnectDB getInstance() {
		return instance;
	}

	public void connect() throws SQLException {
		// Cập nhật chuỗi kết nối MySQL
		String url = "jdbc:mysql://localhost:3306/VPTCoffee?useSSL=false&serverTimezone=UTC";
		String user = "root";  // Tên người dùng MySQL của bạn
		String pass = "root";  // Mật khẩu MySQL của bạn
		con = DriverManager.getConnection(url, user, pass);
	}

	public void disconnect() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static Connection getConnection() throws SQLException {
		instance.connect();
		return con;
	}
}
