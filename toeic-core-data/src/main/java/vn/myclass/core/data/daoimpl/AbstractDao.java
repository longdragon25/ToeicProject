package vn.myclass.core.data.daoimpl;

import javassist.tools.rmi.ObjectNotFoundException;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import vn.myclass.core.common.constant.CoreConstant;
import vn.myclass.core.common.utils.HibernateUtil;
import vn.myclass.core.data.dao.GenericDao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class AbstractDao<ID extends Serializable,T> implements GenericDao <ID,T> {
    private Class<T> persistenceClass;

    public AbstractDao(){
        this.persistenceClass = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }
    // Convert ClassName to String
    public String getPersistenceClassName() {
        return persistenceClass.getSimpleName();
    }
    public String getSessionClassName(){
        return persistenceClass.getSimpleName();
    }


    @Override
    public List<T> findAll() {
        List<T> list=new ArrayList<T>();
        Session session =HibernateUtil.getSessionFactory().openSession();
        Transaction transaction=null;
        try {
            transaction=session.beginTransaction();
            //HQL type
            StringBuilder sql=new StringBuilder("from ");
            sql.append(this.getSessionClassName());
            Query query=session.createQuery(sql.toString());
            list=query.list();
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            throw e;
        }finally {
            session.close();
        }

        return list;
    }

    @Override
    public T update(T entity) {
        T result=null;
        Session session =HibernateUtil.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        try {
            Object object= session.merge(entity);
            result= (T) object;
            transaction.commit();

        }catch (HibernateException e){
            transaction.rollback();
            System.out.println(e);
        }finally {
            session.close();
        }
        return result;
    }

    @Override
    public void save(T entity) {
        Session session=HibernateUtil.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        try {
            session.persist(entity);
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            System.out.println(e);
            throw e;
        }finally {
            session.close();
        }

    }

    @Override
    public T findById(ID id) {
        T result=null;
        Session session=HibernateUtil.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        try {
            result=(T) session.get(persistenceClass,id);
            if (result==null){
                throw new ObjectNotFoundException("NOT FOUND"+id, null);
            }

        }catch (HibernateException e){
            transaction.rollback();
            System.out.println(e);
            throw e;
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }

    @Override
    public Object[] findByProperty(String property, Object value, String sortExpression, String sortDirection) {
        List<T> list=new ArrayList<>();
        Session session=HibernateUtil.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        Object totalItem=0;
        try {
            StringBuilder sql= new StringBuilder("from ");
            sql.append(getSessionClassName());
            if (property !=null && value!=null){
                sql.append(" where ").append(property).append("= :value");
            }
            if (sortDirection!=null && sortExpression!=null){
                sql.append(" order by").append(sortExpression);
                sql.append(" "+(sortDirection.equals(CoreConstant.SORT_ASC)?"asc":"desc"));
            }
            Query query1=session.createQuery(sql.toString());
            if (value!=null){
                query1.setParameter("value",value);
            }
            list=query1.list();
            StringBuilder sql2= new StringBuilder("select count(*) from ");
            sql2.append(getSessionClassName());
            if (property!=null && value!=null){
                sql2.append(" where").append(property).append("= :value");
            }
            Query query2=session.createQuery(sql2.toString());
            if (value!=null){
                query2.setParameter("value",value);
            }
            totalItem= query2.list().get(0);

            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            throw e;
        }finally {
            session.close();
        }

        return new Object[]{totalItem,list};
    }

    @Override
    public Integer delete(List<ID> ids) {
        Integer count=0;
        Session session=HibernateUtil.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        try {
            for (ID item: ids){
                T t=(T) session.get(persistenceClass,item);
                session.delete(t);
                count++;

            }

            transaction.commit();

        }catch (HibernateException e){
            transaction.rollback();
            throw e;
        }
        return count;
    }
}
