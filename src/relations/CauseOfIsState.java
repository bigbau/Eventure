package relations;

import concepts.Event;
import concepts.State;

public class CauseOfIsState extends Relation {
	private State state;
	private Event event;
	public CauseOfIsState(long startNode, State state, Event event) {
		super(startNode);
		this.state = state;
		this.event = event;
	}

	public State getState() {
		return state;
	}

	public Event getEvent() {
		return event;
	}

}
