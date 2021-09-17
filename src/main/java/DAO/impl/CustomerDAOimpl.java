package DAO.impl;

import DAO.CustomerDAO;
import factory.MyFactory;
import model.Customer;
import model.PaidTicket;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class CustomerDAOimpl implements CustomerDAO {

    @Override
    public void addCustomer(Customer customer) {
        Session session = null;
        session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        session.save(customer);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    @Override
    public void updateCustomer(long customerId, Customer customer) {
        Session session = null;
        session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        Customer old_customer = getCustomerById(customerId);
        session.evict(old_customer);
        old_customer.setCustomerFullName(customer.getCustomerFullName());
        session.update(old_customer);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    @Override
    public Customer getCustomerById(long customerId) {
        Session session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        Customer customer = session.get(Customer.class, customerId);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return customer;
    }

    @Override
    public ArrayList<Customer> getAllCustomers() {
        Session session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("select a from Customer a");
        ArrayList<Customer> customers = (ArrayList<Customer>) query.list();
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return customers;
    }

    @Override
    public void deleteCustomer(long customerId) {
        Session session = null;
        session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        Customer customer = getCustomerById(customerId);
        session.delete(customer);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    @Override
    public ArrayList<PaidTicket> getAllCustFlights(Long customerId) {
        Session session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        Customer customer = getCustomerById(customerId);
        Query query = session.createQuery("FROM PaidTicket CAR WHERE CAR.customerId = :customer");
        query.setParameter("customer", customer);
        ArrayList<PaidTicket> PaidTicket = (ArrayList<PaidTicket>) query.list();
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return PaidTicket;
    }
}
