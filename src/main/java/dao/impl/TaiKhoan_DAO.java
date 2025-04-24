package dao.impl;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import dao.ITaiKhoan_Dao;
import entity.TaiKhoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import util.EntityManagerFactory;

public class TaiKhoan_DAO extends UnicastRemoteObject implements ITaiKhoan_Dao, Serializable {
	private EntityManager em = EntityManagerFactory.getInstance().getEntityManager();

	public TaiKhoan_DAO() throws RemoteException {
	}

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
