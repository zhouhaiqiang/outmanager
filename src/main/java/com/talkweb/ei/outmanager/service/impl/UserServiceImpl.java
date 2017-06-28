package com.talkweb.ei.outmanager.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talkweb.ei.di.common.DateUtil;
import com.talkweb.ei.di.common.StringUtils;
import com.talkweb.ei.outmanager.dao.OutUserMapper;
import com.talkweb.ei.outmanager.dao.TOutUserFpinfoMapper;
import com.talkweb.ei.outmanager.dao.TOutUserHtMapper;
import com.talkweb.ei.outmanager.dao.TOutUserJcMapper;
import com.talkweb.ei.outmanager.dao.TOutUserJninfoMapper;
import com.talkweb.ei.outmanager.dao.TOutUserJyinfoMapper;
import com.talkweb.ei.outmanager.dao.TOutUserZyinfoMapper;
import com.talkweb.ei.outmanager.model.OutUser;
import com.talkweb.ei.outmanager.model.OutUserExample;
import com.talkweb.ei.outmanager.model.TOutUserFpinfo;
import com.talkweb.ei.outmanager.model.TOutUserHt;
import com.talkweb.ei.outmanager.model.TOutUserJc;
import com.talkweb.ei.outmanager.model.TOutUserJninfo;
import com.talkweb.ei.outmanager.model.TOutUserJyinfo;
import com.talkweb.ei.outmanager.model.TOutUserZyinfo;
import com.talkweb.ei.outmanager.service.IUserService;
import com.talkweb.ei.shiro.ShiroToken;


