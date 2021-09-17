package DAO.impl;

import DAO.CustAirRelationDAO;
import factory.MyFactory;
import model.CustAirRelation;
import model.Airline;
import model.Customer;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class CustAirRelationDAOimpl implements CustAirRelationDAO {

    @Override
    public void addCustAirRelation(CustAirRelation custAirRelation) {
        Session session = null;
        session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        session.save(custAirRelation);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    @Override
    public void updateCustAirRelation(long c_a_Id, CustAirRelation custAirRelation) {
        Session session = null;
        session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        CustAirRelation old_custAirRelation = getCustAirRelationById(c_a_Id);
        session.evict(old_custAirRelation);
        old_custAirRelation.setAirlineId(custAirRelation.getAirlineId());
        old_custAirRelation.setCustomerId(custAirRelation.getCustomerId());
        old_custAirRelation.setDiscount(custAirRelation.getDiscount());
        old_custAirRelation.setmiles(custAirRelation.getmiles());
        session.update(old_custAirRelation);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    @Override
    public CustAirRelation getCustAirRelationById(long c_a_Id) {
        Session session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        CustAirRelation custAirRelation = session.get(CustAirRelation.class, c_a_Id);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return custAirRelation;
    }

    @Override
    public ArrayList<CustAirRelation> getAllCustAirRelations() {
        Session session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("select a from CustAirRelation a");
        ArrayList<CustAirRelation> custAirRelations = (ArrayList<CustAirRelation>) query.list();
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return custAirRelations;
    }

    @Override
    public void deleteCustAirRelation(long c_a_Id) {
        Session session = null;
        session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        CustAirRelation custAirRelation = getCustAirRelationById(c_a_Id);
        session.delete(custAirRelation);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }
}
