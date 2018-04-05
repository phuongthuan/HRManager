/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HRManager.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAO {

    private static Connection connection;
    
    private static String driver = "com.mysql.jdbc.Driver";
    private static String urlDriver = "jdbc:mysql://";
    private static String hostName = "localhost";
    private static String port = "3306";
    private static String databaseName = "hrmanager";
    private static String username = "root";
    private static String password = "";
    
    
    public static synchronized Connection getConnection() throws ClassNotFoundException, SQLException {
        if (connection == null) {
            Class.forName(driver);
            connection = DriverManager.getConnection(urlDriver + hostName + ":" + port + "/" + databaseName, username, password);
        }
        return connection;
    }

    public static String getDriver() {
        return driver;
    }

    public static void setDriver(String driver) {
        DAO.driver = driver;
    }

    public static String getUrlDriver() {
        return urlDriver;
    }

    public static void setUrlDriver(String urlDriver) {
        DAO.urlDriver = urlDriver;
    }

    public static String getHostName() {
        return hostName;
    }

    public static void setHostName(String hostName) {
        DAO.hostName = hostName;
    }

    public static String getPort() {
        return port;
    }

    public static void setPort(String port) {
        DAO.port = port;
    }

    public static String getDatabaseName() {
        return databaseName;
    }

    public static void setDatabaseName(String databaseName) {
        DAO.databaseName = databaseName;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        DAO.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        DAO.password = password;
    }
    
}