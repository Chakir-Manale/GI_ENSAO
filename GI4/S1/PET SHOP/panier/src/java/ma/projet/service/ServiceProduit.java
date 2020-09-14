package ma.projet.service;

import java.util.List;
import ma.projet.dao.IDao;
import ma.projet.entity.Produit;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Lachgar
 */
public class ServiceProduit implements IDao<Produit>{
    Transaction tx = null;
    Session session = null;
    Produit produit = null;
    
    @Override
    public boolean create(Produit o) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(o);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } 
        return false;
    }

    @Override
    public boolean update(Produit o) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(o);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } 
        return false;
    }

    @Override
    public boolean delete(Produit o) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(o);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } 
        return false;
    }

    @Override
    public List<Produit> getAll() {
         List<Produit> liste = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            liste = session.createQuery("from Produit").list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } 
        return liste;
    }

    @Override
    public Produit getById(int id) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            produit = (Produit) session.get(Produit.class, id);
            tx.commit();
            session.close();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } 
        return produit;
    }
    
    
}
