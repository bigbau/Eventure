package gui;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JTextArea;


public class EventureWindow extends JFrame {

	private JPanel contentPane;
	private JTextField inputLocation;
	private JTextArea messageArea;
	private JButton btnBrowse;
	private JButton btnRun;
	private JScrollPane scrollPane;
	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public void initialize(){
		this.setVisible(true);
	}
	
	public EventureWindow() {
		setResizable(false);
		setFont(new Font("Arial", Font.PLAIN, 12));
		setTitle("Eventure Build 1.2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEventure = new JLabel("EVENTURE");
		lblEventure.setBounds(103, 11, 574, 23);
		lblEventure.setHorizontalAlignment(SwingConstants.CENTER);
		lblEventure.setFont(new Font("Arial", Font.PLAIN, 19));
		contentPane.add(lblEventure);
		
		inputLocation = new JTextField();
		inputLocation.setText("Click browse to look for a .txt file");
		inputLocation.setBounds(10, 45, 640, 29);
		inputLocation.setEditable(false);
		inputLocation.setFont(new Font("Arial", Font.ITALIC, 13));
		contentPane.add(inputLocation);
		inputLocation.setColumns(10);
		
		btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(660, 45, 124, 29);
		btnBrowse.setFont(new Font("Arial", Font.PLAIN, 14));
		btnBrowse.setBackground(Color.WHITE);
		contentPane.add(btnBrowse);
		
		btnRun = new JButton("RUN");
		
		btnRun.setFont(new Font("Arial", Font.PLAIN, 14));
		btnRun.setBackground(Color.WHITE);
		btnRun.setBounds(10, 85, 774, 29);
		contentPane.add(btnRun);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 121, 774, 430);
		contentPane.add(scrollPane);
		
		messageArea = new JTextArea();
		scrollPane.setViewportView(messageArea);
		messageArea.setEditable(false);
		
	}
	public void addBtnBrowseActionListener(ActionListener al) {
		btnBrowse.addActionListener(al);
	}
	public void addBtnRunActionListener(ActionListener al) {
		btnRun.addActionListener(al);
	}
	public String getInputLocationText() {
		return inputLocation.getText();
	}
	public void setMessageArea(String messages) {
		messageArea.setText(messages);
	}
	public void addMessageArea(String messages) {
		messageArea.append(messages);
	}
}
