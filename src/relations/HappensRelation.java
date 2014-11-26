package relations;

public class HappensRelation extends Relation {
	private String timeHappened;

	public HappensRelation(long startNode, String timeHappened) {
		super(startNode);
		this.timeHappened = timeHappened;
	}

	public String getTimeHappened() {
		return timeHappened;
	}
	
	
}
