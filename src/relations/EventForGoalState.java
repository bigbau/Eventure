package relations;

import concepts.Event;
import concepts.State;


public class EventForGoalState extends Relation {
	private Event event;
	private State state;
	public EventForGoalState(long startNode, Event event, State state) {
		super(startNode);
		this.event = event;
		this.state = state;
	}

	public Event getEvent() {
		return event;
	}

	public State getState() {
		return state;
	}
}
