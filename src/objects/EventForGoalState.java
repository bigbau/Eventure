package objects;

public class EventForGoalState extends Annotation {
	private String reqEvent, goalState;

	public EventForGoalState(int startNode, String reqEvent, String goalState) {
		super(startNode);
		this.reqEvent = reqEvent;
		this.goalState = goalState;
	}

	public String getReqEvent() {
		return reqEvent;
	}

	public String getGoalState() {
		return goalState;
	}
	
	
}
