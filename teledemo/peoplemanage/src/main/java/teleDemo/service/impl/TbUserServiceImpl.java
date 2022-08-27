package teleDemo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import teleDemo.dao.TbUserDao;
import teleDemo.entities.TbUser;
import teleDemo.service.TbUserService;

import java.util.List;

@Service
public class TbUserServiceImpl implements TbUserService {
    @Autowired
    TbUserDao tbUserDao;
    @Override
    public List<TbUser> getAllTbUser(int pageNum, int limit) {
        return tbUserDao.getAllTbUser(pageNum, limit);
    }


    @Override
    public List<TbUser> getTbUserByPhoneNumber(String phoneNumber, int pageNum, int limit) {
        return tbUserDao.getTbUserByPhoneNumber(phoneNumber,pageNum,limit);
    }

    @Override
    public List<TbUser> getTbUserByStatus(String status, int pageNum, int limit) {
        return tbUserDao.getTbUserByStatus(status,pageNum,limit);
    }

    @Override
    public List<TbUser> getTbUserByCandidates(int pageNum, int limit, TbUser candidates) {
        return tbUserDao.getTbUserByCandidates(pageNum,limit,candidates);
    }

    @Override
    public int getTbUserSize() {
        return tbUserDao.getTbUserSize();
    }

    @Override
    public void updateTbUser(TbUser tbUserInfo) {
        tbUserDao.updateTbUser(tbUserInfo);
    }

    @Override
    public boolean deleteTbUser(int id) {
        return tbUserDao.deleteTbUser(id);
    }

    @Override
    public void insertTbUser(TbUser tbUserInfo) {
        tbUserDao.insertTbUser(tbUserInfo);
    }
}
