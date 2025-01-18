package ui;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import customUI.CustomTabbed;

public class SanPham_UI extends JPanel implements ChangeListener{
	private Main_UI main;
	public SanPham_UI(Main_UI main) {
		this.main = main;
		setBackground(Color.decode("#D2BAA6"));
		setLayout(new BorderLayout(0, 0));
		setBorder(new EmptyBorder(3, 3, 3, 3));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		tabbedPane.setBackground(Color.decode("#eaeaea"));
		tabbedPane.setForeground(Color.decode("#B16E5C"));
		tabbedPane.setBorder(null);
		add(tabbedPane);

		// tab ban hang
		JPanel pnlBanHang = new JPanel();

		CustomTabbed cusBanHang = new CustomTabbed("Bán Hàng", "icon_banhang.png", pnlBanHang, null);
		tabbedPane.addTab(cusBanHang.getTextTab(), cusBanHang.getPathIcon(), cusBanHang.getComponent(),
				cusBanHang.getTip());
		pnlBanHang.setLayout(new BorderLayout());
		pnlBanHang.add(new SanPham_BanHang_UI(main), BorderLayout.CENTER);

		// tab quan ly san pham
		JPanel pnlQuanLySanPham = new JPanel();
		CustomTabbed cusSanPham = new CustomTabbed("Quản Lý", "icon_quanly.png", pnlQuanLySanPham, null);
		tabbedPane.addTab(cusSanPham.getTextTab(), cusSanPham.getPathIcon(), cusSanPham.getComponent(),
				cusSanPham.getTip());
		pnlQuanLySanPham.setLayout(new BorderLayout());
		pnlQuanLySanPham.add(new SanPham_QuanLy_UI(), BorderLayout.CENTER);

//		// tab tim kiem san pham
//		JPanel pnlTimKiemSanPham = new JPanel();
//		CustomTabbed cusTimKiemSanPham = new CustomTabbed("Tìm Kiếm", "icon_timkiem.png", pnlTimKiemSanPham, null);
//		tabbedPane.addTab(cusTimKiemSanPham.getTextTab(), cusTimKiemSanPham.getPathIcon(), cusTimKiemSanPham.getComponent(), cusTimKiemSanPham.getTip());
//		pnlTimKiemSanPham.setLayout(new BorderLayout());
//		pnlTimKiemSanPham.add(new SanPham_TimKiem_UI(), BorderLayout.CENTER);
		
		tabbedPane.addChangeListener(new ChangeListener() {
		    @Override
		    public void stateChanged(ChangeEvent e) {
		        if (tabbedPane.getSelectedComponent() == pnlBanHang) {
		        	pnlBanHang.removeAll();
		    		pnlBanHang.add(new SanPham_BanHang_UI(main), BorderLayout.CENTER);
		    		pnlBanHang.revalidate();
		    		pnlBanHang.repaint();
		        }
		    }
		});

	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}
}
