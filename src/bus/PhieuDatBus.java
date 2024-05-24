package bus;

import java.util.ArrayList;

import dao.PhieuDatDao;
import entity.PhieuDat;
import entity.TourDuLich;

public class PhieuDatBus {
	PhieuDatDao phieuDatDao = new PhieuDatDao();
	public ArrayList<PhieuDat> getAllPhieuDat(){
		return phieuDatDao.getAllPhieuDat();
	}
	public boolean datPhieu(PhieuDat phieu) {
		return phieuDatDao.datPhieu(phieu);
	}
}
