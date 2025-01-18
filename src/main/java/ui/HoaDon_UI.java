package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import customUI.CustomTabbed;

public class HoaDon_UI extends JPanel{
	public HoaDon_UI() {
		setBackground(Color.decode("#D2BAA6"));
		setLayout(new BorderLayout(0, 0));
		setBorder(new EmptyBorder(3, 3, 3, 3));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		tabbedPane.setBackground(Color.decode("#eaeaea"));
		tabbedPane.setForeground(Color.decode("#B16E5C"));
		tabbedPane.setBorder(null);
		add(tabbedPane);
		
		
		
		// tab quan ly hoa don
		JPanel pnlQuanLyHoaDon = new JPanel();
		
		CustomTabbed cusQLHD = new CustomTabbed("Quản Lý", "icon_quanly.png", pnlQuanLyHoaDon, null);
		tabbedPane.addTab(cusQLHD.getTextTab(), cusQLHD.getPathIcon(), cusQLHD.getComponent(), cusQLHD.getTip());
		pnlQuanLyHoaDon.setLayout(new BorderLayout());
		pnlQuanLyHoaDon.add(new HoaDon_QuanLy_UI(), BorderLayout.CENTER);
	}
}
