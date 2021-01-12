package itacademy;

import java.util.List;

public class Group {
    private String name;
    private List<String> themes;

    public Group(String name, List<String> themes) {
        this.name = name;
        this.themes = themes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getThemes() {
        return themes;
    }

    public void setThemes(List<String> themes) {
        this.themes = themes;
    }
}
