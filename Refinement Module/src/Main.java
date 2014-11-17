
import edu.mit.jwi.item.POS;

/**
 *
 * @author RJ
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        wordnet.WordnetProcessor.printGeneralizations("screw","fuck", POS.VERB);
        
    }
    
}
