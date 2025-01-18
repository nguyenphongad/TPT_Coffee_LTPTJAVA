package ui;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JTextField;

import customUI.ImageScaler;
import customUI.RoundedButton;
import dao.SanPham_DAO;
import entity.SanPham;
import util.SinhMaTuDong_UTIL;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Component;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class SanPham_QuanLy_UI extends JPanel implements ActionListener, MouseListener {
	private JTextField txtMaSP;
	private JTextField txtTenSP;
	private JTextField txtDonGia;
	private JTextField txtTImKiemTheoTenSP;
	private RoundedButton btnAnhSanPham;

	private JTable tblSanPham;
	private DefaultTableModel dtbModelSanPham;
	private RoundedButton btnThemLoaiSP;
	private RoundedButton btnTimSP;
	private RoundedButton btnThem;
	private RoundedButton btnSua;
	private RoundedButton btnXoa;
	private RoundedButton btnLuu;
	private RoundedButton btnHuy;
	private JComboBox<String> cmbTimLoaiSP;
	private JComboBox<String> cmbLoaiSP;
	private JComboBox<String> cmbTimTrangThai;
	private JComboBox<String> cmbTrangThaiSP;
	private ArrayList<SanPham> sanPhamDS = new ArrayList<SanPham>();

	DecimalFormat decimalFormat = new DecimalFormat("#,##0");

	private String pathNameSP = "image_sp_default.PNG";

	private int action = 0;
	private JLabel lblImageSP;
	private JTextField txtLSP;

	public SanPham_QuanLy_UI() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new GridLayout(0, 2, 0, 0));

		JPanel pnlLeft = new JPanel();
		pnlLeft.setBorder(new CompoundBorder(new MatteBorder(0, 0, 0, 2, (Color) new Color(192, 192, 192)),
				new EmptyBorder(0, 0, 0, 5)));
		add(pnlLeft);
		pnlLeft.setLayout(new BorderLayout(0, 0));

		JPanel pnlImageSP = new JPanel();
		pnlImageSP.setBorder(new EmptyBorder(40, 0, 0, 0));
		pnlLeft.add(pnlImageSP, BorderLayout.NORTH);
		pnlImageSP.setLayout(new BorderLayout(0, 0));

		lblImageSP = new JLabel("");
		lblImageSP.setHorizontalAlignment(SwingConstants.CENTER);
		lblImageSP.setIcon(new ImageScaler("/image_SanPham/image_sp_default.PNG", 150, 150).getScaledImageIcon());
		pnlImageSP.add(lblImageSP);

		JPanel panel = new JPanel();
		pnlImageSP.add(panel, BorderLayout.SOUTH);

		btnAnhSanPham = new RoundedButton("image_sp_default.PNG", null, 10, 0, 0.5f);
		btnAnhSanPham.setBackground(Color.decode("#ffffff"));
		btnAnhSanPham.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		panel.add(btnAnhSanPham);

		JPanel pnlInput = new JPanel();
		pnlLeft.add(pnlInput);
		pnlInput.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		Box verticalBox = Box.createVerticalBox();

		Box horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);

		JLabel lblMaSP = new JLabel("Mã Sản Phẩm :         ");
		lblMaSP.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		horizontalBox.add(lblMaSP);

		txtMaSP = new JTextField();
		txtMaSP.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode("#B16E5C")),
						BorderFactory.createEmptyBorder(5, 0, 5, 0)));
		txtMaSP.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		txtMaSP.setEditable(false);
		horizontalBox.add(txtMaSP);
		txtMaSP.setColumns(23);
		pnlInput.add(verticalBox);

		Component verticalStrut = Box.createVerticalStrut(15);
		verticalBox.add(verticalStrut);

		Box horizontalBox_1 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_1);

		JLabel lblTenSanPham = new JLabel("Tên Sản Phẩm : ");
		lblTenSanPham.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		horizontalBox_1.add(lblTenSanPham);

		txtTenSP = new JTextField();
		txtTenSP.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode("#B16E5C")),
						BorderFactory.createEmptyBorder(5, 0, 5, 0)));
		txtTenSP.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		horizontalBox_1.add(txtTenSP);
		txtTenSP.setColumns(10);

		Component verticalStrut_1 = Box.createVerticalStrut(15);
		verticalBox.add(verticalStrut_1);

		Box horizontalBox_2 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_2);

		JLabel lblLoaiSP = new JLabel("Loại Sản Phẩm : ");
		lblLoaiSP.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		horizontalBox_2.add(lblLoaiSP);

		cmbLoaiSP = new JComboBox<String>();
		cmbLoaiSP.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		horizontalBox_2.add(cmbLoaiSP);

		Component verticalStrut_2 = Box.createVerticalStrut(15);
		verticalBox.add(verticalStrut_2);

		Box horizontalBox_3 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_3);

		JLabel lblDonGia = new JLabel("Đơn Giá : ");
		lblDonGia.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		horizontalBox_3.add(lblDonGia);

		txtDonGia = new JTextField();
		txtDonGia.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode("#B16E5C")),
						BorderFactory.createEmptyBorder(5, 0, 5, 0)));
		txtDonGia.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		horizontalBox_3.add(txtDonGia);
		txtDonGia.setColumns(10);

		Component verticalStrut_3 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_3);

		Box horizontalBox_4 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_4);

		JLabel lblTrangThai = new JLabel("Trạng Thái : ");
		lblTrangThai.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		horizontalBox_4.add(lblTrangThai);

		lblTenSanPham.setPreferredSize(lblMaSP.getPreferredSize());
		lblLoaiSP.setPreferredSize(lblMaSP.getPreferredSize());

		Component horizontalStrut_1 = Box.createHorizontalStrut(10);
		horizontalBox_2.add(horizontalStrut_1);

		btnThemLoaiSP = new RoundedButton("Thêm Loại", null, 5, 0, 0.5f);
		btnThemLoaiSP.setBackground(Color.decode("#ffffff"));
		horizontalBox_2.add(btnThemLoaiSP);
		lblDonGia.setPreferredSize(lblMaSP.getPreferredSize());
		lblTrangThai.setPreferredSize(lblMaSP.getPreferredSize());

		cmbTrangThaiSP = new JComboBox<String>();
		cmbTrangThaiSP.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		cmbTrangThaiSP.addItem("Đang bán");
		cmbTrangThaiSP.addItem("Ngừng bán");
		horizontalBox_4.add(cmbTrangThaiSP);

		JPanel pnAction = new JPanel();
		pnlLeft.add(pnAction, BorderLayout.SOUTH);

		btnThem = new RoundedButton("Thêm", null, 15, 0, 0.3f);
		btnThem.setBackground(Color.decode("#ffffff"));
		btnThem.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		pnAction.add(btnThem);

		btnSua = new RoundedButton("Sửa", null, 15, 0, 0.3f);
		btnSua.setBackground(Color.decode("#ffffff"));
		btnSua.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		pnAction.add(btnSua);

		btnXoa = new RoundedButton("Xoá", null, 15, 0, 0.3f);
		btnXoa.setBackground(Color.decode("#ffffff"));
		btnXoa.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		pnAction.add(btnXoa);

		Component horizontalStrut = Box.createHorizontalStrut(40);
		pnAction.add(horizontalStrut);

		btnLuu = new RoundedButton("Lưu", null, 15, 0, 0.3f);
		btnLuu.setBackground(Color.decode("#af2b2b"));
		btnLuu.setForeground(Color.decode("#ffffff"));
		btnLuu.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		pnAction.add(btnLuu);

		btnHuy = new RoundedButton("Huỷ", null, 15, 0, 0.3f);
		btnHuy.setBackground(Color.decode("#ffffff"));
		btnHuy.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		pnAction.add(btnHuy);

		JPanel pnlRight = new JPanel();
		pnlRight.setBorder(new EmptyBorder(0, 5, 0, 0));
		add(pnlRight);
		pnlRight.setLayout(new BorderLayout(0, 0));

		JPanel pnlTimKiem = new JPanel();
		pnlRight.add(pnlTimKiem, BorderLayout.NORTH);
		pnlTimKiem.setLayout(new GridLayout(0, 2, 10, 0));

		JPanel pnlTimTenSP = new JPanel();
		pnlTimKiem.add(pnlTimTenSP);
		pnlTimTenSP.setLayout(new BorderLayout(0, 0));

		JLabel lblTimKiemTheoTenSP = new JLabel("Tìm Theo Mã/Tên SP :");
		lblTimKiemTheoTenSP.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		pnlTimTenSP.add(lblTimKiemTheoTenSP, BorderLayout.NORTH);

		txtTImKiemTheoTenSP = new JTextField();
		txtTImKiemTheoTenSP.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		pnlTimTenSP.add(txtTImKiemTheoTenSP, BorderLayout.CENTER);
		txtTImKiemTheoTenSP.setColumns(15);

		btnTimSP = new RoundedButton("Tìm", null, 5, 0, 0.3f);
		pnlTimTenSP.add(btnTimSP, BorderLayout.EAST);

		JPanel pnlChonLoai = new JPanel();
		pnlTimKiem.add(pnlChonLoai);
		pnlChonLoai.setLayout(new GridLayout(1, 0, 5, 0));

		JPanel pnlChonLoaiSP = new JPanel();
		pnlChonLoai.add(pnlChonLoaiSP);
		pnlChonLoaiSP.setLayout(new BorderLayout(0, 0));

		JLabel lblTimLoaiSP = new JLabel("Loại SP ");
		lblTimLoaiSP.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		pnlChonLoaiSP.add(lblTimLoaiSP, BorderLayout.NORTH);

		cmbTimLoaiSP = new JComboBox<String>();
		cmbTimLoaiSP.addItem("Tất cả");
		pnlChonLoaiSP.add(cmbTimLoaiSP, BorderLayout.SOUTH);

		cmbTimLoaiSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String getLoai = (String) cmbTimLoaiSP.getSelectedItem();
				if (getLoai.equals("Tất cả")) {
					timKiemTheoLoai("");
				} else {
					timKiemTheoLoai(getLoai);
				}
			}
		});

		JPanel pnlChonTrangThaiSP = new JPanel();
		pnlChonLoai.add(pnlChonTrangThaiSP);
		pnlChonTrangThaiSP.setLayout(new BorderLayout(0, 0));

		JLabel lblTrangThaiSP = new JLabel("Trạng Thái");
		lblTrangThaiSP.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		pnlChonTrangThaiSP.add(lblTrangThaiSP, BorderLayout.NORTH);

		cmbTimTrangThai = new JComboBox<String>();
		cmbTimTrangThai.addItem("Tất cả");
		cmbTimTrangThai.addItem("Đang bán");
		cmbTimTrangThai.addItem("Ngừng bán");
		pnlChonTrangThaiSP.add(cmbTimTrangThai, BorderLayout.SOUTH);

		cmbTimTrangThai.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String getTT = (String) cmbTimTrangThai.getSelectedItem();
				if (getTT.equals("Tất cả")) {
					timKiemTheoTrangThai(2);
				} else if (getTT.equals("Đang bán")){
					timKiemTheoTrangThai(1);
				}else if (getTT.equals("Ngừng bán")){
					timKiemTheoTrangThai(0);
				}

			}
		});

		JPanel pnlTable = new JPanel();
		pnlTable.setBorder(new EmptyBorder(10, 0, 0, 0));
		pnlTable.setLayout(new BorderLayout());
		pnlRight.add(pnlTable, BorderLayout.CENTER);

		// table------------
		String cols[] = { "#", "Mã SP", "Tên SP", "Loại SP", "Đơn Giá", "Trạng Thái" };

		dtbModelSanPham = new DefaultTableModel(cols, 0);
		tblSanPham = new JTable(dtbModelSanPham);

		tblSanPham.setRowHeight(30);
		tblSanPham.getColumnModel().getColumn(0).setPreferredWidth(20);
		tblSanPham.getColumnModel().getColumn(1).setPreferredWidth(80);
		tblSanPham.getColumnModel().getColumn(2).setPreferredWidth(120);
		tblSanPham.getColumnModel().getColumn(3).setPreferredWidth(80);
		tblSanPham.getColumnModel().getColumn(4).setPreferredWidth(80);
		tblSanPham.getColumnModel().getColumn(5).setPreferredWidth(60);

		JScrollPane srcODSP = new JScrollPane(tblSanPham);
		pnlTable.add(srcODSP, BorderLayout.CENTER);

		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLuu.addActionListener(this);
		btnHuy.addActionListener(this);

		btnAnhSanPham.addActionListener(this);
		btnThemLoaiSP.addActionListener(this);
		btnTimSP.addActionListener(this);

		tblSanPham.addMouseListener(this);

		txtTenSP.setEditable(false);
		cmbLoaiSP.setEnabled(false);
		btnThemLoaiSP.setEnabled(false);
		txtDonGia.setEditable(false);
		cmbTrangThaiSP.setEnabled(false);
		btnAnhSanPham.setEnabled(false);

		btnLuu.setEnabled(false);
		btnHuy.setEnabled(false);

		xoaRong();

		Set<String> loaiSanPhamSet = new HashSet<>();
		for (SanPham sp : sanPhamDS) {
			loaiSanPhamSet.add(sp.getLoaiSP());
		}

		// them vao cmb loaiSanPham
		for (String LoaiSp : loaiSanPhamSet) {
			cmbLoaiSP.addItem(LoaiSp);
			cmbTimLoaiSP.addItem(LoaiSp);
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		int selectRow = tblSanPham.getSelectedRow();
		if (o == tblSanPham) {
			if (selectRow != -1) {
				hienThiChiTietSP(selectRow);
			}
		}
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
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o == btnThem) {
			action = 0;

			txtTenSP.setEditable(true);
			cmbLoaiSP.setEnabled(true);
			btnThemLoaiSP.setEnabled(true);
			txtDonGia.setEditable(true);
			cmbTrangThaiSP.setEnabled(true);
			btnAnhSanPham.setEnabled(true);

			btnLuu.setEnabled(true);
			btnHuy.setEnabled(true);

			btnThem.setEnabled(false);
			btnSua.setEnabled(false);
			btnXoa.setEnabled(false);

		}
		if (o == btnSua) {
			action = 1;
			if (tblSanPham.getSelectedRow() != -1) {
				txtTenSP.setEditable(true);
				cmbLoaiSP.setEnabled(true);
				btnThemLoaiSP.setEnabled(true);
				txtDonGia.setEditable(true);
				cmbTrangThaiSP.setEnabled(true);
				btnAnhSanPham.setEnabled(true);

				btnLuu.setEnabled(true);
				btnHuy.setEnabled(true);

				btnThem.setEnabled(false);
				btnSua.setEnabled(false);
				btnXoa.setEnabled(false);

			} else {
				alertNotification("Cần chọn 1 dòng sản phẩm cần sửa");
			}

		}
		if (o == btnLuu) {
			if (action == 0) {
				themSP();
			} else if (action == 1) {
				suaSP();
			}
		}

		if (o == btnXoa) {
			if (tblSanPham.getSelectedRow() != -1) {
				xoaSP();
			} else {
				alertNotification("Cần chọn 1 dòng sản phẩm cần xoá");
			}
		}

		if (o == btnHuy) {
			txtTenSP.setEditable(false);
			cmbLoaiSP.setEnabled(false);
			btnThemLoaiSP.setEnabled(false);
			txtDonGia.setEditable(false);
			cmbTrangThaiSP.setEnabled(false);
			btnAnhSanPham.setEnabled(false);

			btnLuu.setEnabled(false);
			btnHuy.setEnabled(false);

			btnThem.setEnabled(true);
			btnSua.setEnabled(true);
			btnXoa.setEnabled(true);
		}

		if (o == btnAnhSanPham) {
			String namePathSP = getPathNameSP();
			lblImageSP.setIcon(new ImageScaler("/image_SanPham/" + namePathSP, 150, 150).getScaledImageIcon());
			btnAnhSanPham.setText(namePathSP);
		}

		if (o == btnThemLoaiSP) {
			JDialog dlThemLoai = new JDialog(new JFrame(), "THÊM LOẠI SẢN PHẨM MỚI",
					JDialog.ModalityType.APPLICATION_MODAL);
			dlThemLoai.setSize(500, 200);
			dlThemLoai.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dlThemLoai.setLocationRelativeTo(null);
			dlThemLoai.setLayout(new BorderLayout());

			JPanel pnlTSP = new JPanel();
			pnlTSP.setBorder(new EmptyBorder(20, 10, 30, 10));
			pnlTSP.setLayout(new BorderLayout());

			JLabel lblLSP = new JLabel("Tên Loại Sản Phẩm Mới:");
			lblLSP.setFont(new Font("Segoe UI Semibold", Font.BOLD, 25));
			pnlTSP.add(lblLSP, BorderLayout.NORTH);

			txtLSP = new JTextField(20);
			txtLSP.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
			pnlTSP.add(txtLSP, BorderLayout.CENTER);

			JButton btnThemLoai = new RoundedButton("THÊM LOẠI", null, 10,0, 0.3f);
			btnThemLoai.setBackground(Color.decode("#af2b2b"));
			btnThemLoai.setForeground(Color.decode("#ffffff"));
			pnlTSP.add(btnThemLoai, BorderLayout.SOUTH);

			btnThemLoai.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					cmbLoaiSP.addItem(txtLSP.getText());
					dlThemLoai.dispose();

				}
			});

			dlThemLoai.add(pnlTSP, BorderLayout.CENTER);

			dlThemLoai.setVisible(true);

		}

		if (o == btnTimSP) {
			timKiemTheoMaTen();
		}

	}

	// hien thi thong tin chi tiet
	public void hienThiChiTietSP(int index) {
		txtMaSP.setText(sanPhamDS.get(index).getMaSP());
		txtTenSP.setText(sanPhamDS.get(index).getTenSP());
		String loai = dtbModelSanPham.getValueAt(index, 3).toString();
		cmbLoaiSP.setSelectedItem(loai);

		txtDonGia.setText(String.valueOf(decimalFormat.format(sanPhamDS.get(index).getDonGia())));

		String trangThai = dtbModelSanPham.getValueAt(index, 5).toString();
		cmbTrangThaiSP.setSelectedItem(trangThai);

		lblImageSP.setIcon(
				new ImageScaler("/image_SanPham/" + sanPhamDS.get(index).getAnhSP(), 150, 150).getScaledImageIcon());
		btnAnhSanPham.setText(sanPhamDS.get(index).getAnhSP());

	}

	// them san pham
	private void themSP() {
		SanPham spNew = convertSP();
		if (spNew != null) {
			if (new SanPham_DAO().themSanPham(spNew)) {
				alertNotification("Thêm Sản Phẩm Thành Công");
				xoaRong();
			} else {
				alertNotification("Thêm Sản Phẩm Không Thành Công (Trùng Mã)");
			}
		}
	}

	// sua san pham
	private void suaSP() {
		SanPham sanPham = convertSP();
		if (sanPham != null) {
			if (new SanPham_DAO().suaSP(sanPham)) {
				alertNotification("Sửa thành công");
				xoaRong();
			} else {
				alertNotification("Sửa thất bại");
			}
		}
	}
	// xoa san pham

	public void xoaSP() {
		String maSP = txtMaSP.getText();
		if (maSP != null) {
			String message = String.format("Quyết định xóa SP có mã %s", maSP);
			int result = JOptionPane.showConfirmDialog(this, message, "NOTIFICATION", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				if (new SanPham_DAO().xoaSP(maSP)) {
					alertNotification("Xoá thành công");
					xoaRong();
				} else {
					alertNotification("Xóa không thành công! Không tìm thấy Sản phẩm cần xóa");
					xoaRong();
				}
			}
		} else {
			alertNotification("Đã có lỗi xảy ra");
		}
	}

	// convert data san pham
	public SanPham convertSP() {
		String maSP = txtMaSP.getText();
		String tenSP = txtTenSP.getText();
		String loaiSP = (String) cmbLoaiSP.getSelectedItem();
		try {
			Double donGia = decimalFormat.parse(txtDonGia.getText()).doubleValue();
			boolean trangThai = cmbTrangThaiSP.getSelectedItem().equals("Đang bán");

			String anhSP = btnAnhSanPham.getText();

			return new SanPham(maSP, tenSP, loaiSP, anhSP, donGia, trangThai);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

	}

	// get data len bang
	private void getDataSPLenBang() {
		sanPhamDS = new SanPham_DAO().getAllSanPham();
		dtbModelSanPham.setRowCount(0);
		for (SanPham sp : sanPhamDS) {
			themSPVaoBang(sp);
		}

	}

	// them san pham vao bang
	private void themSPVaoBang(SanPham sp) {
		String[] row = new String[99];
		row[0] = String.valueOf(dtbModelSanPham.getRowCount() + 1);
		row[1] = sp.getMaSP();
		row[2] = sp.getTenSP();
		row[3] = sp.getLoaiSP();
		row[4] = decimalFormat.format(sp.getDonGia());
		row[5] = sp.isTrangThai() ? "Đang bán" : "Ngừng bán";

		dtbModelSanPham.addRow(row);

	}

	// HÀM ĐỂ GET TÊN ẢNH ĐÃ CHỌN
	private String getPathNameSP() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter("Hình ảnh", "jpg", "jpeg", "png", "gif"));

		int returnValue = fileChooser.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			java.io.File selectedFile = fileChooser.getSelectedFile();
			String filePathSP = selectedFile.getName();
			pathNameSP = filePathSP;
			repaint();
			revalidate();
		}
		return pathNameSP;
	}

	// tim kiem theo ma va ten
	public void timKiemTheoMaTen() {
		sanPhamDS = new SanPham_DAO().timKiemSPTheoMavaTen(txtTImKiemTheoTenSP.getText());

		if (sanPhamDS.size() != 0) {
			alertNotification("Tìm thấy " + sanPhamDS.size() + " Sản phẩm");
			dtbModelSanPham.setRowCount(0);
			for (SanPham sp : sanPhamDS) {
				themSPVaoBang(sp);
			}

		} else {
			alertNotification("Không tìm thấy sản phẩm nào!!!");
			dtbModelSanPham.setRowCount(0);
			for (SanPham sp : sanPhamDS) {
				themSPVaoBang(sp);
			}
		}
	}

	// tim kiem theo loai
	public void timKiemTheoLoai(String loai) {
		sanPhamDS = new SanPham_DAO().timKiemSPTheoLoai(loai);

		if (sanPhamDS.size() != 0) {
			dtbModelSanPham.setRowCount(0);
			for (SanPham sp : sanPhamDS) {
				themSPVaoBang(sp);
			}

		} else {
			dtbModelSanPham.setRowCount(0);
			for (SanPham sp : sanPhamDS) {
				themSPVaoBang(sp);
			}
		}
	}

	// tim kiem theo loai
	public void timKiemTheoTrangThai(int tt) {
		sanPhamDS = new SanPham_DAO().timKiemSPTheoTrangThai(tt);

		if (sanPhamDS.size() != 0) {
			dtbModelSanPham.setRowCount(0);
			for (SanPham sp : sanPhamDS) {
				themSPVaoBang(sp);
			}

		} else {
			dtbModelSanPham.setRowCount(0);
			for (SanPham sp : sanPhamDS) {
				themSPVaoBang(sp);
			}
		}
	}

	// -----------------------------------
	// xoa rong
	public void xoaRong() {
		getDataSPLenBang();

		btnAnhSanPham.setText("image_sp_default.PNG");
		lblImageSP.setIcon(new ImageScaler("/image_SanPham/image_sp_default.PNG", 150, 150).getScaledImageIcon());
		txtMaSP.setText(new SinhMaTuDong_UTIL().sinhMaSP());

		txtTenSP.setText("");
		txtDonGia.setText("0");
		cmbTrangThaiSP.setSelectedIndex(0);

	}

	// ALERT

	public void alertNotification(String textError) {
		JOptionPane.showMessageDialog(null, textError);
	}
}
