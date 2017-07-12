package com.talkweb.ei.outmanager.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.talkweb.ei.di.common.DateUtil;
import com.talkweb.ei.di.common.StringUtils;
import com.talkweb.ei.outmanager.dao.TOutActionMapper;
import com.talkweb.ei.outmanager.model.OutUser;
import com.talkweb.ei.outmanager.model.TOutAction;
import com.talkweb.ei.outmanager.model.TOutGongzi;
import com.talkweb.ei.outmanager.model.TOutJthy;
import com.talkweb.ei.outmanager.model.VOutUseraction;
import com.talkweb.ei.outmanager.service.IActionService;
import com.talkweb.ei.outmanager.service.IUserService;

@Service
public class ActionServiceImpl implements IActionService {
	
	
	
	@Autowired
	private TOutActionMapper tOutActionMapper;
	
	
	@Autowired
	private IUserService  userService;

	@Override
	@Transactional
	public boolean pCreateAction(List<OutUser> list, VOutUseraction userAction ) {
		
		TOutAction record;
		try {
			//�ֱ���û���û�
			for (OutUser outUser : list) {
				
				record = new TOutAction();
				record.setId(UUID.randomUUID().toString());			
				record.setUserid(outUser.getId());
				
				record.setYwline(userAction.getYwline());
				record.setYwaction(userAction.getYwaction());			
				record.setStartdate(userAction.getStartdate());	
				
				//Ĭ�ϡ��ǡ�
				record.setIscb("��");
				
				
				tOutActionMapper.insert(record);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}				
		return true;
	}

	@Override
	@Transactional
	public boolean importAction(List<String> exldata) {
		try {
			TOutAction entity;
			
			for(String rowvalue:exldata){			
				//ӳ������
				entity = mappingOutAction(rowvalue);
				
				//���ݲ��Ϸ�
				if(entity==null) continue;
				
				//�޸��û�
				if("�޸�".equals(entity.getChangeModel())){					
					tOutActionMapper.updateByPrimaryKey(entity);					
				} else {
					tOutActionMapper.insert(entity);							
				
				}
				
					
	
			}	
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	
	
	/**
	 * 0#2# �������ַ�ӳ�䵽����
	 * ӳ������ֵ ԭʼ�۸��
	 * @param rowvalue
	 * @return
	 */
	private TOutAction mappingOutAction(String rowvalue){
	    
		System.out.println("mappingOutAction==========="+rowvalue);
		//�����з���
		rowvalue+="#end";
		
	    String cellvalues[] = rowvalue.split("#");
	    
	    //���ݲ��Ϲ淶�ؼ���û��
	    if(cellvalues==null||"".equals(cellvalues[2])){	        
	        return null;
	    }

	    TOutAction  entity = new TOutAction();
	    
	    //����ģʽ	��֯	��Ա���	����	ҵ���� 	ҵ��	�Ƿ����ɱ�	��ʼ����	У���ʶ    
	    entity.setChangeModel(cellvalues[0]);
	    
	    if("�޸�".equals(cellvalues[0])){
	    	//У��ؼ���
	    	entity.setId(cellvalues[8]);
	    } else {
	    	entity.setId(UUID.randomUUID().toString());
	    }

	    
	    
	    
	    //����ģʽ	��������������֯	�����˾����	�����ͬ���	�ڼ�	
	    entity.setUserid(userService.getUserByCode(cellvalues[2]).getId());
	    
	    
	    
	    entity.setYwline(cellvalues[4]);
	    entity.setYwaction(cellvalues[5]);
	    entity.setIscb(cellvalues[6]);
	    entity.setStartdate(DateUtil.parseDate(cellvalues[7]));

	    return entity;
	    
	}

}
