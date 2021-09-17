package DAO;

import model.CustAirRelation;

import java.sql.SQLException;
import java.util.Collection;

public interface CustAirRelationDAO {
    void addCustAirRelation(CustAirRelation custAirRelation);
    void updateCustAirRelation(long c_a_Id, CustAirRelation custAirRelation);
    CustAirRelation getCustAirRelationById(long c_a_Id);
    Collection getAllCustAirRelations();
    void deleteCustAirRelation(long c_a_Id);    
}