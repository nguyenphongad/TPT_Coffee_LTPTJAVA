package util;

import dao.HoaDon_DAO;
import dao.SanPham_DAO;

public class SinhMaTuDong_UTIL {
	// sinh ma HD tu dong
	public String sinhMaHD() {
		String maFHD = "HD";
		String maHDMax = new HoaDon_DAO().getMaHDTuDong();
		if(maHDMax == null || maHDMax.length() < 9) {
			maFHD = maFHD + "0000001";
		}else {
			try {
				int ma = Integer.parseInt(maHDMax.substring(2)) + 1;
				maFHD = maFHD + String.format("%07d", ma);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		return maFHD;
	}
	
	
	// sinh ma SP tu dong
		public String sinhMaSP() {
			String maFSP = "SP";
			String maSPMax = new SanPham_DAO().getMaSPTuDong();
			if(maSPMax == null || maSPMax.length() < 7) {
				maFSP = maFSP + "00001";
			}else {
				try {
					int ma = Integer.parseInt(maSPMax.substring(2)) + 1;
					maFSP = maFSP + String.format("%05d", ma);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
			return maFSP;
		}
}
