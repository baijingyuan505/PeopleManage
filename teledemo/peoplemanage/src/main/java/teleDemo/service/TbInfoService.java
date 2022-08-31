package teleDemo.service;

import org.apache.ibatis.annotations.Param;
import teleDemo.entities.TbInfo;

import java.util.List;
import java.util.Map;

//Mapper层应该尽可能只写最基本最不可继续抽象的SQL语句
//Service层不仅负责提供调用这些基本SQL功能的接口，也还有必要糅合基础SQL功能去创造复杂的SQL功能以满足多维度的需求
//但目前需求分析指出的所需功能并不复杂，所以Service层长得和Mapper层几乎一样
public interface TbInfoService {
    List<TbInfo> getAllTbInfo(int pageNum, int limit);

    List<TbInfo> getTbInfo(String candidates, int pageNum, int limit);

    int getTbInfoSize();

    int getTbInfoSizeByCandidates(String candidates);

    TbInfo getTbInfoById(@Param("id") int id);

    List<TbInfo> getTbInfoByTbUserId(int userId, int pageNum, int limit);

    List<TbInfo> getTbInfoByDateTime(String dateTime, int pageNum, int limit);

    List<TbInfo> getTbInfoByLac(String lac, int pageNum, int limit);

    List<Map<String,Object>> getAllLonAndLat(int pageNum,int limit);

    List<Map<String,Object>> getLonAndLat(String candidates,int pageNum,int limit);

    Map<String,Object> getLonAndLatById(@Param("id") int id);

    List<Map<String,Object>> getLonAndLatByTbUserId(int tbUserId, int pageNum, int limit);
    
      //lan
    List<Map<String, Object>> getDealedLonAndLatByTbUserId(int tbUserId, int pageNum, int limit);

    List<Map<String,Object>> getLonAndLatByDateTime(String dateTime, int pageNum, int limit);

    List<Map<String,Object>> getLonAndLatByLac(String lac, int pageNum, int limit);

    boolean checkDateTime(String dateTime);
}
