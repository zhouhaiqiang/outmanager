package com.talkweb.ei.outmanager.service.impl;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talkweb.ei.di.common.DateUtil;
import com.talkweb.ei.outmanager.dao.OutUserMapper;
import com.talkweb.ei.outmanager.model.OutContract;
import com.talkweb.ei.outmanager.model.OutUser;
import com.talkweb.ei.outmanager.model.OutUserExample;
import com.talkweb.ei.outmanager.service.IUserService;


@Service
public class UserServiceImpl implements IUserService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 注入Service依赖
	@Autowired
	private OutUserMapper outUserMapper;
	
	@Override
	public boolean auth(String userid, String pwd) {
		
		
		try {
		
			String dbpwd = outUserMapper.selectByPrimaryKey(userid).getPwd();
		
			//密码加密后比较

            //doxxxxxx
			
			
			
			
			if(dbpwd.equals(pwd)){
				return true;
			}
		
		
		} catch(Exception e) {
			e.printStackTrace();
			
		}
		logger.info("认证失败！");
		return false;
	}

	@Override
	public boolean importBase(List<String> exldata) {
		try {
			OutUser entity;
			
			for(String rowvalue:exldata){			
				//映射属性
				entity = mappingOutUser(rowvalue);			
				outUserMapper.insert(entity);	
	
			}	
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public boolean importFenpei(List<String> exldata) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean importJiaoyu(List<String> exldata) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean importZhiye(List<String> exldata) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean importZhuanye(List<String> exldata) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean importLaodong(List<String> exldata) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean importJiechu(List<String> exldata) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 0#2# 这样的字符映射到属性
	 * 映射属性值 原始价格表
	 * @param rowvalue
	 * @return
	 */
	private OutUser mappingOutUser(String rowvalue){
	    
	    
		//加上行符号
		rowvalue+="#end";
		
	    String cellvalues[] = rowvalue.split("#");
	    
	    //数据不合规范
	    if(cellvalues==null){	        
	        return null;
	    }
	    
	    
	    OutUser  entity = new OutUser();
	    
	    //`id` VARCHAR(50) NOT NULL COMMENT 'id', 自动生成	 变更时    
	    
	    entity.setChangeModel(cellvalues[0]);
	    
	    if("修改".equals(cellvalues[0])){
	    	//校验关键字
	    	entity.setId(cellvalues[31]);  //用户模板中为什么没有校验位？？？？难道是code做唯一关键字去更新的？
	    } else {
	    	entity.setId(UUID.randomUUID().toString());
	    }
	    
	    

	    	   
	    //人员编号	姓名	身份证号码	性别
	    entity.setCode(cellvalues[1]);
	    entity.setName(cellvalues[2]);
	    entity.setIdnumber(cellvalues[3]);
	    entity.setSex(cellvalues[4]);
	    
	    
	    //人员类型	出生日期	政治面貌	国籍	    
	    entity.setContype(cellvalues[5]);	    
	    entity.setBirthday(cellvalues[6]);
	    entity.setZhengzhi(cellvalues[7]);	 //表扩充   
	    entity.setNationality(cellvalues[8]);
	    
	    
	    //电子邮件地址	户口类型	户口所在地	民族	    
	    entity.setMail(cellvalues[9]);
	    entity.setHukoutype(cellvalues[10]);
	    entity.setHukouaddress(cellvalues[11]);
	    entity.setMingz(cellvalues[12]);
	    
	    //移居国外者	进入外包单位日期	从事联通业务开始日期(不可修改)	分配开始日期
	    entity.setIsout(cellvalues[13]);
	    entity.setInworkdate(DateUtil.parseDate(cellvalues[14]));
	    entity.setInunicomdate(DateUtil.parseDate(cellvalues[15]));
	    entity.setFenpeidate(DateUtil.parseDate(cellvalues[16]));//扩表
	    
	    
	    //所属外包公司	所属外包合同编号	组织名称	从事外包业务类型
	    entity.setCompanyid(cellvalues[17]);
	    entity.setConcode(cellvalues[18]);
	    entity.setUnit(cellvalues[19]);
	    entity.setYwtype(cellvalues[20]);

	    //从事联通服务途径	增员途径说明	纳税地	社保缴纳地
	    entity.setYwtj(cellvalues[21]);
	    entity.setTjmark(cellvalues[22]);
	    entity.setNsaddress(cellvalues[23]);
	    entity.setSbaddress(cellvalues[24]);	    
	    
	    //岗位序列	岗位分类	参考岗级	考核信息	备用1	备用2
	    entity.setGwnumber(cellvalues[25]);
	    entity.setGwtype(cellvalues[26]);
	    entity.setGwdj(cellvalues[27]);
	    entity.setKaohei(cellvalues[28]);		    
	    
	    
	    entity.setBak1(cellvalues[29]);
	    entity.setBak2(cellvalues[30]);
	    
	    //操作的都是外包用户，联通用户为0
	    entity.setUsertype("1");
	    //初始密码
	    entity.setPwd("111111");
	    
	    return entity;
	    
	}

	@Override
	public int getUserSize(OutUserExample sample) {
		int ret = 0;
		try{
			ret = Integer.parseInt(outUserMapper.countByExample(sample)+"");
		} catch (Exception e) {
			return 0;
		}
		return  ret; 
	}

	@Override
	public List<OutUser> getUserList(OutUserExample sample) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OutUser> getUserList(int limit, int offset, OutUserExample sample) {
		
		
		sample.setLimit(offset+limit);
		sample.setOffset(offset+1);
		return outUserMapper.selectPageByExample(sample);				

	}

	@Override
	public OutUser getUser(String id) {	
		return outUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean updateUser(OutUser sample) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addUser(OutUser sample) {
		try {			
			outUserMapper.insert(sample);		
		
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public boolean deleteUser(OutUser sample) {
		try {			
			outUserMapper.deleteByPrimaryKey(sample.getId());		
		
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
