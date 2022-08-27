package teleDemo.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import teleDemo.dao.TbUserDao;
import teleDemo.entities.TbUser;

import java.util.List;

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
    public List<TbUser> getTbUserByCandidates(int pageNum, int limit, TbUser candidates) {
        String sql = "select * from eqe.tb_user where 1=1";
        //!=,==可能会写成=从而引发bug，因此null作为左值
        if (null != candidates) {
            if (candidates.getId() > 0) {
                sql += " And id=" + candidates.getId();
            }

            if (null != candidates.getStatus()) {
                sql += " And status=" + candidates.getStatus();
            }

            if (null != candidates.getPhoneNumber()) {
                sql += " And phone_number=" + candidates.getPhoneNumber();
            }
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
            System.out.println("获取指定的用户信息失败: " + e);
        }
        return null;
    }

    @Override
    public int getTbUserSize() {
        String sql = "select count(*) from eqe.tb_user";
        int count = 0;
        try {
            count = jdbcTemplate.queryForObject(sql, Integer.class);
        } catch (Exception e) {
            System.out.println("查询用户信息总数失败: " + e);
        } finally {
            return count;
        }
    }

    @Override
    public void updateTbUser(TbUser tbUserInfo) {
        String sql = "update eqe.tb_user set phone_number = ?, status=? where id = ?";
        try {
            jdbcTemplate.update(sql, tbUserInfo.getPhoneNumber(), tbUserInfo.getStatus(), tbUserInfo.getId());
        } catch (Exception e) {
            System.out.println("修改用户信息失败: " + e);
        }
    }

    @Override
    public boolean deleteTbUser(int id) {
        String sql = "delete from eqe.tb_user where id = ?";
        int number = 0;
        try {
            number = jdbcTemplate.update(sql, id);
        } catch (Exception e) {
            System.out.println("删除用户信息失败: " + e);
        }
        if (0 != number) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void insertTbUser(TbUser tbUserInfo) {
        String sql = "insert into eqe.tb_user(id,phone_number,status) values(?,?,?)";
        try {
            jdbcTemplate.update(sql, tbUserInfo.getId(), tbUserInfo.getPhoneNumber(), tbUserInfo.getStatus());
        } catch (Exception e) {
            System.out.println("增加新用户信息失败: " + e);
        }
    }
}
