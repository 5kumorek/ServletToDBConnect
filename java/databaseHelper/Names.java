package databaseHelper;

import java.sql.*;
import java.util.ArrayList;
/**
 * \class Names provide information This provide information about all tables in database
 */
public class Names {
    private DBConnect database;
    private ResultSet result;
    ArrayList<Integer> records = new ArrayList<Integer>();
    ArrayList<Integer> columns = new ArrayList<Integer>();

    /**
     \brief Constructor initialize DBConnect object
     \param[in] tableName - name of table from will be processed
     */
    public Names() {
        database = new DBConnect();
    }

    /**
     \brief this function gets names of tables in database, set arraylist with columns count and set arraylist with rows count
     \return arraylist contained tables names
     \throws SQLException
     */
    public ArrayList<String> getNames() throws SQLException{
        ArrayList<String> listToReturn = new ArrayList<String>();
        Statement stat = database.conn.createStatement();
        //I try get of names of tables
        DatabaseMetaData md = database.conn.getMetaData();
        String query = "SELECT * from ";
        String query2 = "SELECT count(*) from ";
        ResultSet rs = md.getTables(null, null, null, new String[]{"TABLE"});
        while (rs.next()) {
            try {
                result = stat.executeQuery(query + rs.getString("TABLE_NAME") + ";");
                ResultSetMetaData rsmd = result.getMetaData();
                columns.add(rsmd.getColumnCount());

                result = stat.executeQuery(query2 + rs.getString("TABLE_NAME") + ";");
                if (result.next())
                    records.add(result.getInt(1));
            }catch(SQLException ex){
                continue;
            }
            listToReturn.add(rs.getString("TABLE_NAME"));

        }
        return listToReturn;
    }
    /**
     \brief this function return arraylist with records count
     \return arraylist contained tables records count
     */
    public ArrayList<Integer> recordsCount(){
        return records;
    }
    /**
     \brief this function return arraylist with records count
     \return arraylist contained tables records count
     */
    public ArrayList<Integer> columnsCount(){
        return columns;
    }
}
