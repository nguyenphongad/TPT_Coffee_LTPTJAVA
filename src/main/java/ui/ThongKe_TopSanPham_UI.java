package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import dao.ChiTietHoaDon_DAO;

public class ThongKe_TopSanPham_UI extends JPanel{
	private DefaultTableModel modelSP;
	private JTable tableSP;
	private JScrollPane tableScroll;
	private ChiTietHoaDon_DAO chiTietHoaDon_DAO;
	
	public ThongKe_TopSanPham_UI() {
		BorderLayout borderLayout = new BorderLayout();
		setLayout(borderLayout);
		
		JLabel lblNewLabel = new JLabel("Thống Kê Sản Phẩm Bán Chạy", SwingConstants.CENTER);
		lblNewLabel.setBorder(new EmptyBorder(15, 0, 15, 0));
		lblNewLabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));
		add(BorderLayout.NORTH, lblNewLabel);
		
		chiTietHoaDon_DAO = new ChiTietHoaDon_DAO();
		modelSP = chiTietHoaDon_DAO.thongKeSanPhamBanChay();
		tableSP = new JTable(modelSP);
		
		JTableHeader Theader = tableSP.getTableHeader();
        Theader.setForeground(Color.BLACK); 
        Theader.setFont(new Font(Font.SERIF, Font.BOLD, 14)); 
        ((DefaultTableCellRenderer)Theader.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER); 
	        
		tableScroll = new JScrollPane(tableSP);
		tableScroll.getViewport().setBackground(Color.white);
		tableScroll.setBorder(new EmptyBorder(15, 15, 15, 15));
		
		add(BorderLayout.CENTER, tableScroll);
	}
}
