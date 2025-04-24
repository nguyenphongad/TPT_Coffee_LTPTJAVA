package server;

import dao.*;
import dao.impl.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static final String SERVER_NAME = "rmi://localhost:1099/";

    public static void main(String[] args) throws NamingException, RemoteException {
        try {
            InitialContext Naming = new InitialContext();
            IChiTietHoaDon_Dao chiTietHoaDonDao = new ChiTietHoaDon_DAO();
            IHoaDon_Dao hoaDonDao = new HoaDon_DAO();
            IKhachHang_Dao khachHangDao = new KhachHang_DAO();
            ILogin_Dao loginDao = new Login_DAO();
            INhanVien_Dao nhanVienDao = new NhanVien_DAO();
            ISanPham_Dao sanPhamDao = new SanPham_DAO();
            ITaiKhoan_Dao taiKhoanDao = new TaiKhoan_DAO();

            LocateRegistry.createRegistry(1099);

            Naming.rebind(SERVER_NAME + "ChiTietHoaDonDao", chiTietHoaDonDao);
            Naming.rebind(SERVER_NAME + "HoaDonDao", hoaDonDao);
            Naming.rebind(SERVER_NAME + "KhachHangDao", khachHangDao);
            Naming.rebind(SERVER_NAME + "LoginDao", loginDao);
            Naming.rebind(SERVER_NAME + "NhanVienDao", nhanVienDao);
            Naming.rebind(SERVER_NAME + "SanPhamDao", sanPhamDao);
            Naming.rebind(SERVER_NAME + "TaiKhoanDao", taiKhoanDao);

            System.out.println("Server is running on port 1099");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
