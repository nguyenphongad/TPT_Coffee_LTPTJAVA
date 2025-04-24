package entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"taiKhoan", "hoaDons"})
@FieldDefaults(level = AccessLevel.PRIVATE)

public class NhanVien implements Serializable {
	@Id
	String maNV;
	String tenNV;
	boolean gioiTinh;
	java.sql.Date ngaySinh;
	String sDT;
	String email;
	String maCCCD;
	String diaChi;
	java.sql.Date ngayVaoLam;
	String ghiChu;
	boolean trangThai;
	int chucVu;
	String avtString;

	@OneToOne(mappedBy = "NV", cascade = CascadeType.ALL)
	TaiKhoan taiKhoan;

	@OneToMany(mappedBy = "nhanVien", fetch = FetchType.LAZY)
	List<HoaDon> hoaDons;

	public NhanVien(String maNV) {
		super();
		this.maNV = maNV;
	}

	public NhanVien(String maNV, String tenNV, boolean gioiTinh, java.sql.Date ngaySinh, String sDT, String email,
					String maCCCD, String diaChi, java.sql.Date ngayVaoLam, String ghiChu, boolean trangThai, int chucVu,
					String avtString) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.sDT = sDT;
		this.email = email;
		this.maCCCD = maCCCD;
		this.diaChi = diaChi;
		this.ngayVaoLam = ngayVaoLam;
		this.ghiChu = ghiChu;
		this.trangThai = trangThai;
		this.chucVu = chucVu;
		this.avtString = avtString;
	}
}







