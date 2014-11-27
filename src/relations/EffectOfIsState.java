package relations;

import concepts.Event;
import concepts.State;

public class EffectOfIsState extends Relation{
	private Event event;
	private State state;
	public EffectOfIsState(Event event, State state) {
		this.event =event;
		this.state=state;
	}

	public State getState() {
		return state;
	}

	public Event getEvent() {
		return event;
	}
	
}
