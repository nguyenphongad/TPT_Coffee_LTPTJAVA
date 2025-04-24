package test.hoaDonServiceTest;

import dao.impl.HoaDon_DAO;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;

import static org.junit.Assert.*;

public class TongDoanhThuTheoTungNgayTrongThang_Test {
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
    public void testTongDoanhThuTheoTungNgayTrongThang_Valid() {
        // Arrange
        int day = 15;
        int month = 5;

        // Act
        double doanhThu = hoaDonDAO.tongDoanhThuTheoTungNgayTrongThang(day, month);

        // Assert
        assertTrue("Expected total revenue to be greater than 0", doanhThu > 0);
    }

    @Test
    public void testTongDoanhThuTheoTungNgayTrongThang_Empty() {
        // Arrange
        int day = 31;
        int month = 2; // ngày không tồn tại trong tháng

        // Act
        double doanhThu = hoaDonDAO.tongDoanhThuTheoTungNgayTrongThang(day, month);

        // Assert
        assertEquals("Expected total revenue to be 0", 0, doanhThu, 0.01);
    }
}
