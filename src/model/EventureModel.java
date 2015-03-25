package model;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import concepts.Event;
import concepts.Time;
import concepts.State;
import gate.Annotation;
import gate.AnnotationSet;
import gate.Corpus;
import gate.CorpusController;
import gate.Document;
import gate.Factory;
import gate.Gate;
import gate.util.GateException;
import gate.util.persistence.PersistenceManager;


import relations.CauseOfIsState;
import relations.EffectOf;
import relations.EffectOfIsState;
import relations.EventForGoalEvent;
import relations.EventForGoalState;
import gui.EventureWindow;


public class EventureModel {


	private List extractedAnnotations;
	private CorpusController application;
	private String resultsSummary;

	private List<EffectOf> effectOf;
	private List<EffectOfIsState> effectOfIsState;
	private List<CauseOfIsState> causeOfIsState;
	private List<EventForGoalEvent>  eventForGoalEvent;
	private List<EventForGoalState>  eventForGoalState;
	private List<Time>  happensRelation;
	private List<Event> events;

	private List<String> sentences;
	
	public Iterator runPipeline(String inputFile) throws GateException{
		Gate.init();
		try {
			application = (CorpusController)PersistenceManager.loadObjectFromFile(new File("src/Eventure.gapp"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document testDocu = Factory.newDocument(inputFile);
		Corpus testCorpus = Factory.newCorpus("Eventure Corpus");
		testCorpus.add(testDocu);
		application.setCorpus(testCorpus);

		application.execute();
		String docXMLString = null; 
		effectOf = new ArrayList<EffectOf>();
		effectOfIsState = new ArrayList<EffectOfIsState>();
		causeOfIsState = new ArrayList<CauseOfIsState>();
		eventForGoalEvent = new ArrayList<EventForGoalEvent>();
		eventForGoalState = new ArrayList<EventForGoalState>();
		happensRelation = new ArrayList<Time>();
		events = new ArrayList<Event>();
		sentences = separateValues(inputFile);
		
		List annotTypesToWrite = new ArrayList();
		annotTypesToWrite.add("EffectOf");
		annotTypesToWrite.add("EffectOfIsState");
		annotTypesToWrite.add("CauseOfIsState");
		annotTypesToWrite.add("HappensRelation");
		annotTypesToWrite.add("EventForGoalEvent");
		annotTypesToWrite.add("EventForGoalState");
		annotTypesToWrite.add("Event");
		// if we want to just write out specific annotation types, we must
		// extract the annotations into a Set

		// Create a temporary Set to hold the annotations we wish to write out
		Set annotationsToWrite = new HashSet();

		// we only extract annotations from the default (unnamed) AnnotationSet
		// in this example
		AnnotationSet defaultAnnots = testDocu.getAnnotations();
		testDocu.getFeatures();
		Iterator annotTypesIt =  annotTypesToWrite.iterator();

		while(annotTypesIt.hasNext()) {
			// extract all the annotations of each requested type and add them to
			// the temporary set
			AnnotationSet annotsOfThisType = defaultAnnots.get((String)annotTypesIt.next());
			if(annotsOfThisType != null) {
				annotationsToWrite.addAll(annotsOfThisType);
			}
		}

		return annotationsToWrite.iterator();

	}


	public String stringFromFile(String location) throws FileNotFoundException{

		String content = "";

		content = new Scanner(new File(location)).useDelimiter("\\Z").next();

		return content;
	}

	public void processSummary(){
		
		StringBuilder results = new StringBuilder();
		
		if(effectOf.size()>0){
			results.append("EffectOf Relations:\n");
			Iterator temp = effectOf.iterator();
			int id = 0;
			while(temp.hasNext()){
				id++;
				EffectOf holder = (EffectOf)temp.next();
				results.append(id+": "+holder.toString());
				results.append("\n");
			}
		}
		
		if(effectOfIsState.size()>0){
			results.append("EffectOfIsState Relations:\n");
			Iterator temp = effectOfIsState.iterator();
			int id = 0;
			while(temp.hasNext()){
				id++;
				EffectOfIsState holder = (EffectOfIsState)temp.next();
				results.append(id+": "+holder.toString());
				results.append("\n");
			}
		}
		
		if(causeOfIsState.size()>0){
			results.append("CauseOfIsState Relations:\n");
			Iterator temp = causeOfIsState.iterator();
			int id = 0;
			while(temp.hasNext()){
				id++;
				CauseOfIsState holder = (CauseOfIsState)temp.next();
				results.append(id+": "+holder.toString());
				results.append("\n");
			}
		}
		
		if(eventForGoalEvent.size()>0){
			results.append("EventForGoalEvent Relations:\n");
			Iterator temp = eventForGoalEvent.iterator();
			int id = 0;
			while(temp.hasNext()){
				id++;
				EventForGoalEvent holder = (EventForGoalEvent)temp.next();
				results.append(id+": "+holder.toString());
				results.append("\n");
			}
		}

		if(eventForGoalState.size()>0){
			results.append("EventForGoalState Relations:\n");
			Iterator temp = eventForGoalState.iterator();
			int id = 0;
			while(temp.hasNext()){
				id++;
				EventForGoalState holder = (EventForGoalState)temp.next();
				results.append(id+": "+holder.toString());
				results.append("\n");
			}
		}
		if(happensRelation.size()>0){
			results.append("HappensRelation Relations:\n");
			Iterator temp = happensRelation.iterator();
			int id = 0;
			while(temp.hasNext()){
				id++;
				Time holder = (Time)temp.next();
				results.append(id+": "+holder.toString());
				results.append("\n");
			}
		}
		
		if(sentences.size()>0){
			results.append("Sentences:\n");
			Iterator temp = sentences.iterator();
			int id = 0;
			while(temp.hasNext()){
				id++;
				results.append(id+": "+temp.next().toString());
				results.append("\n");
			}
		}
		resultsSummary = results.toString();

	}

	public void putAnnotsToObject(Iterator annots){
		Annotation timeAnnot;
		while(annots.hasNext()){

			timeAnnot = (Annotation) annots.next();

			if(((String)timeAnnot.getType()).equals("EffectOf")){				  
				String causeVerb = ((String)timeAnnot.getFeatures().get("CauseVerb"))
						, causeObject = ((String)timeAnnot.getFeatures().get("CauseObject"))
						, causeObjectAdjective = ((String)timeAnnot.getFeatures().get("CauseObjectAdjectives"))
						, causeAdverb = ((String)timeAnnot.getFeatures().get("CauseAdverb"))
						, effectVerb = ((String)timeAnnot.getFeatures().get("EffectVerb"))
						, effectObject = ((String)timeAnnot.getFeatures().get("EffectObject"))
						, effectObjectAdjective = ((String)timeAnnot.getFeatures().get("EffectObjectAdjectives"))
						, effectAdverb = ((String)timeAnnot.getFeatures().get("EffectAdverb"))
						, causeEvent = ((String)timeAnnot.getFeatures().get("Cause"))
						, effectEvent = ((String)timeAnnot.getFeatures().get("Effect"));
				long startNode = ((Long)timeAnnot.getEndNode().getOffset());
				Event concept1 =new Event(startNode, causeEvent, causeVerb, causeAdverb, causeObject, causeObjectAdjective);
				Event concept2 =new Event(startNode, effectEvent, effectVerb, effectAdverb, effectObject, effectObjectAdjective);
				effectOf.add(new EffectOf(concept1, concept2));		


			}
			if(((String)timeAnnot.getType()).equals("EffectOfIsState")){				  
				String causeVerb = ((String)timeAnnot.getFeatures().get("CauseVerb"))
						, causeObject = ((String)timeAnnot.getFeatures().get("CauseObject"))
						, causeObjectAdjective = ((String)timeAnnot.getFeatures().get("CauseObjectAdjectives"))
						, causeAdverb = ((String)timeAnnot.getFeatures().get("CauseAdverb"))
						, effectAdverb = ((String)timeAnnot.getFeatures().get("EffectAdverb"))
						, causeEvent = ((String)timeAnnot.getFeatures().get("Cause"))
						, effectState = ((String)timeAnnot.getFeatures().get("Effect"));
				long startNode = ((Long)timeAnnot.getEndNode().getOffset());
				Event concept1 = new Event(startNode,causeEvent, causeVerb, causeAdverb, causeObject, causeObjectAdjective);
				State concept2 = new State(effectState, effectAdverb);
				effectOfIsState.add(new EffectOfIsState(concept1, concept2));		


			}
			if(((String)timeAnnot.getType()).equals("EventForGoalEvent")){					  
				String goalVerb = ((String)timeAnnot.getFeatures().get("GoalVerb"))
						, goalObject = ((String)timeAnnot.getFeatures().get("GoalObject"))
						, goalObjectAdjective = ((String)timeAnnot.getFeatures().get("GoalObjectAdjectives"))
						, goalAdverb = ((String)timeAnnot.getFeatures().get("GoalAdverb"))
						, eventVerb = ((String)timeAnnot.getFeatures().get("EventVerb"))
						, eventObject = ((String)timeAnnot.getFeatures().get("EventObject"))
						, eventObjectAdjective = ((String)timeAnnot.getFeatures().get("EventObjectAdjectives"))
						, eventAdverb = ((String)timeAnnot.getFeatures().get("EventAdverb"))
						, goalEvent = ((String)timeAnnot.getFeatures().get("Goal"))
						, event = ((String)timeAnnot.getFeatures().get("Event"));
				long startNode = ((Long)timeAnnot.getEndNode().getOffset());

				Event concept1 =new Event(startNode, event, eventVerb, eventAdverb, eventObject, eventObjectAdjective);
				Event concept2 =new Event(startNode, goalEvent, goalVerb, goalAdverb, goalObject, goalObjectAdjective);
				eventForGoalEvent.add(new EventForGoalEvent(concept1, concept2));		


			}

			if(((String)timeAnnot.getType()).equals("EventForGoalState")){					  
				String goalStateAdverb = ((String)timeAnnot.getFeatures().get("GoalStateAdverb"))
						, eventVerb = ((String)timeAnnot.getFeatures().get("EventVerb"))
						, eventObject = ((String)timeAnnot.getFeatures().get("EventObject"))
						, eventObjectAdjective = ((String)timeAnnot.getFeatures().get("EventObjectAdjectives"))
						, eventAdverb = ((String)timeAnnot.getFeatures().get("EventAdverb"))
						, goalState = ((String)timeAnnot.getFeatures().get("GoalState"))
						, event = ((String)timeAnnot.getFeatures().get("Event"));
				long startNode = ((Long)timeAnnot.getEndNode().getOffset());

				Event concept1 = new Event(startNode, event, eventVerb, eventAdverb, eventObject, eventObjectAdjective);
				State concept2 = new State(goalState, goalStateAdverb);
				eventForGoalState.add(new EventForGoalState(concept1, concept2));


			}
			if(((String)timeAnnot.getType()).equals("CauseOfIsState")){				  
				String effectVerb = ((String)timeAnnot.getFeatures().get("EffectVerb"))
						, effectObject = ((String)timeAnnot.getFeatures().get("EffectObject"))
						, effectObjectAdjective = ((String)timeAnnot.getFeatures().get("EffectObjectAdjectives"))
						, effectAdverb = ((String)timeAnnot.getFeatures().get("EffectAdverb"))
						, causeState = ((String)timeAnnot.getFeatures().get("Cause"))
						, causeAdverb = ((String)timeAnnot.getFeatures().get("CauseAdverb"))
						, effectEvent = ((String)timeAnnot.getFeatures().get("Effect"));
				long startNode = ((Long)timeAnnot.getEndNode().getOffset());

				State concept1 = new State(causeState, causeAdverb);
				Event concept2 = new Event(startNode, effectEvent, effectVerb, effectAdverb, effectObject, effectObjectAdjective);
				causeOfIsState.add(new CauseOfIsState(concept1, concept2));		


			}
			if(((String)timeAnnot.getType()).equals("HappensRelation")){				  
				String timeHappened = ((String)timeAnnot.getFeatures().get("TimeHappened"));
				long startNode = ((Long)timeAnnot.getEndNode().getOffset());
				happensRelation.add(new Time(startNode, timeHappened));
			}
			if(((String)timeAnnot.getType()).equals("Event")){				  
				String event = ((String)timeAnnot.getFeatures().get("Entity1"))
						, verb = ((String)timeAnnot.getFeatures().get("Verb"))
						, objects = ((String)timeAnnot.getFeatures().get("Objects"))
						, adjectives = ((String)timeAnnot.getFeatures().get("Adjectives"))
						, adverbs = ((String)timeAnnot.getFeatures().get("Adverbs"));
				long startNode = ((Long)timeAnnot.getEndNode().getOffset());
				events.add(new Event(startNode, event, verb, adverbs, objects, adjectives));
			}
		}
	}

	public String getResultsSummary() {
		return resultsSummary;
	}


	public List<EffectOf> getEffectOf() {
		return effectOf;
	}


	public List<EffectOfIsState> getEffectOfIsState() {
		return effectOfIsState;
	}


	public List<CauseOfIsState> getCauseOfIsState() {
		return causeOfIsState;
	}


	public List<EventForGoalEvent> getEventForGoalEvent() {
		return eventForGoalEvent;
	}


	public List<EventForGoalState> getEventForGoalState() {
		return eventForGoalState;
	}


	public List<Time> getHappensRelation() {
		return happensRelation;
	}


	public List<Event> getEvents() {
		return events;
	}
	public boolean isValid(Event event){
		validate(event.getAdverbs());
		return (event.getVerb()!=null&&event.getVerb()!="");
	}
	public boolean isValid(State state){
		validate(state.getAdverbs());
		return (state.toString()!=null&&state.toString()!="");
	}
	public boolean isValid(Time time){
		return (time.getTimeHappened()!=null&&time.getTimeHappened()!="");
	}
	public void validate(List<String> adverbs){
		for(int i = 0; i<adverbs.size(); i++){
			if(adverbs.get(i).equals("n't")){
				adverbs.set(i, "not");
			}
		}
			
	}
	public void insertEffectOfAssertions(List<EffectOf> assertions){
		System.out.println(assertions.size()+" EffectOf assertions");
		for(EffectOf assertion: assertions){
			if(isValid(assertion.getCause())&&isValid(assertion.getEffect())){
				System.out.println("Current assertion: EffectOf("+assertion.getCause().getVerb()+", "
			+assertion.getEffect().getVerb()+")");
				try{
					SQLiteModel.insertEffectOf(assertion);
				} catch(Exception ex){
					ex.printStackTrace();
					System.err.println("Unsuccessful insertion");
					SQLiteModel.writeLineToLog("Unsuccessful insertion");
				}
			} else{
				System.err.println("Invalid concept");
				SQLiteModel.writeLineToLog("Invalid concept");
			}
		}
	}
	public void insertEffectOfIsStateAssertions(List<EffectOfIsState> assertions){
		System.out.println(assertions.size()+" EffectOfIsState assertions");
		for(EffectOfIsState assertion: assertions){
			if(isValid(assertion.getEvent())&&isValid(assertion.getState())){
				System.out.println("Current assertion: EffectOfIsState("+assertion.getEvent().getVerb()+", "
			+assertion.getState().toString()+")");
				try{
					SQLiteModel.insertEffectOfIsState(assertion);
				} catch(Exception ex){
					ex.printStackTrace();
					System.err.println("Unsuccessful insertion");
					SQLiteModel.writeLineToLog("Unsuccessful insertion");
				}
			} else{
				System.err.println("Invalid concept");
				SQLiteModel.writeLineToLog("Invalid concept");
			}
		}
	}
	public void insertEventForGoalEventAssertions(List<EventForGoalEvent> assertions){
		System.out.println(assertions.size()+" EventForGoalEvent assertions");
		for(EventForGoalEvent assertion: assertions){
			if(isValid(assertion.getTask())&&isValid(assertion.getGoal())){
				System.out.println("Current assertion: EventForGoalEvent("+assertion.getTask().getVerb()+", "
			+assertion.getGoal().getVerb()+")");
				try{
					SQLiteModel.insertEventForGoalEvent(assertion);
				} catch(Exception ex){
					ex.printStackTrace();
					System.err.println("Unsuccessful insertion");
					SQLiteModel.writeLineToLog("Unsuccessful insertion");
				}
			}else{
				System.err.println("Invalid concept");
				SQLiteModel.writeLineToLog("Invalid concept");
			}
		} 
	}
	public void insertEventForGoalStateAssertions(List<EventForGoalState> assertions){
		System.out.println(assertions.size()+" EventForGoalState assertions");
		for(EventForGoalState assertion: assertions){
			if(isValid(assertion.getEvent())&&isValid(assertion.getState())){
				System.out.println("Current assertion: EventForGoalState("+assertion.getEvent().getVerb()+", "
			+assertion.getState().toString()+")");
				try{
					SQLiteModel.insertEventForGoalState(assertion);
				} catch(Exception ex){
					ex.printStackTrace();
					System.err.println("Unsuccessful insertion");
					SQLiteModel.writeLineToLog("Unsuccessful insertion");
				}
			}else{
				System.err.println("Invalid concept");
				SQLiteModel.writeLineToLog("Invalid concept");
			}
		}
	}
	public void insertCauseOfIsStateAssertions(List<CauseOfIsState> assertions){
		System.out.println(assertions.size()+" CauseOfIsState assertions");
		for(CauseOfIsState assertion: assertions){
			if(isValid(assertion.getEvent())&&isValid(assertion.getState())){
				System.out.println("Current assertion: CauseOfIsState("+assertion.getState().toString()+", "
			+assertion.getEvent().getVerb()+")");
				try{
					SQLiteModel.insertCauseOfIsState(assertion);
				} catch(Exception ex){
					ex.printStackTrace();
					System.err.println("Unsuccessful insertion");
					SQLiteModel.writeLineToLog("Unsuccessful insertion");
				}
			}
			else{
				System.err.println("Invalid concept");
				SQLiteModel.writeLineToLog("Invalid concept");
			}
		}
	}
	public void sortEvents(List<Event> events){
		Collections.sort(events, new Comparator<Event>() {
	        @Override
	        public int compare(Event  event1, Event  event2)
	        {
	        	Long start1 = event1.getStartNode();
	        	Long start2 = event2.getStartNode();
	            return  start1.compareTo(start2);
	        }
	    });
	}
	public void sortTimelines(List<Time> timelines){
		Collections.sort(timelines, new Comparator<Time>() {
	        @Override
	        public int compare(Time  time1, Time  time2)
	        {
	        	Long start1 = time1.getStartNode();
	        	Long start2 = time2.getStartNode();
	            return  start1.compareTo(start2);
	        }
	    });
	}
	public void insertHappensAssertions(List<Time> timelines, List<Event> events){
		System.out.println(timelines.size()+" timelines for "+events.size()+" events");
		for(Event event: events){
			for(int i=0; i<timelines.size(); i++){
				if(isValid(event)&&isValid(timelines.get(i))){
					if(event.getStartNode()>=timelines.get(i).getStartNode()){
						if(timelines.size()==i+1||timelines.get(i+1).getStartNode()>event.getStartNode()){
							System.out.println("Current assertion: Happens("+event.getVerb()+", "
									+timelines.get(i).getTimeHappened()+")");
							try{
								SQLiteModel.insertHappens(event, timelines.get(i));
							} catch(Exception ex){
								ex.printStackTrace();
								System.err.println("Unsuccessful insertion");
								SQLiteModel.writeLineToLog("Unsuccessful insertion");
							}
						}
					}
				}
			}
		}
	}
	

	private List<String> separateValues(String values){
		if(values.equals("")||values==null){
			return new ArrayList<String>();
		}
		return new ArrayList<String>(Arrays.asList(values.split("\\.")));
	}
}
