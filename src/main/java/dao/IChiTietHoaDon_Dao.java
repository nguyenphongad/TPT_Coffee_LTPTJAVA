package dao;

import entity.ChiTietHoaDon;

import javax.swing.table.DefaultTableModel;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IChiTietHoaDon_Dao extends Remote {
    public List<ChiTietHoaDon> getChiTietHoaDonTheoMaHoaDon(String maHoaDon) throws RemoteException;
    public DefaultTableModel thongKeSanPhamBanChay() throws RemoteException;

}
