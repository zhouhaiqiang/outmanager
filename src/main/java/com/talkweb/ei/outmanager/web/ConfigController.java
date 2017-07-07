package com.talkweb.ei.outmanager.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.talkweb.ei.di.common.PageResult;
import com.talkweb.ei.outmanager.dao.OrgMapper;
import com.talkweb.ei.outmanager.dao.OutUserMapper;
import com.talkweb.ei.outmanager.dao.TOutDutyMapper;
import com.talkweb.ei.outmanager.model.KeyValue;
import com.talkweb.ei.outmanager.model.KeyValueObj;
import com.talkweb.ei.outmanager.model.OutCompany;
import com.talkweb.ei.outmanager.model.OutContract;
import com.talkweb.ei.outmanager.model.TOutDict;
import com.talkweb.ei.outmanager.model.TOutDuty;
import com.talkweb.ei.outmanager.model.TreeNode;
import com.talkweb.ei.outmanager.service.IDataService;
import com.talkweb.ei.outmanager.service.IDictory;


/**
 * 项目字典和配置表读取综合服务提供
 * @author zhq
 *
 */

@Controller
@RequestMapping("/config")
public class ConfigController {
	//业务操作一般通过服务来实现
	@Autowired
	private IDataService IDataService;	
	
	
	@Autowired
	private IDictory iDictory;		

	// ajax json
	@RequestMapping(value = "/companyjson", method = RequestMethod.GET, produces = {
			"application/json; charset=utf-8" })
	@ResponseBody	
	
	/**
	 * 分页查询公司
	 * @param limit
	 * @param offset
	 * @param type
	 * @param unit
	 * @param name
	 * @return
	 */
	private PageResult getCompanyList(String lx){

		
		List<OutCompany> list = IDataService.getAllCompanyList();
		//构建返回值
		PageResult ret = new PageResult(true,toKeyValueCom(list),list.size());
		System.out.println("----------"+ret);
		return ret;			
	}
	
	
	
	// ajax json
	@RequestMapping(value = "/contractcode_json", method = RequestMethod.GET, produces = {
			"application/json; charset=utf-8" })
	@ResponseBody	
	
	/**
	 * 分页查询公司
	 * @param limit
	 * @param offset
	 * @param type
	 * @param unit
	 * @param name
	 * @return
	 */
	private PageResult contractcode_json(String lx){

		List<OutContract> list = IDataService.getAllContractList();
		
		
		//构建返回值
		PageResult ret = new PageResult(true,toKeyValueCon(list),list.size());
		//System.out.println("----------"+ret);
		return ret;			
	}	
	
	
	
	
	// ajax json
	@RequestMapping(value = "/dict_json", method = RequestMethod.GET, produces = {
			"application/json; charset=utf-8" })
	@ResponseBody	

	/**
	 * 字典查询
	 * @param lx
	 * @return
	 */
	private PageResult getDictByLx(String lx){
		
		List<TOutDict> list = iDictory.getDirctByLx(lx);
				
		//构建返回值
		PageResult ret = new PageResult(true,list,list.size());
		System.out.println("----dirc------"+ret);
		return ret;			
	}
	
	
	
	
	
	// ajax json
	@RequestMapping(value = "/duty_json", method = RequestMethod.GET, produces = {
			"application/json; charset=utf-8" })
	@ResponseBody	

	/**
	 * 字典查询
	 * @param lx
	 * @return
	 */
	private PageResult getDuty(String lx){
		
		
		//查全部的职务
		List<TOutDuty> list = iDictory.getAllDuty();
				
		//构建返回值
		PageResult ret = new PageResult(true,list,list.size());
		System.out.println("----getDuty------"+ret);
		return ret;			
	}	
		

	
	/**
	 * 合同对象
	 * @param list
	 * @return
	 */
	List<KeyValueObj> toKeyValueCon(List<OutContract> list) {
		
		List<KeyValueObj> ret_list = new ArrayList<KeyValueObj>();
		
		KeyValueObj tmp;
		for(OutContract obj:list) {
			tmp = new KeyValueObj();
			tmp.setCode(obj.getId());	
			tmp.setName(obj.getConCode());
			ret_list.add(tmp);
		}
		
		return ret_list;
		
	}	
	
	
	
	/**
	 * 公司对象
	 * @param list
	 * @return
	 */
	List<KeyValueObj> toKeyValueCom(List<OutCompany> list) {
		
		List<KeyValueObj> ret_list = new ArrayList<KeyValueObj>();
		
		KeyValueObj tmp;
		for(OutCompany obj:list) {
			tmp = new KeyValueObj();
			tmp.setCode(obj.getId());	
			tmp.setName(obj.getName());
			ret_list.add(tmp);
		}
		
		return ret_list;
		
	}	
	
	
	
	
	/**
	 * 动态取得联通公司和部门
	 */
	@RequestMapping(value = "/getunits_json", method = RequestMethod.GET, produces = {
			"application/json; charset=utf-8" })
	@ResponseBody	
	
	/**
	 * 分页查询Unit
	 * @return
	 */
	private PageResult getUnits_json(){

		List<TreeNode> list = iDictory.getAllUnit();
				
		//构建返回值
		PageResult ret = new PageResult(true,list,list.size());
		//System.out.println("----Units------"+ret);
		return ret;			
	}		
	
	/**
	 * 动态取得联通公司和部门
	 */
	@RequestMapping(value = "/getusers_json", method = RequestMethod.GET, produces = {
			"application/json; charset=utf-8" })
	@ResponseBody	
	
	/**
	 * 分页查询users
	 * @return
	 */
	

	private PageResult getUsers_json(String lx){

		//System.out.println("users========"+cacheManager.getCache("dictCache").get("users"));
		
		//建议用缓存处理
		List<KeyValue> list = iDictory.getUserByCom(lx);
				
		//构建返回值
		PageResult ret = new PageResult(true,list,list.size());
		//System.out.println("----users------"+ret);
		return ret;			
	}	
	
}
