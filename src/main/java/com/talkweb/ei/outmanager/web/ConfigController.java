package com.talkweb.ei.outmanager.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.talkweb.ei.di.common.PageResult;
import com.talkweb.ei.outmanager.dao.OrgMapper;
import com.talkweb.ei.outmanager.dao.OutUserMapper;
import com.talkweb.ei.outmanager.model.KeyValue;
import com.talkweb.ei.outmanager.model.KeyValueObj;
import com.talkweb.ei.outmanager.model.OutCompany;
import com.talkweb.ei.outmanager.model.OutCompanyExample;
import com.talkweb.ei.outmanager.model.OutContract;
import com.talkweb.ei.outmanager.model.OutContractExample;
import com.talkweb.ei.outmanager.model.TOutDict;
import com.talkweb.ei.outmanager.model.TreeNode;
import com.talkweb.ei.outmanager.service.IDataService;
import com.talkweb.ei.outmanager.service.IDictory;


/**
 * ��Ŀ�ֵ�����ñ��ȡ�ۺϷ����ṩ
 * @author zhq
 *
 */

@Controller
@RequestMapping("/config")
public class ConfigController {
	//ҵ�����һ��ͨ��������ʵ��
	@Autowired
	private IDataService IDataService;	
	
	
	@Autowired
	private IDictory iDictory;		
	
	
	@Autowired
	private OrgMapper orgMapper;
	
	
	@Autowired
	private OutUserMapper outUserMapper;	
	
	@Autowired
	private EhCacheCacheManager cacheManager;
	
	
	// ajax json
	@RequestMapping(value = "/companyjson", method = RequestMethod.GET, produces = {
			"application/json; charset=utf-8" })
	@ResponseBody	
	
	/**
	 * ��ҳ��ѯ��˾
	 * @param limit
	 * @param offset
	 * @param type
	 * @param unit
	 * @param name
	 * @return
	 */
	private PageResult getCompanyList(String lx){
		
		//��������
		OutCompanyExample sample = new OutCompanyExample();
		
		int total = IDataService.getCompanySize(sample);
		List<OutCompany> list = IDataService.getCompanyList(total, 0, sample);

		//��������ֵ
		PageResult ret = new PageResult(true,toKeyValueCom(list),total);
		System.out.println("----------"+ret);
		return ret;			
	}
	
	
	
	// ajax json
	@RequestMapping(value = "/contractcode_json", method = RequestMethod.GET, produces = {
			"application/json; charset=utf-8" })
	@ResponseBody	
	
	/**
	 * ��ҳ��ѯ��˾
	 * @param limit
	 * @param offset
	 * @param type
	 * @param unit
	 * @param name
	 * @return
	 */
	private PageResult contractcode_json(String lx){
		
		//��������
		OutContractExample sample = new OutContractExample();
		
		sample.setDistinct(true);
		
		int total = IDataService.getContractSize(sample);
		List<OutContract> list = IDataService.getContractList(total, 0, sample);
		
		
		//��������ֵ
		PageResult ret = new PageResult(true,toKeyValueCon(list),total);
		System.out.println("----------"+ret);
		return ret;			
	}	
	
	
	
	
	// ajax json
	@RequestMapping(value = "/dict_json", method = RequestMethod.GET, produces = {
			"application/json; charset=utf-8" })
	@ResponseBody	

	/**
	 * �ֵ��ѯ
	 * @param lx
	 * @return
	 */
	private PageResult getDictByLx(String lx){
		
		List<TOutDict> list = iDictory.getDirctByLx(lx);
				
		//��������ֵ
		PageResult ret = new PageResult(true,list,list.size());
		System.out.println("----dirc------"+ret);
		return ret;			
	}	
	
		
	
	/**
	 * ��ͬ����
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
	 * ��˾����
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
	 * ��̬ȡ����ͨ��˾�Ͳ���
	 */
	@RequestMapping(value = "/getunits_json", method = RequestMethod.GET, produces = {
			"application/json; charset=utf-8" })
	@ResponseBody	
	
	/**
	 * ��ҳ��ѯUnit
	 * @return
	 */
	@Cacheable(value="dictCache", key="units")
	private PageResult getUnits_json(){

		List<TreeNode> list = orgMapper.selectOrg();
				
		//��������ֵ
		PageResult ret = new PageResult(true,list,list.size());
		System.out.println("----Units------"+ret);
		return ret;			
	}		
	
	/**
	 * ��̬ȡ����ͨ��˾�Ͳ���
	 */
	@RequestMapping(value = "/getusers_json", method = RequestMethod.GET, produces = {
			"application/json; charset=utf-8" })
	@ResponseBody	
	
	/**
	 * ��ҳ��ѯusers
	 * @return
	 */
	
	@Cacheable(value="dictCache", key="users")
	private PageResult getUsers_json(String lx){

		//System.out.println("users========"+cacheManager.getCache("dictCache").get("users"));
		
		//�����û��洦��
		List<KeyValue> list = orgMapper.selectUser(lx);
				
		//��������ֵ
		PageResult ret = new PageResult(true,list,list.size());
		//System.out.println("----users------"+ret);
		return ret;			
	}	
	
}
