package controller;

import gate.util.GateException;
import gui.EventureWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import concepts.Event;
import relations.CauseOfIsState;
import relations.EffectOf;
import relations.EffectOfIsState;
import relations.EventForGoalEvent;
import relations.EventForGoalState;
import sqlite.SQLiteProcessor;
import model.EventureModel;

public class EventureController {

	private static EventureWindow eventureWindow = new EventureWindow();
	private static EventureModel eventureModel = new EventureModel();
	private static String inputFile = "";
	private static Set results = null;

	public static void main(String[] args){
		eventureWindow.initialize();

		try {
			inputFile = eventureModel.stringFromFile("src\\sample story.txt");
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			eventureWindow.setMessageArea("File not found!");
		}

		eventureWindow.addBtnRunActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				try {
					Iterator test = eventureModel.runPipeline(inputFile);
					eventureModel.putAnnotsToObject(test);
					eventureModel.processSummary();
					eventureWindow.setMessageArea(eventureModel.getResultsSummary());
					
					List<EffectOf> EO = eventureModel.getEffectOf();
					List<EffectOfIsState> EOIS = eventureModel.getEffectOfIsState();
					List<EventForGoalEvent> EFGE = eventureModel.getEventForGoalEvent();
					List<EventForGoalState> EFGS = eventureModel.getEventForGoalState();
					List<CauseOfIsState> COIS = eventureModel.getCauseOfIsState();
					List<Event> EVENTS = eventureModel.getEvents();

					System.out.println(EVENTS.size()+" Events assertions");
					System.out.println(EO.size()+" EffectOf assertions");
					for(EffectOf eo: EO){
						System.out.println("cause ="+eo.getCause().getVerb());
						System.out.println("effect ="+eo.getEffect().getVerb());
						SQLiteProcessor.insertEffectOf(eo);
					}
					System.out.println(EOIS.size()+" EffectOfIsState assertions");
					for(EffectOfIsState eois: EOIS){
						System.out.println("cause = "+eois.getEvent().getVerb());
						System.out.println("effect = "+eois.getState().toString());
						SQLiteProcessor.insertEffectOfIsState(eois);
					}
					System.out.println(EFGE.size()+" EventForGoalEvent assertions");
					for(EventForGoalEvent efge: EFGE){
						System.out.println("task = "+efge.getTask().getVerb());
						System.out.println("goal = "+efge.getGoal().getVerb());
						SQLiteProcessor.insertEventForGoalEvent(efge);
					}
					System.out.println(EFGS.size()+" EventForGoalState assertions");
					for(EventForGoalState efgs: EFGS){
						System.out.println("task = "+efgs.getEvent().getVerb());
						System.out.println("goal = "+efgs.getState().toString());
						SQLiteProcessor.insertEventForGoalState(efgs);
					}
					System.out.println(COIS.size()+" CauseOfIsState assertions");
					for(CauseOfIsState cois: COIS){
						System.out.println("cause = "+cois.getState().toString());
						System.out.println("effect = "+cois.getEvent().getVerb());
						SQLiteProcessor.insertCauseOfIsState(cois);
					}

				} catch (GateException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
	}

}
