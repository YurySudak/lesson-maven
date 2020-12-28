package itacademy;

import java.util.HashMap;
import java.util.Map;

public class Passwords {
    private static Map<String, String> map;

    public static void init() {
        map = new HashMap<>();
        map.put("admin", "admin");
    }

    public static Map<String, String> getMap() {
        return map;
    }

    public static void setMap(Map<String, String> map) {
        Passwords.map = map;
    }
}
