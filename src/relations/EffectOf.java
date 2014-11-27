package relations;

import concepts.Event;

public class EffectOf extends Relation{
	private Event cause, effect;
	public EffectOf(Event cause, Event effect) {
		this.cause = cause;
		this.effect = effect;
	}

	public Event getCause() {
		return cause;
	}

	public Event getEffect() {
		return effect;
	}

	@Override
	public String toString() {
		return "EffectOf [cause=" + cause.toString() + ", effect=" + effect.toString() + "]";
	}
	
}
