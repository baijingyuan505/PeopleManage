package teleDemo.controller;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import teleDemo.entities.GetVo;
import teleDemo.entities.tbInfo;
import teleDemo.entities.tbuser;
import teleDemo.mapper.*;
import teleDemo.mapper.impl.userInfoMapperImpl;

import java.util.List;

@RestController
public class teleinfoController {
    @Resource
    comInfoMapper comInfoMapper;
    @Resource
    userInfoMapper userInfoMapper;

    @Resource
    userInfoMapperImpl userInfoImpl;

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
        int size = comInfoMapper.getTbInfoSize();
        List<tbInfo> tbInfos = comInfoMapper.gettbINfoByPage((page-1)*limit,limit);
        GetVo<tbInfo> getVo = new GetVo<>(0,"获取数据成功！",size,tbInfos);
        return  getVo;
    }

    @GetMapping("/v1/userInfo")
    public GetVo getAlltbUSer(HttpServletRequest request){
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
        List<tbuser> tbUsers = userInfoMapper.gettbUserByPage((page-1)*limit,limit);
        GetVo<tbuser> getVo = new GetVo<>(0,"获取数据成功！",size,tbUsers);
        return  getVo;
    }

    @PostMapping("/v1/userInfo/query")
    @ResponseBody
    public GetVo gettbUser(@Valid @RequestBody tbuser query, HttpServletRequest request)
    {
        int limit = 10;
        int page = 1;
        if (request.getParameter("limit") != null){
            limit = Integer.valueOf(request.getParameter("limit"));
        }
        if(request.getParameter("page") != null)
        {
            page = Integer.valueOf(request.getParameter("page"));
        }

        int size = userInfoMapper.getAlltbUser().size();
        List<tbuser> tbUsers = userInfoImpl.gettbUserByQuery((page-1)*limit,limit,query);
        GetVo<tbuser> getVo = new GetVo<>(0,"获取数据成功！",size,tbUsers);
        return  getVo;
    }

}
