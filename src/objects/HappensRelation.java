package objects;

public class HappensRelation extends Annotation {
	private String timeHappened;

	public HappensRelation(long startNode, String timeHappened) {
		super(startNode);
		this.timeHappened = timeHappened;
	}

	public String getTimeHappened() {
		return timeHappened;
	}
	
	
}
