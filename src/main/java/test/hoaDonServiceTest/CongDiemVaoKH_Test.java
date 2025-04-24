package test.hoaDonServiceTest;

import dao.impl.HoaDon_DAO;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;

import static org.junit.Assert.*;

public class CongDiemVaoKH_Test {
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
    public void testCongDiemVaoKH_Valid() {
        // Arrange
        String sdt = "0999888777"; // số điện thoại của khách hàng
        int diem = 50;

        // Act
        boolean result = hoaDonDAO.congDiemVaoKH(diem, sdt);

        // Assert
        assertTrue("Expected successful update of customer points", result);
    }

    @Test
    public void testCongDiemVaoKH_Invalid() {
        // Arrange
        String sdt = "0000000000"; // số điện thoại không tồn tại
        int diem = 50;

        // Act
        boolean result = hoaDonDAO.congDiemVaoKH(diem, sdt);

        // Assert
        assertFalse("Expected failure due to invalid phone number", result);
    }
}
