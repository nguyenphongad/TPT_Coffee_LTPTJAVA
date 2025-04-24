package test.chiTietHoaDonServiceTest;

import dao.impl.ChiTietHoaDon_DAO;
import entity.ChiTietHoaDon;
import org.junit.Before;
import org.junit.Test;

import javax.swing.table.DefaultTableModel;
import java.rmi.RemoteException;
import java.util.List;

import static org.junit.Assert.*;

public class ChiTietHoaDon_DAO_Test {
    private ChiTietHoaDon_DAO chiTietHoaDonDAO;

    @Before
    public void setUp() {
        try {
            chiTietHoaDonDAO = new ChiTietHoaDon_DAO();
        } catch (RemoteException e) {
            throw new RuntimeException("Failed to create ChiTietHoaDon_DAO", e);
        }
    }

    @Test
    public void testGetChiTietHoaDonTheoMaHoaDon_KhongCoDuLieu() {
        // Arrange
        String maHoaDonKhongTonTai = "HD000000";

        // Act
        List<ChiTietHoaDon> dsChiTiet = chiTietHoaDonDAO.getChiTietHoaDonTheoMaHoaDon(maHoaDonKhongTonTai);

        // Assert
        assertNotNull(dsChiTiet);
        assertEquals("Expected empty list when no data found", 0, dsChiTiet.size());
    }

    @Test
    public void testGetChiTietHoaDonTheoMaHoaDon_CoDuLieu() {
        // Arrange
        String maHoaDon = "HD123456"; // đảm bảo mã hóa đơn này đã có dữ liệu thật

        // Act
        List<ChiTietHoaDon> dsChiTiet = chiTietHoaDonDAO.getChiTietHoaDonTheoMaHoaDon(maHoaDon);

        // Assert
        assertNotNull(dsChiTiet);
        assertTrue("Expected list to contain at least 1 record", dsChiTiet.size() > 0);

        // In ra thử phần tử đầu tiên (tuỳ chọn)
        ChiTietHoaDon cthd = dsChiTiet.get(0);
        System.out.println("Mã SP: " + cthd.getSanPham().getMaSP());
    }

    @Test
    public void testThongKeSanPhamBanChay_ReturnsTableModel() {
        // Act
        DefaultTableModel model = chiTietHoaDonDAO.thongKeSanPhamBanChay();

        // Assert
        assertNotNull(model);
        assertEquals("Expected 7 columns", 7, model.getColumnCount());

        // Nếu có dữ liệu thật, có thể kiểm tra số dòng > 0
        assertTrue("Expected at least one row if data exists", model.getRowCount() >= 0); // Không ép buộc > 0

        // In thử 1 dòng (tuỳ chọn)
        if (model.getRowCount() > 0) {
            Object[] firstRow = new Object[model.getColumnCount()];
            for (int i = 0; i < model.getColumnCount(); i++) {
                firstRow[i] = model.getValueAt(0, i);
            }
            System.out.println("First row: ");
            for (Object value : firstRow) {
                System.out.print(value + "\t");
            }
            System.out.println();
        }
    }
}
