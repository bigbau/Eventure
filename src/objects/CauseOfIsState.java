package objects;

import java.util.List;

public class CauseOfIsState extends Annotation {
	private String causeState, effectEvent, effectVerb;
	private List<String> causeObjectAdjective, causeAdverb, effectObjectAdjective, effectAdverb, causeObject, effectObject;
	public CauseOfIsState(long startNode, String causeState,
			String effectEvent, String effectVerb, String causeObject,
			String causeObjectAdjective, String causeAdverb,
			String effectObject, String effectObjectAdjective,
			String effectAdverb) {
		super(startNode);
		this.causeState = causeState;
		this.effectEvent = effectEvent;
		this.causeObject = separateValues(causeObject);
		this.causeObjectAdjective = separateValues(causeObjectAdjective);
		this.causeAdverb = separateValues(causeAdverb);
		this.effectObject = separateValues(effectObject);
		this.effectVerb = effectVerb;
		this.effectObjectAdjective = separateValues(effectObjectAdjective);
		this.effectAdverb = separateValues(effectAdverb);
	}

	public String getStateState() {
		return causeState;
	}

	public String getEffectEvent() {
		return effectEvent;
	}

	public String getEffectVerb() {
		return effectVerb;
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
