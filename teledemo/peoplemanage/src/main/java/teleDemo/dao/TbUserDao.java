package teleDemo.dao;

import teleDemo.entities.TbUser;

import java.util.List;

public interface TbUserDao {
    List<TbUser> getAllTbUser(int pageNum, int limit);

    List<TbUser> getTbUserByPhoneNumber(String phoneNumber, int pageNum, int limit);

    List<TbUser> getTbUserByStatus(String status, int pageNum, int limit);

    List<TbUser> getTbUserByCandidates(String candidates, int pageNum, int limit);

    int getTbUserSize();

    int getTbUserSizeByCandidates(String candidates);

    void updateTbUser(TbUser tbUserInfo);

    boolean deleteTbUser(int id);

    void insertTbUser(TbUser tbUserInfo);
}
