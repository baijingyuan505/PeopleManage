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

    @Override
    public List<tbInfo> getAlltbINfo() {
        return null;
    }

    @Override
    public List<tbInfo> gettbINfoByPage(int pageNum, int limit) {
        return null;
    }

    @Override
    public int getTbInfoSize() {
        String sql = "select count(*) from eqe.tb_info";
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


}
