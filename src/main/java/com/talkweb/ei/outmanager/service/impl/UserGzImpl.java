package com.talkweb.ei.outmanager.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talkweb.ei.di.common.DateUtil;
import com.talkweb.ei.di.common.StringUtils;
import com.talkweb.ei.outmanager.dao.TOutGongziMapper;
import com.talkweb.ei.outmanager.dao.TOutJthyMapper;
import com.talkweb.ei.outmanager.dao.VOutUsergzMapper;
import com.talkweb.ei.outmanager.model.OutCompany;
import com.talkweb.ei.outmanager.model.OutUser;
import com.talkweb.ei.outmanager.model.OutUserExample;
import com.talkweb.ei.outmanager.model.TOutGongzi;
import com.talkweb.ei.outmanager.model.TOutJthy;
import com.talkweb.ei.outmanager.model.TOutUserFpinfo;
import com.talkweb.ei.outmanager.model.VOutUsergz;
import com.talkweb.ei.outmanager.model.VOutUsergzExample;
import com.talkweb.ei.outmanager.service.IUserGz;
import com.talkweb.ei.outmanager.service.IUserService;

@Service
public class UserGzImpl implements IUserGz {
	
	@Autowired
	private VOutUsergzMapper vOutUsergzMapper;
	
	@Autowired
	private TOutGongziMapper tOutGongziMapper;	
		
	@Autowired
	private TOutJthyMapper tOutJthyMapper;	
	
	@Autowired
	private IUserService  userService;

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

