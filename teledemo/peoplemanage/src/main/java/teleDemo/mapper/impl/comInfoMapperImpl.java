package teleDemo.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import teleDemo.entities.tbInfo;
import teleDemo.entities.tbuser;
import teleDemo.mapper.comInfoMapper;

import java.util.List;

public class comInfoMapperImpl implements comInfoMapper {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 不分页获取所有轨迹信息，由MyBatis注解实现
     *
     * @return List<tbInfo>
     */
    @Override
    public List<tbInfo> getAlltbINfo() {
        return null;
    }

    /**
     * 分页获取所有轨迹信息，由MyBatis注解实现
     *
     * @param pageNum
     * @param limit
     * @return List<tbInfo>
     */
    @Override
    public List<tbInfo> getTbINfoByPage(int pageNum, int limit) {
        return null;
    }

    /**
     * 获取tb_info表中的数据总数量
     *
     * @return count(总数量, 可能为0, 作为除数需要判断)
     */
    @Override
    public int getTbInfoSize() {
        String sql = "select count(*) from eqe.tb_info";
        int count = 0;
        try {
            count = jdbcTemplate.queryForObject(sql, Integer.class);
        } catch (Exception e) {
            System.out.println("查询轨迹信息总数失败: " + e);
        } finally {
            return count;
        }
    }

    /**
     * 获取指定id的某条轨迹信息
     *
     * @param id
     * @return tbInfo(tbInfo类实例)
     */
    @Override
    public tbInfo getTbInfoById(int id) {
        String sql = "SELECT * FROM eqe.tb_info where id = ?";
        RowMapper<tbInfo> rowMapper = new BeanPropertyRowMapper<>(tbInfo.class);

        try {
            tbInfo tbInfo = jdbcTemplate.queryForObject(sql, rowMapper, id);
            return tbInfo;
        } catch (Exception e) {
            System.out.println("获取指定id的轨迹信息失败: " + e);
        }
        return null;
    }

    /**
     * 分页获取指定用户id的某组轨迹信息
     *
     * @param userId
     * @param pageNum
     * @param limit
     * @return tbInfos(tbInfo类链表)
     */
    @Override
    public List<tbInfo> getTbInfoByTbUserId(int userId, int pageNum, int limit) {
        String sql = "SELECT * FROM eqe.tb_info where user_id = ?";
        if (0 <= pageNum && limit > 0) {
            //只进行有效的分页查询
            sql += " limit " + pageNum + ", " + limit;
        }
        RowMapper<tbInfo> rowMapper = new BeanPropertyRowMapper<>(tbInfo.class);

        try {
            List<tbInfo> tbInfos = jdbcTemplate.query(sql, rowMapper, userId);
            return tbInfos;
        } catch (Exception e) {
            System.out.println("按用户id查询轨迹信息失败: " + e);
        }
        return null;
    }

    /**
     * 分页获取指定日期之前的所有轨迹信息
     *
     * @param dateTime e.g.: "2019-05-21 20:50:52"
     * @param pageNum
     * @param limit
     * @return tbInfos(tbInfo类链表)
     */
    @Override
    public List<tbInfo> getTbInfoByDateTime(String dateTime, int pageNum, int limit) {
        String sql = "SELECT * FROM eqe.tb_info where date_time<= ?";
        if (0 <= pageNum && limit > 0) {
            //只进行有效的分页查询
            sql += " limit " + pageNum + ", " + limit;
        }
        RowMapper<tbInfo> rowMapper = new BeanPropertyRowMapper<>(tbInfo.class);
        try {
            List<tbInfo> tbInfos = jdbcTemplate.query(sql, rowMapper, dateTime);
            return tbInfos;
        } catch (Exception e) {
            System.out.println("获取指定日期之前的轨迹信息失败: " + e);
        }
        return null;
    }

    /**
     * 分页获取指定区域编码的所有轨迹信息
     *
     * @param lac   e.g. "4157"
     * @param pageNum
     * @param limit
     * @return tbInfos(tbInfo类链表)
     */
    @Override
    public List<tbInfo> getTbInfoByLac(String lac, int pageNum, int limit) {
        String sql = "SELECT * FROM eqe.tb_info where lac = ?";
        if (0 <= pageNum && limit > 0) {
            //只进行有效的分页查询
            sql += " limit " + pageNum + ", " + limit;
        }
        RowMapper<tbInfo> rowMapper = new BeanPropertyRowMapper<>(tbInfo.class);
        try {
            List<tbInfo> tbInfos = jdbcTemplate.query(sql, rowMapper, lac);
            return tbInfos;
        } catch (Exception e) {
            System.out.println("获取指定区域编码的轨迹信息失败: " + e);
        }
        return null;
    }


}
