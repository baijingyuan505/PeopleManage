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
    TbInfoService TbInfoService;

    /**
     * 接口说明: 按页获取所有的人员轨迹信息
     * 接口地址: /v1/getAllTbInfo
     * 请求方式: GET
     * 请求参数: limit, page (一页显示limit条，查看第page页)
     * 返回参数: Result
     */
    @GetMapping("/v1/getAllTbInfo")
    public Result getAllTbInfo(HttpServletRequest request) {
        Result result;
        int limit = 10;
        int page = 1;
        if (request.getParameter("limit") != null) {
            System.out.println("limit不为空");
            limit = Integer.valueOf(request.getParameter("limit"));
        }
        if (request.getParameter("page") != null) {
            page = Integer.valueOf(request.getParameter("page"));
        }
        List<TbInfo> TbInfos = TbInfoService.getAllTbInfo((page - 1) * limit, limit);

        if (null == TbInfos) {
            result = Result.createFailureResult("查询所有轨迹信息失败");
            return result;
        }

        result = Result.createSuccessResult(TbInfos);
        return result;
    }

    /**
     * 按ID获取某一条轨迹信息
     *
     * @param TbInfo
     * @param request
     * @return
     */
    @PostMapping("/v1/getTbInfoById")
    @ResponseBody
    public Result getTbInfoById(@Valid @RequestBody TbInfo TbInfo, HttpServletRequest request) {
        Result result;
        int limit = 100;
        int page = 1;
        if (request.getParameter("limit") != null) {
            limit = Integer.valueOf(request.getParameter("limit"));
        }
        if (request.getParameter("page") != null) {
            page = Integer.valueOf(request.getParameter("page"));
        }
        TbInfo TbInfo1 = TbInfoService.getTbInfoById(TbInfo.getId());

        if (null == TbInfo1) {
            result = Result.createFailureResult("获取指定id的经纬度信息失败");
        } else {
            result = Result.createSuccessResult(TbInfo1);
        }
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
        List<Map<String, Object>> points = TbInfoService.getAllLonAndLat((page - 1) * limit, limit);

        if (null == points) {
            result = Result.createFailureResult("分页获取所有经纬度信息失败");
        } else {
            result = Result.createSuccessResult(points);
        }
        return result;
    }

    /**
     * 接口说明: 按页获取所有的人员轨迹信息,实质是按页查询所有轨迹信息后只返回经度和纬度
     * 接口地址: /v1/getLonAndLatById
     * 请求方式: POST
     * 请求参数: TbInfo类实例(确保id有效即可), limit, page (一页显示limit条，查看第page页)
     * 返回参数: Result
     */
    @PostMapping("/v1/getLonAndLatById")
    @ResponseBody
    public Result getLonAndLatById(@Valid @RequestBody TbInfo TbInfo, HttpServletRequest request) {
        Result result;
        int limit = 100;
        int page = 1;
        if (request.getParameter("limit") != null) {
            limit = Integer.valueOf(request.getParameter("limit"));
        }
        if (request.getParameter("page") != null) {
            page = Integer.valueOf(request.getParameter("page"));
        }
        Map<String, Object> point = TbInfoService.getLonAndLatById(TbInfo.getId());

        if (null == point) {
            result = Result.createFailureResult("获取指定id的经纬度信息失败");
        } else {
            result = Result.createSuccessResult(point);
        }
        return result;
    }

    /**
     * POST
     *
     * @param TbInfo
     * @param request
     * @return
     */
    @PostMapping("/v1/getLonAndLatByTbUserId")
    @ResponseBody
    public Result getLonAndLatByTbUserId(@Valid @RequestBody TbInfo TbInfo, HttpServletRequest request) {
        Result result;
        int limit = 100;
        int page = 1;
        if (request.getParameter("limit") != null) {
            limit = Integer.valueOf(request.getParameter("limit"));
        }
        if (request.getParameter("page") != null) {
            page = Integer.valueOf(request.getParameter("page"));
        }
        List<Map<String, Object>> points = TbInfoService.getLonAndLatByTbUserId(TbInfo.getUserId(), (page - 1) * limit, limit);

        if (null == points) {
            result = Result.createFailureResult("分页获取指定用户id的经纬度信息失败");
        } else {
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
    public Result getLonAndLatByDateTime(@Valid @RequestBody TbInfo TbInfo, HttpServletRequest request) {
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
        List<Map<String, Object>> points = TbInfoService.getLonAndLatByDateTime(TbInfo.getDateTime().toString(), (page - 1) * limit, limit);

        if (null == points) {
            result = Result.createFailureResult("分页获取指定日期之前的经纬度信息失败");
        } else {
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
    @PostMapping("/v1/getLonAndLatByLac")
    @ResponseBody
    public Result getLonAndLatByLac(@Valid @RequestBody TbInfo TbInfo, HttpServletRequest request) {
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
        List<Map<String, Object>> points = TbInfoService.getLonAndLatByLac(TbInfo.getLac(), (page - 1) * limit, limit);

        if (null == points) {
            result = Result.createFailureResult("分页获取指定地区编码的经纬度信息失败");
        } else {
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
        int limit = 10;
        int page = 1;
        if (request.getParameter("limit") != null) {
            System.out.println("limit不为空");
            limit = Integer.valueOf(request.getParameter("limit"));
        }
        if (request.getParameter("page") != null) {
            page = Integer.valueOf(request.getParameter("page"));
        }


        List<TbUser> tbUsers = tbUserService.getAllTbUser((page - 1) * limit, limit);


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
    public Result getTbUser(@Valid @RequestBody TbUser candidates, HttpServletRequest request) {
        int limit = 10;
        int page = 1;
        if (request.getParameter("limit") != null) {
            limit = Integer.valueOf(request.getParameter("limit"));
        }
        if (request.getParameter("page") != null) {
            page = Integer.valueOf(request.getParameter("page"));
        }

        int size = tbUserService.getTbUserSize();


        List<TbUser> tbUsers = tbUserService.getTbUserByCandidates((page - 1) * limit, limit, candidates);


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
    @PostMapping("/v1/updateTbUser")
    @ResponseBody
    public Result updateTbUser(@Valid @RequestBody TbUser userInfo, HttpServletRequest request) {
        int limit = 10;
        int page = 1;
        if (request.getParameter("limit") != null) {
            limit = Integer.valueOf(request.getParameter("limit"));
        }
        if (request.getParameter("page") != null) {
            page = Integer.valueOf(request.getParameter("page"));
        }

        //更新操作
        tbUserService.updateTbUser(userInfo);


        return this.getAllTbUser(request);
    }

    /**
     * 接口说明: 按id删除单个用户信息，最后返回当前页的所有用户的查询结果
     * 接口地址: /v1/deleteUserInfo
     * 请求方式: POST
     * 请求参数: userInfo(tbuser类实例), limit, page (一页显示limit条，查看第page页)
     * 返回参数: Result
     */
    @PostMapping("/v1/deleteTbUser")
    @ResponseBody
    public Result deleteTbUser(@Valid @RequestBody TbUser userInfo, HttpServletRequest request) {
        int limit = 10;
        int page = 1;
        if (request.getParameter("limit") != null) {
            limit = Integer.valueOf(request.getParameter("limit"));
        }
        if (request.getParameter("page") != null) {
            page = Integer.valueOf(request.getParameter("page"));
        }

        tbUserService.deleteTbUser(userInfo.getId());

        return this.getAllTbUser(request);
    }

    /**
     * 接口说明: 增加单个用户信息，并返回当前页的所有用户信息的查询结果
     * 接口地址: /v1/getAllTbInfo
     * 请求方式: POST
     * 请求参数: userInfo(tbuser类实例), limit, page (一页显示limit条，查看第page页)
     * 返回参数: Result
     */
    @PostMapping("/v1/insertTbUser")
    @ResponseBody
    public Result insertTbUser(@Valid @RequestBody TbUser userInfo, HttpServletRequest request) {
        int limit = 10;
        int page = 1;
        if (request.getParameter("limit") != null) {
            limit = Integer.valueOf(request.getParameter("limit"));
        }
        if (request.getParameter("page") != null) {
            page = Integer.valueOf(request.getParameter("page"));
        }

        tbUserService.insertTbUser(userInfo);

        return this.getAllTbUser(request);
    }
}
