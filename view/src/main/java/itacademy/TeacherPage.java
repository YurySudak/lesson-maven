package itacademy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(value = {"/teacher/*"})
public class TeacherPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        Teacher trainer = null;
        for (Teacher teacher : TeachersRepository.getTeachers()) {
            if (req.getRequestURI().contains(teacher.getLogin())) {
                trainer = teacher;
                break;
            }
        }
        writer.write("<div align=center><h2>Кабинет тренера</h2>");
        writer.write("<p>ФИО: " + trainer.fio + "</p>");
        writer.write("<p>Возраст: " + trainer.age + "</p>");
        Group group = trainer.getGroup();
        writer.write("<p>Группа: " + group.getName() + "</p><table><tr><td><b>Оценки:</b></td>");
        for (String theme: group.getThemes()) {
            writer.write("<td>" + theme + "</td>");
        }
        writer.write("</tr><tr>");

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:postgresql://localhost:5432/postgres";
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(url, "postgres", "admin");
            Statement statement = connection.createStatement();
            String query = "select fio from marks m\n" +
                    "join student s on s.id = m.student_id\n" +
                    "where group_id = 1;";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String str = resultSet.getString("fio");
                writer.write("<p>" + str + "</p>");
            }
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        /*for (Student student : StudentsRepository.getStudents()) {
            for (Group gr : student.getGroups()) {
                if (gr.equals(group)) {
                    writer.write("<td>" + student + "</td>");
                    for (String mark : student.getMarks().get(0)) {
                        writer.write("<td>" + mark + "</td>");
                    }
                    writer.write("</tr>");
                }
            }

        }*/
        writer.write("</div>");
        writer.close();
    }
}
