package DAO.impl;

import DAO.PaidTicketDAO;
import factory.MyFactory;
import model.PaidTicket;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class PaidTicketDAOimpl implements PaidTicketDAO {

    @Override
    public void addPaidTicket(PaidTicket PaidTicket) {
        Session session = null;
        session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        session.save(PaidTicket);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    @Override
    public void updatePaidTicket(long p_t_Id, PaidTicket PaidTicket) {
        Session session = null;
        session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        PaidTicket old_PaidTicket = getPaidTicketById(p_t_Id);
        session.evict(old_PaidTicket);
        old_PaidTicket.setFlightNumber(PaidTicket.getFlightNumber());
        old_PaidTicket.setCustomerId(PaidTicket.getCustomerId());
        session.update(old_PaidTicket);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    @Override
    public PaidTicket getPaidTicketById(long p_t_Id) {
        Session session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        PaidTicket PaidTicket = session.get(PaidTicket.class, p_t_Id);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return PaidTicket;
    }

    @Override
    public ArrayList<PaidTicket> getAllPaidTickets() {
        Session session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("select a from PaidTicket a");
        ArrayList<PaidTicket> PaidTickets = (ArrayList<PaidTicket>) query.list();
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return PaidTickets;
    }

    @Override
    public void deletePaidTicket(long p_t_Id) {
        Session session = null;
        session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        PaidTicket PaidTicket = getPaidTicketById(p_t_Id);
        session.delete(PaidTicket);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }
}
