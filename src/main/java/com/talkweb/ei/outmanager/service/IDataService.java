package com.talkweb.ei.outmanager.service;

import java.util.List;

import com.talkweb.ei.outmanager.model.OutCompany;
import com.talkweb.ei.outmanager.model.OutCompanyExample;
import com.talkweb.ei.outmanager.model.OutContract;
import com.talkweb.ei.outmanager.model.OutContractExample;

/**
 * 数据服务接口，综合了绝大部分的服务接口
 * @author zhq
 *
 */
public interface IDataService {
	
	/********公司接口*******************************************start******************/
	/**
	 * 取公司列表
	 * @param sample
	 * @return
	 */
	List<OutCompany>getCompanyList(OutCompanyExample sample);
	
	//总条数
	int getCompanySize(OutCompanyExample sample);
	
	//总条数
	int getContractSize(OutContractExample sample);

	
	/**
	 * 分页数据
	 * @param limit 数据集结束下标
	 * @param offset 数据接开始下标
	 * @param sample 查询条件
	 * @return
	 */
	List<OutCompany>getCompanyList(int limit,int offset, OutCompanyExample sample);
	
	
	/**
	 * 取一个
	 * @param ID
	 * @return
	 */
	OutCompany getCompany(String ID);
	
	
	/**
	 * 添加一个
	 * @param sample
	 * @return
	 */
	boolean addCompany(OutCompany sample);

	
	/**
	 * 更新一个
	 * @param sample
	 * @return
	 */
	boolean updateCompany(OutCompany sample);
	
	
	/**
	 * 删除一个
	 * @param sample
	 * @return
	 */
	boolean deleteCompany(OutCompany sample);
	
	
	/**
	 * 批量导入数据
	 * @param sample
	 * @return
	 */
	boolean importCompany(List<String> exldata);	
	
	

	/********公司接口************************************************end*************/
	
	
	
	/********合同接口*******************************************start******************/
	/**
	 * 取公司列表
	 * @param sample
	 * @return
	 */
	List<OutCompany>getContractList(OutContractExample sample);
	
	
	/**
	 * 分页数据
	 * @param limit 数据集结束下标
	 * @param offset 数据接开始下标
	 * @param sample 查询条件
	 * @return
	 */
	List<OutContract>getContractList(int limit,int offset, OutContractExample sample);
	
	
	/**
	 * 取一个
	 * @param ID
	 * @return
	 */
	OutContract getContract(String ID);
	
	
	
	/**
	 * 更新一个
	 * @param sample
	 * @return
	 */
	boolean updateContract(OutContract sample);
	
	/**
	 * 加新一个
	 * @param sample
	 * @return
	 */
	boolean addContract(OutContract sample);

	
	/**
	 * 删除一个
	 * @param sample
	 * @return
	 */
	boolean deleteContract(OutContract sample);
	
	
	
	/**
	 * 批量导入数据
	 * @param sample
	 * @return
	 */
	boolean importContract(List<String> exldata);	

	/********合同接口************************************************end*************/	
	
	
	
}
