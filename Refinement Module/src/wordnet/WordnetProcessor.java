package wordnet;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.IPointer;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;
import edu.mit.jwi.item.Pointer;
import edu.mit.jwi.morph.IStemmer;
import edu.mit.jwi.morph.WordnetStemmer;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RJ
 */
public abstract class WordnetProcessor {
    final static String path = "dict";
    static IDictionary dict = null;
    public static boolean isSynonym(String word1, String word2, POS pos){
        return false;
    }
    public static String findRoot(String word, POS pos){
        openDictionary();
        IStemmer stemmer= new WordnetStemmer(dict);
        List<String> stems = stemmer.findStems(word, pos);
        return stems.get(stems.size()-1);
    }
    public static void printWordData(String word, POS pos){
        word = findRoot(word, pos);
        IIndexWord idxWord = dict.getIndexWord(word, pos);
        IWordID wordID = idxWord.getWordIDs().get(16);
        IWord wnWord = dict.getWord(wordID);
        
        System.out.println("Id = "+wordID);
        System.out.println("Lemma = " + wnWord.getLemma());
        System.out.println("Gloss = " + wnWord.getSynset().getGloss());
        
    }
    private static void openDictionary(){
        if(dict==null){
            //WordNet dictionary directory
            URL url = null;
            try{
                url = new URL("file", null, path);
            } catch(MalformedURLException e){
                e.printStackTrace();
            }
            if(url==null){
                return;
            }
            dict = new Dictionary(url);
        }
        try {
            if(!dict.isOpen())
            dict.open();
        } catch (IOException ex) {
            Logger.getLogger(WordnetProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
