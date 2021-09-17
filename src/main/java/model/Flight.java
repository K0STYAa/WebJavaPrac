package model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @SequenceGenerator(name="flight_number_generator", sequenceName="flight_number_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="flight_number_generator")
    @Column(name="flight_number")
    private long flightNumber;
    
    
    @ManyToOne
    @JoinColumn(name = "airline_company_id", referencedColumnName = "airline_company_id")
    private Airline airlineId;
    
    @ManyToOne
    @JoinColumn(name = "departure_id", referencedColumnName = "airport_id")
    private Airport departure;
    
    @ManyToOne
    @JoinColumn(name = "arrival_id", referencedColumnName = "airport_id")
    private Airport arrival;
    
    @Column(name = "departure_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date DepartureTime;
    
    @Column(name = "arrival_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date ArrivalTime;

    @Column(name="cost")
    @NotNull
    private long cost;
    
    @Column(name="number")
    @NotNull
    private long number;
    
    @Column(name="miles")
    @NotNull
    private long miles;

    public Flight() {

    }

    public Flight(Airline airlineId, Airport departure, Airport arrival, Date DepartureTime, Date ArrivalTime, long cost, long number, long miles) {
        this.airlineId = airlineId;
        this.departure = departure;
        this.arrival = arrival;
        this.DepartureTime = DepartureTime;
        this.ArrivalTime = ArrivalTime;
        this.cost = cost;
        this.number = number;
        this.miles = miles;
    }



    public long getFlightNumber() {
        return flightNumber;
    }
    
    public void setFlightNumber(long flightNumber) {
        this.flightNumber = flightNumber;
    }
    
    public Airline getAirlineId() {
        return airlineId;
    }
    
    public void setAirlineId(Airline airlineId) {
        this.airlineId = airlineId;
    }
    
    public Airport getDeparture() {
        return departure;
    }
    
    public void setDeparture(Airport departure) {
        this.departure = departure;
    }
    
    public Airport getArrival() {
        return arrival;
    }
    
    public void setArrival(Airport arrival) {
        this.arrival = arrival;
    }
    
    public Date getDepartureTime() {
        return DepartureTime;
    }

    public void setDepartureTime(Date DepartureTime) {
        this.DepartureTime = DepartureTime;
    }
    
    public Date getArrivalTime() {
        return ArrivalTime;
    }

    public void setArrivalTime(Date ArrivalTime) {
        this.ArrivalTime = ArrivalTime;
    }
    
    public long getcost() {
        return cost;
    }
    
    public void setcost(long cost) {
        this.cost = cost;
    }
    
    public long getnumber() {
        return number;
    }
    
    public void setnumber(long number) {
        this.number = number;
    }
    
    public long getmiles() {
        return miles;
    }
    
    public void setmiles(long miles) {
        this.miles = miles;
    }

    @Override
    public String toString() {
        return "Flights{" + "flightNumber=" + flightNumber + "AirlineId=" + airlineId + ", AirportDeparture=" + departure + "AirportArrival=" + arrival + "}";
    }
}

