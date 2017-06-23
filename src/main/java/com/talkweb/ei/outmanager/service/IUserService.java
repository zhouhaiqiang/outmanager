package com.talkweb.ei.outmanager.service;

import java.util.List;

import com.talkweb.ei.outmanager.model.OutUserExample;
import com.talkweb.ei.outmanager.model.OutUser;

/**
 * 用户接口
 * @author zhq
 *
 */
public interface IUserService {
	
	/**
	 * 认证
	 * 
	 * @param  String userid, String pwd
	 * @return
	 */
	boolean auth(String userid, String pwd);
	
	
	/**
	 * 用户基本信息导入
	 * 
	 * @param  List<String> exldata
	 * @return
	 */
	boolean importBase(List<String> exldata);	
	

	
	/**
	 * 分配信息导入
	 * 
	 * @param  List<String> exldata
	 * @return
	 */
	boolean importFenpei(List<String> exldata);		
	
	
	/**
	 * 教育信息导入
	 * 
	 * @param  List<String> exldata
	 * @return
	 */
	boolean importJiaoyu(List<String> exldata);		
	
	
	
	/**
	 * 职业技能信息导入
	 * 
	 * @param  List<String> exldata
	 * @return
	 */
	boolean importZhiye(List<String> exldata);		
	
	/**
	 * 专业信息导入
	 * 
	 * @param  List<String> exldata
	 * @return
	 */
	boolean importZhuanye(List<String> exldata);
	
	
	/**
	 * 劳动关系导入
	 * 
	 * @param  List<String> exldata
	 * @return
	 */
	boolean importLaodong(List<String> exldata);	
	
	/**
	 * 解除信息导入
	 * 
	 * @param  List<String> exldata
	 * @return
	 */
	boolean importJiechu(List<String> exldata);		
	
	
	
	
	
	/*******************************常用接口****************************************/
	//总条数
	int getUserSize(OutUserExample sample);
	
	/**
	 * 取公司列表
	 * @param sample
	 * @return
	 */
	List<OutUser>getUserList(OutUserExample sample);
	
	
	/**
	 * 分页数据
	 * @param limit 数据集结束下标
	 * @param offset 数据接开始下标
	 * @param sample 查询条件
	 * @return
	 */
	List<OutUser>getUserList(int limit,int offset, OutUserExample sample);
	
	
	/**
	 * 取一个
	 * @param ID
	 * @return
	 */
	OutUser getUser(String ID);
	

	/**
	 * 取一个用户（用户编号唯一）
	 * @return
	 */
	OutUser getUserByCode(String code);	
	
	
	/**
	 * 更新一个
	 * @param sample
	 * @return
	 */
	boolean updateUser(OutUser sample);
	
	/**
	 * 加新一个
	 * @param sample
	 * @return
	 */
	boolean addUser(OutUser sample);

	
	/**
	 * 删除一个
	 * @param sample
	 * @return
	 */
	boolean deleteUser(OutUser sample);	
	/*******************************常用接口**************************end**************/
	
	
	
	
	
	
}
