package teleDemo.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TbInfo implements Serializable{
    private Integer id;
    private Integer asu;
    private String cid;
    private String dateTime;
    private Integer dbm;
    private String lac;
    private double lat;
    private double lon;
    private Integer userId;
    private String netSpeed;
    private  Integer unreadSms;
    private  Integer wifiCount;
    private String wifiInfo;
}
