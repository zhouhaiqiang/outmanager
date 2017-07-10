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

            //�û��Ľ�ɫ���Ϻ�Ȩ��
            info.setRoles(getUserRoles(inuser.getId()));
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
        //SecurityUtils.getSubject().getSession().setAttribute("currentUser", this.getName());
              
        return authcInfo;

	}
	
	
	/**
	 * ȡ�û���ɫ
	 * @param uid
	 * @return
	 */
	protected Set<String> getUserRoles(String uid) {
		
		Set<String> roles = new HashSet<String>();
		List<VOutUserduty> list = getUserDutys(uid);

		//��ɫ�б�
		for (VOutUserduty vOutUserduty : list) {
			
			roles.add(vOutUserduty.getDuty());
		
			//ʡ����ְ��Ĭ��3��
			if("1".equals(vOutUserduty.getDutyid())||"2".equals(vOutUserduty.getDutyid())||"3".equals(vOutUserduty.getDutyid())){
				
				//������Ƶ���֯ ɽ����ȫ��
				SecurityUtils.getSubject().getSession().setAttribute(vOutUserduty.getDuty(), "14");
				
				
			} else {
						
				//������Ƶ���֯id
				SecurityUtils.getSubject().getSession().setAttribute(vOutUserduty.getDuty(), vOutUserduty.getUnitid());
			
			}
		}

		return roles;
		
	}

	
	/**
	 * ְ���б�
	 * @param uid
	 * @return
	 */
	private List<VOutUserduty> getUserDutys(String uid) {
		VOutUserdutyExample example = new VOutUserdutyExample();
		
		VOutUserdutyExample.Criteria criteria = example.createCriteria();
		
		//ֻ��ѯһ���û�
		criteria.andUseridEqualTo(uid);

		List<VOutUserduty> list = vOutUserdutyMapper.selectByExample(example);
		return list;
	}
	
//	/**
//	 * ȡ�û�Ȩ��
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
//		//���ݲ�����Χ
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
