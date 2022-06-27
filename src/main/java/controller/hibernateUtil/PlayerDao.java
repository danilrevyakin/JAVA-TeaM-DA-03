package controller.hibernateUtil;

import model.Player;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PlayerDao implements DaoImpl<Player> {

    @Override
    public Player findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Player.class, id);
    }

    @Override
    public void save(Player player) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(player);
        tx1.commit();
    }

    @Override
    public void update(Player player) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.update(player);
            tx1.commit();

    }

    @Override
    public void delete(Player player) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(player);
        tx1.commit();
    }

    @Override
    public List<Player> findAll() {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Player").list();
    }
}
