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
    public static ArrayList<Map<String, String>> select(String query) {
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
                        columnNames.add(rsmd.getColumnName(i) + "\t");
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
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return data;
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
