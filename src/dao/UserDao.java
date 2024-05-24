package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import entity.User;
import util.Email;
import util.Util;

public class UserDao {
	public ArrayList<User> getAllUser() {
		ArrayList<User> users = new ArrayList<>();
		
        String query = "SELECT * FROM users";

        try (Connection conn =  DriverManager.getConnection(Util.DB_URL, Util.USER, Util.PASS);
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs =  pstmt.executeQuery()) {
            
            while (rs.next()) {
                int user_id = rs.getInt("user_id");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String password_hash = rs.getString("password_hash");
                Date created_at = rs.getDate("created_at");
                Date last_login = rs.getDate("last_login");
                String sdt = rs.getString("sdt");
                User user = new User(user_id,username,email,password_hash,created_at,last_login, sdt);
                users.add(user);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
		
		return users;
	}
	
	public int DangNhap(String emailuser, String pass ) {
		Map<String, String> account = new HashMap<String, String>();
		int success = 0;
		
        String query = "SELECT email , password_hash FROM users";

        try (Connection conn =  DriverManager.getConnection(Util.DB_URL, Util.USER, Util.PASS);
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs =  pstmt.executeQuery()) {
            
            while (rs.next()) {
                String email = rs.getString("email");
                String password_hash = rs.getString("password_hash");
                
                account.put(email, password_hash);
            }
    		for (Map.Entry<String, String> item : account.entrySet()) {
                if(item.getKey().toString().equals(emailuser)) {
                	if(item.getValue().toString().equals(pass)) {
                		success = 1;
                	}
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;	
	}
	
	public static String generateRandomOTP() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(characters.length());

        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }

        return sb.toString();
    }
	public int DangKy(String userName,String emailUser,String pass, String phone) {
		int success = 0;
		String otpRandom = generateRandomOTP();
		ArrayList<String> emailList = new ArrayList<String>();
        String query = "SELECT email FROM users";
        try (Connection conn =  DriverManager.getConnection(Util.DB_URL, Util.USER, Util.PASS);
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs =  pstmt.executeQuery()) {
            while (rs.next()) {
                String email = rs.getString("email");
                emailList.add(email);  
            }
            for (String item:emailList) {
            	if(item.equals(emailUser)) {
            		return success;
            	}
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query2 = "INSERT INTO users (username, email, password_hash, created_at, last_login, otp, sdt) VALUES ('"
        		+ userName + "', '" + emailUser + "', '" + pass + "', '" + java.time.LocalDate.now() + "', '" + java.time.LocalDate.now() + "', '"
        		+ otpRandom + "', '" + phone + "')";
        try {
        	Connection conn = DriverManager.getConnection(Util.DB_URL, Util.USER, Util.PASS);
        	PreparedStatement pstmt = conn.prepareStatement(query2);
            int rs = pstmt.executeUpdate();
            if(rs > 0) {
            	success = 1;
            }
            Email.sendEmail(emailUser, "Mã xác thực OTP", otpRandom);
        }catch(Exception e) {
        	e.printStackTrace();
        }
        return success;	
	}
	
	public String getOTPofEmailUser(String emailUser) {
		String otp = "";
		String query = "SELECT otp FROM users where email = '" + emailUser + "'";
        try (Connection conn =  DriverManager.getConnection(Util.DB_URL, Util.USER, Util.PASS);
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs =  pstmt.executeQuery()) {
            
            while (rs.next()) {
                otp = rs.getString("otp");     
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return otp;
	}

	public String getUserEmailByOtp(String otp) {
		String userMail = "";
		String query = "SELECT email FROM users where otp = '" + otp + "'";
        try (Connection conn =  DriverManager.getConnection(Util.DB_URL, Util.USER, Util.PASS);
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs =  pstmt.executeQuery()) {
            
            while (rs.next()) {
            	userMail = rs.getString("email");     
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userMail;
	}

	public User getAllInfoUser(String email, String pass) {
		User temp = null;
		
        String query = "SELECT * FROM users where email = '" + email + "' and password_hash = '" + pass + "'";

        try (Connection conn =  DriverManager.getConnection(Util.DB_URL, Util.USER, Util.PASS);
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs =  pstmt.executeQuery()) {
            
            if (rs.next()) {
                int usrs_id = rs.getInt("user_id");
                String username = (rs.getString("username"));
                String emailsub = (rs.getString("email"));
                String password = (rs.getString("password_hash"));
                Date created_at = (rs.getDate("created_at"));
                Date last_login = (rs.getDate("last_login"));
                String sdt = rs.getString("sdt");
                temp = new User(usrs_id, password, username, emailsub, created_at, last_login, sdt);
            }
        }catch(Exception e) {
        	e.printStackTrace();
        }
        return temp;
	}
}
