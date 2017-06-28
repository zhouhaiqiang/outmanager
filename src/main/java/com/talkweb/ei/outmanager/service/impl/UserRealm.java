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
//        //获取登录时输入的用户名
//        String loginName = (String) principalCollection.fromRealm(getName()).iterator().next();
//        //到数据库查是否有此对象
//        User user = this.getDao().findByName(loginName);
//        if (user != null) {
//            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
//            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//            //用户的角色集合
//            info.setRoles(user.getRolesName());
//            //用户的角色对应的所有权限，如果只使用角色定义访问权限，下面的四行可以不要
//            List<Role> roleList = user.getRoleList();
//            for (Role role : roleList) {
//                info.addStringPermissions(role.getPermissionsString());
//            }
//            return info;
//        }
//        return null;
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Set roles =  new HashSet();
		roles.add("Admin");
		roles.add("Manager");
		roles.add("User");
		
		info.setRoles(roles);
		
		Set perms =  new HashSet();
		perms.add("add");
		info.addStringPermissions(perms);
		
		return info;
	}

	@Override
	/**
	 * 认证方法
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {

		//页面传送的认证令牌
		ShiroToken token = (ShiroToken) arg0;
        String userid = token.getUsername();
        String pwd = token.getPswd();
        
        OutUser inuser = userService.getUser(userid);
        System.out.println("=====user============="+inuser.getPwd());
        
        if("di".equals(token.getUsername())){    
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo("di", "wwwwww", this.getName());    
            SecurityUtils.getSubject().getSession().setAttribute("currentUser", this.getName());  
            return authcInfo;    
        }else if("user".equals(token.getUsername())){    
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo("user", "user", this.getName());    
            SecurityUtils.getSubject().getSession().setAttribute("currentUser", this.getName());  
            return authcInfo;    
        }     
        
        
        OutUser user = userService.getUser(userid);
//        if(pwd.equals(user.getPwd())){
//        	
//        	//认证通过
//        	return new SimpleAuthenticationInfo(user,pwd, getName());
//        }
        
        return null;
	
		
	}

}
