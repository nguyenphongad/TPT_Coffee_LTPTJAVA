package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import customUI.ImageScaler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.JMenuItem;

public class Menu_UI extends JPanel implements ActionListener, MouseInputListener {
	private Main_UI main;
	private JMenuItem mnTrangChu, mnBanHang, mnHoaDon, mnSanPham, mnThongKe, mnNhanVien;

	public Menu_UI(Main_UI main) {
		this.main = main;

		setLayout(new BorderLayout(0, 0));

		JPanel pnlContainer = new JPanel();
		pnlContainer.setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(192, 192, 192)));
		pnlContainer.setBackground(Color.decode("#B16E5C"));
		add(pnlContainer, BorderLayout.WEST);
		pnlContainer.setLayout(new BorderLayout(0, 0));

		JPanel pnlLogo = new JPanel();
		pnlLogo.setBorder(new CompoundBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(192, 192, 192)),
				new EmptyBorder(5, 5, 5, 5)));
		pnlLogo.setBackground(Color.decode("#B16E5C"));
		pnlContainer.add(pnlLogo, BorderLayout.NORTH);
		pnlLogo.setLayout(new BorderLayout(0, 0));

		JLabel lblImageLogo = new JLabel("");
		lblImageLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblImageLogo.setIcon(new ImageScaler("/image/icon_logo.PNG", 120, 120).getScaledImageIcon());
		pnlLogo.add(lblImageLogo, BorderLayout.CENTER);

		JLabel lblNameVPT = new JLabel("VPTCoffee");
		lblNameVPT.setFont(new Font("Segoe UI Semibold", Font.BOLD, 25));
		lblNameVPT.setBorder(new EmptyBorder(0, 0, 10, 0));
		lblNameVPT.setForeground(Color.decode("#ffffff"));
		lblNameVPT.setHorizontalAlignment(SwingConstants.CENTER);
		pnlLogo.add(lblNameVPT, BorderLayout.SOUTH);

		JPanel pnlMenu = new JPanel();
		pnlContainer.add(pnlMenu, BorderLayout.CENTER);
		pnlMenu.setLayout(new BoxLayout(pnlMenu, BoxLayout.Y_AXIS));

		mnTrangChu = new JMenuItem("  TRANG CHỦ");
		mnTrangChu.setFont(new Font("Segoe UI Semibold", Font.BOLD, 21));
		mnTrangChu.setIcon(new ImageScaler("/icon/icon_home.png", 25, 25).getScaledImageIcon());
		mnTrangChu.setBackground(Color.decode("#B16E5C"));
		mnTrangChu.setForeground(Color.decode("#ffffff"));
		mnTrangChu.setBorder(new CompoundBorder(new EmptyBorder(0, 10, 0, 10),
				new MatteBorder(0, 0, 1, 0, Color.decode("#f2f2f2"))));
		pnlMenu.add(mnTrangChu);

//		mnBanHang = new JMenuItem("  BÁN HÀNG");
//		mnBanHang.setFont(new Font("Segoe UI Semibold", Font.BOLD, 21));
//		mnBanHang.setIcon(new ImageScaler("/icon/icon_banhang.png", 25, 25).getScaledImageIcon());
//		mnBanHang.setBackground(Color.decode("#B16E5C"));
//		mnBanHang.setForeground(Color.decode("#ffffff"));
//		mnBanHang.setBorder(new CompoundBorder(new EmptyBorder(0, 10, 0, 10),
//				new MatteBorder(0, 0, 1, 0, Color.decode("#f2f2f2"))));
//		pnlMenu.add(mnBanHang);
		
		mnSanPham = new JMenuItem("  SẢN PHẨM");
		mnSanPham.setFont(new Font("Segoe UI Semibold", Font.BOLD, 21));
		mnSanPham.setIcon(new ImageScaler("/icon/icon_sanpham.png", 25, 25).getScaledImageIcon());
		mnSanPham.setBackground(Color.decode("#7f483a"));
		mnSanPham.setForeground(Color.decode("#ffffff"));
		mnSanPham.setBorder(new CompoundBorder(new EmptyBorder(0, 10, 0, 10),
				new MatteBorder(0, 0, 1, 0, Color.decode("#f2f2f2"))));
		pnlMenu.add(mnSanPham);
		
		
		mnHoaDon = new JMenuItem("  HOÁ ĐƠN");
		mnHoaDon.setFont(new Font("Segoe UI Semibold", Font.BOLD, 21));
		mnHoaDon.setIcon(new ImageScaler("/icon/icon_hoadon.png", 25, 25).getScaledImageIcon());
		mnHoaDon.setBackground(Color.decode("#B16E5C"));
		mnHoaDon.setForeground(Color.decode("#ffffff"));
		mnHoaDon.setBorder(new CompoundBorder(new EmptyBorder(0, 10, 0, 10),
				new MatteBorder(0, 0, 1, 0, Color.decode("#f2f2f2"))));
		pnlMenu.add(mnHoaDon);

		

		mnThongKe = new JMenuItem("  THỐNG KÊ");
		mnThongKe.setFont(new Font("Segoe UI Semibold", Font.BOLD, 21));
		mnThongKe.setIcon(new ImageScaler("/icon/icon_thongke.png", 25, 25).getScaledImageIcon());
		mnThongKe.setBackground(Color.decode("#B16E5C"));
		mnThongKe.setForeground(Color.decode("#ffffff"));
		mnThongKe.setBorder(new CompoundBorder(new EmptyBorder(0, 10, 0, 10),
				new MatteBorder(0, 0, 1, 0, Color.decode("#f2f2f2"))));
		pnlMenu.add(mnThongKe);

		mnNhanVien = new JMenuItem("  NHÂN VIÊN");
		mnNhanVien.setFont(new Font("Segoe UI Semibold", Font.BOLD, 21));
		mnNhanVien.setIcon(new ImageScaler("/icon/icon_nhanvien.png", 25, 25).getScaledImageIcon());
		mnNhanVien.setBackground(Color.decode("#B16E5C"));
		mnNhanVien.setForeground(Color.decode("#ffffff"));
		mnNhanVien.setBorder(new CompoundBorder(new EmptyBorder(0, 10, 0, 10),
				new MatteBorder(0, 0, 1, 0, Color.decode("#f2f2f2"))));
		pnlMenu.add(mnNhanVien);

		mnTrangChu.addActionListener(this);
