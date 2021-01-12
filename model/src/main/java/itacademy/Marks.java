package itacademy;

import java.util.List;

public class Marks {
    private int id;
    private int groupId;
    private int studentId;
    private List<Integer> marksOfTheme;

    public Marks(int id, int groupId, int studentId, List<Integer> markOfTheme) {
        this.id = id;
        this.groupId = groupId;
        this.studentId = studentId;
        this.marksOfTheme = markOfTheme;
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

    public List<Integer> getMarksOfTheme() {
        return marksOfTheme;
    }

    public void setMarksOfTheme(List<Integer> marksOfTheme) {
        this.marksOfTheme = marksOfTheme;
    }
}
