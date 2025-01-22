package ui;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.border.EmptyBorder;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXDatePicker;

import customUI.ImageScaler;
import customUI.RoundedButton;
import customUI.RoundedPanel;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.SanPham_DAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;
import util.SinhMaTuDong_UTIL;

import java.awt.Color;

import javax.swing.border.CompoundBorder;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;

import javax.swing.JTextArea;

public class SanPham_BanHang_UI extends JPanel implements ActionListener, MouseListener {

	private ArrayList<SanPham> sanPhamDS;
	private ArrayList<KhachHang> khachHangDS;
	private ChiTietHoaDon chiTietHoaDon;

	private KhachHang_DAO khachHang_dao = new KhachHang_DAO();

	private RoundedButton btnAddHD, btnXoa, btnHuy, btnThanhToan, btnTimKH, btnXoaRong, btnDangKy;

	private static DefaultTableModel dtbModelODSP;
	private static JTable dtblODSP;
	private JTextField txtTenKH, txtSdtKH, txtDiemTL;
	private JTextField txtChietKhau, txtTongTienSP, txtTienChietKhau;
	private JTextField txtTongTienThanhToan;
	private JSpinner txtTienKhachDua;
	private JTextField txtTienThoi, txtSDTNew, txtTenKHNew;
	private JTextArea teaGhiChu;
	private JComboBox<String> cmbPhuongThucThanhToan;
	private JLabel lblTongTienSP;
	private JXDatePicker dpNgaySinhKHnew;

	private RoundedButton btnHuyGD;
	private JCheckBox cbInHoaDon;
	private RoundedButton btnXacNhanTT;
	private JLabel lblNgayLapHDOutput, lblNhanVienLapOutput, lblKHLapOutPut, lblGChuOutPut, lblMaHD;

	private int soDiemTichLuy = 0;
	private JPanel pnlRenderSP;

	DecimalFormat decimalFormat = new DecimalFormat("#,##0");
	private Main_UI main;
	
//	---------------------------------------------------------------------

