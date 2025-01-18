package util;

public class SanPhamForm {
	private String stt;
	private String maSP;
	private String tenSP;
	private double donGia;
	
	
	
	
	
	
	public SanPhamForm(String stt, String maSP, String tenSP, double donGia) {
		super();
		this.stt = stt;
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.donGia = donGia;
	}
	public String getStt() {
		return stt;
	}
	public void setStt(String stt) {
		this.stt = stt;
	}
	public String getMaSP() {
		return maSP;
	}
	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}
	public String getTenSP() {
		return tenSP;
	}
	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	
	
	
	
	
}
