package teleDemo.dao;

import teleDemo.entities.TbInfo;
import teleDemo.entities.TbUser;

import java.util.List;

public interface TbInfoDao {
    List<TbInfo> getAllTbInfo(int pageNum, int limit);

    List<TbInfo> getTbInfo(String candidates, int pageNum, int limit);

    TbInfo getTbInfoById(int id);

    List<TbInfo> getTbInfoByTbUserId(int userId, int pageNum, int limit);

    List<TbInfo> getTbInfoByDateTime(String dateTime, int pageNum, int limit);

    List<TbInfo> getTbInfoByLac(String lac, int pageNum, int limit);

    int getTbInfoSize();

    int getTbInfoSizeByCandidates(String candidates);

    void updateTbInfo(TbInfo tbInfo);

    boolean deleteTbInfo(int id);

    void insertTbInfo(TbInfo tbInfo);

   String addSqlSuffix(String sql);
}
