package dao;

import entity.KhachHang;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IKhachHang_Dao extends Remote {
    public KhachHang getKhachHangTheoSDT(String sdt) throws RemoteException;
    public ArrayList<KhachHang> timSoDienThoaiKhachHang(String sdt) throws RemoteException;
    public boolean dangKySdtTichDiem(KhachHang khnew) throws RemoteException;
}
