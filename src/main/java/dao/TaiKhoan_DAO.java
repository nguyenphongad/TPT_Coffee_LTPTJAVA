package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.NhanVien;
import entity.TaiKhoan;

public class TaiKhoan_DAO {
	public ArrayList<TaiKhoan> getAllTaiKhoan(){
		ArrayList<TaiKhoan> listTK = new ArrayList<TaiKhoan>();
		ConnectDB.getInstance();
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			connection = ConnectDB.getConnection();
			String querry = "SELECT * from TaiKhoan";
			statement = connection.prepareStatement(querry);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				NhanVien nv = new NhanVien(rs.getString("maNV"));
				TaiKhoan tKhoan = new TaiKhoan(nv, rs.getString("matKhau"));
				listTK.add(tKhoan);
			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			if (statement != null)
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		     if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		     
		}
		return listTK;
	}
}
