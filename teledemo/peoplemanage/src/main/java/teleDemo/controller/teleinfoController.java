package teleDemo.controller;

import com.alibaba.druid.support.json.JSONUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import teleDemo.entities.GetVo;
import teleDemo.entities.Result;
import teleDemo.entities.tbInfo;
import teleDemo.entities.tbuser;
import teleDemo.mapper.*;
import teleDemo.mapper.impl.userInfoMapperImpl;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class teleinfoController {
    @Resource
    comInfoMapper comInfoMapper;
    @Resource
    userInfoMapper userInfoMapper;

    @Resource
    userInfoMapperImpl userInfoImpl;

    @GetMapping("/v1/comInfo")
    public GetVo getAllTbInfo(HttpServletRequest request) {
        int limit = 10;
        int page = 1;
        if (request.getParameter("limit") != null) {
            System.out.println("limit不为空");
            limit = Integer.valueOf(request.getParameter("limit"));
        }
        if (request.getParameter("page") != null) {
            page = Integer.valueOf(request.getParameter("page"));
        }
        int size = comInfoMapper.getTbInfoSize();
        List<tbInfo> tbInfos = comInfoMapper.gettbINfoByPage((page - 1) * limit, limit);
        GetVo<tbInfo> getVo = new GetVo<>(0, "获取数据成功！", size, tbInfos);
        return getVo;
    }

    @GetMapping("/v1/lonAndLat")
    public Result getLonAndLat(HttpServletRequest request) {
        int limit = 100;
        int page = 1;
        if (request.getParameter("limit") != null) {
            System.out.println("limit不为空");
            limit = Integer.valueOf(request.getParameter("limit"));
        }
        if (request.getParameter("page") != null) {
            page = Integer.valueOf(request.getParameter("page"));
        }
        int size = userInfoMapper.getAlltbUser().size();
        List<tbInfo> tbInfos = comInfoMapper.gettbINfoByPage((page - 1) * limit, limit);

        if (null == tbInfos) {
            Result result = Result.createFailureResult("sql查询失败捏");
            return result;
        }
        List<Map<String, Double>> points = new ArrayList<Map<String, Double>>();
        for (int i = 0; i < tbInfos.size(); i++) {
            Map<String, Double> point = new HashMap<String, Double>();
            point.put("lon", tbInfos.get(i).getLon());
            point.put("lat", tbInfos.get(i).getLat());
            points.add(point);
        }
        Result result = Result.createSuccessResult(JSONUtils.toJSONString(points));

        return result;
    }



    @GetMapping("/v1/userInfo")
    public GetVo getAllTbUSer(HttpServletRequest request) {
        int limit = 100;
        int page = 1;
        if (request.getParameter("limit") != null) {
            System.out.println("limit不为空");
            limit = Integer.valueOf(request.getParameter("limit"));
        }
        if (request.getParameter("page") != null) {
            page = Integer.valueOf(request.getParameter("page"));
        }
        int size = userInfoMapper.getAlltbUser().size();
        List<tbuser> tbUsers = userInfoMapper.gettbUserByPage((page - 1) * limit, limit);
        GetVo<tbuser> getVo = new GetVo<>(0, "获取数据成功！", size, tbUsers);
        return getVo;
    }

    @PostMapping("/v1/userInfo/query")
    @ResponseBody
    public GetVo gettbUser(@Valid @RequestBody tbuser candidates, HttpServletRequest request) {
        int limit = 10;
        int page = 1;
        if (request.getParameter("limit") != null) {
            limit = Integer.valueOf(request.getParameter("limit"));
        }
        if (request.getParameter("page") != null) {
            page = Integer.valueOf(request.getParameter("page"));
        }

        int size = userInfoMapper.getAlltbUser().size();
        List<tbuser> tbUsers = userInfoImpl.gettbUserByQuery((page - 1) * limit, limit, candidates);
        GetVo<tbuser> getVo = new GetVo<>(0, "获取数据成功！", size, tbUsers);
        return getVo;
    }




}
