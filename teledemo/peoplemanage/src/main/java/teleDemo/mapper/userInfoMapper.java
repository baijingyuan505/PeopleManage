package teleDemo.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import teleDemo.entities.tbuser;

import java.util.List;

@Mapper
public interface userInfoMapper {
    @Select("SELECT * FROM eqe.tb_user;")
    @Results(id = "tbUserMap", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "phone_number", property = "phoneNumber", jdbcType = JdbcType.LONGVARCHAR),
            @Result(column = "status", property = "status", jdbcType = JdbcType.VARCHAR),
    })
    List<tbuser> getAlltbUser();

    @Select("select * from eqe.tb_user limit #{pageNum}, #{limit};")
    @ResultMap(value = "tbUserMap")
    List<tbuser> getTbUserByPage(@Param("pageNum") int pageNum, @Param("limit") int limit);

    @Select("select * from eqe.tb_user where phone_number=#{phoneNumber} limit #{pageNum}, #{limit};")
    @ResultMap(value = "tbUserMap")
    List<tbuser> getTbUserByPhoneNumber(@Param("phoneNumber") String phoneNumber, @Param("pageNum") int pageNum, @Param("limit") int limit);

    @Select("select * from eqe.tb_user where status=#{status} limit #{pageNum}, #{limit};")
    @ResultMap(value = "tbUserMap")
    List<tbuser> getTbUserByStatus(@Param("status") String status, @Param("pageNum") int pageNum, @Param("limit") int limit);

    List<tbuser> getTbUserByCandidates(int pageNum, int limit, tbuser candidates);

    int getTbUserSize();

    void updateTbUser(tbuser tbUserInfo);

    boolean deleteTbUser(int id);

    void insertTbUser(tbuser tbUserInfo);
}
