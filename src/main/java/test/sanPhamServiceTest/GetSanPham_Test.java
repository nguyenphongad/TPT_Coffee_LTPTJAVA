package test.sanPhamServiceTest;

import static org.junit.Assert.*;

import dao.SanPham_DAO;
import entity.SanPham;

import org.junit.Test;

public class GetSanPham_Test {


    private SanPham_DAO sanPhamDAO = new SanPham_DAO();
    ;

    @Test
    public void testGetSanPhamtheoMa_InvalidMaSP() {
        String maSP = "INVALID123";
        SanPham result = sanPhamDAO.getSanPhamtheoMa(maSP);

        // Kỳ vọng
        assertNull(result);
    }

    @Test
    public void testGetSanPhamtheoMa_NotFound() {
        String maSP = "SP10001";
        SanPham result = sanPhamDAO.getSanPhamtheoMa(maSP);

        // Kỳ vọng
        assertNull(result);
    }

    @Test
    public void testGetSanPhamtheoMa_Found() {
        String maSP = "SP002";
        SanPham result = sanPhamDAO.getSanPhamtheoMa(maSP);

        // Kỳ vọng
        assertNotNull(result);
        assertEquals(maSP, result.getMaSP());
    }
}
