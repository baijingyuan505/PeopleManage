package teleDemo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import teleDemo.entities.TbInfo;
import teleDemo.dao.TbInfoDao;
import teleDemo.service.TbInfoService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TbInfoServiceImpl implements TbInfoService {
    @Autowired
    TbInfoDao tbInfoDao;

    @Override
    public List<TbInfo> getAllTbInfo(int pageNum, int limit) {
        return tbInfoDao.getAllTbInfo(pageNum, limit);
    }

    @Override
    public List<TbInfo> getTbInfo(String candidates, int pageNum, int limit) {
        return tbInfoDao.getTbInfo(candidates, pageNum, limit);
    }


    @Override
    public int getTbInfoSize() {
        return tbInfoDao.getTbInfoSize();
    }

    @Override
    public int getTbInfoSizeByCandidates(String candidates) {
        return tbInfoDao.getTbInfoSizeByCandidates(candidates);
    }

    @Override
    public TbInfo getTbInfoById(int id) {
        return tbInfoDao.getTbInfoById(id);
    }

    @Override
    public List<TbInfo> getTbInfoByTbUserId(int userId, int pageNum, int limit) {
        return tbInfoDao.getTbInfoByTbUserId(userId, pageNum, limit);
    }

    @Override
    public List<TbInfo> getTbInfoByDateTime(String dateTime, int pageNum, int limit) {
        if (!checkDateTime(dateTime)) {
            //传入时间错误，返回默认结果
            return getAllTbInfo(pageNum, limit);
        }
        return tbInfoDao.getTbInfoByDateTime(dateTime, pageNum, limit);
    }

    @Override
    public List<TbInfo> getTbInfoByLac(String lac, int pageNum, int limit) {
        return tbInfoDao.getTbInfoByLac(lac, pageNum, limit);
    }

    @Override
    public List<Map<String, Object>> getAllLonAndLat(int pageNum, int limit) {
        List<Map<String, Object>> points = new ArrayList<>();

        List<TbInfo> tbInfos = tbInfoDao.getAllTbInfo(pageNum, limit);
        if (null == tbInfos) {
            return null;
        }
        for (int i = 0; i < tbInfos.size(); i++) {
            Map<String, Object> point = new HashMap<>();
            point.put("date_time", tbInfos.get(i).getDateTime());
            point.put("lon", tbInfos.get(i).getLon());
            point.put("lat", tbInfos.get(i).getLat());
            points.add(point);
        }
        return points;
    }

    @Override
    public List<Map<String, Object>> getLonAndLat(String candidates, int pageNum, int limit) {
        List<Map<String, Object>> points = new ArrayList<>();

        List<TbInfo> tbInfos = tbInfoDao.getTbInfo(candidates, pageNum, limit);
        if (null == tbInfos) {
            return null;
        }
        for (int i = 0; i < tbInfos.size(); i++) {
            Map<String, Object> point = new HashMap<>();
            point.put("date_time", tbInfos.get(i).getDateTime());
            point.put("lon", tbInfos.get(i).getLon());
            point.put("lat", tbInfos.get(i).getLat());
            points.add(point);
        }
        return points;
    }

    @Override
    public Map<String, Object> getLonAndLatById(int id) {
        Map<String, Object> point = new HashMap<>();

        TbInfo TbInfo = tbInfoDao.getTbInfoById(id);
        if (null == TbInfo) {
            return null;
        }
        point.put("date_time", TbInfo.getDateTime());
        point.put("lon", TbInfo.getLon());
        point.put("lat", TbInfo.getLat());
        return point;
    }

    @Override
    public List<Map<String, Object>> getLonAndLatByTbUserId(int tbUserId, int pageNum, int limit) {
        List<Map<String, Object>> points = new ArrayList<>();

        List<TbInfo> tbInfos = tbInfoDao.getTbInfoByTbUserId(tbUserId, pageNum, limit);
        if (null == tbInfos) {
            return null;
        }

        for (int i = 0; i < tbInfos.size(); i++) {
            Map<String, Object> point = new HashMap<>();
            point.put("date_time", tbInfos.get(i).getDateTime());
            point.put("lon", tbInfos.get(i).getLon());
            point.put("lat", tbInfos.get(i).getLat());
            points.add(point);
        }
        return points;
    }

    @Override
    public List<Map<String, Object>> getLonAndLatByDateTime(String dateTime, int pageNum, int limit) {
        List<Map<String, Object>> points = new ArrayList<>();
        if (!checkDateTime(dateTime)) {
            //传入时间错误，返回默认结果
            return getAllLonAndLat(pageNum, limit);
        }
        List<TbInfo> tbInfos = tbInfoDao.getTbInfoByDateTime(dateTime, pageNum, limit);
        if (null == tbInfos) {
            return null;
        }
        for (int i = 0; i < tbInfos.size(); i++) {
            Map<String, Object> point = new HashMap<>();
            point.put("date_time", tbInfos.get(i).getDateTime());
            point.put("lon", tbInfos.get(i).getLon());
            point.put("lat", tbInfos.get(i).getLat());
            points.add(point);
        }
        return points;
    }

    @Override
    public List<Map<String, Object>> getLonAndLatByLac(String lac, int pageNum, int limit) {
        List<Map<String, Object>> points = new ArrayList<>();

        List<TbInfo> tbInfos = tbInfoDao.getTbInfoByLac(lac, pageNum, limit);
        if (null == tbInfos) {
            return null;
        }
        for (int i = 0; i < tbInfos.size(); i++) {
            Map<String, Object> point = new HashMap<>();
            point.put("date_time", tbInfos.get(i).getDateTime());
            point.put("lon", tbInfos.get(i).getLon());
            point.put("lat", tbInfos.get(i).getLat());
            points.add(point);
        }
        return points;
    }

    @Override
    public boolean checkDateTime(String dateTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            sdf.parse(dateTime);
            return true;
        } catch (ParseException pe) {
            return false;
        }
    }

    @Override
    public List<Map<String, Object>> getDealedLonAndLatByTbUser(String userCandidates,int pageNum,int limit) {
        List<Map<String, Object>> points = new ArrayList<>();
        List<TbInfo> tbInfos=tbInfoDao.getTbInfoByTbUser(userCandidates,pageNum,limit);
        for(TbInfo tbInfo :tbInfos){
            Map<String, Object> point = new HashMap<>();
            point.put("date_time", tbInfo.getDateTime());
            point.put("lon", tbInfo.getLon());
            point.put("lat", tbInfo.getLat());
            points.add(point);
        }
        return points;
    }
}
