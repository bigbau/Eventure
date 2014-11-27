package concepts;

import java.util.List;

public class Event extends Concept{
	private long startNode;
	private String verb;
	private List<String> adverbs, objects, objectAdjectives;
	public Event(long startNode, String conceptName, String verb, String adverbs, String objects, String objectAdjectives) {
		super(conceptName);
		// TODO Auto-generated constructor stub
		this.startNode=startNode;
		this.verb=verb;
		this.adverbs = super.separateValues(adverbs);
		this.objects = super.separateValues(objects);
		this.objectAdjectives=super.separateValues(objectAdjectives);
	}
	public long getStartNode(){
		return startNode;
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
