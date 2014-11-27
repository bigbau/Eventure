package relations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Relation {
	protected List<String> separateValues(String values){
		if(values.isEmpty()){
			return new ArrayList<String>();
		}
		return new ArrayList<String>(Arrays.asList(values.split(";")));
	}
}