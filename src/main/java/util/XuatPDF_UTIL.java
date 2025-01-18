package util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import entity.ChiTietHoaDon;
import entity.SanPham;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class XuatPDF_UTIL {
	public void xuatHoaDon(ChiTietHoaDon cthd, ArrayList<SanPham> dssp) throws JRException {
		InputStream arq = getClass().getResourceAsStream("/export_template/HoaDon.jrxml");
		JasperReport report = JasperCompileManager.compileReport(arq);

		Map<String, Object> tthd = new HashMap<String, Object>();

		tthd.put("time", cthd.getHoaDon().getNgayLap());
		tthd.put("soDienThoai", cthd.getHoaDon().getMaHD());

		ArrayList<SanPhamForm> listSP = new ArrayList<>();
		for (int i = 0; i < dssp.size(); i++) {
			listSP.add(new SanPhamForm(String.valueOf(i), dssp.get(i).getMaSP(), dssp.get(i).getTenSP(),
					dssp.get(i).getDonGia()));
		}
		JasperPrint print = JasperFillManager.fillReport(report, tthd, new JRBeanCollectionDataSource(listSP));

		JasperViewer.viewReport(print, false);

	}
}
