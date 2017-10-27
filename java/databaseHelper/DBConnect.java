package databaseHelper;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by radoslaw on 20.09.17.
 */
public class DBConnect {
    public Connection conn=null;
    public DBConnect() {
        try {
            //object Connection I receive from static method getConnection
            try {
                conn = getConnection();
            } catch (IOException e) {
                e.printStackTrace();

            }
        } catch (SQLException e) {
            for (Throwable t : e)
                System.out.println(t.getMessage());
        }
    }
    private Connection getConnection() throws SQLException, IOException
    {
        //I assume information about database from file database.properties
        Properties props = new Properties();
        try
        {
            InputStream in = Files.newInputStream(Paths.get("database.properties"));
            props.load(in);
        }catch(NoSuchFileException ex)
        {
            //ex.printStackTrace();
            System.out.print("No such file!!!");
            System.exit(0);
        }
        try
        {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String drivers = props.getProperty(	"jdbc.drivers");
        if (drivers != null) System.setProperty("jdbc.drivers", drivers);
        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");

        return DriverManager.getConnection(url, username, password);
    }
    public void close(){
        try{
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