	public SanPham_BanHang_UI(Main_UI main) {
		this.main = main;
		setLayout(new BorderLayout(0, 0));

		JPanel pnlContainer = new JPanel();
		pnlContainer.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(pnlContainer);
		pnlContainer.setLayout(new GridLayout(1, 2, 0, 0));

		// phan pnl ben trai
		JPanel pnlLeft = new JPanel();
		pnlLeft.setBorder( new CompoundBorder(new MatteBorder(0, 0, 0, 2, Color.decode("#c0c0c0")), new EmptyBorder(0, 0, 0, 5)));
		pnlContainer.add(pnlLeft);
		pnlLeft.setLayout(new BorderLayout(0, 0));

		// ----------------------------------------------------
		JPanel pnlSearchAction = new JPanel();
		pnlSearchAction.setBorder(new EmptyBorder(0, 0, 5, 0));
		pnlLeft.add(pnlSearchAction, BorderLayout.NORTH);
		pnlSearchAction.setLayout(new BoxLayout(pnlSearchAction, BoxLayout.X_AXIS));

		JPanel pnlCmb = new JPanel();
		pnlCmb.setLayout(new BorderLayout());
		pnlSearchAction.add(pnlCmb);
		pnlCmb.setLayout(new BorderLayout(0, 0));

		JLabel lblTimLoaiSP = new JLabel("Tìm Loại Sản Phẩm : ");
		lblTimLoaiSP.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		pnlCmb.add(lblTimLoaiSP, BorderLayout.WEST);

		JComboBox<String> cmbLoaiSP = new JComboBox<String>();
		cmbLoaiSP.addItem("Tất Cả");
		cmbLoaiSP.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		cmbLoaiSP.setForeground(Color.decode("#000000"));
		cmbLoaiSP.setBackground(Color.decode("#FFFFFF"));
		cmbLoaiSP.setBorder(new MatteBorder(1, 1, 1, 1, Color.decode("#B16E5C")));

		pnlCmb.add(cmbLoaiSP, BorderLayout.CENTER);


		// -------------------------------------------------------------------------

		JPanel pnlTableSP = new JPanel();
		pnlLeft.add(pnlTableSP);
		pnlTableSP.setLayout(new BorderLayout(0, 0));

		pnlRenderSP = new JPanel();
		pnlRenderSP.setBackground(Color.decode("#e0e0e0"));
		pnlRenderSP.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnlRenderSP.setLayout(new GridLayout(0, 3, 5, 5));

		sanPhamDS = new ArrayList<>();

		sanPhamDS.add(new SanPham("SP00001", "Cafe Bạc Sỉu", "Coffee", "coffee_bac-xiu.PNG", 39000, true));
		sanPhamDS.add(new SanPham("SP00002", "Cafe Đen", "Coffee", "coffee_cafe-den.PNG", 35000, true));
		sanPhamDS.add(new SanPham("SP00003", "Cafe Espresso", "Coffee", "coffee_espresso.PNG", 35000, false));
		sanPhamDS.add(new SanPham("SP00004", "Cafe Cappuccino", "Coffee", "coffee_cappuccino.PNG", 55000, true));
		sanPhamDS.add(new SanPham("SP00005", "Cafe Americano", "Coffee", "coffee_americano.PNG", 45000, true));
		sanPhamDS.add(new SanPham("SP00006", "Cafe Latte Đá", "Coffee", "coffee_latte_da.PNG", 58000, true));
		sanPhamDS.add(new SanPham("SP00007", "Bánh Tiramisu", "Ăn Vặt", "anvat_banh_tiramisu.PNG", 58000, true));
		sanPhamDS.add(new SanPham("SP00008", "Khô Gà", "Ăn Vặt", "anvat_kho_ga.PNG", 20000, false));
		sanPhamDS.add(new SanPham("SP00009", "Mít Sấy", "Ăn Vặt", "anvat_mit_say.PNG", 35000, true));
		sanPhamDS.add(new SanPham("SP00010", "Bánh Mì", "Bánh Mì", "banhmi_banh_mi_nguoi.PNG", 18000, true));
		sanPhamDS.add(new SanPham("SP00011", "Cam Arabia", "Đá Say", "dasay_caramel_arabica.PNG", 65000, true));
		sanPhamDS.add(new SanPham("SP00012", "Phúc Bồn Tử", "Đá Say", "dasay_phuc_bon_tu.PNG", 70000, false));
		sanPhamDS.add(new SanPham("SP00013", "Olong Tứ Quý", "Trà", "tea_olong_tu_quy.PNG", 45000, true));
		sanPhamDS.add(new SanPham("SP00014", "Trà Đào Cam Sả", "Trà", "tea_tra_dao_cam_sa.PNG", 48000, true));

		// add san pham menu
		for (SanPham sp : sanPhamDS) {
			if (new SanPham_DAO().kiemTraMaSP(sp.getMaSP())) {

			} else {
				new SanPham_DAO().themSanPham(sp);
			}

		}

		sanPhamDS = new SanPham_DAO().getAllSanPham();

		Set<String> loaiSanPhamSet = new HashSet<>();
		for (SanPham sp : sanPhamDS) {
			loaiSanPhamSet.add(sp.getLoaiSP());
		}
		// them vao cmb loaiSanPham
		for (String loaiSP : loaiSanPhamSet) {
			cmbLoaiSP.addItem(loaiSP);
		}

		cmbLoaiSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedLoaiSP = (String) cmbLoaiSP.getSelectedItem();
				RenderSanPham(selectedLoaiSP);
			}
		});

		RenderSanPham("Tất Cả");

		JScrollPane scrollPane = new JScrollPane(pnlRenderSP, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(50);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(100);
		scrollPane.getVerticalScrollBar().setBlockIncrement(50);
		scrollPane.getHorizontalScrollBar().setBlockIncrement(100);
		pnlTableSP.add(scrollPane, BorderLayout.CENTER);

		JPanel pnlRight = new JPanel();
		pnlContainer.add(pnlRight);
		pnlRight.setBorder(new EmptyBorder(0, 5, 0, 0));
		pnlRight.setLayout(new BorderLayout(0, 0));

		JPanel pnlTitleHD = new JPanel();
		pnlTitleHD.setBorder(new EmptyBorder(0, 0, 5, 0));
		pnlRight.add(pnlTitleHD, BorderLayout.NORTH);

		pnlTitleHD.setLayout(new BorderLayout());

		// HD0000001
		lblMaHD = new JLabel("");
		lblMaHD.setForeground(Color.decode("#B16E5C"));
		lblMaHD.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		lblMaHD.setBorder(null);
		lblMaHD.setBackground(Color.decode("#ffffff"));

		pnlTitleHD.add(lblMaHD, BorderLayout.WEST);
		JPanel pnlListSpOrder = new JPanel();
		pnlRight.add(pnlListSpOrder, BorderLayout.CENTER);
		pnlListSpOrder.setLayout(new BorderLayout(0, 0));

		JPanel pnlMaHD = new JPanel();
		pnlListSpOrder.add(pnlMaHD, BorderLayout.NORTH);
		pnlMaHD.setLayout(new BorderLayout(0, 0));

		JPanel pnlContainerSPOrder = new JPanel();
		pnlContainerSPOrder.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(192, 192, 192)));
		pnlContainerSPOrder.setBackground(new Color(255, 255, 255));
		pnlListSpOrder.add(pnlContainerSPOrder, BorderLayout.CENTER);
		pnlContainerSPOrder.setLayout(new BorderLayout(0, 0));

		JPanel pnlTableSPOrder = new JPanel();
		pnlContainerSPOrder.add(pnlTableSPOrder, BorderLayout.NORTH);
		pnlTableSPOrder.setLayout(new BorderLayout());
		pnlTableSPOrder.setPreferredSize(new Dimension(1000, 140));

		// table---------------------------
		String cols[] = { "#", "Mã SP", "Tên SP", "Đơn giá", "Số lượng", "Thành tiền" };

		dtbModelODSP = new CustomTableModel(cols, 0);
		dtblODSP = new JTable(dtbModelODSP);

		dtblODSP.setRowHeight(30);
		dtblODSP.getColumnModel().getColumn(0).setPreferredWidth(20);
		dtblODSP.getColumnModel().getColumn(1).setPreferredWidth(80);
		dtblODSP.getColumnModel().getColumn(2).setPreferredWidth(120);
		dtblODSP.getColumnModel().getColumn(3).setPreferredWidth(80);
		dtblODSP.getColumnModel().getColumn(4).setPreferredWidth(50);
		dtblODSP.getColumnModel().getColumn(5).setPreferredWidth(110);

		// ham an delete xoa dong
		dtblODSP.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if (keyCode == KeyEvent.VK_DELETE) {
					int selectedRow = dtblODSP.getSelectedRow();
					if (selectedRow != -1) {
						dtbModelODSP.removeRow(selectedRow);
						((CustomTableModel) dtbModelODSP).updateRowNumbers();
						tinhToanGiaTri();
					}
				}
			}
		});

		pnlTableSPOrder.setBackground(new Color(255, 255, 255));
		JScrollPane srcODSP = new JScrollPane(dtblODSP);
		pnlTableSPOrder.add(srcODSP, BorderLayout.CENTER);

		JPanel pnlNoteOrderSP = new JPanel();
		pnlNoteOrderSP.setBorder(new CompoundBorder(new EmptyBorder(5, 0, 5, 0),
				new MatteBorder(1, 1, 1, 1, (Color) new Color(192, 192, 192))));
		pnlContainerSPOrder.add(pnlNoteOrderSP);
		pnlNoteOrderSP.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel pnlNoteLeft = new JPanel();
		pnlNoteLeft.setBorder(new CompoundBorder(new MatteBorder(0, 0, 0, 2, (Color) new Color(192, 192, 192)),
				new EmptyBorder(5, 5, 5, 5)));
		pnlNoteOrderSP.add(pnlNoteLeft);
		pnlNoteLeft.setLayout(new BoxLayout(pnlNoteLeft, BoxLayout.Y_AXIS));

		JPanel pnlLine1 = new JPanel();
		pnlNoteLeft.add(pnlLine1);
		pnlLine1.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		pnlLine1.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblSdtKH = new JLabel("Số điện thoại :");
		lblSdtKH.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		panel.add(lblSdtKH, BorderLayout.NORTH);

		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));

		txtSdtKH = new JTextField();
		txtSdtKH.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		txtSdtKH.setBorder(new MatteBorder(1, 1, 1, 1, Color.decode("#B16E5C")));
		panel_1.add(txtSdtKH);
		txtSdtKH.setColumns(10);

		Component horizontalStrut_1 = Box.createHorizontalStrut(5);
		panel_1.add(horizontalStrut_1);

		btnTimKH = new RoundedButton("Tìm", null, 10, 0, 3f);
		btnTimKH.setBackground(Color.decode("#ffffff"));
		btnTimKH.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		panel_1.add(btnTimKH);

		Component verticalStrut = Box.createVerticalStrut(10);
		pnlNoteLeft.add(verticalStrut);

		JPanel pnlLine2 = new JPanel();
		pnlNoteLeft.add(pnlLine2);
		pnlLine2.setLayout(new BorderLayout(0, 0));

		JLabel lblTenKH = new JLabel("Tên khách hàng :");
		lblTenKH.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		pnlLine2.add(lblTenKH, BorderLayout.NORTH);

		txtTenKH = new JTextField();
		txtTenKH.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		txtTenKH.setEditable(false);
		pnlLine2.add(txtTenKH, BorderLayout.CENTER);
		txtTenKH.setColumns(10);

		Component verticalStrut_1 = Box.createVerticalStrut(10);
		pnlNoteLeft.add(verticalStrut_1);

		JPanel pnlLine3 = new JPanel();
		pnlNoteLeft.add(pnlLine3);
		pnlLine3.setLayout(new GridLayout(0, 2, 5, 0));

		JPanel pnlLine3DTL = new JPanel();
		pnlLine3.add(pnlLine3DTL);
		pnlLine3DTL.setLayout(new BorderLayout(0, 0));

		JLabel lblDiemTichLuy = new JLabel("Điểm tích luỹ : ");
		lblDiemTichLuy.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		pnlLine3DTL.add(lblDiemTichLuy, BorderLayout.NORTH);

		txtDiemTL = new JTextField();
		txtDiemTL.setHorizontalAlignment(SwingConstants.RIGHT);
		txtDiemTL.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		txtDiemTL.setEditable(false);
		pnlLine3DTL.add(txtDiemTL, BorderLayout.SOUTH);
		txtDiemTL.setColumns(10);

		JPanel pnlLine3CK = new JPanel();
		pnlLine3.add(pnlLine3CK);
		pnlLine3CK.setLayout(new BorderLayout(0, 0));

		JLabel lblChietKhau = new JLabel("Chiết khẩu : ?");
		lblChietKhau.setToolTipText(
				">=300 điểm = chiết khẩu 5%   |   >=600 = chiết khẩu 10%  |  " + " >=900 = chiết khẩu 15% ");
		lblChietKhau.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		pnlLine3CK.add(lblChietKhau, BorderLayout.NORTH);

		txtChietKhau = new JTextField();
		txtChietKhau.setHorizontalAlignment(SwingConstants.RIGHT);
		txtChietKhau.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		txtChietKhau.setEditable(false);
		pnlLine3CK.add(txtChietKhau);
		txtChietKhau.setColumns(10);

		Component verticalStrut_2 = Box.createVerticalStrut(10);
		pnlNoteLeft.add(verticalStrut_2);

		JPanel pnlLine4 = new JPanel();
		pnlNoteLeft.add(pnlLine4);
		pnlLine4.setLayout(new BorderLayout(0, 0));

		teaGhiChu = new JTextArea(5, 10);
		teaGhiChu.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		teaGhiChu.setBorder(new MatteBorder(1, 1, 1, 1, Color.decode("#B16E5C")));
		pnlLine4.add(teaGhiChu, BorderLayout.CENTER);

		JLabel lblNote = new JLabel("Ghi chú");
		lblNote.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		pnlLine4.add(lblNote, BorderLayout.NORTH);

		// PHẦN BÊN PHẢI ----------------------------------------------------
		JPanel pnlNoteRight = new JPanel();
		pnlNoteRight.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnlNoteOrderSP.add(pnlNoteRight);
		pnlNoteRight.setLayout(new GridLayout(0, 1, 0, 10));

		// DÒNG 1 PHƯƠNG THỨC THANH TOÁN-------
		JPanel pnlLR1 = new JPanel();
		pnlNoteRight.add(pnlLR1);
		pnlLR1.setLayout(new BorderLayout(0, 0));

		JLabel lblPhuongThucThanhToan = new JLabel("Phương thức : ");
		lblPhuongThucThanhToan.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		pnlLR1.add(lblPhuongThucThanhToan, BorderLayout.NORTH);

		cmbPhuongThucThanhToan = new JComboBox<String>();
		cmbPhuongThucThanhToan.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		cmbPhuongThucThanhToan.setForeground(Color.decode("#000000"));
		cmbPhuongThucThanhToan.setBackground(Color.decode("#FFFFFF"));
		cmbPhuongThucThanhToan.setBorder(new MatteBorder(1, 1, 1, 1, Color.decode("#B16E5C")));
		cmbPhuongThucThanhToan.addItem("Chuyển khoản");
		cmbPhuongThucThanhToan.addItem("Tiền mặt");

		cmbPhuongThucThanhToan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String phuongThuc = (String) cmbPhuongThucThanhToan.getSelectedItem();
				if (phuongThuc.equals("Chuyển khoản")) {
					txtTienKhachDua.setEnabled(false);
					txtTienKhachDua.setValue(0);
					txtTienThoi.setText("0");

				} else if (phuongThuc.equals("Tiền mặt")) {
					txtTienKhachDua.setEnabled(true);
				}
			}
		});

		pnlLR1.add(cmbPhuongThucThanhToan);

		//DÒNG 2 PHƯƠNG THỨC THANH TOÁN ----------------------------
		JPanel pnlL4 = new JPanel();
		pnlNoteRight.add(pnlL4);
		pnlL4.setLayout(new BorderLayout(0, 0));

		lblTongTienSP = new JLabel("Tổng tiền sản phẩm (?) : ");
		lblTongTienSP.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		pnlL4.add(lblTongTienSP, BorderLayout.NORTH);

		txtTongTienSP = new JTextField();
		txtTongTienSP.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTongTienSP.setBackground(Color.decode("#f7f7f7"));
		txtTongTienSP.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		txtTongTienSP.setEditable(false);
		pnlL4.add(txtTongTienSP);
		txtTongTienSP.setColumns(10);

		JPanel pnlL2 = new JPanel();
		pnlNoteRight.add(pnlL2);
		pnlL2.setLayout(new BorderLayout(0, 0));

		JLabel lblTienChietKhau = new JLabel("Tiền chiết khẩu (-)");
		lblTienChietKhau.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		pnlL2.add(lblTienChietKhau, BorderLayout.NORTH);

		txtTienChietKhau = new JTextField();
		txtTienChietKhau.setForeground(Color.decode("#9b5353"));
		txtTienChietKhau.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTienChietKhau.setBackground(Color.decode("#f7f7f7"));
		txtTienChietKhau.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		txtTienChietKhau.setEditable(false);
		txtTienChietKhau.setColumns(10);
		pnlL2.add(txtTienChietKhau);

		JPanel pnlL3 = new JPanel();
		pnlNoteRight.add(pnlL3);
		pnlL3.setLayout(new BorderLayout(0, 0));

		JLabel lblTongTienThanhToan = new JLabel("TỔNG TIỀN THANH TOÁN :");
		lblTongTienThanhToan.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		pnlL3.add(lblTongTienThanhToan, BorderLayout.NORTH);

		txtTongTienThanhToan = new JTextField();
		txtTongTienThanhToan.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTongTienThanhToan.setBackground(Color.decode("#f7f7f7"));
		txtTongTienThanhToan.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		txtTongTienThanhToan.setEditable(false);
		txtTongTienThanhToan.setColumns(10);
		pnlL3.add(txtTongTienThanhToan);

		JPanel pnlL5 = new JPanel();
		pnlNoteRight.add(pnlL5);
		pnlL5.setLayout(new GridLayout(1, 0, 5, 0));

		JPanel pnlL5L1 = new JPanel();
		pnlL5.add(pnlL5L1);
		pnlL5L1.setLayout(new BorderLayout(0, 0));

		JLabel lblTienKhachDua = new JLabel("Tiền đưa : ");
		lblTienKhachDua.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		pnlL5L1.add(lblTienKhachDua, BorderLayout.NORTH);

		SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
		txtTienKhachDua = new JSpinner(spinnerModel);
		txtTienKhachDua.setEnabled(false);
		txtTienKhachDua.setBackground(Color.decode("#f7f7f7"));
		txtTienKhachDua.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		pnlL5L1.add(txtTienKhachDua);

		JPanel pnlL5L2 = new JPanel();
		pnlL5.add(pnlL5L2);
		pnlL5L2.setLayout(new BorderLayout(0, 0));

		JLabel lblTienThua = new JLabel("Tiền thối :");
		lblTienThua.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		pnlL5L2.add(lblTienThua, BorderLayout.NORTH);

		txtTienThoi = new JTextField();
		txtTienThoi.setEditable(false);
		txtTienThoi.setBackground(Color.decode("#f7f7f7"));
		txtTienThoi.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTienThoi.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		pnlL5L2.add(txtTienThoi);
		txtTienThoi.setColumns(10);

		JPanel pnlActionOrderSP = new JPanel();
		pnlContainerSPOrder.add(pnlActionOrderSP, BorderLayout.SOUTH);
		pnlActionOrderSP.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel pnlActionLeft = new JPanel();
		pnlActionLeft.setBorder(new EmptyBorder(0, 0, 0, 5));
		pnlActionOrderSP.add(pnlActionLeft);
		pnlActionLeft.setLayout(new GridLayout(0, 2, 5, 0));

		// BTN HUỶ TÍCH ĐIỂM
		btnHuy = new RoundedButton("Huỷ T.Điểm", null, 10, 0, 3f);
		btnHuy.setBackground(Color.decode("#ffffff"));
		btnHuy.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		pnlActionLeft.add(btnHuy);

		// BTN XOA RỖNG
		btnXoa = new RoundedButton("Xoá Rỗng", null, 10, 0, 3f);
		btnXoa.setBackground(Color.decode("#ffffff"));
		btnXoa.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		pnlActionLeft.add(btnXoa);

		JPanel pnlActionRight = new JPanel();
		pnlActionRight.setBorder(new EmptyBorder(0, 5, 0, 0));
		pnlActionOrderSP.add(pnlActionRight);
		pnlActionRight.setLayout(new BorderLayout(0, 0));

		// BTN THANH TOÁN
		btnThanhToan = new RoundedButton("TIẾP TỤC", null, 10, 0, 3f);
		btnThanhToan.setBackground(Color.decode("#af2b2b"));
		btnThanhToan.setForeground(Color.decode("#ffffff"));
		btnThanhToan.setFont(new Font("Segoe UI Semibold", Font.BOLD, 21));
		pnlActionRight.add(btnThanhToan);


		btnXoa.addActionListener(this);
		btnTimKH.addActionListener(this);
		btnHuy.addActionListener(this);
		btnThanhToan.addActionListener(this);

		xoaRong();

	}

	public void RenderSanPham(String loaiSP) {
		pnlRenderSP.removeAll();
		pnlRenderSP.revalidate();
		pnlRenderSP.repaint();

		for (SanPham product : sanPhamDS) {
			if ("Tất Cả".equals(loaiSP) || product.getLoaiSP().equals(loaiSP)) {
				final SanPham finalProduct = product;

				RoundedPanel pnlThongTinSP = new RoundedPanel(null, 10, 0);
				pnlThongTinSP.setLayout(new BorderLayout(0, 0));
				pnlThongTinSP.setBorder(new EmptyBorder(10, 10, 10, 10));
				pnlThongTinSP.setForeground(Color.decode("#000000"));
				pnlThongTinSP.setBackground(Color.decode("#ffffff"));
				pnlThongTinSP.setPreferredSize(new Dimension(100, 220));

				JPanel pnlImageTTSP = new JPanel();
				pnlThongTinSP.add(pnlImageTTSP);
				pnlImageTTSP.setLayout(null);

				// LBL ICON NGỪNG BÁN
				JLabel lblImageSPNgungBan = new JLabel();
				lblImageSPNgungBan.setIcon(new ImageScaler("/icon/icon_ngungban.png", 110, 30).getScaledImageIcon());
				lblImageSPNgungBan.setBounds(24, 110, 110, 30);

				JPanel pnlBottomTTSP = new JPanel();
				pnlBottomTTSP.setLayout(new BorderLayout());
				pnlBottomTTSP.setBackground(Color.decode("#ffffff"));

				// CHECK TRẠNG THÁI NGỪNG HAY BÁN
				if (finalProduct.isTrangThai()) {

				} else {
					pnlThongTinSP.setBackground(Color.decode("#ede1de"));
					pnlBottomTTSP.setBackground(Color.decode("#ede1de"));
					pnlImageTTSP.add(lblImageSPNgungBan);
				}

				// THÊM ẢNH SẢN PHẨM
				JLabel lblImageTTSP = new JLabel();
				lblImageTTSP.setIcon(
						new ImageScaler("/image_SanPham/" + finalProduct.getAnhSP(), 160, 160).getScaledImageIcon());
				lblImageTTSP.setBounds(0, 0, 250, 160);
				pnlImageTTSP.add(lblImageTTSP);

				pnlThongTinSP.add(pnlBottomTTSP, BorderLayout.SOUTH);

				JLabel lblTTTenSP = new JLabel("<html><i>" + finalProduct.getTenSP() + "</i></html>");
				lblTTTenSP.setHorizontalAlignment(SwingConstants.CENTER);
				lblTTTenSP.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
				pnlBottomTTSP.add(lblTTTenSP, BorderLayout.CENTER);

				JLabel lblTTDonGia = new JLabel(String.valueOf(decimalFormat.format(finalProduct.getDonGia())));
				lblTTDonGia.setHorizontalAlignment(SwingConstants.CENTER);
				lblTTDonGia.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
				pnlBottomTTSP.add(lblTTDonGia, BorderLayout.SOUTH);

				pnlThongTinSP.addMouseListener(new MouseListener() {
					@Override
					public void mouseClicked(MouseEvent e) {

						if (!finalProduct.isTrangThai()) {
							return;
						}

						String maSP = finalProduct.getMaSP();
						boolean existed = false;

						for (int i = 0; i < dtbModelODSP.getRowCount(); i++) {
							String rowMaSP = (String) dtbModelODSP.getValueAt(i, 1);
							if (maSP.equals(rowMaSP)) {

								String sl = String.valueOf(dtbModelODSP.getValueAt(i, 4));
								String thanhTienF = String.valueOf(finalProduct.getDonGia());

								Integer soLuong = convertToInteger(sl);

								if (soLuong != null) {
									soLuong++;
									dtbModelODSP.setValueAt(String.valueOf(soLuong), i, 4);
								}

								Double thanhTien = convertToDouble(thanhTienF);
								if (thanhTien != null && soLuong != null) {
									double newTotal = soLuong * thanhTien;

									DecimalFormat decimalFormat = new DecimalFormat("#,##0");

									dtbModelODSP.setValueAt(String.valueOf(decimalFormat.format(newTotal)), i, 5);
								}

								existed = true;

								tinhToanGiaTri();
								break;
							}
						}

						if (!existed) {
							String[] row = new String[6];
							row[0] = String.valueOf(dtbModelODSP.getRowCount() + 1);
							row[1] = finalProduct.getMaSP();
							row[2] = finalProduct.getTenSP();
							DecimalFormat decimalFormat = new DecimalFormat("#,##0");
							row[3] = String.valueOf(decimalFormat.format(finalProduct.getDonGia()));
							row[4] = "1";
							row[5] = String
									.valueOf(decimalFormat.format(finalProduct.getDonGia() * Integer.parseInt(row[4])));
							dtbModelODSP.addRow(row);

							tinhToanGiaTri();
						}

					}

					@Override
					public void mousePressed(MouseEvent e) {
					}

					@Override
					public void mouseReleased(MouseEvent e) {
					}

					@Override
					public void mouseEntered(MouseEvent e) {

						if (finalProduct.isTrangThai()) {
							pnlThongTinSP.setBackground(Color.decode("#f4f4f4"));
							pnlBottomTTSP.setBackground(Color.decode("#f4f4f4"));

						} else {

						}
					}

					@Override
					public void mouseExited(MouseEvent e) {
						if (finalProduct.isTrangThai()) {
							pnlThongTinSP.setBackground(Color.decode("#ffffff"));
							pnlBottomTTSP.setBackground(Color.decode("#ffffff"));
						} else {

						}
					}
				});

				pnlRenderSP.add(pnlThongTinSP);

			}
		}
	}

	public class CustomTableModel extends DefaultTableModel {
		private boolean[] editableColumns;

		// CHỈ CHO PHÉP SỬA ĐƯỢC Ở CỘT SỐ LƯỢNG
		public CustomTableModel(Object[] columns, int rowCount) {
			super(columns, rowCount);
			editableColumns = new boolean[columns.length];
			editableColumns[4] = true;
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return editableColumns[column];
		}

		// hàm nhập số tự tính và nhập 0 xoá hàng
		@Override
		public void setValueAt(Object value, int row, int column) {
			if (column == 4) {
				double soLuong = convertToInteger(value.toString());
				if (soLuong == 0) {
					removeRow(row);
					updateRowNumbers();
					tinhToanGiaTri();
					return;
				} else {
					double donGia = 0;
					try {
						donGia = decimalFormat.parse(getValueAt(row, 3).toString()).doubleValue();
					} catch (ParseException e) {
						e.printStackTrace();
					}
					double thanhTien = soLuong * donGia;
					super.setValueAt(decimalFormat.format(thanhTien), row, 5);
					tinhToanGiaTri();
				}

			}
			super.setValueAt(value, row, column);
		}

		// HÀM TỰ ĐỘNG UPDATE STT
		public void updateRowNumbers() {
			for (int i = 0; i < getRowCount(); i++) {
				setValueAt(i + 1, i, 0);
			}
		}

	}

	// HÀM TÍNH TOÁN CÁC GIÁ TRỊ
	public void tinhToanGiaTri() {

		// lay maHD tu dong
		lblMaHD.setText(new SinhMaTuDong_UTIL().sinhMaHD());

		double TongThanhTien = 0;
		// tinh tong thanh tien san pham
		for (int i = 0; i < dtbModelODSP.getRowCount(); i++) {
			Object getThanhTien = dtbModelODSP.getValueAt(i, 5);
			try {
				double thanhTien = decimalFormat.parse(getThanhTien.toString()).doubleValue();
				TongThanhTien = TongThanhTien + thanhTien;
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}
		txtTongTienSP.setText(decimalFormat.format(TongThanhTien));

		// TÍNH SỐ CÁC SẢN PHẨM TRONG BẢNG
		lblTongTienSP.setText("Tổng tiền sản phẩm (" + dtbModelODSP.getRowCount() + ") : ");

		// TÍNH SỐ TIỀN TỪ CHIẾT KHẨU----------------------------
		String ckhau = txtChietKhau.getText();
		double tienCK = 0;
		if (ckhau.equalsIgnoreCase("") || ckhau.equalsIgnoreCase("0%")) {
			tienCK = 0;
		} else if (ckhau.equalsIgnoreCase("5%")) {
			tienCK = TongThanhTien * 0.05;
		} else if (ckhau.equalsIgnoreCase("10%")) {
			tienCK = TongThanhTien * 0.1;
		} else if (ckhau.equalsIgnoreCase("15%")) {
			tienCK = TongThanhTien * 0.15;
		}
		txtTienChietKhau.setText(String.valueOf("-" + decimalFormat.format(tienCK)));

		// TÍNH TỔNG SỐ TIỀN THANH TOÁN
		double tongTien = roundToNearestThousand(TongThanhTien - tienCK);

		txtTongTienThanhToan.setText(decimalFormat.format(tongTien));

		JFormattedTextField txtField = ((JSpinner.DefaultEditor) txtTienKhachDua.getEditor()).getTextField();
		txtField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				updateTienThoi();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateTienThoi();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateTienThoi();
			}

			private void updateTienThoi() {
				try {
					double tienKhachDua = ((Number) txtTienKhachDua.getValue()).doubleValue();
					String stTongTien = txtTongTienThanhToan.getText();
					double fmTongTien = decimalFormat.parse(stTongTien).doubleValue();
					double tienThoi = tienKhachDua - fmTongTien;
					txtTienThoi.setText(decimalFormat.format(tienThoi));
				} catch (ParseException ex) {
					ex.printStackTrace();
				}
			}
		});

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

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o == btnXoa) {
			xoaRong();
		}

		if (o == btnTimKH) {
			if (!txtSdtKH.getText().matches("^0[0-9]{9}$")) {
				alertNotification("Nhập đúng định dạng số điện thoại");
			} else {
				kiemTraTichDiemKH();
			}
		}

		if (o == btnHuy) {
			txtSdtKH.setText("");
			txtTenKH.setText("");
			txtDiemTL.setText("");
			txtChietKhau.setText("");
			tinhToanGiaTri();
		}

		if (o == btnThanhToan) {
			if (dtbModelODSP.getRowCount() == 0) {
				alertNotification("CẦN CÓ ÍT NHẤT 1 SẢN PHẨM ĐỂ THANH TOÁN !");
			} else {

				String tienThoiFor = null;
				try {
					tienThoiFor = String.valueOf(decimalFormat.parse(txtTienThoi.getText()));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				double tienThoiDb = Double.parseDouble(tienThoiFor);

				if (tienThoiDb < 0) {
					alertNotification("TIỀN KHÁCH PHẢI ĐƯA PHẢI LỚN HƠN HOẶC BẰNG SÓ TIỀN CẦN THANH TOÁN !!!");
				} else {
					String tenkh = txtTenKH.getText();
					String diemTl = txtDiemTL.getText();
					if (tenkh.equalsIgnoreCase("") && diemTl.contentEquals("")) {
						int result = JOptionPane.showConfirmDialog(new JFrame(),
								"HOÁ ĐƠN KHÔNG CÓ TÀI KHOẢN TÍCH ĐIỂM, " + "TIẾP TỤC ?", "", JOptionPane.YES_NO_OPTION);
						if (result == JOptionPane.YES_OPTION) {
							thanhToanHoaDon();
						}
					} else {
						thanhToanHoaDon();
					}
				}

			}
		}

	}

	// DIALOG THEM KH TÍCH ĐIỂM ---------------------------------------------------------------------

	public void showDialogTimKH() {
		JDialog dlTimKH = new JDialog(new JFrame(), "THÊM KHÁCH HÀNG TÍCH ĐIỂM",
				JDialog.ModalityType.APPLICATION_MODAL);
		dlTimKH.setSize(500, 300);
		dlTimKH.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dlTimKH.setLocationRelativeTo(null);
		dlTimKH.setLayout(new BorderLayout());

		JPanel pnlTimKHTitle = new JPanel();
		pnlTimKHTitle.setBorder(new EmptyBorder(5, 0, 20, 0));
		JLabel lblTitleTKH = new JLabel("THÊM KHÁCH HÀNG MỚI");
		lblTitleTKH.setFont(new Font("Segoe UI Semibold", Font.BOLD, 25));

		pnlTimKHTitle.add(lblTitleTKH);

		JPanel pnlTimKHContent = new JPanel();
		pnlTimKHContent.setLayout(new BorderLayout());

		dlTimKH.add(pnlTimKHTitle, BorderLayout.NORTH);

		dlTimKH.add(pnlTimKHContent, BorderLayout.CENTER);

		JPanel pnlTimKHLeft = new JPanel();
		pnlTimKHLeft.setLayout(new FlowLayout());

		Box vtb = Box.createVerticalBox();
		pnlTimKHLeft.add(vtb);

		Box htbLine1 = Box.createHorizontalBox();
		JLabel lblSDTnew = new JLabel("SỐ ĐIỆN THOẠI : ");
		lblSDTnew.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		htbLine1.add(lblSDTnew);
		txtSDTNew = new JTextField(15);
		txtSDTNew.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		htbLine1.add(txtSDTNew);

		txtSDTNew.setText(txtSdtKH.getText());

		vtb.add(htbLine1);

		Component verticalStrut = Box.createVerticalStrut(10);
		vtb.add(verticalStrut);

		Box htbLine2 = Box.createHorizontalBox();
		JLabel lblTenKHnew = new JLabel("TÊN KHÁCH HÀNG : ");
		lblTenKHnew.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		htbLine2.add(lblTenKHnew);
		txtTenKHNew = new JTextField(15);
		txtTenKHNew.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		htbLine2.add(txtTenKHNew);
		vtb.add(htbLine2);

		Component verticalStrut2 = Box.createVerticalStrut(10);
		vtb.add(verticalStrut2);

		Box htbLine3 = Box.createHorizontalBox();
		JLabel lblNgaySinhKHnew = new JLabel("NGÀY SINH : ");
		lblNgaySinhKHnew.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		htbLine3.add(lblNgaySinhKHnew);

		JPanel pnlDPNSnew = new JPanel();
		pnlDPNSnew.setLayout(new BorderLayout());

		dpNgaySinhKHnew = new JXDatePicker();
		dpNgaySinhKHnew.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		dpNgaySinhKHnew.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		pnlDPNSnew.add(dpNgaySinhKHnew);
		htbLine3.add(pnlDPNSnew);
		vtb.add(htbLine3);

		lblNgaySinhKHnew.setPreferredSize(lblTenKHnew.getPreferredSize());
		lblSDTnew.setPreferredSize(lblTenKHnew.getPreferredSize());

		txtSDTNew.setPreferredSize(new Dimension(txtSDTNew.getPreferredSize().width, 30));
		txtTenKHNew.setPreferredSize(new Dimension(txtTenKHNew.getPreferredSize().width, 30));
		dpNgaySinhKHnew.setPreferredSize(new Dimension(dpNgaySinhKHnew.getPreferredSize().width, 30));

		pnlTimKHContent.add(pnlTimKHLeft, BorderLayout.CENTER);

		JPanel pnlActionTKH = new JPanel();
		pnlActionTKH.setBorder(new EmptyBorder(10, 0, 15, 0));

		btnXoaRong = new RoundedButton("Xoá Rỗng", null, 10, 0, 3f);
		btnXoaRong.setBackground(Color.decode("#ffffff"));
		btnXoaRong.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		pnlActionTKH.add(btnXoaRong);

		Component horizontalStrut = Box.createHorizontalStrut(30);
		pnlActionTKH.add(horizontalStrut);

		btnDangKy = new RoundedButton("ĐĂNG KÝ", null, 10, 0, 3f);
		btnDangKy.setBackground(Color.decode("#af2b2b"));
		btnDangKy.setForeground(Color.decode("#ffffff"));
		btnDangKy.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));

		Calendar defaultDate = Calendar.getInstance();
		defaultDate.set(2000, Calendar.JANUARY, 1);
		dpNgaySinhKHnew.setDate(defaultDate.getTime());

		btnDangKy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (txtTenKHNew.getText().matches("^[a-zA-Z]+$")) {
					String sdtnew = txtSDTNew.getText();
					String tenKHnew = txtTenKHNew.getText();
					java.sql.Date ngSinhnew =  new java.sql.Date(dpNgaySinhKHnew.getDate().getTime());
					KhachHang khnew = new KhachHang(sdtnew, tenKHnew, ngSinhnew, 0);

					if (khachHang_dao.dangKySdtTichDiem(khnew)) {
						alertNotification("ĐĂNG KÝ THÀNH VIÊN THÀNH CÔNG !!!");
						dlTimKH.dispose();

						txtSdtKH.setText(String.valueOf(sdtnew));
						txtTenKH.setText(tenKHnew);
						txtDiemTL.setText(String.valueOf(0));
						txtChietKhau.setText(tinhToanChietKhau(0));

						tinhToanGiaTri();

					} else {
						alertNotification("lỗi đăng ký tích điểm (sdt đã được đăng ký)");
					}

				} else {
					alertNotification("Nhập Tên không đúng định dạng!!!");
				}

			}
		});

		pnlActionTKH.add(btnDangKy);

		pnlTimKHContent.add(pnlActionTKH, BorderLayout.SOUTH);

		dlTimKH.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				txtTenKHNew.requestFocus();
			}

		});
		dlTimKH.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int choice = JOptionPane.showConfirmDialog(dlTimKH, "THOÁT SẼ XOÁ MỌI THÔNG TIN ĐÃ NHẬP ?",
						"HUỶ ĐĂNG KÝ TÍCH ĐIỂM", JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					dlTimKH.dispose();
					txtSdtKH.setText("");
					txtTenKH.setText("");
					txtDiemTL.setText("");
					txtChietKhau.setText("");
				}
			}
		});

		dlTimKH.setVisible(true);
	}

	// KIỂM TRA KHÁCH HÀNG QUA SỐ ĐIỆN THOẠI
	public void kiemTraTichDiemKH() {
		khachHangDS = khachHang_dao.timSoDienThoaiKhachHang(txtSdtKH.getText().trim());

		if (khachHangDS.size() != 0) {

			for (int i = 0; i < khachHangDS.size(); i++) {
				txtSdtKH.setText(String.valueOf(khachHangDS.get(i).getSoDienThoai()));
				txtTenKH.setText(khachHangDS.get(i).getTenKH());
				txtDiemTL.setText(String.valueOf(khachHangDS.get(i).getTongDiemTichLuy()));
				txtChietKhau.setText(tinhToanChietKhau(khachHangDS.get(i).getTongDiemTichLuy()));
				tinhToanGiaTri();
			}

		} else {
			int result = JOptionPane.showConfirmDialog(new JFrame(),
					"SỐ ĐIỆN THOẠI CHƯA ĐƯỢC ĐĂNG KÝ TÍCH ĐIỂM, " + "TIẾP TỤC ĐĂNG KÝ?",
					"ĐĂNG KÝ SỐ ĐIỆN THOẠI TÍCH ĐIỂM", JOptionPane.YES_NO_OPTION);

			if (result == JOptionPane.YES_OPTION) {
				showDialogTimKH();
			} else if (result == JOptionPane.NO_OPTION) {
				txtSdtKH.setText("");
				txtTenKH.setText("");
				txtDiemTL.setText("");
				txtChietKhau.setText("");
			}
		}
	}

	// HÀM TÍCH TOÁN CHIẾT KHẨU DỰA TRÊN SỐ ĐIỂM-----------------------------
	public String tinhToanChietKhau(int tongDiemTL) {
		if (tongDiemTL >= 0 && tongDiemTL < 300) {
			return "0%";
		} else if (tongDiemTL >= 300 && tongDiemTL < 600) {
			return "5%";
		} else if (tongDiemTL >= 600 && tongDiemTL < 900) {
			return "10%";
		} else if (tongDiemTL >= 900) {
			return "15%";
		}
		return "?";
	}

	// thanh toan hoa don
	public void thanhToanHoaDon() {
		String phuongThuc = (String) cmbPhuongThucThanhToan.getSelectedItem();
		if (phuongThuc.equals("Chuyển khoản")) {
			xacNhanThanhToan();
		} else if (phuongThuc.equals("Tiền mặt")) {
			if (txtTienKhachDua.getValue() == null) {
				alertNotification("CẦN NHẬP TIỀN KHÁCH ĐƯA");
			} else {
				xacNhanThanhToan();
			}
		}
	}

	// dialog xac nhan thanh toan
	public void xacNhanThanhToan() {
		JDialog dlQR = new JDialog(new JFrame(), "THANH TOÁN CHUYỂN KHOẢN", JDialog.ModalityType.APPLICATION_MODAL);
		dlQR.setSize(1200, 640);
		dlQR.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dlQR.setLocationRelativeTo(null);
		dlQR.setLayout(new BorderLayout());

		JPanel pnlQtitle = new JPanel();
		pnlQtitle.setBackground(new Color(255, 255, 255));
		dlQR.add(pnlQtitle, BorderLayout.NORTH);

		JLabel lblTtitleQuet = new JLabel("QUÉT MÃ ĐỂ THANH TOÁN");
		lblTtitleQuet.setFont(new Font("Segoe UI Semibold", Font.BOLD, 25));
		pnlQtitle.add(lblTtitleQuet);

		JPanel pnlQuet = new JPanel();
		pnlQuet.setBackground(new Color(255, 255, 255));
		pnlQuet.setBorder(new EmptyBorder(10, 10, 20, 40));
		dlQR.add(pnlQuet, BorderLayout.CENTER);
		pnlQuet.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel pnlInQR = new JPanel();
		pnlInQR.setBackground(new Color(255, 255, 255));
		pnlQuet.add(pnlInQR);
		pnlInQR.setLayout(new BorderLayout(0, 0));

		JPanel pnlShowQR = new JPanel();
		pnlShowQR.setBackground(new Color(255, 255, 255));
		pnlInQR.add(pnlShowQR, BorderLayout.SOUTH);
		pnlShowQR.setLayout(new BorderLayout(0, 0));

		JLabel lbltaikhoan = new JLabel("109999999 - VTPCoffee");
		lbltaikhoan.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
		lbltaikhoan.setHorizontalAlignment(SwingConstants.CENTER);
		pnlShowQR.add(lbltaikhoan, BorderLayout.NORTH);

		JPanel pnlImageQR = new JPanel();
		pnlImageQR.setBackground(new Color(255, 255, 255));
		pnlInQR.add(pnlImageQR, BorderLayout.NORTH);

		JLabel lblMaQR = new JLabel();
		lblMaQR.setFont(new Font("Segoe UI Semibold", Font.BOLD, 60));
		String phuongThuc = (String) cmbPhuongThucThanhToan.getSelectedItem();
		if (phuongThuc.equals("Chuyển khoản")) {
			lblMaQR.setIcon(new ImageScaler("/image/QR_thanhToan.png", 430, 420).getScaledImageIcon());
		} else if (phuongThuc.equals("Tiền mặt")) {
			lblMaQR.setText("Tiền mặt");
		}

		pnlImageQR.add(lblMaQR);

		JPanel pnlLeftQR = new JPanel();
		pnlLeftQR.setBackground(new Color(255, 255, 255));
		pnlQuet.add(pnlLeftQR);
		pnlLeftQR.setLayout(new BorderLayout(0, 0));

		JLabel lblChiTiet = new JLabel("CHI TIẾT HOÁ ĐƠN");
		lblChiTiet.setBackground(new Color(255, 255, 255));
		lblChiTiet.setHorizontalAlignment(SwingConstants.CENTER);
		lblChiTiet.setFont(new Font("Segoe UI Semibold", Font.BOLD, 25));
		lblChiTiet.setBorder(new EmptyBorder(0, 0, 20, 0));
		pnlLeftQR.add(lblChiTiet, BorderLayout.NORTH);

		JPanel pnBodyCTHD = new JPanel();
		pnlLeftQR.add(pnBodyCTHD, BorderLayout.CENTER);
		pnBodyCTHD.setLayout(new BorderLayout(0, 0));

		JPanel pnlThongTinCTHD = new JPanel();
		pnlThongTinCTHD.setBackground(new Color(255, 255, 255));
		pnBodyCTHD.add(pnlThongTinCTHD, BorderLayout.NORTH);
		pnlThongTinCTHD.setLayout(new BoxLayout(pnlThongTinCTHD, BoxLayout.Y_AXIS));

		JPanel pnlLine1CTHD = new JPanel();
		pnlLine1CTHD.setBackground(new Color(255, 255, 255));
		pnlThongTinCTHD.add(pnlLine1CTHD);
		pnlLine1CTHD.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel pnlNL = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) pnlNL.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		pnlNL.setBackground(new Color(255, 255, 255));
		pnlLine1CTHD.add(pnlNL);

		JLabel lblNgayLapHD = new JLabel("NGÀY LẬP : ");
		lblNgayLapHD.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		pnlNL.add(lblNgayLapHD);

		lblNgayLapHDOutput = new JLabel();
		pnlNL.add(lblNgayLapHDOutput);

		JPanel pnlNVLap = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) pnlNVLap.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		pnlNVLap.setBackground(new Color(255, 255, 255));
		pnlLine1CTHD.add(pnlNVLap);

		JLabel lblNhanVienLap = new JLabel("NHÂN VIÊN : ");
		lblNhanVienLap.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		pnlNVLap.add(lblNhanVienLap);

		lblNhanVienLapOutput = new JLabel("maNV");
		pnlNVLap.add(lblNhanVienLapOutput);

		JPanel pnlLine2CTHD = new JPanel();
		pnlLine2CTHD.setBackground(new Color(255, 255, 255));
		pnlThongTinCTHD.add(pnlLine2CTHD);
		pnlLine2CTHD.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel pnlKH = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) pnlKH.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		pnlKH.setBackground(new Color(255, 255, 255));
		pnlLine2CTHD.add(pnlKH);

		JLabel lblKHLap = new JLabel("KHÁCH HÀNG : ");
		lblKHLap.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		pnlKH.add(lblKHLap);

		lblKHLapOutPut = new JLabel("");
		pnlKH.add(lblKHLapOutPut);

		JPanel pnlGChu = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) pnlGChu.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		pnlGChu.setBackground(new Color(255, 255, 255));
		pnlLine2CTHD.add(pnlGChu);

		JLabel lblGChu = new JLabel("GHI CHÚ : ");
		lblGChu.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		pnlGChu.add(lblGChu);

		lblGChuOutPut = new JLabel("ghichu");
		pnlGChu.add(lblGChuOutPut);

		JPanel pnlChiTiet = new JPanel();
		pnBodyCTHD.add(pnlChiTiet, BorderLayout.CENTER);
		pnlChiTiet.setBorder(new MatteBorder(1, 0, 0, 0, getBackground()));
		pnlChiTiet.setLayout(new GridLayout(0, 3, 0, 0));

		JPanel pnl_11 = new JPanel();
		pnl_11.setBackground(new Color(255, 255, 255));
		pnl_11.setPreferredSize(new Dimension(120, 120));
		pnlChiTiet.add(pnl_11);
		pnl_11.setLayout(new BoxLayout(pnl_11, BoxLayout.Y_AXIS));

		JLabel lblTenSP = new JLabel("TÊN SẢN PHẨM");
		lblTenSP.setBorder(new EmptyBorder(5, 0, 15, 0));
		lblTenSP.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		lblTenSP.setPreferredSize(new Dimension(70, 40));
		pnl_11.add(lblTenSP);

		JPanel pnl_22 = new JPanel();
		pnl_22.setBackground(new Color(255, 255, 255));
		pnl_22.setPreferredSize(new Dimension(50, 50));
		pnlChiTiet.add(pnl_22);
		pnl_22.setLayout(new BoxLayout(pnl_22, BoxLayout.Y_AXIS));

		JLabel lblSoLuong = new JLabel("<html><div style='text-align: right'>" + "SỐ LƯỢNG" + "</div></html>");
		lblSoLuong.setBorder(new EmptyBorder(5, 0, 15, 0));
		lblSoLuong.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoLuong.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		pnl_22.add(lblSoLuong);

		JPanel pnl_33 = new JPanel();
		pnl_33.setBackground(new Color(255, 255, 255));
		pnlChiTiet.add(pnl_33);
		pnl_33.setLayout(new BoxLayout(pnl_33, BoxLayout.Y_AXIS));

		JLabel lblThanhTien = new JLabel("<html><div style='text-align: right'>" + "THÀNH TIỀN" + "</div></html>");
		lblThanhTien.setBorder(new EmptyBorder(5, 0, 15, 0));
		lblThanhTien.setHorizontalAlignment(SwingConstants.CENTER);
		lblThanhTien.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		lblThanhTien.setPreferredSize(pnl_33.getPreferredSize());
		pnl_33.add(lblThanhTien);

		JPanel pnlAction = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnlAction.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setHgap(15);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		pnlAction.setBackground(Color.decode("#d3d3d3"));
		dlQR.add(pnlAction, BorderLayout.SOUTH);

		btnHuyGD = new RoundedButton("HUỶ GIAO DỊCH", null, 10, 0, 0.3f);
		btnHuyGD.setBackground(Color.decode("#ffffff"));
		btnHuyGD.setForeground(Color.decode("#d62f2f"));
		btnHuyGD.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		pnlAction.add(btnHuyGD);

		btnHuyGD.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dlQR.dispose();
			}
		});

		Component horizontalStrut = Box.createHorizontalStrut(560);
		pnlAction.add(horizontalStrut);

		cbInHoaDon = new JCheckBox("In Hoá Đơn");
		cbInHoaDon.setFont(new Font("Segoe UI Semibold", Font.BOLD, 19));
		cbInHoaDon.setSelected(true);
		pnlAction.add(cbInHoaDon);

		Component horizontalStrut1 = Box.createHorizontalStrut(10);
		pnlAction.add(horizontalStrut1);

		btnXacNhanTT = new RoundedButton("XÁC NHẬN ĐÃ THANH TOÁN", null, 10, 0, 0.3f);
		btnXacNhanTT.setBackground(Color.decode("#0065F7"));
		btnXacNhanTT.setForeground(Color.decode("#ffffff"));
		btnXacNhanTT.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		pnlAction.add(btnXacNhanTT);

		List<JLabel> ListTenSP = new ArrayList<>();
		List<JLabel> ListSoLuongSP = new ArrayList<>();
		List<JLabel> ListThanhTienSP = new ArrayList<>();

		for (int i = 0; i < dtbModelODSP.getRowCount(); i++) {
			String tenSP = dtbModelODSP.getValueAt(i, 2).toString();
			JLabel lblTenSPItem = new JLabel("<html><i>" + tenSP + "</i></html>");
			lblTenSPItem.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
			ListTenSP.add(lblTenSPItem);

			String soLuong = dtbModelODSP.getValueAt(i, 4).toString();
			JLabel lblSoLuongItem = new JLabel(
					"<html><div style='width:105px;text-align: right'>" + "x" + soLuong + "</div></html>");
			lblSoLuongItem.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
			ListSoLuongSP.add(lblSoLuongItem);

			String thanhTien = dtbModelODSP.getValueAt(i, 5).toString();
			JLabel lblThanhTienItem = new JLabel(
					"<html><div style='width:115px;text-align: right'>" + thanhTien + "</div></html>");
			lblThanhTienItem.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
			ListThanhTienSP.add(lblThanhTienItem);

		}

		for (JLabel label : ListTenSP) {
			pnl_11.add(label);
		}

		for (JLabel label : ListSoLuongSP) {
			pnl_22.add(label);
		}

		for (JLabel label : ListThanhTienSP) {
			pnl_33.add(label);
		}

		JLabel lblTongTienThanhToan = new JLabel(
				"<html><div style='text-align: right'>" + "TỔNG TIỀN : " + "</div></html>");
		lblTongTienThanhToan.setFont(new Font("Segoe UI Semibold", Font.BOLD, 19));
		lblTongTienThanhToan.setHorizontalAlignment(SwingConstants.CENTER);
		lblTongTienThanhToan.setBorder(new EmptyBorder(10, 0, 0, 0));
		pnl_22.add(lblTongTienThanhToan);

		JLabel lblNumberTongTienThanhToan = new JLabel(
				"<html><div style='width:115px;text-align: right'>" + txtTongTienThanhToan.getText() + "</div></html>");
		lblNumberTongTienThanhToan.setForeground(Color.decode("#9b5353"));
		lblNumberTongTienThanhToan.setFont(new Font("Segoe UI Semibold", Font.BOLD, 19));
		lblNumberTongTienThanhToan.setBorder(new EmptyBorder(10, 0, 0, 0));
		pnl_33.add(lblNumberTongTienThanhToan);

		JLabel lblDTL = new JLabel("<html><div style='text-align: right'>" + "SDTL : " + "</div></html>");
		lblDTL.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		lblDTL.setHorizontalAlignment(SwingConstants.CENTER);
		lblDTL.setBorder(new EmptyBorder(10, 0, 0, 0));

		JLabel lblNumberSDTL = new JLabel();

		try {
			String stTongTien = txtTongTienThanhToan.getText();
			double fmTongTien = decimalFormat.parse(stTongTien).doubleValue();
			soDiemTichLuy = (int) (fmTongTien * 0.0001);

			lblNumberSDTL
					.setText("<html><div style='width:115px;text-align: right'>" + soDiemTichLuy + "</div></html>");
			lblNumberSDTL.setForeground(Color.decode("#000000"));
			lblNumberSDTL.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
			lblNumberSDTL.setBorder(new EmptyBorder(10, 0, 0, 0));

		} catch (ParseException ex) {
			ex.printStackTrace();
		}

		if (!txtTenKH.getText().equalsIgnoreCase("")) {
			pnl_22.add(lblDTL);
			pnl_33.add(lblNumberSDTL);
		}

		// lay ngay hien tai
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
		String formattedDateTime = now.format(formatter);

		lblNgayLapHDOutput.setText(formattedDateTime);

		// luu thong tin nhan vien dang nhap
		lblNhanVienLapOutput.setText(main.taiKhoan.getNV().getMaNV());

		// lay thong tin khach hang
		lblKHLapOutPut.setText(txtSdtKH.getText() + " - " + txtTenKH.getText());

		// lay ghi chu
		lblGChuOutPut.setText(teaGhiChu.getText());

		btnXacNhanTT.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String maHd = lblMaHD.getText();

				String ngayLap = lblNgayLapHDOutput.getText();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
				LocalDateTime localDateTimeNgayLap = LocalDateTime.parse(ngayLap, formatter);

				String maNV = lblNhanVienLapOutput.getText();
				String sdtKH = txtSdtKH.getText();
				String tenKH = txtTenKH.getText();

				if (tenKH.equalsIgnoreCase("")) {
					sdtKH = "0";
				}

				String stTongTien = txtTongTienThanhToan.getText();
				double fmTongTien;
				try {
					fmTongTien = decimalFormat.parse(stTongTien).doubleValue();
					NhanVien nvNew = new NhanVien(maNV);
					KhachHang khNew = new KhachHang(sdtKH);

					// -------- HoaDon
					HoaDon hdnew = new HoaDon(maHd, localDateTimeNgayLap, nvNew, khNew, fmTongTien);

//					System.out.println(maHd + " - "+ localDateTimeNgayLap + " - "+ nvNew.getMaNV() 
//					+ " - "+ khNew.getSoDienThoai() + " - "+fmTongTien);

					// ------------- ChiTietHoaDon

					if (maHd != null) {
						if (new HoaDon_DAO().themHoaDon(hdnew)) {
							for (int i = 0; i < dtbModelODSP.getRowCount(); i++) {
								String maSPnew = (String) dtbModelODSP.getValueAt(i, 1);
								int soLuongSP = Integer.parseInt(dtbModelODSP.getValueAt(i, 4).toString());
								SanPham spnewCTHD = new SanPham(maSPnew);

								int sdtlCTHD = soDiemTichLuy;
								String ghiChunewCTHD = teaGhiChu.getText();

								HoaDon maHD = new HoaDon(maHd);
								ChiTietHoaDon cthdnew = new ChiTietHoaDon(maHD, spnewCTHD, sdtlCTHD, soLuongSP,
										ghiChunewCTHD);

								chiTietHoaDon = cthdnew;

								new HoaDon_DAO().themChiTietHoaDon(cthdnew);

								int diemTL = 0;

								if (!txtDiemTL.getText().equalsIgnoreCase("")) {
									diemTL = Integer.valueOf(txtDiemTL.getText());
								} else {
									diemTL = 0;
								}

								String sdtKHCong = txtSdtKH.getText();

								if (tenKH.equalsIgnoreCase("")) {
									sdtKHCong = "0";
								}

								new HoaDon_DAO().congDiemVaoKH((diemTL + sdtlCTHD), sdtKHCong);

							}
							;
							alertNotification("TẠO HOÁ ĐƠN THÀNH CÔNG");
							xoaRong();

							dlQR.dispose();

//							try {
//								new XuatPDF_UTIL().xuatHoaDon(chiTietHoaDon, sanPhamDS);
//							} catch (Exception e2) {
//								e2.printStackTrace();
//							}

						} else {
							alertNotification("TẠO HOÁ ĐƠN KHÔNG THÀNH CÔNG!!!");
						}

					} else {
						alertNotification("ma hd trùng");
					}

				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		dlQR.setVisible(true);

	}

	// xuat pdf
	public void xuatPDF() {

	}

