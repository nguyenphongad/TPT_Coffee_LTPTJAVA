package util;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;

import java.io.FileOutputStream;
import java.util.List;



public class XuatPDF_UTIL {
	public void xuatHoaDon(NhanVien nhanVien, KhachHang khachHang, List<SanPham> dsSanPham) throws Exception {
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream("invoice.pdf"));
		document.open();

		Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);
		Font normalFont = new Font(Font.HELVETICA, 12);
		Font boldFont = new Font(Font.HELVETICA, 12, Font.BOLD);

		document.add(new Paragraph("HÓA ĐƠN BÁN HÀNG", titleFont));
		document.add(new Paragraph(" "));

		document.add(new Paragraph("Nhân viên: " + nhanVien.getTenNV() + " - " + nhanVien.getMaNV(), normalFont));
		if(khachHang != null) document.add(new Paragraph("Khách hàng: " + khachHang.getTenKH() + " - SĐT: " + khachHang.getSoDienThoai(), normalFont));
		document.add(new Paragraph(" "));

		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);
		table.setWidths(new float[]{2, 5, 2});

		table.addCell(new PdfPCell(new Phrase("Mã SP", boldFont)));
		table.addCell(new PdfPCell(new Phrase("Tên SP", boldFont)));
		table.addCell(new PdfPCell(new Phrase("Giá", boldFont)));

		double total = 0;
		for (SanPham p : dsSanPham) {
			table.addCell(p.getMaSP());
			table.addCell(p.getTenSP());
			table.addCell(String.format("%,.0f VNĐ", p.getDonGia()));
			total += p.getDonGia();
		}

		document.add(table);
		document.add(new Paragraph(" "));

		Paragraph totalPara = new Paragraph("TỔNG TIỀN: " + String.format("%,.0f VNĐ", total), boldFont);
		totalPara.setAlignment(Element.ALIGN_RIGHT);
		document.add(totalPara);

		document.close();
		System.out.println("PDF đã được tạo thành công.");
	}
}
