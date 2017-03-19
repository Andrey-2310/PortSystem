package sample;

import com.sun.org.apache.xpath.internal.SourceTree;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;

/**
 * Created by Андрей on 28.02.2017.
 */


/**
 * This class collects all methods that are required during the work of app
 */
public class SuperExtd {
    private static Connection connection;
    private static final String UserName = "root";
    private static final String Password = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/portdispatchsystem?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false&serverTimezone=UTC";

    /**
     * This method gets connection of the DB
     * @return Connection
     */
    protected Connection GetConnection() {
        try {
            Driver myDriver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(myDriver);
            connection = DriverManager.getConnection(URL, UserName, Password);
            //  statement=connection.createStatement();
        } catch (SQLException e) {
            System.err.println("Что-то пошло не так");
        }
        return connection;
    }


    /**
     * This method helps to open file from directory
     * @param fileName - name of file, which will be opened
     * @return true if file opens, false if not
     * @throws IOException
     */
    public static boolean OpenFile(String fileName) throws IOException {
        //text file, should be opening in default text editor
        if(fileName==null) return false;
        File file = new File(fileName);

        //first check if Desktop is supported by Platform or not
        if (!Desktop.isDesktopSupported()) {
            System.out.println("Desktop is not supported");
            return false;
        }
        Desktop desktop = Desktop.getDesktop();
        if (file.exists()) {
            desktop.open(file);
            return true;
        } else {
            System.out.println("File doesn't exist, let's save it");
            return false;
        }
    }
}
