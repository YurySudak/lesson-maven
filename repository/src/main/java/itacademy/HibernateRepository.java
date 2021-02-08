package itacademy;

import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class HibernateRepository {

    private static final Configuration CONFIGURATION = new Configuration().configure();
    private static final SessionFactory SESSION_FACTORY = CONFIGURATION.buildSessionFactory();

    @Getter
    private static List<User> users = new CopyOnWriteArrayList();
    @Getter
    private static List<Mark> marks = new CopyOnWriteArrayList();
    @Getter
    private static List<Group> groups = new CopyOnWriteArrayList();
    @Getter
    private static List<Salary> salaries = new CopyOnWriteArrayList();
    @Getter
    private static List<Type> types = new CopyOnWriteArrayList();

    public static Map<String, User> usersMap = new ConcurrentHashMap();

    static {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();
        users.addAll(session.createQuery("select u from user_ u", User.class).getResultList());
        marks.addAll(session.createQuery("select m from mark m", Mark.class).getResultList());
        groups.addAll(session.createQuery("select g from group_ g", Group.class).getResultList());
        salaries.addAll(session.createQuery("select s from salary s", Salary.class).getResultList());
        types.addAll(session.createQuery("select t from type t", Type.class).getResultList());
        session.getTransaction().commit();
        session.close();

        for(User user : users) {
            usersMap.put(user.getLogin(), user);
        }
    }

    public static int getAmountOfThemes() {
        return marks.stream()
                .map(Mark::getTheme)
                .max(Comparator.naturalOrder())
                .get();
    }

    public static void addUser(User user) throws ExistException {
        if (users.contains(user)) throw new ExistException("Login: " + user.getLogin());
        else {
            Session session = SESSION_FACTORY.openSession();
            session.beginTransaction();
            int id = (int) session.save(user);
            session.getTransaction().commit();
            session.close();
            user.setId(id);
            users.add(user);
        }
    }

    public static void addGroup(String groupName) throws ExistException  {
        Group group = new Group(groupName);
        if (groups.contains(group)) throw new ExistException("Group: " + group.getGroupName());
        else {
            Session session = SESSION_FACTORY.openSession();
            session.beginTransaction();
            int id = (int) session.save(group);
            session.getTransaction().commit();
            session.close();
            group.setId(id);
            groups.add(group);
        }
    }

    public static User getUserByLogin(String login) {
        return usersMap.get(login);
    }

    public static void setMark(int id, int value) {
        marks.get(id).setValue(value);
    }
}
