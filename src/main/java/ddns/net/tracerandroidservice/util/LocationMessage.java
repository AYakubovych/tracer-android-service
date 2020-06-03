package ddns.net.tracerandroidservice.util;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class LocationMessage implements Serializable {

    private long id;
    private double latitude;
    private double longitude;

    public LocationMessage(@JsonProperty("id") long id,
                           @JsonProperty("latitude") double latitude,
                           @JsonProperty("longitude") double longitude) {
                this.id = id;
                this.latitude = latitude;
                this.longitude = longitude;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "LocationMessage: " + id
                + " lat: " + latitude
                + " lon: " + longitude;
    }
}
