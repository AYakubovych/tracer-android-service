package ddns.net.tracerandroidservice.data.entities;


import ddns.net.tracerandroidservice.util.LocationMessage;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
@Table
public class LocationData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Column
    private long targetId;

    @Column
    private double latitude;

    @Column
    private double longitude;

    @Column
    private String  date;

    @Column
    private String  time;

    public LocationData(){}

    public LocationData(LocationMessage message){
        this.targetId = message.getId();
        this.latitude = message.getLatitude();
        this.longitude = message.getLongitude();

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //[0] - day,[1] - hours
        String[] dateArray = simpleDateFormat.format(date).split(" ");
        this.date = dateArray[0];
        this.time = dateArray[1];
    }

    @Override
    public String toString(){
        return id + " " + targetId + " " + latitude + " " + longitude + " " + date + " " + time;
    }

    public long getTargetId() {
        return targetId;
    }

    public void setTargetId(long targetId) {
        this.targetId = targetId;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
