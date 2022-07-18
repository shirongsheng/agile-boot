package com.shirs.agileboot.modules.system.controller;

import com.shirs.agileboot.common.page.ResponseResult;
import com.shirs.agileboot.modules.system.entity.MenuCreateVo;
import com.shirs.agileboot.modules.system.entity.MenuVo;
import com.shirs.agileboot.modules.system.entity.SysMenu;
import com.shirs.agileboot.modules.system.service.SysMenuService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "菜单")
public class MenuController {

    @Autowired
    private SysMenuService menuService;

    @PostMapping(value = "/menu")
    public ResponseResult create(@RequestBody MenuCreateVo menuCreateVo) {
        menuService.create(menuCreateVo);
        return new ResponseResult(200, "success");
    }

    @GetMapping(value = "/menu/list")
    public ResponseResult menuList() {
        List<SysMenu> menus = menuService.menuList();
        return new ResponseResult(200, "success", menus);
    }

    @GetMapping(value = "/menu/tree")
    public ResponseResult queryMenuTree() {
        List<MenuVo> menuVos = menuService.queryMenuTree();
        return new ResponseResult(200, "success", menuVos);
    }
}
