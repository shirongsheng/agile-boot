package com.shirs.agileboot.modules.system.controller;

import com.shirs.agileboot.modules.system.entity.MenuVo;
import com.shirs.agileboot.modules.system.entity.UserVo;
import com.shirs.agileboot.modules.system.service.MenuService;
import com.shirs.agileboot.modules.system.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/menu")
@Api(tags = "菜单")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    @ApiOperation(value = "列表",notes = "列表")
    public List<MenuVo> queryList(){
        List<MenuVo> menuVos = menuService.menuList();
        return menuVos;
    }

}
