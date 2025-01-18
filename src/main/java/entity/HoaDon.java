package entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HoaDon {
	@Id
	String maHD;
	LocalDateTime ngayLap;
	@ManyToOne
	@JoinColumn(name = "maNV", nullable = false)
	NhanVien nhanVien;
	@ManyToOne
	@JoinColumn(name= "maKh", nullable = false)
	KhachHang khachHang;
	double tongTien;

	public HoaDon(String maHD) {
		super();
		this.maHD = maHD;
	}
}
