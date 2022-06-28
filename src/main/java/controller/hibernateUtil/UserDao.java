package controller.hibernateUtil;

import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public class UserDao implements DaoImpl<User> {
    @Override
    public User findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, id);
    }

    @Override
    public void save(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
    }


    @Override
    public void update(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
    }

    @Override
    public void delete(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
    }

    @Override
    public List<User> findAll() {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From User").list();
    }

    public User findByLogin(String login){
        return HibernateSessionFactoryUtil.getSessionFactory()
                .openSession()
                .byNaturalId(User.class)
                .using("login", login)
                .load();
    }

    public List<String> findLogins(){
        return HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("SELECT login FROM User").list();
    }

    public List<String> findEmails(){
        return HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("SELECT email FROM User").list();
    }
}
