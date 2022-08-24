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
    public List<tbuser> getTbUserByCandidates(int pageNum, int limit, tbuser candidates) {
        String sql = "select * from eqe.tb_user where 1=1";
        if(candidates!=null) {
            if (candidates.getId() > 0) {
                sql += " And id=" + candidates.getId();
            }

            if (candidates.getStatus() != null) {
                sql += " And status=" + candidates.getStatus();
            }

            if (candidates.getPhoneNumber() != null) {
                sql += " And phone_number=" + candidates.getPhoneNumber();
            }
        }

        sql += " limit "+pageNum+", "+limit;
        System.out.println(sql);
        RowMapper<tbuser> rowMapper = new BeanPropertyRowMapper<>(tbuser.class);
        try {
            List<tbuser> tbUsers = jdbcTemplate.query(sql, rowMapper);
            return tbUsers;
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public int getTbUserSize() {
        String sql = "select count(*) from eqe.tb_user";
        int count = 0;
        try {
            count = jdbcTemplate.queryForObject(sql, Integer.class);
        }
        catch (Exception e)
        {
            System.out.println("查询总数失败");
        }
        finally {
            return count;
        }
    }

    @Override
    public void updateTbUser(tbuser tbUserInfo) {
        String sql = "update eqe.tb_user set phone_number = ?, status=? where id = ?";
        try {
            jdbcTemplate.update(sql, tbUserInfo.getPhoneNumber(), tbUserInfo.getStatus(), tbUserInfo.getId());
        }
        catch (Exception e)
        {
            System.out.println("修改用户信息失败: "+e);
        }
    }

    @Override
    public boolean deleteTbUser(tbuser tbUserInfo) {
        String sql = "delete from eqe.tb_user where id = ?";
        int number = 0;
        try {
            number = jdbcTemplate.update(sql,tbUserInfo.getId());
        }
        catch (Exception e)
        {
            System.out.println("删除用户信息失败: "+e);
        }
        if(number!=0){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void insertTbUser(tbuser tbUserInfo) {
        String sql = "insert into eqe.tb_user(id,phone_number,status) values(?,?,?)";
        try {
            jdbcTemplate.update(sql,tbUserInfo.getId(),tbUserInfo.getPhoneNumber(),tbUserInfo.getStatus());
        }
        catch (Exception e)
        {
            System.out.println("增加用户信息失败: "+e);
        }
    }


}
// 最好日代码
// 一般日代码
// 最差日代码