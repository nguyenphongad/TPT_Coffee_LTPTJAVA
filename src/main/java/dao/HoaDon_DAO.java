package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entity.ChiTietHoaDon;
import entity.HoaDon;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import util.EntityManagerFactory;

import java.util.ArrayList;
import java.util.List;

public class HoaDon_DAO {
	private EntityManager em = EntityManagerFactory.getInstance().getEntityManager();

	public List<HoaDon> getAllHoaDon() {
		List<HoaDon> hoaDonList = new ArrayList<HoaDon>();

		try {
			em.getTransaction().begin();

			TypedQuery<HoaDon> query = em.createQuery(
					"SELECT h FROM HoaDon h",
					HoaDon.class
			);

			hoaDonList.addAll(query.getResultList());

			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}

		return hoaDonList;
	}

	public HoaDon getHoaDontheoMa(String maHD) {
		HoaDon hoaDon = null;

		try {
			em.getTransaction().begin();

			// Using find method for primary key lookup
			hoaDon = em.find(HoaDon.class, maHD);

			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}

		return hoaDon;
	}

	public int tongSoHoaDonTheoThang(int thang) {
		int soHoaDon = 0;

		try {
			em.getTransaction().begin();

			TypedQuery<Long> query = em.createQuery(
					"SELECT COUNT(h) FROM HoaDon h WHERE FUNCTION('MONTH', h.ngayLap) = :thang",
					Long.class
			);
			query.setParameter("thang", thang);

			soHoaDon = query.getSingleResult().intValue();

			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}

		return soHoaDon;
	}

	public double tongDoanhThuTheoTungNgayTrongThang(int day, int month) {
		double tongDoanhThu = 0;

		try {
			em.getTransaction().begin();

			TypedQuery<Double> query = em.createQuery(
					"SELECT SUM(hd.tongTien) FROM HoaDon hd WHERE FUNCTION('MONTH', hd.ngayLap) = :month AND FUNCTION('DAY', hd.ngayLap) = :day",
					Double.class
			);
			query.setParameter("month", month);
			query.setParameter("day", day);

			Double result = query.getSingleResult();
			tongDoanhThu = result != null ? result : 0;

			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}

		return tongDoanhThu;
	}
	
	// lay ma HD tụ dong
	public String getMaHDTuDong() {
		String maHDLonNhat = null;

		try {
			em.getTransaction().begin();

			TypedQuery<String> query = em.createQuery(
					"SELECT MAX(hd.maHD) FROM HoaDon hd",
					String.class
			);

			maHDLonNhat = query.getSingleResult();

			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}

		return maHDLonNhat;
	}

	
	// them hoa don
	public boolean themHoaDon(HoaDon hd) {
		boolean isSuccess = false;

		try {
			em.getTransaction().begin();

			em.persist(hd);

			em.getTransaction().commit();
			isSuccess = true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}

		return isSuccess;
	}

	// them chi tiet hoa don
	public boolean themChiTietHoaDon(ChiTietHoaDon cthd) {
		boolean isSuccess = false;

		try {
			em.getTransaction().begin();

			em.persist(cthd);

			em.getTransaction().commit();
			isSuccess = true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}

		return isSuccess;
	}

	// cong diem tich luy vao tong diem tich luy khach hang
	public boolean congDiemVaoKH(int diem, String sdt) {
		boolean isSuccess = false;

		try {
			em.getTransaction().begin();

			Query query = em.createQuery("UPDATE KhachHang kh SET kh.tongDiemTichLuy = :diem WHERE kh.soDienThoai = :sdt");
			query.setParameter("diem", diem);
			query.setParameter("sdt", sdt);

			int rowsUpdated = query.executeUpdate();

			em.getTransaction().commit();
			isSuccess = rowsUpdated > 0;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}

		return isSuccess;
	}
}
