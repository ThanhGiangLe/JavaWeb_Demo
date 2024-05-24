package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bus.UserBus;
import dao.UserDao;
import entity.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JPasswordField;

public class SignUp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtEmail;
	private ArrayList<User> dsUser = new ArrayList<User>();
	private UserBus userBus = new UserBus();
	private UserDao userDao = new UserDao();
	private JPasswordField txtPass;
	private JTextField txtPhone;
	
	/**
	 * Create the frame.
	 */
	public SignUp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 505, 332);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Đăng ký");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(217, 10, 135, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tên tài khoản");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(10, 73, 140, 22);
		contentPane.add(lblNewLabel_1);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtName.setBounds(160, 64, 312, 31);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Email");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(10, 114, 140, 22);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Password");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(10, 155, 140, 22);
		contentPane.add(lblNewLabel_1_2);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtEmail.setColumns(10);
		txtEmail.setBounds(160, 105, 312, 31);
		contentPane.add(txtEmail);
		
		JButton btnDK = new JButton("Tạo tài khoản");
		btnDK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = txtName.getText();
				String email = txtEmail.getText();
				String passWord = txtPass.getPassword().toString();
				String phone = txtPhone.getText().toString();
				
				if(email.contains(".employee")) {
					JOptionPane.showMessageDialog(null, "Không thể đăng ký tài khoản nhân viên");
				}else {
					int result = userBus.DangKy(userName, email, passWord, phone);
					
					if(result == 1) {
		            	OTP frame = new OTP();
						frame.setVisible(true);
						
						dispose();
		            }else {
		            	JOptionPane.showMessageDialog(null, "Đăng ký thất bại");
		            }
				}
			}
		});
		btnDK.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDK.setBounds(289, 229, 183, 38);
		contentPane.add(btnDK);
		
		JButton btnNewButton = new JButton("<-");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignIn frame = new SignIn();
				frame.setVisible(true);
				
				dispose();
			}
		});
		btnNewButton.setBounds(10, 10, 50, 21);
		contentPane.add(btnNewButton);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(160, 148, 312, 31);
		contentPane.add(txtPass);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Phone");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_2_1.setBounds(10, 197, 140, 22);
		contentPane.add(lblNewLabel_1_2_1);
		
		txtPhone = new JPasswordField();
		txtPhone.setBounds(160, 188, 312, 31);
		contentPane.add(txtPhone);
	}
}
