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

    private int count;
    private Object data;

    static public Result createSuccessResult(int coumt,Object data)
    {
        Result result = new Result();
        result.code = 200;
        result.msg = "请求成功";
        result.count=coumt;
        result.data = data;
        return result;
    }

    static public Result createFailureResult(String err)
    {
        Result result = new Result();
        result.code = 404;
        result.msg = err;
        result.count=0;
        result.data=null;
        return result;
    }
}
