package test.hoaDonServiceTest;

import dao.impl.HoaDon_DAO;
import entity.HoaDon;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;
import java.util.List;

import static org.junit.Assert.*;

public class GetAllHoaDon_Test {
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
    public void testGetAllHoaDon() {
        // Act
        List<HoaDon> hoaDonList = hoaDonDAO.getAllHoaDon();

        // Assert
        assertNotNull("Expected list of HoaDon should not be null", hoaDonList);
        assertTrue("Expected list of HoaDon should not be empty", hoaDonList.size() > 0);
    }
}
