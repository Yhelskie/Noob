

import java.sql.*;
import javax.swing.*;

public class JavaMySqlConnection {

    Connection conn = null;

    public static Connection ConnectDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/aebsfms", "root", "");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            e.getSuppressed();
            JOptionPane.showMessageDialog(null, "NO SERVER FOUND","Warning",JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
