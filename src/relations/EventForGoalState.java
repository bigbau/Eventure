package relations;

import concepts.Event;
import concepts.State;


public class EventForGoalState extends Relation {
	private Event event;
	private State state;
	public EventForGoalState(Event event, State state) {
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
