package itacademy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class NamesRepository {
    private final static Logger log = LoggerFactory.getLogger(UsersRepository.class);
    private static final Map<Integer, String> groupNames = new HashMap<>();
    private static final Map<String, Integer> groupIds = new HashMap<>();

    public static void init() {
        try {
            ResultSet resultSet = Db.getGroups();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String group_name = resultSet.getString("group_name");
                groupNames.put(id, group_name);
                groupIds.put(group_name, id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    public static String getGroupNameById(int id) {
        return groupNames.get(id);
    }

    public static Integer getGroupIdByName(String name) {
        Integer id =  groupIds.get(name);
        if (id != null) {
            return id;
        } else {
            id = Db.setGroup(name);
            groupIds.put(name, id);
            groupNames.put(id, name);
        }
        return id;
    }
}
