package test.sanPhamServiceTest;

import dao.impl.SanPham_DAO;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;

import static org.junit.Assert.*;

public class XoaSanPham_Test {
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
    public void testXoaSanPham_foundSanPham() {
        // Arrange
        String maSP = "SP12388";

        // Act
        boolean result = sanPhamDAO.xoaSP(maSP);

        // Assert
        assertTrue("Expected the product to be deleted successfully", result);
    }

    @Test
    public void testXoaSanPham_notFoundSanPham() {
        // Arrange
        String maSP = "SP99999"; // Non-existent product ID

        // Act
        boolean result = sanPhamDAO.xoaSP(maSP);

        // Assert
        assertFalse("Expected deletion to fail for non-existent product", result);
    }

    @Test
    public void testXoaSanPham_nullMaSP() {
        // Arrange
        String maSP = null;

        // Act
        boolean result = sanPhamDAO.xoaSP(maSP);

        // Assert
        assertFalse("Expected deletion to fail for null product ID", result);
    }

    @Test
    public void testXoaSanPham_emptyMaSP() {
        // Arrange
        String maSP = "";

        // Act
        boolean result = sanPhamDAO.xoaSP(maSP);

        // Assert
        assertFalse("Expected deletion to fail for empty product ID", result);
    }

    @Test
    public void testXoaSanPham_deletedSanPham() {
        // Arrange
        String maSP = "SP12341"; // Product already deleted

        // Act
        boolean result = sanPhamDAO.xoaSP(maSP);

        // Assert
        assertFalse("Expected deletion to fail for already deleted product", result);
    }
}