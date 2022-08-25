package teleDemo.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Trace implements Serializable {
    public String data_time;
    public double lat;
    public double lon;

    public Trace(String data_time,double lat,double lon){
        this.data_time=data_time;
        this.lat=lat;
        this.lon=lon;
    }
    public Trace(){}

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public String getData_time() {
        return data_time;
    }
}
