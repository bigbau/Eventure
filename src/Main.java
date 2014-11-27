import concepts.Event;
import concepts.State;
import edu.mit.jwi.item.POS;
import relations.CauseOfIsState;
import relations.EffectOf;
import relations.EffectOfIsState;
import relations.EventForGoalEvent;
import relations.EventForGoalState;
import sqlite.SQLiteProcessor;
import wordnet.WordnetProcessor;

/**
 *
 * @author RJ
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SQLiteProcessor.setConnection();
        Event event = new Event("willingly and gladly gave a ball", "ran", "lap", "willingly","");
        Event event2 = new Event("smiled merrily", "stumbled", "", "badly","");
        //EventForGoalEvent assertion = new EventForGoalEvent(1, event, event2);
        //SQLiteProcessor.insertEventForGoalEvent(assertion);
        State state = new State("furious","incredibly;obviously");
        CauseOfIsState eois = new CauseOfIsState(1,state, event2);
        SQLiteProcessor.insertCauseOfIsState(eois);
    	//WordnetProcessor.printGeneralizations("say", "tell", POS.VERB);
    }
}
