package teleDemo.dao.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import teleDemo.dao.TbUserDao;
import teleDemo.entities.TbUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TbUserDaoImpl implements TbUserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<TbUser> getAllTbUser(int pageNum, int limit) {
        String sql = "select * from eqe.tb_user";
        if (0 <= pageNum && limit > 0) {
            //只进行有效的分页查询
            sql += " limit " + pageNum + ", " + limit;
        }
        RowMapper<TbUser> rowMapper = new BeanPropertyRowMapper<>(TbUser.class);
        try {
            List<TbUser> tbUsers = jdbcTemplate.query(sql, rowMapper);
            return tbUsers;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<TbUser> getTbUserByPhoneNumber(String phoneNumber, int pageNum, int limit) {
        String sql = "select * from eqe.tb_user where phone_number = ?";
        if (0 <= pageNum && limit > 0) {
            //只进行有效的分页查询
            sql += " limit " + pageNum + ", " + limit;
        }
        RowMapper<TbUser> rowMapper = new BeanPropertyRowMapper<>(TbUser.class);
        try {
            List<TbUser> tbUsers = jdbcTemplate.query(sql, rowMapper,phoneNumber);
            return tbUsers;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<TbUser> getTbUserByStatus(String status, int pageNum, int limit) {
        String sql = "select * from eqe.tb_user where status = ?";
        if (0 <= pageNum && limit > 0) {
            //只进行有效的分页查询
            sql += " limit " + pageNum + ", " + limit;
        }
        RowMapper<TbUser> rowMapper = new BeanPropertyRowMapper<>(TbUser.class);
        try {
            List<TbUser> tbUsers = jdbcTemplate.query(sql, rowMapper,status);
            return tbUsers;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<TbUser> getTbUserByCandidates(String candidates,int pageNum, int limit) {
        Gson gson = new Gson();
        Map<String,Object> map = gson.fromJson(candidates,new TypeToken<Map<String, Object>>() { }.getType());
        String sql = "select * from eqe.tb_user where 1=1";

        for(String key : map.keySet()){
            sql += " And "+key+"=\""+map.get(key)+"\"";
        }

        if (0 <= pageNum && limit > 0) {
            //只进行有效的分页查询
            sql += " limit " + pageNum + ", " + limit;
        }

        RowMapper<TbUser> rowMapper = new BeanPropertyRowMapper<>(TbUser.class);
        try {
            List<TbUser> tbUsers = jdbcTemplate.query(sql, rowMapper);
            return tbUsers;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int getTbUserSize() {
        String sql = "select count(*) from eqe.tb_user";
        int count = 0;
        try {
            count = jdbcTemplate.queryForObject(sql, Integer.class);
        } finally {
            return count;
        }
    }

    @Override
    public int getTbUserSizeByCandidates(String candidates) {
        Gson gson = new Gson();
        Map<String,Object> map = gson.fromJson(candidates,new TypeToken<Map<String, Object>>() { }.getType());
        String sql = "select count(*) from eqe.tb_user where 1=1";

        for(String key : map.keySet()){
            sql += " And "+key+"=\""+map.get(key)+"\"";
        }
        int count = 0;
        try {
            count = jdbcTemplate.queryForObject(sql, Integer.class);
        } finally {
            return count;
        }
    }

    @Override
    public void updateTbUser(TbUser tbUser) {
        String sql = "update eqe.tb_user set phone_number = ?, status=? where id = ?";
        try {
            jdbcTemplate.update(sql, tbUser.getPhoneNumber(), tbUser.getStatus(), tbUser.getId());
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean deleteTbUser(int id) {
        String sql = "delete from eqe.tb_user where id = ?";
        int number = 0;
        try {
            number = jdbcTemplate.update(sql, id);
        } catch (Exception e) {
            return false;
        }
        if (0 != number) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void insertTbUser(TbUser tbUser) {
        String sql = "insert into eqe.tb_user(id,phone_number,status) values(?,?,?)";
        try {
            jdbcTemplate.update(sql, tbUser.getId(), tbUser.getPhoneNumber(), tbUser.getStatus());
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String addSqlSuffix(String sql) {
        return null;
    }
}
