package DAO;

import model.Airport;
import model.Flight;

import java.sql.SQLException;
import java.util.Collection;
import java.util.ArrayList;
public interface AirportDAO {
    void addAirport(Airport airport);
    void updateAirport(long airportId, Airport airport);
    Airport getAirportById(long airportId);
    ArrayList<Airport> getAllAirports();
    void deleteAirport(long airportId);
    ArrayList<Flight> getAllAirportDepFlights(Long airportId);
    ArrayList<Flight> getAllAirportArrFlights(Long airportId);
}