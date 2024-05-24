package gui;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bus.PhieuDatBus;
import bus.TourDuLichBus;
import dao.PhieuDatDao;
import entity.PhieuDat;
import entity.TourDuLich;
import entity.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class KhachHangForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTable tableTour;
	private static JTextField txtUserName;
	private TourDuLichBus tourDuLichBus = new TourDuLichBus();
	private ArrayList<TourDuLich> listTour = tourDuLichBus.getAllTour();
	private PhieuDatBus phieuDatBus = new PhieuDatBus();
	private ArrayList<PhieuDat> listPhieu = phieuDatBus.getAllPhieuDat();


	public void showResult() {
		DefaultTableModel model = (DefaultTableModel)tableTour.getModel();
		for(TourDuLich item : listTour) {
			model.addRow(new Object[] {
				item.getMaTour(),
				item.getTenTour(),
				item.getTenDiaDiem(),
				item.getGia(),
				item.getStartDate(),
				item.getEndDate()
			});
		}
		tableTour.setModel(model);
	}
	/**
	 * Create the frame.
	 */
	public KhachHangForm(User s) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 731, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 105, 697, 302);
		contentPane.add(scrollPane);
		
		tableTour = new JTable();
		tableTour.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 tour", "T\u00EAn Tour", "T\u00EAn \u0110\u1ECBa \u0110i\u1EC3m", "Gi\u00E1", "Ng\u00E0y Kh\u1EDFi H\u00E0nh", "Ng\u00E0y V\u1EC1"
			}
		));
		showResult();
		scrollPane.setViewportView(tableTour);
		
		JLabel lblNewLabel = new JLabel("Tên ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 70, 59, 22);
		contentPane.add(lblNewLabel);
		
		JButton btnDat = new JButton("Đặt");
		btnDat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tableTour.getSelectedRow();
				int maTour = (int)tableTour.getValueAt(index, 0);
				String tenTour = (String) tableTour.getValueAt(index, 1);
				int maKhach = s.getUser_id();
				String tenKhach = s.getUsername();
				
				PhieuDat phieu = new PhieuDat(maTour, tenTour, maKhach, tenKhach);
				if(phieuDatBus.datPhieu(phieu)) {
					JOptionPane.showMessageDialog(null, "Đặt thành công");
				}else {
					JOptionPane.showMessageDialog(null, "Đặt thất bại");
				}
			}
		});
		btnDat.setBounds(214, 70, 85, 22);
		contentPane.add(btnDat);
		
		txtUserName = new JTextField();
		txtUserName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		System.out.println("inside: " + s);
		txtUserName.setText(s.getUsername());
		txtUserName.setBounds(81, 67, 107, 25);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);
		
		JButton btnNewButton = new JButton("<-");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SignIn frame = new SignIn();
				frame.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(10, 13, 74, 25);
		contentPane.add(btnNewButton);
	}
}
