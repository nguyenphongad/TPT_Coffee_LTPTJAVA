package test.sanPhamServiceTest;

import dao.SanPham_DAO;
import entity.SanPham;
import org.junit.Test;

import static org.junit.Assert.*;

public class ThemSanPham_Test {
    private SanPham_DAO sanPhamDAO = new SanPham_DAO();
    @Test
    public void testThemSanPham_foundSanPham() {
        SanPham sp = new SanPham(
                "SP00001",         // maSP
                "Cà phê Robusta", // tenSP
                "Cà phê",         // loaiSP
                "path/to/image.jpg", // anhSP
                50000,            // donGia
                true              // trangThai
        );
        boolean result = sanPhamDAO.themSanPham(sp);

        // Kỳ vọng
        assertFalse(result);
    }
    @Test
    public void testThemSanPham_validInput() {
        SanPham sp = new SanPham(
                "SP12340",         // maSP
                "Cà phê Robusta", // tenSP
                "Cà phê",         // loaiSP
                "path/to/image.jpg", // anhSP
                50000,            // donGia
                true              // trangThai
        );
        boolean result = sanPhamDAO.themSanPham(sp);

        // Kỳ vọng
        assertTrue(result);
    }
    @Test
    public void testThemSanPham_InvalidMaSP() {
        SanPham sp = new SanPham(
                "S2340",         // maSP
                "Cà phê Robusta", // tenSP
                "Cà phê",         // loaiSP
                "path/to/image.jpg", // anhSP
                50000,            // donGia
                true              // trangThai
        );
        boolean result = sanPhamDAO.themSanPham(sp);

        // Kỳ vọng
        assertFalse(result);
    }
    @Test
    public void testThemSanPham_InvalidTenSP() {
        SanPham sp = new SanPham(
                "SP12388",         // maSP
                "", // tenSP
                "Cà phê",         // loaiSP
                "path/to/image.jpg", // anhSP
                50000,            // donGia
                true              // trangThai
        );
        boolean result = sanPhamDAO.themSanPham(sp);

        // Kỳ vọng
        assertFalse(result);
    }
    @Test
    public void testThemSanPham_InvalidDonGia() {
        SanPham sp = new SanPham(
                "SP12394",         // maSP
                "Cà phê Robusta", // tenSP
                "Cà phê",         // loaiSP
                "path/to/image.jpg", // anhSP
                -50000,            // donGia
                true              // trangThai
        );
        boolean result = sanPhamDAO.themSanPham(sp);

        // Kỳ vọng
        assertFalse(result);
    }
    @Test
    public void testThemSanPham_InvalidTrangThai() {
        SanPham sp = new SanPham(
                "SP12341",         // maSP
                "Cà phê Robusta", // tenSP
                "Cà phê",         // loaiSP
                "path/to/image.jpg", // anhSP
                50000,            // donGia
                false              // trangThai
        );
        boolean result = sanPhamDAO.themSanPham(sp);

        // Kỳ vọng
        assertFalse(result);
    }
}
