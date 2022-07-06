package com.shirs.agileboot.modules.system.controller;

import com.shirs.agileboot.common.page.ResponseResult;
import com.shirs.agileboot.modules.system.entity.SysUser;
import com.shirs.agileboot.modules.system.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@Slf4j
public class LoginController {

    @Autowired
    private SysUserService userService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody SysUser sysUser) {
        Map<String, String> login = userService.login(sysUser);
        ResponseResult responseResult = new ResponseResult(200, "登录成功1", login);
        return responseResult;
    }

    @GetMapping("/logout")
    public ResponseResult logout() {
        try {
            userService.logout();
            return new ResponseResult(200, "退出成功");
        } catch (Exception e) {
            log.error("log out failed!" + e);
            return new ResponseResult(500, "退出失败");
        }
    }
}
