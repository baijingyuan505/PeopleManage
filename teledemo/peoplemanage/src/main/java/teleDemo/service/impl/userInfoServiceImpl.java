package teleDemo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import teleDemo.entities.tbuser;
import teleDemo.service.userInfoService;
import teleDemo.mapper.*;

import java.util.List;

public class userInfoServiceImpl implements userInfoService {
    @Autowired
    userInfoMapper userInfoMapper;
    @Override
    public List<tbuser> getAlltbUser() {
        return userInfoMapper.getAlltbUser();
    }

    @Override
    public List<tbuser> getTbUserByPage(int pageNum, int limit) {
        return userInfoMapper.getTbUserByPage(pageNum,limit);
    }

    @Override
    public List<tbuser> getTbUserByPhoneNumber(String phoneNumber, int pageNum, int limit) {
        return userInfoMapper.getTbUserByPhoneNumber(phoneNumber,pageNum,limit);
    }

    @Override
    public List<tbuser> getTbUserByStatus(String status, int pageNum, int limit) {
        return userInfoMapper.getTbUserByStatus(status,pageNum,limit);
    }

    @Override
    public List<tbuser> getTbUserByCandidates(int pageNum, int limit, tbuser candidates) {
        return userInfoMapper.getTbUserByCandidates(pageNum,limit,candidates);
    }

    @Override
    public int getTbUserSize() {
        return userInfoMapper.getTbUserSize();
    }

    @Override
    public void updateTbUser(tbuser tbUserInfo) {
        userInfoMapper.updateTbUser(tbUserInfo);
    }

    @Override
    public boolean deleteTbUser(int id) {
        return userInfoMapper.deleteTbUser(id);
    }

    @Override
    public void insertTbUser(tbuser tbUserInfo) {
        userInfoMapper.insertTbUser(tbUserInfo);
    }
}
