package objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventForGoalEvent extends Annotation {
	private String event, goal, goalVerb, goalObject, eventVerb, eventObject;
	private List<String> goalObjectAdjective, goalAdverb, eventObjectAdjective, eventAdverb;
	public EventForGoalEvent(long startNode, String event, String goalEvent,
			String goalVerb, String goalObject, String goalObjectAdjective,
			String goalAdverb, String eventVerb, String eventObject,
			String eventObjectAdjective, String eventAdverb) {
		super(startNode);
		this.event = event;
		this.goal = goalEvent;
		this.goalVerb = goalVerb;
		this.goalObject = goalObject;
		this.goalObjectAdjective = separateValues(goalObjectAdjective);
		this.goalAdverb = separateValues(goalAdverb);
		this.eventVerb = eventVerb;
		this.eventObject = eventObject;
		this.eventObjectAdjective = separateValues(eventObjectAdjective);
		this.eventAdverb = separateValues(eventAdverb);
	}
	
	public String getEvent() {
		return event;
	}

	public String getGoalEvent() {
		return goal;
	}

	public String getGoalVerb() {
		return goalVerb;
	}

	public String getGoalObject() {
		return goalObject;
	}

	public List<String> getGoalObjectAdjective() {
		return goalObjectAdjective;
	}

	public List<String> getGoalAdverb() {
		return goalAdverb;
	}

	public String getEventVerb() {
		return eventVerb;
	}

	public String getEventObject() {
		return eventObject;
	}

	public List<String> getEventObjectAdjective() {
		return eventObjectAdjective;
	}

	public List<String> getEventAdverb() {
		return eventAdverb;
	}
}
