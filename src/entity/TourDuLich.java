package entity;

import java.sql.Date;

public class TourDuLich {
	private int maTour;
	private String tenTour;
	private String tenDiaDiem;
	private float gia;
	private Date startDate;
	private Date endDate;
	public TourDuLich() {
		super();
	}
	public TourDuLich(String tenTour, String tenDiaDiem, float gia, Date startDate, Date endDate) {
		super();
		this.tenTour = tenTour;
		this.tenDiaDiem = tenDiaDiem;
		this.gia = gia;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public TourDuLich(int maTour, String tenTour, String tenDiaDiem, float gia, Date startDate, Date endDate) {
		super();
		this.maTour = maTour;
		this.tenTour = tenTour;
		this.tenDiaDiem = tenDiaDiem;
		this.gia = gia;
		this.startDate = startDate;
		this.endDate = endDate;
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
	public String getTenDiaDiem() {
		return tenDiaDiem;
	}
	public void setTenDiaDiem(String tenDiaDiem) {
		this.tenDiaDiem = tenDiaDiem;
	}
	public float getGia() {
		return gia;
	}
	public void setGia(float gia) {
		this.gia = gia;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "TourDuLich [maTour=" + maTour + ", tenTour=" + tenTour + ", tenDiaDiem=" + tenDiaDiem + ", gia=" + gia
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
}
