import edu.mit.jwi.item.POS;
import sqlite.SQLiteProcessor;
import wordnet.WordnetProcessor;
import objects.EffectOf;

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
        EffectOf assertion = new EffectOf(1, "gave a ball", "smiled immediately",
    			"shit", "ball", "",
    			"", "ran", "",
    			"", "");
        SQLiteProcessor.insertEffectOf(assertion);
    }
}
