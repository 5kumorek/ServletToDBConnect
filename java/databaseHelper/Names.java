package databaseHelper;

import java.sql.*;
import java.util.ArrayList;

public class Names {
    private DBConnect database;
    private ResultSet result;
    ArrayList<Integer> records = new ArrayList<Integer>();
    ArrayList<Integer> columns = new ArrayList<Integer>();

    public Names() {
        database = new DBConnect();
    }

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
    public ArrayList<Integer> recordsCount(){
        return records;
    }
    public ArrayList<Integer> columnsCount(){
        return columns;
    }
}
