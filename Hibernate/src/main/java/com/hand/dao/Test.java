package com.hand.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hand.entity.Address;
import com.hand.entity.Customer;
import com.hand.entity.Dbuser;
import com.hand.filter.MyInterceptor;

public class Test {

	/* Method to CREATE an employee in the database */
	public Integer addUser(String firstname, String lastname, String email,
			int store_id, short address_id, String last_update,
			String create_date, MyInterceptor interceptor) {
		Session session = HibernateUtil.getSessionFactory().withOptions()
				.interceptor(interceptor).openSession();
		Transaction tx = null;
		Integer UserID = null;
		try {
			tx = session.beginTransaction();
			// ApplicationContext factory=new
			// ClassPathXmlApplicationContext("applicationContext.xml");
			Customer user = new Customer(firstname, lastname, email, store_id,
					address_id, last_update, create_date);
			
			UserID = (Integer) session.save(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return UserID;
	}

	/* Method to READ all the employees */
	public short QueryUsers(MyInterceptor interceptor) {
		 SessionFactory factory =new Configuration().configure().buildSessionFactory();
		Session session = factory.withOptions()
				.interceptor(interceptor).openSession();
		Transaction tx = null;
		short address_id = 0 ;
		List results = null;
		try {
			tx = session.beginTransaction();

			System.out.println("请输入AddressID：");
			Scanner scanner3 = new Scanner(System.in);
			address_id = scanner3.nextShort();

			String hql = "FROM Address Where addressId=" + address_id;
			Query query = session.createQuery(hql);
			results = query.list();
			tx.commit();
		} catch (HibernateException e) {

		} finally {
			if (results.size() == 0) {
				System.out.println("你输入的AddressID不存在，请重新输入");
				QueryUsers(interceptor);
				
			}
			session.close();
		}
		return address_id;

	}

	/* Method to READ all the employees */
	public void QueryAddress(String first_name, short addressid) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List results = null;
		try {
			tx = session.beginTransaction();
			System.out.println("first_name+"+first_name);
			String hql = "FROM 	Dbuser WHERE firstname=" + first_name;
			Query query = session.createQuery(hql);
			results = query.list();
			Iterator iterator = results.iterator();
			while (iterator.hasNext()) {
				Customer customer = (Customer) iterator.next();
				System.out.print("ID: " + customer.getCustomerid());
				System.out.print("First Name: " + customer.getFirstname());
				
			}
			
				//Customer customer = (Customer) iterator.next();
//				System.out.print("ID: " + customer.getCustomerid());
//				System.out.print("First Name: " + customer.getFirstname());
//				System.out.print(" Last Name: " + customer.getLastname());
//				System.out.println("  Email: " + customer.getEmail());
//				String hql1 = "FROM Address Where addressId=" + addressid;
//				Query query1 = session.createQuery(hql);
//				List results1 = query.list();
//				for (Iterator iterator1 = results1.iterator(); iterator
//						.hasNext();) {
//					Address address = (Address) iterator1.next();
//					{
//						System.out
//								.println("  Address: " + address.getAddress());
//					}
//				}
				tx.commit();
			//}
		} catch (HibernateException e) {
			e.printStackTrace();

		} finally {
			System.out.println("执行数据");
			session.close();
		}

	}

	/* Method to UPDATE salary for an employee */
	public void updateEmployee(Integer UserID, String firstname) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Dbuser user = (Dbuser) session.get(Dbuser.class, UserID);
			user.setFirstname(firstname);
			session.update(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Method to DELETE an employee from the records */
	public void deleteUser(Integer UserID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			
			Dbuser user = (Dbuser) session.get(Dbuser.class, UserID);
			session.delete(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public static void main(String[] args) {
		System.out.println("test--user---");

		Test test = new Test();
		MyInterceptor interceptor = new MyInterceptor();

//		// -------------
		System.out.println("请输入FirstName(firstname):");
		Scanner scanner = new Scanner(System.in);
		String firstname = scanner.next();
		System.out.println("请输入LastName(lastname):");
		Scanner scanner1 = new Scanner(System.in);
		String lastname = scanner1.next();
		System.out.println("请输入Email(Email):");
		Scanner scanner2 = new Scanner(System.in);
		String email = scanner2.next();

		// 查询
		short address_id = test.QueryUsers(interceptor);
		// 插入

		SimpleDateFormat bartDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH-mm-ss");
		Date date = new Date();
		String newdate = bartDateFormat.format(date);
		System.out.println(bartDateFormat.format(date));
		short as = Short.parseShort("1");
		 test.addUser(firstname,lastname,email,as,address_id,newdate,newdate,interceptor);
		// 查询
		System.out.println("已经保存的数据如下：");
		//test.QueryAddress(firstname, address_id);

		// --------------

		// test.listUsers(interceptor);

		// test.addUser(firstname,lastname,email,1,address_id,newdate,newdate,interceptor);
		
		System.out.println("请输入你要删除的Customer的ID：");
		Scanner scanner4 = new Scanner(System.in);
		int id = scanner.nextInt();
		test.deleteUser(id);
	}
}
