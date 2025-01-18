package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.KhachHang;

public class KhachHang_DAO {
	
	
	public KhachHang getKhachHangTheoSDT(String sdt) {
		KhachHang khachHang = new KhachHang();
		Connection connection = null;
		PreparedStatement preparedStatement =null;
		try {
			connection  = ConnectDB.getConnection();
			preparedStatement = connection.prepareStatement("select * from [dbo].[KhachHang] where [soDienThoai] = ?");
			preparedStatement.setString(1, sdt);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				khachHang.setTenKH(resultSet.getString("tenKH"));
				khachHang.setSoDienThoai(resultSet.getString("soDienThoai"));
				khachHang.setNgaySinh(resultSet.getDate("ngaySinh"));
				khachHang.setTongDiemTichLuy(resultSet.getInt("tongDiemTichLuy"));		
			}
			
			preparedStatement.close();
			resultSet.close();
			
			return khachHang;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	
	// tìm sdt tích điểm
	public ArrayList<KhachHang> timSoDienThoaiKhachHang(int sdt ){
		ArrayList<KhachHang> listKH = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		PreparedStatement st =null;
		ResultSet rs = null;
		
		try {
			Connection conn  = ConnectDB.getConnection();
			
			String query = "select * from [dbo].[KhachHang] where [soDienThoai] = ?";
			st = conn.prepareStatement(query);
			
			st.setInt(1, sdt);
			rs = st.executeQuery();
			while(rs.next()) {
				KhachHang kh = new KhachHang(
						rs.getString("soDienThoai"),
						rs.getString("tenKH"),
						rs.getDate("ngaySinh"),
						rs.getInt("tongDiemTichLuy")
						);
				listKH.add(kh);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return listKH;
	}
	// đăng ký số điện thoại tích điểm
	public boolean dangKySdtTichDiem(KhachHang khnew) {
		ConnectDB.getInstance();
		PreparedStatement st = null;
		int n=0;
		try {
			Connection conn = ConnectDB.getConnection();
			String query = "insert into [dbo].[KhachHang] values(?,?,?,?)";
			st = conn.prepareStatement(query);
			
			
			st.setString(1, khnew.getSoDienThoai());
			st.setString(2, khnew.getTenKH());
			st.setDate(3, new Date (khnew.getNgaySinh().getTime()));
			st.setInt(4, khnew.getTongDiemTichLuy());
			
			n = st.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n >0;
	}
	
	
	
}




















