package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class EventureWindow extends JFrame {
	private JTabbedPane tabbedPane;
	private JPanel extractionPanel;
	private JPanel ontologyPanel;
	private JTextField inputLocation;
	private JTextArea messageArea;
	private JButton btnBrowse;
	private JButton btnRun;
	private JScrollPane scrollPane;
	private JTable tAssertions;
	private JTable tMetadata;
	
	
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
		setTitle("Eventure Build 1.5");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 620);
		extractionPanel = new JPanel();
		extractionPanel.setLayout(null);
		
		ontologyPanel = new JPanel();
		ontologyPanel.setLayout(null);
		
		JLabel lblEventure = new JLabel("EVENTURE: Extraction Module");
		lblEventure.setBounds(103, 11, 574, 23);
		lblEventure.setHorizontalAlignment(SwingConstants.CENTER);
		lblEventure.setFont(new Font("Arial", Font.PLAIN, 19));
		extractionPanel.add(lblEventure);
		
		JLabel lblOntology = new JLabel("EVENTURE Ontology");
		lblOntology.setBounds(103, 11, 574, 23);
		lblOntology.setHorizontalAlignment(SwingConstants.CENTER);
		lblOntology.setFont(new Font("Arial", Font.PLAIN, 19));
		ontologyPanel.add(lblOntology);
		
		inputLocation = new JTextField();
		inputLocation.setText("Click browse to look for a .txt file");
		inputLocation.setBounds(10, 45, 640, 29);
		inputLocation.setEditable(false);
		inputLocation.setFont(new Font("Arial", Font.ITALIC, 13));
		extractionPanel.add(inputLocation);
		inputLocation.setColumns(10);
		
		btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(660, 45, 124, 29);
		btnBrowse.setFont(new Font("Arial", Font.PLAIN, 14));
		btnBrowse.setBackground(Color.WHITE);
		extractionPanel.add(btnBrowse);
		
		btnRun = new JButton("RUN");
		
		btnRun.setFont(new Font("Arial", Font.PLAIN, 14));
		btnRun.setBackground(Color.WHITE);
		btnRun.setBounds(10, 85, 774, 29);
		extractionPanel.add(btnRun);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 121, 774, 430);
		extractionPanel.add(scrollPane);
		
		messageArea = new JTextArea();
		scrollPane.setViewportView(messageArea);
		messageArea.setEditable(false);
		

		tabbedPane = new JTabbedPane();
		tabbedPane.setBorder(new EmptyBorder(0,0,0,0));
		add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.addTab("Extaction Module", null, extractionPanel,
		                  "Extracts assertions in a selected story");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		tabbedPane.addTab("Ontology", null, ontologyPanel,
		                  "Presents the assertions extracted by Eventure");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_2);

		setContentPane(tabbedPane);
	}
	public void setInputLocation(String inputLocation){
		this.inputLocation.setText(inputLocation);
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
