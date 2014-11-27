package concepts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Concept {
	protected String conceptName;
	protected Concept(String conceptName){
		this.conceptName = conceptName;
	}
	public String toString(){
		return conceptName;
	}
	protected List<String> separateValues(String values){
		if(values.equals("")||values==null){
			return new ArrayList<String>();
		}
		return new ArrayList<String>(Arrays.asList(values.split(";")));
	}
}
