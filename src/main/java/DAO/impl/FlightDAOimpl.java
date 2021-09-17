package DAO.impl;

import DAO.FlightDAO;
import factory.MyFactory;
import model.Flight;
import model.Airline;
import model.Airport;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class FlightDAOimpl implements FlightDAO {

    @Override
    public void addFlight(Flight flight) {
        Session session = null;
        session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        session.save(flight);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    @Override
    public void updateFlight(long flightNumber, Flight flight) {
        Session session = null;
        session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        Flight old_flight = getFlightById(flightNumber);
        session.evict(old_flight);
        old_flight.setAirlineId(flight.getAirlineId());
        old_flight.setDeparture(flight.getDeparture());
        old_flight.setArrival(flight.getArrival());
        old_flight.setDepartureTime(flight.getDepartureTime());
        old_flight.setArrivalTime(flight.getArrivalTime());
        old_flight.setcost(flight.getcost());
        old_flight.setnumber(flight.getnumber());
        old_flight.setmiles(flight.getmiles());
        session.update(old_flight);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    @Override
    public Flight getFlightById(long flightNumber) {
        Session session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        Flight flight = session.get(Flight.class, flightNumber);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return flight;
    }

    @Override
    public ArrayList<Flight> getAllFlights() {
        Session session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("select a from Flight a");
        ArrayList<Flight> flights = (ArrayList<Flight>) query.list();
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return flights;
    }

    @Override
    public void deleteFlight(long flightNumber) {
        Session session = null;
        session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        Flight flight = getFlightById(flightNumber);
        session.delete(flight);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }
}
