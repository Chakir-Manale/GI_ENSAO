package ma.projet.test;

import ma.projet.util.HibernateUtil;

/**
 *
 * @author Lachgar
 */
public class Test {
    public static void main(String[] args) {
        HibernateUtil.getSessionFactory().openSession();
        
    }
    
}
