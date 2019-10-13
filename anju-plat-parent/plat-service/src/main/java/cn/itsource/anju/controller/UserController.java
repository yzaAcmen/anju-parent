package cn.itsource.anju.controller;

import cn.itsource.anju.User;
import cn.itsource.anju.util.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "登录的Controller")
public class UserController {

    /**
     * 登录接口
     * @return
     */
    @ApiOperation(value = "登录的接口")
    @PostMapping("/login")
    public AjaxResult login(@RequestBody User user){
        String username = "admin";
        String password = "admin";
        if(username.equals(user.getName())&&password.equals(user.getPassword())){
            return AjaxResult.me().setSuccess(true).setMessage("登录成功!").setRestObj(user);
        }
        return AjaxResult.me().setSuccess(false).setMessage("登录失败!");
    }
}
