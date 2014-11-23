import edu.mit.jwi.item.POS;
import java.util.Set;
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
        //Set<String> set1 = WordnetProcessor.findRootWords("pants", POS.NOUN);
        //Set<String> set2 = WordnetProcessor.findRootWords("jeans", POS.NOUN);
        //WordnetProcessor.printGeneralizations(set1,set2, POS.NOUN);
        //System.out.println(WordnetProcessor.areSynonyms(word1, word2, POS.ADJECTIVE));
        SQLiteProcessor.setConnection();
        //SQLiteProcessor.update("");
        SQLiteProcessor.printTable(SQLiteProcessor.select("SELECT * FROM metadata_types"));
    }
}
