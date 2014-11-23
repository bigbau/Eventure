package objects;

public class EffectOfIsState extends Annotation{
	private String effectState, causeEvent, causeVerb, causeObject, causeObjectAdjective, causeAdverb, effectObject, effectObjectAdjective, effectAdverb;
	
	public EffectOfIsState(int startNode, String effectState,
			String causeEvent, String causeVerb, String causeObject,
			String causeObjectAdjective, String causeAdverb,
			String effectObject, String effectObjectAdjective,
			String effectAdverb) {
		super(startNode);
		this.effectState = effectState;
		this.causeEvent = causeEvent;
		this.causeVerb = causeVerb;
		this.causeObject = causeObject;
		this.causeObjectAdjective = causeObjectAdjective;
		this.causeAdverb = causeAdverb;
		this.effectObject = effectObject;
		this.effectObjectAdjective = effectObjectAdjective;
		this.effectAdverb = effectAdverb;
	}

	public String getEffectState() {
		return effectState;
	}

	public String getCauseEvent() {
		return causeEvent;
	}

	public String getCauseVerb() {
		return causeVerb;
	}

	public String getCauseObject() {
		return causeObject;
	}

	public String getCauseObjectAdjective() {
		return causeObjectAdjective;
	}

	public String getCauseAdverb() {
		return causeAdverb;
	}

	public String getEffectObject() {
		return effectObject;
	}

	public String getEffectObjectAdjective() {
		return effectObjectAdjective;
	}

	public String getEffectAdverb() {
		return effectAdverb;
	}
	
}
