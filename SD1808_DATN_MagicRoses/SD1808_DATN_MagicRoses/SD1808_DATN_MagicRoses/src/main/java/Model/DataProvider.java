/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class DataProvider {

    public static Connection GetDataConnection() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String user = "sa";
        String pass = "123";
        String url = "jdbc:sqlserver://localhost:1433;databaseName=SOF102_SD1808_SmileyBall;encrypt=true;trustServerCertificate=true;";
        conn = DriverManager.getConnection(url, user, pass);
        return conn;
    }
}
