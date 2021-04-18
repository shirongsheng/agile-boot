package com.shirs.agileboot.modules.system.controller;

import com.shirs.agileboot.modules.system.entity.ReplaceString;
    import com.shirs.agileboot.modules.system.service.UserService;
    import io.swagger.annotations.Api;
    import io.swagger.annotations.ApiOperation;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping("/index")
    @Api(tags = "首页")
    public class IndexController {

        @Autowired
        private UserService userService;

        @PostMapping("/page")
        @ApiOperation(value = "首页",notes = "首页")
        public String index(@RequestBody ReplaceString replaceString){

            String[] origin = replaceString.getOrigin().split("，");
            String[] target = replaceString.getTarget().split("，");
            String content = replaceString.getContent();
            for (int i=0;i<origin.length;i++){
                content = content.replace(origin[i],target[i]);
            }
            return content;

        }

    }
