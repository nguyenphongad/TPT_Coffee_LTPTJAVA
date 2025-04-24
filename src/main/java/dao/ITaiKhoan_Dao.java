package dao;

import entity.TaiKhoan;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ITaiKhoan_Dao extends Remote {
    public ArrayList<TaiKhoan> getAllTaiKhoan() throws RemoteException;
}
