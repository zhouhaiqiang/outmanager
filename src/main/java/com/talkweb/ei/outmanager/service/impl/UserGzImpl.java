package com.talkweb.ei.outmanager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talkweb.ei.outmanager.dao.VOutUsergzMapper;
import com.talkweb.ei.outmanager.model.VOutUsergz;
import com.talkweb.ei.outmanager.model.VOutUsergzExample;
import com.talkweb.ei.outmanager.service.IUserGz;

@Service
public class UserGzImpl implements IUserGz {
	
	@Autowired
	private VOutUsergzMapper vOutUsergzMapper;

	@Override
	public int getUserGzSize(VOutUsergzExample sample) {
		int ret = 0;
		try{
			ret = Integer.parseInt(vOutUsergzMapper.countByExample(sample)+"");
		} catch (Exception e) {
			return 0;
		}
		return  ret; 
	}

	@Override
	public List<VOutUsergz> getUserGzList(int limit, int offset, VOutUsergzExample sample) {
		sample.setLimit(offset+limit);
		sample.setOffset(offset+1);
		return vOutUsergzMapper.selectPageByExample(sample);		
	}

}
