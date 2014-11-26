package relations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Relation {
	private long startNode;

	
	
	public Relation(long startNode) {
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
