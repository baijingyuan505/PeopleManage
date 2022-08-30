package teleDemo.dao.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import teleDemo.dao.TbInfoDao;
import teleDemo.entities.TbInfo;
import teleDemo.entities.TbUser;

import java.util.List;
import java.util.Map;

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
            List<TbInfo> tbInfos = jdbcTemplate.query(sql, rowMapper);
            return tbInfos;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<TbInfo> getTbInfo(String candidates, int pageNum, int limit) {
        Gson gson = new Gson();
        Map<String,Object> map = gson.fromJson(candidates,new TypeToken<Map<String, Object>>() { }.getType());
        String sql = "select * from eqe.tb_info where 1=1";

        for(String key : map.keySet()){
            if("date_time" == key){
                sql += " And "+key+"<="+map.get(key);
            }
            else {
                sql += " And "+key+"="+map.get(key);
            }
        }

        if (0 <= pageNum && limit > 0) {
            //只进行有效的分页查询
            sql += " limit " + pageNum + ", " + limit;
        }

        RowMapper<TbInfo> rowMapper = new BeanPropertyRowMapper<>(TbInfo.class);

        try {
            List<TbInfo> tbInfos = jdbcTemplate.query(sql, rowMapper);
            return tbInfos;
        } catch (Exception e) {
            return null;
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
            TbInfo tbInfo = jdbcTemplate.queryForObject(sql, rowMapper, id);
            return tbInfo;
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
     * @return tbInfos(TbInfo类链表)
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
            List<TbInfo> tbInfos = jdbcTemplate.query(sql, rowMapper, userId);
            return tbInfos;
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
     * @return tbInfos(TbInfo类链表)
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
            List<TbInfo> tbInfos = jdbcTemplate.query(sql, rowMapper, dateTime);
            return tbInfos;
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
     * @return tbInfos(TbInfo类链表)
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
            List<TbInfo> tbInfos = jdbcTemplate.query(sql, rowMapper, lac);
            return tbInfos;
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

    @Override
    public int getTbInfoSizeByCandidates(String candidates) {
        Gson gson = new Gson();
        Map<String,Object> map = gson.fromJson(candidates,new TypeToken<Map<String, Object>>() { }.getType());
        String sql = "select count(*) from eqe.tb_info where 1=1";

        for(String key : map.keySet()){
            if("date_time" == key){
                sql += " And "+key+"<="+map.get(key);
            }
            else {
                sql += " And "+key+"="+map.get(key);
            }
        }
        int count = 0;
        try {
            count = jdbcTemplate.queryForObject(sql, Integer.class);
        } finally {
            return count;
        }
    }

    @Override
    public void updateTbInfo(TbInfo tbInfo) {
        String sql = "update `eqe`.`tb_info` set `asu`=?, `cid`=?, `date_time`=?, `dbm`=?, `lac`=?, `lat`=?, `lon`=?, `user_id`=?, `net_speed`=?, `unread_sms`=?, `wifi_count`=?, `wifi_info`=? where id = ?";
        try {
            jdbcTemplate.update(sql, tbInfo.getAsu(), tbInfo.getCid(),tbInfo.getDateTime(),tbInfo.getDbm(),tbInfo.getLac(),tbInfo.getLat(),tbInfo.getLon(),tbInfo.getUserId(),tbInfo.getNetSpeed(),tbInfo.getUnreadSms(),tbInfo.getWifiCount(),tbInfo.getWifiInfo(),tbInfo.getId());
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean deleteTbInfo(int id) {
        String sql = "delete from eqe.tb_info where id = ?";
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
    public void insertTbInfo(TbInfo tbInfo) {
        String sql = "INSERT INTO `eqe`.`tb_info` (`id`, `asu`, `cid`, `date_time`, `dbm`, `lac`, `lat`, `lon`, `user_id`, `net_speed`, `unread_sms`, `wifi_count`, `wifi_info`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql, tbInfo.getId(), tbInfo.getAsu(), tbInfo.getCid(),tbInfo.getDateTime(),tbInfo.getDbm(),tbInfo.getLac(),tbInfo.getLat(),tbInfo.getLon(),tbInfo.getUserId(),tbInfo.getNetSpeed(),tbInfo.getUnreadSms(),tbInfo.getWifiCount(),tbInfo.getWifiInfo());
        } catch (Exception e) {
            throw e;
        }
    }
}