//--------------------------------------------------------
	// hàm xoá rỗng
	public void xoaRong() {

		dtbModelODSP.setRowCount(0);
		txtSdtKH.setText("");
		txtTenKH.setText("");
		txtDiemTL.setText("");
		txtChietKhau.setText("");
		teaGhiChu.setText("");
		cmbPhuongThucThanhToan.setSelectedIndex(0);
		txtTongTienSP.setText("0");
		txtTienChietKhau.setText("0");
		txtTongTienThanhToan.setText("0");
		txtTienKhachDua.setValue(0);
		txtTienThoi.setText("0");

		tinhToanGiaTri();

	}

	// quy tron tien
	public static double roundToNearestThousand(double number) {
		double quotient = number / 1000;
		double remainder = number % 1000;

		if (remainder < 500) {
			quotient = Math.floor(quotient);
		} else {
			quotient = Math.ceil(quotient);
		}

		return quotient * 1000;
	}

	private Integer convertToInteger(String value) {
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			alertNotification("Giá trị nhập vào không hợp lệ");
			return null;
		}
	}

	private Double convertToDouble(String value) {
		try {
			return Double.parseDouble(value);

		} catch (NumberFormatException e) {
			alertNotification("Giá trị nhập vào không hợp lệ");
			return null;
		}
	}

	// ALERT

	public void alertNotification(String textError) {
		JOptionPane.showMessageDialog(null, textError);
	}
}
