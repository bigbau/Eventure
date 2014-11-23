package objects;

public class CauseOfIsState extends Annotation {
	private String causeState, effectEvent;

	public CauseOfIsState(long startNode, String causeState, String effectEvent) {
		super(startNode);
		this.causeState = causeState;
		this.effectEvent = effectEvent;
	}

	public String getCauseState() {
		return causeState;
	}

	public String getEffectEvent() {
		return effectEvent;
	}
	
}