	@Override
	public boolean importGrhy(List<String> exldata) {
		try {
			TOutGongzi entity;
			
			for(String rowvalue:exldata){			
				//映射属性
				entity = mappingOutGrgongzi(rowvalue);	
				
				//修改用户
				if("修改".equals(entity.getChangeModel())){					
					tOutGongziMapper.updateByPrimaryKey(entity);					
				} else {
					tOutGongziMapper.insert(entity);							
				
				}
				
					
	
			}	
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public boolean importJthy(List<String> exldata) {
		try {
			TOutJthy entity;
			
			for(String rowvalue:exldata){			
				//映射属性
				
				entity = mappingOutJthy(rowvalue);	
				
				//修改用户
				if("修改".equals(entity.getChangeModel())){					
					tOutJthyMapper.updateByPrimaryKey(entity);					
				} else {
					
					if(entity!=null) {
						tOutJthyMapper.insert(entity);							
					}					
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
	private TOutGongzi mappingOutGrgongzi(String rowvalue){
	    
	    
		//加上行符号
		rowvalue+="#end";
		
	    String cellvalues[] = rowvalue.split("#");
	    
	    //数据不合规范
	    if(cellvalues==null){	        
	        return null;
	    }

	    TOutGongzi  entity = new TOutGongzi();
	    
	    //`id` VARCHAR(50) NOT NULL COMMENT 'id', 自动生成	 变更时    	    
	    entity.setChangeModel(cellvalues[0]);
	    
	    if("修改".equals(cellvalues[0])){
	    	//校验关键字
	    	entity.setId(cellvalues[32]);
	    } else {
	    	entity.setId(UUID.randomUUID().toString());
	    }

	    //操作模式	人员编号	姓名	身份证号码	归属外包公司名称	
	    
	    /*
	     * 人员基本信息不要重新导入,直接根据人员编号查找活动cellvalues【1】
	     */
	    
	    try {
	    	OutUser user = userService.getUserByCode(cellvalues[1]);
	    	entity.setUserid(user.getId());	
	    	entity.setCompanyid(user.getCompanyid());
	    //提供的关键字有在人员表中不存在	
		} catch (Exception e) {
			return null;
		}
	    
	    
	    //发薪月度	固定工资	绩效工资	津贴补贴	过节费
	    entity.setMonth(cellvalues[5]);
	    entity.setJiben(StringUtils.toBigDecimal(cellvalues[6]));
	    entity.setJixiao(StringUtils.toBigDecimal(cellvalues[7]));
	    entity.setJintie(StringUtils.toBigDecimal(cellvalues[8]));
	    entity.setGuojie(StringUtils.toBigDecimal(cellvalues[9]));
	    
	    
	    
	    //加班工资	其他工资性支出	应发金额	税前扣款项	税后扣款项
	    entity.setJiaban(StringUtils.toBigDecimal(cellvalues[10]));
	    entity.setQtgz(StringUtils.toBigDecimal(cellvalues[11]));
	    entity.setYfa(StringUtils.toBigDecimal(cellvalues[12]));
	    entity.setSxkk(StringUtils.toBigDecimal(cellvalues[13]));
	    entity.setShkk(StringUtils.toBigDecimal(cellvalues[14]));
        
       
        //社保公积金扣减额	个人所得税扣缴额	实发金额	养老保险	生育保险
	    entity.setGongji(StringUtils.toBigDecimal(cellvalues[15]));
	    entity.setGeren(StringUtils.toBigDecimal(cellvalues[16]));
	    entity.setShifa(StringUtils.toBigDecimal(cellvalues[17]));
	    entity.setYanglao(StringUtils.toBigDecimal(cellvalues[18]));
	    entity.setShengyu(StringUtils.toBigDecimal(cellvalues[19]));
	
        
        //失业保险	医疗保险	工伤保险	公积金	小计	
	    entity.setShiye(StringUtils.toBigDecimal(cellvalues[20]));
	    entity.setYiliao(StringUtils.toBigDecimal(cellvalues[21]));
	    entity.setGongshang(StringUtils.toBigDecimal(cellvalues[22]));
	    entity.setGongji(StringUtils.toBigDecimal(cellvalues[23]));
	    //entity.setXiaoji(StringUtils.toBigDecimal(cellvalues[24]));	    
 
        
        //为本企业服务起始日期	在本企业缴纳社会保险起始日期	工会会费	管理费	税金	其他人工支出项目	备注        
	    entity.setStartfwdate(DateUtil.parseDate(cellvalues[25]));
	    entity.setStartbxdate(DateUtil.parseDate(cellvalues[26]));
       
	    entity.setGonghui(StringUtils.toBigDecimal(cellvalues[27]));
	    entity.setGuanli(StringUtils.toBigDecimal(cellvalues[28]));
	    entity.setShuijin(StringUtils.toBigDecimal(cellvalues[29]));
	    
	    entity.setQtjine(StringUtils.toBigDecimal(cellvalues[30]));
	    entity.setRemark(cellvalues[31]);

	    return entity;
	    
	}

	
	
	
	
	/**
	 * 0#2# 这样的字符映射到属性
	 * 映射属性值 原始价格表
	 * @param rowvalue
	 * @return
	 */
	private TOutJthy mappingOutJthy(String rowvalue){
	    
	    
		//加上行符号
		rowvalue+="#end";
		
	    String cellvalues[] = rowvalue.split("#");
	    
	    //数据不合规范
	    if(cellvalues==null){	        
	        return null;
	    }

	    TOutJthy  entity = new TOutJthy();
	    
	    //`id` VARCHAR(50) NOT NULL COMMENT 'id', 自动生成	 变更时    	    
	    entity.setChangeModel(cellvalues[0]);
	    
	    if("修改".equals(cellvalues[0])){
	    	//校验关键字
	    	entity.setId(cellvalues[11]);
	    } else {
	    	entity.setId(UUID.randomUUID().toString());
	    }

	    
	    //操作模式	发生费用所属组织	外包公司名称	外包合同编号	期间	
	    entity.setUnit(cellvalues[1]);
	    entity.setCompanyid(cellvalues[2]);
	    entity.setConcode(cellvalues[3]);
	    entity.setMonth(cellvalues[4]);
	    
	    //工会会费	管理费	税金	其他人工支出项目	离职前人工费用	备注
	    entity.setGhhy(StringUtils.toBigDecimal(cellvalues[5]));
	    entity.setGlh(StringUtils.toBigDecimal(cellvalues[6]));
	    entity.setShuijin(StringUtils.toBigDecimal(cellvalues[7]));
	    entity.setQt(StringUtils.toBigDecimal(cellvalues[8]));
	    entity.setLzrhy(StringUtils.toBigDecimal(cellvalues[9]));
	    entity.setRemark(cellvalues[10]);
	    return entity;
	    
	}	
	

}
