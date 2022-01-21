package com.just.learning.ecommerce.repository;

import com.just.learning.ecommerce.model.Item;
import lombok.extern.log4j.Log4j2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Log4j2
public class SqlJdbcRepository {

    public SqlJdbcRepository() {
        loadDriver();
        createConnection();
    }
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/ecommerce";
    private static final String USER = "root";
    private static final String PASSWORD = "password";
    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String GET_ITEM_QUERY = "SELECT * FROM item WHERE item.item_id = %s";

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    private static void createConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch(SQLException e) {
            log.error("Error in establishing repository", e);
        }
    }

    public static void loadDriver() {
        try {
            Class.forName(DRIVER_CLASS_NAME);
        } catch (ClassNotFoundException e) {
            log.error("Unable to register driver class", e);
        }
    }

    private static void createStatementAndGetResultSet(String query) {

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch(SQLException e) {
            log.error("Error in executing statement", e);
        }
    }

    public Item getItem(int id) {
        createStatementAndGetResultSet(String.format(GET_ITEM_QUERY, id));
        try {
            if(resultSet.next())
                return new Item(resultSet.getString(1), resultSet.getString(2),resultSet.getInt(3),
                    resultSet.getString(4),resultSet.getString(5));
        } catch(SQLException e) {
            log.error("Error in retrieving Item", e);
        }
        return null;
    }

}
