package test.khachHangServiceTest;

import dao.impl.KhachHang_DAO;
import entity.KhachHang;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.Calendar;

import static org.junit.Assert.*;

public class DangKySdtTichDiem_Test {
    private KhachHang_DAO khachHangDAO;

    @Before
    public void setUp() {
        try {
            khachHangDAO = new KhachHang_DAO();
        } catch (RemoteException e) {
            throw new RuntimeException("Failed to initialize KhachHang_DAO", e);
        }
    }

    @Test
    public void testDangKySdtTichDiem_Valid() {
        // Arrange
        String soDienThoai = "0999888777"; // số này nên chưa có trong DB
        String tenKH = "Nguyen Van Test";
        Date ngaySinh = new Date(Calendar.getInstance().getTimeInMillis()); // ngày hiện tại
        int tongDiemTichLuy = 0;

        KhachHang kh = new KhachHang(soDienThoai, tenKH, ngaySinh, tongDiemTichLuy);

        // Act
        boolean result = khachHangDAO.dangKySdtTichDiem(kh);

        // Assert
        assertTrue("Expected successful registration", result);
    }

    @Test
    public void testDangKySdtTichDiem_Duplicate() {
        // Arrange
        String soDienThoai = "0123456789"; // phải có sẵn trong DB để test trùng
        KhachHang kh = new KhachHang(soDienThoai, "Existing User", new Date(System.currentTimeMillis()), 100);

        // Act
        boolean result = khachHangDAO.dangKySdtTichDiem(kh);

        // Assert
        assertFalse("Expected failure due to duplicate phone number", result);
    }

    @Test
    public void testDangKySdtTichDiem_Null() {
        // Act
        boolean result = khachHangDAO.dangKySdtTichDiem(null);

        // Assert
        assertFalse("Expected failure when customer is null", result);
    }
}
