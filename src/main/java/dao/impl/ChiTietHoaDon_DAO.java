package dao.impl;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import dao.IChiTietHoaDon_Dao;
import entity.ChiTietHoaDon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import util.EntityManagerFactory;

public class ChiTietHoaDon_DAO extends UnicastRemoteObject implements IChiTietHoaDon_Dao, Serializable {
	private EntityManager em = EntityManagerFactory.getInstance().getEntityManager();

	public ChiTietHoaDon_DAO() throws RemoteException {
	}

	public List<ChiTietHoaDon> getChiTietHoaDonTheoMaHoaDon(String maHoaDon) {
		List<ChiTietHoaDon> dsChiTietHoaDon = new ArrayList<>();

		try {
			em.getTransaction().begin();

			TypedQuery<ChiTietHoaDon> query = em.createQuery(
					"SELECT cthd FROM ChiTietHoaDon cthd WHERE cthd.hoaDon.maHD = :maHD",
					ChiTietHoaDon.class
			);

			query.setParameter("maHD", maHoaDon);

			dsChiTietHoaDon.addAll(query.getResultList());

			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}

		return dsChiTietHoaDon;
	}


	public DefaultTableModel thongKeSanPhamBanChay() {
		String[] cols = { "Xếp Hạng", "Mã Sản Phẩm", "Tên Sản Phẩm", "Loại Sản Phẩm", "Đơn Giá", "Số Lượng Đã Bán", "Tổng Doanh Thu" };
		DefaultTableModel modelSP = new DefaultTableModel(cols, 0);
		DecimalFormat decimalFormat = new DecimalFormat("#,###.#VND");

		try {
			em.getTransaction().begin();

			Query query = em.createQuery(
					"SELECT cthd.sanPham.maSP, sp.tenSP, sp.loaiSP, sp.donGia, " +
							"SUM(cthd.soLuong) as soLuongDaBan, SUM(sp.donGia * cthd.soLuong) as tongDoanhThu " +
							"FROM ChiTietHoaDon cthd JOIN cthd.sanPham sp " +
							"GROUP BY cthd.sanPham.maSP, sp.tenSP, sp.loaiSP, sp.donGia " +
							"ORDER BY SUM(cthd.soLuong) DESC"
			);

			List<Object[]> resultList = query.getResultList();
			em.getTransaction().commit();

			int rank = 1;
			for (Object[] row : resultList) {
				String[] rows = {
						Integer.toString(rank++),
						(String) row[0],
						(String) row[1],
						(String) row[2],
						decimalFormat.format((Double) row[3]),
						Integer.toString(((Long) row[4]).intValue()),
						decimalFormat.format((Double) row[5])
				};
				modelSP.addRow(rows);
			}
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}

		return modelSP;
	}
}