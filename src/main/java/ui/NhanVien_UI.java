package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import customUI.CustomTabbed;

public class NhanVien_UI extends JPanel {
	public NhanVien_UI() {
		setBackground(Color.decode("#D2BAA6"));
		setLayout(new BorderLayout(0, 0));
		setBorder(new EmptyBorder(3, 3, 3, 3));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		tabbedPane.setBackground(Color.decode("#eaeaea"));
		tabbedPane.setForeground(Color.decode("#B16E5C"));
		tabbedPane.setBorder(null);
		add(tabbedPane);

		// tab quan ly nhan vien
		JPanel pnlQuanLyNhanVien = new JPanel();

		CustomTabbed cusQLNV = new CustomTabbed("Quản Lý", "icon_quanly.png", pnlQuanLyNhanVien, null);
		tabbedPane.addTab(cusQLNV.getTextTab(), cusQLNV.getPathIcon(), cusQLNV.getComponent(), cusQLNV.getTip());
		pnlQuanLyNhanVien.setLayout(new BorderLayout());
		pnlQuanLyNhanVien.add(new NhanVien_QuanLy_UI(), BorderLayout.CENTER);

		// tab quan ly nhan vien
		JPanel pnlTimKiemNhanVien = new JPanel();

		CustomTabbed cusTKNV = new CustomTabbed("Tìm Kiếm", "icon_timkiem.png", pnlTimKiemNhanVien, null);
		tabbedPane.addTab(cusTKNV.getTextTab(), cusTKNV.getPathIcon(), cusTKNV.getComponent(), cusTKNV.getTip());
		pnlTimKiemNhanVien.setLayout(new BorderLayout());
		pnlTimKiemNhanVien.add(new NhanVien_TimKiem_UI(), BorderLayout.CENTER);
	}
}
