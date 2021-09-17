package DAO;

import model.Airline;
import model.Flight;
import model.Customer;
import model.CustAirRelation;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.Collection;

public interface AirlineDAO {
    void addAirline(Airline airline);
    void updateAirline(long airlineId, Airline airline);
    Airline getAirlineById(long airlineId);
    Collection getAllAirlines();
    void deleteAirline(long airlineId);
    ArrayList<Flight> getAllAirlineFlights(Long airlineId);
    ArrayList<CustAirRelation> getAllAirlineCust(Long airlineId);
}