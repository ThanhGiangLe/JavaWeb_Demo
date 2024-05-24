package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.cj.xdevapi.PreparableStatement;

import entity.TourDuLich;
import util.Util;

public class TourDuLichDao {

	public ArrayList<TourDuLich> getAllTour() {
		ArrayList<TourDuLich> listTour = new ArrayList<TourDuLich>();
		String query = "select * from tourdulich";
		try {
			Connection conn = DriverManager.getConnection(Util.DB_URL, Util.USER, Util.PASS);
			PreparedStatement pstm = conn.prepareStatement(query);
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) {
				int maTour = rs.getInt("MaTour");
				String tenTour = rs.getString("tenTour");
				String diaDiem = rs.getString("tenDiaDiem");
				float gia = rs.getFloat("Gia");
				Date start = rs.getDate("StartDate");
				Date end = rs.getDate("EndDate");
				
				TourDuLich tour = new TourDuLich(maTour, tenTour, diaDiem, gia, start, end);
				listTour.add(tour);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return listTour;
	}

	public boolean insertTour(TourDuLich tour) {
		boolean result = false;
		String query = "INSERT INTO tourdulich(tenTour, tenDiaDiem, Gia, StartDate, EndDate) VALUES ('"
				+ tour.getTenTour() + "', '" + tour.getTenDiaDiem() + "', "
				+ tour.getGia() + ", '" + tour.getStartDate() + "', '" 
				+ tour.getEndDate() + "')";
		try {
			Connection conn = DriverManager.getConnection(Util.DB_URL, Util.USER, Util.PASS);
			PreparedStatement pstm = conn.prepareStatement(query);
			int rs = pstm.executeUpdate();
			if(rs>0) {
				result=true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean updateTour(TourDuLich tour) {
		boolean result = false;
		String query = "update tourdulich set tenTour = '" + tour.getTenTour() 
				+ "', tenDiaDiem = '" + tour.getTenDiaDiem() 
				+ "', Gia = " + tour.getGia()
				+ ", StartDate = '" + tour.getStartDate()
				+ "', EndDate = '" + tour.getEndDate() 
				+ "' where maTour = " + tour.getMaTour();
		try {
			Connection conn = DriverManager.getConnection(Util.DB_URL, Util.USER, Util.PASS);
			PreparedStatement pstm = conn.prepareStatement(query);
			int rs = pstm.executeUpdate();
			if(rs>0) {
				result=true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean deleteTour(int maTour) {
		delete(maTour);
		boolean result = false;
		String query = "delete from tourdulich where maTour = " + maTour;
		try {
			Connection conn = DriverManager.getConnection(Util.DB_URL, Util.USER, Util.PASS);
			PreparedStatement pstm = conn.prepareStatement(query);
			int rs = pstm.executeUpdate();
			if(rs>0) {
				result=true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public void delete(int maTour) {
		String query = "delete from phieudat where maTour = " + maTour;
		try {
			Connection conn = DriverManager.getConnection(Util.DB_URL, Util.USER, Util.PASS);
			PreparedStatement pstm = conn.prepareStatement(query);
			int rs = pstm.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
