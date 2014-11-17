package wordnet;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.ISynsetID;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;
import edu.mit.jwi.item.Pointer;
import edu.mit.jwi.morph.IStemmer;
import edu.mit.jwi.morph.SimpleStemmer;
import edu.mit.jwi.morph.WordnetStemmer;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RJ
 */
public abstract class WordnetProcessor {
    private final static String path = "dict";
    private static IDictionary dict = null;
    public static void printGeneralizations(String word1, String word2, POS pos){
        ArrayList<String> generalizations = getGeneralizations(word1, word2, pos);
        System.out.println("printing generalizations for "+word1+" and "+word2+"...");
        for(String g: generalizations){
            System.out.println(g);
        }
    }
    public static ArrayList<String> getGeneralizations(String word1, String word2, POS pos){
        ArrayList<String> generalizations = new ArrayList<String>();
        if (pos == POS.NOUN) {
            ArrayList<String> word1Hypernyms = getHypernyms(word1);
            ArrayList<String> word2Hypernyms = getHypernyms(word2);
            for (String s1 : word1Hypernyms) {
                for (String s2 : word2Hypernyms) {
                    if (s1.equals(s2)) {
                        generalizations.add(s1);
                    }
                }
            }
        }
        return generalizations;
    }
    public static void printHypernyms(String word){
        ArrayList<String> hypernyms = getHypernyms(word);
        System.out.println("Printing hypernyms for word \""+word+"\"...");
        for(String h: hypernyms){
            System.out.println(h);
        }
    }
    public static ArrayList<String> getHypernyms(String word){
        openDictionary();
        IIndexWord idxWord = dict .getIndexWord (word, POS.NOUN);
        ArrayList<String> hypernyms = new ArrayList<String>();
        for (int i = 0; i < idxWord.getWordIDs().size(); i++) {
            IWordID wordID = idxWord.getWordIDs().get(i); // curr meaning
            IWord wnWord = dict.getWord(wordID);
            ISynset synset = wnWord.getSynset();
            
            //get hypernyms
            List<ISynsetID> hypernymList = synset.getRelatedSynsets(Pointer.HYPERNYM);
            List<IWord> words;
            for (ISynsetID sid : hypernymList) {
                words = dict.getSynset(sid).getWords();
                for (Iterator<IWord> it = words.iterator(); it.hasNext();) {
                    hypernyms.add(it.next().getLemma());
                }
            }
        }
        return hypernyms;
    }
    public static boolean areSynonyms(String word1, String word2, POS pos){
        ArrayList<String> synonyms = getSynonyms(word1, pos);
        SimpleStemmer stemmer= new SimpleStemmer();
        for(String s: synonyms){
            if(normalize(s).toLowerCase().equals(normalize(word2).toLowerCase())){
                return true;
            }
        }
        return false;
    }
    public static String normalize(String word){
        word=word.trim();
        if(word.length()==0){
            throw new IllegalArgumentException();
        }
        for(int i = 0; i<word.length(); i++){
            if (Character.isWhitespace(word.charAt(i))){
                word = word.replaceAll(" ", "_");
                break;
            }
        }
        return word;
    }
    public static void printSynonyms(String word, POS pos){
        ArrayList<String> synonyms = getSynonyms(word, pos);
        System.out.println("Printing synonyms for word \""+word+"\"...");
        for(String s: synonyms){
            System.out.println(s);
        }
    }
    public static ArrayList<String> getSynonyms(String word, POS pos) {
        openDictionary();
        // look up first sense of the word "dog "
        IIndexWord idxWord = dict.getIndexWord(word, pos);
        ArrayList<String> synonyms = new ArrayList<String>();
        for (int i = 0; i < idxWord.getWordIDs().size(); i++) {
            IWordID wordID = idxWord.getWordIDs().get(i); // curr meaning
            IWord wnWord = dict.getWord(wordID);
            ISynset synset = wnWord.getSynset();
            
            // iterate over words associated with the synset
            for (IWord w : synset.getWords()) {
                synonyms.add(w.getLemma());
            }
        }
        return synonyms;
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
