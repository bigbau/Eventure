/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.sqlite.SQLiteConfig;

import wordnet.WordnetProcessor;
import edu.mit.jwi.item.POS;
import objects.CauseOfIsState;
import objects.EffectOf;
import objects.EffectOfIsState;
import objects.EventForGoalEvent;
import objects.EventForGoalState;
import objects.HappensRelation;

/**
 *
 * @author RJ
 */
public abstract class SQLiteProcessor {
    private static Connection c = null;
    public static final String DB_URL = "jdbc:sqlite:eventure.db";  
    public static final String DRIVER = "org.sqlite.JDBC";  
    public static void setConnection() {
        try {
        	SQLiteConfig config = new SQLiteConfig();  
        	config.enforceForeignKeys(true); 
            Class.forName(DRIVER);
            c = DriverManager.getConnection(DB_URL,config.toProperties());
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    public static void printTable(List<Map<String, String>> data) {
        Set<String> columnNames = new HashSet<String>();
        try {
            for(String columnName:data.get(0).keySet()){
                System.out.print(columnName +"\t");
                columnNames.add(columnName);
            }
            System.out.println();
            for(Map<String, String> h: data){
                for (String columnName: columnNames) {
                    System.out.print(h.get(columnName) + "\t");
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    private static List<Map<String, String>> select(String query) {
        ResultSet rs = null;
        Statement stmt = null;
        int first =1;
        List<String> columnNames = new ArrayList<String>();
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        try {
            stmt = c.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                int count = rsmd.getColumnCount();
                if (first == 1) {
                    for (int i = 1; i <= count; i++) {
                        columnNames.add(rsmd.getColumnName(i));
                    }
                }
                Map<String, String> curr = new HashMap<String, String>();
                for (int i = 1; i <= count; i++) {
                    curr.put(columnNames.get(i-1), rs.getString(i));
                }
                data.add(curr);
                first++;
            }
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return data;
    }
    public static void insertEffectOf(EffectOf assertion){
    	String concept1 = WordnetProcessor.findRootWord(assertion.getCauseVerb(),POS.VERB);
    	String concept2 = WordnetProcessor.findRootWord(assertion.getEffectVerb(),POS.VERB);
    	int concept1Id = getConceptID(concept1, "event");
    	int concept2Id = getConceptID(concept2, "event");
    	int assertionId = getAssertionID(concept1Id, concept2Id, "EffectOf");
    	
    	findRelationships(concept1, concept1Id, "event");
    	findRelationships(concept2, concept2Id, "event");
    	
    	List<String> causeAdverbs = assertion.getCauseAdverb();
    	List<String> effectAdverbs = assertion.getEffectAdverb();
    	
    	for(String causeAdverb: causeAdverbs){
    		int metadatumId = getMetadatumID(causeAdverb, "adverb", concept1Id, assertionId);
    		findMetadataRelationships(causeAdverb, metadatumId, "adverb", concept1Id, assertionId);
    	}
    	for(String effectAdverb: effectAdverbs){
    		int metadatumId = getMetadatumID(effectAdverb, "adverb", concept2Id, assertionId);
    		findMetadataRelationships(effectAdverb, metadatumId, "adverb", concept2Id, assertionId);
    	}
    	
    	List<String> causeObjects = assertion.getCauseObject();
    	List<String> effectObjects = assertion.getEffectObject();
    	
    	for(String causeObject: causeObjects){
    		int metadatumId = getMetadatumID(causeObject, "object", concept1Id, assertionId);
    		findMetadataRelationships(causeObject, metadatumId, "object", concept1Id, assertionId);
    	}
    	for(String effectObject: effectObjects){
    		int metadatumId = getMetadatumID(effectObject, "object", concept2Id, assertionId);
    		findMetadataRelationships(effectObject, metadatumId, "object", concept2Id, assertionId);
    	}
    }
    public static void insertEffectOfIsState(EffectOfIsState assertion){
    	String concept1 = WordnetProcessor.findRootWord(assertion.getCauseVerb(),POS.VERB);
    	String concept2 = WordnetProcessor.findRootWord(assertion.getEffectState(),POS.ADJECTIVE);
    	int concept1Id = getConceptID(concept1, "event");
    	int concept2Id = getConceptID(concept2, "state");
    	int assertionId = getAssertionID(concept1Id, concept2Id, "EffectOfIsState");
    	
    	findRelationships(concept1, concept1Id, "event");
    	findRelationships(concept2, concept2Id, "state");
    	
    	List<String> causeAdverbs = assertion.getCauseAdverb();
    	List<String> effectAdverbs = assertion.getEffectAdverb();
    	
    	for(String causeAdverb: causeAdverbs){
    		int metadatumId = getMetadatumID(causeAdverb, "adverb", concept1Id, assertionId);
    		findMetadataRelationships(causeAdverb, metadatumId, "adverb", concept1Id, assertionId);
    	}
    	for(String effectAdverb: effectAdverbs){
    		int metadatumId = getMetadatumID(effectAdverb, "adverb", concept2Id, assertionId);
    		findMetadataRelationships(effectAdverb, metadatumId, "adverb", concept2Id, assertionId);
    	}
    }
    public static void insertCauseOfIsState(CauseOfIsState assertion){
    	String concept1 = WordnetProcessor.findRootWord(assertion.getStateState(),POS.ADJECTIVE);
    	String concept2 = WordnetProcessor.findRootWord(assertion.getEffectVerb(),POS.VERB);
    	int concept1Id = getConceptID(concept1, "state");
    	int concept2Id = getConceptID(concept2, "event");
    	int assertionId = getAssertionID(concept1Id, concept2Id, "CauseOfIsState");
    	
    	findRelationships(concept1, concept1Id, "state");
    	findRelationships(concept2, concept2Id, "event");
    	
    	List<String> causeAdverbs = assertion.getCauseAdverb();
    	List<String> effectAdverbs = assertion.getEffectAdverb();
    	
    	for(String causeAdverb: causeAdverbs){
    		int metadatumId = getMetadatumID(causeAdverb, "adverb", concept1Id, assertionId);
    		findMetadataRelationships(causeAdverb, metadatumId, "adverb", concept1Id, assertionId);
    	}
    	for(String effectAdverb: effectAdverbs){
    		int metadatumId = getMetadatumID(effectAdverb, "adverb", concept2Id, assertionId);
    		findMetadataRelationships(effectAdverb, metadatumId, "adverb", concept2Id, assertionId);
    	}
    }
    public static void insertEventForGoalEvent(EventForGoalEvent assertion){
    	String concept1 = WordnetProcessor.findRootWord(assertion.getEventVerb(),POS.VERB);
    	String concept2 = WordnetProcessor.findRootWord(assertion.getGoalVerb(),POS.VERB);
    	int concept1Id = getConceptID(concept1, "event");
    	int concept2Id = getConceptID(concept2, "event");
    	int assertionId = getAssertionID(concept1Id, concept2Id, "EventForGoalEvent");
    	
    	findRelationships(concept1, concept1Id, "event");
    	findRelationships(concept2, concept2Id, "event");
    	
    	List<String> eventAdverbs = assertion.getEventAdverb();
    	List<String> goalAdverbs = assertion.getGoalAdverb();
    	
    	for(String eventAdverb: eventAdverbs){
    		int metadatumId = getMetadatumID(eventAdverb, "adverb", concept1Id, assertionId);
    		findMetadataRelationships(eventAdverb, metadatumId, "adverb", concept1Id, assertionId);
    	}
    	for(String goalAdverb: goalAdverbs){
    		int metadatumId = getMetadatumID(goalAdverb, "adverb", concept2Id, assertionId);
    		findMetadataRelationships(goalAdverb, metadatumId, "adverb", concept2Id, assertionId);
    	}
    }
    public static void insertEventForGoalState(EventForGoalState assertion){
    	String concept1 = WordnetProcessor.findRootWord(assertion.getEventVerb(),POS.VERB);
    	String concept2 = WordnetProcessor.findRootWord(assertion.getGoalState(),POS.ADJECTIVE);
    	int concept1Id = getConceptID(concept1, "event");
    	int concept2Id = getConceptID(concept2, "state");
    	int assertionId = getAssertionID(concept1Id, concept2Id, "EventForGoalState");
    	
    	findRelationships(concept1, concept1Id, "event");
    	findRelationships(concept2, concept2Id, "state");
    	
    	List<String> eventAdverbs = assertion.getEventAdverb();
    	List<String> goalAdverbs = assertion.getGoalStateAdverb();
    	
    	for(String eventAdverb: eventAdverbs){
    		int metadatumId = getMetadatumID(eventAdverb, "adverb", concept1Id, assertionId);
    		findMetadataRelationships(eventAdverb, metadatumId, "adverb", concept1Id, assertionId);
    	}
    	for(String goalAdverb: goalAdverbs){
    		int metadatumId = getMetadatumID(goalAdverb, "adverb", concept2Id, assertionId);
    		findMetadataRelationships(goalAdverb, metadatumId, "adverb", concept2Id, assertionId);
    	}
    }
    /*
     * findMetadataRelationships()
     * finds generalizations or synonyms for metadata by comparing all metadata in the db with the metadatum at hand
     */ 
    private static void findMetadataRelationships(String metadatum, int metadatumId, String metadata_type, int conceptId, int assertionId){
    	POS pos = POS.NOUN;
    	if(metadata_type.equals("adverb")){
    		pos = POS.ADVERB;
    	}
    	String query = "SELECT metadatumId, metadatum FROM metadata WHERE metadata_type ='"+metadata_type+"' "
    			+ "AND conceptId = "+conceptId+" AND assertionId = "+assertionId;
    	List<Map<String, String>> metadata = select(query);
    	printTable(metadata);
    	for(Map<String, String> row: metadata){
    		int id = Integer.parseInt(row.get("metadatumId"));
    		String currDatum = row.get("metadatum");
    		if(!currDatum.equals(metadatum)){
    			if(!ifMetadataSynonymsExist(metadatumId,id)&&WordnetProcessor.areSynonyms(metadatum, currDatum, pos)){
	    			insertMetadataSynonyms(id, metadatumId);
	    	    	System.out.println("Inserted "+metadatum+" and "+currDatum+" as synonyms");
	    		} else if(pos==POS.NOUN){
		    		Set<String> generalizations = WordnetProcessor.getGeneralizations(metadatum, currDatum, pos);
		    		for(String generalization: generalizations){
		    			if(insertMetadataGeneralization(id, metadatumId, generalization))
		    				System.out.println("Generalized "+metadatum+" and "+currDatum+" as "+generalization);
		    		}
	    		}
    		}
    	}
    }
    private static boolean insertMetadataGeneralization(int metadatum1, int metadatum2, String generalization){
    	boolean success =false;
    	String query = "SELECT metadatumId FROM metadata_generalizations WHERE metadatumId="+metadatum1+" AND generalization='"+generalization+"'";
    	List<Map<String, String>> check = select(query);
    	if(check.isEmpty()){
        	query = "INSERT INTO metadata_generalizations VALUES("+metadatum1+",'"+generalization+"')";
    		update(query);
    		success=true;
    	}
    	query = "SELECT metadatumId FROM metadata_generalizations WHERE metadatumId="+metadatum2+" AND generalization='"+generalization+"'";
    	check = select(query);
    	if(check.isEmpty()){
        	query = "INSERT INTO metadata_generalizations VALUES("+metadatum2+",'"+generalization+"')";
    		update(query);
    		success=true;
    	}
    	return success;
    }
    private static void insertMetadataSynonyms(int metadatum1, int metadatum2){
    	String query = "INSERT INTO metadata_synonyms VALUES("+metadatum1+","+metadatum2+")";
    	update(query);
    	query = "INSERT INTO metadata_synonyms VALUES("+metadatum2+","+metadatum1+")";
    	update(query);
    	
    }
    private static boolean ifMetadataSynonymsExist(int metadatum1, int metadatum2){
    	String query = "SELECT metadatum1 FROM metadata_synonyms WHERE metadatum1="+metadatum1+" AND metadatum2 = "+metadatum2;
    	List<Map<String, String>> data = select(query);
    	if(data.isEmpty())
    		return false;
    	return true;
    	
    }
    /*
     * findRelationships()
     * finds generalizations or synonyms by comparing all concepts in the db with the concept at hand
     */ 
    private static void findRelationships(String concept, int conceptId, String concept_type){
    	POS pos = POS.VERB;
    	if(concept_type.equals("state")){
    		pos = POS.ADJECTIVE;
    	}
    	String query = "SELECT concept, conceptId FROM concepts WHERE concept_type = '"+concept_type+"'";
    	List<Map<String, String>> concepts = select(query);
    	for(Map<String, String> row: concepts){
    		int id =Integer.parseInt(row.get("conceptId"));
    		String currConcept = row.get("concept");
    		if(!currConcept.equals(concept)){
	    		if(!ifConceptSynonymsExist(conceptId,id)&&WordnetProcessor.areSynonyms(currConcept, concept, pos)){
	    			insertConceptSynonyms(id, conceptId);
	    	    	System.out.println("Inserted "+concept+" and "+currConcept+" as synonyms");
	    		} else if(pos==POS.VERB){
		    		Set<String> generalizations = WordnetProcessor.getGeneralizations(concept, currConcept, pos);
		    		for(String generalization: generalizations){
		    			if(insertConceptGeneralization(id, conceptId, generalization))
		    				System.out.println("Generalized "+concept+" and "+currConcept+" as "+generalization);
		    		}
	    		}
    		}
    	}
    }
    private static boolean insertConceptGeneralization(int concept1Id, int concept2Id, String generalization){
    	boolean success =false;
    	String checkQuery = "SELECT conceptId FROM concept_generalizations WHERE conceptId="+concept1Id+" AND generalization='"+generalization+"'";
    	String query = "INSERT INTO concept_generalizations VALUES("+concept1Id+",'"+generalization+"')";
    	List<Map<String, String>> check = select(checkQuery);
    	if(check.isEmpty()){
    		update(query);
    		success=true;
    	}
    	checkQuery = "SELECT conceptId FROM concept_generalizations WHERE conceptId="+concept2Id+" AND generalization='"+generalization+"'";
    	query = "INSERT INTO concept_generalizations VALUES("+concept2Id+",'"+generalization+"')";
    	check = select(checkQuery);
    	if(check.isEmpty()){
    		update(query);
    		success=true;
    	}
    	return success;
    }
    private static void insertConceptSynonyms(int concept1, int concept2){
    	String query = "INSERT INTO concept_synonyms VALUES('"+concept1+"','"+concept2+"')";
    	update(query);
    	query = "INSERT INTO concept_synonyms VALUES('"+concept2+"','"+concept1+"')";
    	update(query);
    }
    public static boolean ifConceptSynonymsExist(int concept1, int concept2){
    	String query = "SELECT concept1Id FROM concept_synonyms WHERE concept1Id="+concept1+" AND concept2Id = "+concept2;
    	List<Map<String, String>> data = select(query);
    	if(data.isEmpty())
    		return false;
    	return true;
    }
    private static int getMetadatumID(String metadatum, String metadata_type, int conceptId, int assertionId){
    	String query = "SELECT metadatumId FROM metadata WHERE "
    			+ "metadatum = '"+metadatum+"' AND metadata_type ='"+metadata_type+"'"
    					+ " AND conceptId = "+conceptId+" AND assertionId ="+assertionId;
    	List<Map<String, String>> data = select(query);
    	if(data.isEmpty()){
    		insertMetadatum(metadatum, metadata_type, conceptId, assertionId);
    		data = select(query);
    	} else{
    		String frequencyQuery = "UPDATE metadata"
    				+ " SET frequency = frequency+1"
    				+ " WHERE metadatumId ='"+data.get(0).get("metadatumId")+"'";
    		update(frequencyQuery);
    	}
    	return Integer.parseInt(data.get(0).get("metadatumId"));
    }
    private static void insertMetadatum(String metadatum, String metadata_type, int conceptId, int assertionId){
    	String maxQuery = "SELECT (MAX(metadatumId)+1) as id FROM metadata";
    	int id = 0;
    	List<Map<String, String>> idData = select(maxQuery);
    	if(idData.get(0).get("id")!=null){
    		id = Integer.parseInt(idData.get(0).get("id"));
    	}
    	String query ="INSERT INTO metadata (metadatum,metadata_type,conceptId,assertionId,metadatumId) "
    			+ "VALUES ('"+metadatum+"','"+metadata_type+"',"+conceptId+","+assertionId+","+id+")";
    	update(query);
    	System.out.println("New "+metadata_type+":"+metadatum+" added for concept # "+conceptId +" in assertion # "+assertionId);
    }
    private static int getAssertionID(int concept1Id, int concept2Id, String relation){
    	String query = "SELECT assertionId FROM assertions WHERE "
    			+ "concept1Id = "+concept1Id+" AND concept2Id = "+concept2Id+" AND relation = '"+relation+"'";
    	List<Map<String, String>> data = select(query);
    	if(data.isEmpty()){
    		insertAssertion(concept1Id, concept2Id, relation);
    		data = select(query);
    	} else{
    		String frequencyQuery = "UPDATE assertions"
    				+ " SET frequency = frequency+1"
    				+ " WHERE assertionId ='"+data.get(0).get("assertionId")+"'";
    		update(frequencyQuery);
    	}
    	return Integer.parseInt(data.get(0).get("assertionId"));
    }
    private static void insertAssertion(int concept1Id, int concept2Id, String relation){
    	String maxQuery = "SELECT (MAX(assertionId)+1) as id FROM assertions";
    	int id = 0;
    	List<Map<String, String>> idData = select(maxQuery);
    	if(idData.get(0).get("id")!=null){
    		id = Integer.parseInt(idData.get(0).get("id"));
    	}
    	String query ="INSERT INTO assertions(concept1Id,concept2Id,relation,assertionId) "
    			+ "VALUES ("+concept1Id+","+concept2Id+",'"+relation+"',"+id+")";
    	update(query);
    	System.out.println("New "+relation+" added");
    }
    private static int getConceptID(String concept, String concept_type){
    	String query =  "SELECT conceptId FROM concepts WHERE concept = '"+concept+
    			"' AND concept_type = '"+concept_type+"'";
    	List<Map<String, String>> data = select(query);
    	if(data.isEmpty()){
    		insertConcept(concept,concept_type);
    		data = select(query);
    	}
    	return Integer.parseInt(data.get(0).get("conceptId"));
    }
    private static void insertConcept(String concept, String concept_type){
    	String maxQuery = "SELECT (MAX(conceptId)+1) as id FROM concepts";
    	int id=0;
    	List<Map<String, String>> idData = select(maxQuery);
    	if(idData.get(0).get("id")!=null){
    		id = Integer.parseInt(idData.get(0).get("id"));
    	}
    	String query = "INSERT INTO concepts VALUES('"+concept+"','"+concept_type+"',"+id+")";
    	update(query);
    	System.out.println("Concept "+concept+" added");
    }
    public static void update(String query) {
        Statement stmt = null;
        try {

            stmt = c.createStatement();
            if(stmt.executeUpdate(query)==0){
                System.out.println("Records created successfully");
            }

            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
