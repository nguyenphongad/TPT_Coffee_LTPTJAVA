package test.sanPhamServiceTest;

import dao.impl.SanPham_DAO;
import entity.SanPham;
import org.junit.Test;

import java.rmi.RemoteException;

import static org.junit.Assert.*;

public class UpdateSanPham_Test {
    private SanPham_DAO sanPhamDAO;

    {
        try {
            sanPhamDAO = new SanPham_DAO();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    private SanPham sp = new SanPham(
            "SP00001",         // maSP
            "Cà phê Robusta", // tenSP
            "Cà phê",         // loaiSP
            "path/to/image.jpg", // anhSP
            50000,            // donGia
            true              // trangThai
    );
    @Test
    public void testSuaSanPham_validTen() {
        String tenSP = "Đá bào";
        sp.setTenSP(tenSP);
        boolean result = sanPhamDAO.suaSP(sp);
        // Kỳ vọng
        assertTrue(result);
        assertEquals(tenSP, sp.getTenSP());
    }
    @Test
    public void testSuaSanPham_emptyTenSP() {
        String tenSP = "";
        sp.setTenSP(tenSP);
        boolean result = sanPhamDAO.suaSP(sp);
        // Kỳ vọng
        assertFalse(result);
    }
    @Test
    public void testSuaSanPham_invalidDonGia() {
        double donGia = -12000;
        sp.setDonGia(donGia);
        boolean result = sanPhamDAO.suaSP(sp);
        // Kỳ vọng
        assertFalse(result);
    }
    @Test
    public void testSuaSanPham_validDonGia() {
        double donGia = 12000;
        sp.setDonGia(donGia);
        boolean result = sanPhamDAO.suaSP(sp);
        // Kỳ vọng
        assertTrue(result);
        assertEquals(donGia, sp.getDonGia());
    }
    @Test
    public void testSuaSanPham_validTrangThai() {
        boolean trangThai = !sp.isTrangThai();
        sp.setTrangThai(trangThai);
        boolean result = sanPhamDAO.suaSP(sp);
        // Kỳ vọng
        assertTrue(result);
        assertEquals(trangThai, sp.isTrangThai());
    }
    @Test
    public void testSuaSanPham_notFoundSanPham() {
        String tenSP = "Trà Sữa";
        sp.setTenSP(tenSP);
        sp.setMaSP("S12030");
        boolean result = sanPhamDAO.suaSP(sp);
        // Kỳ vọng
        assertFalse(result);
    }
}
