package objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Annotation {
	private long startNode;

	
	
	public Annotation(long startNode) {
		this.startNode = startNode;
	}


	
	protected List<String> separateValues(String values){
		if(values.isEmpty()){
			return new ArrayList<String>();
		}
		return new ArrayList<String>(Arrays.asList(values.split(";")));
	}
	

	public long getStartNode() {
		return startNode;
	}
	 
}
