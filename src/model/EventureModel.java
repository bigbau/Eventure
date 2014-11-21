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
//	private List<EffectOf> effectOf = new ArrayList<EffectOf>();
//	private List<EffectOfIsState> effectOfIsState = new ArrayList<EffectOfIsState>();
//	private List<CauseOfIsState> causeOfIsState = new ArrayList<CauseOfIsState>();
//	private List<EventForGoalEvent>  eventForGoalEvent = new ArrayList<EventForGoalEvent>();
//	private List<EventForGoalState>  eventForGoalState = new ArrayList<EventForGoalState>();
//	private List<HappensRelation>  happensRelation = new ArrayList<HappensRelation>();


	public Set runPipeline(String inputFile) throws GateException{
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

		return annotationsToWrite;

	}
	public String stringFromFile(String location) throws FileNotFoundException{

		String content = "";
			
		content = new Scanner(new File(location)).useDelimiter("\\Z").next();
		
		return content;
	}

	public void addRelationsToLists(Set annotationsToWrite){
		StringBuilder temp = new StringBuilder();
		Iterator nodes = annotationsToWrite.iterator();
		while(nodes.hasNext()) {
			String node = nodes.next().toString();
			temp.append(node);
				//eventure.addMessageArea(node+"\n");
		}
		resultsSummary = temp.toString();
		
	}
	
	public String getResultsSummary() {
		return resultsSummary;
	}
	
	
}
