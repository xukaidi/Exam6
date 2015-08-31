package com.hand.Main;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hand.dao.MyHQL;
import com.hand.entity.Address;
import com.hand.entity.Customer;
import com.hand.entity.Store;


public class Main {

	public static void main(String[] args) {
		
		Main main = new Main();
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");
		context.start();
		MyHQL myhql;
		myhql = context.getBean("myhql", MyHQL.class);
		Customer customer = new Customer();
		int store_id = 1;
		customer.setStore((Store) myhql.select(
				"from Store where storeId = " + store_id).get(0));
		System.out.println("请输入FirstName(first_name):");
		Scanner scanner;
		scanner = new Scanner(System.in);
		customer.setFirstName(scanner.nextLine());

		System.out.println("请输入LastName(last_name):");
		scanner = new Scanner(System.in);
		customer.setLastName(scanner.nextLine());

		System.out.println("请输入Email(email):");
		scanner = new Scanner(System.in);
		customer.setEmail(scanner.nextLine());

		System.out.println("请输入Address ID:");
		scanner = new Scanner(System.in);
		List list = myhql.select("from Address where addressId = "
				+ Integer.valueOf(scanner.nextShort()));

		while (list.size() == 0) {
			System.out.println("你输入的Address ID不存在，请重新输入:");
			scanner = new Scanner(System.in);
			list = myhql.select("from Address where addressId = "
					+ Integer.valueOf(scanner.nextLine()));
		}
		customer.setAddress((Address) list.get(0));

		java.sql.Date date = new java.sql.Date(new Date().getTime());
		customer.setCreateDate(date);
		customer.setLastUpdate(date);

		if (myhql.add(customer)) {
			System.out.println("已经保存的数据如下:");
			System.out.println("ID:" + customer.getCustomerId());
			System.out.println("First_name:" + customer.getFirstName());
			System.out.println("Last_name:" + customer.getLastName());
			System.out.println("Email:" + customer.getEmail());
			System.out.println("Address:" + customer.getAddress().getAddress());
		}
		System.out.println("请输入要删除的Customer的ID:");
		Scanner scanner2 = new Scanner(System.in);
		Short id = scanner.nextShort();
		Customer c = (Customer) myhql.getCustomerTest(Customer.class, id);
		while (c == null) {
			System.out.println("输入错误，请重新输入要删除的Customer的ID:");
			scanner2 = new Scanner(System.in);
			c = (Customer) myhql.getCustomerTest(Customer.class,
					scanner.nextShort());
		}
		if (myhql.delete(c)) {
			System.out.println("你输入的ID为" + c + "的Customer已经删除.");
		}
		context.stop();
	}
}