package test.sanPhamServiceTest;

import dao.impl.SanPham_DAO;
import entity.SanPham;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;

import static org.junit.Assert.*;

public class GetSanPham_Test {
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
    public void testGetSanPham_ValidMaSP() {
        // Arrange
        String maSP = "SP00001"; // mã sản phẩm này đã tồn tại trong database

        // Act
        SanPham sp = sanPhamDAO.getSanPhamtheoMa(maSP);

        // Assert
        assertNotNull("Expected a product to be returned", sp);
        assertEquals("Expected the correct product ID", maSP, sp.getMaSP());
    }

    @Test
    public void testGetSanPham_InvalidMaSP() {
        // Arrange
        String maSP = "INVALID123"; // mã sản phẩm không tồn tại

        // Act
        SanPham sp = sanPhamDAO.getSanPhamtheoMa(maSP);

        // Assert
        assertNull("Expected null for non-existing product", sp);
    }

    @Test
    public void testGetSanPham_NullMaSP() {
        // Arrange
        String maSP = null;

        // Act
        SanPham sp = sanPhamDAO.getSanPhamtheoMa(maSP);

        // Assert
        assertNull("Expected null when product ID is null", sp);
    }

    @Test
    public void testGetSanPham_EmptyMaSP() {
        // Arrange
        String maSP = "";

        // Act
        SanPham sp = sanPhamDAO.getSanPhamtheoMa(maSP);

        // Assert
        assertNull("Expected null when product ID is empty", sp);
    }
}
