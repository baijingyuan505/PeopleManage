package teleDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import teleDemo.entities.*;
import teleDemo.service.*;

import java.util.List;
import java.util.Map;

@RestController
public class teleinfoController {

    @Autowired
    TbUserService tbUserService;

    @Autowired
    TbInfoService tbInfoService;

    /**
     * 接口说明: 按页获取所有的人员轨迹信息
     * 接口地址: /v1/getAllTbInfo
     * 请求方式: GET
     * 请求参数: limit, page (一页显示limit条，查看第page页)
     * 返回参数: Result
     */
    @GetMapping("/v1/getAllTbInfo")
    public Result getAllTbInfo(HttpServletRequest request) {
        int limit = 100;
        int page = 1;
        if (request.getParameter("limit") != null) {
            limit = Integer.valueOf(request.getParameter("limit"));
        }
        if (request.getParameter("page") != null) {
            page = Integer.valueOf(request.getParameter("page"));
        }

        List<TbInfo> tbInfos = tbInfoService.getAllTbInfo((page - 1) * limit, limit);
        int count = tbInfoService.getTbInfoSize();

        if (null == tbInfos) {
            return Result.createFailureResult("查询所有轨迹信息失败");
        }
        return Result.createSuccessResult(count, tbInfos);
    }


    /**
     * 查询特定的轨迹信息
     *
     * @param tbInfo
     * @param request
     * @return
     */
    @PostMapping("/v1/getTbInfo")
    @ResponseBody
    public Result getTbInfoById(@Valid @RequestBody TbInfo tbInfo, HttpServletRequest request) {
        int limit = 100;
        int page = 1;
        if (request.getParameter("limit") != null) {
            limit = Integer.valueOf(request.getParameter("limit"));
        }
        if (request.getParameter("page") != null) {
            page = Integer.valueOf(request.getParameter("page"));
        }

        List<TbInfo> tbInfos = tbInfoService.getTbInfo(tbInfo.toString(), (page - 1) * limit, limit);
        int count = tbInfoService.getTbInfoSizeByCandidates(tbInfo.toString());

        if (null == tbInfos) {
            return Result.createFailureResult("查询特定轨迹信息失败");
        }
        return Result.createSuccessResult(count, tbInfos);
    }

    /**
     * 按ID获取某一条轨迹信息
     *
     * @param tbInfo
     * @return
     */
    @PostMapping("/v1/getTbInfoById")
    @ResponseBody
    public Result getTbInfoById(@Valid @RequestBody TbInfo tbInfo) {
        TbInfo tbInfo1 = tbInfoService.getTbInfoById(tbInfo.getId());

        if (null == tbInfo1) {
            return Result.createFailureResult("获取指定id的经纬度信息失败");
        }
        return Result.createSuccessResult(1, tbInfo1);
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
        int limit = 100;
        int page = 1;
        if (request.getParameter("limit") != null) {
            limit = Integer.valueOf(request.getParameter("limit"));
        }
        if (request.getParameter("page") != null) {
            page = Integer.valueOf(request.getParameter("page"));
        }
        List<Map<String, Object>> points = tbInfoService.getAllLonAndLat((page - 1) * limit, limit);
        int count = tbInfoService.getTbInfoSize();

        if (null == points) {
            return Result.createFailureResult("分页获取所有经纬度信息失败");
        }
        return Result.createSuccessResult(count, points);
    }

    /**
     * 分页获取特定的经纬度信息
     *
     * @param tbInfo
     * @param request
     * @return
     */
    @PostMapping("/v1/getLonAndLat")
    @ResponseBody
    public Result getLonAndLat(@Valid @RequestBody TbInfo tbInfo, HttpServletRequest request) {
        int limit = 100;
        int page = 1;
        if (request.getParameter("limit") != null) {
            limit = Integer.valueOf(request.getParameter("limit"));
        }
        if (request.getParameter("page") != null) {
            page = Integer.valueOf(request.getParameter("page"));
        }

        List<Map<String, Object>> points = tbInfoService.getLonAndLat(tbInfo.toString(), (page - 1) * limit, limit);
        int count = tbInfoService.getTbInfoSizeByCandidates(tbInfo.toString());

        if (null == points) {
            return Result.createFailureResult("分页获取特定经纬度信息失败");
        }
        return Result.createSuccessResult(count, points);
    }

