package databaseHelper;

import java.sql.*;
import java.util.*;

import static java.sql.Types.*;


/**
 * Created by radoslaw on 20.09.17.
 */
//this class is responsible for printing content of table
public class Table {
    //prywatne składowe klasy
    private DBConnect database;
    private ResultSet result;
    private ResultSet resultForTable;
    private ResultSetMetaData rsmd;
    private DatabaseMetaData dtmd;
    private String tableName;
    private int countOfColumns;
    private Map<Integer, String> sqlTypes=new HashMap<Integer, String>(){
        {
            put(BOOLEAN, "boolean");
            put(DATE, "date");
            put(DECIMAL, "decimal");
            put(FLOAT, "float");
            put(INTEGER, "integer");
            put(NUMERIC, "numeric");
            put(VARCHAR, "varchar");
            put(CHAR, "char");
        }};
    //constructor, where I initialize variables
    public Table(String tableName){
        this.tableName = tableName;
        database = new DBConnect();
        try {
            //I try get of records
            Statement stat = database.conn.createStatement();
            String query = "SELECT * FROM " + tableName + ";";
            result = stat.executeQuery(query);
            rsmd = result.getMetaData();
            dtmd = database.conn.getMetaData();
            resultForTable = dtmd.getColumns(null, null, tableName, "%");

            //at first, I look how many columns is in table
            countOfColumns = rsmd.getColumnCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //this method is created, to checking name of columns
    public ArrayList<String> getTableColumns() {
        ArrayList<String> namesOfColumns=new ArrayList<String>();
        Statement stat = null;
        try {

            // i generate first arraylist, row of namesOfColumns

            for(int i = 1; i<= countOfColumns; i++) {
                namesOfColumns.add(rsmd.getColumnName(i));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return namesOfColumns;
    }
    //this method is created, to checking types of columns
    public ArrayList<String> getColumnTypes() {
        ArrayList<String> nameOfTypes=new ArrayList<String>();
        try {
            //firstly I generate table with default variables fot each column
            ArrayList<String> defaults=new ArrayList<String>();
            while(resultForTable.next()) {
                defaults.add(resultForTable.getString("COLUMN_DEF"));
            }
            // i generate first arraylist, row of namesOfColumns
            for(int i = 1; i<= countOfColumns; i++) {
                String type;
                    type = checkType(rsmd.getColumnType(i), rsmd.getColumnTypeName(i),rsmd.getColumnDisplaySize(i), rsmd.isAutoIncrement(i), rsmd.isNullable(i), defaults.get(i-1));
                nameOfTypes.add(type);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nameOfTypes;
    }
    //this method is created, to checking all records in table
    public ArrayList<ArrayList<String>> getTableRecords(){
        ArrayList<ArrayList<String>> listToReturn=new ArrayList<ArrayList<String>>();
        try {
            //next I fill my arralists
            while(result.next()){
                ArrayList<String> temp = new ArrayList<String>();
                for(int i=1;i<=countOfColumns;i++) {
                    temp.add(result.getString(i));
                }

                listToReturn.add(temp);
            }
        } catch (SQLException e) {
            //if occurs exception the I send arraylist with exception
            e.printStackTrace();
        }
        return listToReturn;
    }
    //this is private method, which allow me join all of information about column
    private String checkType(Integer type,String typeName, int size, boolean increment, int notnull, String defaultValue){
        StringBuilder newType;
        if(sqlTypes.containsKey(type))
            newType = new StringBuilder(sqlTypes.get(type));
        else
            newType = new StringBuilder(typeName);
        if(type==VARCHAR || type==CHAR)
        {
            newType.append('(').append(size).append(')');
        }
        if(increment)
            newType.append(" autoincrement");
        if(notnull==0)
            newType.append(" not null ");
        if(defaultValue!=null)
            newType.append(" default ").append(defaultValue);
        return newType.toString();
    }

}