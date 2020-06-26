package com.example.hdtyglxt.controller;

import com.example.hdtyglxt.base.dto.ResultDTO;
import com.example.hdtyglxt.base.util.ResultUtil;
import com.example.hdtyglxt.config.ErrorCodeMsg;
import com.example.hdtyglxt.dto.UserDTO;
import com.example.hdtyglxt.entity.User;
import com.example.hdtyglxt.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    @ApiOperation("注册")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userId", dataType = "Integer", required = false, value = "用户ID"),
            @ApiImplicitParam(paramType = "query", name = "username", dataType = "String", required = true, value = "用户名"),
            @ApiImplicitParam(paramType = "query", name = "password", dataType = "Integer", required = true, value = "密码"),
            @ApiImplicitParam(paramType = "query", name = "nickname", dataType = "String", required = true, value = "昵称"),
            @ApiImplicitParam(paramType = "query", name = "studentId", dataType = "String", required = true, value = "学号"),
            @ApiImplicitParam(paramType = "query", name = "role", dataType = "String", required = false, value = "角色")
    })
    public ResultDTO register(User user)throws Exception{
        log.info("接口：register");
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

    @PostMapping("/login")
    public ResultDTO login(String username, String password, HttpServletRequest request, HttpServletResponse response)throws Exception{
        log.info("接口：login");
        User user = new User(username,password);
        List<User> users = userService.findByParams(user);
        if(users.size() == 0){

            log.info("登录失败");
            return ResultUtil.Error(ErrorCodeMsg.LOGIN_FAILURE);
        }else{
            User loginUser = users.get(0);
            request.getSession().setAttribute("user",loginUser);
            log.info("登录成功");
            return ResultUtil.Success();
        }
    }


    @PostMapping("/update")
    public ResultDTO update(String nickname,String studentId,HttpServletRequest request)throws Exception{
        log.info("接口：update");
        User user = null;
        try {
            user  = (User) request.getSession().getAttribute("user");
        } catch (Exception e) {
            log.info(ErrorCodeMsg.UNLOGIN.getMsg());
            return ResultUtil.Error(ErrorCodeMsg.UNLOGIN);
        }
        if(userService.get(user.getUserId()) == null){
            log.info("用户不存在");
            return ResultUtil.Error(ErrorCodeMsg.USER_NO_EXIST);
        }
        User newUser = new User(user.getUserId(),nickname,studentId);
        userService.update(newUser);
        log.info("修改用户信息成功");
        return ResultUtil.Success();
    }


    @PostMapping("/changePassword")
    public ResultDTO changePassword(String password,String changePassword,HttpServletRequest request)throws Exception{
        log.info("接口：changePassword");
        User user = null;
        try {
            user  = (User) request.getSession().getAttribute("user");
        } catch (Exception e) {
            log.info(ErrorCodeMsg.UNLOGIN.getMsg());
            return ResultUtil.Error(ErrorCodeMsg.UNLOGIN);
        }
        if(password.equals(user.getPassword())){
            User newUser = new User(user.getUserId(),changePassword);
            userService.update(newUser);
            log.info("修改密码成功");
            return ResultUtil.Success();
        }else{
            log.info("密码不正确");
            return ResultUtil.Error(ErrorCodeMsg.PASSWORD_NO_TRUE);
        }
    }

    @GetMapping("/getUserMsg")
    public ResultDTO getUserMsg(HttpServletRequest request)throws Exception{
        log.info("接口：getUserMsg");
        User user = null;
        try {
            user  = (User) request.getSession().getAttribute("user");
        } catch (Exception e) {
            log.info(ErrorCodeMsg.UNLOGIN.getMsg());
            return ResultUtil.Error(ErrorCodeMsg.UNLOGIN);
        }
        User getUser = userService.get(user.getUserId());
        log.info("获取用户信息成功");
        return ResultUtil.Success(new UserDTO(getUser));
    }

    @DeleteMapping("/logout")
    public ResultDTO logout(HttpServletRequest request)throws Exception{
        log.info("接口：logout");
        User user = null;
        try {
            user  = (User) request.getSession().getAttribute("user");
        } catch (Exception e) {
            log.info(ErrorCodeMsg.UNLOGIN.getMsg());
            return ResultUtil.Error(ErrorCodeMsg.UNLOGIN);
        }
        request.getSession().removeAttribute("user");
        return ResultUtil.Success();
    }

}
