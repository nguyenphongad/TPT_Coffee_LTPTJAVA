package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import jakarta.persistence.EntityManager;
import util.EntityManagerFactory;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class HoaDon_DAO {
	private EntityManager em = EntityManagerFactory.getInstance().getEntityManager();
	

	public HoaDon getHoaDon(ResultSet resultSet) {
		try {
			em.getTransaction().begin();
			String maHD = resultSet.getString("maHD");
			Timestamp ngayLap = resultSet.getTimestamp("ngayLap");
			String maNV = resultSet.getString("maNV");
			String sdt = resultSet.getString("soDienThoai");
			int tongTien = resultSet.getInt("tongTien");
			
			KhachHang_DAO khachHang_DAO = new KhachHang_DAO();
			NhanVien_DAO nhanVien_DAO = new NhanVien_DAO();
			
			NhanVien nv = nhanVien_DAO.getNhanVienTheoMa(maNV);
			KhachHang kh = khachHang_DAO.getKhachHangTheoSDT(sdt);

			em.getTransaction().commit();

			return new HoaDon(maHD, ngayLap.toLocalDateTime(), nv, kh, tongTien);

		} catch (Exception e) {
			// TODO: handle exception
			em.getTransaction().rollback();
			e.printStackTrace();
		} 
		return null;
	}
	
	public List<HoaDon> getAllHoaDon() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<HoaDon> hoaDonList = new ArrayList<HoaDon>();
		
		try {
			connection = ConnectDB.getConnection();
			preparedStatement = connection.prepareStatement("Select * From HoaDon");
			
			resultSet = preparedStatement.executeQuery();

	            while (resultSet.next()) {
	                HoaDon hoaDon = getHoaDon(resultSet);
	                hoaDonList.add(hoaDon);
	            }

	            resultSet.close();
	            preparedStatement.close();   
	            
	            return hoaDonList;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		return null;
	}
	
	public HoaDon getHoaDontheoMa(String maHD) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		HoaDon hoaDon = null;
		
		try {
			connection = ConnectDB.getConnection();
			preparedStatement = connection.prepareStatement("Select * From HoaDon Where maHD = ?");
			preparedStatement.setString(1, maHD);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) hoaDon = getHoaDon(resultSet);
			
			preparedStatement.close();
			resultSet.close();
			
			return hoaDon;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}
	
	public int tongSoHoaDonTheoThang(int thang) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int soHoaDon = 0;
		try {
			connection = ConnectDB.getConnection();
			preparedStatement = connection.prepareStatement("Select count(*) as soHoaDon From HoaDon Where Month(ngayLap) = ? ");
			preparedStatement.setInt(1, thang);
			resultSet = preparedStatement.executeQuery();
		
			if(resultSet.next()) soHoaDon = resultSet.getInt("soHoaDon");
		
			preparedStatement.close();
			resultSet.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return soHoaDon;
	}
	
	public double tongDoanhThuTheoTungNgayTrongThang(int day, int month) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		double tongDoanhThu = 0;
		
		try {
			connection = ConnectDB.getConnection();
			preparedStatement = connection.prepareStatement("Select sum(tongTien) as tongDoanhThu From HoaDon Where Month(ngayLap) = ? and Day(ngayLap) = ?");
			preparedStatement.setInt(1, month);
			preparedStatement.setInt(2, day);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) tongDoanhThu = resultSet.getDouble("tongDoanhThu");
			preparedStatement.close();
			resultSet.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return tongDoanhThu;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// lay ma HD tụ dong
	public String getMaHDTuDong() {
		ConnectDB.getInstance();
		PreparedStatement st = null;
		ResultSet rs = null;
		String maHDLonNhat = null;
		try {
			Connection conn = ConnectDB.getConnection();
			String querry = "SELECT MAX(maHD) AS maHDMax FROM HoaDon";
			st = conn.prepareStatement(querry);
			rs = st.executeQuery();

			if (rs.next()) {
				maHDLonNhat = rs.getString("maHDMax");
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				if (st != null) {
					st.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return maHDLonNhat;
	}
	
	// them hoa don
	public boolean themHoaDon(HoaDon hd) {
		ConnectDB.getInstance();
		PreparedStatement st = null;
		int n = 0;
		try {
			Connection conn = ConnectDB.getConnection();
			String query = "insert into [dbo].[HoaDon] values(?,?,?,?,?)";
			st = conn.prepareStatement(query);
			st.setString(1, hd.getMaHD());
			st.setTimestamp(2, Timestamp.valueOf(hd.getNgayLap()));
			st.setString(3, hd.getNhanVien().getMaNV());
			st.setString(4, hd.getKhachHang().getSoDienThoai());
			st.setDouble(5, hd.getTongTien());
			n = st.executeUpdate();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
		
		return n>0;
	}
	
	
	// them chi tiet hoa don
	
	public boolean themChiTietHoaDon(ChiTietHoaDon cthd) {
		ConnectDB.getInstance();
		PreparedStatement st = null;
		int n = 0;
		try {
			Connection conn = ConnectDB.getConnection();
			String query = "insert into [dbo].[ChiTietHoaDon] values(?,?,?,?,?)";
			st = conn.prepareStatement(query);
			st.setString(1, cthd.getHoaDon().getMaHD());
			st.setString(2, cthd.getSanPham().getMaSP());
			st.setInt(3, cthd.getSoLuong());
			st.setInt(4, cthd.getSoDiemTichLuy());
			st.setString(5, cthd.getGhiChu());
			n = st.executeUpdate();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
		
		return n>0;
	}
	
	// cong diem tich luy vao tong diem tich luy khach hang
	public boolean congDiemVaoKH(int diem, String sdt) {
		ConnectDB.getInstance();
		PreparedStatement st = null;
		int n = 0;
		try {
			Connection conn = ConnectDB.getConnection();
			String query = "update [dbo].[KhachHang] set tongDiemTichLuy = ? where [soDienThoai] = ?";
			st = conn.prepareStatement(query);
			
			st.setInt(1, diem);
			st.setString(2, sdt);
			
			n = st.executeUpdate();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
		
		return n>0;
	}
	
	
	
	
	
	
}
