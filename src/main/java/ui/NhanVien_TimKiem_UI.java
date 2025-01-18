package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXDatePicker;

import customUI.ImageScaler;
import customUI.RoundedButton;
import dao.NhanVien_DAO;
import entity.NhanVien;

public class NhanVien_TimKiem_UI extends JPanel implements ActionListener, MouseListener{
//	private DefaultTableModel dataTableModel;
//	private JTable tableDanhSach;
//	private JTextField txtTimKiemMaNV;
	private JXDatePicker TimKiemNgaySinh;
//	private JTextField txtTimKiemSDT;
	private JXDatePicker TimKiemNgayVaoLam;
//	private JTextField txtTimKiemHoTen;
//	private JTextField txtTimKiemDiaChi;
//	private JTextField txtKetQuaMaNV;
	private JXDatePicker KetQuaNgaySinh;
//	private JTextField txtKetQuaSDT;
	private JXDatePicker KetQuaNgayVaoLam;
//	private JTextField txtKetQuaHoTen;
//	private JTextField txtKetQuaDiaChi;
//	private RoundedButton btnTim;
	private ArrayList<NhanVien> listNV;
	private NhanVien_DAO nv_dao;
//	private JComboBox listKetQuaGioiTinh;
//	private JComboBox listGioiTinh2;
//	private JTextField txtTimKiemCCCD;
//	private JTextField txtKetQuaCCCD;
//	private JTextField txtKetQuaEmail;
//	private JTextField txtTimKiemEmail;
//	private JTextField txtKetQuaGhiChu;
//	private JComboBox listKetQuaTrangThai;
//	private JComboBox listKetQuaChucVu;
	private DefaultTableModel dataTableModel;
	private JTable tableDanhSach;
	private JTextField txtTimKiemMaNV;
	private JTextField txtTimKiemNgaySinh;
	private JTextField txtTimKiemSDT;
	private JTextField txtTimKiemNgayVaoLam;
	private JTextField txtTimKiemHoTen;
	private JTextField txtTimKiemDiaChi;
	private JTextField txtKetQuaMaNV;
	private JTextField txtKetQuaNgaySinh;
	private JTextField txtKetQuaSDT;
	private JTextField txtKetQuaNgayVaoLam;
	private JTextField txtKetQuaHoTen;
	private JTextField txtKetQuaDiaChi;
	private JTextField txtTimKiemCCCD;
	private JTextField txtTimKiemEmail;
	private JTextField txtKetQuaCCCD;
	private JTextField txtKetQuaEmail;
	private JTextField txtKetQuaGhiChu;
	private RoundedButton btnTim;
	private JComboBox<String> listKetQuaTrangThai;
	private JComboBox<String> listKetQuaGioiTinh;
	private JComboBox<String> listKetQuaChucVu;
	private JComboBox<String> listTrangThaiTimKiem;
	private JComboBox<String> listGioiTinhTimKiem;
	private JComboBox<String> listChucVuTimKiem;
	private JLabel lblAvt;
	private JRadioButton checkBoxKetQuaNam;
	private JRadioButton checkBoxKetQuaNu;
	private JRadioButton checkBoxTimKiemNam;
	private JRadioButton checkBoxTimKiemNu;
	private ButtonGroup groupKetQuaGioiTinh;

			private ArrayList<NhanVien> listKetQuaTimKiem = new ArrayList<NhanVien>();
	
