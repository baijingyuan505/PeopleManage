package teleDemo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import teleDemo.entities.Result;
import teleDemo.entities.tbInfo;
import teleDemo.mapper.comInfoMapper;
import teleDemo.service.comInfoService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class comInfoServiceImpl implements comInfoService {
    @Autowired
    comInfoMapper comInfoMapper;
    @Override
    public List<tbInfo> getAlltbINfo() {
        return comInfoMapper.getAlltbINfo();
    }

    @Override
    public List<tbInfo> getTbINfoByPage(int pageNum, int limit) {
        return comInfoMapper.getTbINfoByPage(pageNum,limit);
    }

    @Override
    public int getTbInfoSize() {
        return comInfoMapper.getTbInfoSize();
    }

    @Override
    public tbInfo getTbInfoById(int id) {
        return comInfoMapper.getTbInfoById(id);
    }

    @Override
    public List<tbInfo> getTbInfoByTbUserId(int userId, int pageNum, int limit) {
        return comInfoMapper.getTbInfoByTbUserId(userId,pageNum,limit);
    }

    @Override
    public List<tbInfo> getTbInfoByDateTime(String dateTime, int pageNum, int limit) {
        return comInfoMapper.getTbInfoByDateTime(dateTime,pageNum,limit);
    }

    @Override
    public List<tbInfo> getTbInfoByLac(String lac, int pageNum, int limit) {
        return comInfoMapper.getTbInfoByLac(lac,pageNum,limit);
    }

    @Override
    public List<Map<String, Object>> getAllLonAndLat(int pageNum,int limit) {
        List<Map<String, Object>> points = new ArrayList<>();

        List<tbInfo> tbInfos = comInfoMapper.getTbINfoByPage(pageNum, limit);
        if (null == tbInfos) {
            return null;
        }
        Map<String, Object> point = new HashMap<>();
        for (int i = 0; i < tbInfos.size(); i++) {
            point.put("date_time",tbInfos.get(i).getDateTime());
            point.put("lon", tbInfos.get(i).getLon());
            point.put("lat", tbInfos.get(i).getLat());
            points.add(point);
        }
        return points;
    }

    @Override
    public Map<String, Object> getLonAndLatById(int id) {
        Map<String,Object> point = new HashMap<>();

        tbInfo tbInfo = comInfoMapper.getTbInfoById(id);
        if(null == tbInfo)
        {
            return null;
        }

        point.put("date_time",tbInfo.getDateTime());
        point.put("lon", tbInfo.getLon());
        point.put("lat", tbInfo.getLat());
        return point;
    }

    @Override
    public List<Map<String, Object>> getLonAndLatByTbUserId(int tbUserId, int pageNum, int limit) {
        List<Map<String, Object>> points = new ArrayList<>();

        List<tbInfo> tbInfos = comInfoMapper.getTbInfoByTbUserId(tbUserId, pageNum, limit);
        if (null == tbInfos) {
            return null;
        }
        Map<String, Object> point = new HashMap<>();
        for (int i = 0; i < tbInfos.size(); i++) {
            point.put("date_time",tbInfos.get(i).getDateTime());
            point.put("lon", tbInfos.get(i).getLon());
            point.put("lat", tbInfos.get(i).getLat());
            points.add(point);
        }
        return points;
    }

    @Override
    public List<Map<String, Object>> getLonAndLatByDateTime(String dateTime, int pageNum, int limit) {
        List<Map<String, Object>> points = new ArrayList<>();

        List<tbInfo> tbInfos = comInfoMapper.getTbInfoByDateTime(dateTime, pageNum, limit);
        if (null == tbInfos) {
            return null;
        }
        Map<String, Object> point = new HashMap<>();
        for (int i = 0; i < tbInfos.size(); i++) {
            point.put("date_time",tbInfos.get(i).getDateTime());
            point.put("lon", tbInfos.get(i).getLon());
            point.put("lat", tbInfos.get(i).getLat());
            points.add(point);
        }
        return points;
    }

    @Override
    public List<Map<String, Object>> getLonAndLatByLac(String lac, int pageNum, int limit) {
        List<Map<String, Object>> points = new ArrayList<>();

        List<tbInfo> tbInfos = comInfoMapper.getTbInfoByLac(lac, pageNum, limit);
        if (null == tbInfos) {
            return null;
        }
        Map<String, Object> point = new HashMap<>();
        for (int i = 0; i < tbInfos.size(); i++) {
            point.put("date_time",tbInfos.get(i).getDateTime());
            point.put("lon", tbInfos.get(i).getLon());
            point.put("lat", tbInfos.get(i).getLat());
            points.add(point);
        }
        return points;
    }
}
