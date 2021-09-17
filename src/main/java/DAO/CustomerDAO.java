package DAO;

import model.Customer;
import model.PaidTicket;

import java.sql.SQLException;
import java.util.Collection;
import java.util.ArrayList;
public interface CustomerDAO {
    void addCustomer(Customer customer);
    void updateCustomer(long customerId, Customer customer);
    Customer getCustomerById(long customerId);
    Collection getAllCustomers();
    void deleteCustomer(long customerId);    
    ArrayList<PaidTicket> getAllCustFlights(Long customerId);
}