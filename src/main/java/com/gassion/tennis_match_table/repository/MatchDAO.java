package com.gassion.tennis_match_table.repository;

import com.gassion.tennis_match_table.Util.HibernateUtil;
import com.gassion.tennis_match_table.entities.Match;
import com.gassion.tennis_match_table.entities.Player;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

public class MatchDAO extends DAO<Match>{
    @Override
    public Match getByID(long id) throws HibernateException {
        Match match = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.getTransaction().begin();

            match = session.get(Match.class, id);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return match;
    }

    @Override
    public List<Match> getAll() throws HibernateException {
        List<Match> matches = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.getTransaction().begin();

            matches = session.createQuery("from Match").list();

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return matches;
    }

    @Override
    public void set(Match match) throws HibernateException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.getTransaction().begin();

            session.merge(match);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(Match match) throws HibernateException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.getTransaction().begin();

            session.persist(match);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) throws HibernateException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.getTransaction().begin();

            Match match = new Match();
            match.setId(id);
            session.remove(match);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
