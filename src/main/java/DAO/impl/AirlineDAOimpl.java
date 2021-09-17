package DAO.impl;

import DAO.AirlineDAO;
import factory.MyFactory;
import model.Airline;
import model.Flight;
import model.CustAirRelation;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class AirlineDAOimpl implements AirlineDAO {

    @Override
    public void addAirline(Airline airline) {
        Session session = null;
        session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        session.save(airline);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    @Override
    public void updateAirline(long airlineId, Airline airline) {
        Session session = null;
        session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        Airline old_airline = getAirlineById(airlineId);
        session.evict(old_airline);
        old_airline.setAirlineName(airline.getAirlineName());
        session.update(old_airline);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    @Override
    public Airline getAirlineById(long airlineId) {
        Session session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        Airline airline = session.get(Airline.class, airlineId);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return airline;
    }

    @Override
    public ArrayList<Airline> getAllAirlines() {
        Session session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("select a from Airline a");
        ArrayList<Airline> airlines = (ArrayList<Airline>) query.list();
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return airlines;
    }

    @Override
    public void deleteAirline(long airlineId) {
        Session session = null;
        session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        Airline airline = getAirlineById(airlineId);
        session.delete(airline);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    @Override
    public ArrayList<Flight> getAllAirlineFlights(Long airlineId) {
        Session session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        Airline airline = getAirlineById(airlineId);
        Query query = session.createQuery("FROM Flight A WHERE A.airlineId = :airline");
        query.setParameter("airline", airline);
        ArrayList<Flight> flights = (ArrayList<Flight>) query.list();
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return flights;
    }

    @Override
    public ArrayList<CustAirRelation> getAllAirlineCust(Long airlineId) {
        Session session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        Airline airline = getAirlineById(airlineId);
        Query query = session.createQuery("FROM CustAirRelation CAR WHERE CAR.airlineId = :airline");
        query.setParameter("airline", airline);
        ArrayList<CustAirRelation> custAirRelation = (ArrayList<CustAirRelation>) query.list();
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return custAirRelation;
    }
}
