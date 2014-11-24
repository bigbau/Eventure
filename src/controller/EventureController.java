package controller;

import gate.util.GateException;
import gui.EventureWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Set;

import model.EventureModel;

public class EventureController {

	private static EventureWindow eventureWindow = new EventureWindow();
	private static EventureModel eventureModel = new EventureModel();
	private static String inputFile = "";
	private static Set results = null;

	public static void main(String[] args){
		eventureWindow.initialize();

		try {
			inputFile = eventureModel.stringFromFile("src/sample story.txt");
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

				} catch (GateException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
	}

}
