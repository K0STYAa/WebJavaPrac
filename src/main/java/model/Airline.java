package model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "airlines")
public class Airline {

    @Id
    @SequenceGenerator(name="airline_company_id_generator", sequenceName="airline_company_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="airline_company_id_generator")
    @Column(name="airline_company_id")
    private long airlineId;

    @Column(name="airline_company_name", length = 80)
    @NotBlank
    @NotNull
    @Size(min=5, max=40)
    private String airlineName;

    public Airline() {

    }

    public Airline(String AirlineName) {
        this.airlineName = AirlineName;
    }


    public long getAirlineId() {
        return airlineId;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String AirlineName) {
        this.airlineName = AirlineName;
    }

    @Override
    public String toString() {
        return "Airline{" + "AirlineId=" + airlineId + ", AirlineName=" + airlineName + "}";
    }

    public void setAirlineId(long airlineId) {
        this.airlineId = airlineId;
    }
}

