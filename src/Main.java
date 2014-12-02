import model.SQLiteModel;
import model.WordnetModel;
import concepts.Event;
import concepts.State;
import edu.mit.jwi.item.POS;
import relations.CauseOfIsState;
import relations.EffectOf;
import relations.EffectOfIsState;
import relations.EventForGoalEvent;
import relations.EventForGoalState;

/**
 *
 * @author RJ
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SQLiteModel.setConnection();
    	SQLiteModel.closeConnection();
    }
}
