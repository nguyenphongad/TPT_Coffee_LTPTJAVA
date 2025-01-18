package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import customUI.CustomTabbed;

public class ThongKe_UI extends JPanel{
	public ThongKe_UI() {
		setBackground(Color.decode("#D2BAA6"));
		setLayout(new BorderLayout(0, 0));
		setBorder(new EmptyBorder(3, 3, 3, 3));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		tabbedPane.setBackground(Color.decode("#eaeaea"));
		tabbedPane.setForeground(Color.decode("#B16E5C"));
		tabbedPane.setBorder(null);
		add(tabbedPane);
		
		
		
		// tab thong ke doanh thu
		JPanel pnlThongKeDoanhThu = new JPanel();
		
		CustomTabbed cusTKDT = new CustomTabbed("Doanh Thu", "icon_doanhthu.png", pnlThongKeDoanhThu, null);
		tabbedPane.addTab(cusTKDT.getTextTab(), cusTKDT.getPathIcon(), cusTKDT.getComponent(), cusTKDT.getTip());
		pnlThongKeDoanhThu.setLayout(new BorderLayout());
		pnlThongKeDoanhThu.add(new ThongKe_DoanhThu_UI(), BorderLayout.CENTER);
		
		
		// tab thong ke top san pham
		JPanel pnlTopSanPham = new JPanel();
		
		CustomTabbed cusTSP = new CustomTabbed("TOP Sản Phẩm", "icon_topsanpham.png", pnlTopSanPham, null);
		tabbedPane.addTab(cusTSP.getTextTab(), cusTSP.getPathIcon(), cusTSP.getComponent(), cusTSP.getTip());
		pnlTopSanPham.setLayout(new BorderLayout());
		pnlTopSanPham.add(new ThongKe_TopSanPham_UI(), BorderLayout.CENTER);
	}
}
