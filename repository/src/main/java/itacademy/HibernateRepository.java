package itacademy;

import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

public class HibernateRepository {

    private static final Configuration CONFIGURATION = new Configuration().configure();
    private static final SessionFactory SESSION_FACTORY = CONFIGURATION.buildSessionFactory();

    @Getter
    private static Set<User> users = new CopyOnWriteArraySet<>();
    @Getter
    private static Set<Mark> marks = new CopyOnWriteArraySet<>();
    @Getter
    private static Set<Group> groups = new CopyOnWriteArraySet<>();
    @Getter
    private static Set<Salary> salaries = new CopyOnWriteArraySet<>();
    @Getter
    private static Set<Type> types = new CopyOnWriteArraySet<>();

    public static Map<String, User> usersMap = new ConcurrentHashMap<>();
    public static Map<String, Integer> typesMap = new ConcurrentHashMap<>();
    public static Map<Integer, String> groupsMap = new ConcurrentHashMap<>();
    public static Map<Integer, Mark> marksMap = new ConcurrentHashMap<>();

    static {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();
        groups.addAll(session.createQuery("select g from group_ g", Group.class).getResultList());
        users.addAll(session.createQuery("select u from user_ u", User.class).getResultList());
        marks.addAll(session.createQuery("select m from mark m", Mark.class).getResultList());
        salaries.addAll(session.createQuery("select s from salary s", Salary.class).getResultList());
        types.addAll(session.createQuery("select t from type t", Type.class).getResultList());
        session.getTransaction().commit();
        session.close();

        for(User user : users) {
            usersMap.put(user.getLogin(), user);
        }

        for (Type type : types) {
            typesMap.put(type.getTypeName(), type.getId());
        }

        for (Group group : groups) {
            groupsMap.put(group.getId(), group.getGroupName());
        }

        for (Mark mark : marks) {
            marksMap.put(mark.getId(), mark);
        }
    }

    public static int getAmountOfThemes() {
        return marks.stream()
                .map(Mark::getTheme)
                .max(Comparator.naturalOrder())
                .orElse(0);
    }

    public static int getAmountOfMonths() {
        return salaries.stream()
                .map(Salary::getMonth)
                .max(Comparator.naturalOrder())
                .orElse(0);
    }

    public static boolean checkLogin(String login) {
        return usersMap.containsKey(login);
    }

    public static void addUser(User user) {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();
        int id = (int) session.save(user);
        session.getTransaction().commit();
        session.close();
        user.setId(id);
        users.add(user);
        usersMap.put(user.getLogin(), user);
    }

    public static Group addGroup(String groupName) {
        Group group = new Group(groupName);
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();
        int id = (int) session.save(group);
        session.getTransaction().commit();
        session.close();
        group.setId(id);
        groups.add(group);
        return group;
    }

    public static User getUserByLogin(String login) {
        return usersMap.get(login);
    }

    public static void setMark(int id, int value) {
        marksMap.get(id).setValue(value);
    }

    public static void updateMarks() {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();
        for (Mark mark : marks) {
            session.update(mark);
        }
        session.getTransaction().commit();
        session.close();
    }

    public static List<User> getUsers(String userType) {
        List<User> currentUsers = new ArrayList<>();
        for (User user : users) {
            if (user.getType() == typesMap.get(userType)) {
                currentUsers.add(user);
            }
        }
        return currentUsers;
    }

    public static String getGroupNameByTeacher(User teacher) {
        return teacher.getGroups().iterator().next().getGroupName();
    }

    public static Mark getMark(int studentId, int groupId, int theme) {
        for (Mark mark : marks) {
            if (studentId == mark.getStudentId() && groupId == mark.getGroupId() && theme == mark.getTheme()) {
                return mark;
            }
        }
        return null;
    }

    public static List<User> getStudentsByTeacher(User teacher, String userType) {
        Group group = teacher.getGroups().iterator().next();
        int userTypeId = typesMap.get(userType);
        List<User> resultList = new ArrayList<>();
        for (User user : users) {
            if (userTypeId == user.getType()) {
                for (Group userGroup : user.getGroups()) {
                    if (userGroup.getId() == group.getId()) {
                        resultList.add(user);
                    }
                }
            }
        }
        return resultList;
    }

    public static void deleteUser(String login) {
        User user = getUserByLogin(login);
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();
        if (user.getType() == 2) {
            for (Salary salary : salaries) {
                if (salary.getTeacherId() == user.getId()) {
                    session.delete(salary);
                }
            }
            for (Group group : groups) {
                if (group.getId() == user.getGroups().iterator().next().getId()) {
                    session.delete(group);
                }
            }
        }
        session.delete(user);
        session.getTransaction().commit();
        session.close();
        users.remove(user);
        usersMap.remove(user.getLogin());
    }

    public static void setSalary(User teacher, int month, double value) {
        Salary salary = new Salary(teacher.getId(), month, value);
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();
        int id = (int) session.save(salary);
        session.getTransaction().commit();
        session.close();
        salary.setId(id);
        salaries.add(salary);
    }

    public static Salary getSalary(User teacher, int month) {
        for (Salary salary : salaries) {
            if (salary.getTeacherId() == teacher.getId() && salary.getMonth() == month)
                return salary;
        }
        return null;
    }

}
