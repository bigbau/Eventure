import edu.mit.jwi.item.POS;
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
        WordnetProcessor.printGeneralizations("pants","jeans", POS.NOUN);
        //System.out.println(WordnetProcessor.areSynonyms(word1, word2, POS.ADJECTIVE));
        SQLiteProcessor.setConnection();
        //SQLiteProcessor.update("");
        SQLiteProcessor.printTable(SQLiteProcessor.select("SELECT * FROM metadata_types"));
    }
}
