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
import java.util.Map;
import java.util.Set;

import wordnet.WordnetProcessor;
import edu.mit.jwi.item.POS;

import objects.EffectOf;

/**
 *
 * @author RJ
 */
public abstract class SQLiteProcessor {
    private static Connection c = null;
    public static void setConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:eventure.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    public static void printTable(ArrayList<Map<String, String>> data) {
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
    private static ArrayList<Map<String, String>> select(String query) {
        ResultSet rs = null;
        Statement stmt = null;
        int first =1;
        ArrayList<String> columnNames = new ArrayList<String>();
        ArrayList<Map<String, String>> data = new ArrayList<Map<String, String>>();
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
                for (int i = 1; i <= count; i++) {
                    Map<String, String> curr = new HashMap<String, String>();
                    curr.put(columnNames.get(i-1), rs.getString(i));
                    data.add(curr);
                }
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
    	int concept1Id = getConceptID(WordnetProcessor.findRootWord(assertion.getCauseVerb(),POS.VERB), "event");
    	int concept2Id = getConceptID(WordnetProcessor.findRootWord(assertion.getEffectVerb(),POS.VERB), "event");
    	int assertionId = getAssertionID(concept1Id, concept2Id, "EffectOf");
    }
    private static int getAssertionID(int concept1Id, int concept2Id, String relation){
    	String query = "SELECT assertionId FROM assertions WHERE "
    			+ "concept1Id = "+concept1Id+" AND concept2Id = "+concept2Id+" AND relation = '"+relation+"'";
    	ArrayList<Map<String, String>> data = select(query);
    	if(data.isEmpty()){
    		addNewAssertion(concept1Id, concept2Id, relation);
    		data = select(query);
    	} else{
    		String frequencyQuery = "UPDATE assertions"
    				+ " SET frequency = frequency+1"
    				+ " WHERE assertionId ='"+data.get(0).get("assertionId")+"'";
    		update(frequencyQuery);
    	}
    	return Integer.parseInt(data.get(0).get("assertionId"));
    }
    private static void addNewAssertion(int concept1Id, int concept2Id, String relation){
    	String maxQuery = "SELECT (MAX(assertionId)+1) as id FROM assertions";
    	int id = 0;
    	ArrayList<Map<String, String>> idData = select(maxQuery);
    	if(!idData.isEmpty()&&idData.get(0).get("id")!=null){
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
    	ArrayList<Map<String, String>> data = select(query);
    	if(data.isEmpty()){
    		addNewConcept(concept,concept_type);
    		data = select(query);
    	}
    	return Integer.parseInt(data.get(0).get("conceptId"));
    }
    private static void addNewConcept(String concept, String concept_type){
    	String maxQuery = "SELECT (MAX(conceptId)+1) as id FROM concepts";
    	int id=0;
    	ArrayList<Map<String, String>> idData = select(maxQuery);
    	if(!idData.isEmpty()&&idData.get(0).get("id")!=null){
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
