package test.hoaDonServiceTest;

import dao.impl.HoaDon_DAO;
import entity.HoaDon;
import entity.NhanVien;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class ThemHoaDon_Test {
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
    public void testThemHoaDon_Valid() {
        // Arrange
        HoaDon hoaDon = new HoaDon("HD001", LocalDateTime.now(), new NhanVien(), null, 1000.0);

        // Act
        boolean result = hoaDonDAO.themHoaDon(hoaDon);

        // Assert
        assertTrue("Expected successful addition of HoaDon", result);
    }

    @Test
    public void testThemHoaDon_Invalid() {
        // Arrange
        HoaDon hoaDon = null;

        // Act
        boolean result = hoaDonDAO.themHoaDon(hoaDon);

        // Assert
        assertFalse("Expected failure due to null HoaDon", result);
    }
}