package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final String USERTABLE = "user";

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {

        try (Session session = new Util().getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            String sql = String.format("CREATE TABLE %s(id bigint PRIMARY KEY AUTO_INCREMENT, name VARCHAR(45), lastName VARCHAR(45), age INT)", USERTABLE);
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            //---ignore
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = new Util().getSessionFactory().openSession()) {
            session.beginTransaction();
            String sql = String.format("DROP TABLE %s", USERTABLE);
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error in dropUserTable: " + e.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = new Util().getSessionFactory().openSession()) {
            User user = new User(name, lastName, age);
            session.save(user);
        } catch (Exception e) {
            System.out.println("saveUsers Error: " + e.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = new Util().getSessionFactory().openSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.remove(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("removeUserbyID Error: " + e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Session session = new Util().getSessionFactory().openSession()) {
            list = session.createQuery("SELECT i FROM User i", User.class).getResultList();
//            CriteriaBuilder cb = session.getCriteriaBuilder();
//            CriteriaQuery<User> cq = cb.createQuery(User.class);
//            Root<User> root = cq.from(User.class);
//            Query query = session.createQuery(cq);
//            list = query.getResultList();
        } catch (Exception e) {
            System.out.println("getAllUsers Error: " + e.getMessage());
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = new Util().getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createQuery("DELETE  FROM User ").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("cleanUserTable: " + e.getMessage());
        }
    }
}
