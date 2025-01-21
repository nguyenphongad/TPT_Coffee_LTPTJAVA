package dao;

import java.util.ArrayList;

import entity.KhachHang;
import jakarta.persistence.EntityManager;
import util.EntityManagerFactory;

public class KhachHang_DAO {
	private EntityManager em = EntityManagerFactory.getInstance().getEntityManager();

	public KhachHang getKhachHangTheoSDT(String sdt) {
		KhachHang khachHang = null;

		try {
			em.getTransaction().begin();

			String query = "SELECT kh FROM KhachHang kh WHERE kh.soDienThoai = :sdt";
			khachHang = em.createQuery(query, KhachHang.class)
					.setParameter("sdt", sdt)
					.getResultStream()
					.findFirst()
					.orElse(null);

			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}

		return khachHang;
	}

	// Find customer's phone
	public ArrayList<KhachHang> timSoDienThoaiKhachHang(String sdt) {
		ArrayList<KhachHang> listKH = new ArrayList<>();

		try {
			em.getTransaction().begin();

			String query = "SELECT kh FROM KhachHang kh WHERE kh.soDienThoai = :sdt";
			listKH = (ArrayList<KhachHang>) em.createQuery(query, KhachHang.class)
					.setParameter("sdt", sdt)
					.getResultList();

			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}

		return listKH;
	}

	// Register customer's phone
	public boolean dangKySdtTichDiem(KhachHang khnew) {
		boolean isSuccess = false;

		try {
			em.getTransaction().begin();

			em.persist(khnew);

			em.getTransaction().commit();
			isSuccess = true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}

		return isSuccess;
	}
}




















