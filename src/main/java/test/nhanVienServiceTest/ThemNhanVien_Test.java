package test.nhanVienServiceTest;

import dao.impl.NhanVien_DAO;
import entity.NhanVien;
import entity.TaiKhoan;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;
import java.sql.Date;

import static org.junit.Assert.*;

public class ThemNhanVien_Test {
    private NhanVien_DAO nhanVienDAO;

    @Before
    public void setUp() {
        try {
            nhanVienDAO = new NhanVien_DAO();
        } catch (RemoteException e) {
            throw new RuntimeException("Failed to initialize NhanVien_DAO", e);
        }
    }

    @Test
    public void testThemNhanVien_Valid() {
        // Arrange
        NhanVien nv = new NhanVien("NV123", "Test NV", true, 
            new Date(System.currentTimeMillis()), "0123456789", 
            "test@email.com", "123456789", "Test Address",
            new Date(System.currentTimeMillis()), "Test Note", true, 1, "avatar.jpg");
        TaiKhoan tk = new TaiKhoan(nv, "password123");

        // Act
        boolean result = nhanVienDAO.themNhanVien(nv, tk);

        // Assert
        assertTrue("Expected successful addition of NhanVien", result);
    }

    @Test
    public void testThemNhanVien_Null() {
        // Act
        boolean result = nhanVienDAO.themNhanVien(null, null);

        // Assert
        assertFalse("Expected failure when adding null NhanVien", result);
    }
}
