package dao;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Soundbank;
import javax.swing.JOptionPane;

import connectDB.ConnectDB;
import entity.NhanVien;
import entity.TaiKhoan;

public class NhanVien_DAO {
//	private Connection connection;
//	private ArrayList<NhanVien> listNhanViens;
//	
//	
//	public NhanVien_DAO() {
//		// TODO Auto-generated constructor stub
//		this.listNhanViens = new ArrayList<NhanVien>();
//	}
//
//
//	public List<NhanVien> getListNhanViens() {
//		return listNhanViens;
//	}
//
//
//	public void setListNhanViens(ArrayList<NhanVien> listNhanViens) {
//		this.listNhanViens = listNhanViens;
//	}
//	
//	public boolean themNhanVien(NhanVien nv) {
//		if(!listNhanViens.contains(nv)) {
//			return listNhanViens.add(nv);
//		}
//		return false;
//	}
//	
//	public NhanVien timKiemNhanVien(String ma) {
//		for(NhanVien nv: listNhanViens) {
//			if(nv.getMaNV().trim().equalsIgnoreCase(ma)) {
//				return nv;
//			}
//		}
//		return null;
//	}
//	
	
//	
//	public boolean xoaNhanVien(String maNV) {
//		if(timKiemNhanVien(maNV) != null) {
//			return listNhanViens.remove(timKiemNhanVien(maNV));
//		}
//		return false;
//	}
//	
//	public boolean suaNhanVien(String ma, NhanVien nvMoi) {
//		for(NhanVien nv: listNhanViens) {
//			if(nv.getMaNV().equalsIgnoreCase(ma)) {
//				listNhanViens.set(listNhanViens.indexOf(nv), nvMoi);
//				return true;
//			}
//		}
//		return false;
//	}
//	
//	public String getMatKhau(String ma) {
//		for(NhanVien nv: listNhanViens) {
//			if(nv.getMaNV().equalsIgnoreCase(ma)) {
//				return nv.getMatKhau();
//			}
//		}
//		return null;
//	}
//	
//	//C: create
//	public boolean create(NhanVien nv) {
//        int n = 0;
//        try {
//        	connection = ConnectDB.getInstance().getConnection();
//            PreparedStatement statement = null;
//			statement = connection.prepareStatement("Insert into NhanVien value (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
//			statement.setString(1, null);
//			statement.setString(2, null);
//			statement.setString(3, null);
//			statement.setString(4, null);
//			statement.setString(5, null);
//			statement.setString(6, null);
//			statement.setString(7, null);
//			statement.setString(8, null);
//			statement.setString(9, null);
//			statement.setString(10, null);
//			n = statement.executeUpdate();
//		} catch (Exception e) { 
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//        return n>0;
//	}
//	
//	//R: Read
//	public ArrayList<NhanVien> getListNhanVien(){
//		try {
//			connection = ConnectDB.getInstance().getConnection();
//			String sql = "Select * from NhanVien";
//			java.sql.Statement statement = connection.createStatement();
//			ResultSet rs = statement.executeQuery(sql);
//			while(rs.next()) {
//				String ma = rs.getString(1);
//				String ten = rs.getString(2);
//				String matKhau = rs.getString(3);
//				boolean gioiTinh = rs.getBoolean(4);
//				java.util.Date ngaySinh = rs.getDate(5);
//				String sdt = rs.getString(6);
//				String email = rs.getString(7);
//				String macccd = rs.getString(8);
//				String diaChi = rs.getString(9);
//				java.util.Date ngayVaoLam = rs.getDate(10);
//				String ghichu = rs.getString(11);
//				boolean trangthai = rs.getBoolean(12);
//				int chucVu = rs.getInt(13);
//				NhanVien nVien = new NhanVien(ma, ten, matKhau, gioiTinh, ngaySinh, sdt, email, macccd, diaChi, ngayVaoLam, ghichu, trangthai, chucVu);
//				listNhanViens.add(nVien);
//				
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return listNhanViens;
//	}
//	
//	//U: Update
//	public boolean update(NhanVien nv) {
//		
//		int n = 0;
//		try {
//			connection = ConnectDB.getInstance().getConnection();
//			PreparedStatement statement = null;
//			//caulenh
//			n = statement.executeUpdate();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return n>0;
//	}
//	
//	
//	//D: Delete
//	public boolean delete(String maNV) {
//		
//		int n = 0;
//		try {
//			connection = ConnectDB.getInstance().getConnection();
//			PreparedStatement statement = null;
//			statement = connection.prepareStatement("delete from NhanVien where maNV = ?");
//			statement.setString(1, maNV);
//			n = statement.executeUpdate();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return n>0;
//	}
//	
	
