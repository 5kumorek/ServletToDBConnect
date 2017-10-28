package databaseHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DeleteRecord {

    private String tableName;
    private DBConnect database;
    private Statement stat;
    private ResultSet result;
    public DeleteRecord(String tableName){
        this.tableName  = tableName;
        database = new DBConnect();
        try{
            stat = database.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            String query = "SELECT * FROM " + tableName + ";";
            result = stat.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteRecord(int recordToDelete) throws SQLException{
        int i=0;
        while(result.next()) {
            if(recordToDelete==i++){
                result.deleteRow();
            }
        }
    }
}
