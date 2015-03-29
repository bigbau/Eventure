package concepts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Concept {
	public final static String EVENT = "event";
	public final static String STATE = "state";
	public final static String TIME = "time";
	protected String conceptName;
	protected Concept(String conceptName){
		this.conceptName = conceptName;
	}
	public String toString(){
		return conceptName;
	}
	protected List<String> separateValues(String values){
		if(values==null||values.equals("")){
			return new ArrayList<String>();
		}
		return new ArrayList<String>(Arrays.asList(values.split(";")));
	}
}