    /**
     * 接口说明: 按页获取指定id的人员轨迹信息,实质是按页查询所有轨迹信息后只返回经度和纬度
     * 接口地址: /v1/getLonAndLatById
     * 请求方式: POST
     * 请求参数: TbInfo类实例(确保id有效即可)
     * 返回参数: Result
     */
    @PostMapping("/v1/getLonAndLatById")
    @ResponseBody
    public Result getLonAndLatById(@Valid @RequestBody TbInfo tbInfo) {
        Map<String, Object> point = tbInfoService.getLonAndLatById(tbInfo.getId());
        if (null == point) {
            return Result.createFailureResult("获取指定id的经纬度信息失败");
        }
        return Result.createSuccessResult(1, point);
    }

    /**
     * POST
     *
     * @param tbInfo
     * @param request
     * @return
     */
    @PostMapping("/v1/getLonAndLatByTbUserId")
    @ResponseBody
    public Result getLonAndLatByTbUserId(@Valid @RequestBody TbInfo tbInfo, HttpServletRequest request) {
        int limit = 100;
        int page = 1;
        if (request.getParameter("limit") != null) {
            limit = Integer.valueOf(request.getParameter("limit"));
        }
        if (request.getParameter("page") != null) {
            page = Integer.valueOf(request.getParameter("page"));
        }
        List<Map<String, Object>> points;
        if (request.getParameter("deal") != null) {
            limit=-1;
            points = tbInfoService.getDealedLonAndLatByTbUserId(tbInfo.getUserId(), (page - 1) * limit, limit);
        }else{
            points = tbInfoService.getLonAndLatByTbUserId(tbInfo.getUserId(), (page - 1) * limit, limit);
        }
        int count = tbInfoService.getTbInfoSizeByCandidates(tbInfo.toString());
        if (null == points) {
            return Result.createFailureResult("分页获取指定用户id的经纬度信息失败");
        }
        return Result.createSuccessResult(count, points);
    }

    /**
     * 接口说明: 按页获取所有的人员轨迹信息,实质是按页查询所有轨迹信息后只返回经度和纬度
     * 接口地址: /v1/getLonAndLat
     * 请求方式: POST
     * 请求参数: limit, page (一页显示limit条，查看第page页)
     * 返回参数: Result
     */
    @PostMapping("/v1/getLonAndLatByDateTime")
    @ResponseBody
    public Result getLonAndLatByDateTime(@Valid @RequestBody TbInfo tbInfo, HttpServletRequest request) {
        int limit = 100;
        int page = 1;
        if (request.getParameter("limit") != null) {
            limit = Integer.valueOf(request.getParameter("limit"));
        }
        if (request.getParameter("page") != null) {
            page = Integer.valueOf(request.getParameter("page"));
        }
        List<Map<String, Object>> points = tbInfoService.getLonAndLatByDateTime(tbInfo.getDateTime(), (page - 1) * limit, limit);
        int count = tbInfoService.getTbInfoSizeByCandidates(tbInfo.toString());

        if (null == points) {
            return Result.createFailureResult("分页获取指定日期之前的经纬度信息失败");
        }
        return Result.createSuccessResult(count, points);
    }

    /**
     * 接口说明: 按页获取所有的人员轨迹信息,实质是按页查询所有轨迹信息后只返回经度和纬度
     * 接口地址: /v1/getLonAndLat
     * 请求方式: GET
     * 请求参数: limit, page (一页显示limit条，查看第page页)
     * 返回参数: Result
     */
    @PostMapping("/v1/getLonAndLatByLac")
    @ResponseBody
    public Result getLonAndLatByLac(@Valid @RequestBody TbInfo tbInfo, HttpServletRequest request) {
        int limit = 100;
        int page = 1;
        if (request.getParameter("limit") != null) {
            limit = Integer.valueOf(request.getParameter("limit"));
        }
        if (request.getParameter("page") != null) {
            page = Integer.valueOf(request.getParameter("page"));
        }
        List<Map<String, Object>> points = tbInfoService.getLonAndLatByLac(tbInfo.getLac(), (page - 1) * limit, limit);
        int count = tbInfoService.getTbInfoSizeByCandidates(tbInfo.toString());
        if (null == points) {
            return Result.createFailureResult("分页获取指定地区编码的经纬度信息失败");
        }
        return Result.createSuccessResult(count, points);
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
        int limit = 10;
        int page = 1;
        if (request.getParameter("limit") != null) {
            limit = Integer.valueOf(request.getParameter("limit"));
        }
        if (request.getParameter("page") != null) {
            page = Integer.valueOf(request.getParameter("page"));
        }


        List<TbUser> tbUsers = tbUserService.getAllTbUser((page - 1) * limit, limit);
        int count = tbUserService.getTbUserSize();

        if (null == tbUsers) {
            return Result.createFailureResult("查询所有用户信息失败");
        }
        return Result.createSuccessResult(count, tbUsers);
    }

