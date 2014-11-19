import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import gate.*;
import gate.creole.ResourceInstantiationException;
import gate.gui.MainFrame;
import gate.persist.PersistenceException;
import gate.util.GateException;
import gate.util.persistence.PersistenceManager;
import gui.MainWindow;


public class Eventure {


	private static List extractedAnnotations;
	private static CorpusController application;
	private static MainWindow eventure = new MainWindow();

	private static List effectOf = new ArrayList();
	private static List effectOfIsState = new ArrayList();
	private static List causeOfIsState = new ArrayList();
	private static List eventForGoalEvent = new ArrayList();
	private static List eventForGoalState = new ArrayList();
	private static List happensRelation = new ArrayList();


	public static void main(String[] args){
		eventure.initialize();
		eventure.addBtnRunActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e)
			{
				try {
					run();
				} catch (GateException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

	}

	public static void run() throws GateException{
		Gate.init();
		//MainFrame.getInstance().setVisible(true);
		try {
			application = (CorpusController)PersistenceManager.loadObjectFromFile(new File("src/Eventure.gapp"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document testDocu = Factory.newDocument(stringToFile());
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

		//eventure.setMessageArea(annotationsToWrite.toString());
		addRelationsToLists(annotationsToWrite);

	}
	private static String stringToFile(){

		String content = "";
		try {
			content = new Scanner(new File("src/sample story.txt")).useDelimiter("\\Z").next();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}

	private static void addRelationsToLists(Set annotationsToWrite){

		Iterator nodes = annotationsToWrite.iterator();
		while(nodes.hasNext()) {
			String node = nodes.next().toString();
			if(node.contains("HappensRelation"))
				eventure.addMessageArea("happens: "+node+"\n");
		}
	}
}
