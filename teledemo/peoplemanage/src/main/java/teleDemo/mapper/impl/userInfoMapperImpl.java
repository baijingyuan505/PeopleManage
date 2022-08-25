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

    /**
     * 不分页获取所有用户信息，由MyBatis注解完成
     *
     * @return tbUsers(tbuser类链表)
     */
    @Override
    public List<tbuser> getAlltbUser() {
        return null;
    }


    /**
     * 分页获取所有用户信息，由MyBatis注解完成
     *
     * @param pageNum
     * @param limit
     * @return tbUsers(tbuser类链表)
     */
    @Override
    public List<tbuser> getTbUserByPage(int pageNum, int limit) {
        return null;
    }

    /**
     * 分页获取指定电话号码的所有用户信息，由MyBatis注解完成
     *
     * @param phoneNumber
     * @param pageNum
     * @param limit
     * @return tbUsers(tbuser类链表)
     */
    @Override
    public List<tbuser> getTbUserByPhoneNumber(String phoneNumber, int pageNum, int limit) {
        return null;
    }

    /**
     * 分页获取指定状态所有用户信息，由MyBatis注解完成
     *
     * @param status
     * @param pageNum
     * @param limit
     * @return tbUsers(tbuser类链表)
     */
    @Override
    public List<tbuser> getTbUserByStatus(String status, int pageNum, int limit) {
        return null;
    }

    /**
     * 分页获取指定查询条件的所有用户信息
     *
     * @param pageNum
     * @param limit
     * @param candidates 一个tbuser类实例，会根据其成员变量的值是否有查询意义而改变sql的查询条件
     * @return tbUsers(tbuser类链表)
     */
    //如果有100个Query项难道写100个if？  重写tbuser类的toString，得到tbuser的类变量名字符串，拆分出类变量名称数组，从而可以用循环来解决
    @Override
    public List<tbuser> getTbUserByCandidates(int pageNum, int limit, tbuser candidates) {
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

        RowMapper<tbuser> rowMapper = new BeanPropertyRowMapper<>(tbuser.class);
        try {
            List<tbuser> tbUsers = jdbcTemplate.query(sql, rowMapper);
            return tbUsers;
        } catch (Exception e) {
            System.out.println("获取指定的用户信息失败: " + e);
        }
        return null;
    }

    /**
     * 获取tb_user表中的数据总数量
     *
     * @return count(总数量, 可能为0, 作为除数需要判断)
     */
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

    /**
     * 更新指定id的某条用户信息
     *
     * @param tbUserInfo (用户类实例，其中包含了待修改用户所有的新信息)
     */
    @Override
    public void updateTbUser(tbuser tbUserInfo) {
        String sql = "update eqe.tb_user set phone_number = ?, status=? where id = ?";
        try {
            jdbcTemplate.update(sql, tbUserInfo.getPhoneNumber(), tbUserInfo.getStatus(), tbUserInfo.getId());
        } catch (Exception e) {
            System.out.println("修改用户信息失败: " + e);
        }
    }

    /**
     * 删除指定id的某条用户信息
     *
     * @param id (用户id)
     * @return False/True(删除成功/删除失败)
     */
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

    /**
     * 增加一条新的用户信息
     *
     * @param tbUserInfo (tbuser实例，包含新用户的所有信息)
     */
    @Override
    public void insertTbUser(tbuser tbUserInfo) {
        String sql = "insert into eqe.tb_user(id,phone_number,status) values(?,?,?)";
        try {
            jdbcTemplate.update(sql, tbUserInfo.getId(), tbUserInfo.getPhoneNumber(), tbUserInfo.getStatus());
        } catch (Exception e) {
            System.out.println("增加新用户信息失败: " + e);
        }
    }


}