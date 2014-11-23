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

import gate.Annotation;
import gate.AnnotationSet;
import gate.Corpus;
import gate.CorpusController;
import gate.Document;
import gate.Factory;
import gate.Gate;
import gate.util.GateException;
import gate.util.persistence.PersistenceManager;


import objects.CauseOfIsState;
import objects.EffectOf;
import objects.EffectOfIsState;
import objects.EventForGoalEvent;
import objects.EventForGoalState;
import objects.HappensRelation;


public class EventureModel {


	private List extractedAnnotations;
	private CorpusController application;
	private String resultsSummary;

	private List<EffectOf> effectOf = new ArrayList<EffectOf>();
	private List<EffectOfIsState> effectOfIsState = new ArrayList<EffectOfIsState>();
	private List<CauseOfIsState> causeOfIsState = new ArrayList<CauseOfIsState>();
	private List<EventForGoalEvent>  eventForGoalEvent = new ArrayList<EventForGoalEvent>();
	private List<EventForGoalState>  eventForGoalState = new ArrayList<EventForGoalState>();
	private List<HappensRelation>  happensRelation = new ArrayList<HappensRelation>();



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
		annotTypesToWrite.add("EventForGoalState");;
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
				results.append("ID: "+id+" ,");
				results.append("\n");
			}
		}
		
		if(effectOfIsState.size()>0){
			results.append("EffectOf Relations:\n");
			Iterator temp = effectOfIsState.iterator();
			int id = 0;
			while(temp.hasNext()){
				id++;
				EffectOfIsState holder = (EffectOfIsState)temp.next();
				results.append("ID: "+id+" ,");
				results.append("\n");
			}
		}
		
		if(causeOfIsState.size()>0){
			results.append("EffectOf Relations:\n");
			Iterator temp = causeOfIsState.iterator();
			int id = 0;
			while(temp.hasNext()){
				id++;
				CauseOfIsState holder = (CauseOfIsState)temp.next();
				results.append("ID: "+id+" ,");
				results.append("\n");
			}
		}
		
		if(eventForGoalEvent.size()>0){
			results.append("EffectOf Relations:\n");
			Iterator temp = eventForGoalEvent.iterator();
			int id = 0;
			while(temp.hasNext()){
				id++;
				EventForGoalEvent holder = (EventForGoalEvent)temp.next();
				results.append("ID: "+id+" ,");
				results.append("\n");
			}
		}
		
		if(eventForGoalState.size()>0){
			results.append("EffectOf Relations:\n");
			Iterator temp = eventForGoalState.iterator();
			int id = 0;
			while(temp.hasNext()){
				id++;
				EventForGoalState holder = (EventForGoalState)temp.next();
				results.append("ID: "+id+" ,");
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
						, causeObjectAdjective = ((String)timeAnnot.getFeatures().get("CauseObjectAdjective"))
						, causeAdverb = ((String)timeAnnot.getFeatures().get("CauseAdverb"))
						, effectVerb = ((String)timeAnnot.getFeatures().get("EffectVerb"))
						, effectObject = ((String)timeAnnot.getFeatures().get("EffectObject"))
						, effectObjectAdjective = ((String)timeAnnot.getFeatures().get("EffectObjectAdjective"))
						, effectAdverb = ((String)timeAnnot.getFeatures().get("EffectAdverb"))
						, causeEvent = ((String)timeAnnot.getFeatures().get("Cause"))
						, effectEvent = ((String)timeAnnot.getFeatures().get("Effect"));
				long startNode = ((Long)timeAnnot.getEndNode().getOffset());

				effectOf.add(new EffectOf(startNode, causeEvent, effectEvent, causeVerb, causeObject, causeObjectAdjective, causeAdverb, effectVerb, effectObject,effectObjectAdjective, effectAdverb));		


			}
			if(((String)timeAnnot.getType()).equals("EffectOfIsState")){				  
				String causeVerb = ((String)timeAnnot.getFeatures().get("CauseVerb"))
						, causeObject = ((String)timeAnnot.getFeatures().get("CauseObject"))
						, causeObjectAdjective = ((String)timeAnnot.getFeatures().get("CauseObjectAdjective"))
						, causeAdverb = ((String)timeAnnot.getFeatures().get("CauseAdverb"))
						, effectObject = ((String)timeAnnot.getFeatures().get("EffectObject"))
						, effectObjectAdjective = ((String)timeAnnot.getFeatures().get("EffectObjectAdjective"))
						, effectAdverb = ((String)timeAnnot.getFeatures().get("EffectAdverb"))
						, causeEvent = ((String)timeAnnot.getFeatures().get("Cause"))
						, effectEvent = ((String)timeAnnot.getFeatures().get("Effect"));
				long startNode = ((Long)timeAnnot.getEndNode().getOffset());

				effectOfIsState.add(new EffectOfIsState(startNode, causeEvent, effectEvent, causeVerb, causeObject, causeObjectAdjective, causeAdverb, effectObject,effectObjectAdjective, effectAdverb));		


			}
			if(((String)timeAnnot.getType()).equals("EventForGoalEvent")){					  
				String goalVerb = ((String)timeAnnot.getFeatures().get("GoalVerb"))
						, goalObject = ((String)timeAnnot.getFeatures().get("GoalObject"))
						, goalObjectAdjective = ((String)timeAnnot.getFeatures().get("GoalObjectAdjective"))
						, goalAdverb = ((String)timeAnnot.getFeatures().get("GoalAdverb"))
						, eventVerb = ((String)timeAnnot.getFeatures().get("EventVerb"))
						, eventObject = ((String)timeAnnot.getFeatures().get("EventObject"))
						, eventObjectAdjective = ((String)timeAnnot.getFeatures().get("EventObjectAdjective"))
						, eventAdverb = ((String)timeAnnot.getFeatures().get("EventAdverb"))
						, goalEvent = ((String)timeAnnot.getFeatures().get("Goal"))
						, event = ((String)timeAnnot.getFeatures().get("Event"));
				long startNode = ((Long)timeAnnot.getEndNode().getOffset());

				eventForGoalEvent.add(new EventForGoalEvent(startNode, event, goalEvent, goalVerb, goalObject, goalObjectAdjective, goalAdverb, eventVerb, eventObject,eventObjectAdjective, eventAdverb));		


			}

			if(((String)timeAnnot.getType()).equals("EventForGoalState")){					  
				String goalObject = ((String)timeAnnot.getFeatures().get("GoalObject"))
						, goalObjectAdjective = ((String)timeAnnot.getFeatures().get("GoalObjectAdjective"))
						, goalAdverb = ((String)timeAnnot.getFeatures().get("GoalAdverb"))
						, eventVerb = ((String)timeAnnot.getFeatures().get("EventVerb"))
						, eventObject = ((String)timeAnnot.getFeatures().get("EventObject"))
						, eventObjectAdjective = ((String)timeAnnot.getFeatures().get("EventObjectAdjective"))
						, eventAdverb = ((String)timeAnnot.getFeatures().get("EventAdverb"))
						, goalEvent = ((String)timeAnnot.getFeatures().get("Goal"))
						, event = ((String)timeAnnot.getFeatures().get("Event"));
				long startNode = ((Long)timeAnnot.getEndNode().getOffset());

				eventForGoalState.add(new EventForGoalState(startNode, event, goalEvent, goalObject, goalObjectAdjective, goalAdverb, eventVerb, eventObject,eventObjectAdjective, eventAdverb));		


			}
			if(((String)timeAnnot.getType()).equals("HappensRelation")){				  
				String timeHappened = ((String)timeAnnot.getFeatures().get("TimeHappened"));
				long startNode = ((Long)timeAnnot.getEndNode().getOffset());
				happensRelation.add(new HappensRelation(startNode, timeHappened));
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


	public List<HappensRelation> getHappensRelation() {
		return happensRelation;
	}

}
