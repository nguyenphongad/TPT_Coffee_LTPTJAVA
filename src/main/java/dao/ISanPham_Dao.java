package dao;

import entity.SanPham;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ISanPham_Dao extends Remote {
    public SanPham getSanPhamtheoMa(String maSP) throws RemoteException;
    public String getMaSPTuDong() throws RemoteException;
    public boolean themSanPham(SanPham sp) throws RemoteException;
    public boolean suaSP(SanPham sp) throws RemoteException;
    public boolean xoaSP(String maSP) throws RemoteException;
    public boolean kiemTraMaSP(String maSP) throws RemoteException;
    public ArrayList<SanPham> getAllSanPham() throws RemoteException;
    public ArrayList<SanPham> timKiemSPTheoMavaTen(String value) throws RemoteException;
    public ArrayList<SanPham> timKiemSPTheoLoai(String loai) throws RemoteException;
    public ArrayList<SanPham> timKiemSPTheoTrangThai(int tt) throws RemoteException;
}
