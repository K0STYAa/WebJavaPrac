package DAO.impl;

import DAO.AirportDAO;
import factory.MyFactory;
import model.Airport;
import model.Flight;
import org.hibernate.Session;
import org.hibernate.query.Query;
import model.CustAirRelation;

import java.util.ArrayList;

public class AirportDAOimpl implements AirportDAO {

    @Override
    public void addAirport(Airport airport) {
        Session session = null;
        session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        session.save(airport);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    @Override
    public void updateAirport(long airportId, Airport airport) {
        Session session = null;
        session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        Airport old_airport = getAirportById(airportId);
        session.evict(old_airport);
        old_airport.setAirportName(airport.getAirportName());
        session.update(old_airport);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    @Override
    public Airport getAirportById(long airportId) {
        Session session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        Airport airport = session.get(Airport.class, airportId);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return airport;
    }

    @Override
    public ArrayList<Airport> getAllAirports() {
        Session session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("select a from Airport a");
        ArrayList<Airport> airports = (ArrayList<Airport>) query.list();
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return airports;
    }

    @Override
    public void deleteAirport(long airportId) {
        Session session = null;
        session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        Airport airport = getAirportById(airportId);
        session.delete(airport);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    @Override
    public ArrayList<Flight> getAllAirportDepFlights(Long airportId) {
        Session session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        Airport airport = getAirportById(airportId);
        Query query = session.createQuery("FROM Flight F WHERE F.departure = :departure");
        query.setParameter("departure", airport);
        ArrayList<Flight> flights = (ArrayList<Flight>) query.list();
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return flights;
    }

    @Override
    public ArrayList<Flight> getAllAirportArrFlights(Long airportId) {
        Session session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        Airport airport = getAirportById(airportId);
        Query query = session.createQuery("FROM Flight F WHERE F.arrival = :arrival");
        query.setParameter("arrival", airport);
        ArrayList<Flight> flights = (ArrayList<Flight>) query.list();
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return flights;
    }
}
