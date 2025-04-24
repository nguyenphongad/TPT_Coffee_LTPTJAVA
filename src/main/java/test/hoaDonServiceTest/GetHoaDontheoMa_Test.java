package test.hoaDonServiceTest;

import dao.impl.HoaDon_DAO;
import entity.HoaDon;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;

import static org.junit.Assert.*;

public class GetHoaDontheoMa_Test {
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
    public void testGetHoaDontheoMa_Valid() {
        // Arrange
        String maHD = "HD001"; // phải có maHD này trong DB

        // Act
        HoaDon hoaDon = hoaDonDAO.getHoaDontheoMa(maHD);

        // Assert
        assertNotNull("Expected HoaDon to be found", hoaDon);
        assertEquals("Expected HoaDon ID to match", maHD, hoaDon.getMaHD());
    }

    @Test
    public void testGetHoaDontheoMa_NotFound() {
        // Arrange
        String maHD = "HD999"; // maHD này không tồn tại trong DB

        // Act
        HoaDon hoaDon = hoaDonDAO.getHoaDontheoMa(maHD);

        // Assert
        assertNull("Expected HoaDon to be null", hoaDon);
    }
}
