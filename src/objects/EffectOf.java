package objects;

import java.util.List;

public class EffectOf extends Annotation{
	private String causeEvent, effectEvent, causeVerb, effectVerb;
private List<String> causeObjectAdjective, causeAdverb, effectObjectAdjective, effectAdverb, causeObject, effectObject;
	public EffectOf(long startNode, String causeEvent, String effectEvent,
			String causeVerb, String causeObject, String causeObjectAdjective,
			String causeAdverb, String effectVerb, String effectObject,
			String effectObjectAdjective, String effectAdverb) {
		super(startNode);
		this.causeEvent = causeEvent;
		this.effectEvent = effectEvent;
		this.causeVerb = causeVerb;
		this.causeObject = separateValues(causeObject);
		this.causeObjectAdjective = separateValues(causeObjectAdjective);
		this.causeAdverb = separateValues(causeAdverb);
		this.effectVerb = effectVerb;
		this.effectObject = separateValues(effectObject);
		this.effectObjectAdjective = separateValues(effectObjectAdjective);
		this.effectAdverb = separateValues(effectAdverb);
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

	public List<String> getCauseObject() {
		return causeObject;
	}

	public List<String> getCauseObjectAdjective() {
		return causeObjectAdjective;
	}

	public List<String> getCauseAdverb() {
		return causeAdverb;
	}

	public String getEffectVerb() {
		return effectVerb;
	}

	public List<String> getEffectObject() {
		return effectObject;
	}

	public List<String> getEffectObjectAdjective() {
		return effectObjectAdjective;
	}

	public List<String> getEffectAdverb() {
		return effectAdverb;
	}
	
}
