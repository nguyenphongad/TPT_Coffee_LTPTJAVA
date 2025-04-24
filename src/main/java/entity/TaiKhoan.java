package entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "NV")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class TaiKhoan implements Serializable {
	@Id
	@OneToOne
	@JoinColumn(name = "maNV", nullable = false, unique = true)
	NhanVien NV;
	String matKhau;
}
