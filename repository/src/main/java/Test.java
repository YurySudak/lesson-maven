import itacademy.HibernateRepository;
import itacademy.User;

public class Test {

    public static void main(String[] args) {
        for (User user : HibernateRepository.getStudentsByTeacher(HibernateRepository.getUserByLogin("kal"), "student")) {
            System.out.println(user.getLogin());
        }


//        int id = (int) session.save(new Group("Go"));
//        System.out.println("!!!!!!!!!!" + id);
//        System.out.println(entityManager.find(Group.class,2).toString());
//        session.delete(session.find(Group.class, 36));

    }
}
