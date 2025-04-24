package test.sanPhamServiceTest;

import dao.impl.SanPham_DAO;
import entity.SanPham;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;

import static org.junit.Assert.*;

public class UpdateSanPham_Test {
    private SanPham_DAO sanPhamDAO;
    private SanPham sp;

    @Before
    public void setUp() {
        try {
            sanPhamDAO = new SanPham_DAO();
        } catch (RemoteException e) {
            throw new RuntimeException("Failed to initialize SanPham_DAO", e);
        }

        sp = new SanPham(
                "SP00001",         // maSP
                "Cà phê Robusta", // tenSP
                "Cà phê",         // loaiSP
                "path/to/image.jpg", // anhSP
                50000,            // donGia
                true              // trangThai
        );
    }

    @Test
    public void testSuaSanPham_validTen() {
        // Arrange
        String tenSP = "Đá bào";
        sp.setTenSP(tenSP);

        // Act
        boolean result = sanPhamDAO.suaSP(sp);

        // Assert
        assertTrue("Expected the update to succeed", result);
        assertEquals("Expected the product name to be updated", tenSP, sp.getTenSP());
    }

    @Test
    public void testSuaSanPham_emptyTenSP() {
        // Arrange
        String tenSP = "";
        sp.setTenSP(tenSP);

        // Act
        boolean result = sanPhamDAO.suaSP(sp);

        // Assert
        assertFalse("Expected the update to fail for empty product name", result);
    }

    @Test
    public void testSuaSanPham_invalidDonGia() {
        // Arrange
        double donGia = -12000;
        sp.setDonGia(donGia);

        // Act
        boolean result = sanPhamDAO.suaSP(sp);

        // Assert
        assertFalse("Expected the update to fail for negative price", result);
    }

    @Test
    public void testSuaSanPham_validDonGia() {
        // Arrange
        double donGia = 12000;
        sp.setDonGia(donGia);

        // Act
        boolean result = sanPhamDAO.suaSP(sp);

        // Assert
        assertTrue("Expected the update to succeed for valid price", result);
        assertEquals("Expected the product price to be updated", donGia, sp.getDonGia(), 0.01);
    }

    @Test
    public void testSuaSanPham_validTrangThai() {
        // Arrange
        boolean trangThai = !sp.isTrangThai();
        sp.setTrangThai(trangThai);

        // Act
        boolean result = sanPhamDAO.suaSP(sp);

        // Assert
        assertTrue("Expected the update to succeed for valid status", result);
        assertEquals("Expected the product status to be updated", trangThai, sp.isTrangThai());
    }

    @Test
    public void testSuaSanPham_notFoundSanPham() {
        // Arrange
        sp.setMaSP("S12030"); // Non-existent product ID
        sp.setTenSP("Trà Sữa");

        // Act
        boolean result = sanPhamDAO.suaSP(sp);

        // Assert
        assertFalse("Expected the update to fail for non-existent product", result);
    }
}