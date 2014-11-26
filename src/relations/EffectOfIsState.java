package relations;

import concepts.Event;
import concepts.State;

public class EffectOfIsState extends Relation{
	private Event event;
	private State state;
	public EffectOfIsState(long startNode, Event event, State state) {
		super(startNode);
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
