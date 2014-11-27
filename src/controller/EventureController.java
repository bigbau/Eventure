package controller;

import gate.util.GateException;
import gui.EventureWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import concepts.Event;
import concepts.Time;
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
					List<Time> TIME = eventureModel.getHappensRelation();
					//Sorting EVENTS
					Collections.sort(EVENTS, new Comparator<Event>() {
				        @Override
				        public int compare(Event  event1, Event  event2)
				        {
				        	Long start1 = event1.getStartNode();
				        	Long start2 = event2.getStartNode();
				            return  start1.compareTo(start2);
				        }
				    });
					for(Event event: EVENTS){
						System.out.println(event.getStartNode());
					}
					//Sorting
					Collections.sort(TIME, new Comparator<Time>() {
					        @Override
					        public int compare(Time  time1, Time  time2)
					        {
					        	Long start1 = time1.getStartNode();
					        	Long start2 = time2.getStartNode();
					            return  start1.compareTo(start2);
					        }
					    });

					System.out.println(EO.size()+" EffectOf assertions");
					for(EffectOf eo: EO){
						System.out.println("cause ="+eo.getCause().getVerb());
						System.out.println("effect ="+eo.getEffect().getVerb());
						try{
							SQLiteProcessor.insertEffectOf(eo);
						} catch(NullPointerException ex){
							ex.printStackTrace();
						}
					}
					System.out.println(EOIS.size()+" EffectOfIsState assertions");
					for(EffectOfIsState eois: EOIS){
						System.out.println("cause = "+eois.getEvent().getVerb());
						System.out.println("effect = "+eois.getState().toString());
						try{
							SQLiteProcessor.insertEffectOfIsState(eois);
						} catch(NullPointerException ex){
							ex.printStackTrace();
						}
					}
					System.out.println(EFGE.size()+" EventForGoalEvent assertions");
					for(EventForGoalEvent efge: EFGE){
						System.out.println("task = "+efge.getTask().getVerb());
						System.out.println("goal = "+efge.getGoal().getVerb());
						try{
							SQLiteProcessor.insertEventForGoalEvent(efge);
						} catch(NullPointerException ex){
							ex.printStackTrace();
						}
					}
					System.out.println(EFGS.size()+" EventForGoalState assertions");
					for(EventForGoalState efgs: EFGS){
						System.out.println("task = "+efgs.getEvent().getVerb());
						System.out.println("goal = "+efgs.getState().toString());
						try{
							SQLiteProcessor.insertEventForGoalState(efgs);
						} catch(NullPointerException ex){
							ex.printStackTrace();
						}
					}
					System.out.println(TIME.size()+" timelines for "+EVENTS.size()+" events");
					for(Event event: EVENTS){
						for(int i=0; i<TIME.size(); i++){
							if(event.getStartNode()>=TIME.get(i).getStartNode()){
								if(TIME.size()==i+1||(TIME.size()>i+1&&TIME.get(i+1).getStartNode()>event.getStartNode())){
									System.out.println("event = "+event.getVerb());
									System.out.println("time = "+TIME.get(i).getTimeHappened());
									try{
										SQLiteProcessor.insertHappens(event, TIME.get(i));
									} catch(NullPointerException ex){
										ex.printStackTrace();
									}
								}
							}
						}
					}
					System.out.println(COIS.size()+" CauseOfIsState assertions");
					for(CauseOfIsState cois: COIS){
						System.out.println("cause = "+cois.getState().toString());
						System.out.println("effect = "+cois.getEvent().getVerb());
						try{
							SQLiteProcessor.insertCauseOfIsState(cois);
						} catch(NullPointerException ex){
							ex.printStackTrace();
						}
					}
					

				} catch (GateException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
	}

}
