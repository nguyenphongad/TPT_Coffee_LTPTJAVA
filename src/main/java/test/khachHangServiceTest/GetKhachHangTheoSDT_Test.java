package test.khachHangServiceTest;

import dao.impl.KhachHang_DAO;
import entity.KhachHang;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;

import static org.junit.Assert.*;

public class GetKhachHangTheoSDT_Test {
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
    public void testGetKhachHangTheoSDT_Valid() {
        String sdt = "0123456789"; // SDT này cần có trong DB trước
        KhachHang kh = khachHangDAO.getKhachHangTheoSDT(sdt);
        assertNotNull("Expected a customer to be returned", kh);
        assertEquals("Expected correct phone number", sdt, kh.getSoDienThoai());
    }

    @Test
    public void testGetKhachHangTheoSDT_Invalid() {
        String sdt = "0000000000"; // Không có trong DB
        KhachHang kh = khachHangDAO.getKhachHangTheoSDT(sdt);
        assertNull("Expected null for non-existent customer", kh);
    }

    @Test
    public void testGetKhachHangTheoSDT_Null() {
        KhachHang kh = khachHangDAO.getKhachHangTheoSDT(null);
        assertNull("Expected null when SDT is null", kh);
    }
}
