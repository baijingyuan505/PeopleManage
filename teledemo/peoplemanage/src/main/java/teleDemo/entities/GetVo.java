package teleDemo.entities;
import lombok.*;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetVo<T> {
    private int code;
    private String msg;
    private int count;
    private List<T> data;
}
