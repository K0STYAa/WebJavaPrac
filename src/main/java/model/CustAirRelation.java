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
@Table(name = "cust_air_relation")
public class CustAirRelation {

    
    @Id
    @SequenceGenerator(name="c_a_id_generator", sequenceName="c_a_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="c_a_id_generator")
    @Column(name="c_a_id")
    private long c_a_Id;

    @ManyToOne
    @JoinColumn(name = "airline_id", referencedColumnName = "airline_company_id")
    private Airline airlineId;
    
    @ManyToOne
    @JoinColumn(name = "customer", referencedColumnName = "customer_id")
    private Customer customerId;
    
    @Column(name="discount")
    @NotBlank
    @NotNull
    private long discount;
    
    @Column(name="miles")
    @NotBlank
    @NotNull
    private long miles;
    

    public CustAirRelation() {

    }

    public CustAirRelation(Airline airlineId, Customer customerId, long discount, long miles) {
        this.airlineId = airlineId;
        this.customerId = customerId;
        this.discount = discount;
        this.miles = miles;
    }


    public long getCAId() {
        return c_a_Id;
    }

    public void setCAId(long c_a_Id) {
        this.c_a_Id = c_a_Id;
    }
    public Airline getAirlineId() {
        return airlineId;
    }
    
    public void setAirlineId(Airline airlineId) {
        this.airlineId = airlineId;
    }
    
    public Customer getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }
    
    public long getDiscount() {
        return discount;
    }
    
    public void setDiscount(long discount) {
        this.discount = discount;
    }
        
    public long getmiles() {
        return miles;
    }
    
    public void setmiles(long miles) {
        this.miles = miles;
    }

    @Override
    public String toString() {
        return "CustAirRelation{" + "customerId=" + customerId + "airlineId=" + airlineId + "}";
    }
}

