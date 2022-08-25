package teleDemo.service;


import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import teleDemo.entities.tbuser;

import java.util.List;

public interface userInfoService {

    List<tbuser> getAlltbUser();


    List<tbuser> getTbUserByPage(int pageNum, int limit);


    List<tbuser> getTbUserByPhoneNumber(String phoneNumber, int pageNum, int limit);

    List<tbuser> getTbUserByStatus(String status, int pageNum, int limit);

    List<tbuser> getTbUserByCandidates(int pageNum, int limit, tbuser candidates);

    int getTbUserSize();

    void updateTbUser(tbuser tbUserInfo);

    boolean deleteTbUser(int id);

    void insertTbUser(tbuser tbUserInfo);
}
