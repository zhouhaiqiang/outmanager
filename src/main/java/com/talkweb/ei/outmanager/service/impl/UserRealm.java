package com.talkweb.ei.outmanager.service.impl;

import java.util.HashSet;
import java.util.List;
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

import com.talkweb.ei.outmanager.dao.VOutUserdutyMapper;
import com.talkweb.ei.outmanager.model.OutUser;
import com.talkweb.ei.outmanager.model.VOutUserduty;
import com.talkweb.ei.outmanager.model.VOutUserdutyExample;
import com.talkweb.ei.outmanager.service.IUserService;
import com.talkweb.ei.shiro.ShiroToken;

@Service
public class UserRealm extends AuthorizingRealm {


	@Autowired
	private IUserService userService;
	
	
	@Autowired
	private VOutUserdutyMapper vOutUserdutyMapper;
	
	
	
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

            //用户的角色集合和权限
            info.setRoles(getUserRoles(inuser.getId()));
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
        //SecurityUtils.getSubject().getSession().setAttribute("currentUser", this.getName());
              
        return authcInfo;

	}
	
	
	/**
	 * 取用户角色
	 * @param uid
	 * @return
	 */
	protected Set<String> getUserRoles(String uid) {
		
		Set<String> roles = new HashSet<String>();
		List<VOutUserduty> list = getUserDutys(uid);

		//角色列表
		for (VOutUserduty vOutUserduty : list) {
			
			roles.add(vOutUserduty.getDuty());
		
			//省级的职务，默认3个
			if("1".equals(vOutUserduty.getDutyid())||"2".equals(vOutUserduty.getDutyid())||"3".equals(vOutUserduty.getDutyid())){
				
				//保存控制的组织 山西（全）
				SecurityUtils.getSubject().getSession().setAttribute(vOutUserduty.getDuty(), "14");
				
				
			} else {
						
				//保存控制的组织id
				SecurityUtils.getSubject().getSession().setAttribute(vOutUserduty.getDuty(), vOutUserduty.getUnitid());
			
			}
		}

		return roles;
		
	}

	
	/**
	 * 职务列表
	 * @param uid
	 * @return
	 */
	private List<VOutUserduty> getUserDutys(String uid) {
		VOutUserdutyExample example = new VOutUserdutyExample();
		
		VOutUserdutyExample.Criteria criteria = example.createCriteria();
		
		//只查询一个用户
		criteria.andUseridEqualTo(uid);

		List<VOutUserduty> list = vOutUserdutyMapper.selectByExample(example);
		return list;
	}
	
//	/**
//	 * 取用户权限
//	 * @param uid
//	 * @return
//	 */
//	protected Set<String> getUserPermissionss(String uid) {
//		
//		Set<String> permissions = new HashSet<String>();
//		
//		
//		List<VOutUserduty> list = getUserDutys(uid);
//
//		//数据操作范围
//		for (VOutUserduty vOutUserduty : list) {
//			
//			
//			permissions.add(vOutUserduty.getDuty());			
//		}
//		
//		return permissions;
//		
//	}
}
