package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.SanPham;

public class ChiTietHoaDon_DAO {
	public ChiTietHoaDon getChiTietHoaDon(ResultSet resultSet) {
		try {
			String maHD = resultSet.getString("maHD");
			String maSP = resultSet.getString("maSP");
			int soLuong = resultSet.getInt("soLuong");
			int soDiemTichLuy = resultSet.getInt("soDiemTichLuy");
			String ghiChu = resultSet.getString("ghiChu");
			
			HoaDon_DAO hoaDon_DAO  = new HoaDon_DAO();
			SanPham_DAO sanPham_DAO = new SanPham_DAO();
			
			HoaDon hd = hoaDon_DAO.getHoaDontheoMa(maHD);
			SanPham sp = sanPham_DAO.getSanPhamtheoMa(maSP);
			
			return new ChiTietHoaDon(hd, sp, soDiemTichLuy, soLuong, ghiChu);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ChiTietHoaDon> getChiTietHoaDonTheoMaHoaDon(String maHoaDon) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<ChiTietHoaDon> dsChiTietHoaDon = new ArrayList<ChiTietHoaDon>();
		
		try {
			connection = ConnectDB.getConnection();
			preparedStatement = connection.prepareStatement("Select * From ChiTietHoaDon Where maHD = ?");
			preparedStatement.setString(1, maHoaDon);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				dsChiTietHoaDon.add(getChiTietHoaDon(resultSet));
			}
			return dsChiTietHoaDon;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public DefaultTableModel thongKeSanPhamBanChay() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String[] cols = { "Xếp Hạng", "Mã Sản Phẩm", "Tên Sản Phẩm", "Loại Sản Phẩm", "Đơn Giá", "Số Lương Đã Bán", "Tổng Doanh Thu"};
		DefaultTableModel modelSP = new DefaultTableModel();
		
		modelSP = new DefaultTableModel(cols, 0);
		DecimalFormat decimalFormat = new DecimalFormat("#,###.#VND");
		try {
			connection = ConnectDB.getConnection();
			preparedStatement = connection.prepareStatement(
				"SELECT cthd.maSP, sp.tenSP, sp.loaiSP, sp.donGia, COUNT(soLuong) as soLuongDaBan, SUM(sp.donGia) as tongDoanhThu " +
				"FROM ChiTietHoaDon cthd JOIN [dbo].[SanPham] sp on cthd.maSP = sp.maSP " +
				"GROUP BY cthd.maSP, sp.tenSP, sp.loaiSP, sp.donGia, cthd.maSP " +
				"ORDER BY COUNT(soLuong) DESC"
			);
			resultSet = preparedStatement.executeQuery();
			
			int rank = 1;
			while(resultSet.next()) {
				String rows[] = {
					Integer.toString(rank), resultSet.getString("maSP"), 
					resultSet.getString("tenSP"), resultSet.getString("loaiSP"),
					decimalFormat.format(resultSet.getDouble("donGia")),
					Integer.toString(resultSet.getInt("soLuongDaBan")), 
					decimalFormat.format(resultSet.getDouble("tongDoanhThu"))
				};	
				modelSP.addRow(rows);
				rank++;
			}
			preparedStatement.close();
			resultSet.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return modelSP;
	}
	
}
