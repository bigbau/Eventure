package objects;

public class EventForGoalState extends Annotation {
	private String reqEvent, goalState, goalStateObject, goalStateObjectAdjective, goalStateAdverb, eventVerb, eventObject, eventObjectAdjective, eventAdverb;

	public EventForGoalState(int startNode, String reqEvent, String goalState,
			String goalStateObject, String goalStateObjectAdjective,
			String goalStateAdverb, String eventVerb, String eventObject,
			String eventObjectAdjective, String eventAdverb) {
		super(startNode);
		this.reqEvent = reqEvent;
		this.goalState = goalState;
		this.goalStateObject = goalStateObject;
		this.goalStateObjectAdjective = goalStateObjectAdjective;
		this.goalStateAdverb = goalStateAdverb;
		this.eventVerb = eventVerb;
		this.eventObject = eventObject;
		this.eventObjectAdjective = eventObjectAdjective;
		this.eventAdverb = eventAdverb;
	}

	public String getReqEvent() {
		return reqEvent;
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
