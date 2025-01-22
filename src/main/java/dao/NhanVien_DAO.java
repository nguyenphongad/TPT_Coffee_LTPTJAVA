package dao;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import entity.NhanVien;
import entity.TaiKhoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import util.EntityManagerFactory;

public class NhanVien_DAO {
	private EntityManager em = EntityManagerFactory.getInstance().getEntityManager();

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

			String queryString = "SELECT nv FROM NhanVien nv WHERE 1 = 1";
			if (maNV != null && !maNV.isBlank()) {
				queryString += " AND nv.maNV LIKE :maNV";
			}
			if (tenNV != null && !tenNV.isBlank()) {
				queryString += " AND nv.tenNV LIKE :tenNV";
			}
			if (gioiTinh != null) {
				queryString += " AND nv.gioiTinh = :gioiTinh";
			}
			if (ngaySinh != null) {
				queryString += " AND nv.ngaySinh = :ngaySinh";
			}
			if (sDT != null && !sDT.isBlank()) {
				queryString += " AND nv.sDT LIKE :sDT";
			}
			if (email != null && !email.isBlank()) {
				queryString += " AND nv.email LIKE :email";
			}
			if (maCCCD != null && !maCCCD.isBlank()) {
				queryString += " AND nv.maCCCD LIKE :maCCCD";
			}
			if (diaChi != null && !diaChi.isBlank()) {
				queryString += " AND nv.diaChi LIKE :diaChi";
			}
			if (ngayVaoLam != null) {
				queryString += " AND nv.ngayVaoLam = :ngayVaoLam";
			}
			if (trangThai != null) {
				queryString += " AND nv.trangThai = :trangThai";
			}
			if (chucVu != null) {
				queryString += " AND nv.chucVu = :chucVu";
			}

			TypedQuery<NhanVien> query = em.createQuery(queryString, NhanVien.class);

			if (maNV != null && !maNV.isBlank()) {
				query.setParameter("maNV", "%" + maNV + "%");
			}
			if (tenNV != null && !tenNV.isBlank()) {
				query.setParameter("tenNV", "%" + tenNV + "%");
			}
			if (gioiTinh != null) {
				query.setParameter("gioiTinh", gioiTinh);
			}
			if (ngaySinh != null) {
				query.setParameter("ngaySinh", ngaySinh);
			}
			if (sDT != null && !sDT.isBlank()) {
				query.setParameter("sDT", "%" + sDT + "%");
			}
			if (email != null && !email.isBlank()) {
				query.setParameter("email", "%" + email + "%");
			}
			if (maCCCD != null && !maCCCD.isBlank()) {
				query.setParameter("maCCCD", "%" + maCCCD + "%");
			}
			if (diaChi != null && !diaChi.isBlank()) {
				query.setParameter("diaChi", "%" + diaChi + "%");
			}
			if (ngayVaoLam != null) {
				query.setParameter("ngayVaoLam", ngayVaoLam);
			}
			if (trangThai != null) {
				query.setParameter("trangThai", trangThai);
			}
			if (chucVu != null) {
				query.setParameter("chucVu", chucVu);
			}

			listNV = query.getResultList();
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}

		return new ArrayList<NhanVien>(listNV);
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