@Service
public class UserServiceImpl implements IUserService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 注入Service依赖
	@Autowired
	private OutUserMapper outUserMapper;
	
	
	@Autowired
	private TOutUserFpinfoMapper tOutUserFpinfoMapper;

	@Autowired
	private TOutUserHtMapper tOutUserHtMapper;
	
	@Autowired
	private TOutUserJcMapper tOutUserJcMapper;	
	
	@Autowired
	private TOutUserJyinfoMapper tOutUserJyinfoMapper;
	
	@Autowired
	private TOutUserJninfoMapper tOutUserJninfoMapper;	
	
	@Autowired
	private TOutUserZyinfoMapper tOutUserZyinfoMapper;	

	@Override
	public boolean auth(String userid, String pwd) {
		
		
		try {

			ShiroToken token = new ShiroToken(userid, pwd);
            token.setRememberMe(false);           
            //登录
            SecurityUtils.getSubject().login(token);
            
            
			return true;
		
		} catch(Exception e) {
			e.printStackTrace();	
		}
		logger.info("认证失败！");
		return false;
	}
	
	

	@Override
	public boolean logout() {
		try {
            //登出
            SecurityUtils.getSubject().logout();
			return true;
		
		} catch(Exception e) {
			e.printStackTrace();	
		}
		logger.info("登出失败");
		return false;
	}	

	@Override
	public boolean importBase(List<String> exldata) {
		try {
			OutUser entity;
			
			for(String rowvalue:exldata){			
				//映射属性
				
				//分配信息
				TOutUserFpinfo fpinfo = new TOutUserFpinfo();
				
				entity = mappingOutUser(rowvalue, fpinfo);	
				
				//修改用户
				if("修改".equals(entity.getChangeModel())){
					
					outUserMapper.updateByPrimaryKey(entity);
					
				} else {
					
					if(entity!=null) {
						outUserMapper.insert(entity);	
						if(fpinfo!=null) {
							tOutUserFpinfoMapper.insert(fpinfo);					
						}
					}					
				}
				
					
	
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
		
		
		try {
			TOutUserJyinfo entity;
			
			for(String rowvalue:exldata){			
				//映射属性
			
				entity = mappingOutJiaoy(rowvalue);	
				
				//映射文件没问题
				if(entity!=null){
					//修改用户
					if("修改".equals(entity.getChangeModel())){
						
						tOutUserJyinfoMapper.updateByPrimaryKey(entity);
						
					} else {
						
						if(entity!=null) {
							tOutUserJyinfoMapper.insert(entity);	
							
						}					
					}
				} else {
					logger.info("数据映射有问题，或者更新时没找到对应的数据==========="+entity);
				}
	
			}	
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public boolean importZhiye(List<String> exldata) {
		
		
		
		try {
			TOutUserJninfo entity;
			
			for(String rowvalue:exldata){			
				//映射属性
			
				entity = mappingOutJineng(rowvalue);	
				
				//映射文件没问题
				if(entity!=null){
					//修改用户
					if("修改".equals(entity.getChangeModel())){
						
						tOutUserJninfoMapper.updateByPrimaryKey(entity);
						
					} else {
						
						if(entity!=null) {
							tOutUserJninfoMapper.insert(entity);	
							
						}					
					}
				} else {
					logger.info("数据映射有问题，或者更新时没找到对应的数据==========="+entity);
				}
	
			}	
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	
	/**
	 * 技能对象映射
	 * @param rowvalue
	 * @return
	 */
	private TOutUserJninfo mappingOutJineng(String rowvalue) {
	    
	    
		//加上行符号
		rowvalue+="#end";
		
	    String cellvalues[] = rowvalue.split("#");
	    
	    //数据不合规范
	    if(cellvalues==null){	        
	        return null;
	    }
	    
	    
	    TOutUserJninfo  entity = new TOutUserJninfo();
	    
	    //`id` VARCHAR(50) NOT NULL COMMENT 'id', 自动生成	 变更时    
	    
	    entity.setChangeModel(cellvalues[0]);
	    
	    
	    //查找对应用户
    	try {
	    	
    		OutUser tmpuser = getUserByCode(cellvalues[1]);
	    	entity.setUserid(tmpuser.getId()); 
    	} catch(Exception e){
    		
    		logger.error("更新的用户对象不存在！！！"+cellvalues[1]);	    		
    		return null;
    		
    	}	    
	    
	    
	    
	    if("修改".equals(cellvalues[0])){
	    	//校验关键字
	    	entity.setId(cellvalues[11]);	
	    } else {    	
	    	entity.setId(UUID.randomUUID().toString()); 
	    }
	    
	    
		 //人员编号	姓名	认定开始日期	认定终止日期	认定单位
	    entity.setStartdate(DateUtil.parseDate(cellvalues[4]));
	    entity.setEnddate(DateUtil.parseDate(cellvalues[5]));
	    entity.setRdunit(cellvalues[6]);

        //认定技能资格名称	认定技能资格等级	其他请注明	是否主要认定
	    entity.setRdname(cellvalues[7]);
	    entity.setRddengji(cellvalues[8]);
	    entity.setQt(cellvalues[9]);
	    entity.setIsmain(cellvalues[10]);


	    return entity;
	}

	@Override
	public boolean importZhuanye(List<String> exldata) {
		try {
			TOutUserZyinfo entity;
			
			for(String rowvalue:exldata){			
				//映射属性
			
				entity = mappingOutZhiye(rowvalue);	
				
				//映射文件没问题
				if(entity!=null){
					//修改用户
					if("修改".equals(entity.getChangeModel())){
						
						tOutUserZyinfoMapper.updateByPrimaryKey(entity);
						
					} else {
						
						if(entity!=null) {
							tOutUserZyinfoMapper.insert(entity);	
							
						}					
					}
				} else {
					logger.info("数据映射有问题，或者更新时没找到对应的数据==========="+entity);
				}
	
			}	
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	/**
	 * 技能专业信息
	 * @param rowvalue
	 * @return
	 */
	private TOutUserZyinfo mappingOutZhiye(String rowvalue) {
		//加上行符号
		rowvalue+="#end";
		
	    String cellvalues[] = rowvalue.split("#");
	    
	    //数据不合规范
	    if(cellvalues==null){	        
	        return null;
	    }
	    
	    
	    TOutUserZyinfo  entity = new TOutUserZyinfo();
	    
	    //`id` VARCHAR(50) NOT NULL COMMENT 'id', 自动生成	 变更时    
	    
	    entity.setChangeModel(cellvalues[0]);
	    
	    
	    //查找对应用户
    	try {
	    	
    		OutUser tmpuser = getUserByCode(cellvalues[1]);
	    	entity.setUserid(tmpuser.getId()); 
    	} catch(Exception e){
    		
    		logger.error("更新的用户对象不存在！！！"+cellvalues[1]);	    		
    		return null;
    		
    	}	    
	    
	    
	    
	    if("修改".equals(cellvalues[0])){
	    	//校验关键字
	    	entity.setId(cellvalues[16]);	
	    } else {    	
	    	entity.setId(UUID.randomUUID().toString()); 
	    }
	    
	    
	    //人员编号	姓名	专业技术资格序列	专业技术资格名称	专业分类
	    entity.setXulie(cellvalues[4]);
	    entity.setName(cellvalues[5]);
	    entity.setZytype(cellvalues[6]);
	    

        //专业子分类	其它请注明	专业技术资格证书编号  取得资格日期
	    entity.setSubtype(cellvalues[7]);
	    entity.setQt(cellvalues[8]);
	    entity.setZsnumber(cellvalues[9]);
	    entity.setGotdate(DateUtil.parseDate(cellvalues[10]));
	

        //取得资格途径	专业技术资格等级	到期日	资格授予单位	是否主要专业技术资格 
	    entity.setGotway(cellvalues[11]);
	    entity.setDengji(cellvalues[12]);
	    entity.setOutdate(DateUtil.parseDate(cellvalues[13]));
	    entity.setShareunit(cellvalues[14]);
	    entity.setIsmain(cellvalues[15]);
	    
	    
	    return entity;
	}

	@Override
	public boolean importLaodong(List<String> exldata) {
		try {
			TOutUserHt entity;
			
			for(String rowvalue:exldata){			
				//映射属性
			
				entity = mappingOutLaodong(rowvalue);	
				
				//映射文件没问题
				if(entity!=null){
					//修改用户
					if("修改".equals(entity.getChangeModel())){
						
						tOutUserHtMapper.updateByPrimaryKey(entity);
						
					} else {
						
						if(entity!=null) {
							tOutUserHtMapper.insert(entity);	
							
						}					
					}
				} else {
					logger.info("数据映射有问题，或者更新时没找到对应的数据==========="+entity);
				}
	
			}	
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	
	/**
	 * 劳动合同信息映射
	 * @param rowvalue
	 * @return
	 */
	private TOutUserHt mappingOutLaodong(String rowvalue) {
		//加上行符号
		rowvalue+="#end";
		
	    String cellvalues[] = rowvalue.split("#");
	    
	    //数据不合规范
	    if(cellvalues==null){	        
	        return null;
	    }
	    
	    
	    TOutUserHt  entity = new TOutUserHt();
	    
	    //`id` VARCHAR(50) NOT NULL COMMENT 'id', 自动生成	 变更时    
	    
	    entity.setChangeModel(cellvalues[0]);
	    
	    
	    //查找对应用户
    	try {
	    	
    		OutUser tmpuser = getUserByCode(cellvalues[1]);
	    	entity.setUserid(tmpuser.getId()); 
    	} catch(Exception e){
    		
    		logger.error("更新的用户对象不存在！！！"+cellvalues[1]);	    		
    		return null;
    		
    	}	    
	    
	    
	    
	    if("修改".equals(cellvalues[0])){
	    	//校验关键字
	    	entity.setId(cellvalues[15]);	
	    } else {    	
	    	entity.setId(UUID.randomUUID().toString()); 
	    }
	    
	    
	    //合同类型	劳动合同编号	合同期限类型
	    entity.setContype(cellvalues[4]);
	    entity.setConnumber(cellvalues[5]);
	    entity.setConqxtype(cellvalues[6]);
		 
	    //劳动合同起始日期	劳动合同终止日期	劳动合同状态	劳动合同期限	劳动合同签订单位	
	    entity.setStartdate(DateUtil.parseDate(cellvalues[7]));
	    entity.setEnddate(DateUtil.parseDate(cellvalues[8]));
	    
	    entity.setConstatus(cellvalues[9]);
	    entity.setQixian(cellvalues[10]);
	    entity.setUnit(cellvalues[11]);

	    //劳务合同签订的甲方公司	对应劳务合同编号	劳务合同名称
	    entity.setUnit(cellvalues[12]);
	    entity.setNwconnumber(cellvalues[13]);
	    entity.setLwconname(cellvalues[14]);	
	    
	    return entity;
	
	}

	@Override
	public boolean importJiechu(List<String> exldata) {
		try {
			TOutUserJc entity;
			
			for(String rowvalue:exldata){			
				//映射属性
			
				entity = mappingOutJiechu(rowvalue);	
				
				//映射文件没问题
				if(entity!=null){
					//修改用户
					if("修改".equals(entity.getChangeModel())){
						
						tOutUserJcMapper.updateByPrimaryKey(entity);
						
					} else {
						
						if(entity!=null) {
							tOutUserJcMapper.insert(entity);	
							
						}					
					}
				} else {
					logger.info("数据映射有问题，或者更新时没找到对应的数据==========="+entity);
				}
	
			}	
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * 劳动关系解除
	 * @param rowvalue
	 * @return
	 */
	private TOutUserJc mappingOutJiechu(String rowvalue) {
		//加上行符号
		rowvalue+="#end";
		
	    String cellvalues[] = rowvalue.split("#");
	    
	    //数据不合规范
	    if(cellvalues==null){	        
	        return null;
	    }
	    
	    
	    TOutUserJc  entity = new TOutUserJc();
	    
	    //`id` VARCHAR(50) NOT NULL COMMENT 'id', 自动生成	 变更时    
	    
	    entity.setChangeModel(cellvalues[0]);
	    
	    
	    //查找对应用户
    	try {
	    	
    		OutUser tmpuser = getUserByCode(cellvalues[1]);
	    	entity.setUserid(tmpuser.getId()); 
    	} catch(Exception e){
    		
    		logger.error("更新的用户对象不存在！！！"+cellvalues[1]);	
    		//删除的对象，保存记录
    		entity.setUserid(cellvalues[1]); 
    		//return null;
    		
    	}	    
	    
	    
	    
	    if("修改".equals(cellvalues[0])){
	    	//校验关键字
	    	entity.setId(cellvalues[8]);	
	    } else {    	
	    	entity.setId(UUID.randomUUID().toString()); 
	    }
	    
	    
	    //解除原因	解除外包关系日期	费用最终日期	途径说明（新外包公司名称）
	    entity.setJcreason(cellvalues[4]);
	    entity.setJcdate((DateUtil.parseDate(cellvalues[5])));
	    entity.setGzenddate((DateUtil.parseDate(cellvalues[6])));
	    entity.setQt(cellvalues[7]);
	 	
	    
	    return entity;
	}

	/**
	 * 0#2# 这样的字符映射到属性
	 * 映射属性值 原始价格表
	 * @param rowvalue
	 * @param outFenPei 分配信息在用户base信息中取出
	 * @return
	 */
	private OutUser mappingOutUser(String rowvalue,TOutUserFpinfo outFenPei){
	    
	    
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
	    	//根据code取id然后更新
	    	
	    	try {
		    	OutUserExample example = new OutUserExample();
		    	
		    	OutUserExample.Criteria criteria = example.createCriteria();
				
				//只查询当前用户
				criteria.andCodeEqualTo(cellvalues[1]);
		    	
		    	example.createCriteria();		    	
		    	List<OutUser> tmpuserlist = outUserMapper.selectByExample(example);		    	
		    	entity.setId(tmpuserlist.get(0).getId()); 
		    	
			    //操作的都是外包用户，联通用户为0
			    entity.setUsertype(tmpuserlist.get(0).getUsertype());
			    
			    //密码保持不变
			    entity.setPwd(tmpuserlist.get(0).getPwd());
		    	
		    	
	    	} catch(Exception e){
	    		
	    		logger.error("更新的用户对象不存在！！！"+cellvalues[1]);	    		
	    		return null;
	    		
	    	}
	    	

	    	
	    } else {
	    	
	    	String userid = UUID.randomUUID().toString();
	    	entity.setId(userid);
	    	
	    	//没有给编号的自动生成编号
	    	if(StringUtils.isEmpty(cellvalues[1])){
	    		entity.setCode("SX" +new Date().getTime()); //编号自动生成
	    	} else {
	    		entity.setCode(cellvalues[1]);
	    	}
	    
		    //初始密码
		    entity.setPwd("111111");
		    //操作的都是外包用户，联通用户为0
		    entity.setUsertype("1");
		    
		    
		    //有分配西西
		    if(cellvalues[25]!=null&&!"".equals(cellvalues[25].trim())) {
		    	
			    //剥离分配信息
			    if(outFenPei==null) outFenPei = new TOutUserFpinfo();
			    
			    outFenPei.setId(UUID.randomUUID().toString());
			    outFenPei.setUserid(userid);
			    
			    outFenPei.setGw(cellvalues[25]);
			    outFenPei.setGwtype(cellvalues[26]);
			    outFenPei.setGwjb(cellvalues[27]);
			    outFenPei.setKaohe(cellvalues[28]);
			    
//			    outFenPei.setQt1(cellvalues[29]);	 //老系统导出数据错误		    
//			    outFenPei.setQt2(cellvalues[30]);
			    
			    outFenPei.setFzr(cellvalues[29]);
			    outFenPei.setIszuixiao(cellvalues[30]);
			    
			    outFenPei.setCompany(cellvalues[17]);
			    outFenPei.setConnumber(cellvalues[18]);
			    
			    outFenPei.setStartdate(DateUtil.parseDate(cellvalues[16]));
			    
			    //服务部门
			    outFenPei.setFwdept(cellvalues[19]);
			    
		    }
		    
	    }
	    
	    

	    	   
	    //人员编号	姓名	身份证号码	性别	    
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
	    

	    return entity;
	    
	}
	
	
	
	
	/**
	 * 0#2# 这样的字符映射到属性
	 * 映射属性值 原始价格表
	 * @param rowvalue
	 * @param outFenPei 分配信息在用户base信息中取出
	 * @return
	 */
	private TOutUserJyinfo mappingOutJiaoy(String rowvalue){
	    
	    
		//加上行符号
		rowvalue+="#end";
		
	    String cellvalues[] = rowvalue.split("#");
	    
	    //数据不合规范
	    if(cellvalues==null){	        
	        return null;
	    }
	    
	    
	    TOutUserJyinfo  entity = new TOutUserJyinfo();
	    
	    //`id` VARCHAR(50) NOT NULL COMMENT 'id', 自动生成	 变更时    
	    
	    entity.setChangeModel(cellvalues[0]);
	    
	    
	    //查找对应用户
    	try {
	    	
    		OutUser tmpuser = getUserByCode(cellvalues[1]);
	    	entity.setUserid(tmpuser.getId()); 
    	} catch(Exception e){
    		
    		logger.error("更新的用户对象不存在！！！"+cellvalues[1]);	    		
    		return null;
    		
    	}	    
	    
	    
	    
	    if("修改".equals(cellvalues[0])){
	    	//校验关键字
	    	entity.setId(cellvalues[28]);	
	    } else {    	
	    	entity.setId(UUID.randomUUID().toString()); 
	    }
	    
	    
		 //人员编号	姓名	学校	入学时间	毕业时间
	    entity.setSchool(cellvalues[4]);
	    entity.setStartdate(DateUtil.parseDate(cellvalues[5]));
	    entity.setEnddate(DateUtil.parseDate(cellvalues[6]));


        //学历	学历证书编号	是否最高学历	是否全日制学历	学位
	    entity.setXueli(cellvalues[7]);
	    entity.setXuelizsnumber(cellvalues[8]);
	    entity.setIsmaxxl(cellvalues[9]);
	    entity.setIsqrz(cellvalues[10]);
	    entity.setXuewei(cellvalues[11]);
	    
        
        
        //第一学位类别	第二学位类别	学位授予日期	学位授予单位	学位证书编号	是否最高学位
	    entity.setD1xwtype(cellvalues[12]);
	    entity.setD2xwtype(cellvalues[13]);
	    entity.setXwdate(DateUtil.parseDate(cellvalues[14]));
	    entity.setXwunit(cellvalues[15]);
	    entity.setXwzsnumber(cellvalues[16]);
	    entity.setIsmaxxw(cellvalues[17]);
	  
        //同等学历	相当毕业	专业类别	专业子类别	第一专业	第二专业
	    entity.setTdxl(cellvalues[18]);
	    entity.setXdby(cellvalues[19]);
	    entity.setZytype(cellvalues[20]);
	    entity.setZysubtype(cellvalues[21]);
	    entity.setD1zy(cellvalues[22]);
	    entity.setD2zy(cellvalues[23]);
	    
        
        
        //学制	学习形式	学习情况	备注
	    entity.setXuezhi(cellvalues[24]);
	    entity.setXxxs(cellvalues[25]);
	    entity.setXxqk(cellvalues[26]);
	    entity.setQt(cellvalues[27]);

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
	
	
	/**
	 * 根据用户唯一关键字取用户
	 * @param code
	 * @return
	 */
	@Override
	public OutUser getUserByCode(String code) {
		OutUserExample example = new OutUserExample();
		
		OutUserExample.Criteria criteria = example.createCriteria();
		
		//只查询当前用户
		criteria.andCodeEqualTo(code);
		
		example.createCriteria();		    	
		List<OutUser> tmpuserlist = outUserMapper.selectByExample(example);	
		
		if(tmpuserlist!=null && tmpuserlist.size()>0){
			return tmpuserlist.get(0);
		}
		return null;
	}


}
