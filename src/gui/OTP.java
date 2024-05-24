package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bus.UserBus;
import dao.UserDao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OTP extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtOtp;
	private UserBus uBus = new UserBus();
	private UserDao uDao = new UserDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OTP frame = new OTP();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OTP() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nhập mã xát nhận");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(98, 25, 281, 36);
		contentPane.add(lblNewLabel);
		
		txtOtp = new JTextField();
		txtOtp.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtOtp.setBounds(55, 80, 307, 33);
		contentPane.add(txtOtp);
		txtOtp.setColumns(10);
		
		JButton btnXatThuc = new JButton("Xát nhận");
		btnXatThuc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userEmail = UserEmailCheck();
				if(uDao.getOTPofEmailUser(userEmail).equals(txtOtp.getText())) {
					JOptionPane.showMessageDialog(null, "Đăng ký thành công");
	            	SignIn frame = new SignIn();
					frame.setVisible(true);
					
					dispose();
				}else {
	            	JOptionPane.showMessageDialog(null, "Đăng ký thất bại");
	            }
			}
		});
		btnXatThuc.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnXatThuc.setBounds(137, 135, 156, 36);
		contentPane.add(btnXatThuc);
	}
	public String UserEmailCheck() {
		return uBus.getUserEmailByOtp(txtOtp.getText());
	}
}
