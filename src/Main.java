import concepts.Event;
import edu.mit.jwi.item.POS;
import relations.EffectOf;
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
        Event causeEvent = new Event("willingly and gladly gave a ball", "ran", "lap", "willingly","");
        Event effectEvent = new Event("smiled merrily", "stumbled", "", "badly","");
        EffectOf assertion = new EffectOf(1, causeEvent, effectEvent);
        SQLiteProcessor.insertEffectOf(assertion);
    	//WordnetProcessor.printGeneralizations("say", "tell", POS.VERB);
    }
}