	public String getMaNVTuDong() {
		ConnectDB.getInstance();
		PreparedStatement statement = null;
		ResultSet rs = null;
		String maNVLonNhat = null;
		try {
			Connection connection = ConnectDB.getConnection();
			String query = "SELECT MAX(maNV) AS maNVMax from NhanVien";
			statement = connection.prepareStatement(query);
			rs = statement.executeQuery();
			if(rs.next()) {
				maNVLonNhat = rs.getString("maNVMax");
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				rs.close();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return maNVLonNhat;
	}
	
	public boolean themNhanVien(NhanVien nv, TaiKhoan tKhoan) {
		ConnectDB.getInstance();
		PreparedStatement statement = null;
		Connection connection = null;
		int n = 0;
		try {
			connection = ConnectDB.getConnection();
			connection.setAutoCommit(false);
			String query = "Insert into NhanVien values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			statement = connection.prepareStatement(query);
			statement.setString(1, nv.getMaNV());
			statement.setString(2, nv.getTenNV());
			statement.setBoolean(3, nv.isGioiTinh());
			statement.setDate(4, nv.getNgaySinh());
			statement.setString(5, nv.getSDT());
			statement.setString(6, nv.getEmail());
			statement.setString(7, nv.getMaCCCD());
			statement.setString(8, nv.getDiaChi());
			statement.setDate(9, nv.getNgayVaoLam());
			statement.setString(10, nv.getGhiChu());
			statement.setBoolean(11, nv.isTrangThai());
			statement.setInt(12, nv.getChucVu());
			statement.setString(13, nv.getAvtString());
//			System.out.println("check value of NhanVIen: " + nv.getMaNV() + " " + nv.getTenNV() + " "+ nv.isGioiTinh() + " " + nv.getNgaySinh());
//			System.out.println(nv.getsDT() + " " + nv.getEmail() + " " + nv.getMaCCCD() + " " + nv.getDiaChi() + " " + nv.getNgayVaoLam() + " " + nv.getGhiChu());
//			System.out.println(nv.isTrangThai() + " " + nv.getChucVu() + " " + nv.getAvtString());
			
			n += statement.executeUpdate();

			String queryTaiKhoan = "Insert into TaiKhoan values(?, ?)";
			statement = connection.prepareStatement(queryTaiKhoan);
			statement.setString(1, nv.getMaNV());
			statement.setString(2, tKhoan.getMatKhau());
			n += statement.executeUpdate();
			connection.commit();

		} catch (Exception e) {
			// TODO: handle exception
			try {
				if(connection != null)
					connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Trùng mã nhân viên.");
//			e.printStackTrace();
		}
		finally {
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
		return n > 0;
	}
	
	//get tat ca cac nhanvien
	public ArrayList<NhanVien> getAllNhanVien(){
		ArrayList<NhanVien> listNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			connection = ConnectDB.getConnection();
			String querry = "SELECT NV.*, TK.matKhau\r\n"
					+ "FROM NhanVien NV\r\n"
					+ "LEFT JOIN TaiKhoan TK ON NV.maNV = TK.maNV;";
			statement = connection.prepareStatement(querry);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				NhanVien nVien = new NhanVien(rs.getString(1), rs.getString(2), rs.getBoolean(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getDate(9), rs.getString(10), rs.getBoolean(11), rs.getInt(12), rs.getString(13));
				listNhanVien.add(nVien);
			}
//			          				 NhanVien(String maNV, String tenNV, String matKhau, boolean gioiTinh, java.sql.Date ngaySinh, String sDT, String email, String maCCCD, String diaChi, java.sql.Date ngayVaoLam, String ghiChu, boolean trangThai,   int chucVu, String avtString) 
			
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
		return listNhanVien;
	}
	
	//sua Nhan Vien
	public boolean suaNhanVien(NhanVien nv, TaiKhoan tk) {
		ConnectDB.getInstance();
		PreparedStatement statement = null;
		int n = 0;
		Connection connection = null;
		try {
			connection  = ConnectDB.getConnection();
//			String query = "Update NhanVien SET tenNV = ?, gioiTinh = ?, ngaySinh = ?, sDT = ?" + 
//			"email = ?, maCCCD = ?,  diaChi = ?, ngayVaoLam = ?, ghiChu = ?, trangThai = ?, chucVu = ?, urlAnh = ?" +
//					"where maNV = ?";
			connection.setAutoCommit(false);
			String query = "UPDATE NhanVien "
		             + "SET "
		             + "    tenNV = ?, "
		             + "    gioiTinh = ?, "
		             + "    ngaySinh = ?, "
		             + "    sDT = ?, "
		             + "    email = ?, "
		             + "    maCCCD = ?, "
		             + "    diaChi = ?, "
		             + "    ngayVaoLam = ?, "
		             + "    ghiChu = ?, "
		             + "    trangThai = ?, "
		             + "    chucVu = ?, "
		             + "    urlAnh = ? "
//		             + "FROM NhanVien "
//		             + "LEFT JOIN TaiKhoan TK ON NhanVien.maNV = TK.maNV "
		             + "WHERE NhanVien.maNV = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, nv.getTenNV());
			statement.setBoolean(2, nv.isGioiTinh());
			statement.setDate(3, nv.getNgaySinh());
			statement.setString(4, nv.getSDT());
			statement.setString(5, nv.getEmail());
			statement.setString(6, nv.getMaCCCD());
			statement.setString(7, nv.getDiaChi());
			statement.setDate(8, nv.getNgayVaoLam());
			statement.setString(9, nv.getGhiChu());
			statement.setBoolean(10, nv.isTrangThai());
			statement.setInt(11, nv.getChucVu());
			statement.setString(12, nv.getAvtString());
			statement.setString(13, nv.getMaNV());
			n += statement.executeUpdate();
			
			String queryMatKhau = "UPDATE TaiKhoan SET matKhau =? WHERE maNV = ?";
			statement = connection.prepareStatement(queryMatKhau);
			statement.setString(1, tk.getMatKhau());
			statement.setString(2, nv.getMaNV());
			n += statement.executeUpdate();
			connection.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
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
		return n>0;
	}
	
	//xoa nhan vien
	public boolean xoaNhanVien(String maNV) {
		ConnectDB.getInstance();
		PreparedStatement statement = null;
		int n = 0;
		Connection connection = null;
		try {
			connection = ConnectDB.getConnection();
			connection.setAutoCommit(false);
			String querry = "DELETE from TaiKhoan where maNV = ?";
			statement = connection.prepareStatement(querry);
			statement.setString(1, maNV);
			n += statement.executeUpdate();
			
			String queryNhanVien = "DELETE from NhanVien where maNV = ?";
			statement = connection.prepareStatement(queryNhanVien);
			statement.setString(1, maNV);
			n+= statement.executeUpdate();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
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
		return n>0;
	}
	
	//Tim kiem
//	public ArrayList<NhanVien> timNhanVien(String maNV,String tenNV,
//			boolean gioiTinh, java.util.Date ngaySinh, String sDT,
//			String email, String maCCCD, String diaChi, java.util.Date ngayVaoLam, 
//			boolean trangThai, int chucVu){
//		ArrayList<NhanVien> listNV = new ArrayList<NhanVien>();
//		ConnectDB.getInstance();
//		PreparedStatement statement = null;
//		ResultSet rs = null;
//		Connection connection = null;
//		
//		try {
//			connection = ConnectDB.getConnection();
//			String queryString = "SELECT * FROM NhanVien WHERE\r\n"
//					+ "maNV like ?\r\n"
//					+ "and tenNV like ?\r\n"
//					+ "and gioiTinh like ?\r\n"
//					+ "and sDT like ?\r\n"
//					+ "and email like ?\r\n"
//					+ "and maCCCD like ?\r\n"
//					+ "and diaChi like ?\r\n"
//					+ "and trangThai like ?\r\n"
//					+ "and chucVu like ?";
//			
//			if(ngaySinh != null) {
//				queryString = queryString + " and ngaySinh = ?";
//			}
//				
//		
//			if(ngayVaoLam != null) {
//				queryString = queryString + " and ngayVaoLam = ?";
//			
//			}
//				
//			statement = connection.prepareStatement(queryString);
//			statement.setString(1,  maNV );
//			statement.setString(2, "%" + tenNV + "%");
//			statement.setBoolean(3, gioiTinh);
//			statement.setString(4, "%" + sDT + "%");
//			statement.setString(5, "%" + email + "%");
//			statement.setString(6, "%" + maCCCD + "%");
//			statement.setString(7, "%" + diaChi + "%");
//			statement.setBoolean(8, trangThai);
//			statement.setInt(9, chucVu);
//			
//			if(ngaySinh != null) {
//				statement.setDate(10, new java.sql.Date(ngaySinh.getTime()));
//			}
//			
//			if(ngayVaoLam != null) {
//				statement.setDate(11, new java.sql.Date(ngayVaoLam.getTime()));
//			}
//		
//			rs = statement.executeQuery();
//			
//			System.out.println(rs);
//			
//			while(rs.next()) {
//				NhanVien nVien = new NhanVien(rs.getString("maNV"), rs.getString("tenNV"), "", rs.getBoolean("gioiTinh"),rs.getDate("ngaySinh"), rs.getString("sDT"), rs.getString("email"), rs.getString("maCCCD"), rs.getString("diaChi"), rs.getDate("ngayVaoLam"), rs.getString("ghiChu"), rs.getBoolean("trangThai"), rs.getInt("chucVu"), rs.getString("urlAnh"));
//				
//				
//				listNV.add(nVien);
//				
//			}
//			System.out.println("dao" +listNV);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			// TODO: handle exception
//		}
//		finally {
//			if (statement != null)
//				try {
//					statement.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		     if (connection != null)
//				try {
//					connection.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		}
//		return listNV;
//	}
//	
	
	
	//Tim kiem
		public ArrayList<NhanVien> timNhanVien(String maNV,String tenNV, boolean gioiTinh, java.util.Date ngaySinh, String sDT, String email, String maCCCD, String diaChi, java.util.Date ngayVaoLam,  boolean trangThai, int chucVu){
//				String queryString = "SELECT * FROM NhanVien"
//							+ " WHERE"
//							+ " maNV like ?"
//							+ " and tenNV like ?"
//							+ " and gioiTinh = ?"
//							+ " and ngaySinh = ?"
//							+ " and sDT like ?"
//							+ " and email like ?"
//							+ " and maCCCD like ?"
//							+ " and diaChi like ?"
//							+ " and ngayVaoLam = ?"
//							+ " and trangThai = ?"
//							+ " and chucVu = ?";
//				statement = connection.prepareStatement(queryString);
//				statement.setString(1, "%" + maNV + "%");
//				statement.setString(2, "%" + tenNV + "%");
//				statement.setBoolean(3, gioiTinh);
//				if(ngaySinh != null)
//					statement.setDate(4, new Date(ngaySinh.getTime()));
//				else
//					statement.setDate(4, null);
//				statement.setString(5, "%" + sDT + "%");
//				statement.setString(6, "%" + email + "%");
//				statement.setString(7, "%" + maCCCD + "%");
//				statement.setString(8, "%" + diaChi + "%");
//				if(ngayVaoLam != null)
//					statement.setDate(9, new Date(ngayVaoLam.getTime()));
//				else
//					statement.setDate(9, null);
//				statement.setBoolean(10, trangThai);
//				statement.setInt(11, chucVu);
				
				
//				String queryString = "SELECT *\r\n"
//						+ "FROM NhanVien\r\n"
//						+ " WHERE\r\n"
//						+ "   (maNV = ? OR ? IS NULL)"
//						+ " and (tenNV LIKE ? OR ? IS NULL)"
//						+ " and (gioiTinh = ? OR ? IS NULL)"
//						+ " and (ngaySinh = ? OR ? IS NULL)"
//						+ " and (sDT LIKE ? OR ? IS NULL)"
//						+ " and (email LIKE ? OR ? IS NULL)"
//						+ " and (maCCCD LIKE ? OR ? IS NULL)"
//						+ " and (diaChi LIKE ? OR ? IS NULL)"
//						+ " and (ngayVaoLam = ? OR ? IS NULL)"
//						+ " and (trangThai = ? OR ? IS NULL)"
//						+ " and (chucVu = ? OR ? IS NULL)"
//						+ "";
//				statement = connection.prepareStatement(queryString);
//				statement.setString(1,maNV );
//				statement.setString(2, "maNV");
//				statement.setString(3, "%" + tenNV + "%");
//				statement.setString(4, "tenNV");
//				statement.setBoolean(5, gioiTinh);
//				statement.setString(6, "gioiTinh");
//				if(ngaySinh != null) {
//					statement.setDate(7, new Date(ngaySinh.getTime()));
//					statement.setString(8, "ngaySinh");
//				}
//				else {
//					statement.setDate(7, null);
//					statement.setString(8, "ngaySinh");;
//				}
//				statement.setString(9, "%" + sDT + "%");
//				statement.setString(10, "sDT");
//				statement.setString(11, "%" + email + "%");
//				statement.setString(12, "email");
//				statement.setString(13, "%" + maCCCD + "%");
//				statement.setString(14, "maCCCD");
//				statement.setString(15, "%" + diaChi + "%");
//				statement.setString(16, "diaChi");
//				if(ngayVaoLam != null) {
//					statement.setDate(17, new Date(ngayVaoLam.getTime()));
//					statement.setString(18, "ngayVaoLam");
//				}
//				else {
//					statement.setDate(17, null);
//					statement.setString(18, "ngayVaoLam");
//				}
//				statement.setBoolean(19, trangThai);
//				statement.setString(20, "trangThai");
//				statement.setInt(21, chucVu);
//				statement.setString(22, "chucVu");
			
			//	System.out.println("check param: " +maNV +  tenNV + gioiTinh +  ngaySinh + sDT + email + maCCCD + diaChi + ngayVaoLam + trangThai + chucVu);
				
			ArrayList<NhanVien> listNV = new ArrayList<NhanVien>();
			ConnectDB.getInstance();
			PreparedStatement statement = null;
			ResultSet rs = null;
			Connection connection = null;
			
			try {
				connection = ConnectDB.getConnection();
				String queryString = "";
				queryString += ("SELECT * FROM NhanVien WHERE 1 = 1");
				if(maNV.isBlank() == false) {
					queryString += " and maNV like ? ";
				}
				if(tenNV.isBlank() == false) {
					queryString += " and tenNV like ? ";
				}
				queryString += (" and gioiTinh = ? ");
				if(ngaySinh != null) {
					queryString += " and ngaySinh = ? ";
				}
				if(sDT.isBlank() == false) {
					queryString += " and sDT like ? ";
				}
				if(email.isBlank() == false) {
					queryString += " and email like ? ";
				}
				if(maCCCD.isBlank() == false) {
					queryString += " and maCCCD like ? ";
				}
				if(diaChi.isBlank() == false) {
					queryString += " and diaChi like ? ";
				}
				if(ngayVaoLam != null) {
					queryString += " and ngayVaoLam = ? ";
				}
				queryString += " and trangThai = ? ";
				queryString += " and chucVu = ? ";
				int paramIndex = 1;
				statement = connection.prepareStatement(queryString);
				System.out.println("statement" + paramIndex +":" + queryString);
				if(maNV.isBlank() == false) {
					statement.setString(paramIndex, "%" + maNV + "%");
					paramIndex++;
					System.out.println("statement" + paramIndex +":" + queryString);
				}
				if(tenNV.isBlank() == false) {
					statement.setString(paramIndex, "%" + tenNV + "%");
					paramIndex++;
					System.out.println("statement" + paramIndex +":" + queryString);
				}
				statement.setBoolean(paramIndex, gioiTinh);
				paramIndex++;
				System.out.println("statement" + paramIndex +":" + queryString);
				if(ngaySinh != null) {
					statement.setDate(paramIndex, new Date(ngaySinh.getTime()));
					paramIndex++;
					System.out.println("statement" + paramIndex +":" + queryString);
				}
				if(sDT.isBlank() == false) {
					statement.setString(paramIndex, "%" + sDT + "%");
					paramIndex++;
					System.out.println("statement" + paramIndex +":" + queryString);
				}
				if(email.isBlank() == false) {
					statement.setString(paramIndex, "%" + email + "%");
					paramIndex++;
					System.out.println("statement" + paramIndex +":" + queryString);
				}
				if(maCCCD.isBlank() == false) {
					statement.setString(paramIndex, "%" + maCCCD + "%");
					paramIndex++;
					System.out.println("statement" + paramIndex +":" + queryString);
				}
				if(diaChi.isBlank() == false) {
					statement.setString(paramIndex, "%" + diaChi + "%");
					paramIndex++;
					System.out.println("statement" + paramIndex +":" + queryString);
				}
				if(ngayVaoLam != null) {
					statement.setDate(paramIndex, new Date(ngayVaoLam.getTime()));
					paramIndex++;
					System.out.println("statement" + paramIndex +":" + queryString);
				}
				statement.setBoolean(paramIndex, trangThai);
				paramIndex++;
				statement.setInt(paramIndex, chucVu);
				paramIndex++;
				System.out.println("statement" + paramIndex +":" + queryString);
				System.out.println(paramIndex);
				rs = statement.executeQuery();
				while(rs.next()) {
					NhanVien nVien = new NhanVien(rs.getString("maNV"), rs.getString("tenNV"),  rs.getBoolean("gioiTinh"), rs.getDate("ngaySinh"), rs.getString("sDT"), rs.getString("email"), rs.getString("maCCCD"), rs.getString("diaChi"), rs.getDate("ngayVaoLam"), rs.getString("ghiChu"), rs.getBoolean("trangThai"), rs.getInt("chucVu"), rs.getString("urlAnh"));
					listNV.add(nVien);
				}
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
			finally {
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
			return listNV;
		}
	public int laySoLuongNhanVien() {
		ConnectDB.getInstance();
		PreparedStatement statement = null;
		int soLuong = 0;
		try {
			Connection connection = ConnectDB.getConnection();
			statement = connection.prepareStatement("SELECT COUNT (*) AS SoLuong from NhanVien");
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				soLuong = rs.getInt("SoLuong");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				if(statement != null)
					statement.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return soLuong;
	}
	private NhanVien getNhanVien(ResultSet resultSet) throws SQLException {
		String maNV = resultSet.getString("maNV");
		String tenNV = resultSet.getString("tenNV");
//		String matKhau = resultSet.getString("matKhau");
		boolean gioiTinh = resultSet.getBoolean("gioiTinh");
        Date ngaySinh = resultSet.getDate("ngaySinh");
		String sDT = resultSet.getString("sDT");
		String email = resultSet.getString("email");
		String maCCCD = resultSet.getString("maCCCD");
		String diaChi = resultSet.getString("diaChi");
		Date ngayVaoLam = resultSet.getDate("ngayVaoLam");
		String ghiChu = resultSet.getString("ghiChu");
		boolean trangThai = resultSet.getBoolean("trangThai");
		int chucVu = resultSet.getInt("chucVu");
		
		return new NhanVien(maNV, tenNV, gioiTinh, ngaySinh, sDT, email, maCCCD, diaChi, ngayVaoLam, ghiChu, trangThai, chucVu, "");
	}
	
	public NhanVien getNhanVienTheoMa(String maNhanVien) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		NhanVien nhanVien = null;
		try {
			preparedStatement = ConnectDB.getConnection()
					.prepareStatement("SELECT * FROM NhanVien WHERE maNV = ?");
			preparedStatement.setString(1, maNhanVien);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) nhanVien = getNhanVien(resultSet);

			preparedStatement.close();
			resultSet.close();
			
			return nhanVien;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}


}
