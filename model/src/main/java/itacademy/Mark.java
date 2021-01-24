package itacademy;

public class Mark {
    private int id;
    private int groupId;
    private int studentId;
    private int theme;
    private int value;

    public Mark(int id, int groupId, int studentId, int theme, int value) {
        this.id = id;
        this.groupId = groupId;
        this.studentId = studentId;
        this.theme = theme;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getTheme() {
        return theme;
    }

    public void setTheme(int theme) {
        this.theme = theme;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
