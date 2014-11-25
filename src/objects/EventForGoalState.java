package objects;

import java.util.List;

public class EventForGoalState extends Annotation {
	private String event, goalState, eventVerb;
	private List<String> goalStateObjectAdjective, goalStateAdverb, eventObjectAdjective, eventAdverb, eventObject, goalStateObject;
	public EventForGoalState(long startNode, String event, String goalState,
			String goalStateObject, String goalStateObjectAdjective,
			String goalStateAdverb, String eventVerb, String eventObject,
			String eventObjectAdjective, String eventAdverb) {
		super(startNode);
		this.event = event;
		this.goalState = goalState;
		this.goalStateObject = separateValues(goalStateObject);
		this.goalStateObjectAdjective = separateValues(goalStateObjectAdjective);
		this.goalStateAdverb = separateValues(goalStateAdverb);
		this.eventVerb = eventVerb;
		this.eventObject = separateValues(eventObject);
		this.eventObjectAdjective = separateValues(eventObjectAdjective);
		this.eventAdverb = separateValues(eventAdverb);
	}

	public String getEvent() {
		return event;
	}

	public String getGoalState() {
		return goalState;
	}

	public List<String> getGoalStateObject() {
		return goalStateObject;
	}

	public List<String> getGoalStateObjectAdjective() {
		return goalStateObjectAdjective;
	}

	public List<String> getGoalStateAdverb() {
		return goalStateAdverb;
	}

	public String getEventVerb() {
		return eventVerb;
	}

	public List<String> getEventObject() {
		return eventObject;
	}

	public List<String> getEventObjectAdjective() {
		return eventObjectAdjective;
	}

	public List<String> getEventAdverb() {
		return eventAdverb;
	}
	
	
}
