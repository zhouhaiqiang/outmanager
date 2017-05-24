package com.talkweb.ei.outmanager.service;

import java.util.List;

import com.talkweb.ei.outmanager.model.TOutDict;
import com.talkweb.ei.outmanager.model.TOutDictExample;

/**
 * �û��ӿ�
 * @author zhq
 *
 */
public interface IDictory {
	
	/**
	 * ���ֵ��б�
	 * @param sample
	 * @return
	 */
	List<TOutDict>getDirctList(TOutDictExample sample);
	
	
	
	/**
	 * �����Ͳ��Ҷ�Ӧ���ֵ��б�
	 * @param lx
	 * @return
	 */
	List<TOutDict>getDirctByLx(String lx);

}
