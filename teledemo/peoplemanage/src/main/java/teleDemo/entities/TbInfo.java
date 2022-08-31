package teleDemo.entities;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TbInfo implements Serializable {
    private Integer id;
    private Integer asu;
    private String cid;
    private String dateTime;
    private Integer dbm;
    private String lac;
    private Double lat;
    private Double lon;
    private Integer userId;
    private String netSpeed;
    private Integer unreadSms;
    private Integer wifiCount;
    private String wifiInfo;

    public String getDateTime() {
        return dateTime.replace(".0", "");
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        Map<String, Object> tbInfoMap = new HashMap<>();
        tbInfoMap.put("id", id);
        tbInfoMap.put("asu", asu);
        tbInfoMap.put("cid", cid);
        tbInfoMap.put("date_time", dateTime);
        tbInfoMap.put("dbm", dbm);
        tbInfoMap.put("lac", lac);
        tbInfoMap.put("lat", lat);
        tbInfoMap.put("lon", lon);
        tbInfoMap.put("user_id", userId);
        tbInfoMap.put("net_speed", netSpeed);
        tbInfoMap.put("unread_sms", unreadSms);
        tbInfoMap.put("wifi_count", wifiCount);
        tbInfoMap.put("wifi_info", wifiInfo);
        //Gson转化时会自动忽略值为null的键值对，非常人性化
        return gson.toJson(tbInfoMap);
    }
}
