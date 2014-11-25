package objects;

import java.util.List;

public class EffectOfIsState extends Annotation{
	private String effectState, causeEvent, causeVerb;
	private List<String> causeObjectAdjective, causeAdverb, effectObjectAdjective, effectAdverb, causeObject, effectObject;
	public EffectOfIsState(long startNode, String effectState,
			String causeEvent, String causeVerb, String causeObject,
			String causeObjectAdjective, String causeAdverb,
			String effectObject, String effectObjectAdjective,
			String effectAdverb) {
		super(startNode);
		this.effectState = effectState;
		this.causeEvent = causeEvent;
		this.causeVerb = causeVerb;
		this.causeObject = separateValues(causeObject);
		this.causeObjectAdjective = separateValues(causeObjectAdjective);
		this.causeAdverb = separateValues(causeAdverb);
		this.effectObject = separateValues(effectObject);
		this.effectObjectAdjective = separateValues(effectObjectAdjective);
		this.effectAdverb = separateValues(effectAdverb);
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

	public List<String> getCauseObject() {
		return causeObject;
	}

	public List<String> getCauseObjectAdjective() {
		return causeObjectAdjective;
	}

	public List<String> getCauseAdverb() {
		return causeAdverb;
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
