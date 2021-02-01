package itacademy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NamesRepository {
    private final static Logger LOG = LoggerFactory.getLogger(NamesRepository.class);
    private static final Map<Integer, String> groupNames = new ConcurrentHashMap<>();
    private static final Map<String, Integer> groupIds = new ConcurrentHashMap<>();

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
            LOG.error(e.getMessage());
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
            id = Db.addGroup(name);
            groupIds.put(name, id);
            groupNames.put(id, name);
        }
        return id;
    }

    public static void setGroup(int groupId, String groupName) {
        groupNames.put(groupId, groupName);
        groupIds.put(groupName, groupId);
    }
}
