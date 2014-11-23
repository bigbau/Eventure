package objects;

public class EventForGoalEvent extends Annotation {
	private String reqEvent, goalEvent, goalVerb, goalObject, goalObjectAdjective, goalAdverb, eventVerb, eventObject, eventObjectAdjective, eventAdverb;

	public EventForGoalEvent(int startNode, String reqEvent, String goalEvent,
			String goalVerb, String goalObject, String goalObjectAdjective,
			String goalAdverb, String eventVerb, String eventObject,
			String eventObjectAdjective, String eventAdverb) {
		super(startNode);
		this.reqEvent = reqEvent;
		this.goalEvent = goalEvent;
		this.goalVerb = goalVerb;
		this.goalObject = goalObject;
		this.goalObjectAdjective = goalObjectAdjective;
		this.goalAdverb = goalAdverb;
		this.eventVerb = eventVerb;
		this.eventObject = eventObject;
		this.eventObjectAdjective = eventObjectAdjective;
		this.eventAdverb = eventAdverb;
	}

	public String getReqEvent() {
		return reqEvent;
	}

	public String getGoalEvent() {
		return goalEvent;
	}

	public String getGoalVerb() {
		return goalVerb;
	}

	public String getGoalObject() {
		return goalObject;
	}

	public String getGoalObjectAdjective() {
		return goalObjectAdjective;
	}

	public String getGoalAdverb() {
		return goalAdverb;
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
