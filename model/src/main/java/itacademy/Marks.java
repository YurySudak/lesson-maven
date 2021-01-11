package itacademy;

import java.util.List;

public class Marks {
    private int id;
    private int groupId;
    private int studentId;
    private List<Integer> marksOfTheme;

    public Marks(int id, int groupId, int student, List<Integer> markOfTheme) {
        this.id = id;
        this.groupId = groupId;
        this.studentId = student;
        this.marksOfTheme = markOfTheme;
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
