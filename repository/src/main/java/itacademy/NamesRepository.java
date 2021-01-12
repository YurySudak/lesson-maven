package itacademy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class NamesRepository {
    private final static Logger log = LoggerFactory.getLogger(UsersRepository.class);
    private static Map<Integer, String> groupNames = new HashMap<>();
    private static boolean isInit = false;

    public static void init() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/postgres";
            Connection connection = DriverManager.getConnection(url, "postgres", "admin");
            String sql = "select * from \"group\"";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String group_name = resultSet.getString("group_name");
                groupNames.put(id, group_name);
            }
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    public static String getGroupNameById(int id) {
        if (!isInit) {
            init();
            isInit = true;
        }
        return groupNames.get(id);
    }
}
