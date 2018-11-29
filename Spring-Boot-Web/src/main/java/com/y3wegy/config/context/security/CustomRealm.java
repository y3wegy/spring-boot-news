package com.y3wegy.config.context.security;

import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.y3wegy.bean.user.SecurityUser;
import com.y3wegy.mapper.master.ShiroSampleMapper;
import com.y3wegy.mapper.master.user.UserMapper;

/**
 * @author Rui
 */
@Component("customRealm")
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private ShiroSampleMapper shiroSampleMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * login in validate
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        List<SecurityUser> userList = userMapper.get(username);
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        }
        token.setRememberMe(true);
        return new SimpleAuthenticationInfo(userList.get(0), userList.get(0).getPassword(), getName());
    }

    /**
     * perm
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SecurityUser securityUser = (SecurityUser) super.getAvailablePrincipal(principalCollection);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roles = shiroSampleMapper.getRolesByUsername(securityUser.getUserName());
        authorizationInfo.setRoles(roles);
        roles.forEach(role -> {
            Set<String> permissions = this.shiroSampleMapper.getPermissionsByRole(role);
            authorizationInfo.addStringPermissions(permissions);
        });
        return authorizationInfo;
    }
}
