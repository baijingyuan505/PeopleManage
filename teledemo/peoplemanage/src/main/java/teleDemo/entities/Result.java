package teleDemo.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Result{
    private int code;
    private String msg;
    private Object data;

    static public Result createSuccessResult(Object data)
    {
        Result result = new Result();
        result.code = 200;
        result.msg = "请求成功捏";
        result.data = data;
        return result;
    }

    static public Result createFailureResult(String err)
    {
        Result result = new Result();
        result.code = 404;
        result.msg = err;
        result.data=null;
        return result;
    }
}
