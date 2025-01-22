package test.sanPhamServiceTest;

import dao.SanPham_DAO;
import entity.SanPham;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class XoaSanPham_Test {
    private SanPham_DAO sanPhamDAO = new SanPham_DAO();
    @Test
    public void testXoaSanPham_foundSanPham() {
        String masp = "SP12388";
        boolean result = sanPhamDAO.xoaSP(masp);

        // Kỳ vọng
        assertTrue(result);
    }
    @Test
    public void testXoaSanPham_notFoundSanPham() {
        String masp = "SP99999";
        boolean result = sanPhamDAO.xoaSP(masp);

        // Kỳ vọng
        assertFalse(result);
    }
    @Test
    public void testXoaSanPham_SanPhamDeleted() {
        String masp = "SP12341";
        boolean result = sanPhamDAO.xoaSP(masp);
        // Kỳ vọng
        assertFalse(result);
    }
}
