package com.talkweb.ei.outmanager.service;

import java.util.List;

import com.talkweb.ei.outmanager.model.OutUser;
import com.talkweb.ei.outmanager.model.VOutUseraction;

/**
 * 用户接口
 * @author zhq
 *
 */
public interface IActionService {
	

	/**
	 * 批量添加用户的活动
	 * @param list
	 * @return
	 */
	boolean pCreateAction(List<OutUser> list, VOutUseraction userAction);
	
	
	
	
	/**
	 * 导入用户的活动
	 * @param list
	 * @return
	 */
	boolean importAction(List<String> exldata);	
	
	
	
	
	
	
}
