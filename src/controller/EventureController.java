package controller;

import gate.util.GateException;
import gui.EventureWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import concepts.Event;
import concepts.Time;
import relations.CauseOfIsState;
import relations.EffectOf;
import relations.EffectOfIsState;
import relations.EventForGoalEvent;
import relations.EventForGoalState;
import model.EventureModel;
import model.SQLiteModel;

public class EventureController {

	private static EventureWindow eventureWindow = new EventureWindow();
	private static EventureModel eventureModel = new EventureModel();
	private static String path ="src/sample story.txt";
	private static String inputFile = "";

	public static void main(String[] args){
		eventureWindow.initialize();

		try {
			inputFile = eventureModel.stringFromFile(path);
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			eventureWindow.setMessageArea("File not found!");
		}
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
					
					eventureModel.insertEffectOfAssertions(eo);
					eventureModel.insertEffectOfIsStateAssertions(eois);
					eventureModel.insertEventForGoalEventAssertions(efge);
					eventureModel.insertEventForGoalStateAssertions(efgs);
					eventureModel.insertCauseOfIsStateAssertions(cois);

					eventureModel.sortEvents(events);
					eventureModel.sortTimelines(timelines);
					eventureModel.insertHappensAssertions(timelines, events);
					
					SQLiteModel.closeConnection();
					
					System.out.println("Extraction done!");

				} catch (GateException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
	}

}
