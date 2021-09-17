import DAO.impl.*;
import model.*;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;


public class DaoTest {

    @Test
    public void AirportTest() {
        // add
        AirportDAOimpl obj = new AirportDAOimpl();
        Airport air = new Airport("домодедово");
        obj.addAirport(air);
        ArrayList<Airport> airs = obj.getAllAirports();
        int size = airs.size();
        Airport last_air = obj.getAirportById(air.getAirportId());
        Assert.assertEquals(last_air.getAirportName(), air.getAirportName());
        air.setAirportName("Северный");

        // update
        long last_id = last_air.getAirportId();
        obj.updateAirport(last_air.getAirportId(), air);
        last_air = obj.getAirportById(last_id);
        Assert.assertEquals(last_air.getAirportName(), air.getAirportName());

        // delete
        obj.deleteAirport(last_id);

        int new_size = obj.getAllAirports().size();
        Assert.assertEquals(new_size + 1, size);
    }
    
    @Test
    public void AirlineTest() {
        // add
        AirlineDAOimpl obj = new AirlineDAOimpl();
        Airline air = new Airline("svvvv8");
        obj.addAirline(air);
        ArrayList<Airline> airs = obj.getAllAirlines();
        int size = airs.size();
        Airline last_air = obj.getAirlineById(air.getAirlineId());
        Assert.assertEquals(last_air.getAirlineName(), air.getAirlineName());
        air.setAirlineName("new_ss");

        // update
        long last_id = last_air.getAirlineId();
        obj.updateAirline(last_air.getAirlineId(), air);
        last_air = obj.getAirlineById(last_id);
        Assert.assertEquals(last_air.getAirlineName(), air.getAirlineName());

        // delete
        obj.deleteAirline(last_id);

        int new_size = obj.getAllAirlines().size();
        Assert.assertEquals(new_size + 1, size);
    }
    
    @Test
    public void CustomerTest() {
        // add
        CustomerDAOimpl obj = new CustomerDAOimpl();
        Customer cus = new Customer("svvvv8", "swswjw", "flkrlrf", "klldlkd");
        obj.addCustomer(cus);
        ArrayList<Customer> custs = obj.getAllCustomers();
        int size = custs.size();
        Customer last_cus = obj.getCustomerById(cus.getCustomerId());
        Assert.assertEquals(last_cus.getCustomerFullName(), cus.getCustomerFullName());
        cus.setCustomerFullName("new_ss");

        // update
        long last_id = last_cus.getCustomerId();
        obj.updateCustomer(last_cus.getCustomerId(), cus);
        last_cus = obj.getCustomerById(last_id);
        Assert.assertEquals(last_cus.getCustomerFullName(), cus.getCustomerFullName());

        // delete
        obj.deleteCustomer(last_id);

        int new_size = obj.getAllCustomers().size();
        Assert.assertEquals(new_size + 1, size);
    }

    @Test
    public void FlightTest() {
        // // add
        // FlightDAOimpl obj = new FlightDAOimpl();
        // Flight fly = new Flight(1000, 3, 400);
        // obj.addFlight(fly);
        // ArrayList<Flight> flys = obj.getAllFlights();
        // int size = flys.size();
        // Flight last_fly = obj.getFlightById(fly.getFlightNumber());
        // Assert.assertEquals(last_fly.getFlightNumber(), fly.getFlightNumber());
        // fly.setcost(777);

        // // update
        // long last_id = last_fly.getFlightNumber();
        // obj.updateFlight(last_fly.getFlightNumber(), fly);
        // last_fly = obj.getFlightById(last_id);
        // Assert.assertEquals(last_fly.getFlightNumber(), fly.getFlightNumber());

        // // delete
        // obj.deleteFlight(last_id);

        // int new_size = obj.getAllFlights().size();
        // Assert.assertEquals(new_size + 1, size);
    }

}