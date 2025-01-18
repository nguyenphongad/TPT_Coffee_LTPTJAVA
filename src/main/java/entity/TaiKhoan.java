package entity;


import dao.NhanVien_DAO;
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

public class TaiKhoan {
	@Id
	@OneToOne
	@JoinColumn(name = "maNV", nullable = false, unique = true)
	NhanVien NV;
	String matKhau;
}
