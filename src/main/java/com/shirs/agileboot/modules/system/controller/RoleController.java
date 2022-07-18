package com.shirs.agileboot.modules.system.controller;

import com.shirs.agileboot.common.page.ResponseResult;
import com.shirs.agileboot.modules.system.entity.RoleCreateVo;
import com.shirs.agileboot.modules.system.service.SysRoleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "角色")
public class RoleController {

    @Autowired
    private SysRoleService roleService;

    @PostMapping(value = "/role")
    public ResponseResult create(@RequestBody RoleCreateVo roleCreateVo) {
        int i = roleService.create(roleCreateVo);
        return new ResponseResult(200, "success", i);
    }
}
