import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import gate.*;
import gate.gui.MainFrame;
import gate.util.GateException;


public class Eventure {
	
	
	public static void main(String[] args) throws GateException{
		
		Gate.init();
		MainFrame.getInstance().setVisible(true);
		Factory.newDocument(stringToFile());
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
