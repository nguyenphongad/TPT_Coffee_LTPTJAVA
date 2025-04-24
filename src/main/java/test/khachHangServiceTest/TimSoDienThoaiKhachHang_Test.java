package test.khachHangServiceTest;

import dao.impl.KhachHang_DAO;
import entity.KhachHang;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TimSoDienThoaiKhachHang_Test {
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
    public void testTimSoDienThoaiKhachHang_Valid() {
        String sdt = "0123456789"; // SDT có trong DB
        ArrayList<KhachHang> list = khachHangDAO.timSoDienThoaiKhachHang(sdt);
        assertNotNull("Expected list of customers", list);
        assertFalse("Expected at least one customer", list.isEmpty());
    }

    @Test
    public void testTimSoDienThoaiKhachHang_Invalid() {
        String sdt = "9999999999"; // Không có trong DB
        ArrayList<KhachHang> list = khachHangDAO.timSoDienThoaiKhachHang(sdt);
        assertNotNull("Expected non-null list", list);
        assertTrue("Expected empty list", list.isEmpty());
    }

    @Test
    public void testTimSoDienThoaiKhachHang_Null() {
        ArrayList<KhachHang> list = khachHangDAO.timSoDienThoaiKhachHang(null);
        assertNotNull("Expected non-null list", list);
        assertTrue("Expected empty list when SDT is null", list.isEmpty());
    }
}
