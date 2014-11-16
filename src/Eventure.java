import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import gate.*;
import gate.gui.MainFrame;
import gate.util.GateException;
import gate.util.persistence.PersistenceManager;


public class Eventure {
	
	
	public static void main(String[] args) throws GateException{
		Gate.init();
		MainFrame.getInstance().setVisible(true);
		try {
			CorpusController application = (CorpusController)PersistenceManager.loadObjectFromFile(new File("src/Eventure.gapp"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document testDocu = Factory.newDocument(stringToFile());
		Corpus testCorpus = Factory.newCorpus("Eventure Corpus");
		testCorpus.add(testDocu);
		//System.out.println(Gate.getPluginsHome());
	}
	
	
	public static String stringToFile(){
		
		String content = "";
		try {
			content = new Scanner(new File("src/sample story.txt")).useDelimiter("\\Z").next();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}


}
