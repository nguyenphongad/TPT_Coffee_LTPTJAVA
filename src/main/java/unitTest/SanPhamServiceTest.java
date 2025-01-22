package  unitTest;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import dao.SanPham_DAO;
import entity.SanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

public class SanPhamServiceTest {


    private SanPham_DAO sanPhamDAO = new SanPham_DAO();;

    @Test
    public void testGetSanPhamtheoMa_InvalidMaSP() {
        String maSP = "INVALID123";

        // Gọi phương thức cần test
        SanPham result = sanPhamDAO.getSanPhamtheoMa(maSP);

        // Kỳ vọng
        assertNull(result);
    }

    @Test
    public void testGetSanPhamtheoMa_NotFound() {
        String maSP = "SP10001";

        // Gọi phương thức cần test
        SanPham result = sanPhamDAO.getSanPhamtheoMa(maSP);

        // Kỳ vọng
        assertNull(result);
    }

    @Test
    public void testGetSanPhamtheoMa_Found() {
        String maSP = "SP002";

        // Gọi phương thức cần test
        SanPham result = sanPhamDAO.getSanPhamtheoMa(maSP);

        // Kỳ vọng
        assertNotNull(result);
        assertEquals(maSP, result.getMaSP());
    }
}
