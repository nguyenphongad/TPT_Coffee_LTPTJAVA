package dao;

import entity.TaiKhoan;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ILogin_Dao extends Remote {
    public List<TaiKhoan> getTaiKhoan() throws RemoteException;
    public TaiKhoan kiemTraDangNhap(String maNV, String matKhau) throws RemoteException;

}
