package com.shirs.agileboot.modules.system.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shirs.agileboot.annotation.OperationLogDetail;
import com.shirs.agileboot.common.page.PageRequest;
import com.shirs.agileboot.common.page.PageResult;
import com.shirs.agileboot.enums.OperationType;
import com.shirs.agileboot.enums.OperationUnit;
import com.shirs.agileboot.modules.system.entity.User;
import com.shirs.agileboot.modules.system.entity.UserVo;
import com.shirs.agileboot.modules.system.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
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
    @OperationLogDetail(detail = "注册用户",level = 3,operationUnit = OperationUnit.USER,operationType = OperationType.INSERT)
    public String registerUser(@RequestBody User user){
        int insert = userService.insert(user);
        return "注册成功";
    }

    @PostMapping("/deleteBatch")
    @ApiOperation(value = "删除",notes = "删除")
    @OperationLogDetail(detail = "删除用户",level = 3,operationUnit = OperationUnit.USER,operationType = OperationType.DELETE)
    public int registerUser(@RequestBody JSONObject params){
        ArrayList list = (ArrayList) params.get("ids");
        int i = userService.deleteBatch(list);
        return i;
    }

    @PostMapping(value="/findPage")
    public PageResult findPage(@RequestBody User user) {
        return userService.findPage(user);
    }
}
