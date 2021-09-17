package DAO;

import model.Flight;

import java.sql.SQLException;
import java.util.Collection;

public interface FlightDAO {
    void addFlight(Flight flight);
    void updateFlight(long flightNumber, Flight flight);
    Flight getFlightById(long flightNumber);
    Collection getAllFlights();
    void deleteFlight(long flightNumber);    
}