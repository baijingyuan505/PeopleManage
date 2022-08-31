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
public class TbUser implements Serializable {
    private Integer id;
    private String phoneNumber;
    private String status;

    @Override
    public String toString() {
        Gson gson = new Gson();
        Map<String, Object> tbUserMap = new HashMap<>();

        tbUserMap.put("id", id);
        tbUserMap.put("phone_number", phoneNumber);
        tbUserMap.put("status", status);

        //Gson转化时会自动忽略值为null的键值对，非常人性化
        return gson.toJson(tbUserMap);
    }
}
