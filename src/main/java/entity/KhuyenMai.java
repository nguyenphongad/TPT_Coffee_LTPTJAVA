package entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "KhuyenMai")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KhuyenMai implements Serializable {
    @Id
    @Column(name = "maKM")
    String maKM;

    @Column(name = "tenKM")
    String tenKM;

    @Column(name = "moTa")
    String moTa;

    @Column(name = "ngayBatDau")
    LocalDateTime ngayBatDau;

    @Column(name = "ngayKetThuc")
    LocalDateTime ngayKetThuc;

    @Column(name = "giamGia")
    double giamGia;

    @Column(name = "trangThai")
    boolean trangThai;

    public KhuyenMai(String maKM) {
        this.maKM = maKM;
    }
}