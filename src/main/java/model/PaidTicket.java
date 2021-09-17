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
@Table(name = "paid_tickets")
public class PaidTicket {

    
    @Id
    @SequenceGenerator(name="p_t_id_generator", sequenceName="p_t_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="p_t_id_generator")
    @Column(name="p_t_id")
    private long p_t_Id;

    @ManyToOne
    @JoinColumn(name = "flight_number", referencedColumnName = "flight_number")
    private Flight flightNumber;
    
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customerId;
    

    public PaidTicket() {

    }

    public PaidTicket(Flight flightNumber, Customer customerId) {
        this.flightNumber = flightNumber;
        this.customerId = customerId;
    }


    public long getBPId() {
        return p_t_Id;
    }

    public void setBPId(long p_t_Id) {
        this.p_t_Id = p_t_Id;
    }

    public Flight getFlightNumber() {
        return flightNumber;
    }
    
    public void setFlightNumber(Flight flightNumber) {
        this.flightNumber = flightNumber;
    }
    
    public Customer getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "PaidTicket{" + "flightNumber=" + flightNumber + "customerId=" + customerId + "}";
    }
}

