package com.y3wegy.config.context.security;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.y3wegy.base.ServiceExeption;
import com.y3wegy.base.tools.JackSonHelper;
import com.y3wegy.base.web.ResponseJson;
import com.y3wegy.base.web.bean.user.SecurityUser;
import com.y3wegy.base.web.bean.user.UserRole;
import com.y3wegy.base.web.tools.RestCallExecutor;
import com.y3wegy.mapper.master.ShiroSampleMapper;
import com.y3wegy.rpc.impl.UserFeignClient;

/**
 * @author y3wegy
 */
@Component("customRealm")
public class CustomRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(CustomRealm.class);

    @Value("${eureka.service.url}")
    private String serviceURL;

    @Autowired
    private ShiroSampleMapper shiroSampleMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserFeignClient userFeignClient;

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

        try {
            /*List<SecurityUser> userList = RestCallExecutor.postForObject(restTemplate,
                    serviceURL + "/api/user/query", securityUser, new TypeReference<List<SecurityUser>>() {
                    });*/
            ResponseJson responseJson = userFeignClient.queryUser(securityUser);
            List<SecurityUser> userList = JackSonHelper.jsonStr2Obj(String.valueOf(responseJson.getData()),new TypeReference<List<SecurityUser>>() {});
            if (CollectionUtils.isEmpty(userList)) {
                return null;
            }
            token.setRememberMe(true);
            return new SimpleAuthenticationInfo(userList.get(0), userList.get(0).getPassword(), getName());
        } catch (ServiceExeption serviceExeption) {
            logger.error("Call " + serviceURL + "/api/user/query failed", serviceExeption);
            throw new AuthenticationException(serviceExeption);
        }

    }

    /**
     * perm
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SecurityUser securityUser = (SecurityUser) super.getAvailablePrincipal(principalCollection);
        List<UserRole> userRoleList = null;
        try {
            userRoleList = RestCallExecutor.postForObject(restTemplate,
                    serviceURL + "/api/user/queryRole", securityUser, new TypeReference<List<UserRole>>() {});
        } catch (ServiceExeption serviceExeption) {
            logger.error("Call " + serviceURL + "/api/user/queryRole failed", serviceExeption);
        }
        if (CollectionUtils.isEmpty(userRoleList)) {
            return null;
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roles = new HashSet<>(userRoleList.size());
        userRoleList.forEach(userRole -> roles.add(userRole.name()));
        authorizationInfo.setRoles(roles);
        roles.forEach(role -> {
            Set<String> permissions = this.shiroSampleMapper.getPermissionsByRole(role);
            authorizationInfo.addStringPermissions(permissions);
        });
        return authorizationInfo;
    }
}
