package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entity.PhieuDat;
import entity.TourDuLich;
import util.Util;

public class PhieuDatDao {

	public ArrayList<PhieuDat> getAllPhieuDat() {
		ArrayList<PhieuDat> listPhieu = new ArrayList<PhieuDat>();
		String query = "select * from phieudat";
		try {
			Connection conn = DriverManager.getConnection(Util.DB_URL, Util.USER, Util.PASS);
			PreparedStatement pstm = conn.prepareStatement(query);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				int maDat = rs.getInt("MaDat");
				int maTour = rs.getInt("MaTour");
				String tenTour = rs.getString("tenTour");
				int maKhach = rs.getInt("MaKh");
				String tenKhach = rs.getNString("TenKh");
				
				PhieuDat phieu = new PhieuDat(maDat, maTour, tenTour, maKhach, tenKhach);
				listPhieu.add(phieu);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return listPhieu;
	}

	public boolean datPhieu(PhieuDat phieu) {
		boolean result = false;
		String query = "INSERT INTO phieudat(MaTour, tenTour, MaKh, TenKh) VALUES (" 
				+ phieu.getMaTour() + ", '" + phieu.getTenTour() + "', "
				+ phieu.getMaKH() + ", '" + phieu.getTenKH() + "')";
		try {
			Connection conn = DriverManager.getConnection(Util.DB_URL, Util.USER, Util.PASS);
			PreparedStatement pstm = conn.prepareStatement(query);
			int rs = pstm.executeUpdate();
			if(rs>0)
				result=true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
