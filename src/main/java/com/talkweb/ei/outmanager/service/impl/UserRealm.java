package com.talkweb.ei.outmanager.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talkweb.ei.outmanager.model.OutUser;
import com.talkweb.ei.outmanager.service.IUserService;
import com.talkweb.ei.shiro.ShiroToken;

@Service
public class UserRealm extends AuthorizingRealm {


	@Autowired
	private IUserService userService;
	
	
	@Override
	/**
	 * 授权
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        //获取登录时输入的用户名
		OutUser inuser = (OutUser) arg0.fromRealm(getName()).iterator().next();
        //到数据库查是否有此对象
        OutUser user = userService.getUser(inuser.getId());
        
        if (user != null) {
            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            
            //角色          
            Set<String> roles = new HashSet<String>();
            //权限
            Set<String> permissions = new HashSet<String>();
            
            if("0".equals(user.getUsertype())){           	
            	//管理者
            	roles.add("manager");
            	
            	
            	//button view
            	permissions.add("add");            	
            	permissions.add("mod");
            	permissions.add("del");            	
            	permissions.add("export");           	
            	permissions.add("import");
            	
            	//报表
            	permissions.add("report");
            	
            	
            } else {
            	//普通用户
            	roles.add("user");
            }
            
            permissions.add("query");
            
            //用户的角色集合和权限
            info.setRoles(roles);
            info.addStringPermissions(permissions);
            
            
            return info;
        }
        return null;
		
	}

	@Override
	/**
	 * 认证方法
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {

		//页面传送的认证令牌
		ShiroToken token = (ShiroToken) arg0;
        String userid = token.getUsername();
          
        OutUser inuser = userService.getUser(userid);        
        AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(inuser, inuser.getPwd(), this.getName());
        SecurityUtils.getSubject().getSession().setAttribute("currentUser", this.getName());
        
        return authcInfo;

	}

}
