package com.hand.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import com.hand.entity.Customer;
import com.hand.entity.Language;

public class MyHQL {

	
	public  boolean update(String hql){
		boolean result = true;
		Session session = null;
		Query query = null;
		try{
			session = HibernateUtil.getSession();
			query = session.createQuery(hql);
	        query.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			result = false;
		}finally {
			session.close();
		}
        return result;
	}
	
	public  boolean updateSQL(String sql){
		boolean result = true;
		Session session = null;
		Query query = null;
		try{
			session = HibernateUtil.getSession();
			query = session.createSQLQuery(sql);
	        query.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			result = false;
		}finally {
			session.close();
		}
        return result;
	}
	
	
	public  List select(String hql){
		List result = null;
		try{
			Session session = HibernateUtil.getSession();
			Query query = session.createQuery(hql);
			result= query.list();
	        session.close();
		}catch(Exception exception){
			exception.printStackTrace();
		}
		
        return result;
	}
	
	
	public  Customer getCustomer(Short customerId){
		Customer customer = null;
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from Customer where customerId = "+customerId);
        List result = query.list();
        if(result.size() == 1){
        	customer = (Customer)query.list().get(0);
        }
        session.close();
        return customer;
	}
	
	public  Object getCustomerTest(Class ob,Short customerId){
		Object result = null;
		Transaction tx = null;
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			result = session.get(ob, customerId);
			tx.commit();
		} catch(Exception e){
			e.printStackTrace();
			tx.rollback();
			result = null;
		} finally {
			session.close();
		}
		
		return result;
	}
	
	public  boolean add(Object ob){
		boolean result = true;
		Transaction tx = null;
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			session.save(ob);
			tx.commit();
		} catch(Exception e){
			e.printStackTrace();
			tx.rollback();
			result = false;
		} finally {
			session.close();
		}
		
		return result;
	}
	
	public  boolean delete(Object ob){
		boolean result = true;
		Transaction tx = null;
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			session.delete(ob);
			tx.commit();
		} catch(Exception e){
			e.printStackTrace();
			tx.rollback();
			result = false;
		} finally {
			session.close();
		}
		
		return result;
	}
	
	
}
