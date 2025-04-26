package dao.impl;


import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import dao.INhanVien_Dao;
import entity.NhanVien;
import entity.TaiKhoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import util.EntityManagerFactory;

public class NhanVien_DAO extends UnicastRemoteObject implements INhanVien_Dao, Serializable {
	private EntityManager em = EntityManagerFactory.getInstance().getEntityManager();

	public NhanVien_DAO() throws RemoteException {
	}

	// Generate randomly ID
	public String getMaNVTuDong() {
		String maNVLonNhat = null;

		try {
			em.getTransaction().begin();

			String query = "SELECT MAX(nv.maNV) FROM NhanVien nv";
			maNVLonNhat = em.createQuery(query, String.class).getSingleResult();

			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}

		return maNVLonNhat;
	}

	// Add new staff
	public boolean themNhanVien(NhanVien nv, TaiKhoan tKhoan) {
		boolean isSuccess = false;

		try {
			em.getTransaction().begin();

			em.persist(nv);
			em.persist(tKhoan);

			em.getTransaction().commit();
			isSuccess = true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}

		return isSuccess;
	}

	// Get all staff
	public ArrayList<NhanVien> getAllNhanVien() {
		List<NhanVien> listNhanVien = new ArrayList<>();

		try {
			em.getTransaction().begin();

			String query = "SELECT nv FROM NhanVien nv LEFT JOIN FETCH nv.taiKhoan";
			listNhanVien = em.createQuery(query, NhanVien.class).getResultList();

			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}

		return new ArrayList<NhanVien>(listNhanVien);
	}

	// Edit staff information
	public boolean suaNhanVien(NhanVien nv, TaiKhoan tk) {
		boolean isSuccess = false;

		try {
			em.getTransaction().begin();

			NhanVien existingNhanVien = em.find(NhanVien.class, nv.getMaNV());
			if (existingNhanVien != null) {
				existingNhanVien.setTenNV(nv.getTenNV());
				existingNhanVien.setGioiTinh(nv.isGioiTinh());
				existingNhanVien.setNgaySinh(nv.getNgaySinh());
				existingNhanVien.setSDT(nv.getSDT());
				existingNhanVien.setEmail(nv.getEmail());
				existingNhanVien.setMaCCCD(nv.getMaCCCD());
				existingNhanVien.setDiaChi(nv.getDiaChi());
				existingNhanVien.setNgayVaoLam(nv.getNgayVaoLam());
				existingNhanVien.setGhiChu(nv.getGhiChu());
				existingNhanVien.setTrangThai(nv.isTrangThai());
				existingNhanVien.setChucVu(nv.getChucVu());
				existingNhanVien.setAvtString(nv.getAvtString());
			}

			// Update account
			TaiKhoan existingTaiKhoan = em.find(TaiKhoan.class, nv.getMaNV());
			if (existingTaiKhoan != null) {
				existingTaiKhoan.setMatKhau(tk.getMatKhau());
			}

			em.getTransaction().commit();
			isSuccess = true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}

		return isSuccess;
	}
	
	// Delete employee
	public boolean xoaNhanVien(String maNV) {
		boolean isSuccess = false;

		try {
			em.getTransaction().begin();

			// Remove account
			TaiKhoan taiKhoan = em.find(TaiKhoan.class, maNV);
			if (taiKhoan != null) {
				em.remove(taiKhoan);
			}

			// Remove staff
			NhanVien nhanVien = em.find(NhanVien.class, maNV);
			if (nhanVien != null) {
				em.remove(nhanVien);
			}

			em.getTransaction().commit();
			isSuccess = true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}

		return isSuccess;
	}

	// Search specific staff by fields
	public ArrayList<NhanVien> timNhanVien(String maNV, String tenNV, Boolean gioiTinh, Date ngaySinh, String sDT, String email,
										   String maCCCD, String diaChi, Date ngayVaoLam, Boolean trangThai, Integer chucVu) {
		List<NhanVien> listNV = new ArrayList<>();
		try {
			em.getTransaction().begin();

			// Xây dựng query động
			StringBuilder queryString = new StringBuilder("SELECT nv FROM NhanVien nv WHERE 1=1");

			if (maNV != null && !maNV.isEmpty()) {
				queryString.append(" AND nv.maNV LIKE :maNV");
			}
			if (tenNV != null && !tenNV.isEmpty()) {
				queryString.append(" AND nv.tenNV LIKE :tenNV");
			}
			if (maCCCD != null && !maCCCD.isEmpty()) {
				queryString.append(" AND nv.maCCCD LIKE :maCCCD");
			}

			TypedQuery<NhanVien> query = em.createQuery(queryString.toString(), NhanVien.class);

			if (maNV != null && !maNV.isEmpty()) {
				query.setParameter("maNV", "%" + maNV + "%");
			}
			if (tenNV != null && !tenNV.isEmpty()) {
				query.setParameter("tenNV", "%" + tenNV + "%");
			}
			if (maCCCD != null && !maCCCD.isEmpty()) {
				query.setParameter("maCCCD", "%" + maCCCD + "%");
			}

			listNV = query.getResultList();
			System.out.println(">>>>>>> " + listNV);

			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		return new ArrayList<>(listNV);
	}


	public int laySoLuongNhanVien() {
		int soLuong = 0;

		try {
			em.getTransaction().begin();

			String query = "SELECT COUNT(nv) FROM NhanVien nv";
			soLuong = ((Long) em.createQuery(query).getSingleResult()).intValue();

			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}

		return soLuong;
	}


	public NhanVien getNhanVienTheoMa(String maNhanVien) {
		NhanVien nhanVien = null;

		try {
			em.getTransaction().begin();

			String query = "SELECT nv FROM NhanVien nv WHERE nv.maNV = :maNV";
			nhanVien = em.createQuery(query, NhanVien.class)
					.setParameter("maNV", maNhanVien)
					.getResultStream()
					.findFirst()
					.orElse(null);

			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}

		return nhanVien;
	}
}
