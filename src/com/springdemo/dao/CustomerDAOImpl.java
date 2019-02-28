package com.springdemo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.context.spi.CurrentSessionContext;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory; // 在xml里面已经实现了唯一的一个sessionFactory对象，所以这里可以直接Autowired。

	@Override
	public List<Customer> getCustomers() {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query and sort by last name
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName", Customer.class);

		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();

		// return the results
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		// get the current session
		Session currentSession = sessionFactory.getCurrentSession();

		// save the customer
		currentSession.saveOrUpdate(theCustomer);

	}

	@Override
	public Customer getCustomer(int theId) {
		// get the current session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query
		Customer theCustomer = currentSession.get(Customer.class, theId);

		return theCustomer;
	}
	
	@Override
	public void deleteCustomer(int theId) {
		// get the current session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// find the Customer
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		// delete the customer
		currentSession.delete(theCustomer);
	}

}