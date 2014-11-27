package relations;

import concepts.Event;
import concepts.State;

public class CauseOfIsState extends Relation {
	private State state;
	private Event event;
	public CauseOfIsState(State state, Event event) {
		this.state = state;
		this.event = event;
	}

	public State getState() {
		return state;
	}

	public Event getEvent() {
		return event;
	}

	@Override
	public String toString() {
		return "CauseOfIsState [state=" + state.toString() + ", event=" + event.toString() + "]";
	}

}
