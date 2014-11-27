package model;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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


public class EventureModel {


	private List extractedAnnotations;
	private CorpusController application;
	private String resultsSummary;

	private List<EffectOf> effectOf = new ArrayList<EffectOf>();
	private List<EffectOfIsState> effectOfIsState = new ArrayList<EffectOfIsState>();
	private List<CauseOfIsState> causeOfIsState = new ArrayList<CauseOfIsState>();
	private List<EventForGoalEvent>  eventForGoalEvent = new ArrayList<EventForGoalEvent>();
	private List<EventForGoalState>  eventForGoalState = new ArrayList<EventForGoalState>();
	private List<Time>  happensRelation = new ArrayList<Time>();
	private List<Event> events = new ArrayList<Event>();


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
						, effectEvent = ((String)timeAnnot.getFeatures().get("Effect"));
				long startNode = ((Long)timeAnnot.getEndNode().getOffset());

				State concept1 = new State(causeState, effectAdverb);
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

}
