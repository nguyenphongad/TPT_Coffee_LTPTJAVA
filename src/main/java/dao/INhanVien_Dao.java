package dao;

import entity.NhanVien;
import entity.TaiKhoan;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;

public interface INhanVien_Dao extends Remote {
    public String getMaNVTuDong() throws RemoteException;
    public boolean themNhanVien(NhanVien nv, TaiKhoan tKhoan) throws RemoteException;
    public ArrayList<NhanVien> getAllNhanVien() throws RemoteException;
    public boolean suaNhanVien(NhanVien nv, TaiKhoan tk) throws RemoteException;
    public boolean xoaNhanVien(String maNV) throws RemoteException;
    public ArrayList<NhanVien> timNhanVien(String maNV, String tenNV, Boolean gioiTinh, Date ngaySinh, String sDT, String email, String maCCCD, String diaChi, Date ngayVaoLam, Boolean trangThai, Integer chucVu) throws RemoteException;
    public int laySoLuongNhanVien() throws RemoteException;
    public NhanVien getNhanVienTheoMa(String maNhanVien) throws RemoteException;

}
