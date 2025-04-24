package test.sanPhamServiceTest;

import static org.junit.Assert.*;

import dao.impl.SanPham_DAO;
import entity.SanPham;

import org.junit.Test;

import java.rmi.RemoteException;

public class GetSanPham_Test {


    private SanPham_DAO sanPhamDAO;

    {
        try {
            sanPhamDAO = new SanPham_DAO();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    };

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
