package com.talkweb.ei.outmanager.service;

import java.util.List;

import com.talkweb.ei.outmanager.model.KeyValue;
import com.talkweb.ei.outmanager.model.TOutDict;
import com.talkweb.ei.outmanager.model.TOutDictExample;
import com.talkweb.ei.outmanager.model.TreeNode;

/**
 * 用户接口
 * @author zhq
 *
 */
public interface IDictory {
	
	/**
	 * 查字典列表
	 * @param sample
	 * @return
	 */
	List<TOutDict>getDirctList(TOutDictExample sample);
	
	
	
	/**
	 * 按类型查找对应的字典列表
	 * @param lx
	 * @return
	 */
	List<TOutDict>getDirctByLx(String lx);
	
	
	/**
	 * 查找公司下的员工
	 * @param companyname
	 * @return
	 */
	List<KeyValue> getUserByCom(String companyname);
	
	
	
	/**
	 * 查找联通的所有单位
	 * @return
	 */
	List<TreeNode> getAllUnit();	
	
	

}
