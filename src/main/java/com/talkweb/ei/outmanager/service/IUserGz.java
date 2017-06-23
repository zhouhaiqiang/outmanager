package com.talkweb.ei.outmanager.service;

import java.util.List;

import com.talkweb.ei.outmanager.model.VOutUsergz;
import com.talkweb.ei.outmanager.model.VOutUsergzExample;


/**
 * 用户接口
 * @author zhq
 *
 */
public interface IUserGz {
	
	
	//总条数
	int getUserGzSize(VOutUsergzExample sample);
	
	
	
	/**
	 * 分页数据
	 * @param limit 数据集结束下标
	 * @param offset 数据接开始下标
	 * @param sample 查询条件
	 * @return
	 */
	List<VOutUsergz>getUserGzList(int limit,int offset, VOutUsergzExample sample);
	

	
	/**
	 * 个人费用信息导入
	 * 
	 * @param  List<String> exldata
	 * @return
	 */
	boolean importGrhy(List<String> exldata);	
	
	
	/**
	 * 个人费用信息导入
	 * 
	 * @param  List<String> exldata
	 * @return
	 */
	boolean importJthy(List<String> exldata);		
	
	
	
	
	
	
	
}
