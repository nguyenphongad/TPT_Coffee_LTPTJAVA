package entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@ToString(exclude = "hoaDons")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KhachHang implements Serializable {
	@Id
	String soDienThoai;
	String tenKH;
	Date ngaySinh;
	int tongDiemTichLuy;

	public KhachHang(String soDienThoai, String tenKH, Date ngaySinh, int tongDiemTichLuy) {
		super();
		this.soDienThoai = soDienThoai;
		this.tenKH = tenKH;
		this.ngaySinh = ngaySinh;
		this.tongDiemTichLuy = tongDiemTichLuy;
	}

	public KhachHang(String soDienThoai) {
		super();
		this.soDienThoai = soDienThoai;
	}

	public KhachHang() {
		super();
	}

	@OneToMany(mappedBy = "khachHang", fetch = FetchType.LAZY)
	List<HoaDon> hoaDons;
}
