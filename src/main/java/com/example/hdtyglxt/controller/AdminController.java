package com.example.hdtyglxt.controller;

import com.example.hdtyglxt.base.dto.ResultDTO;
import com.example.hdtyglxt.base.util.ResultUtil;
import com.example.hdtyglxt.config.ErrorCodeMsg;
import com.example.hdtyglxt.entity.User;
import com.example.hdtyglxt.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/admin")
@Slf4j
@CrossOrigin
public class AdminController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    @ApiOperation("新增用户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userId", dataType = "Integer", required = false, value = "用户ID"),
            @ApiImplicitParam(paramType = "query", name = "username", dataType = "String", required = true, value = "用户名"),
            @ApiImplicitParam(paramType = "query", name = "password", dataType = "Integer", required = true, value = "密码"),
            @ApiImplicitParam(paramType = "query", name = "nickname", dataType = "String", required = true, value = "昵称"),
            @ApiImplicitParam(paramType = "query", name = "studentId", dataType = "String", required = true, value = "学号"),
            @ApiImplicitParam(paramType = "query", name = "role", dataType = "String", required = false, value = "角色")
    })
    public ResultDTO addUser(User user, HttpServletRequest request)throws Exception{
        log.info("接口：addUser");
        User loginUser = null;
        try {
            loginUser  = (User) request.getSession().getAttribute("user");
        } catch (Exception e) {
            log.info(ErrorCodeMsg.UNLOGIN.getMsg());
            return ResultUtil.Error(ErrorCodeMsg.UNLOGIN);
        }
        if(loginUser.getRole() == 0){
            return ResultUtil.Error(ErrorCodeMsg.AUTHORIT_OUT_OF_BOUND);
        }
        user.setRole(0);
        User findUser = new User();
        findUser.setUsername(user.getUsername());
        if(userService.findByParams(findUser).size()!= 0) {
            log.info("用户名已被注册");
            return ResultUtil.Error(ErrorCodeMsg.USERNAME_USED);
        }
        User user1 = new User();
        user1.setStudentId(user.getStudentId());
        if(userService.findByParams(findUser).size()!= 0) {
            log.info("此学号已注册");
            return ResultUtil.Error(ErrorCodeMsg.STUDENT_ID_USED);
        }
        User saveUser = userService.save(user);
        if(saveUser != null) {
            log.info("注册成功");
            return ResultUtil.Success(saveUser);
        }else{
            log.info("注册失败");
            return ResultUtil.Error(ErrorCodeMsg.REGISTER_FAILURE);
        }
    }

    @DeleteMapping("/deleteUser")
    public ResultDTO deleteUser(Integer userId,HttpServletRequest request)throws Exception{
        log.info("接口：deleteUser");
        User loginUser = null;
        try {
            loginUser  = (User) request.getSession().getAttribute("user");
        } catch (Exception e) {
            log.info(ErrorCodeMsg.UNLOGIN.getMsg());
            return ResultUtil.Error(ErrorCodeMsg.UNLOGIN);
        }
        if(loginUser.getRole() == 0){
            return ResultUtil.Error(ErrorCodeMsg.AUTHORIT_OUT_OF_BOUND);
        }
        userService.deleteById(userId);
        log.info("删除成功");
        return ResultUtil.Success();
    }

    @GetMapping("/getAllUser")
    public ResultDTO getAllUser(HttpServletRequest request)throws Exception{
        log.info("接口：getAllUser");
        User loginUser = null;
        try {
            loginUser  = (User) request.getSession().getAttribute("user");
        } catch (Exception e) {
            log.info(ErrorCodeMsg.UNLOGIN.getMsg());
            return ResultUtil.Error(ErrorCodeMsg.UNLOGIN);
        }
        if(loginUser.getRole() == 0){
            return ResultUtil.Error(ErrorCodeMsg.AUTHORIT_OUT_OF_BOUND);
        }
        User user = new User();
        user.setRole(0);
        return ResultUtil.Success(userService.findByParams(user));
    }

    @GetMapping("/getUserLimit")
    public ResultDTO getUserLimit(Integer pageNum,Integer pageSize,HttpServletRequest request)throws Exception{
        log.info("接口：getUserLimit");
        User loginUser = null;
        try {
            loginUser  = (User) request.getSession().getAttribute("user");
        } catch (Exception e) {
            log.info(ErrorCodeMsg.UNLOGIN.getMsg());
            return ResultUtil.Error(ErrorCodeMsg.UNLOGIN);
        }
        if(loginUser.getRole() == 0){
            return ResultUtil.Error(ErrorCodeMsg.AUTHORIT_OUT_OF_BOUND);
        }
        User user = new User();
        user.setRole(0);
        return ResultUtil.Success(userService.findPageData(user,pageNum,pageSize));
    }


    @PostMapping("/addAdminUser")
    public ResultDTO addAdminUser(User user, HttpServletRequest request)throws Exception{
        log.info("接口：addAdminUser");
        User loginUser = null;
        try {
            loginUser  = (User) request.getSession().getAttribute("user");
        } catch (Exception e) {
            log.info(ErrorCodeMsg.UNLOGIN.getMsg());
            return ResultUtil.Error(ErrorCodeMsg.UNLOGIN);
        }
        if(loginUser.getRole() != 2){
            return ResultUtil.Error(ErrorCodeMsg.AUTHORIT_OUT_OF_BOUND);
        }
        user.setRole(1);
        User findUser = new User();
        findUser.setUsername(user.getUsername());
        if(userService.findByParams(findUser).size()!= 0) {
            log.info("用户名已被注册");
            return ResultUtil.Error(ErrorCodeMsg.USERNAME_USED);
        }
        User saveUser = userService.save(user);
        if(saveUser != null) {
            log.info("管理员注册成功");
            return ResultUtil.Success(saveUser);
        }else{
            log.info("管理员注册失败");
            return ResultUtil.Error(ErrorCodeMsg.REGISTER_FAILURE);
        }
    }

    @DeleteMapping("/deleteAdminUser")
    public ResultDTO deleteAdminUser(Integer userId ,HttpServletRequest request)throws Exception{
        log.info("userId: " + userId);
        log.info("接口:deleteAdminUser");
        User loginUser = null;
        try {
            loginUser  = (User) request.getSession().getAttribute("user");
        } catch (Exception e) {
            log.info(ErrorCodeMsg.UNLOGIN.getMsg());
            return ResultUtil.Error(ErrorCodeMsg.UNLOGIN);
        }
        if(loginUser.getRole() != 2){
            return ResultUtil.Error(ErrorCodeMsg.AUTHORIT_OUT_OF_BOUND);
        }

        userService.deleteById(userId);
        log.info("删除管理员成功");
        return ResultUtil.Success();
    }

    @GetMapping("/getAllAdminUser")
    public ResultDTO getAllAdminUser(HttpServletRequest request)throws Exception{
        log.info("接口：getAllUser");
        User loginUser = null;
        try {
            loginUser  = (User) request.getSession().getAttribute("user");
        } catch (Exception e) {
            log.info(ErrorCodeMsg.UNLOGIN.getMsg());
            return ResultUtil.Error(ErrorCodeMsg.UNLOGIN);
        }
        if(loginUser.getRole() == 0){
            return ResultUtil.Error(ErrorCodeMsg.AUTHORIT_OUT_OF_BOUND);
        }
        User user = new User();
        user.setRole(1);
        return ResultUtil.Success(userService.findByParams(user));
    }

    @GetMapping("/getAdminUserLimit")
    public ResultDTO getAdminUserLimit(Integer pageNum,Integer pageSize,HttpServletRequest request)throws Exception{
        log.info("接口：getUserLimit");
        User loginUser = null;
        try {
            loginUser  = (User) request.getSession().getAttribute("user");
        } catch (Exception e) {
            log.info(ErrorCodeMsg.UNLOGIN.getMsg());
            return ResultUtil.Error(ErrorCodeMsg.UNLOGIN);
        }
        if(loginUser.getRole() == 0){
            return ResultUtil.Error(ErrorCodeMsg.AUTHORIT_OUT_OF_BOUND);
        }
        User user = new User();
        user.setRole(1);
        return ResultUtil.Success(userService.findPageData(user,pageNum,pageSize));
    }
}
