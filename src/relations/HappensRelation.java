package relations;

public class HappensRelation extends Relation {
	long startNode;
	private String timeHappened;

	public HappensRelation(long startNode, String timeHappened) {
		this.startNode=startNode;
		this.timeHappened = timeHappened;
	}

	public String getTimeHappened() {
		return timeHappened;
	}
	
	
}
