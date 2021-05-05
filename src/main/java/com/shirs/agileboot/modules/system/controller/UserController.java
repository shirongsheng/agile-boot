package com.shirs.agileboot.modules.system.controller;

import com.shirs.agileboot.annotation.OperationLogDetail;
import com.shirs.agileboot.enums.OperationType;
import com.shirs.agileboot.enums.OperationUnit;
import com.shirs.agileboot.modules.system.entity.User;
import com.shirs.agileboot.modules.system.entity.UserVo;
import com.shirs.agileboot.modules.system.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Api(tags = "用户")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/list")
    @ApiOperation(value = "列表",notes = "列表")
    public List<UserVo> queryList(@RequestBody User user){

        return userService.userList(user);

    }

    @PostMapping("/register")
    @ApiOperation(value = "注册",notes = "注册")
    @OperationLogDetail(detail = "通过手机号[{{tel}}]获取用户名",level = 3,operationUnit = OperationUnit.USER,operationType = OperationType.SELECT)
    public String registerUser(@RequestBody User user){
        int insert = userService.insert(user);
        return "注册成功";
    }

}
