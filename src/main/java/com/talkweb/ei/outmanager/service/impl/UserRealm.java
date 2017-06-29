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
        //��ȡ��¼ʱ������û���
		OutUser inuser = (OutUser) arg0.fromRealm(getName()).iterator().next();
        //�����ݿ���Ƿ��д˶���
        OutUser user = userService.getUser(inuser.getId());
        
        if (user != null) {
            //Ȩ����Ϣ����info,������Ų�����û������еĽ�ɫ��role����Ȩ�ޣ�permission��
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            
            //��ɫ          
            Set<String> roles = new HashSet<String>();
            //Ȩ��
            Set<String> permissions = new HashSet<String>();
            
            if("0".equals(user.getUsertype())){           	
            	//������
            	roles.add("manager");
            	
            	
            	//button view
            	permissions.add("add");            	
            	permissions.add("mod");
            	permissions.add("del");            	
            	permissions.add("export");           	
            	permissions.add("import");
            	
            	//����
            	permissions.add("report");
            	
            	
            } else {
            	//��ͨ�û�
            	roles.add("user");
            }
            
            permissions.add("query");
            
            //�û��Ľ�ɫ���Ϻ�Ȩ��
            info.setRoles(roles);
            info.addStringPermissions(permissions);
            
            
            return info;
        }
        return null;
		
	}

	@Override
	/**
	 * ��֤����
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {

		//ҳ�洫�͵���֤����
		ShiroToken token = (ShiroToken) arg0;
        String userid = token.getUsername();
          
        OutUser inuser = userService.getUser(userid);        
        AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(inuser, inuser.getPwd(), this.getName());
        SecurityUtils.getSubject().getSession().setAttribute("currentUser", this.getName());
        
        return authcInfo;

	}

}
