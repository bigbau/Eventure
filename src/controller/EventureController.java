package controller;

import gate.util.GateException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import concepts.Event;
import concepts.Time;
import relations.CauseOfIsState;
import relations.EffectOf;
import relations.EffectOfIsState;
import relations.EventForGoalEvent;
import relations.EventForGoalState;
import gui.EventureWindow;
import model.EventureModel;
import model.SQLiteModel;

public class EventureController {

	private static EventureWindow eventureWindow;
	private static EventureModel eventureModel = new EventureModel();
	private static String path ="src/sample story.txt";
	private static String inputFile = "";
	private static String storyName = "";
	
	public static void main(String[] args){
		SQLiteModel.setConnection();
		eventureWindow = new EventureWindow();
		eventureWindow.updateAssertions(SQLiteModel.getAssertions());
		eventureWindow.updateConcepts(SQLiteModel.getConcepts());
		eventureWindow.updateMetadata(SQLiteModel.getMetadata());
		SQLiteModel.closeConnection();
		eventureWindow.initialize();

		try {
			inputFile = eventureModel.stringFromFile(path);
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			eventureWindow.setMessageArea("File not found!");
		}
		eventureWindow.addTAssertionsActionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				eventureWindow.enableGetMetadata();
			}

			
		});
		
		eventureWindow.addTConceptsActionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				eventureWindow.enableGetGeneralizations();
			}

			
		});
		
		eventureWindow.addTMetadataActionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				eventureWindow.enableGetMGeneralizations();
			}

			
		});
		eventureWindow.addBtnGetMetadataActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				SQLiteModel.setConnection();
				eventureWindow.showMetadata(SQLiteModel.getFirstMetadata(eventureWindow.getSelectedAssertionId()),
						SQLiteModel.getSecondMetadata(eventureWindow.getSelectedAssertionId()));
				SQLiteModel.closeConnection();
				eventureWindow.addBtnGoBackActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						eventureWindow.goBacktoAssertions();
					}
				});
			}
			
		});
		
		eventureWindow.addBtnGetGeneralizationsActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				SQLiteModel.setConnection();
				eventureWindow.showGeneralizations(SQLiteModel.getGeneralizations(eventureWindow.getSelectedConceptId()),
						SQLiteModel.getSynonyms(eventureWindow.getSelectedConceptId()));
				SQLiteModel.closeConnection();
				eventureWindow.addBtnGoBacktoConceptsActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						eventureWindow.goBacktoConcepts();
					}
				});
			}
		});
		eventureWindow.addBtnGetMGeneralizationsActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				SQLiteModel.setConnection();
				eventureWindow.showMGeneralizations(SQLiteModel.getMGeneralizations(eventureWindow.getSelectedMetadatumId()),
						SQLiteModel.getMSynonyms(eventureWindow.getSelectedMetadatumId()));
				SQLiteModel.closeConnection();
				eventureWindow.addBtnGoBacktoMetadataActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						eventureWindow.goBacktoMetadata();
					}
				});
			}
		});
		
		eventureWindow.addBtnBrowseActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("src/stories"));
			    FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "TXT & PDF Files", "txt", "pdf");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(eventureWindow);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	path="";
			    	inputFile = "";
			       System.out.println("You chose to open this file: " +
			            chooser.getSelectedFile().getName());
			       setStoryName(chooser.getSelectedFile().getName());
			       path = chooser.getSelectedFile().getPath();
			       try {
			    	   inputFile = eventureModel.stringFromFile(path);
			       } catch (FileNotFoundException e2) {
						// TODO Auto-generated catch block
						eventureWindow.setMessageArea("File not found!");
					}
			       eventureWindow.setInputLocation(path);
			    }
			}
		});
		eventureWindow.addBtnRunActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				try {
					//Event Relations Extraction
					Iterator test = eventureModel.runPipeline(inputFile);
					eventureModel.putAnnotsToObject(test);
					eventureModel.processSummary();
					eventureWindow.setMessageArea(eventureModel.getResultsSummary());
					
					
					//Refining Module
					List<EffectOf> eo = eventureModel.getEffectOf();
					List<EffectOfIsState> eois = eventureModel.getEffectOfIsState();
					List<EventForGoalEvent> efge = eventureModel.getEventForGoalEvent();
					List<EventForGoalState> efgs = eventureModel.getEventForGoalState();
					List<CauseOfIsState> cois = eventureModel.getCauseOfIsState();
					List<Event> events = eventureModel.getEvents();
					List<Time> timelines = eventureModel.getHappensRelation();
					
					SQLiteModel.setConnection();
					
					SQLiteModel.setStoryName(getStoryName());
					
					eventureModel.insertEffectOfAssertions(eo);
					eventureModel.insertEffectOfIsStateAssertions(eois);
					eventureModel.insertEventForGoalEventAssertions(efge);
					eventureModel.insertEventForGoalStateAssertions(efgs);
					eventureModel.insertCauseOfIsStateAssertions(cois);

					eventureModel.sortEvents(events);
					eventureModel.sortTimelines(timelines);
					eventureModel.insertHappensAssertions(timelines, events);
					
					eventureWindow.updateAssertions(SQLiteModel.getAssertions());
					eventureWindow.updateConcepts(SQLiteModel.getConcepts());
					eventureWindow.updateMetadata(SQLiteModel.getMetadata());
					System.out.println("Extraction done!");

					SQLiteModel.closeConnection();
				} catch (GateException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
	}
	private static String getStoryName() {
		return storyName;
	}
	public static void setStoryName(String storyName1) {
		storyName = storyName1;
	}

}
