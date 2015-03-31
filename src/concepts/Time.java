package concepts;

import relations.Relation;

public class Time extends Relation {
	long startNode;
	private String timeHappened;

	public Time(long startNode, String timeHappened) {
		this.startNode=startNode;
		this.timeHappened = timeHappened;
	}

	public String getTimeHappened() {
		return timeHappened;
	}
	public long getStartNode(){
		return startNode;
	}
	
	
}
