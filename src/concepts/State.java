package concepts;

import java.util.List;

public class State extends Concept {
	private List<String> adverbs;
	public State(String conceptName, String adverbs) {
		super(conceptName);
		// TODO Auto-generated constructor stub
		this.adverbs = super.separateValues(adverbs);
	}
	public List<String> getAdverbs(){
		return adverbs;
	}

}
