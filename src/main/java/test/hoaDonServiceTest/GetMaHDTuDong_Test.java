package test.hoaDonServiceTest;

import dao.impl.HoaDon_DAO;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;

import static org.junit.Assert.*;

public class GetMaHDTuDong_Test {
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
    public void testGetMaHDTuDong() {
        // Act
        String maHD = hoaDonDAO.getMaHDTuDong();

        // Assert
        assertNotNull("Expected maHD to be found", maHD);
    }
}
