package concepts;

import java.util.List;

public class Event extends Concept{
	private String verb;
	private List<String> adverbs, objects, objectAdjectives;
	public Event(String conceptName, String verb, String adverbs, String objects, String objectAdjectives) {
		super(conceptName);
		// TODO Auto-generated constructor stub
		this.verb=verb;
		this.adverbs = super.separateValues(adverbs);
		this.adverbs = super.separateValues(objects);
		this.objectAdjectives=super.separateValues(objectAdjectives);
	}
	public String getVerb(){
		return verb;
	}
	public List<String> getAdverbs(){
		return adverbs;
	}
	public List<String> getObjects(){
		return objects;
	}
	public List<String> getObjectAdjectives(){
		return objectAdjectives;
	}
}
