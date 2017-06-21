package com.talkweb.ei.outmanager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.talkweb.ei.outmanager.dao.OrgMapper;
import com.talkweb.ei.outmanager.dao.TOutDictMapper;
import com.talkweb.ei.outmanager.model.KeyValue;
import com.talkweb.ei.outmanager.model.TOutDict;
import com.talkweb.ei.outmanager.model.TOutDictExample;
import com.talkweb.ei.outmanager.model.TreeNode;
import com.talkweb.ei.outmanager.service.IDictory;


@Service
public class DictoryServiceImpl implements IDictory {
	
	// ע��Service����
	@Autowired	
	TOutDictMapper outDictMapper;
	
	
	@Autowired
	private OrgMapper orgMapper;


	@Override
	public List<TOutDict> getDirctList(TOutDictExample sample) {		
		return outDictMapper.selectByExample(sample);
	}

	@Override
	@Cacheable(value="dictCache", key="#lx")
	public List<TOutDict> getDirctByLx(String lx) {
		TOutDictExample sample = new TOutDictExample();
		TOutDictExample.Criteria criteria = sample.createCriteria();
		
		//�ֵ����
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

}
