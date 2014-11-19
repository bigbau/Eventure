package objects;

public class EventForGoalEvent extends Annotation {
	private String reqEvent, goalEvent;

	public EventForGoalEvent(int startNode, String reqEvent, String goalEvent) {
		super(startNode);
		this.reqEvent = reqEvent;
		this.goalEvent = goalEvent;
	}

	public String getReqEvent() {
		return reqEvent;
	}

	public String getGoalEvent() {
		return goalEvent;
	}
	
	
}
