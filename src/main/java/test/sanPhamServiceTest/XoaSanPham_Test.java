package test.sanPhamServiceTest;

import dao.impl.SanPham_DAO;
import org.junit.Test;

import java.rmi.RemoteException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class XoaSanPham_Test {
    private SanPham_DAO sanPhamDAO;

    {
        try {
            sanPhamDAO = new SanPham_DAO();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

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
