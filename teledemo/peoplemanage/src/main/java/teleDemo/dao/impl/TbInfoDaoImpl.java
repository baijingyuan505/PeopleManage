package teleDemo.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import teleDemo.dao.TbInfoDao;
import teleDemo.entities.TbInfo;

import java.util.List;

@Repository
public class TbInfoDaoImpl implements TbInfoDao {


    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * 分页获取所有轨迹信息
     *
     * @param pageNum
     * @param limit
     * @return
     */
    @Override
    public List<TbInfo> getAllTbInfo(int pageNum, int limit) {
        String sql = "SELECT * FROM eqe.tb_info";
        if (0 <= pageNum && limit > 0) {
            //只进行有效的分页查询
            sql += " limit " + pageNum + ", " + limit;
        }
        RowMapper<TbInfo> rowMapper = new BeanPropertyRowMapper<>(TbInfo.class);

        try {
            List<TbInfo> TbInfos = jdbcTemplate.query(sql, rowMapper);
            return TbInfos;
        } catch (Exception e) {
            return null;
        }
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
        } finally {
            return count;
        }
    }

    /**
     * 获取指定id的某条轨迹信息
     *
     * @param id
     * @return TbInfo(TbInfo类实例)
     */
    @Override
    public TbInfo getTbInfoById(int id) {
        String sql = "SELECT * FROM eqe.tb_info where id = ?";
        RowMapper<TbInfo> rowMapper = new BeanPropertyRowMapper<>(TbInfo.class);

        try {
            TbInfo TbInfo = jdbcTemplate.queryForObject(sql, rowMapper, id);
            return TbInfo;
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 分页获取指定用户id的某组轨迹信息
     *
     * @param userId
     * @param pageNum
     * @param limit
     * @return TbInfos(TbInfo类链表)
     */
    @Override
    public List<TbInfo> getTbInfoByTbUserId(int userId, int pageNum, int limit) {
        String sql = "SELECT * FROM eqe.tb_info where user_id = ?";
        if (0 <= pageNum && limit > 0) {
            //只进行有效的分页查询
            sql += " limit " + pageNum + ", " + limit;
        }
        RowMapper<TbInfo> rowMapper = new BeanPropertyRowMapper<>(TbInfo.class);

        try {
            List<TbInfo> TbInfos = jdbcTemplate.query(sql, rowMapper, userId);
            return TbInfos;
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 分页获取指定日期之前的所有轨迹信息
     *
     * @param dateTime e.g.: "2019-05-21 20:50:52"
     * @param pageNum
     * @param limit
     * @return TbInfos(TbInfo类链表)
     */
    @Override
    public List<TbInfo> getTbInfoByDateTime(String dateTime, int pageNum, int limit) {
        String sql = "SELECT * FROM eqe.tb_info where date_time<= ?";
        if (0 <= pageNum && limit > 0) {
            //只进行有效的分页查询
            sql += " limit " + pageNum + ", " + limit;
        }
        RowMapper<TbInfo> rowMapper = new BeanPropertyRowMapper<>(TbInfo.class);
        try {
            List<TbInfo> TbInfos = jdbcTemplate.query(sql, rowMapper, dateTime);
            return TbInfos;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 分页获取指定区域编码的所有轨迹信息
     *
     * @param lac     e.g. "4157"
     * @param pageNum
     * @param limit
     * @return TbInfos(TbInfo类链表)
     */
    @Override
    public List<TbInfo> getTbInfoByLac(String lac, int pageNum, int limit) {
        String sql = "SELECT * FROM eqe.tb_info where lac = ?";
        if (0 <= pageNum && limit > 0) {
            //只进行有效的分页查询
            sql += " limit " + pageNum + ", " + limit;
        }
        RowMapper<TbInfo> rowMapper = new BeanPropertyRowMapper<>(TbInfo.class);
        try {
            List<TbInfo> TbInfos = jdbcTemplate.query(sql, rowMapper, lac);
            return TbInfos;
        } catch (Exception e) {
            return null;
        }
    }
}
