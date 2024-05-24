package entity;

public class PhieuDat {
	private int maDat;
	private int maTour;
	private String tenTour;
	private int maKH;
	private String tenKH;
	public PhieuDat() {
		super();
	}
	public PhieuDat(int maTour, String tenTour, int maKH, String tenKH) {
		super();
		this.maTour = maTour;
		this.tenTour = tenTour;
		this.maKH = maKH;
		this.tenKH = tenKH;
	}
	public PhieuDat(int maDat, int maTour, String tenTour, int maKH, String tenKH) {
		super();
		this.maDat = maDat;
		this.maTour = maTour;
		this.tenTour = tenTour;
		this.maKH = maKH;
		this.tenKH = tenKH;
	}
	public int getMaDat() {
		return maDat;
	}
	public void setMaDat(int maDat) {
		this.maDat = maDat;
	}
	public int getMaTour() {
		return maTour;
	}
	public void setMaTour(int maTour) {
		this.maTour = maTour;
	}
	public String getTenTour() {
		return tenTour;
	}
	public void setTenTour(String tenTour) {
		this.tenTour = tenTour;
	}
	public int getMaKH() {
		return maKH;
	}
	public void setMaKH(int maKH) {
		this.maKH = maKH;
	}
	public String getTenKH() {
		return tenKH;
	}
	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}
	@Override
	public String toString() {
		return "PhieuDat [maDat=" + maDat + ", maTour=" + maTour + ", tenTour=" + tenTour + ", maKH=" + maKH
				+ ", tenKH=" + tenKH + "]";
	}
	
}
