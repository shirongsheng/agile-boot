package com.shirs.agileboot.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

@Data
public class SysUser implements UserDetails {

    private long id;

    private String userName;

    private String nickName;

    @TableField("password")
    private String password;

    private Integer status;

    private String email;

    private String phone;

    private Integer sex;

    private String avatar;

    private String userType;

    private long createBy;

    private Date createTime;

    private long updateBy;

    private Date updateTime;

    private Integer isDeleted;

    //TODO 以下方法待实现

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
