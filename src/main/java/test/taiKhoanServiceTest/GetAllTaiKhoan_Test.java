package test.taiKhoanServiceTest;

import dao.impl.TaiKhoan_DAO;
import entity.TaiKhoan;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class GetAllTaiKhoan_Test {
    private TaiKhoan_DAO taiKhoanDAO;

    @Before
    public void setUp() {
        try {
            taiKhoanDAO = new TaiKhoan_DAO();
        } catch (RemoteException e) {
            throw new RuntimeException("Failed to initialize TaiKhoan_DAO", e);
        }
    }

    @Test
    public void testGetAllTaiKhoan() {
        // Act
        ArrayList<TaiKhoan> taiKhoans = taiKhoanDAO.getAllTaiKhoan();

        // Assert
        assertNotNull("Expected list not to be null", taiKhoans);
        assertTrue("Expected list to contain accounts", taiKhoans.size() > 0);
    }
}
