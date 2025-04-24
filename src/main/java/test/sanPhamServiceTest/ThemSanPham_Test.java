package test.sanPhamServiceTest;

import dao.impl.SanPham_DAO;
import entity.SanPham;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;

import static org.junit.Assert.*;

public class ThemSanPham_Test {
    private SanPham_DAO sanPhamDAO;

    @Before
    public void setUp() {
        try {
            sanPhamDAO = new SanPham_DAO();
        } catch (RemoteException e) {
            throw new RuntimeException("Failed to initialize SanPham_DAO", e);
        }
    }

    @Test
    public void testThemSanPham_validInput() {
        // Arrange
        SanPham sp = new SanPham(
                "SP12340",         // maSP
                "Cà phê Robusta", // tenSP
                "Cà phê",         // loaiSP
                "path/to/image.jpg", // anhSP
                50000,            // donGia
                true              // trangThai
        );

        // Act
        boolean result = sanPhamDAO.themSanPham(sp);

        // Assert
        assertTrue("Expected the product to be added successfully", result);
    }

    @Test
    public void testThemSanPham_duplicateMaSP() {
        // Arrange
        SanPham sp = new SanPham(
                "SP00001",         // maSP (already exists)
                "Cà phê Robusta", // tenSP
                "Cà phê",         // loaiSP
                "path/to/image.jpg", // anhSP
                50000,            // donGia
                true              // trangThai
        );

        // Act
        boolean result = sanPhamDAO.themSanPham(sp);

        // Assert
        assertFalse("Expected the addition to fail for duplicate product ID", result);
    }

    @Test
    public void testThemSanPham_invalidMaSP() {
        // Arrange
        SanPham sp = new SanPham(
                "S2340",         // Invalid maSP
                "Cà phê Robusta", // tenSP
                "Cà phê",         // loaiSP
                "path/to/image.jpg", // anhSP
                50000,            // donGia
                true              // trangThai
        );

        // Act
        boolean result = sanPhamDAO.themSanPham(sp);

        // Assert
        assertFalse("Expected the addition to fail for invalid product ID", result);
    }

    @Test
    public void testThemSanPham_emptyTenSP() {
        // Arrange
        SanPham sp = new SanPham(
                "SP12388",         // maSP
                "",                // Empty tenSP
                "Cà phê",         // loaiSP
                "path/to/image.jpg", // anhSP
                50000,            // donGia
                true              // trangThai
        );

        // Act
        boolean result = sanPhamDAO.themSanPham(sp);

        // Assert
        assertFalse("Expected the addition to fail for empty product name", result);
    }

    @Test
    public void testThemSanPham_negativeDonGia() {
        // Arrange
        SanPham sp = new SanPham(
                "SP12394",         // maSP
                "Cà phê Robusta", // tenSP
                "Cà phê",         // loaiSP
                "path/to/image.jpg", // anhSP
                -50000,            // Negative donGia
                true              // trangThai
        );

        // Act
        boolean result = sanPhamDAO.themSanPham(sp);

        // Assert
        assertFalse("Expected the addition to fail for negative price", result);
    }

    @Test
    public void testThemSanPham_nullSanPham() {
        // Arrange
        SanPham sp = null;

        // Act
        boolean result = sanPhamDAO.themSanPham(sp);

        // Assert
        assertFalse("Expected the addition to fail for null product", result);
    }
}