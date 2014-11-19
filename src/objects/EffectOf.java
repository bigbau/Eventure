package objects;

public class EffectOf extends Annotation{
	private String causeEvent, effectEvent;

	public EffectOf(int startNode, String causeEvent, String effectEvent) {
		super(startNode);
		this.causeEvent = causeEvent;
		this.effectEvent = effectEvent;
	}

	public String getCauseEvent() {
		return causeEvent;
	}

	public String getEffectEvent() {
		return effectEvent;
	}
	
}
