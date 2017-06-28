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
	 * ��Ȩ
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
//        //��ȡ��¼ʱ������û���
//        String loginName = (String) principalCollection.fromRealm(getName()).iterator().next();
//        //�����ݿ���Ƿ��д˶���
//        User user = this.getDao().findByName(loginName);
//        if (user != null) {
//            //Ȩ����Ϣ����info,������Ų�����û������еĽ�ɫ��role����Ȩ�ޣ�permission��
//            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//            //�û��Ľ�ɫ����
//            info.setRoles(user.getRolesName());
//            //�û��Ľ�ɫ��Ӧ������Ȩ�ޣ����ֻʹ�ý�ɫ�������Ȩ�ޣ���������п��Բ�Ҫ
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
	 * ��֤����
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {

		//ҳ�洫�͵���֤����
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
//        	//��֤ͨ��
//        	return new SimpleAuthenticationInfo(user,pwd, getName());
//        }
        
        return null;
	
		
	}

}
