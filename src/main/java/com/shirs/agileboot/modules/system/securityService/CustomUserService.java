package com.shirs.agileboot.modules.system.securityService;

import com.shirs.agileboot.common.constant.AgileConstant;
import com.shirs.agileboot.modules.system.entity.UserVo;
import com.shirs.agileboot.modules.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomUserService implements UserDetailsService {

    @Autowired
    private UserService userService;

    /**
     * 登陆验证时，通过username获取用户的所有权限信息
     * 并返回UserDetails放到spring的全局缓存SecurityContextHolder中，以供授权器使用
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserVo userVo = userService.selectUser(username);

        if (null == userVo) {
            log.info(AgileConstant.USER_NOT_EXIST);
            throw new UsernameNotFoundException(AgileConstant.USERNMAE_OR_PASSWORD_ERROR);
        }

        MyUserDetails myUserDetail = new MyUserDetails();
        myUserDetail.setUsername(userVo.getName());
        myUserDetail.setPassword(userVo.getPassword());
        return myUserDetail;
    }
}
