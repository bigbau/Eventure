package relations;

import concepts.Event;

public class EffectOf extends Relation{
	private Event cause, effect;
	public EffectOf(long startNode, Event cause, Event effect) {
		super(startNode);
		this.cause = cause;
		this.effect = effect;
	}

	public Event getCause() {
		return cause;
	}

	public Event getEffect() {
		return effect;
	}
	
}
