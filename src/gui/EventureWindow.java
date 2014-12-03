package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
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
	private JLabel lblOntology;
	private JLabel lblAssertion;
	private JLabel lblConcept1;
	private JLabel lblConcept2;
	private JTextField inputLocation;
	private JTextArea messageArea;
	private JButton btnBrowse;
	private JButton btnRun;
	private JButton btnGetMetadata;
	private JButton btnGoBack;
	private JScrollPane extractionScrollPane;
	private JScrollPane ontologyScrollPane;
	private JScrollPane metadataScrollPane1;
	private JScrollPane metadataScrollPane2;
	private JTable tAssertions;
	private JTable tMetadata1;
	private JTable tMetadata2;

	
	
	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public void initialize(){
		this.setVisible(true);
	}
	
	public EventureWindow(String[][] data) {
		setResizable(false);
		setFont(new Font("Arial", Font.PLAIN, 12));
		setTitle("Eventure Build 1.6");
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
		
		lblOntology = new JLabel("EVENTURE Ontology");
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
		
		btnGetMetadata = new JButton("Get Metadata");
		
		btnGetMetadata.setFont(new Font("Arial", Font.PLAIN, 14));
		btnGetMetadata.setBackground(Color.WHITE);
		btnGetMetadata.setBounds(10, 45, 200, 25);
		btnGetMetadata.setEnabled(false);
		ontologyPanel.add(btnGetMetadata);
		
		
		extractionScrollPane = new JScrollPane();
		extractionScrollPane.setBounds(10, 121, 774, 430);
		extractionPanel.add(extractionScrollPane);
		
		messageArea = new JTextArea();
		extractionScrollPane.setViewportView(messageArea);
		messageArea.setEditable(false);

		ontologyScrollPane = new JScrollPane();
		ontologyPanel.add(ontologyScrollPane);
		String[] columnNames = {"Assertion ID","Relation","Concept 1","Concept 2","Frequency"};

		DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
			   @Override
			   public boolean isCellEditable(int row, int column) {
			       return false;
			   }
			};
		tAssertions = new JTable(tableModel);
		tAssertions.setRowSelectionAllowed(true);
		tAssertions.setColumnSelectionAllowed(false);

		ontologyScrollPane.setBounds(10, 75, 774, 470);
		tAssertions.setFont(new Font("Arial", Font.PLAIN, 13));
		ontologyScrollPane.setViewportView(tAssertions);
	    
	    tAssertions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    

		tabbedPane = new JTabbedPane();
		tabbedPane.setBorder(new EmptyBorder(0,0,0,0));
		add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.addTab("Extaction Module", null, extractionPanel,
		                  "Extracts assertions in a selected story");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		tabbedPane.addTab("Assertions", null, ontologyPanel,
		                  "Presents the assertions extracted by Eventure");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_2);

		setContentPane(tabbedPane);
	}
	public void goBacktoAssertions(){
		metadataScrollPane1.setVisible(false);
		metadataScrollPane2.setVisible(false);
		lblAssertion.setVisible(false);
		lblConcept1.setVisible(false);
		lblConcept2.setVisible(false);
		btnGoBack.setVisible(false);

		ontologyScrollPane.setVisible(true);
		lblOntology.setVisible(true);
		btnGetMetadata.setEnabled(true);
		btnGetMetadata.setVisible(true);
		
	}
	public void showMetadata(String[][] data1, String[][] data2){
		String[] metadataColumns = {"Metadatum", "Metadata Type", "Frequency"};
		DefaultTableModel tableModel1 = new DefaultTableModel(data1, metadataColumns) {
			   @Override
			   public boolean isCellEditable(int row, int column) {
			       return false;
			   }
			};
		DefaultTableModel tableModel2 = new DefaultTableModel(data2, metadataColumns) {
			   @Override
			   public boolean isCellEditable(int row, int column) {
			       return false;
			   }
			};
		tMetadata1 = new JTable(tableModel1);
		tMetadata2 = new JTable(tableModel2);
		tMetadata1.setRowSelectionAllowed(true);
		tMetadata2.setRowSelectionAllowed(true);
		tMetadata1.setColumnSelectionAllowed(false);
		tMetadata2.setColumnSelectionAllowed(false);
		
		metadataScrollPane1 = new JScrollPane();
		metadataScrollPane2 = new JScrollPane();
		metadataScrollPane1.setBounds(10, 75, 385, 470);
		metadataScrollPane2.setBounds(397, 75, 385, 470);
		tMetadata1.setFont(new Font("Arial", Font.PLAIN, 13));
		tMetadata2.setFont(new Font("Arial", Font.PLAIN, 13));
		ontologyPanel.add(metadataScrollPane1);
		ontologyPanel.add(metadataScrollPane2);
		metadataScrollPane1.setViewportView(tMetadata1);
		metadataScrollPane2.setViewportView(tMetadata2);
		ontologyScrollPane.setVisible(false);
	    
	    tMetadata1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    
		lblAssertion = new JLabel(getSelectedRelation()+"("+getSelectedConcept1()+", "+getSelectedConcept2()+")");
		lblAssertion.setBounds(103, 11, 574, 23);
		lblAssertion.setHorizontalAlignment(SwingConstants.CENTER);
		lblAssertion.setFont(new Font("Arial", Font.PLAIN, 19));
		ontologyPanel.add(lblAssertion);
		
		lblConcept1 = new JLabel(getSelectedConcept1()+" Metadata");
		lblConcept1.setBounds(10, 45, 385, 25);
		lblConcept1.setFont(new Font("Arial", Font.PLAIN, 13));
		ontologyPanel.add(lblConcept1);
		
		lblConcept2 = new JLabel(getSelectedConcept2()+" Metadata");
		lblConcept2.setBounds(397, 45, 385, 25);
		lblConcept2.setFont(new Font("Arial", Font.PLAIN, 13));
		ontologyPanel.add(lblConcept2);
		
		lblOntology.setVisible(false);
		btnGetMetadata.setEnabled(false);
		btnGetMetadata.setVisible(false);
		
		btnGoBack = new JButton("Go Back");
		
		btnGoBack.setFont(new Font("Arial", Font.PLAIN, 14));
		btnGoBack.setBackground(Color.WHITE);
		btnGoBack.setBounds(10, 11, 200, 25);
		btnGoBack.setEnabled(true);
		ontologyPanel.add(btnGoBack);
	}
	public void enableGetMetadata(){
		btnGetMetadata.setEnabled(true);
	}
	public String getSelectedRelation(){
		return (String)tAssertions.getModel().getValueAt(tAssertions.getSelectedRow(), 1);
	}
	public String getSelectedConcept1(){
		return (String)tAssertions.getModel().getValueAt(tAssertions.getSelectedRow(), 2);
	}
	public String getSelectedConcept2(){
		return (String)tAssertions.getModel().getValueAt(tAssertions.getSelectedRow(), 3);
	}
	public int getSelectedAssertionId(){
		return Integer.parseInt((String)tAssertions.getModel().getValueAt(tAssertions.getSelectedRow(), 0));
	}
	public void setInputLocation(String inputLocation){
		this.inputLocation.setText(inputLocation);
	}
	public void addTAssertionsActionListener(ListSelectionListener ll) {
		tAssertions.getSelectionModel().addListSelectionListener(ll);
	}
	public void addBtnBrowseActionListener(ActionListener al) {
		btnBrowse.addActionListener(al);
	}
	public void addBtnRunActionListener(ActionListener al) {
		btnRun.addActionListener(al);
	}
	public void addBtnGetMetadataActionListener(ActionListener al){
		btnGetMetadata.addActionListener(al);
	}
	public void addBtnGoBackActionListener(ActionListener al){
		btnGoBack.addActionListener(al);
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
