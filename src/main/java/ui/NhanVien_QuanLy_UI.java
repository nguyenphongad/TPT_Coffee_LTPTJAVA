package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.ExceptionListener;
import java.lang.reflect.Array;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.jdesktop.swingx.JXDatePicker;

import customUI.ImageScaler;
import customUI.RoundedButton;
import dao.NhanVien_DAO;
import dao.TaiKhoan_DAO;
import entity.NhanVien;
import entity.TaiKhoan;

public class NhanVien_QuanLy_UI extends JPanel implements ActionListener,  MouseListener{
	
	private NhanVien_DAO nv_dao;
	private ArrayList<NhanVien> listNV;
	private ArrayList<TaiKhoan> listTK;
	private JTextField txtMaNV;
	private JTextField txtEmail;
	private JTextField txtCCCD;
	private JTextField txtDiaChi;
	private JTextField txtGhiChu;
	private JTable tableDanhSach;
	private DefaultTableModel dataTableModel;
	private JTextField txtHoTen;
	private JComboBox<String> comboboxGioiTinh;
	private Color componentColor = Color.decode("#424242");
	private RoundedButton btnThem;
	private RoundedButton btnSua;
	private RoundedButton btnXoa;
	private RoundedButton btnLuu;
	private RoundedButton btnHuy;
	private JPasswordField pwdMatKhau;
	private JTextField txtSdt;
	private JXDatePicker ngaySinh;
	private JXDatePicker ngayVaoLam;
	private JComboBox<String> comboBoxChucVu;
	private JComboBox<String> comboBoxTrangThai;
	private JLabel lblAvt;
	private JButton btnSetAvt;
	private boolean isThem = false;
	private int editSelected = 0;
	private String pathNameAvatar = "defaultavt.jpg";
	private TaiKhoan_DAO tk_dao;
	
	
	public NhanVien_QuanLy_UI() {
		nv_dao = new NhanVien_DAO();
		tk_dao = new TaiKhoan_DAO();
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelQuanLy = new JPanel();
		add(panelQuanLy, BorderLayout.NORTH);
		panelQuanLy.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTitle = new JPanel();
		panelQuanLy.add(panelTitle, BorderLayout.NORTH);
		
		JLabel lblTitle = new JLabel("Quản lý nhân viên");
		panelTitle.add(lblTitle);
		
		JPanel panelDetail = new JPanel();
		panelDetail.setBorder(new TitledBorder(null, "Thông tin nhân viên", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelQuanLy.add(panelDetail, BorderLayout.CENTER);
		panelDetail.setLayout(new BorderLayout(0, 0));
		
		JPanel panelAvt = new JPanel();
		panelAvt.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelDetail.add(panelAvt, BorderLayout.WEST);
		panelAvt.setLayout(new BorderLayout(0, 0));
		
		lblAvt = new JLabel("");
		panelAvt.add(lblAvt, BorderLayout.CENTER);
		lblAvt.setIcon(new ImageScaler( "/image_NhanVien/" +  pathNameAvatar  , 150, 150).getScaledImageIcon());
		lblAvt.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnSetAvt = new JButton("Ảnh đại diện");
		panelAvt.add(btnSetAvt, BorderLayout.SOUTH);
		
		JPanel panelInfo = new JPanel();
		panelDetail.add(panelInfo, BorderLayout.CENTER);
		panelInfo.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panelInfo.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(3, 3, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_13 = new JPanel();
		panel_2.add(panel_13, BorderLayout.NORTH);
		panel_13.setLayout(new BorderLayout(0, 0));
		
		JLabel lblMaNV = new JLabel("Mã NV");
		panel_13.add(lblMaNV, BorderLayout.WEST);
		
		txtMaNV = new JTextField();
		panel_13.add(txtMaNV, BorderLayout.CENTER);
		txtMaNV.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_14 = new JPanel();
		panel_3.add(panel_14, BorderLayout.NORTH);
		panel_14.setLayout(new BorderLayout(0, 0));
		
		JLabel lblHoTen = new JLabel("Họ tên");
		panel_14.add(lblHoTen, BorderLayout.WEST);
		
		txtHoTen = new JTextField();
		panel_14.add(txtHoTen, BorderLayout.CENTER);
		txtHoTen.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_15 = new JPanel();
		panel_4.add(panel_15, BorderLayout.NORTH);
		panel_15.setLayout(new BorderLayout(0, 0));
		
		JLabel lblMatKhau = new JLabel("Mật khẩu");
		panel_15.add(lblMatKhau, BorderLayout.WEST);
		
		pwdMatKhau = new JPasswordField();
		panel_15.add(pwdMatKhau, BorderLayout.CENTER);
		
		JPanel panel_5 = new JPanel();
		panel.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_16 = new JPanel();
		panel_5.add(panel_16, BorderLayout.NORTH);
		panel_16.setLayout(new BorderLayout(0, 0));
		
		JLabel lblGioiTinh = new JLabel("Giới tính");
		panel_16.add(lblGioiTinh, BorderLayout.WEST);
		
		comboboxGioiTinh = new JComboBox<String>();
		panel_16.add(comboboxGioiTinh, BorderLayout.CENTER);
		comboboxGioiTinh.addItem("Nam");
		comboboxGioiTinh.addItem("Nữ");
		
		JPanel panel_6 = new JPanel();
		panel.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_17 = new JPanel();
		panel_6.add(panel_17, BorderLayout.NORTH);
		panel_17.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNgaySinh = new JLabel("Ngày sinh");
		panel_17.add(lblNgaySinh, BorderLayout.WEST);
		
		ngaySinh = new JXDatePicker();
		panel_17.add(ngaySinh, BorderLayout.CENTER);

		JPanel panel_7 = new JPanel();
		panel.add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_18 = new JPanel();
		panel_7.add(panel_18, BorderLayout.NORTH);
		panel_18.setLayout(new BorderLayout(0, 0));
		
		JLabel lblSDT = new JLabel("Số ĐT");
		panel_18.add(lblSDT, BorderLayout.WEST);
		
		txtSdt = new JTextField();
		txtSdt.setText("");
		panel_18.add(txtSdt, BorderLayout.CENTER);
		txtSdt.setColumns(10);
		
		JPanel panel_8 = new JPanel();
		panel.add(panel_8);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_19 = new JPanel();
		panel_8.add(panel_19, BorderLayout.NORTH);
		panel_19.setLayout(new BorderLayout(0, 0));
		
		JLabel lblEmail = new JLabel("Email");
		panel_19.add(lblEmail, BorderLayout.WEST);
		
		txtEmail = new JTextField();
		panel_19.add(txtEmail, BorderLayout.CENTER);
		txtEmail.setColumns(10);
		
		JPanel panel_9 = new JPanel();
		panel.add(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_20 = new JPanel();
		panel_9.add(panel_20, BorderLayout.NORTH);
		panel_20.setLayout(new BorderLayout(0, 0));
		
		JLabel lblCCCD = new JLabel("Mã CCCD");
		panel_20.add(lblCCCD, BorderLayout.WEST);
		
		txtCCCD = new JTextField();
		panel_20.add(txtCCCD, BorderLayout.CENTER);
		txtCCCD.setColumns(10);
		
		JPanel panel_10 = new JPanel();
		panel.add(panel_10);
		panel_10.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_21 = new JPanel();
		panel_10.add(panel_21, BorderLayout.NORTH);
		panel_21.setLayout(new BorderLayout(0, 0));
		
		JLabel lblDiaChi = new JLabel("Địa chỉ");
		panel_21.add(lblDiaChi, BorderLayout.WEST);
		
		txtDiaChi = new JTextField();
		panel_21.add(txtDiaChi, BorderLayout.CENTER);
		txtDiaChi.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(0, 0, 15, 0));
		panelInfo.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(2, 3, 0, 0));
		
		JPanel panel_11 = new JPanel();
		panel_1.add(panel_11);
		panel_11.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_22 = new JPanel();
		panel_11.add(panel_22, BorderLayout.NORTH);
		panel_22.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNgayVaoLam = new JLabel("Ngày vào làm");
		panel_22.add(lblNgayVaoLam, BorderLayout.WEST);
		
		ngayVaoLam = new JXDatePicker();
		panel_22.add(ngayVaoLam, BorderLayout.CENTER);
		//txtNgayVaoLam.setColumns(8);
		
		JPanel panel_12 = new JPanel();
		panel_1.add(panel_12);
		panel_12.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_23 = new JPanel();
		panel_12.add(panel_23, BorderLayout.NORTH);
		panel_23.setLayout(new BorderLayout(0, 0));
		
		JLabel lblGhiChu = new JLabel("Ghi chú");
		panel_23.add(lblGhiChu, BorderLayout.WEST);
		
		txtGhiChu = new JTextField();
		txtGhiChu.setColumns(10);
		panel_23.add(txtGhiChu, BorderLayout.CENTER);
		
		JPanel panelOption = new JPanel();
		panelQuanLy.add(panelOption, BorderLayout.SOUTH);
		panelOption.setLayout(new GridLayout(1, 2, 0, 0));
		
		lblCCCD.setPreferredSize(lblNgayVaoLam.getPreferredSize());
		lblDiaChi.setPreferredSize(lblNgayVaoLam.getPreferredSize());
		lblEmail.setPreferredSize(lblNgayVaoLam.getPreferredSize());
		
		JPanel panel_26 = new JPanel();
		panel_1.add(panel_26);
		panel_26.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_28 = new JPanel();
		panel_26.add(panel_28, BorderLayout.NORTH);
		panel_28.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTrangThai = new JLabel("Trạng thái");
		lblTrangThai.setPreferredSize(new Dimension(62, 13));
		panel_28.add(lblTrangThai, BorderLayout.WEST);
		
		comboBoxTrangThai = new JComboBox<String>();
		panel_28.add(comboBoxTrangThai, BorderLayout.CENTER);
		comboBoxTrangThai.addItem("Đang làm");
		comboBoxTrangThai.addItem("Đã nghỉ");
		
		JPanel panel_27 = new JPanel();
		panel_1.add(panel_27);
		panel_27.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_29 = new JPanel();
		panel_27.add(panel_29, BorderLayout.NORTH);
		panel_29.setLayout(new BorderLayout(0, 0));
		
		JLabel lblChucVu = new JLabel("Chức vụ");
		panel_29.add(lblChucVu, BorderLayout.WEST);
		
		comboBoxChucVu = new JComboBox<String>();
		panel_29.add(comboBoxChucVu, BorderLayout.CENTER);
		comboBoxChucVu.addItem("Nhân viên");
		comboBoxChucVu.addItem("Quản lý");
		lblGioiTinh.setPreferredSize(lblNgayVaoLam.getPreferredSize());
		lblHoTen.setPreferredSize(lblNgayVaoLam.getPreferredSize());
		lblMaNV.setPreferredSize(lblNgayVaoLam.getPreferredSize());
		lblMatKhau.setPreferredSize(lblNgayVaoLam.getPreferredSize());
		lblNgaySinh.setPreferredSize(lblNgayVaoLam.getPreferredSize());
		lblSDT.setPreferredSize(lblNgayVaoLam.getPreferredSize());
		
		JPanel panel_24 = new JPanel();
		panelOption.add(panel_24);
		panel_24.add(btnThem = new RoundedButton("Thêm", Color.BLUE, 20, 0, 1.0f));
		btnThem.setBackground(new Color(0, 128, 255));
		btnThem.setFont(new Font("Roboto", Font.BOLD, 20));
		btnThem.setForeground(Color.WHITE);
		panel_24.add(btnSua = new RoundedButton("Sửa", Color.YELLOW, 20, 0, 1.0f));
		btnSua.setBackground(new Color(255, 128, 0));
		btnSua.setFont(new Font("Roboto", Font.BOLD, 20));
		btnSua.setForeground(Color.WHITE);
		panel_24.add(btnXoa = new RoundedButton("Xóa", Color.red, 20, 0, 1.0f));
		btnXoa.setBackground(new Color(255, 0, 0));
		btnXoa.setFont(new Font("Roboto", Font.BOLD, 20));
		btnXoa.setForeground(Color.WHITE);
		
		JPanel panel_25 = new JPanel();
		panelOption.add(panel_25);
		panel_25.add(btnLuu = new RoundedButton("Lưu", Color.GREEN, 20, 0, 1.0f));
		btnLuu.setBackground(new Color(0, 255, 0));
		btnLuu.setFont(new Font("Roboto", Font.BOLD, 20));
		btnLuu.setForeground(Color.WHITE);
		panel_25.add(btnHuy = new RoundedButton("Hủy", Color.GRAY, 20, 0, 1.0f));
		btnHuy.setBackground(new Color(128, 128, 192));
		btnHuy.setFont(new Font("Roboto", Font.BOLD, 20));
		btnHuy.setForeground(Color.WHITE);
		
		JPanel panelDanhSach = new JPanel();
		add(panelDanhSach, BorderLayout.CENTER);
		panelDanhSach.setLayout(new BorderLayout(0, 0));
		
		Box boxTable = Box.createHorizontalBox();
		panelDanhSach.add(boxTable);
		
		String[] headerStrings = {"STT", "Mã nhân viên", "Họ tên", "Giới tính", "Ngày sinh", "SDT", "Email", "Địa chỉ", "CCCD", "Ngày vào làm", "Trạng thái", "Chức vụ","Ghi chú"};
		dataTableModel = new DefaultTableModel(headerStrings, 0);
		tableDanhSach = new JTable(dataTableModel);
		tableDanhSach.setRowHeight(24);

		JScrollPane scrollTable = new JScrollPane();
		scrollTable.setViewportView(tableDanhSach);
		boxTable.add(scrollTable);
		
		txtMaNV.setEditable(false);
		txtHoTen.setEditable(false);
		pwdMatKhau.setEditable(false);
		comboboxGioiTinh.setEditable(false);
		ngaySinh.setEditable(false);
		txtSdt.setEditable(false);
		txtEmail.setEditable(false);
		txtCCCD.setEditable(false);
		txtDiaChi.setEditable(false);
		ngayVaoLam.setEditable(false);
		txtGhiChu.setEditable(false);
		comboBoxTrangThai.setEditable(false);
		comboBoxChucVu.setEditable(false);

		btnSetAvt.addActionListener(this);
		btnHuy.addActionListener(this);
		btnLuu.addActionListener(this);
		btnSua.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSetAvt.addActionListener(this);
		
		btnLuu.setEnabled(false);
		btnHuy.setEnabled(false);
		btnSetAvt.setEnabled(false);
		tableDanhSach.addMouseListener(this);
		
		layDataThemVaoBang();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object event = e.getSource();
		if(event == tableDanhSach) {
			int selectedItem = tableDanhSach.getSelectedRow();
			if(selectedItem != -1) {
				choPhepLuuVaHuy(false);
				btnHuy.setEnabled(true);
				settextEditable(false);
				hienThiThongTinNhanVien(selectedItem);
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
		// TODO Auto-generated method stub
		Object ev = e.getSource();
		if(ev.equals(btnSetAvt)) {
			String avtPath = getPathNameAvatar();
			System.out.println("avt check: " + avtPath);
			lblAvt.setIcon(new ImageScaler( "/image_NhanVien/" + avtPath, 150, 150).getScaledImageIcon());
			btnSetAvt.setText(avtPath);
		}
		if(ev.equals(btnThem)) {
			choPhepLuuVaHuy(true);
			settextEditable(true);
			txtMaNV.setEditable(true);
			xoaRong();
			ngayVaoLam.setDate(new java.util.Date());
			isThem = true;
		}
		if(ev.equals(btnXoa)) {
			if(tableDanhSach.getSelectedRow() != -1) {
				xoaAction();
			}
			else {
				JOptionPane.showMessageDialog(null, "Chưa chọn dòng cần xóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}		
		}
		if(ev.equals(btnSua)) {
			
			isThem = false;
			if(tableDanhSach.getSelectedRow() != -1) {
				choPhepLuuVaHuy(true);
				settextEditable(true);
			}
		}
		if(ev.equals(btnHuy)) {
			choPhepLuuVaHuy(false);
			settextEditable(false);
			xoaRong();
		}
		if(ev.equals(btnLuu)) {
			if(isThem == true) {
				themAction();
			}
			else {
				suaAction();
			}
		}
		
	}

	private void suaAction() {
		// TODO Auto-generated method stub
		if(isValidInput() == true) {
			NhanVien nVien = convertDataToNhanVien();
			TaiKhoan tKhoan = new TaiKhoan(nVien, pwdMatKhau.getText());
			if(nVien != null) {
				if(nv_dao.suaNhanVien(nVien, tKhoan)) {
					xoaRong();
					choPhepLuuVaHuy(false);
					settextEditable(false);
					JOptionPane.showMessageDialog(null, "Sửa nhân viên thành công.");
					refreshTableToUpdateChange();
				}
				else {
					JOptionPane.showMessageDialog(null, "Sửa không thành công");
				}
			}
		}
	}

	private void themAction() {
		// TODO Auto-generated method stub
		if(isValidInput() == true) {
			NhanVien nVien = convertDataToNhanVien();
			TaiKhoan taiKhoan = new TaiKhoan(nVien, pwdMatKhau.getText());
			System.out.println(nVien.toString());
			if(nVien != null) {
				if(nv_dao.themNhanVien(nVien, taiKhoan)) {
					themNhanVienVaoBang(nVien);
					
					xoaRong();
					JOptionPane.showMessageDialog(null, "Thêm nhân viên mới thành công.");
				}
				else {
					JOptionPane.showMessageDialog(null, "Thêm nhân viên mới không thành công.");
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Đã có lỗi xảy ra trong quá trình thêm.");
			}
		}
	}

	private void xoaAction() {
		// TODO Auto-generated method stub
		String ma = txtMaNV.getText();
		if(ma != null) {
			int result = JOptionPane.showConfirmDialog(this, "Xác nhận xóa?", "Chú Ý", JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION) {
				if(nv_dao.xoaNhanVien(ma)) {
					xoaRong();
					refreshTableToUpdateChange();
					JOptionPane.showMessageDialog(null, "Xóa thành công.");
				}
				else {
					JOptionPane.showMessageDialog(null, "Xóa không thành công.");
				}
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Đã có lỗi xảy ra trong quá trình sửa.");
		}
	}

	private void xoaRong() {
		// TODO Auto-generated method stub
//		listNV = nv_dao.getAllNhanVien();
//		themAllNhanVienVaoBang(listNV);
		txtCCCD.setText("");		
		txtDiaChi.setText("");		
		txtEmail.setText(""); 		
		txtGhiChu.setText(""); 		
		txtHoTen.setText("");		
		txtMaNV.setText("");		
		pwdMatKhau.setText("");		
		txtSdt.setText(""); 		
		lblAvt.setIcon(null);		
		ngaySinh.setDate(null);		
		ngayVaoLam.setDate(null);	
		tableDanhSach.clearSelection();
		lblAvt.setIcon(new ImageScaler("/image_NhanVien/defaultavt.jpg" , 150, 150).getScaledImageIcon());
		btnSetAvt.setText("Ảnh đại diện");
	}

	private void layDataThemVaoBang() {
		listNV = nv_dao.getAllNhanVien();
		listTK = tk_dao.getAllTaiKhoan();
		System.out.println(listNV.toString());
		themAllNhanVienVaoBang(listNV);
	}
	
	private void refreshTableToUpdateChange() {
		ArrayList<NhanVien> listNVMoi = nv_dao.getAllNhanVien();
		dataTableModel.setRowCount(0);
		themAllNhanVienVaoBang(listNVMoi);
		dataTableModel.fireTableDataChanged();
		tableDanhSach.repaint();
	}
	
	private void settextEditable(boolean isEditable) {
		// TODO Auto-generated method stub
		if(isEditable == false) {
			txtMaNV.setEditable(false);
			txtHoTen.setEditable(false);
			pwdMatKhau.setEditable(false);
			comboboxGioiTinh.setEditable(false);
			ngaySinh.setEditable(false);
			txtSdt.setEditable(false);
			txtEmail.setEditable(false);
			txtCCCD.setEditable(false);
			txtDiaChi.setEditable(false);
			ngayVaoLam.setEditable(false);
			txtGhiChu.setEditable(false);
			comboBoxTrangThai.setEditable(false);
			comboBoxChucVu.setEditable(false);
			btnSetAvt.setEnabled(false);
		}
		else {
			txtMaNV.setEditable(false);
			txtHoTen.setEditable(true);
			pwdMatKhau.setEditable(true);
			comboboxGioiTinh.setEditable(true);
			ngaySinh.setEditable(true);
			txtSdt.setEditable(true);
			txtEmail.setEditable(true);
			txtCCCD.setEditable(true);
			txtDiaChi.setEditable(true);
			ngayVaoLam.setEditable(true);
			txtGhiChu.setEditable(true);
			comboBoxTrangThai.setEditable(true);
			comboBoxChucVu.setEditable(true);
			btnSetAvt.setEnabled(true);
		}
	}

	
	//set enable nut luu va huy
	private void choPhepLuuVaHuy( boolean display) {
		// TODO Auto-generated method stub
		if(display == true) {
			btnLuu.setEnabled(true);
			btnHuy.setEnabled(true);
			
			btnThem.setEnabled(false);
			btnXoa.setEnabled(false);
			btnSua.setEnabled(false);
		}
		else {
			btnLuu.setEnabled(false);
			btnHuy.setEnabled(false);
			btnThem.setEnabled(true);
			btnXoa.setEnabled(true);
			btnSua.setEnabled(true);
		}
		
	}

	//lay avt
	private String getPathNameAvatar() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter("Hình ảnh", "jpg", "jpeg", "png", "gif"));

		int returnValue = fileChooser.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			java.io.File selectedFile = fileChooser.getSelectedFile();
			String filePathAvatar = selectedFile.getName();
			pathNameAvatar = filePathAvatar;
			repaint();
			revalidate();
		}
		return pathNameAvatar;
	}
	public java.util.Date castToDateFromString(String dateString) {
		try {
			String dateFormatPattern = "EEE MMM dd HH:mm:ss zzz yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatPattern, Locale.US);
			java.util.Date casedDate;
			casedDate =  dateFormat.parse(dateString);
			return casedDate;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return null;
	}
	
	private void themAllNhanVienVaoBang(ArrayList<NhanVien> list) {
		dataTableModel.setRowCount(0);
		for(NhanVien nv: list) {
			themNhanVienVaoBang(nv);
		}
	}
	
	private void themNhanVienVaoBang(NhanVien nv) {
		String[] row = new String[13];
		row[0] = String.valueOf(dataTableModel.getRowCount() +1);
		row[1] = nv.getMaNV();
		row[2] = nv.getTenNV();
		row[3] = nv.isGioiTinh() == true ? "Nam" : "Nữ";
		row[4] = nv.getNgaySinh().toString();
		row[5] = nv.getSDT();
		row[6] = nv.getEmail();
		row[7] = nv.getDiaChi();
		row[8] = nv.getMaCCCD();
		row[9] = nv.getNgayVaoLam().toString();
		row[10]= nv.isTrangThai() == true ? "Đang làm" : "Đã nghỉ";
		row[11]= comboBoxChucVu.getItemAt(nv.getChucVu());
		row[12]= nv.getGhiChu();
		dataTableModel.addRow(row);
 	}
	
	private void hienThiThongTinNhanVien(int selectedItem) {
		txtMaNV.setText(listNV.get(selectedItem).getMaNV());
		txtHoTen.setText(listNV.get(selectedItem).getTenNV());
		pwdMatKhau.setText(listTK.get(selectedItem).getMatKhau());
		comboboxGioiTinh.setSelectedIndex(listNV.get(selectedItem).isGioiTinh() == true ? 0 : 1);
		ngaySinh.setDate(listNV.get(selectedItem).getNgaySinh());
		txtSdt.setText(listNV.get(selectedItem).getSDT());
		txtEmail.setText(listNV.get(selectedItem).getEmail());
		txtCCCD.setText(listNV.get(selectedItem).getMaCCCD());
		txtDiaChi.setText(listNV.get(selectedItem).getDiaChi());
		ngayVaoLam.setDate(listNV.get(selectedItem).getNgayVaoLam());
		txtGhiChu.setText(listNV.get(selectedItem).getGhiChu());
		comboBoxTrangThai.setSelectedIndex(listNV.get(selectedItem).isTrangThai() == true ? 0 : 1);
		comboBoxChucVu.setSelectedIndex(listNV.get(selectedItem).getChucVu());
		if(listNV.get(selectedItem).getAvtString() == null) {
			lblAvt.setIcon(new ImageScaler("defaultavt.jpg", 150, 150).getScaledImageIcon());
		}
		else {
			lblAvt.setIcon(new ImageScaler("/image_NhanVien/" + listNV.get(selectedItem).getAvtString() , 150, 150).getScaledImageIcon());
		}
	}
	
	public boolean isValidInput() {
		String regexMa = "^NV+[0-9]{4}$";
//		String regexHoTen = "^[A-Za-z]+( [A-Za-z]+)*$";
		String regexmatKhau = "^[a-zA-Z0-9]{9,15}$";
		java.util.Date ngayHnay = new java.util.Date();
		String regexSdt = "^0[0-9]{9}$";
		String regexEmail = "^[a-zA-Z0-9._%+-]+@[\\w]*+\\.[\\w]*$";
		String regexCCCD = "^[0-9]{12}";
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, -18);
		java.util.Date allowAge = calendar.getTime();
		
		
		if(!txtMaNV.getText().matches(regexMa)) {
			JOptionPane.showMessageDialog(null, "Lỗi mã nhân viên.", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if(txtHoTen.getText().length() < 2) {
			JOptionPane.showMessageDialog(null, "Lỗi họ tên", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if(!pwdMatKhau.getText().matches(regexmatKhau)) {
			JOptionPane.showMessageDialog(null, "Lỗi mật khẩu", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if(ngaySinh.getDate().after(allowAge)) {
			JOptionPane.showMessageDialog(null, "Lỗi ngày sinh. Phai hon 18 tuoi", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if(!txtSdt.getText().matches(regexSdt)) {
			JOptionPane.showMessageDialog(null, "Lỗi số điện thoại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if(!txtEmail.getText().matches(regexEmail)) {
			JOptionPane.showMessageDialog(null, "Lỗi email", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if(!txtCCCD.getText().matches(regexCCCD)) {
			JOptionPane.showMessageDialog(null, "Lỗi căn cước công dân", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if(ngayVaoLam.getDate().after(ngayHnay)) {
			JOptionPane.showMessageDialog(null, "Lỗi ngày vào làm", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		return true;
	}
	
	private NhanVien convertDataToNhanVien() {
		String ma = txtMaNV.getText();
		String hoTen = txtHoTen.getText();
		String matKhau = pwdMatKhau.getText();
		boolean gioiTinh = false;
		if(comboboxGioiTinh.getSelectedItem().toString().equalsIgnoreCase("Nam")) {
			gioiTinh = true;
		}
		boolean trangthai = false;
		if(comboBoxTrangThai.getSelectedItem().toString().equalsIgnoreCase("Đang làm")) {
			trangthai = true;
		}
		java.util.Date ngayXinh = ngaySinh.getDate();
		Date ngayXinhSQLDate = new Date(ngayXinh.getTime());
		String sdt = txtSdt.getText();
		String email = txtEmail.getText();
		String macccd = txtCCCD.getText();
		String diaChi = txtDiaChi.getText();
		java.util.Date ngayvaolam = ngayVaoLam.getDate();
		Date ngayVaoLamSQLDate = new Date(ngayvaolam.getTime());
		String ghichu = txtGhiChu.getText();
//		boolean trangThai = true;
//		if(comboBoxTrangThai.getSelectedItem().toString().equalsIgnoreCase("Đã nghỉ")) {
//			trangthai = false;
//		}
		int chucvu = comboBoxChucVu.getSelectedIndex();
		String avt = pathNameAvatar;
		NhanVien nVien = new NhanVien(ma, hoTen, gioiTinh, ngayXinhSQLDate, sdt, email, macccd, diaChi, ngayVaoLamSQLDate, ghichu, trangthai, chucvu, avt);
		return nVien;
	}

}
