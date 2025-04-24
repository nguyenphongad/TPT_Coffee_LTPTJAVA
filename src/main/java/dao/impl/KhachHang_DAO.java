package dao.impl;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import dao.IKhachHang_Dao;
import entity.KhachHang;
import jakarta.persistence.EntityManager;
import util.EntityManagerFactory;

public class KhachHang_DAO extends UnicastRemoteObject implements IKhachHang_Dao, Serializable {
	private EntityManager em = EntityManagerFactory.getInstance().getEntityManager();

	public KhachHang_DAO() throws RemoteException {
	}

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




















