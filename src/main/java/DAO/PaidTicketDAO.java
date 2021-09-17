package DAO;

import model.PaidTicket;

import java.sql.SQLException;
import java.util.Collection;

public interface PaidTicketDAO {
    void addPaidTicket(PaidTicket PaidTicket);
    void updatePaidTicket(long p_t_Id, PaidTicket PaidTicket);
    PaidTicket getPaidTicketById(long p_t_Id);
    Collection getAllPaidTickets();
    void deletePaidTicket(long p_t_Id);    
}