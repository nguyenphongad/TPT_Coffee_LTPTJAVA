package test.hoaDonServiceTest;

import dao.impl.HoaDon_DAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.SanPham;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;

import static org.junit.Assert.*;

public class ThemChiTietHoaDon_Test {
    private HoaDon_DAO hoaDonDAO;

    @Before
    public void setUp() {
        try {
            hoaDonDAO = new HoaDon_DAO();
        } catch (RemoteException e) {
            throw new RuntimeException("Failed to initialize HoaDon_DAO", e);
        }
    }

    @Test
    public void testThemChiTietHoaDon_Valid() {
        // Arrange
        HoaDon hoaDon = new HoaDon("HD001"); // Sử dụng mã hóa đơn có sẵn hoặc tạo mới
        SanPham sanPham = new SanPham(); // Tạo đối tượng SanPham hợp lệ

        // Khởi tạo ChiTietHoaDon với các tham số hợp lệ
        ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(hoaDon, sanPham, 10, 5, "Ghi chú");

        // Act
        boolean result = hoaDonDAO.themChiTietHoaDon(chiTietHoaDon);

        // Assert
        assertTrue("Expected successful addition of ChiTietHoaDon", result);
    }

    @Test
    public void testThemChiTietHoaDon_Invalid() {
        // Arrange
        ChiTietHoaDon chiTietHoaDon = null;

        // Act
        boolean result = hoaDonDAO.themChiTietHoaDon(chiTietHoaDon);

        // Assert
        assertFalse("Expected failure due to null ChiTietHoaDon", result);
    }
}
