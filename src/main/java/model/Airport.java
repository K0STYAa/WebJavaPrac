package model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "airports")
public class Airport {

    @Id
    @SequenceGenerator(name="airport_id_generator", sequenceName="airport_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="airport_id_generator")
    @Column(name="airport_id")
    private long airportId;

    @Column(name="airport_name", length = 80)
    @NotBlank
    @NotNull
    @Size(min=5, max=40)
    private String airportName;

    public Airport() {

    }

    public Airport(String AirportName) {
        this.airportName = AirportName;
    }


    public long getAirportId() {
        return airportId;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String AirportName) {
        this.airportName = AirportName;
    }

    @Override
    public String toString() {
        return "Airport{" + "AirportId=" + airportId + ", AirportName=" + airportName + "}";
    }

    public void setAirportId(long airportId) {
        this.airportId = airportId;
    }
}

