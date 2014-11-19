
import edu.mit.jwi.item.POS;
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
        String word1 = WordnetProcessor.findRoot("crack", POS.NOUN);
        String word2 = WordnetProcessor.findRoot("cocaine", POS.NOUN);
        WordnetProcessor.printGeneralizations(word1, word2, POS.NOUN);
       // WordnetProcessor.printHyponyms("entity", POS.NOUN);
    }
}
