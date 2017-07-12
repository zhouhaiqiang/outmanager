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
			//分别处理没有用户
			for (OutUser outUser : list) {
				
				record = new TOutAction();
				record.setId(UUID.randomUUID().toString());			
				record.setUserid(outUser.getId());
				
				record.setYwline(userAction.getYwline());
				record.setYwaction(userAction.getYwaction());			
				record.setStartdate(userAction.getStartdate());	
				
				//默认【是】
				record.setIscb("是");
				
				
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
				//映射属性
				entity = mappingOutAction(rowvalue);
				
				//数据不合法
				if(entity==null) continue;
				
				//修改用户
				if("修改".equals(entity.getChangeModel())){					
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
	 * 0#2# 这样的字符映射到属性
	 * 映射属性值 原始价格表
	 * @param rowvalue
	 * @return
	 */
	private TOutAction mappingOutAction(String rowvalue){
	    
		System.out.println("mappingOutAction==========="+rowvalue);
		//加上行符号
		rowvalue+="#end";
		
	    String cellvalues[] = rowvalue.split("#");
	    
	    //数据不合规范关键字没有
	    if(cellvalues==null||"".equals(cellvalues[2])){	        
	        return null;
	    }

	    TOutAction  entity = new TOutAction();
	    
	    //操作模式	组织	人员编号	姓名	业务线 	业务活动	是否计入成本	开始日期	校验标识    
	    entity.setChangeModel(cellvalues[0]);
	    
	    if("修改".equals(cellvalues[0])){
	    	//校验关键字
	    	entity.setId(cellvalues[8]);
	    } else {
	    	entity.setId(UUID.randomUUID().toString());
	    }

	    
	    
	    
	    //操作模式	发生费用所属组织	外包公司名称	外包合同编号	期间	
	    entity.setUserid(userService.getUserByCode(cellvalues[2]).getId());
	    
	    
	    
	    entity.setYwline(cellvalues[4]);
	    entity.setYwaction(cellvalues[5]);
	    entity.setIscb(cellvalues[6]);
	    entity.setStartdate(DateUtil.parseDate(cellvalues[7]));

	    return entity;
	    
	}

}
