package com.y3wegy.config.context.security;

import com.y3wegy.base.web.ResponseJson;
import com.y3wegy.base.web.bean.user.SecurityUser;
import com.y3wegy.mapper.master.ShiroSampleMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Rui
 */
@Component("customRealm")
public class CustomRealm extends AuthorizingRealm {

    @Value("${eureka.service.url}")
    private String serviceURL;

    @Autowired
    private ShiroSampleMapper shiroSampleMapper;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * login in validate
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = String.valueOf(token.getPassword());
        SecurityUser securityUser = new SecurityUser();
        securityUser.setUserName(username);
        securityUser.setPassword(password);
        Map<String, String> map = new HashMap<>(2);
        map.put("userName", username);
        map.put("password", password);
        ResponseJson responseJson = restTemplate.postForObject("http://" + serviceURL + "/login", null, ResponseJson.class, map);
        SecurityUser user = (SecurityUser) responseJson.getData();
        if (user == null) {
            return null;
        }
        token.setRememberMe(true);
        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
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
