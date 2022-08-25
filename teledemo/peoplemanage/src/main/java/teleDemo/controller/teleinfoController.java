package teleDemo.controller;

import com.google.gson.Gson;
import com.alibaba.druid.support.json.JSONUtils;
import net.sf.json.JSONArray;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import teleDemo.entities.*;
import teleDemo.mapper.personalTraceMapper;
import teleDemo.service.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class teleinfoController {

    @Resource
    userInfoService userInfoService;

    @Resource
    comInfoService comInfoService;
    /**
     * 接口说明: 按页获取所有的人员轨迹信息
     * 接口地址: /v1/getAllTbInfo
     * 请求方式: GET
     * 请求参数: limit, page (一页显示limit条，查看第page页)
     * 返回参数: Result
     */
    @GetMapping("/v1/getAllTbInfo")
    public Result getAllTbInfo(HttpServletRequest request) {
        int limit = 10;
        int page = 1;
        if (request.getParameter("limit") != null) {
            System.out.println("limit不为空");
            limit = Integer.valueOf(request.getParameter("limit"));
        }
        if (request.getParameter("page") != null) {
            page = Integer.valueOf(request.getParameter("page"));
        }
        List<tbInfo> tbInfos = comInfoService.getTbINfoByPage((page - 1) * limit, limit);

        if (null == tbInfos) {
            Result result = Result.createFailureResult("查询所有轨迹信息失败");
            return result;
        }

        Result result = Result.createSuccessResult(tbInfos);
        return result;
    }

    /**
     * 接口说明: 按页获取所有的人员轨迹信息,实质是按页查询所有轨迹信息后只返回经度和纬度，如不传入分页信息则获取所有
     * 接口地址: /v1/getAllLonAndLat
     * 请求方式: GET
     * 请求参数: limit, page (一页显示limit条，查看第page页)
     * 返回参数: Result
     */
    @GetMapping("/v1/getAllLonAndLat")
    public Result getAllLonAndLat(HttpServletRequest request) {
        Result result;
        int limit = 100;
        int page = 1;
        if (request.getParameter("limit") != null) {
            limit = Integer.valueOf(request.getParameter("limit"));
        }
        if (request.getParameter("page") != null) {
            page = Integer.valueOf(request.getParameter("page"));
        }
        List<Map<String,Object>> points = comInfoService.getAllLonAndLat((page - 1) * limit,limit);

        if(null == points){
            result = Result.createFailureResult("分页获取所有经纬度信息失败");
        }
        else {
            result = Result.createSuccessResult(points);
        }
        return result;
    }

    /**
     * 接口说明: 按页获取所有的人员轨迹信息,实质是按页查询所有轨迹信息后只返回经度和纬度
     * 接口地址: /v1/getLonAndLatById
     * 请求方式: POST
     * 请求参数: tbInfo类实例(确保id有效即可), limit, page (一页显示limit条，查看第page页)
     * 返回参数: Result
     */
    @PostMapping("/v1/getLonAndLatById")
    @ResponseBody
    public Result getLonAndLatById(@Valid @RequestBody tbInfo tbInfo,HttpServletRequest request) {
        Result result;
        int limit = 100;
        int page = 1;
        if (request.getParameter("limit") != null) {
            limit = Integer.valueOf(request.getParameter("limit"));
        }
        if (request.getParameter("page") != null) {
            page = Integer.valueOf(request.getParameter("page"));
        }
        Map<String,Object> point = comInfoService.getLonAndLatById(tbInfo.getId());

        if(null == point){
            result = Result.createFailureResult("获取指定id的经纬度信息失败");
        }
        else {
            result = Result.createSuccessResult(point);
        }
        return result;
    }

    /**
     *
     * POST
     * @param tbInfo
     * @param request
     * @return
     */
    @PostMapping("/v1/getLonAndLatByTbUserId")
    @ResponseBody
    public Result getLonAndLatByTbUserId(@Valid @RequestBody tbInfo tbInfo,HttpServletRequest request) {
        Result result;
        int limit = 100;
        int page = 1;
        if (request.getParameter("limit") != null) {
            limit = Integer.valueOf(request.getParameter("limit"));
        }
        if (request.getParameter("page") != null) {
            page = Integer.valueOf(request.getParameter("page"));
        }
        List<Map<String,Object>> points = comInfoService.getLonAndLatByTbUserId(tbInfo.getUserId(),(page - 1) * limit,limit);

        if(null == points){
            result = Result.createFailureResult("分页获取指定用户id的经纬度信息失败");
        }
        else {
            result = Result.createSuccessResult(points);
        }
        return result;
    }
    /**
     * 接口说明: 按页获取所有的人员轨迹信息,实质是按页查询所有轨迹信息后只返回经度和纬度
     * 接口地址: /v1/getLonAndLat
     * 请求方式: POST
     * 请求参数: llimit, page (一页显示limit条，查看第page页)
     * 返回参数: Result
     */
    @PostMapping("/v1/getLonAndLatByDateTime")
    @ResponseBody
    public Result getLonAndLatByDateTime(@Valid @RequestBody tbInfo tbInfo,HttpServletRequest request) {
        Result result;
        int limit = 100;
        int page = 1;
        if (request.getParameter("limit") != null) {
            System.out.println("limit不为空");
            limit = Integer.valueOf(request.getParameter("limit"));
        }
        if (request.getParameter("page") != null) {
            page = Integer.valueOf(request.getParameter("page"));
        }
        List<Map<String,Object>> points = comInfoService.getLonAndLatByDateTime(tbInfo.getDateTime().toString(),(page - 1) * limit,limit);

        if(null == points){
            result = Result.createFailureResult("分页获取指定日期之前的经纬度信息失败");
        }
        else {
            result = Result.createSuccessResult(points);
        }
        return result;
    }

    /**
     * 接口说明: 按页获取所有的人员轨迹信息,实质是按页查询所有轨迹信息后只返回经度和纬度
     * 接口地址: /v1/getLonAndLat
     * 请求方式: GET
     * 请求参数: llimit, page (一页显示limit条，查看第page页)
     * 返回参数: Result
     */
    @PostMapping("/v1/getLonAndLatById")
    @ResponseBody
    public Result getLonAndLatByLac(@Valid @RequestBody tbInfo tbInfo,HttpServletRequest request) {
        Result result;
        int limit = 100;
        int page = 1;
        if (request.getParameter("limit") != null) {
            System.out.println("limit不为空");
            limit = Integer.valueOf(request.getParameter("limit"));
        }
        if (request.getParameter("page") != null) {
            page = Integer.valueOf(request.getParameter("page"));
        }
        List<Map<String,Object>> points = comInfoService.getLonAndLatByLac(tbInfo.getLac(),(page - 1) * limit,limit);

        if(null == points){
            result = Result.createFailureResult("分页获取指定地区编码的经纬度信息失败");
        }
        else {
            result = Result.createSuccessResult(points);
        }
        return result;
    }
    /**
     * 接口说明: 按页获取所有的人员信息
     * 接口地址: /v1/getAllTbUser
     * 请求方式: GET
     * 请求参数: limit, page (一页显示limit条，查看第page页)
     * 返回参数: Result
     */
    @GetMapping("/v1/getAllTbUser")
    public Result getAllTbUser(HttpServletRequest request) {
        int limit = 100;
        int page = 1;
        if (request.getParameter("limit") != null) {
            System.out.println("limit不为空");
            limit = Integer.valueOf(request.getParameter("limit"));
        }
        if (request.getParameter("page") != null) {
            page = Integer.valueOf(request.getParameter("page"));
        }
        int size = userInfoService.getTbUserSize();
        List<tbuser> tbUsers = userInfoService.getTbUserByPage((page - 1) * limit, limit);


        if (null == tbUsers) {
            Result result = Result.createFailureResult("查询所有用户信息失败");
            return result;
        }

        Result result = Result.createSuccessResult(tbUsers);
        return result;
    }

    /**
     * 接口说明: 按页获取特定条件的人员信息，从一个tbuser类实例candidates中获取查询条件
     * 接口地址: /v1/getTbUser
     * 请求方式: POST
     * 请求参数: candidates(tbuser类实例) , limit, page (一页显示limit条，查看第page页)
     * 返回参数: Result
     */
    @PostMapping("/v1/getTbUser")
    @ResponseBody
    public Result getTbUser(@Valid @RequestBody tbuser candidates, HttpServletRequest request) {
        int limit = 10;
        int page = 1;
        if (request.getParameter("limit") != null) {
            limit = Integer.valueOf(request.getParameter("limit"));
        }
        if (request.getParameter("page") != null) {
            page = Integer.valueOf(request.getParameter("page"));
        }

        int size = userInfoService.getTbUserSize();
        List<tbuser> tbUsers = userInfoService.getTbUserByCandidates((page - 1) * limit, limit, candidates);
        if (null == tbUsers) {
            Result result = Result.createFailureResult("所有用户查询失败");
            return result;
        }

        Result result = Result.createSuccessResult(tbUsers);
        return result;
    }

    /**
     * 接口说明: 按id更新单个用户信息，最后返回当前页的所有用户的查询结果
     * 接口地址: /v1/updateUserInfo
     * 请求方式: POST
     * 请求参数: userInfo(tbuser类实例), limit, page (一页显示limit条，查看第page页)
     * 返回参数: Result
     */
    @PostMapping("/v1/updateUserInfo")
    @ResponseBody
    public Result updateTbUser(@Valid @RequestBody tbuser userInfo, HttpServletRequest request) {
        int limit = 10;
        int page = 1;
        if (request.getParameter("limit") != null) {
            limit = Integer.valueOf(request.getParameter("limit"));
        }
        if (request.getParameter("page") != null) {
            page = Integer.valueOf(request.getParameter("page"));
        }
        int size = userInfoService.getAlltbUser().size();

        //更新操作
        userInfoService.updateTbUser(userInfo);


        //返回当前查询页的数据
        List<tbuser> tbUsers = userInfoService.getTbUserByPage((page - 1) * limit, limit);
        if (null == tbUsers) {
            Result result = Result.createFailureResult("所有用户查询失败");
            return result;
        }

        Result result = Result.createSuccessResult(tbUsers);
        return result;
    }

    /**
     * 接口说明: 按id删除单个用户信息，最后返回当前页的所有用户的查询结果
     * 接口地址: /v1/deleteUserInfo
     * 请求方式: POST
     * 请求参数: userInfo(tbuser类实例), limit, page (一页显示limit条，查看第page页)
     * 返回参数: Result
     */
    @PostMapping("/v1/deleteUserInfo")
    @ResponseBody
    public Result deleteTbUser(@Valid @RequestBody tbuser userInfo, HttpServletRequest request) {
        int limit = 10;
        int page = 1;
        if (request.getParameter("limit") != null) {
            limit = Integer.valueOf(request.getParameter("limit"));
        }
        if (request.getParameter("page") != null) {
            page = Integer.valueOf(request.getParameter("page"));
        }

        userInfoService.deleteTbUser(userInfo.getId());

        List<tbuser> tbUsers = userInfoService.getTbUserByPage((page - 1) * limit, limit);
        if (null == tbUsers) {
            Result result = Result.createFailureResult("所有用户查询失败");
            return result;
        }

        Result result = Result.createSuccessResult(tbUsers);
        return result;
    }

    /**
     * 接口说明: 增加单个用户信息，并返回当前页的所有用户信息的查询结果
     * 接口地址: /v1/getAllTbInfo
     * 请求方式: POST
     * 请求参数: userInfo(tbuser类实例), limit, page (一页显示limit条，查看第page页)
     * 返回参数: Result
     */
    @PostMapping("/v1/insertUserInfo")
    @ResponseBody
    public Result insertTbUser(@Valid @RequestBody tbuser userInfo, HttpServletRequest request) {
        int limit = 10;
        int page = 1;
        if (request.getParameter("limit") != null) {
            limit = Integer.valueOf(request.getParameter("limit"));
        }
        if (request.getParameter("page") != null) {
            page = Integer.valueOf(request.getParameter("page"));
        }

        userInfoService.insertTbUser(userInfo);

        List<tbuser> tbUsers = userInfoService.getTbUserByPage((page - 1) * limit, limit);
        if (null == tbUsers) {
            Result result = Result.createFailureResult("所有用户查询失败");
            return result;
        }
        Result result = Result.createSuccessResult(tbUsers);
        return result;
    }

    @Resource
    teleDemo.mapper.personalTraceMapper personalTraceMapper;
    @PostMapping("/v1/personTrace")
    public void getPersonalTrace(HttpServletRequest request, HttpServletResponse response){
        System.out.println("nihao");
        int id=Integer.valueOf(request.getParameter("id"));
        System.out.println(id);
        List<Trace> trace = personalTraceMapper.getPersonalTrace(id);
        List<Trace> dealedTrace=new ArrayList<>();
        double lat=0;
        double lon=0;
        for(Trace i : trace){
            if(i.getLat()!=lat&&i.getLon()!=lon){
                lat=i.getLat();
                lon=i.getLon();
                System.out.println(lat+" "+lon);
                dealedTrace.add(i);
            }
        }
        System.out.println("呵呵呵呵");
        PrintWriter writer=null;
        try{
            writer=response.getWriter();
        }catch (IOException e){
            e.printStackTrace();
        }
        JSONArray data=JSONArray.fromObject(dealedTrace);
        response.setCharacterEncoding("utf-8");
        writer.append(data.toString());

    }
}
