package databaseHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 * \class DeleteRecord provide information This object allows tunneling by ssh and connecting to database.
 */
public class DeleteRecord {

    private String tableName;
    private DBConnect database;
    private Statement stat;
    private ResultSet result;
    /**
     \brief Constructor initialize DBConnect object and select all object from table "tablName"
     \param[in] tableName - name of table from will be processed
     */
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
    /**
     \brief this function create object Connection, establish connection to postgresql database, and return this object
     \param[in] recordToDelete - index of record to deleting
     \throws SQLException
     */
    public void deleteRecord(int recordToDelete) throws SQLException{
        int i=0;
        while(result.next()) {
            if(recordToDelete==i++){
                result.deleteRow();
            }
        }
    }
}
