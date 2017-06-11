package com.taotao.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

import com.taotao.mapper.UserMapper;
import com.taotao.pojo.User;

public class ShiroDbRealm extends CasRealm{  
  
	@Autowired
    private UserMapper userMapper;  
  
    /** 
     * 授权信息 
     */  
 protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {  
        String username=(String)principals.fromRealm(getName()).iterator().next();  
        if( username != null ){  
//            User user = accountManager.get( username );  
//            if( user != null && user.getRoles() != null ){  
//                SimpleAuthorizationInfo info = new SimpleAuthorizationInf();  
//                for( SecurityRole each: user.getRoles() ){  
//                        info.addRole(each.getName());  
//                        info.addStringPermissions(each.getPermissionsAsString());  
//                }  
//                return info;  
//            }  
        	SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        	//去数据库中查询用户的角色和权限
        	info.addRole("领导1");  
        	info.addStringPermission("listItem");
        	//info.addStringPermissions(permissions)
        	return info;
        } 
        return null;  
    }  
  
    /** 
     * 认证信息 （认证后，返回一个SimpleAuthenticationInfo：用户名和密码   subject）
     */  
    protected AuthenticationInfo doGetAuthenticationInfo( AuthenticationToken authcToken ){  
    	AuthenticationInfo authc = super.doGetAuthenticationInfo(authcToken);

		String account = (String) authc.getPrincipals().getPrimaryPrincipal();

		User user = userMapper.findUserByUsernameAndPassword(account, "1b115d01a16bf363a8da2f588b3a0297");
		
		SecurityUtils.getSubject().getSession().setAttribute("user", user);

		return authc;
    }  
  
}  
