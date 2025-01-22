package dao;

import java.util.ArrayList;
import java.util.List;

import entity.SanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import util.EntityManagerFactory;

public class SanPham_DAO {
	private EntityManager em = EntityManagerFactory.getInstance().getEntityManager();

	public SanPham getSanPhamtheoMa(String maSP) {
		SanPham sanPham = null;

		try {
			em.getTransaction().begin();

			sanPham = em.find(SanPham.class, maSP);

			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}

		return sanPham;
	}
	
	// Generate randomly ID
	public String getMaSPTuDong() {
		String maSPLonNhat = null;

		try {
			em.getTransaction().begin();

			String query = "SELECT MAX(sp.maSP) FROM SanPham sp";
			maSPLonNhat = em.createQuery(query, String.class)
					.getSingleResult();

			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}

		return maSPLonNhat;
	}

	// Add product
	public boolean themSanPham(SanPham sp) {
		boolean isSuccess = false;

		try {
			em.getTransaction().begin();

			em.persist(sp);

			em.getTransaction().commit();
			isSuccess = true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}

		return isSuccess;
	}

	// Edit product
	public boolean suaSP(SanPham sp) {
		boolean isSuccess = false;

		try {
			em.getTransaction().begin();

			SanPham existingSP = em.find(SanPham.class, sp.getMaSP());
			if (existingSP != null) {
				existingSP.setTenSP(sp.getTenSP());
				existingSP.setLoaiSP(sp.getLoaiSP());
				existingSP.setAnhSP(sp.getAnhSP());
				existingSP.setDonGia(sp.getDonGia());
				existingSP.setTrangThai(sp.isTrangThai());
				em.merge(existingSP);
				isSuccess = true;
			}

			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}

		return isSuccess;
	}

	// Remove product
	public boolean xoaSP(String maSP) {
		boolean isSuccess = false;

		try {
			em.getTransaction().begin();

			SanPham sp = em.find(SanPham.class, maSP);
			if (sp != null) {
				em.remove(sp);
				isSuccess = true;
			}

			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}

		return isSuccess;
	}


	// Check existed product
	public boolean kiemTraMaSP(String maSP) {
		boolean exists = false;

		try {
			em.getTransaction().begin();

			String query = "SELECT COUNT(sp) FROM SanPham sp WHERE sp.maSP = :maSP";
			Long count = em.createQuery(query, Long.class)
					.setParameter("maSP", maSP)
					.getSingleResult();

			exists = count > 0;

			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}

		return exists;
	}

	// Get all product
	public ArrayList<SanPham> getAllSanPham() {
		List<SanPham> sanPhamList = new ArrayList<>();

		try {
			em.getTransaction().begin();

			String query = "SELECT sp FROM SanPham sp";
			TypedQuery<SanPham> typedQuery = em.createQuery(query, SanPham.class);
			sanPhamList = typedQuery.getResultList();

			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}

		return new ArrayList<SanPham>(sanPhamList);
	}

	// Get product by ID
	public ArrayList<SanPham> timKiemSPTheoMavaTen(String value) {
		List<SanPham> sanPhamList = new ArrayList<>();

		try {
			em.getTransaction().begin();

			String query = "SELECT sp FROM SanPham sp WHERE sp.maSP LIKE :value OR sp.tenSP LIKE :value";
			TypedQuery<SanPham> typedQuery = em.createQuery(query, SanPham.class);
			typedQuery.setParameter("value", "%" + value + "%");
			sanPhamList = typedQuery.getResultList();

			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}

		return new ArrayList<SanPham>(sanPhamList);
	}

	// Find product by type
	public ArrayList<SanPham> timKiemSPTheoLoai(String loai) {
		List<SanPham> listSP = new ArrayList<>();

		try {
			em.getTransaction().begin();

			TypedQuery<SanPham> query;
			if (loai.equals("")) {
				query = em.createQuery("SELECT s FROM SanPham s", SanPham.class);
			} else {
				query = em.createQuery("SELECT s FROM SanPham s WHERE s.loaiSP LIKE :loai", SanPham.class);
				query.setParameter("loai", loai);
			}

			listSP = query.getResultList();

			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}

		return new ArrayList<SanPham>(listSP);
	}


	// Find product by status
	public ArrayList<SanPham> timKiemSPTheoTrangThai(int tt) {
		ArrayList<SanPham> listSP = new ArrayList<SanPham>();

		try {
			em.getTransaction().begin();

			TypedQuery<SanPham> query;
			if (tt == 2) {
				query = em.createQuery("SELECT s FROM SanPham s", SanPham.class);
			} else {
				query = em.createQuery("SELECT s FROM SanPham s WHERE s.trangThai = :trangThai", SanPham.class);
				query.setParameter("trangThai", tt);
			}

			listSP.addAll(query.getResultList());

			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}

		return listSP;
	}
}
