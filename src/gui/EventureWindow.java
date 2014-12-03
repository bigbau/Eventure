package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
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
import java.util.Comparator;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.SQLiteModel;
import relations.Relation;
import concepts.Concept;


public class EventureWindow extends JFrame {
	private JTabbedPane tabbedPane;
	private JPanel extractionPanel;
	private JPanel assertionsPanel;
	private JPanel conceptsPanel;
	private JLabel lblOntology;
	private JLabel lblAssertion;
	private JLabel lblConcepts;
	private JLabel lblCAssertions;
	private JLabel lblGeneralizations;
	private JTextField inputLocation;
	private JTextArea messageArea;
	private JButton btnBrowse;
	private JButton btnRun;
	private JButton btnGetMetadata;
	private JButton btnGoBack;
	private JButton btnGoBacktoConcepts;
	private JButton btnGetGeneralizations;
	private JScrollPane extractionScrollPane;
	private JScrollPane assertionsScrollPane;
	private JScrollPane metadataScrollPane1;
	private JScrollPane metadataScrollPane2;
	private JScrollPane conceptsScrollPane;
	private JScrollPane generalizationsScrollPane;
	private JScrollPane synonymsScrollPane;
	private JTable tAssertions=null;
	private JTable tMetadata1=null;
	private JTable tMetadata2=null;
	private JTable tConcepts;
	private JTable tGeneralizations;
	private JTable tSynonyms;

	
	
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
		setTitle("Eventure Build 1.6");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 620);
		extractionPanel = new JPanel();
		extractionPanel.setLayout(null);
		
		assertionsPanel = new JPanel();
		assertionsPanel.setLayout(null);
		
		conceptsPanel = new JPanel();
		conceptsPanel.setLayout(null);
		
		JLabel lblEventure = new JLabel("EVENTURE: Extraction Module");
		lblEventure.setBounds(103, 11, 574, 23);
		lblEventure.setHorizontalAlignment(SwingConstants.CENTER);
		lblEventure.setFont(new Font("Arial", Font.PLAIN, 19));
		extractionPanel.add(lblEventure);
		
		lblOntology = new JLabel("EVENTURE Ontology");
		lblOntology.setBounds(103, 11, 574, 23);
		lblOntology.setHorizontalAlignment(SwingConstants.CENTER);
		lblOntology.setFont(new Font("Arial", Font.PLAIN, 19));
		assertionsPanel.add(lblOntology);
		
		lblConcepts = new JLabel("EVENTURE Concepts");
		lblConcepts.setBounds(103, 11, 574, 23);
		lblConcepts.setHorizontalAlignment(SwingConstants.CENTER);
		lblConcepts.setFont(new Font("Arial", Font.PLAIN, 19));
		conceptsPanel.add(lblConcepts);
		
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
		assertionsPanel.add(btnGetMetadata);
		
		btnGetGeneralizations = new JButton("Get Generalizations");
		btnGetGeneralizations.setFont(new Font("Arial", Font.PLAIN, 14));
		btnGetGeneralizations.setBackground(Color.WHITE);
		btnGetGeneralizations.setBounds(10, 45, 256, 25);
		btnGetGeneralizations.setEnabled(false);
		conceptsPanel.add(btnGetGeneralizations);
		
		extractionScrollPane = new JScrollPane();
		extractionScrollPane.setBounds(10, 121, 774, 430);
		extractionPanel.add(extractionScrollPane);
		
		messageArea = new JTextArea();
		extractionScrollPane.setViewportView(messageArea);
		messageArea.setEditable(false);

		tabbedPane = new JTabbedPane();
		tabbedPane.setBorder(new EmptyBorder(0,0,0,0));
		add(tabbedPane, BorderLayout.CENTER);
		
		tabbedPane.addTab("Extaction Module", null, extractionPanel,
		                  "Extracts assertions in a selected story");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		
		tabbedPane.addTab("Assertions", null, assertionsPanel,
		                  "Presents the assertions extracted by Eventure");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_2);
		
		tabbedPane.addTab("Concepts", null, conceptsPanel,
                "Presents the concepts in Eventure");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_3);

		setContentPane(tabbedPane);
	}
	public void updateConcepts(Object[][] data){
		String[] columnNames = {"Concept Id","Concept","Type"};
		DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
			   @Override
			   public boolean isCellEditable(int row, int column) {
			       return false;
			   }
			};
		if(tConcepts==null){
			conceptsScrollPane = new JScrollPane();
			conceptsPanel.add(conceptsScrollPane);
			tConcepts = new JTable(tableModel);
			tConcepts.setRowSelectionAllowed(true);
			tConcepts.setColumnSelectionAllowed(false);
			tConcepts.setFont(new Font("Arial", Font.PLAIN, 13));
			tConcepts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			conceptsScrollPane.setViewportView(tConcepts);

			conceptsScrollPane.setBounds(10, 75, 774, 470);
			TableRowSorter trs = new TableRowSorter(tableModel);

	        trs.setComparator(0, new IntComparator());
	        tConcepts.setRowSorter(trs);
		    
		} else
			tConcepts.setModel(tableModel);
	}
	public void updateAssertions(Object[][] data){
		String[] columnNames = {"Assertion ID","Relation","Concept 1","Concept 2","Frequency"};
		DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
			   @Override
			   public boolean isCellEditable(int row, int column) {
			       return false;
			   }
			};
		if(tAssertions==null){
			assertionsScrollPane = new JScrollPane();
			assertionsPanel.add(assertionsScrollPane);
			tAssertions = new JTable(tableModel);
			tAssertions.setRowSelectionAllowed(true);
			tAssertions.setColumnSelectionAllowed(false);
			tAssertions.setFont(new Font("Arial", Font.PLAIN, 13));
		    tAssertions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			assertionsScrollPane.setViewportView(tAssertions);

			assertionsScrollPane.setBounds(10, 75, 774, 470);
			TableRowSorter trs = new TableRowSorter(tableModel);

	        trs.setComparator(0, new IntComparator());

	        tAssertions.setRowSorter(trs);
		} else
			tAssertions.setModel(tableModel);
	}
	public void goBacktoAssertions(){
		metadataScrollPane1.setVisible(false);
		metadataScrollPane2.setVisible(false);
		lblAssertion.setVisible(false);
		btnGoBack.setVisible(false);

		assertionsScrollPane.setVisible(true);
		lblOntology.setVisible(true);
		btnGetMetadata.setEnabled(true);
		btnGetMetadata.setVisible(true);
		
	}
	public void goBacktoConcepts(){
		synonymsScrollPane.setVisible(false);
		generalizationsScrollPane.setVisible(false);
		lblGeneralizations.setVisible(false);
		btnGoBacktoConcepts.setVisible(false);

		conceptsScrollPane.setVisible(true);
		lblConcepts.setVisible(true);
		btnGetGeneralizations.setEnabled(true);
		btnGetGeneralizations.setVisible(true);
		
	}
	public void showMetadata(String[][] data1, String[][] data2){
		String[] metadataColumns = {"Concept","Metadatum", "Metadata Type", "Frequency"};
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
		if(tMetadata1==null){
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
			assertionsPanel.add(metadataScrollPane1);
			assertionsPanel.add(metadataScrollPane2);
			metadataScrollPane1.setViewportView(tMetadata1);
			metadataScrollPane2.setViewportView(tMetadata2);
			
		    tMetadata1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		    tMetadata2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		    
			lblAssertion = new JLabel();
			lblAssertion.setBounds(103, 11, 574, 23);
			lblAssertion.setHorizontalAlignment(SwingConstants.CENTER);
			lblAssertion.setFont(new Font("Arial", Font.PLAIN, 19));
			assertionsPanel.add(lblAssertion);
			
			
			btnGoBack = new JButton("<-");
			
			btnGoBack.setFont(new Font("Arial", Font.PLAIN, 14));
			btnGoBack.setBackground(Color.WHITE);
			btnGoBack.setBounds(10, 11, 50, 25);
			assertionsPanel.add(btnGoBack);
		} 
		else{
			tMetadata1.setModel(tableModel1);
			tMetadata2.setModel(tableModel2);
		}
		String relation = getSelectedRelation(),concept1_type = Concept.EVENT,concept2_type = Concept.EVENT,concept1 = getSelectedConcept1(),
		concept2 = getSelectedConcept2();
		if(relation.equals(Relation.CAUSEOFISSTATE)){
			concept1_type = Concept.STATE;
		}
		if(relation.equals(Relation.EFFECTOFISSTATE)||relation.equals(Relation.EVENTFORGOALSTATE)){
			concept2_type = Concept.STATE;
		} else if(relation.equals(Relation.HAPPENS)){
			concept2_type=Concept.TIME;
		}
		SQLiteModel.setConnection();
		lblAssertion.setText("Metadata: "+relation+"("+concept1+"["+SQLiteModel.getConceptID(concept1, concept1_type)+"], "
		+concept2+"["+SQLiteModel.getConceptID(concept2, concept2_type)+"])");
		SQLiteModel.closeConnection();
		lblOntology.setVisible(false);
		btnGetMetadata.setEnabled(false);
		btnGetMetadata.setVisible(false);
		btnGoBack.setEnabled(true);
		assertionsScrollPane.setVisible(false);
		metadataScrollPane1.setVisible(true);
		metadataScrollPane2.setVisible(true);
		lblAssertion.setVisible(true);
		btnGoBack.setVisible(true);
	}
	public void showGeneralizations(Object[][] generalizations, Object[][] synonyms){
		String[] gColumns = {"Generalization", "Frequency"};
		String[] sColumns = {"Synonym", "Concept ID"};
		DefaultTableModel tableModel1 = new DefaultTableModel(generalizations, gColumns) {
			   @Override
			   public boolean isCellEditable(int row, int column) {
			       return false;
			   }
			};
		DefaultTableModel tableModel2 = new DefaultTableModel(synonyms, sColumns) {
			   @Override
			   public boolean isCellEditable(int row, int column) {
			       return false;
			   }
			};
		if(tGeneralizations==null){
			tGeneralizations = new JTable(tableModel1);
			tSynonyms = new JTable(tableModel2);
			tGeneralizations.setRowSelectionAllowed(true);
			tSynonyms.setRowSelectionAllowed(true);
			tGeneralizations.setColumnSelectionAllowed(false);
			tSynonyms.setColumnSelectionAllowed(false);
			generalizationsScrollPane = new JScrollPane();
			synonymsScrollPane = new JScrollPane();
			generalizationsScrollPane.setBounds(10, 75, 385, 470);
			synonymsScrollPane.setBounds(397, 75, 385, 470);
			tGeneralizations.setFont(new Font("Arial", Font.PLAIN, 13));
			tSynonyms.setFont(new Font("Arial", Font.PLAIN, 13));
			conceptsPanel.add(generalizationsScrollPane);
			conceptsPanel.add(synonymsScrollPane);
			generalizationsScrollPane.setViewportView(tGeneralizations);
			synonymsScrollPane.setViewportView(tSynonyms);
			
			tSynonyms.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tGeneralizations.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		    
			lblGeneralizations = new JLabel();
			lblGeneralizations.setBounds(103, 11, 574, 23);
			lblGeneralizations.setHorizontalAlignment(SwingConstants.CENTER);
			lblGeneralizations.setFont(new Font("Arial", Font.PLAIN, 19));
			conceptsPanel.add(lblGeneralizations);
			
			
			btnGoBacktoConcepts = new JButton("<-");
			
			btnGoBacktoConcepts.setFont(new Font("Arial", Font.PLAIN, 14));
			btnGoBacktoConcepts.setBackground(Color.WHITE);
			btnGoBacktoConcepts.setBounds(10, 11, 50, 25);
			conceptsPanel.add(btnGoBacktoConcepts);
		} 
		else{
			tGeneralizations.setModel(tableModel1);
			tSynonyms.setModel(tableModel2);
		}
		lblGeneralizations.setText("Generalizations: "+getSelectedConcept()+"["+getSelectedConceptId()+"]");
		lblGeneralizations.setVisible(true);
		lblConcepts.setVisible(false);
		btnGetGeneralizations.setEnabled(false);
		btnGetGeneralizations.setVisible(false);
		btnGoBacktoConcepts.setEnabled(true);
		conceptsScrollPane.setVisible(false);
		generalizationsScrollPane.setVisible(true);
		synonymsScrollPane.setVisible(true);
		generalizationsScrollPane.setVisible(true);
		btnGoBacktoConcepts.setVisible(true);
	}
	public void enableGetMetadata(){
		btnGetMetadata.setEnabled(true);
	}
	public void enableGetGeneralizations(){
		btnGetGeneralizations.setEnabled(true);
	}
	public String getSelectedRelation(){
		return (String)tAssertions.getModel().getValueAt(tAssertions.getSelectedRow(), 1);
	}
	public Integer getSelectedConceptId(){
		return (Integer)tConcepts.getModel().getValueAt(tConcepts.getSelectedRow(), 0);
	}
	public String getSelectedConcept(){
		return (String)tConcepts.getModel().getValueAt(tConcepts.getSelectedRow(), 1);
	}
	public String getSelectedConcept1(){
		return (String)tAssertions.getModel().getValueAt(tAssertions.getSelectedRow(), 2);
	}
	public String getSelectedConcept2(){
		return (String)tAssertions.getModel().getValueAt(tAssertions.getSelectedRow(), 3);
	}
	public Integer getSelectedAssertionId(){
		return (Integer)tAssertions.getModel().getValueAt(tAssertions.getSelectedRow(), 0);
	}
	public void setInputLocation(String inputLocation){
		this.inputLocation.setText(inputLocation);
	}
	public void addTAssertionsActionListener(ListSelectionListener ll) {
		tAssertions.getSelectionModel().addListSelectionListener(ll);
	}
	public void addTConceptsActionListener(ListSelectionListener ll) {
		tConcepts.getSelectionModel().addListSelectionListener(ll);
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
	public void addBtnGetGeneralizationsActionListener(ActionListener al){
		btnGetGeneralizations.addActionListener(al);
	}
	public void addBtnGoBackActionListener(ActionListener al){
		btnGoBack.addActionListener(al);
	}
	public void addBtnGoBacktoConceptsActionListener(ActionListener al){
		btnGoBacktoConcepts.addActionListener(al);
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

    class IntComparator implements Comparator {
        public int compare(Object o1, Object o2) {
            Integer int1 = (Integer)o1;
            Integer int2 = (Integer)o2;
            return int1.compareTo(int2);
        }

        public boolean equals(Object o2) {
            return this.equals(o2);
        }
    }
}
