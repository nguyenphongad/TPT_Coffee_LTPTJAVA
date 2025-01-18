package entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class ChiTietHoaDon {
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "maHD", nullable = false)
	HoaDon hoaDon;
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "maSP", nullable = false)
	SanPham sanPham;
	int soDiemTichLuy;
	int soLuong;
	String ghiChu;
}
