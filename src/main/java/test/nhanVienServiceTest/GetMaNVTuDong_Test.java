package test.nhanVienServiceTest;

import dao.impl.NhanVien_DAO;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;

import static org.junit.Assert.*;

public class GetMaNVTuDong_Test {
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
    public void testGetMaNVTuDong() {
        // Act
        String maNV = nhanVienDAO.getMaNVTuDong();

        // Assert
        assertNotNull("Expected maNV to be generated", maNV);
        assertTrue("Expected maNV to start with NV", maNV.startsWith("NV"));
    }
}