    /**
     * 接口说明: 按页获取特定条件的人员信息，从一个TbUser类实例candidates中获取查询条件
     * 接口地址: /v1/getTbUser
     * 请求方式: POST
     * 请求参数: candidates(TbUser类实例) , limit, page (一页显示limit条，查看第page页)
     * 返回参数: Result
     */
    @PostMapping("/v1/getTbUser")
    @ResponseBody
    public Result getTbUser(@Valid @RequestBody TbUser tbUser, HttpServletRequest request) {
        int limit = 10;
        int page = 1;
        if (request.getParameter("limit") != null) {
            limit = Integer.valueOf(request.getParameter("limit"));
        }
        if (request.getParameter("page") != null) {
            page = Integer.valueOf(request.getParameter("page"));
        }


        List<TbUser> tbUsers = tbUserService.getTbUserByCandidates(tbUser.toString(), (page - 1) * limit, limit);
        int count = tbUserService.getTbUserSizeByCandidates(tbUser.toString());

        if (null == tbUsers) {
            return Result.createFailureResult("查询用户失败");
        }

        return Result.createSuccessResult(count, tbUsers);
    }

    /**
     * 接口说明: 按id更新单个用户信息，最后返回当前页的所有用户的查询结果
     * 接口地址: /v1/updateUserInfo
     * 请求方式: POST
     * 请求参数: userInfo(TbUser类实例), limit, page (一页显示limit条，查看第page页)
     * 返回参数: Result
     */
    @PostMapping("/v1/updateTbUser")
    @ResponseBody
    public Result updateTbUser(@Valid @RequestBody TbUser userInfo, HttpServletRequest request) {
        //更新操作
        tbUserService.updateTbUser(userInfo);
        return this.getAllTbUser(request);
    }

    /**
     * 接口说明: 按id删除单个用户信息，最后返回当前页的所有用户的查询结果
     * 接口地址: /v1/deleteUserInfo
     * 请求方式: POST
     * 请求参数: userInfo(TbUser类实例), limit, page (一页显示limit条，查看第page页)
     * 返回参数: Result
     */
    @PostMapping("/v1/deleteTbUser")
    @ResponseBody
    public Result deleteTbUser(@Valid @RequestBody TbUser userInfo, HttpServletRequest request) {
        tbUserService.deleteTbUser(userInfo.getId());
        return this.getAllTbUser(request);
    }

    /**
     * 接口说明: 增加单个用户信息，并返回当前页的所有用户信息的查询结果
     * 接口地址: /v1/getAllTbInfo
     * 请求方式: POST
     * 请求参数: userInfo(TbUser类实例), limit, page (一页显示limit条，查看第page页)
     * 返回参数: Result
     */
    @PostMapping("/v1/insertTbUser")
    @ResponseBody
    public Result insertTbUser(@Valid @RequestBody TbUser userInfo, HttpServletRequest request) {
        tbUserService.insertTbUser(userInfo);
        return this.getAllTbUser(request);
    }

    @PostMapping("/v1/getLonAndLatByTbUser")
    @ResponseBody
    public Result getLonAndLatByTbUser(@Valid @RequestBody TbUser tbUser,HttpServletRequest request) {
        int limit = 10;
        int page = 1;
        if (request.getParameter("limit") != null) {
            limit = Integer.valueOf(request.getParameter("limit"));
        }
        if (request.getParameter("page") != null) {
            page = Integer.valueOf(request.getParameter("page"));
        }
        System.out.println("In controller"+tbUser.toString());
        List<Map<String, Object>> points = tbInfoService.getDealedLonAndLatByTbUser(tbUser.toString(),(page - 1) * limit, limit);
        if (null == points) {
            return Result.createFailureResult("分页获取指定用户们的经纬度信息失败");
        }
        return Result.createSuccessResult(points.size(),points);
    }
}
