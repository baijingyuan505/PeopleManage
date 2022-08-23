package teleDemo.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class tbInfo implements Serializable{
    private int id;
    private int asu;
    private String cid;
    private Date dateTime;
    private int dbm;
    private String lac;
    private double lat;
    private double lon;
    private int userId;
    private String netSpeed;
    private  int unreadSms;
    private  int wifiCount;
    private String wifiInfo;
}