	public NhanVien_TimKiem_UI() {
		
		nv_dao = new NhanVien_DAO();
		setLayout(new BorderLayout(0, 0));
		
		JPanel centerPanel = new JPanel();
		add(centerPanel, BorderLayout.NORTH);
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		Box boxEast = Box.createVerticalBox();
		centerPanel.add(boxEast, BorderLayout.WEST);
		
		JPanel timKiemInfoPanel = new JPanel();
		boxEast.add(timKiemInfoPanel);
		timKiemInfoPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel timKiem = new JPanel();
		timKiem.setBorder(new TitledBorder(null, "Th\u00F4ng tin c\u1EA7n t\u00ECm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		timKiemInfoPanel.add(timKiem);
		timKiem.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel timKiemLeft = new JPanel();
		timKiem.add(timKiemLeft);
		timKiemLeft.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelTimKiemMaNV = new JPanel();
		timKiemLeft.add(panelTimKiemMaNV);
		panelTimKiemMaNV.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panelTimKiemMaNV.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTimKiemMaNV = new JLabel("Mã NV");
		panel_1.add(lblTimKiemMaNV, BorderLayout.WEST);
		
		txtTimKiemMaNV = new JTextField();
		txtTimKiemMaNV.setColumns(10);
		panel_1.add(txtTimKiemMaNV);
		
		JPanel panelTimKiemNgaySinh = new JPanel();
		timKiemLeft.add(panelTimKiemNgaySinh);
		panelTimKiemNgaySinh.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panelTimKiemNgaySinh.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTimKiemNgaySinh = new JLabel("Ngày sinh");
		panel_2.add(lblTimKiemNgaySinh, BorderLayout.WEST);
		
		TimKiemNgaySinh = new JXDatePicker();
		panel_2.add(TimKiemNgaySinh);
		
		JPanel panelTimKiemSDT = new JPanel();
		timKiemLeft.add(panelTimKiemSDT);
		panelTimKiemSDT.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panelTimKiemSDT.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTimKiemSDT = new JLabel("Số ĐT");
		panel_3.add(lblTimKiemSDT, BorderLayout.WEST);
		
		txtTimKiemSDT = new JTextField();
		txtTimKiemSDT.setColumns(10);
		panel_3.add(txtTimKiemSDT);
		
		JPanel panelTimKiemNgayVaoLam = new JPanel();
		timKiemLeft.add(panelTimKiemNgayVaoLam);
		panelTimKiemNgayVaoLam.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panelTimKiemNgayVaoLam.add(panel_4, BorderLayout.NORTH);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTimKiemNgayVaoLam = new JLabel("Ngày vào làm");
		panel_4.add(lblTimKiemNgayVaoLam, BorderLayout.WEST);
		
		TimKiemNgayVaoLam = new JXDatePicker();
		panel_4.add(TimKiemNgayVaoLam);
		
		JPanel panelTimKiemTrangThai = new JPanel();
		timKiemLeft.add(panelTimKiemTrangThai);
		panelTimKiemTrangThai.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panelTimKiemTrangThai.add(panel_5, BorderLayout.NORTH);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTimKiemTrangThai = new JLabel("Trạng thái");
		panel_5.add(lblTimKiemTrangThai, BorderLayout.WEST);
		
		listTrangThaiTimKiem = new JComboBox<String>();
		listTrangThaiTimKiem.addItem("Đang làm");
		listTrangThaiTimKiem.addItem("Đã nghỉ");
		panel_5.add(listTrangThaiTimKiem);
		
		JPanel timKiemRight = new JPanel();
		timKiem.add(timKiemRight);
		timKiemRight.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelTimKiemHoTen = new JPanel();
		timKiemRight.add(panelTimKiemHoTen);
		panelTimKiemHoTen.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_6 = new JPanel();
		panelTimKiemHoTen.add(panel_6, BorderLayout.NORTH);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTimKiemHoTen = new JLabel("Họ tên");
		panel_6.add(lblTimKiemHoTen, BorderLayout.WEST);
		
		txtTimKiemHoTen = new JTextField();
		txtTimKiemHoTen.setColumns(10);
		panel_6.add(txtTimKiemHoTen);
		
		JPanel panelTimKiemGioiTinh = new JPanel();
		timKiemRight.add(panelTimKiemGioiTinh);
		panelTimKiemGioiTinh.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_7 = new JPanel();
		panelTimKiemGioiTinh.add(panel_7, BorderLayout.NORTH);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTimKiemGioiTnh = new JLabel("Giới tính");
		panel_7.add(lblTimKiemGioiTnh, BorderLayout.WEST);
		
		JPanel panel_29 = new JPanel();
		panel_7.add(panel_29, BorderLayout.CENTER);
		
		checkBoxTimKiemNam = new JRadioButton("Nam");
		panel_29.add(checkBoxTimKiemNam);
		
		checkBoxTimKiemNu = new JRadioButton("Nữ");
		panel_29.add(checkBoxTimKiemNu);
		checkBoxTimKiemNam.setSelected(true);
		
		ButtonGroup groupTimKiemGioiTinh = new ButtonGroup();
		groupTimKiemGioiTinh.add(checkBoxTimKiemNam);
		groupTimKiemGioiTinh.add(checkBoxTimKiemNu);
		
		
		JPanel panelTimKiemDiaChi = new JPanel();
		timKiemRight.add(panelTimKiemDiaChi);
		panelTimKiemDiaChi.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_8 = new JPanel();
		panelTimKiemDiaChi.add(panel_8, BorderLayout.NORTH);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTimKiemDiaChi = new JLabel("Địa chỉ");
		panel_8.add(lblTimKiemDiaChi, BorderLayout.WEST);
		
		txtTimKiemDiaChi = new JTextField();
		txtTimKiemDiaChi.setColumns(10);
		panel_8.add(txtTimKiemDiaChi, BorderLayout.CENTER);
		
		JPanel panelTimKiemChucVU = new JPanel();
		timKiemRight.add(panelTimKiemChucVU);
		panelTimKiemChucVU.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_9 = new JPanel();
		panelTimKiemChucVU.add(panel_9, BorderLayout.NORTH);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTimKiemChucVu = new JLabel("Chức vụ");
		panel_9.add(lblTimKiemChucVu, BorderLayout.WEST);
		
		listChucVuTimKiem = new JComboBox<String>();
		listChucVuTimKiem.addItem("Nhân viên");
		listChucVuTimKiem.addItem("Quản lý");
		panel_9.add(listChucVuTimKiem, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		timKiemRight.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_22 = new JPanel();
		panel.add(panel_22, BorderLayout.NORTH);
		panel_22.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTimKiemEmail = new JLabel("Email");
		panel_22.add(lblTimKiemEmail, BorderLayout.WEST);
		
		txtTimKiemEmail = new JTextField();
		txtTimKiemEmail.setText("");
		panel_22.add(txtTimKiemEmail, BorderLayout.CENTER);
		txtTimKiemEmail.setColumns(10);
		
		JPanel pnlNorth = new JPanel();
		centerPanel.add(pnlNorth, BorderLayout.NORTH);
		
		JLabel lblTitle = new JLabel("TÌM KIẾM NHÂN VIÊN");
		pnlNorth.add(lblTitle);
		
		JPanel pnlWest = new JPanel();
		pnlWest.setBorder(new TitledBorder(null, "Kết quả tìm kiếm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		centerPanel.add(pnlWest, BorderLayout.CENTER);
		pnlWest.setLayout(new BorderLayout(0, 0));
		
		JPanel panelAVT = new JPanel();
		panelAVT.setBorder(new EmptyBorder(10, 10, 10, 10));
		pnlWest.add(panelAVT, BorderLayout.WEST);
		panelAVT.setLayout(new BorderLayout(0, 0));
		
		lblAvt = new JLabel("");
		panelAVT.add(lblAvt, BorderLayout.NORTH);
		lblAvt.setIcon(new ImageScaler("/image_NhanVien/defaultavt.jpg" , 150, 150).getScaledImageIcon());
		lblAvt.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panelKetQuaTimKiem = new JPanel();
		pnlWest.add(panelKetQuaTimKiem, BorderLayout.CENTER);
		panelKetQuaTimKiem.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelKetQuaTimKiemLeft = new JPanel();
		panelKetQuaTimKiem.add(panelKetQuaTimKiemLeft);
		panelKetQuaTimKiemLeft.setLayout(new GridLayout(6, 0, 0, 0));
		
		JPanel panelKetQuaNV = new JPanel();
		panelKetQuaTimKiemLeft.add(panelKetQuaNV);
		panelKetQuaNV.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_10 = new JPanel();
		panelKetQuaNV.add(panel_10, BorderLayout.NORTH);
		panel_10.setLayout(new BorderLayout(0, 0));
		
		JLabel lblKetQuaMaNV = new JLabel("Mã NV");
		panel_10.add(lblKetQuaMaNV, BorderLayout.WEST);
		
		txtKetQuaMaNV = new JTextField();
		txtKetQuaMaNV.setColumns(10);
		panel_10.add(txtKetQuaMaNV);
		
		JPanel panelKetQuaNgaySinh = new JPanel();
		panelKetQuaNgaySinh.setLayout(new BorderLayout());
		panelKetQuaTimKiemLeft.add(panelKetQuaNgaySinh);
		
		JPanel panel_11 = new JPanel();
		panelKetQuaNgaySinh.add(panel_11, BorderLayout.NORTH);
		panel_11.setLayout(new BorderLayout(0, 0));
		
		JLabel lblKetQuaNgaySinh = new JLabel("Ngày sinh");
		panel_11.add(lblKetQuaNgaySinh, BorderLayout.WEST);
		
		KetQuaNgaySinh = new JXDatePicker();
		panel_11.add(KetQuaNgaySinh, BorderLayout.CENTER);
		
		JPanel panelKetQuaSDT = new JPanel();
		panelKetQuaTimKiemLeft.add(panelKetQuaSDT);
		panelKetQuaSDT.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_12 = new JPanel();
		panelKetQuaSDT.add(panel_12, BorderLayout.NORTH);
		panel_12.setLayout(new BorderLayout(0, 0));
		
		JLabel lblKetQuaSoDT = new JLabel("Số ĐT");
		panel_12.add(lblKetQuaSoDT, BorderLayout.WEST);
		
		txtKetQuaSDT = new JTextField();
		txtKetQuaSDT.setColumns(10);
		panel_12.add(txtKetQuaSDT, BorderLayout.CENTER);
		
		JPanel panelKetQuaNgayVaoLam = new JPanel();
		panelKetQuaNgayVaoLam.setLayout(new BorderLayout());
		panelKetQuaTimKiemLeft.add(panelKetQuaNgayVaoLam);
		
		JPanel panel_13 = new JPanel();
		panelKetQuaNgayVaoLam.add(panel_13, BorderLayout.NORTH);
		panel_13.setLayout(new BorderLayout(0, 0));
		
		JLabel lblKetQuaNgayVaoLam = new JLabel("Ngày vào làm");
		panel_13.add(lblKetQuaNgayVaoLam, BorderLayout.WEST);
		
		KetQuaNgayVaoLam = new JXDatePicker();
		panel_13.add(KetQuaNgayVaoLam, BorderLayout.CENTER);
		
		JPanel panelKetQuaTrangThai = new JPanel();
		panelKetQuaTimKiemLeft.add(panelKetQuaTrangThai);
		panelKetQuaTrangThai.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_14 = new JPanel();
		panelKetQuaTrangThai.add(panel_14, BorderLayout.NORTH);
		panel_14.setLayout(new BorderLayout(0, 0));
		
		JLabel lblKetQuaTrangThai = new JLabel("Trạng thái");
		panel_14.add(lblKetQuaTrangThai, BorderLayout.WEST);
		
		listKetQuaTrangThai = new JComboBox<String>();
		listKetQuaTrangThai.addItem("Đang làm");
		listKetQuaTrangThai.addItem("Đã nghỉ");
		panel_14.add(listKetQuaTrangThai, BorderLayout.CENTER);
		
		JPanel panelKetQuaTimKiemRight = new JPanel();
		panelKetQuaTimKiem.add(panelKetQuaTimKiemRight);
		panelKetQuaTimKiemRight.setLayout(new GridLayout(6, 1, 0, 0));
		
		JPanel panelKetQuaHoTen = new JPanel();
		panelKetQuaTimKiemRight.add(panelKetQuaHoTen);
		panelKetQuaHoTen.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_15 = new JPanel();
		panelKetQuaHoTen.add(panel_15, BorderLayout.NORTH);
		panel_15.setLayout(new BorderLayout(0, 0));
		
		JLabel lblKetQuaHoTen = new JLabel("Họ tên");
		panel_15.add(lblKetQuaHoTen, BorderLayout.WEST);
		
		txtKetQuaHoTen = new JTextField();
		txtKetQuaHoTen.setColumns(10);
		panel_15.add(txtKetQuaHoTen, BorderLayout.CENTER);
		
		JPanel panelKetQuaGioiTinh = new JPanel();
		panelKetQuaTimKiemRight.add(panelKetQuaGioiTinh);
		panelKetQuaGioiTinh.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_16 = new JPanel();
		panelKetQuaGioiTinh.add(panel_16, BorderLayout.NORTH);
		panel_16.setLayout(new BorderLayout(0, 0));
		
		JLabel lblKetQuaGioiTinh = new JLabel("Giới tính");
		panel_16.add(lblKetQuaGioiTinh, BorderLayout.WEST);
		
		JPanel panel_30 = new JPanel();
		panel_16.add(panel_30, BorderLayout.CENTER);
		panel_30.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		checkBoxKetQuaNam = new JRadioButton("Nam");
		panel_30.add(checkBoxKetQuaNam);
		
		checkBoxKetQuaNu = new JRadioButton("Nữ");
		panel_30.add(checkBoxKetQuaNu);
		
		groupKetQuaGioiTinh = new ButtonGroup();
		groupKetQuaGioiTinh.add(checkBoxKetQuaNam);
		groupKetQuaGioiTinh.add(checkBoxKetQuaNu);
		
		
		JPanel panelKetQuaDiaChi = new JPanel();
		panelKetQuaTimKiemRight.add(panelKetQuaDiaChi);
		panelKetQuaDiaChi.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_17 = new JPanel();
		panelKetQuaDiaChi.add(panel_17, BorderLayout.NORTH);
		panel_17.setLayout(new BorderLayout(0, 0));
		
		JLabel lblKetQuaDiaChi = new JLabel("Địa chỉ");
		panel_17.add(lblKetQuaDiaChi, BorderLayout.WEST);
		
		txtKetQuaDiaChi = new JTextField();
		txtKetQuaDiaChi.setColumns(10);
		panel_17.add(txtKetQuaDiaChi, BorderLayout.CENTER);
		
		JPanel panelKetQuaChucVu = new JPanel();
		panelKetQuaTimKiemRight.add(panelKetQuaChucVu);
		panelKetQuaChucVu.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_18 = new JPanel();
		panelKetQuaChucVu.add(panel_18, BorderLayout.NORTH);
		panel_18.setLayout(new BorderLayout(0, 0));
		
		JLabel lblKetQuaChucVu = new JLabel("Chức vụ");
		panel_18.add(lblKetQuaChucVu, BorderLayout.WEST);
		
		listKetQuaChucVu = new JComboBox<String>();
		listKetQuaChucVu.addItem("Nhân viên");
		listKetQuaChucVu.addItem("Quản lý");
		panel_18.add(listKetQuaChucVu, BorderLayout.CENTER);
		
		JPanel pnlBang = new JPanel();
		add(pnlBang, BorderLayout.CENTER);
		pnlBang.setLayout(new BorderLayout(0, 0));
		
		Box horizontalBox_11 = Box.createHorizontalBox();
		pnlBang.add(horizontalBox_11);
		
		
		
		String[] headerStrings = {"STT", "Mã nhân viên", "Họ tên", "Giới tính", "Ngày sinh", "SDT", "Email", "Địa chỉ", "CCCD", "Ngày vào làm", "Trạng thái", "Chức vụ","Ghi chú"};
		dataTableModel = new DefaultTableModel(headerStrings, 0);
		tableDanhSach = new JTable(dataTableModel);
		JScrollPane scrollTable = new JScrollPane();
		scrollTable.setViewportView(tableDanhSach);
		horizontalBox_11.add(scrollTable);
		
		lblTimKiemChucVu.setPreferredSize(lblTimKiemNgayVaoLam.getPreferredSize());
		lblTimKiemDiaChi.setPreferredSize(lblTimKiemNgayVaoLam.getPreferredSize());
		lblTimKiemHoTen.setPreferredSize(lblTimKiemNgayVaoLam.getPreferredSize());
		lblTimKiemMaNV.setPreferredSize(lblTimKiemNgayVaoLam.getPreferredSize());
		lblTimKiemNgaySinh.setPreferredSize(lblTimKiemNgayVaoLam.getPreferredSize());
		lblTimKiemSDT.setPreferredSize(lblTimKiemNgayVaoLam.getPreferredSize());
		lblTimKiemTrangThai.setPreferredSize(lblTimKiemNgayVaoLam.getPreferredSize());
		lblTimKiemEmail.setPreferredSize(lblTimKiemNgayVaoLam.getPreferredSize());
		
		JPanel panel_19 = new JPanel();
		timKiemLeft.add(panel_19);
		panel_19.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_21 = new JPanel();
		panel_19.add(panel_21, BorderLayout.NORTH);
		panel_21.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTimKiemCCCD = new JLabel("Mã CCCD");
		panel_21.add(lblTimKiemCCCD, BorderLayout.WEST);
		
		txtTimKiemCCCD = new JTextField();
		panel_21.add(txtTimKiemCCCD, BorderLayout.CENTER);
		txtTimKiemCCCD.setColumns(10);
		lblTimKiemGioiTnh.setPreferredSize(lblTimKiemNgayVaoLam.getPreferredSize());
		
		JPanel panel_20 = new JPanel();
		timKiemRight.add(panel_20);
		panel_20.add(btnTim = new RoundedButton("TÌM KIẾM", Color.GREEN, 20, 0, 1.0f));
		btnTim.setBackground(Color.GREEN);
		btnTim.setForeground(Color.WHITE);
		btnTim.setFont(new Font("Roboto", Font.BOLD, 20));
		
		lblKetQuaChucVu.setPreferredSize(lblKetQuaNgayVaoLam.getPreferredSize());
		lblKetQuaDiaChi.setPreferredSize(lblKetQuaNgayVaoLam.getPreferredSize());
		lblKetQuaGioiTinh.setPreferredSize(lblKetQuaNgayVaoLam.getPreferredSize());
		lblKetQuaHoTen.setPreferredSize(lblKetQuaNgayVaoLam.getPreferredSize());
		lblTimKiemCCCD.setPreferredSize(lblKetQuaNgayVaoLam.getPreferredSize());
		
		JPanel panel_24 = new JPanel();
		panelKetQuaTimKiemRight.add(panel_24);
		panel_24.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_27 = new JPanel();
		panel_24.add(panel_27, BorderLayout.NORTH);
		panel_27.setLayout(new BorderLayout(0, 0));
		
		JLabel lblKetQuaEmail = new JLabel("Email");
		panel_27.add(lblKetQuaEmail, BorderLayout.WEST);
		
		txtKetQuaEmail = new JTextField();
		txtKetQuaEmail.setText("");
		panel_27.add(txtKetQuaEmail, BorderLayout.CENTER);
		txtKetQuaEmail.setColumns(10);
		
		JPanel panel_25 = new JPanel();
		panelKetQuaTimKiemRight.add(panel_25);
		panel_25.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_28 = new JPanel();
		panel_25.add(panel_28, BorderLayout.NORTH);
		panel_28.setLayout(new BorderLayout(0, 0));
		
		JLabel lblKetQuaGhiChu = new JLabel("Ghi chú");
		panel_28.add(lblKetQuaGhiChu, BorderLayout.WEST);
		
		txtKetQuaGhiChu = new JTextField();
		txtKetQuaGhiChu.setText("");
		panel_28.add(txtKetQuaGhiChu, BorderLayout.CENTER);
		txtKetQuaGhiChu.setColumns(10);
		lblKetQuaMaNV.setPreferredSize(lblKetQuaNgayVaoLam.getPreferredSize());
		lblKetQuaNgaySinh.setPreferredSize(lblKetQuaNgayVaoLam.getPreferredSize());
		lblKetQuaSoDT.setPreferredSize(lblKetQuaNgayVaoLam.getPreferredSize());
		lblKetQuaTrangThai.setPreferredSize(lblKetQuaNgayVaoLam.getPreferredSize());
		
		JPanel panel_23 = new JPanel();
		panelKetQuaTimKiemLeft.add(panel_23);
		panel_23.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_26 = new JPanel();
		panel_23.add(panel_26, BorderLayout.NORTH);
		panel_26.setLayout(new BorderLayout(0, 0));
		
		JLabel lblKetQuaCCCD = new JLabel("Mã CCCD");
		panel_26.add(lblKetQuaCCCD, BorderLayout.WEST);
		
		txtKetQuaCCCD = new JTextField();
		txtKetQuaCCCD.setText("");
		panel_26.add(txtKetQuaCCCD, BorderLayout.CENTER);
		txtKetQuaCCCD.setColumns(10);
		
		lblKetQuaCCCD.setPreferredSize(lblTimKiemNgayVaoLam.getPreferredSize());
		lblKetQuaEmail.setPreferredSize(lblTimKiemNgayVaoLam.getPreferredSize());
		lblKetQuaGhiChu.setPreferredSize(lblTimKiemNgayVaoLam.getPreferredSize());
		
		txtKetQuaCCCD.setEditable(false);
		txtKetQuaDiaChi.setEditable(false);
		txtKetQuaEmail.setEditable(false);
		txtKetQuaGhiChu.setEditable(false);
		txtKetQuaHoTen.setEditable(false);
		txtKetQuaMaNV.setEditable(false);
		KetQuaNgaySinh.setEditable(false);
		txtKetQuaSDT.setEditable(false);
		KetQuaNgayVaoLam.setEditable(false);
		listKetQuaChucVu.setEditable(false);
		listKetQuaTrangThai.setEditable(false);
		checkBoxKetQuaNam.setEnabled(false);
		checkBoxKetQuaNu.setEnabled(false);
		
		btnTim.addActionListener(this);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object event = e.getSource();
		if(event == tableDanhSach) {
			int selectedItem = tableDanhSach.getSelectedRow();
			if(selectedItem != -1) {
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
		Object event = e.getSource();
		if(event.equals(btnTim)) {
			timAction();
		}
	}
	

	private void hienThiThongTinNhanVien(int selectedItem) {
		txtKetQuaMaNV.setText(listNV.get(selectedItem).getMaNV());
		txtKetQuaHoTen.setText(listNV.get(selectedItem).getTenNV());
		checkBoxKetQuaNam.setSelected(listNV.get(selectedItem).isGioiTinh() == true);
		checkBoxKetQuaNu.setSelected(listNV.get(selectedItem).isGioiTinh() == false);
		KetQuaNgaySinh.setDate(listNV.get(selectedItem).getNgaySinh());
		txtKetQuaSDT.setText(listNV.get(selectedItem).getSDT());
		txtKetQuaEmail.setText(listNV.get(selectedItem).getEmail());
		txtKetQuaCCCD.setText(listNV.get(selectedItem).getMaCCCD());
		txtKetQuaDiaChi.setText(listNV.get(selectedItem).getDiaChi());
		txtKetQuaGhiChu.setText(listNV.get(selectedItem).getGhiChu());
		KetQuaNgayVaoLam.setDate(listNV.get(selectedItem).getNgayVaoLam());
		listKetQuaTrangThai.setSelectedIndex(listNV.get(selectedItem).isTrangThai() == true ? 0 : 1);
		listKetQuaChucVu.setSelectedIndex(listNV.get(selectedItem).getChucVu());
		if(listNV.get(selectedItem).getAvtString() == null) {
			lblAvt.setIcon(new ImageScaler("defaultavt.jpg", 150, 150).getScaledImageIcon());
		}
		else {
			lblAvt.setIcon(new ImageScaler("/image_NhanVien/" + listNV.get(selectedItem).getAvtString() , 150, 150).getScaledImageIcon());
		}
	}
	
	private void timAction() {
		// TODO Auto-generated method stub
		NhanVien nvTim = convertDataToNhanVien();
		if(nvTim != null) {
			ArrayList<NhanVien> listKetQuaTimKiem = new ArrayList<NhanVien>();
			listKetQuaTimKiem = nv_dao.timNhanVien(nvTim.getMaNV(), nvTim.getTenNV(), nvTim.isGioiTinh(), nvTim.getNgaySinh(), nvTim.getSDT(), nvTim.getEmail(), nvTim.getMaCCCD(), nvTim.getDiaChi(), nvTim.getNgayVaoLam(), nvTim.isTrangThai(), nvTim.getChucVu());
//			System.out.println("check tim action: " + nvTim.toString());
			if(nvTim.getTenNV() == null) {
				System.out.println("ĐÂY LÀ NULL");}
//			}else if(nvTim.getTenNV().isEmpty()){
//				System.out.println("ĐÂY LÀ ISEMPTY");
//			}
//			else if(nvTim.getTenNV().isBlank()) {
//				System.out.println("ĐÂY LÀ BLANK");
//			}
			else {
				//System.out.println("ĐÂY KO LÀ CÁI GÌ CẢ");
			}
			if(listKetQuaTimKiem != null) {
				themAllNhanVienVaoBang(listKetQuaTimKiem);
				JOptionPane.showMessageDialog(null, "Tìm thấy " + dataTableModel.getRowCount() + " nhân viên thỏa điều kiện.");
			}
			else {
				JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên nào thỏa điều kiện.");
			}
		}
	}
	
//	private void timAction() {
//		// TODO Auto-generated method stub
//		NhanVien nvTim = convertDataToNhanVien();
//		
////		String maNV = txtTimKiemMaNV.getText();
////		String hoTen = txtTimKiemHoTen.getText();
////		Date ngaySinh = null;
////		if(TimKiemNgaySinh.getDate() != null) {
////			ngaySinh = new Date(TimKiemNgaySinh.getDate().getTime());
////		}
////		boolean gioiTinh;
////		if(checkBoxTimKiemNam.isSelected() == true)
////			gioiTinh = true;
////		else
////			gioiTinh = false;
////		String sdt = txtTimKiemSDT.getText();
////		String diaChi = txtTimKiemDiaChi.getText();
////		Date ngayVaoLam = null;
////		if(TimKiemNgayVaoLam.getDate() != null) {
////			ngayVaoLam = new Date(TimKiemNgayVaoLam.getDate().getTime());
////		}
////		int chucVu = listChucVuTimKiem.getSelectedIndex();
////		boolean trangThai = listTrangThaiTimKiem.getSelectedIndex() == 1;
////		String email = txtTimKiemEmail.getText();
////		String maCCCD = txtTimKiemCCCD.getText();
////		String ghiChu = "";
////		String urlAnh = "";
////		
////		NhanVien nVien = new NhanVien(maNV, hoTen, hoTen, gioiTinh, ngaySinh, sdt, email, maCCCD, diaChi, ngayVaoLam, ghiChu, trangThai, chucVu, urlAnh);
//		listKetQuaTimKiem = new NhanVien_DAO().timNhanVien(maNV, hoTen, gioiTinh, ngaySinh, sdt, email,maCCCD, diaChi, ngayVaoLam, trangThai,chucVu);
//
//		
////		
////		
////		
////		if(listKetQuaTimKiem.size()!=0) {
////			themAllNhanVienVaoBang(listKetQuaTimKiem);
////			JOptionPane.showMessageDialog(null, "Tìm thấy " + dataTableModel.getRowCount() + " nhân viên thỏa điều kiện.");
////		}else {
////			themAllNhanVienVaoBang(listKetQuaTimKiem);
////			JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên nào thỏa điều kiện.");
////		}
////		
//		
//		if(nvTim != null) {
//			
//			//listKetQuaTimKiem = nv_dao.timNhanVien(nvTim.getMaNV(), nvTim.getTenNV(), nvTim.isGioiTinh(), nvTim.getNgaySinh(), nvTim.getSDT(), nvTim.getEmail(), nvTim.getMaCCCD(), nvTim.getDiaChi(), nvTim.getNgayVaoLam(), nvTim.isTrangThai(), nvTim.getChucVu());
//			System.out.println("check tim action: " + nvTim.toString());
//			
//			if(listKetQuaTimKiem != null) {
//				themAllNhanVienVaoBang(listKetQuaTimKiem);
//				JOptionPane.showMessageDialog(null, "Tìm thấy " + dataTableModel.getRowCount() + " nhân viên thỏa điều kiện.");
//			}
//			else {
//				JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên nào thỏa điều kiện.");
//			}
//		}
//	}

	private NhanVien convertDataToNhanVien() {
		String maNV = txtTimKiemMaNV.getText();
		String hoTen = txtTimKiemHoTen.getText();
		Date ngaySinh = null;
		if(TimKiemNgaySinh.getDate() != null) {
			ngaySinh = new Date(TimKiemNgaySinh.getDate().getTime());
		}
		boolean gioiTinh = checkBoxTimKiemNam.isSelected();
		String sdt = txtTimKiemSDT.getText();
		String diaChi = txtTimKiemDiaChi.getText();
		Date ngayVaoLam = null;
		if(TimKiemNgayVaoLam.getDate() != null) {
			ngayVaoLam = new Date(TimKiemNgayVaoLam.getDate().getTime());
		}
		int chucVu = listChucVuTimKiem.getSelectedIndex();
		boolean trangThai = listTrangThaiTimKiem.getSelectedIndex() == 1;
		String email = txtTimKiemEmail.getText();
		String maCCCD = txtTimKiemCCCD.getText();
		String ghiChu = "";
		String urlAnh = "";
		return new NhanVien(maNV, hoTen,  gioiTinh, ngaySinh, sdt, email, maCCCD, diaChi, ngayVaoLam, ghiChu, trangThai, chucVu, urlAnh);
	}
	
	private void themAllNhanVienVaoBang(ArrayList<NhanVien> list) {
		dataTableModel.setRowCount(0);
		for(NhanVien nv: list) {
			themNhanVienVaoBang(nv);
		}
	}
	
	private void themNhanVienVaoBang(NhanVien nv) {
		String[] row = new String[13];
		row[0] = String.valueOf(dataTableModel.getRowCount() + 1);
		row[1] = nv.getMaNV();
		row[2] = nv.getTenNV();
		row[3] = nv.isGioiTinh() == true ? "Nam" : "Nữ";
//		row[4] = nv.getNgaySinh().toString();
		row[5] = nv.getSDT();
		row[6] = nv.getEmail();
		row[7] = nv.getDiaChi();
		row[8] = nv.getMaCCCD();
//		row[9] = nv.getNgayVaoLam().toString();
		row[10]= nv.isTrangThai() == true ? "Đang làm" : "Đã nghỉ";
		row[11]= listChucVuTimKiem.getItemAt(nv.getChucVu()).toString();
		row[12]= nv.getGhiChu();
		dataTableModel.addRow(row);
 	}
	
}