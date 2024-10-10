package rectangles;

import java.sql.*;

public class RectangleDBWriter implements RectangleWriter {

    private static Connection getConnection(String dbUrl) {
        Connection connection = null;
        //"jdbc:postgresql://10.10.104.136:5432/Geometry";
        String username = "postgres";
        String password = "123";

        try {
            connection = DriverManager.getConnection(dbUrl, username, password);
        } catch (SQLException e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return connection;
    }

    private static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    private static void createTableIfNotExists(Connection connection) {
        String sql = "CREATE TABLE IF NOT EXISTS rectangle (\n" +
                "  id SERIAL PRIMARY KEY, \n" +
                "  width FLOAT8, \n" +
                "  length FLOAT8 \n" +
                ")";
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(sql);
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    private static void addRectangleToDBTable(Rectangle rectangle, Connection connection) {
        String sql = "insert into rectangle (width, length) values (?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, rectangle.width);
            preparedStatement.setDouble(2, rectangle.length);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    @Override
    public void writeRectangle(Rectangle rectangle, String dbUrl) {
        Connection connection = getConnection(dbUrl);
        createTableIfNotExists(connection);
        addRectangleToDBTable(rectangle, connection);
        closeConnection(connection);
    }
}
