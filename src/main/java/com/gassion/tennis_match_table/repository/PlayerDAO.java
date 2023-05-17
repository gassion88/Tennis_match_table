package com.gassion.tennis_match_table.repository;

import com.gassion.tennis_match_table.Util.HibernateUtil;
import com.gassion.tennis_match_table.entities.Player;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

@Transactional
public class PlayerDAO extends DAO<Player>{
    public Player getByName(String name) {
        Player player = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.getTransaction().begin();

            Query query = session.createQuery("from Player where name = :s ", Player.class);
            query.setParameter("s", name);
            List player1 = query.getResultList();

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return player;


    }

    @Override
    public Player getByID(long id) throws HibernateException {
        Player player = null;
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.getTransaction().begin();

            player = session.get(Player.class, id);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        
        return player;
    }

    @Override
    public List getAll() throws HibernateException {
        List<Player> player = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.getTransaction().begin();

            Query query = session.createQuery("from Player", Player.class);
            List<Player>player1 = query.getResultList();

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return player;
    }

    @Override
    public void set(Player player) throws HibernateException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.getTransaction().begin();

            session.merge(player);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(Player player) throws HibernateException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.getTransaction().begin();

            session.persist(player);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) throws HibernateException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.getTransaction().begin();

            Player player = new Player();
            player.setId(id);
            session.remove(player);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
