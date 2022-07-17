package com.shirs.agileboot.modules.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shirs.agileboot.common.page.ResponseResult;
import com.shirs.agileboot.modules.system.entity.SysUser;
import com.shirs.agileboot.modules.system.service.SysUserService;
import com.shirs.agileboot.modules.system.vo.UserQueryVo;
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
    private SysUserService userService;

    @PostMapping("/list")
    @ApiOperation(value = "列表", notes = "列表")
    public List<SysUser> queryList() {

        return userService.userList();

    }

    /*@PostMapping("/register")
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
    }*/

    @PostMapping(value = "/findPage")
    public ResponseResult findPage(@RequestBody UserQueryVo userQueryVo) {
        IPage<SysUser> page = userService.findPage(userQueryVo);
        return new ResponseResult(200, "success", page);
    }
}
