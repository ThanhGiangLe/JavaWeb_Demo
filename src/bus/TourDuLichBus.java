package bus;

import java.util.ArrayList;

import dao.TourDuLichDao;
import entity.TourDuLich;

public class TourDuLichBus {
	TourDuLichDao tourDuLichDao = new TourDuLichDao();
	
	public ArrayList<TourDuLich> getAllTour(){
		return tourDuLichDao.getAllTour();
	}
	public boolean insertTour(TourDuLich tour) {
		return tourDuLichDao.insertTour(tour);
	}
	public boolean updateTour(TourDuLich tour) {
		return tourDuLichDao.updateTour(tour);
	}
	public boolean deleteTour(int maTour) {
		return tourDuLichDao.deleteTour(maTour);
	}
}
