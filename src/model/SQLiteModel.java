/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringEscapeUtils;
import org.sqlite.SQLiteConfig;

import concepts.Event;
import concepts.Time;
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
public abstract class SQLiteModel {
	private static String storyName = "";
    private static Connection c = null;
    public static final String DB_URL = "jdbc:sqlite:eventure3.db";  
    public static final String DRIVER = "org.sqlite.JDBC";  
    private static final String  logPath ="eventure_log.txt";
    private static FileWriter writer = null;
    private static PrintWriter print_line = null;
    public static void writeLineToLog(String line){
		try {
			writer = new FileWriter(logPath,true);
		    print_line = new PrintWriter(writer);
	    	print_line.println(line);
	    	writer.close();
	    	print_line.close();
	    }
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
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
    public static void closeConnection(){
    	try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Closed database successfully");
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
        }
    }
    
    private static List<Map<String, String>> select(String query) {
    	//query = StringEscapeUtils.escapeJavaScript(query);
    	//System.out.println(query);
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
            System.out.println("Unsuccessful select query: "+query);
            writeLineToLog("Unsuccessful select query: "+query);
        }
        return data;
    }
    public static void insertEffectOf(EffectOf assertion){
    	Event cause = assertion.getCause();
    	Event effect = assertion.getEffect();
    	String concept1 = WordnetModel.findRootWord(cause.getVerb(),POS.VERB);
    	String concept2 = WordnetModel.findRootWord(effect.getVerb(),POS.VERB);
    	writeLineToLog("Inserting EffectOf("+concept1+", "+concept2+")");
    	int concept1Id = getConceptID(concept1, "event");
    	int concept2Id = getConceptID(concept2, "event");
    	int assertionId = getAssertionID(concept1Id, concept2Id, "EffectOf");
    	
    	insertRawAssertion(assertionId, assertion.getCause().toString(), assertion.getEffect().toString(), getStoryName());
    	
    	findRelationships(concept1, concept1Id, "event");
    	findRelationships(concept2, concept2Id, "event");

    	insertMetadata(cause.getAdverbs(), "adverb", concept1Id, assertionId);
    	insertMetadata(effect.getAdverbs(), "adverb", concept2Id, assertionId);
    	
    	insertMetadata(cause.getObjects(), "object", concept1Id, assertionId);
    	insertMetadata(effect.getObjects(), "object", concept2Id, assertionId);
    	
    }
    public static void insertEffectOfIsState(EffectOfIsState assertion){
    	Event cause = assertion.getEvent();
    	State effect = assertion.getState();
    	String concept1 = WordnetModel.findRootWord(cause.getVerb(),POS.VERB);
    	String concept2 = effect.toString();
    	writeLineToLog("Inserting EffectOfIsState("+concept1+", "+concept2+")");
    	int concept1Id = getConceptID(concept1, "event");
    	int concept2Id = getConceptID(concept2, "state");
    	int assertionId = getAssertionID(concept1Id, concept2Id, "EffectOfIsState");
    	
    	insertRawAssertion(assertionId, assertion.getEvent().toString(), assertion.getState().toString(), getStoryName());
    	
    	findRelationships(concept1, concept1Id, "event");
    	findRelationships(concept2, concept2Id, "state");

    	insertMetadata(cause.getAdverbs(),"adverb", concept1Id, assertionId);
    	insertMetadata(effect.getAdverbs(),"adverb", concept2Id, assertionId);
    	
    	insertMetadata(cause.getObjects(), "object", concept1Id, assertionId);
    }
    public static void insertCauseOfIsState(CauseOfIsState assertion){
    	State cause = assertion.getState();
    	Event effect = assertion.getEvent();
    	String concept1 = cause.toString();
    	String concept2 = WordnetModel.findRootWord(effect.getVerb(),POS.VERB);
    	int concept1Id = getConceptID(concept1, "state");
    	int concept2Id = getConceptID(concept2, "event");
    	writeLineToLog("Inserting CauseOfIsState("+concept1+", "+concept2+")");
    	int assertionId = getAssertionID(concept1Id, concept2Id, "CauseOfIsState");

    	insertRawAssertion(assertionId, assertion.getState().toString(), assertion.getEvent().toString(), getStoryName());
    	
    	findRelationships(concept1, concept1Id, "state");
    	findRelationships(concept2, concept2Id, "event");
    	
    	insertMetadata(cause.getAdverbs(),"adverb", concept1Id, assertionId);
    	insertMetadata(effect.getAdverbs(),"adverb", concept2Id, assertionId);
    	
    	insertMetadata(effect.getObjects(), "object", concept2Id, assertionId);
    }
    public static void insertEventForGoalEvent(EventForGoalEvent assertion){
    	Event task = assertion.getTask(), goal = assertion.getGoal();
    	
    	String concept1 = WordnetModel.findRootWord(task.getVerb(),POS.VERB);
    	String concept2 = WordnetModel.findRootWord(goal.getVerb(),POS.VERB);
    	int concept1Id = getConceptID(concept1, "event");
    	int concept2Id = getConceptID(concept2, "event");
    	writeLineToLog("Inserting EventForGoalEvent("+concept1+", "+concept2+")");
    	int assertionId = getAssertionID(concept1Id, concept2Id, "EventForGoalEvent");

    	insertRawAssertion(assertionId, assertion.getTask().toString(), assertion.getGoal().toString(), getStoryName());
    	
    	findRelationships(concept1, concept1Id, "event");
    	findRelationships(concept2, concept2Id, "event");
    	
    	insertMetadata(task.getAdverbs(), "adverb", concept1Id, assertionId);
    	insertMetadata(goal.getAdverbs(), "adverb", concept2Id, assertionId);
    	
    	insertMetadata(task.getObjects(), "object", concept1Id, assertionId);
    	insertMetadata(goal.getObjects(), "object", concept2Id, assertionId);
    }
    public static void insertEventForGoalState(EventForGoalState assertion){
    	Event task = assertion.getEvent();
    	State goal = assertion.getState();
    	String concept1 = WordnetModel.findRootWord(task.getVerb(),POS.VERB);
    	String concept2 = goal.toString();
    	int concept1Id = getConceptID(concept1, "event");
    	int concept2Id = getConceptID(concept2, "state");
    	writeLineToLog("Inserting EventForGoalState("+concept1+", "+concept2+")");
    	int assertionId = getAssertionID(concept1Id, concept2Id, "EventForGoalState");

    	insertRawAssertion(assertionId, assertion.getEvent().toString(), assertion.getState().toString(), getStoryName());
    	
    	findRelationships(concept1, concept1Id, "event");
    	findRelationships(concept2, concept2Id, "state");

    	insertMetadata(task.getAdverbs(), "adverb", concept1Id, assertionId);
    	insertMetadata(goal.getAdverbs(), "adverb", concept2Id, assertionId);
    	
    	insertMetadata(task.getObjects(), "object", concept1Id, assertionId);
    }
    public static void insertHappens(Event event, Time happens){
    	String concept1 = WordnetModel.findRootWord(event.getVerb(),POS.VERB);
    	String concept2 = happens.getTimeHappened();
    	
    	int concept1Id = getConceptID(concept1, "event");
    	int concept2Id = getConceptID(concept2, "time");
    	writeLineToLog("Inserting Happens("+concept1+", "+concept2+")");
    	int assertionId = getAssertionID(concept1Id, concept2Id, "Happens");

    	insertRawAssertion(assertionId, event.toString(), concept2, getStoryName());

    	findRelationships(concept1, concept1Id, "event");

    	insertMetadata(event.getAdverbs(), "adverb", concept1Id, assertionId);

    	insertMetadata(event.getObjects(), "object", concept1Id, assertionId);
    	
    }
    public static void insertMetadata(List<String> metadata, String type, int conceptId, int assertionId){
    	writeLineToLog("Inserting "+type+"s for concept # "+conceptId+" in assertion # "+assertionId+"...");
    	if(metadata!=null&&!metadata.isEmpty()){
	    	for(String metadatum: metadata){
	    		int metadatumId = getMetadatumID(metadatum, type, conceptId, assertionId);
	    		findMetadataRelationships(metadatum, metadatumId, type, conceptId, assertionId);
	    	}
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
    	for(Map<String, String> row: metadata){
    		int id = Integer.parseInt(row.get("metadatumId"));
    		String currDatum = row.get("metadatum");
    		if(!currDatum.equals(metadatum)){
    			if(!ifMetadataSynonymsExist(metadatumId,id)&&WordnetModel.areSynonyms(metadatum, currDatum, pos)){
	    			insertMetadataSynonyms(id, metadatumId);
	    	    	writeLineToLog("Inserted "+metadatum+" and "+currDatum+" as synonyms");
	    		} else if(pos==POS.NOUN){
		    		Set<String> generalizations = WordnetModel.getGeneralizations(metadatum, currDatum, pos);
		    		for(String generalization: generalizations){
		    			if(insertMetadataGeneralization(id, metadatumId, generalization))
		    				writeLineToLog("Generalized "+metadatum+" and "+currDatum+" as "+generalization);
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
	    		if(!ifConceptSynonymsExist(conceptId,id)&&WordnetModel.areSynonyms(currConcept, concept, pos)){
	    			insertConceptSynonyms(id, conceptId);
	    	    	writeLineToLog("Inserted "+concept+" and "+currConcept+" as synonyms");
	    		} else if(pos==POS.VERB){
		    		Set<String> generalizations = WordnetModel.getGeneralizations(concept, currConcept, pos);
		    		for(String generalization: generalizations){
		    			if(insertConceptGeneralization(id, conceptId, generalization))
		    				writeLineToLog("Generalized "+concept+" and "+currConcept+" as "+generalization);
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
    	int id;
    	if(data.isEmpty()){
    		insertMetadatum(metadatum, metadata_type, conceptId, assertionId);
    		data = select(query);
    		id = Integer.parseInt(data.get(0).get("metadatumId"));
    	} else{
    		id = Integer.parseInt(data.get(0).get("metadatumId"));
    		String frequencyQuery = "UPDATE metadata"
    				+ " SET frequency = frequency+1"
    				+ " WHERE metadatumId ='"+data.get(0).get("metadatumId")+"'";
    		update(frequencyQuery);
    		writeLineToLog("Increased frequency for metadatum # "+id);
    	}
    	return id;
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
    	writeLineToLog("New "+metadata_type+":"+metadatum+" added for concept # "+conceptId +" in assertion # "+assertionId);
    }
    private static int getAssertionID(int concept1Id, int concept2Id, String relation){
    	String query = "SELECT assertionId FROM assertions WHERE "
    			+ "concept1Id = "+concept1Id+" AND concept2Id = "+concept2Id+" AND relation = '"+relation+"'";
    	List<Map<String, String>> data = select(query);
    	int id;
    	if(data.isEmpty()){
    		insertAssertion(concept1Id, concept2Id, relation);
    		data = select(query);
    		id = Integer.parseInt(data.get(0).get("assertionId"));
    	} else{
    		id = Integer.parseInt(data.get(0).get("assertionId"));
    		String frequencyQuery = "UPDATE assertions"
    				+ " SET frequency = frequency+1"
    				+ " WHERE assertionId ='"+data.get(0).get("assertionId")+"'";
    		update(frequencyQuery);
    		writeLineToLog("Increased frequency for assertion # "+id);
    	}
    	return id;
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
    	writeLineToLog("New "+relation+" added");
    }
    public static String[][] getFirstMetadata(int assertionId){
    	String query ="SELECT metadatum, metadata_type, frequency,"
    			+ "(SELECT concept FROM concepts as c WHERE m.conceptId=c.conceptId) AS concept"
    			+ " FROM metadata as m "
    			+ "WHERE assertionId = "+assertionId
    			+ " AND conceptId = ("
    			+ "SELECT concept1Id FROM assertions as a "
    			+ "WHERE a.assertionId = m.assertionId);";
    	List<Map<String, String>>data = select(query);
    	String[][] tableData =null;
    	try {
        	tableData = new String[data.size()][4];
            for(int i=0; i<data.size(); i++){
                tableData[i][0]= data.get(i).get("concept");
                tableData[i][1]= data.get(i).get("metadatum");
                tableData[i][2]= data.get(i).get("metadata_type");
                tableData[i][3]= data.get(i).get("frequency");
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    	return tableData;
    }
    public static String[][] getSecondMetadata(int assertionId){
    	String query ="SELECT metadatum, metadata_type, frequency,"
    			+ "(SELECT concept FROM concepts as c WHERE m.conceptId=c.conceptId) AS concept"
    			+ " FROM metadata as m "
    			+ "WHERE assertionId = "+assertionId
    			+ " AND conceptId = ("
    			+ "SELECT concept2Id FROM assertions as a "
    			+ "WHERE a.assertionId = m.assertionId);";
    	List<Map<String, String>>data = select(query);
    	String[][] tableData =null;
    	try {
        	tableData = new String[data.size()][4];
            for(int i=0; i<data.size(); i++){
                   tableData[i][0]= data.get(i).get("concept");
                   tableData[i][1]= data.get(i).get("metadatum");
                   tableData[i][2]= data.get(i).get("metadata_type");
                   tableData[i][3]= data.get(i).get("frequency");
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    	return tableData;
    } public static Object[][] getMGeneralizations(int metadatumID){
    	String query = "SELECT generalization, ("
    			+ "SELECT count(metadatumId) "
    			+ "FROM metadata_generalizations as g2 WHERE g2.generalization=g1.generalization) as frequency "
    			+ "FROM metadata_generalizations as g1 WHERE metadatumId="+metadatumID;
    	List<Map<String,String>> data = select(query);
    	Object[][] tableData=null;
    	try{
    		tableData = new Object[data.size()][2];
            for(int i=0; i<data.size(); i++){
                   tableData[i][0]= data.get(i).get("generalization");
                   tableData[i][1]= Integer.parseInt(data.get(i).get("frequency"));
            }
    	} catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    	return tableData;
    }
    public static Object[][] getMSynonyms(int metadatumID){
    	String query = "SELECT (SELECT metadatum FROM metadata WHERE metadatum2=metadatumId) as synonym, metadatum2 "
    			+ "FROM metadata_synonyms WHERE metadatum1="+metadatumID;
    	List<Map<String,String>> data = select(query);
    	Object[][] tableData=null;
    	try{
    		tableData = new Object[data.size()][2];
            for(int i=0; i<data.size(); i++){
                   tableData[i][0]= data.get(i).get("synonym");
                   tableData[i][1]= Integer.parseInt(data.get(i).get("metadatum2"));
            }
    	} catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    	return tableData;
    }
    public static Object[][] getGeneralizations(int conceptID){
    	String query = "SELECT generalization, ("
    			+ "SELECT count(conceptId) "
    			+ "FROM concept_generalizations as g2 WHERE g2.generalization=g1.generalization) as frequency "
    			+ "FROM concept_generalizations as g1 WHERE conceptId="+conceptID;
    	List<Map<String,String>> data = select(query);
    	Object[][] tableData=null;
    	try{
    		tableData = new Object[data.size()][2];
            for(int i=0; i<data.size(); i++){
                   tableData[i][0]= data.get(i).get("generalization");
                   tableData[i][1]= Integer.parseInt(data.get(i).get("frequency"));
            }
    	} catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    	return tableData;
    }
    public static Object[][] getSynonyms(int conceptID){
    	String query = "SELECT (SELECT concept FROM concepts WHERE concept2Id=conceptId) as synonym, concept2Id "
    			+ "FROM concept_synonyms WHERE concept1Id="+conceptID;
    	List<Map<String,String>> data = select(query);
    	Object[][] tableData=null;
    	try{
    		tableData = new Object[data.size()][2];
            for(int i=0; i<data.size(); i++){
                   tableData[i][0]= data.get(i).get("synonym");
                   tableData[i][1]= Integer.parseInt(data.get(i).get("concept2Id"));
            }
    	} catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    	return tableData;
    }
    public static Object[][] getConcepts(){
    	String query = "SELECT * FROM concepts";
    	List<Map<String,String>> data = select(query);
    	Object[][] tableData=null;
    	try{
    		tableData = new Object[data.size()][3];
            for(int i=0; i<data.size(); i++){
                   tableData[i][0]= Integer.parseInt(data.get(i).get("conceptId"));
                   tableData[i][1]= data.get(i).get("concept");
                   tableData[i][2]= data.get(i).get("concept_type");
            }
    	} catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    	return tableData;
    }
    public static Object[][] getMetadata(){
    	String query = "SELECT metadatumId, metadatum, metadata_type, frequency, ("
    			+ "SELECT concept "
    			+ "FROM concepts as c "
    			+ "WHERE c.conceptId = m.conceptId) as concept,"
    			+ "assertionId "
    			+ "FROM  metadata as m";
    	List<Map<String,String>> data = select(query);
    	Object[][] tableData=null;
    	try{
    		tableData = new Object[data.size()][6];
            for(int i=0; i<data.size(); i++){
                   tableData[i][0]= Integer.parseInt(data.get(i).get("metadatumId"));
                   tableData[i][1]= data.get(i).get("metadatum");
                   tableData[i][2]= data.get(i).get("metadata_type");
                   tableData[i][3]= Integer.parseInt(data.get(i).get("frequency"));
                   tableData[i][4]= data.get(i).get("concept");
                   tableData[i][5]= Integer.parseInt(data.get(i).get("assertionId"));
            }
    	} catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    	return tableData;
    }
    public static Object[][] getAssertions(){
    	String query = "SELECT assertionId, relation, "
    			+ "(SELECT concept FROM concepts WHERE conceptId=concept1Id) as concept1, "
    			+ "(SELECT concept FROM concepts WHERE conceptId=concept2Id) as concept2, "
    			+ "frequency FROM assertions";
    	List<Map<String, String>>data = select(query);
        Object[][] tableData=null;
        try {
        	tableData = new Object[data.size()][5];
            for(int i=0; i<data.size(); i++){
                   tableData[i][0]= new Integer(Integer.parseInt(data.get(i).get("assertionId")));
                   tableData[i][1]= data.get(i).get("relation");
                   tableData[i][2]= data.get(i).get("concept1");
                   tableData[i][3]= data.get(i).get("concept2");
                   tableData[i][4]= new Integer(Integer.parseInt(data.get(i).get("frequency")));
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return tableData;
    }
    public static int getConceptID(String concept, String concept_type){
    	String query =  "SELECT conceptId FROM concepts WHERE concept = '"+concept+
    			"' AND concept_type = '"+concept_type+"'";
    	List<Map<String, String>> data = select(query);
    	if(data.isEmpty()){
    		insertConcept(concept,concept_type);
    		data = select(query);
    	}
    	return Integer.parseInt(data.get(0).get("conceptId"));
    }
    private static void insertRawAssertion(int assertionId,String rawConcept1, String rawConcept2, String storyName){
    	String maxQuery = "SELECT (MAX(rawId)+1) as id FROM raw_assertions";
    	int id = 0;
    	List<Map<String, String>> idData = select(maxQuery);
    	if(idData.get(0).get("id")!=null){
    		id = Integer.parseInt(idData.get(0).get("id"));
    	};
    	String query = "INSERT INTO raw_assertions (rawId, assertionId,concept1Raw,concept2Raw, source_story) "
    			+ "VALUES("+id+","+assertionId+",\""+rawConcept1+"\",\""
    			+rawConcept2+"\", '"+storyName+"')";
    	update(query);
    	writeLineToLog("Raw Assertion Inserted");
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
    	writeLineToLog("Concept "+concept+" added");
    }
    public static void update(String query) {
    	//query = StringEscapeUtils.escapeJavaScript(query);
    	//System.out.println(query);
        Statement stmt = null;
        try {

            stmt = c.createStatement();
            if(stmt.executeUpdate(query)==0){
                writeLineToLog("Records created successfully");
            }

            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.out.println("Unsuccessful update query: "+query);
            writeLineToLog("Unsuccessful update query: "+query);
        }
    }
	private static String getStoryName() {
		return storyName;
	}
	public static void setStoryName(String storyName1) {
		storyName = storyName1;
	}
}
