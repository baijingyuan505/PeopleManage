package teleDemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import teleDemo.entities.GetVo;
import teleDemo.entities.tbInfo;
import teleDemo.entities.tbuser;
import teleDemo.mapper.*;

import java.util.List;

@RestController
public class teleinfoController {
    @Resource
    comInfoMapper comInfoMapper;
    @Resource
    userInfoMapper userInfoMapper;

    @GetMapping("/v1/comInfo")
    public GetVo gettbInfo(HttpServletRequest request){
        int limit = 10;
        int page = 1;
        if (request.getParameter("limit") != null){
            System.out.println("limit不为空");
            limit = Integer.valueOf(request.getParameter("limit"));
        }
        if(request.getParameter("page") != null)
        {
            page = Integer.valueOf(request.getParameter("page"));
        }
        int size = comInfoMapper.getAlltbINfo().size();
        List<tbInfo> tbInfos = comInfoMapper.gettbINfoByPage((page-1)*limit,limit);
        GetVo<tbInfo> getVo = new GetVo<>(0,"获取数据成功！",size,tbInfos);
        return  getVo;
    }

    @GetMapping("/v1/userInfo")
    public GetVo gettbUSer(HttpServletRequest request){
        int limit = 10;
        int page = 1;
        if (request.getParameter("limit") != null){
            System.out.println("limit不为空");
            limit = Integer.valueOf(request.getParameter("limit"));
        }
        if(request.getParameter("page") != null)
        {
            page = Integer.valueOf(request.getParameter("page"));
        }
        int size = userInfoMapper.getAlltbUser().size();
        List<tbuser> tbInfos = userInfoMapper.gettbUserByPage((page-1)*limit,limit);
        GetVo<tbuser> getVo = new GetVo<>(0,"获取数据成功！",size,tbInfos);
        return  getVo;
    }
}
