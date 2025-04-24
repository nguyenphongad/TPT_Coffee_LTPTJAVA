package test.loginServiceTest;

import dao.impl.Login_DAO;
import entity.TaiKhoan;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;

import static org.junit.Assert.*;

public class KiemTraDangNhap_Test {
    private Login_DAO loginDAO;

    @Before
    public void setUp() {
        try {
            loginDAO = new Login_DAO();
        } catch (RemoteException e) {
            throw new RuntimeException("Failed to initialize Login_DAO", e);
        }
    }

    @Test
    public void testKiemTraDangNhap_Valid() {
        // Arrange
        String maNV = "NV001"; // Sử dụng mã NV có thật trong DB
        String matKhau = "password123"; // Sử dụng mật khẩu thật

        // Act
        TaiKhoan taiKhoan = loginDAO.kiemTraDangNhap(maNV, matKhau);

        // Assert
        assertNotNull("Expected valid login credentials", taiKhoan);
        assertEquals("Expected matching NV ID", maNV, taiKhoan.getNV().getMaNV());
    }

    @Test
    public void testKiemTraDangNhap_Invalid() {
        // Arrange
        String maNV = "invalid";
        String matKhau = "invalid";

        // Act
        TaiKhoan taiKhoan = loginDAO.kiemTraDangNhap(maNV, matKhau);

        // Assert
        assertNull("Expected null for invalid credentials", taiKhoan);
    }
}
