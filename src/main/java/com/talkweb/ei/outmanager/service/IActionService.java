package com.talkweb.ei.outmanager.service;

import java.util.List;

import com.talkweb.ei.outmanager.model.OutUser;
import com.talkweb.ei.outmanager.model.VOutUseraction;

/**
 * �û��ӿ�
 * @author zhq
 *
 */
public interface IActionService {
	

	/**
	 * ��������û��Ļ
	 * @param list
	 * @return
	 */
	boolean pCreateAction(List<OutUser> list, VOutUseraction userAction);
	
	
	
	
	/**
	 * �����û��Ļ
	 * @param list
	 * @return
	 */
	boolean importAction(List<String> exldata);	
	
	
	
	
	
	
}
