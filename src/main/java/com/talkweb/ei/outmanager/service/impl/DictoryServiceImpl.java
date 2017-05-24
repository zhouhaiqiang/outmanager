package com.talkweb.ei.outmanager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talkweb.ei.outmanager.dao.TOutDictMapper;
import com.talkweb.ei.outmanager.model.TOutDict;
import com.talkweb.ei.outmanager.model.TOutDictExample;
import com.talkweb.ei.outmanager.service.IDictory;


@Service
public class DictoryServiceImpl implements IDictory {
	
	// ◊¢»ÎService“¿¿µ
	@Autowired	
	TOutDictMapper outDictMapper;

	@Override
	public List<TOutDict> getDirctList(TOutDictExample sample) {		
		return outDictMapper.selectByExample(sample);
	}

	@Override
	public List<TOutDict> getDirctByLx(String lx) {
		TOutDictExample sample = new TOutDictExample();
		TOutDictExample.Criteria criteria = sample.createCriteria();
		
		//◊÷µ‰¥Û¿‡
		criteria.andLxEqualTo(lx);		
		return outDictMapper.selectByExample(sample);
	}

}
