package com.taotao.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	//不适用shiro的login，使用自己的login，自己实现认证
	@RequestMapping("login")
	public String login(String username,String password,HttpServletRequest request){
		//生成一个shiro的token，拿着token去shiro认证
		if(StringUtils.isBlank(username)||StringUtils.isBlank(password)){
			return "login";
		}
		UsernamePasswordToken token = new UsernamePasswordToken(username, DigestUtils.md5DigestAsHex(password.getBytes()));  
		token.setRememberMe(true);  
		
		//再次去认证，调用shiroDbRealm.doGetAuthenticationInfo()方法
		Subject currentUser = SecurityUtils.getSubject();
		
		try {
			currentUser.login(token);
		} catch(Exception uae){  
            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,未知账户");  
            request.setAttribute("message_login", "用户名或密码不正确");  
            return "login";
        }
		return "index";
	}
	@RequestMapping("index")
	public String index(String username,String password){
		return "index";//如果认证通过，跳转到主页面
	}
	@RequestMapping("unAuth")
	public String unAuth(String username,String password){
		return "unAuth";//如果认证通过，跳转到主页面
	}
}
