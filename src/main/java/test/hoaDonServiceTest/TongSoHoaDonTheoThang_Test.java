package test.hoaDonServiceTest;

import dao.impl.HoaDon_DAO;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;

import static org.junit.Assert.*;

public class TongSoHoaDonTheoThang_Test {
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
    public void testTongSoHoaDonTheoThang_Valid() {
        // Arrange
        int thang = 5; // tháng có hóa đơn trong DB

        // Act
        int soHoaDon = hoaDonDAO.tongSoHoaDonTheoThang(thang);

        // Assert
        assertTrue("Expected total number of invoices to be greater than 0", soHoaDon > 0);
    }

    @Test
    public void testTongSoHoaDonTheoThang_Empty() {
        // Arrange
        int thang = 12; // tháng không có hóa đơn trong DB

        // Act
        int soHoaDon = hoaDonDAO.tongSoHoaDonTheoThang(thang);

        // Assert
        assertEquals("Expected total number of invoices to be 0", 0, soHoaDon);
    }
}
