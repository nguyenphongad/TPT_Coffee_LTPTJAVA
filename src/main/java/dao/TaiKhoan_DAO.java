package dao;

import java.util.ArrayList;

import entity.TaiKhoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import util.EntityManagerFactory;

public class TaiKhoan_DAO {
	private EntityManager em = EntityManagerFactory.getInstance().getEntityManager();

	public ArrayList<TaiKhoan> getAllTaiKhoan() {
		ArrayList<TaiKhoan> listTK = new ArrayList<TaiKhoan>();

		try {
			em.getTransaction().begin();

			TypedQuery<TaiKhoan> query = em.createQuery(
					"SELECT t FROM TaiKhoan t JOIN FETCH t.NV",
					TaiKhoan.class
			);

			listTK.addAll(query.getResultList());

			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}

		return listTK;
	}
}
