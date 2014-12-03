package relations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Relation {
	public static final String CAUSEOFISSTATE = "CauseOfIsState";
	public static final String HAPPENS = "Happens";
	public static final String EFFECTOF = "EffectOf";
	public static final String EFFECTOFISSTATE = "EffectOfIsState";
	public static final String EVENTFORGOALEVENT = "EventForGoalEvent";
	public static final String EVENTFORGOALSTATE = "EventForGoalState";
	protected List<String> separateValues(String values){
		if(values.isEmpty()){
			return new ArrayList<String>();
		}
		return new ArrayList<String>(Arrays.asList(values.split(";")));
	}
}