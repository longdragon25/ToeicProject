package vn.myclass.core.common.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory= buidSessionFactory();
    private static SessionFactory buidSessionFactory(){
        try {
            // create sessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        }catch (Throwable e){
            System.out.println("Initial session factory fail");
            System.out.println(e);
            throw new ExceptionInInitializerError();
        }
    }

    public static  SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
