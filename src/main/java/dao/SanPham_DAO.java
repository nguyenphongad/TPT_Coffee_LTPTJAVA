package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.SanPham;

public class SanPham_DAO {
	
	public SanPham getSanPham(ResultSet resultSet) {
		try {
			String maSP = resultSet.getString("maSP");
			String tenSP = resultSet.getString("tenSP");
			String loaiSP = resultSet.getString("loaiSP");
			Double donGia = resultSet.getDouble("donGia");
			boolean trangThai = resultSet.getBoolean("trangThai");
			
			return new SanPham(maSP, tenSP, loaiSP, null,  donGia, trangThai);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		return null;
	}
	
	public SanPham getSanPhamtheoMa(String maSP) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		SanPham sanPham = null;
		
		try {
			connection = ConnectDB.getConnection();
			preparedStatement = connection.prepareStatement("Select * From SanPham Where maSP=?");
			preparedStatement.setString(1, maSP);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) sanPham = getSanPham(resultSet);
			
			preparedStatement.close();
			resultSet.close();
			
			return sanPham;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// lay ma SP tụ dong
	public String getMaSPTuDong() {
		ConnectDB.getInstance();
		PreparedStatement st = null;
		ResultSet rs = null;
		String maSPLonNhat = null;
		try {
			Connection conn = ConnectDB.getConnection();
			String querry = "SELECT MAX(maSP) AS maSPMax FROM SanPham";
			st = conn.prepareStatement(querry);
			rs = st.executeQuery();

			if (rs.next()) {
				maSPLonNhat = rs.getString("maSPMax");
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
		return maSPLonNhat;
	}

	// them san pham
	public boolean themSanPham(SanPham sp) {
		ConnectDB.getInstance();
		PreparedStatement st = null;
		int n = 0;
		try {
			Connection conn = ConnectDB.getConnection();
			String query = "insert into SanPham values(?,?,?,?,?,?)";
			st = conn.prepareStatement(query);

			st.setString(1, sp.getMaSP());
			st.setString(2, sp.getTenSP());
			st.setString(3, sp.getLoaiSP());
			st.setString(4, sp.getAnhSP());
			st.setDouble(5, sp.getDonGia());
			st.setBoolean(6, sp.isTrangThai());

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

		return n > 0;
	}

	// sua san pham
	public boolean suaSP(SanPham sp) {
		ConnectDB.getInstance();
		PreparedStatement st = null;
		int n = 0;
		try {
			Connection conn = ConnectDB.getConnection();
			String querry = "update [dbo].[SanPham] set [tenSP]= ?,[loaiSP] = ?, [anhSP] = ?,\r\n"
					+ "[donGia] = ?, [trangThai] = ? where [maSP] = ?";

			st = conn.prepareStatement(querry);
			st.setString(1, sp.getTenSP());
			st.setString(2, sp.getLoaiSP());
			st.setString(3, sp.getAnhSP());
			st.setDouble(4, sp.getDonGia());
			st.setBoolean(5, sp.isTrangThai());
			st.setString(6, sp.getMaSP());

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

		return n > 0;
	}

	// xoa san pham
	public boolean xoaSP(String maSP) {
		ConnectDB.getInstance();
		PreparedStatement st = null;
		int n = 0;
		try {
			Connection conn = ConnectDB.getConnection();
			String querry = "delete from SanPham where [maSP] = ?";

			st = conn.prepareStatement(querry);
			st.setString(1, maSP);

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

		return n > 0;
	}

	// kiem tra ton tai ma SP
	public boolean kiemTraMaSP(String maSP) {
		boolean check = false;
		ConnectDB.getInstance();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			Connection conn = ConnectDB.getConnection();
			String query = "select * from SanPham where maSP LIKE ?";
			st = conn.prepareStatement(query);

			st.setString(1, maSP);
			rs = st.executeQuery();

			if (rs.next()) {
				check = true;
			}

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

		return check;

	}

	// lay all san pham
	public ArrayList<SanPham> getAllSanPham() {
		ConnectDB.getInstance();
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<SanPham> spds = new ArrayList<SanPham>();

		try {
			Connection conn = ConnectDB.getConnection();
			String querry = "SELECT * FROM SanPham";
			st = conn.prepareStatement(querry);
			rs = st.executeQuery();

			while (rs.next()) {
				SanPham sp = new SanPham(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getDouble(5), rs.getBoolean(6));
				spds.add(sp);
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

		return spds;
	}

	// tim kiem theo ma
	public ArrayList<SanPham> timKiemSPTheoMavaTen(String value) {
		ArrayList<SanPham> listSP = new ArrayList<SanPham>();
		ConnectDB.getInstance();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			Connection conn = ConnectDB.getConnection();
			String query = "select * from SanPham where maSP like ? or tenSP like ?";

			st = conn.prepareStatement(query);

			st.setString(1, '%' + value + '%');
			st.setString(2, '%' + value + '%');

			rs = st.executeQuery();

			while (rs.next()) {
				SanPham sanPham = new SanPham(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getDouble(5), rs.getBoolean(6));
				listSP.add(sanPham);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return listSP;

	}

	// tim kiem theo loai
	public ArrayList<SanPham> timKiemSPTheoLoai(String loai) {
		ArrayList<SanPham> listSP = new ArrayList<SanPham>();
		ConnectDB.getInstance();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			Connection conn = ConnectDB.getConnection();
			String query = null;
			if (loai.equals("")) {
				query = "select * from SanPham";
				st = conn.prepareStatement(query);
			} else {
				query = "select * from SanPham where [loaiSP] LIKE ?";
				st = conn.prepareStatement(query);
				st.setString(1, loai);

			}

			rs = st.executeQuery();

			while (rs.next()) {
				SanPham sanPham = new SanPham(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getDouble(5), rs.getBoolean(6));
				listSP.add(sanPham);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return listSP;

	}
	
	
	// tim kiem theo trang thai
		public ArrayList<SanPham> timKiemSPTheoTrangThai(int tt) {
			ArrayList<SanPham> listSP = new ArrayList<SanPham>();
			ConnectDB.getInstance();
			PreparedStatement st = null;
			ResultSet rs = null;
			try {
				Connection conn = ConnectDB.getConnection();
				String query = null;
				if (tt==2) {
					query = "select * from SanPham";
					st = conn.prepareStatement(query);
				} else {
					query = "select * from SanPham where [trangThai] LIKE ?";
					st = conn.prepareStatement(query);
					st.setInt(1, tt);

				}

				rs = st.executeQuery();

				while (rs.next()) {
					SanPham sanPham = new SanPham(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getDouble(5), rs.getBoolean(6));
					listSP.add(sanPham);
				}

			} catch (Exception e) {
				// TODO: handle exception
			}
			return listSP;

		}

}
