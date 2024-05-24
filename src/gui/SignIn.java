package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import bus.UserBus;
import entity.User;
import util.Util;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JPasswordField;

public class SignIn extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public  static JTextField txtEmail;
	private UserBus userBus = new UserBus();
	private User userKhach = new User();
	private User userNhanVien = new User();
	public static JPasswordField txtPassword;

	/**
	 * Create the frame.
	 */
	public SignIn() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 521, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Đăng Nhập");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(180, 10, 168, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(69, 51, 66, 29);
		contentPane.add(lblNewLabel_1);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtEmail.setBounds(146, 51, 324, 32);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("PassWord");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(26, 114, 109, 29);
		contentPane.add(lblNewLabel_1_1);
		
		JButton btnDK = new JButton("Đăng ký");
		btnDK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUp frame = new SignUp();
				frame.setVisible(true);
				
				dispose();
			}
		});
		btnDK.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDK.setBounds(81, 174, 164, 40);
		contentPane.add(btnDK);
		
		JButton btnDN = new JButton("Đăng nhập");
		btnDN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = txtEmail.getText();
				String passWord = txtPassword.getText();
				
				if (userBus.DangNhap(userName, passWord) == 1 && userName.contains(".employee")) {
					userNhanVien = getAllInfoUser();
					updateLastLogin();
					NhanVienForm frame = new NhanVienForm(userNhanVien);
					frame.setVisible(true);
					dispose();
				} 
				else if(userBus.DangNhap(userName, passWord) == 1) {
					userKhach = getAllInfoUser();
					updateLastLogin();
					System.out.println("SignIn: " + userKhach);
					KhachHangForm frame = new KhachHangForm(userKhach);
					frame.setVisible(true);
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "Thong tin khong hop le");
					txtEmail.setText("");
					txtPassword.setText("");
				}
			}
		});
		btnDN.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDN.setBounds(284, 174, 164, 40);
		contentPane.add(btnDN);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(146, 111, 324, 32);
		contentPane.add(txtPassword);
	}
	public static User getAllInfoUser() {
		User temp = null;
		System.out.println("getAllUser: " + txtPassword.getText());
		String query = "SELECT * FROM users where email = '" + txtEmail.getText() + "' and password_hash = '" + txtPassword.getText() + "'";

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
	public static void updateLastLogin() {
		String email = txtEmail.getText().toString();
		String passWord = txtPassword.getPassword().toString();
		
        String query = "Update users set last_login = '" + java.time.LocalDate.now() + "' where email = '" + email + "' and password_hash = '" + passWord + "'";

        try{
        	Connection conn = DriverManager.getConnection(Util.DB_URL, Util.USER, Util.PASS);
        	PreparedStatement pstmt = conn.prepareStatement(query);
            int rs = pstmt.executeUpdate();
        }catch(Exception e) {
        	e.printStackTrace();
        }
	}
}
