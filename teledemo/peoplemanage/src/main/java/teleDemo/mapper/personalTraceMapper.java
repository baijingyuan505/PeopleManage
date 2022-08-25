package teleDemo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import teleDemo.entities.Trace;

import java.util.List;

@Mapper
public interface personalTraceMapper {
    @Select("SELECT date_time,lat,lon FROM eqe.tb_info where user_id=#{id}" +
            ";")
    List<Trace> getPersonalTrace(@Param("id") int id);
}
