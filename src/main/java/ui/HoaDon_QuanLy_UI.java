package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dao.ChiTietHoaDon_DAO;
import dao.HoaDon_DAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;

public class HoaDon_QuanLy_UI extends JPanel{
	private JTable tableHoaDon;
	private DefaultTableModel modelHoaDon;
	private JTable tableCTHoaDon;
	private DefaultTableModel modelCTHoaDon;
	private JScrollPane tableScroll_1;
	private JScrollPane tableScroll_2;
	private JLabel maHDValue;
	private JLabel tenKHValue;
	private JLabel sdtValue;
	private JLabel ngayLapHDValue;
	private JTextArea ghiChuArea;
	private JLabel tongTienSanPhamValue;
	private HoaDon_DAO hoaDon_DAO;
	private List<HoaDon> dsHoaDon;
	private ChiTietHoaDon_DAO chiTietHoaDon_Dao;
	private List<ChiTietHoaDon> dsChiTietHoaDon;
	
	public HoaDon_QuanLy_UI() {
		GridLayout gridLayout_1 = new GridLayout(1, 2);
		setLayout(gridLayout_1);
		
		JPanel danhSachHoaDonPnl = new JPanel();
		danhSachHoaDonPnl.setBorder(new EmptyBorder(10, 10, 10, 10));
		JPanel chiTietHoaDonPnl = new JPanel();
		chiTietHoaDonPnl.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		BorderLayout borderLayout_1 = new BorderLayout(5, 5);
		danhSachHoaDonPnl.setLayout(borderLayout_1);
		
		JLabel danhSachHoaDonLabel = new JLabel("Danh Sách Hóa Đơn", SwingConstants.CENTER);
		danhSachHoaDonLabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));
		danhSachHoaDonPnl.add(borderLayout_1.NORTH, danhSachHoaDonLabel);
		
		String[] danhSachHoaDonColHeader = { "Mã HĐ", "Tên Khách Hàng", "Tên Nhân Viên", "Ngày Lập HĐ", "Tổng tiền"};
		modelHoaDon = new DefaultTableModel(danhSachHoaDonColHeader, 0);
		tableHoaDon = new JTable(modelHoaDon);
		
		JTableHeader Theader = tableHoaDon.getTableHeader();
        Theader.setForeground(Color.BLACK); 
        Theader.setFont(new Font(Font.SERIF, Font.BOLD, 13)); 
        ((DefaultTableCellRenderer)Theader.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER); 
        
		tableScroll_1 = new JScrollPane(tableHoaDon);
		tableScroll_1.getViewport().setBackground(Color.white);
		loadDSHoaDon();
		danhSachHoaDonPnl.add(tableScroll_1, BorderLayout.CENTER);
		
		tableHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    	int row = tableHoaDon.getSelectedRow();
			       if(row != -1 && row < tableHoaDon.getRowCount()) {
			    	   dsChiTietHoaDon = new ArrayList<ChiTietHoaDon>();
			    	   chiTietHoaDon_Dao = new ChiTietHoaDon_DAO();
			    	   dsChiTietHoaDon = chiTietHoaDon_Dao.getChiTietHoaDonTheoMaHoaDon(tableHoaDon.getValueAt(row, 0).toString());
			    	   loadDSCTHoaDon();
			    	   
			    	   hoaDon_DAO = new HoaDon_DAO();
			    	   HoaDon hoaDon =  hoaDon_DAO.getHoaDontheoMa(tableHoaDon.getValueAt(row, 0).toString());
			    	  
			    	   String formated_NgayLap = hoaDon.getNgayLap().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
			    	   DecimalFormat decimalFormat = new DecimalFormat("#,###.#VND");
			    	   
			    	   maHDValue.setText(hoaDon.getMaHD());
			    	   tenKHValue.setText(hoaDon.getKhachHang().getTenKH());
			    	   sdtValue.setText(hoaDon.getKhachHang().getSoDienThoai());
			    	   ngayLapHDValue.setText(formated_NgayLap);
			    	   tongTienSanPhamValue.setText(decimalFormat.format(hoaDon.getTongTien()));
			    	   
			    	   ghiChuArea.setText(dsChiTietHoaDon.get(0).getGhiChu());
			       }
		    }
		});
		
		
		BorderLayout borderLayout_2 = new BorderLayout(5, 5);
		chiTietHoaDonPnl.setLayout(borderLayout_2);
		
		JLabel chiTietHoaDonLabel = new JLabel("Chi Tiết Hóa Đơn", SwingConstants.CENTER);
		chiTietHoaDonLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		chiTietHoaDonPnl.add(borderLayout_2.NORTH, chiTietHoaDonLabel);
		
		String[] chiTietHoaDonColHeader = { "Tên SP", "Đơn Giá", "Số lượng", "Thành Tiền"};
		modelCTHoaDon = new DefaultTableModel(chiTietHoaDonColHeader, 0);
		tableCTHoaDon = new JTable(modelCTHoaDon);
		
		JTableHeader Theader_1 = tableCTHoaDon.getTableHeader();
		Theader_1.setForeground(Color.BLACK); 
		Theader_1.setFont(new Font(Font.SERIF, Font.BOLD, 13)); 
        ((DefaultTableCellRenderer)Theader_1.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER); 
        
		tableScroll_2 = new JScrollPane(tableCTHoaDon);
		tableScroll_2.getViewport().setBackground(Color.white);
		chiTietHoaDonPnl.add(tableScroll_2, BorderLayout.CENTER);

		JPanel noiDungChiTietHoaDonPnl = new JPanel();
		
		GridLayout gridLayout_2 = new GridLayout(1, 2);
		noiDungChiTietHoaDonPnl.setLayout(gridLayout_2);
		
		JPanel leftColPanel = new JPanel();
		BoxLayout boxLayout_1 = new BoxLayout(leftColPanel, BoxLayout.Y_AXIS);
		leftColPanel.setLayout(boxLayout_1);
		
		JPanel rightColPanel = new JPanel();
		BoxLayout boxLayout_2 = new BoxLayout(rightColPanel, BoxLayout.Y_AXIS);
		rightColPanel.setLayout(boxLayout_2);
		
		JPanel maHDPanel = new JPanel();
		maHDPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel maHDLabel = new JLabel("Mã Hóa Đơn:");
		maHDValue = new JLabel("");
		maHDValue.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
		maHDPanel.add(maHDLabel);
		maHDPanel.add(maHDValue);
		
		JPanel tenKHPanel = new JPanel();
		tenKHPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel tenKHLabel = new JLabel("Tên Khách Hàng:");
		tenKHValue = new JLabel("");
		tenKHValue.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
		tenKHPanel.add(tenKHLabel);
		tenKHPanel.add(tenKHValue);
		
		JPanel sdtPanel = new JPanel();
		sdtPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel sdtLabel = new JLabel("Số Điện Thoại:");
		sdtValue = new JLabel("");
		sdtValue.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
		sdtPanel.add(sdtLabel);
		sdtPanel.add(sdtValue);
		
		JPanel ngayLapHDPanel = new JPanel();
		ngayLapHDPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel ngayLapHDLabel = new JLabel("Ngày Lập HĐ:");
		ngayLapHDValue = new JLabel("");
		ngayLapHDValue.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
		ngayLapHDPanel.add(ngayLapHDLabel);
		ngayLapHDPanel.add(ngayLapHDValue);
		
		JPanel tongTienSanPhamPanel = new JPanel();
		tongTienSanPhamPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel tongTienSanPhamLabel = new JLabel("Tổng tiền:");
		tongTienSanPhamValue = new JLabel("");
		tongTienSanPhamValue.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
		tongTienSanPhamPanel.add(tongTienSanPhamLabel);
		tongTienSanPhamPanel.add(tongTienSanPhamValue);

		leftColPanel.add(maHDPanel);
		leftColPanel.add(tenKHPanel);
		leftColPanel.add(sdtPanel);
		leftColPanel.add(ngayLapHDPanel);
		leftColPanel.add(tongTienSanPhamPanel);
		
		JPanel ghiChuPanel = new JPanel();
		ghiChuPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel ghiChuLabel = new JLabel("Ghi Chú Hóa Đơn:");
		ghiChuArea = new JTextArea(10, 30);
		ghiChuPanel.add(ghiChuLabel);
		ghiChuPanel.add(ghiChuArea);
		
		rightColPanel.add(ghiChuPanel);
		
		noiDungChiTietHoaDonPnl.add(leftColPanel);
		noiDungChiTietHoaDonPnl.add(rightColPanel);
		
		chiTietHoaDonPnl.add(BorderLayout.SOUTH, noiDungChiTietHoaDonPnl);
		
		add(danhSachHoaDonPnl);
		add(chiTietHoaDonPnl);		
	}
	
	public void loadDSHoaDon() {
		hoaDon_DAO = new HoaDon_DAO();
		dsHoaDon = hoaDon_DAO.getAllHoaDon();
		DecimalFormat decimalFormat = new DecimalFormat("#,###.#VND");
		modelHoaDon.setRowCount(0);
		for(HoaDon hoaDon : dsHoaDon) {
			
			String formated_NgayLap = hoaDon.getNgayLap().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
			String rows[] = {hoaDon.getMaHD(), hoaDon.getKhachHang().getTenKH(), hoaDon.getNhanVien().getTenNV(), formated_NgayLap, decimalFormat.format(hoaDon.getTongTien())};
			modelHoaDon.addRow(rows);
		}
		repaint();
	}
	
	public void loadDSCTHoaDon() {
		DecimalFormat decimalFormat = new DecimalFormat("#,###.#VND");
		modelCTHoaDon.setRowCount(0);
		for(ChiTietHoaDon chiTietHoaDon : dsChiTietHoaDon) {
			String formated_donGia = decimalFormat.format(chiTietHoaDon.getSanPham().getDonGia());
			String formated_thanhTien = decimalFormat.format(chiTietHoaDon.getSanPham().getDonGia() * chiTietHoaDon.getSoLuong());
			String rows[] = {chiTietHoaDon.getSanPham().getTenSP(), formated_donGia, Integer.toString(chiTietHoaDon.getSoLuong()), formated_thanhTien};
			modelCTHoaDon.addRow(rows);
		}
		repaint();
	}
}
