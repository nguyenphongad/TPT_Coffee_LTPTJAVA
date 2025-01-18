package ui;

import java.awt.BorderLayout;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import entity.TaiKhoan;
import util.ImportFont;

public class Main_UI extends JFrame {
	public ImportFont impFont;
	
	public JPanel pnlContainer, pnlContent;
	public TaiKhoan taiKhoan;

	public Main_UI(TaiKhoan taiKhoan) {
		ImageIcon appIcon = new ImageIcon("res/image/icon_logo.PNG");
		setIconImage(appIcon.getImage());
		this.taiKhoan = taiKhoan;
		
		setTitle("VPTCoffee | HỆ THỐNG CỬA HÀNG BÁN CÀ PHÊ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		
		// rong full man hinh
//		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		setSize(1370, 720);
		setLocationRelativeTo(null);
		
		startDefault();
		
		pnlContainer = new JPanel();
		pnlContainer.setLayout(new BorderLayout(0,0));
		setContentPane(pnlContainer);
		
		add(new Header_UI(this), BorderLayout.NORTH);
		add(new Menu_UI(this), BorderLayout.WEST);
		
		add(pnlContent, BorderLayout.CENTER);

		pnlContent.add(new SanPham_UI(this), BorderLayout.CENTER);
	}
	

	// khoi tao mac dinh khi chay ung dung
	public void startDefault() {
		pnlContent = new JPanel(new BorderLayout());
	}
	
}
