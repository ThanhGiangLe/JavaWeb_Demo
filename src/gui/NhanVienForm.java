package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.CardLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bus.TourDuLichBus;
import entity.PhieuDat;
import entity.TourDuLich;
import entity.User;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class NhanVienForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMaTour;
	private JTextField txtTenTour;
	private JTextField txtGia;
	private JTextField txtStart;
	private JTable tableMain;
	private TourDuLichBus tourDuLichBus = new TourDuLichBus();
	private ArrayList<TourDuLich> listTour = tourDuLichBus.getAllTour();
	private JTextField txtDiaDiem;
	private JTextField txtEnd;

	public void showResult() {
		ArrayList<TourDuLich> newList = tourDuLichBus.getAllTour();
		DefaultTableModel model = (DefaultTableModel)tableMain.getModel();
		model.setNumRows(0);
		for(TourDuLich item : newList) {
			model.addRow(new Object[] {
				item.getMaTour(),
				item.getTenTour(),
				item.getTenDiaDiem(),
				item.getGia(),
				item.getStartDate(),
				item.getEndDate()
			});
		}
		tableMain.setModel(model);
	}
	public void showSelected() {
		int i = tableMain.getSelectedRow();
		TourDuLich tour = listTour.get(i);
		txtMaTour.setText(String.valueOf(tour.getMaTour()));
		txtTenTour.setText(tour.getTenTour());
		txtDiaDiem.setText(tour.getTenDiaDiem());
		txtGia.setText(String.valueOf(tour.getGia()));
		txtStart.setText(tour.getStartDate().toString());
		txtEnd.setText(tour.getEndDate().toString());
	}
	/**
	 * Create the frame.
	 */
	public NhanVienForm(User s) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 919, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTnTour = new JLabel("Mã tour");
		lblTnTour.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTnTour.setBounds(12, 15, 100, 27);
		contentPane.add(lblTnTour);
		
		JLabel lblTnaim = new JLabel("Tên tour");
		lblTnaim.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTnaim.setBounds(12, 50, 100, 27);
		contentPane.add(lblTnaim);
		
		txtMaTour = new JTextField();
		txtMaTour.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMaTour.setColumns(10);
		txtMaTour.setBounds(160, 17, 199, 22);
		contentPane.add(txtMaTour);
		
		txtTenTour = new JTextField();
		txtTenTour.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTenTour.setColumns(10);
		txtTenTour.setBounds(160, 52, 199, 22);
		contentPane.add(txtTenTour);
		
		txtGia = new JTextField();
		txtGia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtGia.setColumns(10);
		txtGia.setBounds(160, 120, 199, 22);
		contentPane.add(txtGia);
		
		txtStart = new JTextField();
		txtStart.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtStart.setColumns(10);
		txtStart.setBounds(160, 160, 199, 22);
		contentPane.add(txtStart);
		
		JButton btnSua = new JButton("Sửa");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int maTour = Integer.parseInt(txtMaTour.getText());
				String tenTour = txtTenTour.getText();
				String tenDiaDiem = txtDiaDiem.getText();
				float gia = Float.parseFloat(txtGia.getText());
				Date startDate = Date.valueOf(txtStart.getText());
				Date endDate = Date.valueOf(txtEnd.getText());
				
				TourDuLich tour = new TourDuLich(maTour, tenTour, tenDiaDiem, gia, startDate, endDate);
				tourDuLichBus.updateTour(tour);
				tableMain.removeAll();
				showResult();
			}
		});
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSua.setBounds(418, 15, 97, 36);
		contentPane.add(btnSua);
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int maTour = Integer.parseInt(txtMaTour.getText());
				tourDuLichBus.deleteTour(maTour);
				tableMain.removeAll();
				showResult();
			}
		});
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnXoa.setBounds(418, 69, 97, 36);
		contentPane.add(btnXoa);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
//				showSelected();
			}
		});
		scrollPane.setBounds(12, 235, 877, 188);
		contentPane.add(scrollPane);
		
		tableMain = new JTable();
		tableMain.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				showSelected();
			}
		});
		tableMain.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 tour", "T\u00EAn tour", "T\u00EAn \u0111\u1ECBa \u0111i\u1EC3m", "Gi\u00E1", "Ng\u00E0y b\u1EAFt \u0111\u1EA7u", "Ng\u00E0y k\u1EBFt th\u00FAc"
			}
		));
		scrollPane.setViewportView(tableMain);
		
		JButton btnThot = new JButton("Thoát");
		btnThot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SignIn frame = new SignIn();
				frame.setVisible(true);
				dispose();
			}
		});
		btnThot.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnThot.setBounds(418, 119, 97, 36);
		contentPane.add(btnThot);
		
		JLabel lblTnaim_2 = new JLabel("Tên địa điểm");
		lblTnaim_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTnaim_2.setBounds(12, 83, 100, 27);
		contentPane.add(lblTnaim_2);
		
		txtDiaDiem = new JTextField();
		txtDiaDiem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtDiaDiem.setColumns(10);
		txtDiaDiem.setBounds(160, 85, 199, 22);
		contentPane.add(txtDiaDiem);
		
		JLabel lblTnaim_2_1 = new JLabel("Giá");
		lblTnaim_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTnaim_2_1.setBounds(12, 118, 100, 27);
		contentPane.add(lblTnaim_2_1);
		
		JLabel lblTnaim_2_2 = new JLabel("Ngày bắt đầu");
		lblTnaim_2_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTnaim_2_2.setBounds(12, 155, 119, 27);
		contentPane.add(lblTnaim_2_2);
		
		JLabel lblTnaim_2_3 = new JLabel("Ngày kết thúc");
		lblTnaim_2_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTnaim_2_3.setBounds(12, 195, 119, 27);
		contentPane.add(lblTnaim_2_3);
		
		txtEnd = new JTextField();
		txtEnd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtEnd.setColumns(10);
		txtEnd.setBounds(160, 200, 199, 22);
		contentPane.add(txtEnd);
		showResult();
	}
}
