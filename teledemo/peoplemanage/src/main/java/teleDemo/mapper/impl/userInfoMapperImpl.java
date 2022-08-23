package teleDemo.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import teleDemo.entities.tbuser;
import teleDemo.mapper.userInfoMapper;
import java.util.List;
@Repository
public class userInfoMapperImpl implements userInfoMapper {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<tbuser> getAlltbUser() {
        return null;
    }

    @Override
    public List<tbuser> gettbUserByPage(int pageNum, int limit) {
        return null;
    }

    @Override
    public List<tbuser> gettbUserByPhoneNumber(String phoneNumber, int pageNum, int limit) {
        return null;
    }

    @Override
    public List<tbuser> gettbUserByStatus(String status, int pageNum, int limit) {
        return null;
    }

    //如果有100个Query项难道写100个if？  重写tbuser类的toString，得到tbuser的类变量名字符串，拆分出类变量名称数组，从而可以用循环来解决
    @Override
    public List<tbuser> gettbUserByQuery(int pageNum, int limit, tbuser query) {
        String sql = "select * from eqe.tb_user where 1=1";
        if(query!=null) {
            if (query.getId() > 0) {
                sql += " And id=" + query.getId();
            }

            if (query.getStatus() != null) {
                sql += " And status=" + query.getStatus();
            }

            if (query.getPhoneNumber() != null) {
                sql += " And phone_number=" + query.getPhoneNumber();
            }
        }

        sql += " limit "+pageNum+", "+limit;
        System.out.println(sql);
        RowMapper<tbuser> rowMapper = new BeanPropertyRowMapper<>(tbuser.class);
        List<tbuser> tbUsers = jdbcTemplate.query(sql,rowMapper);
        return tbUsers;
    }


}
// 最好日代码
// 一般日代码
// 最差日代码