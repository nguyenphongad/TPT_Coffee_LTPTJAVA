package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.NhanVien;
import entity.TaiKhoan;
import jakarta.persistence.EntityManager;
import util.EntityManagerFactory;

public class Login_DAO {
	// lay thong tin tai khoan
	private EntityManager em = EntityManagerFactory.getInstance().getEntityManager();

	public ArrayList<TaiKhoan> getTaiKhoan(){
		ArrayList<TaiKhoan> dstk = new ArrayList<TaiKhoan>();
		ConnectDB.getInstance();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		
		try {
			Connection conn = ConnectDB.getConnection();
			String querry = "SELECT * FROM TaiKhoan ";
			
			st = conn.prepareStatement(querry);
			
			rs = st.executeQuery();
			while(rs.next()) {
				NhanVien nvien = new NhanVien(rs.getString("maNV"));
				TaiKhoan tk = new TaiKhoan(nvien, rs.getString("matKhau"));
				dstk.add(tk);
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
		return dstk;
	}
	
	public TaiKhoan kiemTraDangNhap(String maNV, String matKhau) {
		    TaiKhoan taiKhoan = null;

		    try {
				em.getTransaction().begin();

				String query = "select tk from TaiKhoan tk where tk.NV.maNV=:maNV and tk.matKhau=:matKhau";
				taiKhoan =  em.createQuery(query, TaiKhoan.class).setParameter("maNV", maNV).setParameter("matKhau", matKhau).getSingleResult();
				
				if(taiKhoan != null) return taiKhoan;
//					NhanVien nvien = new NhanVien(rs.getString("maNV"), rs.getString("tenNV"),
//							rs.getBoolean("gioiTinh"),rs.getDate("ngaySinh"), rs.getString("sDT"),
//							rs.getString("email"), rs.getString("maCCCD"), rs.getString("diaChi"),
//							rs.getDate("ngayVaoLam"), rs.getString("ghiChu"), rs.getBoolean("trangThai"),
//							rs.getInt("chucVu"), rs.getString("urlAnh"));
					
					
//					 tk = new TaiKhoan(nvien, rs.getString("matKhau"));
					
				em.getTransaction().commit();
			} catch (Exception e) {
				// TODO: handle exception
				em.getTransaction().rollback();
				e.printStackTrace();
			}
		    return null;
	}
	
	
	
	
	
	
}
