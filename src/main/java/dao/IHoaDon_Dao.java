package dao;

import entity.ChiTietHoaDon;
import entity.HoaDon;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IHoaDon_Dao extends Remote {
    public List<HoaDon> getAllHoaDon() throws RemoteException;
    public HoaDon getHoaDontheoMa(String maHD) throws RemoteException;
    public int tongSoHoaDonTheoThang(int thang) throws RemoteException;
    public double tongDoanhThuTheoTungNgayTrongThang(int day, int month) throws RemoteException;
    public String getMaHDTuDong() throws RemoteException;
    public boolean themHoaDon(HoaDon hd) throws RemoteException;
    public boolean themChiTietHoaDon(ChiTietHoaDon cthd) throws RemoteException;
    public boolean congDiemVaoKH(int diem, String sdt) throws RemoteException;
}