//		mnBanHang.addActionListener(this);
		mnHoaDon.addActionListener(this);
		mnSanPham.addActionListener(this);
		mnThongKe.addActionListener(this);
		mnNhanVien.addActionListener(this);
		
		
		if(main.taiKhoan.getNV().getChucVu()==1) {
			mnNhanVien.setEnabled(true);
		}else if(main.taiKhoan.getNV().getChucVu()==0){
			mnNhanVien.setEnabled(false);
			mnNhanVien.setBackground(Color.decode("#8c7069"));
		}
		
		

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		// click hien giao dien Trang Chu
		if (o == mnTrangChu) {
			resetPage(new TrangChu_UI());
			
			mnTrangChu.setBackground(Color.decode("#7f483a"));
//			mnBanHang.setBackground(Color.decode("#B16E5C"));
			mnHoaDon.setBackground(Color.decode("#B16E5C"));
			mnSanPham.setBackground(Color.decode("#B16E5C"));
			mnThongKe.setBackground(Color.decode("#B16E5C"));
			mnNhanVien.setBackground(Color.decode("#B16E5C"));
			
		}

		// click hien giao dien Ban Hang
//		if (o == mnBanHang) {
//			resetPage(new BanHang_UI());
//			
//			mnTrangChu.setBackground(Color.decode("#B16E5C"));
//			mnBanHang.setBackground(Color.decode("#7f483a"));
//			mnHoaDon.setBackground(Color.decode("#B16E5C"));
//			mnSanPham.setBackground(Color.decode("#B16E5C"));
//			mnThongKe.setBackground(Color.decode("#B16E5C"));
//			mnNhanVien.setBackground(Color.decode("#B16E5C"));
//			
//		}

		// click hien giao dien Hoa don
		if (o == mnHoaDon) {
			resetPage(new HoaDon_UI());
			
			mnTrangChu.setBackground(Color.decode("#B16E5C"));
//			mnBanHang.setBackground(Color.decode("#B16E5C"));
			mnHoaDon.setBackground(Color.decode("#7f483a"));
			mnSanPham.setBackground(Color.decode("#B16E5C"));
			mnThongKe.setBackground(Color.decode("#B16E5C"));
			mnNhanVien.setBackground(Color.decode("#B16E5C"));
			
		}
		// click hien giao dien san pham
		if (o == mnSanPham) {
			resetPage(new SanPham_UI(main));
			
			mnTrangChu.setBackground(Color.decode("#B16E5C"));
//			mnBanHang.setBackground(Color.decode("#B16E5C"));
			mnHoaDon.setBackground(Color.decode("#B16E5C"));
			mnSanPham.setBackground(Color.decode("#7f483a"));
			mnThongKe.setBackground(Color.decode("#B16E5C"));
			mnNhanVien.setBackground(Color.decode("#B16E5C"));
			
			
			
		}
		// click hien giao dien thong ke
		if (o == mnThongKe) {
			resetPage(new ThongKe_UI());
			
			mnTrangChu.setBackground(Color.decode("#B16E5C"));
//			mnBanHang.setBackground(Color.decode("#B16E5C"));
			mnHoaDon.setBackground(Color.decode("#B16E5C"));
			mnSanPham.setBackground(Color.decode("#B16E5C"));
			mnThongKe.setBackground(Color.decode("#7f483a"));
			mnNhanVien.setBackground(Color.decode("#B16E5C"));
		}
		// click hien giao dien nhan vien
		if (o == mnNhanVien) {
			resetPage(new NhanVien_UI());
			
			mnTrangChu.setBackground(Color.decode("#B16E5C"));
//			mnBanHang.setBackground(Color.decode("#B16E5C"));
			mnHoaDon.setBackground(Color.decode("#B16E5C"));
			mnSanPham.setBackground(Color.decode("#B16E5C"));
			mnThongKe.setBackground(Color.decode("#B16E5C"));
			mnNhanVien.setBackground(Color.decode("#7f483a"));
		}
	}

	// ham chuyen menu
	public void resetPage(Component cmn) {
		main.pnlContent.removeAll();
		main.pnlContent.add(cmn, BorderLayout.CENTER);
		main.validate();
	}
}
