package itacademy;

import java.sql.*;

public class TestJDBC {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/postgres";
        Connection connection = DriverManager.getConnection(url, "postgres", "admin");
        Statement statement = connection.createStatement();
        String query = "select name from \"group\"";
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            String str = resultSet.getString("name");
            System.out.println(str);
        }
    }
}
