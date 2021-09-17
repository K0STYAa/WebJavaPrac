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
@Table(name = "customers")
public class Customer {

    @Id
    @SequenceGenerator(name="customer_id_generator", sequenceName="customer_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="customer_id_generator")
    @Column(name="customer_id")
    private long customerId;
    
    
    @Column(name="full_name", length = 150)
    @NotBlank
    @NotNull
    private String customerFullName;

    @Column(name="address", length = 80)
    @NotBlank
    @NotNull
    private String customerAddress;

    @Column(name="phone_number", length = 20)
    @NotBlank
    @NotNull
    private String customerPhoneNumber;
    
    @Column(name = "email", length = 50)
    @NotNull
    @NotBlank
    private String customerEmail;
    

    public Customer() {

    }

    public Customer(String customerFullName, String customerAddress, String customerPhoneNumber, String customerEmail) {
        this.customerFullName = customerFullName;
        this.customerAddress = customerAddress;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerEmail = customerEmail;
    }



    public long getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }
    
    public String getCustomerFullName() {
        return customerFullName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }
    
    public void setCustomerFullName(String customerFullName) {
        this.customerFullName = customerFullName;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerId=" + customerId + "customerFullName=" + customerFullName + "}";
    }
}

