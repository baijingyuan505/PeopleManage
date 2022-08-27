package teleDemo.dao;

import teleDemo.entities.TbUser;

import java.util.List;

public interface TbUserDao {
    List<TbUser> getAllTbUser(int pageNum, int limit);
    
    List<TbUser> getTbUserByPhoneNumber(String phoneNumber, int pageNum,int limit);
    
    List<TbUser> getTbUserByStatus(String status,int pageNum,int limit);

    List<TbUser> getTbUserByCandidates(int pageNum, int limit, TbUser candidates);

    int getTbUserSize();

    void updateTbUser(TbUser tbUserInfo);

    boolean deleteTbUser(int id);

    void insertTbUser(TbUser tbUserInfo);
}
