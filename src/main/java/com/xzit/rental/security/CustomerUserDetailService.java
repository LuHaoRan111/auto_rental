package com.xzit.rental.security;

import com.xzit.rental.entity.Permission;
import com.xzit.rental.entity.User;
import com.xzit.rental.service.IPermissionService;
import com.xzit.rental.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;


/**
 * 编写userDetailService
 */
@Component
public class CustomerUserDetailService implements UserDetailsService {
    @Autowired
    private IUserService userService;


    @Autowired
    private IPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userService.selectUserByName(username);//到数据库中按username查询
        if(user==null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        //查询用户权限列表
        List<Permission> permissionList=permissionService.selectPermissionByUserId(user.getId());
        user.setPermissionList(permissionList);
        //通过stream流处理，将权限对象转换为权限字符串列表
        List<String> list=permissionList.stream().filter(Objects::nonNull)
                .map(Permission::getPermissionCode)
                .filter(Objects::nonNull).
                toList();
        String[] array=list.toArray(new String[list.size()]);
        List<GrantedAuthority> authorities= AuthorityUtils.createAuthorityList(array);
        user.setAuthorities(authorities);
        System.out.println(user);
        return user;
    }
}
