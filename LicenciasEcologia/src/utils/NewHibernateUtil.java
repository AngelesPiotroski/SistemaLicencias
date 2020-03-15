package utils;



import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class NewHibernateUtil {

    private static SessionFactory sessionFactory;
	
	static {
		sessionFactory = new Configuration().configure("/hibernate1.cfg.xml").buildSessionFactory();
		
	}
	
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public static void setSessionFactory(SessionFactory sessionFactory) {
		NewHibernateUtil.sessionFactory = sessionFactory;
	}
}
