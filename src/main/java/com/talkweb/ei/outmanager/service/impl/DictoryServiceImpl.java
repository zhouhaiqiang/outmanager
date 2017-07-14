package com.talkweb.ei.outmanager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.talkweb.ei.di.common.StringUtils;
import com.talkweb.ei.outmanager.dao.OrgMapper;
import com.talkweb.ei.outmanager.dao.TOutDictMapper;
import com.talkweb.ei.outmanager.dao.TOutDutyMapper;
import com.talkweb.ei.outmanager.model.KeyValue;
import com.talkweb.ei.outmanager.model.TOutDict;
import com.talkweb.ei.outmanager.model.TOutDictExample;
import com.talkweb.ei.outmanager.model.TOutDuty;
import com.talkweb.ei.outmanager.model.TOutDutyExample;
import com.talkweb.ei.outmanager.model.TreeNode;
import com.talkweb.ei.outmanager.service.IDictory;


@Service
public class DictoryServiceImpl implements IDictory {
	
	// 注入Service依赖
	@Autowired	
	TOutDictMapper outDictMapper;
	
	
	@Autowired
	private OrgMapper orgMapper;
	
	
	@Autowired
	private TOutDutyMapper tOutDutyMapper;//职务
	
	


	@Override
	public List<TOutDict> getDirctList(TOutDictExample sample) {		
		return outDictMapper.selectByExample(sample);
	}

	@Override
	@Cacheable(value="dictCache", key="#lx")
	public List<TOutDict> getDirctByLx(String lx) {
		TOutDictExample sample = new TOutDictExample();
		TOutDictExample.Criteria criteria = sample.createCriteria();
		
		//字典大类
		criteria.andLxEqualTo(lx);		
		return outDictMapper.selectByExample(sample);
	}

	@Override
	@Cacheable(value="dictCache", key="#companyname")
	public List<KeyValue> getUserByCom(String companyname) {
		
		return orgMapper.selectUser(companyname);
	}

	@Override
	@Cacheable(value="dictCache", key="#root.targetClass + #root.methodName")
	public List<TreeNode> getAllUnit() {
		return orgMapper.selectOrg();
	}

	@Override
	@Cacheable(value="dictCache", key="#root.targetClass + #root.methodName")
	public List<TOutDuty> getAllDuty() {
	
		return  tOutDutyMapper.selectByExample(null);
	}

	@Override
	@Cacheable(value="dictCache", key="#root.targetClass + #root.methodName  + #name")
	public String getUnitNameByName(String name) {
		List<TreeNode> suborgs =  orgMapper.getSubOrgByName(name);
		
		String names = "";
		for (TreeNode treeNode : suborgs) {
			
			if(StringUtils.isEmpty(names)) {
				names+="/"+treeNode.getName()+"/";
			} else {
				names+=treeNode.getName()+"/";
			}
			
			
		}		
		return names;
	}

	



	@Override
	@Cacheable(value="dictCache", key="#root.targetClass + #root.methodName + #orgid")
	public String getUnitNameById(String orgid) {
		List<TreeNode> suborgs =  orgMapper.getSubOrgByID(orgid);
		
		String names = "";
		for (TreeNode treeNode : suborgs) {
			
			if(StringUtils.isEmpty(names)) {
				names+="/"+treeNode.getName()+"/";
			} else {
				names+=treeNode.getName()+"/";
			}
			
			
		}		
		return names;
	}

	
	
	@Override
	public String getUnitIDByName(String name) {
		      
		//查找匹配的ID
		List<TreeNode> tmpuserlist = orgMapper.getOrgByName(name);	
		
		if(tmpuserlist!=null && tmpuserlist.size()>0){
			return tmpuserlist.get(0).getId();
		}
		return null;
		
	}

	@Override
	public String getDutyIDByName(String name) {
				
		TOutDutyExample sample = new TOutDutyExample();
		TOutDutyExample.Criteria criteria = sample.createCriteria();
		
		//字典大类
		criteria.andNameEqualTo(name);		
		List<TOutDuty> list =  tOutDutyMapper.selectByExample(sample);
		
		if(list!=null && list.size()>0){
			return list.get(0).getId();
		}		
		return null;
		
	}
}
