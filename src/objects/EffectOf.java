package objects;

public class EffectOf extends Annotation{
	private String causeEvent, effectEvent, causeVerb, causeObject, causeObjectAdjective, causeAdverb, effectVerb, effectObject, effectObjectAdjective, effectAdverb;
	
	public EffectOf(long startNode, String causeEvent, String effectEvent,
			String causeVerb, String causeObject, String causeObjectAdjective,
			String causeAdverb, String effectVerb, String effectObject,
			String effectObjectAdjective, String effectAdverb) {
		super(startNode);
		this.causeEvent = causeEvent;
		this.effectEvent = effectEvent;
		this.causeVerb = causeVerb;
		this.causeObject = causeObject;
		this.causeObjectAdjective = causeObjectAdjective;
		this.causeAdverb = causeAdverb;
		this.effectVerb = effectVerb;
		this.effectObject = effectObject;
		this.effectObjectAdjective = effectObjectAdjective;
		this.effectAdverb = effectAdverb;
	}

	public String getCauseEvent() {
		return causeEvent;
	}

	public String getEffectEvent() {
		return effectEvent;
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

	public String getEffectVerb() {
		return effectVerb;
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
