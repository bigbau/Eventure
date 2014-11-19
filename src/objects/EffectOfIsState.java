package objects;

public class EffectOfIsState extends Annotation{
	private String effectState, causeEvent;

	public EffectOfIsState(int startNode, String effectState, String causeEvent) {
		super(startNode);
		this.effectState = effectState;
		this.causeEvent = causeEvent;
	}

	public String getEffectState() {
		return effectState;
	}

	public String getCauseEvent() {
		return causeEvent;
	}
	
}
