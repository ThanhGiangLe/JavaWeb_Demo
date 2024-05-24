package bus;
import java.util.ArrayList;

import dao.UserDao;
import entity.User;
public class UserBus {
	UserDao UserDao = new UserDao();
	
	public ArrayList<User> getAllUser() {
		return UserDao.getAllUser();
	}
	public User getAllInfoUser(String email, String pass) {
		return UserDao.getAllInfoUser(email, pass);
	}
	public int DangNhap(String emailUser,String pass) {
		return UserDao.DangNhap(emailUser,pass);
	}
	
	public int DangKy(String name,String emailUser,String pass, String phone) {
		return UserDao.DangKy(name,emailUser,pass, phone);
	}
	public String getOTPofEmailUser(String emailUser) {
		return UserDao.getOTPofEmailUser(emailUser);
	}
	public String getUserEmailByOtp(String otp) {
		return UserDao.getUserEmailByOtp(otp);
	}
}
