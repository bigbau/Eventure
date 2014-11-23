package objects;

public class EventForGoalState extends Annotation {
	private String event, goalState, goalStateObject, goalStateObjectAdjective, goalStateAdverb, eventVerb, eventObject, eventObjectAdjective, eventAdverb;

	public EventForGoalState(long startNode, String event, String goalState,
			String goalStateObject, String goalStateObjectAdjective,
			String goalStateAdverb, String eventVerb, String eventObject,
			String eventObjectAdjective, String eventAdverb) {
		super(startNode);
		this.event = event;
		this.goalState = goalState;
		this.goalStateObject = goalStateObject;
		this.goalStateObjectAdjective = goalStateObjectAdjective;
		this.goalStateAdverb = goalStateAdverb;
		this.eventVerb = eventVerb;
		this.eventObject = eventObject;
		this.eventObjectAdjective = eventObjectAdjective;
		this.eventAdverb = eventAdverb;
	}

	public String getEvent() {
		return event;
	}

	public String getGoalState() {
		return goalState;
	}

	public String getGoalStateObject() {
		return goalStateObject;
	}

	public String getGoalStateObjectAdjective() {
		return goalStateObjectAdjective;
	}

	public String getGoalStateAdverb() {
		return goalStateAdverb;
	}

	public String getEventVerb() {
		return eventVerb;
	}

	public String getEventObject() {
		return eventObject;
	}

	public String getEventObjectAdjective() {
		return eventObjectAdjective;
	}

	public String getEventAdverb() {
		return eventAdverb;
	}
	
	
}
