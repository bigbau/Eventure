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
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RJ
 */
public abstract class WordnetProcessor {
    private final static String path = "dict";
    private final static String stopwordsPath = "stopwords.txt";
    private static IDictionary dict = null;
    //commented below is the code for getting the most general words
    public static void printHyponyms(String word, POS pos){
        Set<String> hyponyms = getHyponyms(word, pos, 2);
        System.out.println("Printing hyponyms for word \""+word+"\"...");
        for(String h: hyponyms){
            System.out.println(h);
        }
    }
    public static Set<String> getHyponyms(ISynset synset, Set<String> hyponyms, int hNum, int depth){
        //get hypornyms
        String t = "";
        List<ISynsetID> hyponymList = synset.getRelatedSynsets(Pointer.HYPONYM);
        List<IWord> words;
        /*for(int i=0;i<hNum;i++){
            t+="\t";
        }*/
        if(hNum>depth){//depth limit
            return hyponyms;
        }
        for (ISynsetID sid : hyponymList) {
            words = dict.getSynset(sid).getWords();
            for (Iterator<IWord> it = words.iterator(); it.hasNext();) {
                hyponyms.add(t+it.next().getLemma());
            }
            hyponyms = getHyponyms(dict.getSynset(sid), hyponyms, hNum+1, depth);
        }
        return hyponyms;
    }
    public static Set<String> getHyponyms(String word, POS pos, int depth){
        openDictionary();
        IIndexWord idxWord = dict .getIndexWord (word, pos);
        Set<String> hyponyms = new HashSet<String>() {};
        for (int i = 0; i < idxWord.getWordIDs().size(); i++) {
            IWordID wordID = idxWord.getWordIDs().get(i); // curr meaning
            IWord wnWord = dict.getWord(wordID);
            ISynset synset = wnWord.getSynset();
            
            hyponyms = getHyponyms(synset, hyponyms, 0, depth);
        }
        return hyponyms;
        
    }
    public static void printGeneralizations(String word1, String word2, POS pos){
        Set<String> generalizations = getGeneralizations(word1, word2, pos);
        System.out.println("printing "+generalizations.size()+" generalizations for "+word1+" and "+word2+"...");
        for(String g: generalizations){
            System.out.println(g);
        }
    }
    public static Set<String> getGeneralizations(String word1, String word2, POS pos){
        Set<String> generalizations = new HashSet<String>();
        Set<String> stopwords = getHyponyms("entity",POS.NOUN,2);
        stopwords.add("entity");
        if (pos == POS.NOUN||pos==POS.VERB) {
            Set<String> word1Hypernyms = getHypernyms(word1, pos);
            Set<String> word2Hypernyms = getHypernyms(word2, pos);
            for (String s1 : word1Hypernyms) {
                for (String s2 : word2Hypernyms) {
                    if (s1.equals(s2)&&!stopwords.contains(s1)) {
                        generalizations.add(s1);
                    }
                }
            }
        }
        return generalizations;
    }
    public static Set<String> getStopwords(){
        Set<String> stopwords = new HashSet<String>();
        try {
            // Open the file that is the first 
            // command line parameter
            FileInputStream fstream = new FileInputStream(stopwordsPath);
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                stopwords.add(strLine);
            }
            //Close the input stream
            in.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
        return stopwords;
    }
    public static void printHypernyms(String word, POS pos){
        Set<String> hypernyms = getHypernyms(word, pos);
        System.out.println("Printing hypernyms for word \""+word+"\"...");
        for(String h: hypernyms){
            System.out.println(h);
        }
    }
    public static Set<String> getHypernyms(ISynset synset, Set<String> hypernyms, int hNum){
        //get hypernyms
        List<ISynsetID> hypernymList = synset.getRelatedSynsets(Pointer.HYPERNYM);
        List<IWord> words;
        if(hypernymList.isEmpty()){//depth limit
            return hypernyms;
        }
        for (ISynsetID sid : hypernymList) {
            words = dict.getSynset(sid).getWords();
            for (Iterator<IWord> it = words.iterator(); it.hasNext();) {
                hypernyms.add(it.next().getLemma());
            }
            hypernyms = getHypernyms(dict.getSynset(sid), hypernyms, hNum+1);
        }
        return hypernyms;
    }
    public static Set<String> getHypernyms(String word, POS pos){
        openDictionary();
        IIndexWord idxWord = dict .getIndexWord (word, pos);
        Set<String> hypernyms = new HashSet<String>() {};
        for (int i = 0; i < idxWord.getWordIDs().size(); i++) {
            IWordID wordID = idxWord.getWordIDs().get(i); // curr meaning
            IWord wnWord = dict.getWord(wordID);
            ISynset synset = wnWord.getSynset();
            
            hypernyms = getHypernyms(synset, hypernyms, 0);
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
