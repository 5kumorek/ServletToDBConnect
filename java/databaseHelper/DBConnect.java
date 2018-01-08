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
import com.jcraft.jsch.*;

/**
 * \class DBConnect provide information This object allows tunneling by ssh and connecting to database.
 * Created by radoslaw on 20.09.17.
 */
public class DBConnect {
    public Connection conn=null;
    public static boolean portIsFree=true;
    /**
     \brief this constructor tunneling by ssh if tunnel isn't establish, and set Connection object
     */
    DBConnect() {
        try {
            //object Connection I receive from static method getConnection
            try {
                if(portIsFree)
                    sshTunneling();
                conn = getConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            for (Throwable t : e)
                System.out.println(t.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     \brief this function tunneling by ssh with JSch class
     \throws JSchException, IOException
     */
    private void sshTunneling() throws JSchException, IOException {
        Properties props = new Properties();
        try
        {
            InputStream in = Files.newInputStream(Paths.get("tunneling.properties"));
            props.load(in);
        }catch(NoSuchFileException ex)
        {
            //ex.printStackTrace();
            System.out.print("No such file!!!");
            System.exit(0);
        }
        String remote_host = props.getProperty(	"remote_host");
        String username = props.getProperty("ssh.username");
        String password = props.getProperty("ssh.password");
        int local_port = 33333;//Integer.parseInt(props.getProperty("ssh.local_port"));
        int remote_port = 5432;//Integer.parseInt(props.getProperty("ssh.remote_port"));

        int assigned_port;

        JSch jsch = new JSch();

        // Create SSH session.  Port 22 is your SSH port which
        // is open in your firewall setup.
        Session session = jsch.getSession(username, remote_host, 22);
        session.setPassword(password);

        // Additional SSH options.  See your ssh_config manual for
        // more options.  Set options according to your requirements.
        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
        config.put("Compression", "yes");
        config.put("ConnectionAttempts","2");

        session.setConfig(config);

        // Connect
        session.connect();

        // Create the tunnel through port forwarding.
        // This is basically instructing jsch session to send
        // data received from local_port in the local machine to
        // remote_port of the remote_host
        // assigned_port is the port assigned by jsch for use,
        // it may not always be the same as
        // local_port.

        assigned_port = session.setPortForwardingL(local_port, remote_host, remote_port);
        portIsFree=false;
    }
    /**
     \brief this function create object Connection, establish connection to postgresql database, and return this object
     \return Connection - connection to postgresql database
     \throws JSchException, IOException
     */
    private Connection getConnection() throws SQLException, IOException
    {
        //I assume information about database from file database.properties
        Properties props = new Properties();
        try
        {
            InputStream in = Files.newInputStream(Paths.get("database2.properties"));
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
