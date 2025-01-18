package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)

public class SanPham {
	@Id
	String maSP;
	String tenSP;
	String loaiSP;
	String anhSP;
	double donGia;
	boolean trangThai;

	public SanPham() {
		super();
	}

	public SanPham(String maSP) {
		super();
		this.maSP = maSP;
	}
}
