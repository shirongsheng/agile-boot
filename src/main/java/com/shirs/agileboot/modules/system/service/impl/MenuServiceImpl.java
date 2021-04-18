package com.shirs.agileboot.modules.system.service.impl;

import com.shirs.agileboot.modules.system.entity.MenuVo;
import com.shirs.agileboot.modules.system.mapper.MenuMapper;
import com.shirs.agileboot.modules.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<MenuVo> menuList() {
        return menuMapper.menuList();
    }
}
