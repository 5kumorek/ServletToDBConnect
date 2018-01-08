package databaseHelper;

import java.sql.*;
import java.util.*;

import static java.sql.Types.*;


/**
 * Created by radoslaw on 20.09.17.
 */
/**
 * \class InsertRecord provide information This object allows on inserting records to database.
 */
//this class is responsible for insert new record to table
public class InsertRecord {

    //prywatne składowe klasy
    private DBConnect database;
    private Statement stat;
    private ResultSet result;
    private String tableName;
    private String exception;
    ArrayList<String> row;
    ArrayList<String> values;

    /**
     \brief Constructor initialize DBConnect object and initialize global variables
     \param[in] tableName - name of table from will be processed
     */
    public InsertRecord(String tableName){
        this.tableName  = tableName;
        database = new DBConnect();
        try{
            stat = database.conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        row=new ArrayList<String>();
        values=new ArrayList<String>();
    }
    /**
     \brief this function create query from obtained Strings and execute it. Additional if exception was throw, then set exception
     \param[in] dataFromForm - array of String, which should be processed and inserted to database
     \throws SQLException
     */
    public boolean insertRecord(String dataFromForm[]) throws SQLException {

        //firstly I check correctness of data
        boolean isCorrect = checkValues(dataFromForm);
        if(!isCorrect)
            return false;

        StringBuilder sql = new StringBuilder("INSERT INTO "+tableName+" (");

        //I adding to my sql query applicable names of rows
        for(int i=0;i<row.size()-1;i++)
            sql.append(row.get(i)).append(", ");
        try{
            sql.append(row.get(row.size()-1));
        }catch(ArrayIndexOutOfBoundsException a)
        {
            a.printStackTrace();
        }
        sql.append(") VALUES ('");

        //I adding to my sql query applicable names of rows
        for(int i=0;i<values.size()-1;i++)
            sql.append(values.get(i)).append("', '");
        try{
            sql.append(values.get(values.size()-1));
        }catch(ArrayIndexOutOfBoundsException a)
        {
            a.printStackTrace();
        }
        sql.append("');");

        //and now I execute insert
        stat.executeUpdate(sql.toString());

        return true;
    }
    /**
     \brief this function check types of arguments and provide safety insertion to database.
     \param[in] dataFromForm - variables to check
     */
    private boolean checkValues(String[] dataFromForm){
        try {
            String query = "SELECT * FROM " + tableName + ";";
            result = stat.executeQuery(query);
            ResultSetMetaData rsmd = result.getMetaData();
            int countOfColumns = rsmd.getColumnCount();
            for(int i = 1; i<= countOfColumns; i++) {
                if(rsmd.isNullable(i)== ResultSetMetaData.columnNoNulls && dataFromForm[i-1].isEmpty() && !rsmd.isAutoIncrement(i)) {
                    exception = "ERROR: field "+rsmd.getColumnName(i)+" cant be null";
                    return false;
                }
                if(!dataFromForm[i-1].isEmpty())
                {
                    row.add(rsmd.getColumnName(i));
                    values.add(dataFromForm[i-1]);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    /**
     \brief this function create object Connection, establish connection to postgresql database, and return this object
     \param[in] recordToDelete - index of record to deleting
     \throws SQLException
     */
    public String getException(){
        return this.exception;
    }
}