package JDBC;

import java.sql.*;
import java.util.Date;

public class ConnectionToAV6 {
    private Connection connection = null;

    public ConnectionToAV6() {
        String connectionURL = "jdbc:mysql://localhost:3306/AMSG?useUnicode=true&characterEncoding=UTF-8&characterSetResults=UTF-8";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            this.connection = DriverManager.getConnection(connectionURL, "root", "qwertyui");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Date readDateBeginAV6() {
        return dateSelectFromDB("SELECT MIN(date) FROM table_av6");
    }

    public Date readDateEndAV6() {
        return dateSelectFromDB("SELECT MAX(date) FROM table_av6");
    }

    public Date readDateBeginAV6Ext() {
        return dateSelectFromDB("SELECT MIN(date) FROM table_av6_extended");
    }

    public Date readDateEndAV6Ext() {
        return dateSelectFromDB("SELECT MAX(date) FROM table_av6_extended");
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Date dateSelectFromDB(String SqlSelectQuery) {
        Date date = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SqlSelectQuery);
            while (resultSet.next()) {
                date = resultSet.getDate(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return date;
    }
}
