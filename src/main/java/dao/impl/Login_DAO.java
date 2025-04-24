package dao.impl;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import dao.ILogin_Dao;
import entity.TaiKhoan;
import jakarta.persistence.EntityManager;
import util.EntityManagerFactory;

public class Login_DAO extends UnicastRemoteObject implements ILogin_Dao, Serializable {
	private EntityManager em = EntityManagerFactory.getInstance().getEntityManager();

	public Login_DAO() throws RemoteException {
	}

	public List<TaiKhoan> getTaiKhoan() {
		List<TaiKhoan> dstk = new ArrayList<>();

		try {
			em.getTransaction().begin();

			String query = "select tk from TaiKhoan tk";
			dstk = em.createQuery(query, TaiKhoan.class).getResultList();

			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}

		return dstk;
	}


	public TaiKhoan kiemTraDangNhap(String maNV, String matKhau) {
		    TaiKhoan taiKhoan = null;
			System.out.println("123 MA NHAN VIEN DAY NEK :" + maNV + "\n 123MAT KHAU DAY NEK " + matKhau);
		    try {
				em.getTransaction().begin();

				String query = "select tk from TaiKhoan tk where tk.NV.maNV=:maNV and tk.matKhau=:matKhau";
				taiKhoan =  em.createQuery(query, TaiKhoan.class).setParameter("maNV", maNV).setParameter("matKhau", matKhau).getSingleResult();
				
				if(taiKhoan != null) return taiKhoan;

				em.getTransaction().commit();
			} catch (Exception e) {
				// TODO: handle exception
				em.getTransaction().rollback();
				e.printStackTrace();
			}
		    return null;
	}
}
