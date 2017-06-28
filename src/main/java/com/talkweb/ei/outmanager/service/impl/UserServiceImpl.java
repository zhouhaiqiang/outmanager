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
	
	// ע��Service����
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
            //��¼
            SecurityUtils.getSubject().login(token);
            
            
			return true;
		
		} catch(Exception e) {
			e.printStackTrace();	
		}
		logger.info("��֤ʧ�ܣ�");
		return false;
	}
	
	

	@Override
	public boolean logout() {
		try {
            //�ǳ�
            SecurityUtils.getSubject().logout();
			return true;
		
		} catch(Exception e) {
			e.printStackTrace();	
		}
		logger.info("�ǳ�ʧ��");
		return false;
	}	

	@Override
	public boolean importBase(List<String> exldata) {
		try {
			OutUser entity;
			
			for(String rowvalue:exldata){			
				//ӳ������
				
				//������Ϣ
				TOutUserFpinfo fpinfo = new TOutUserFpinfo();
				
				entity = mappingOutUser(rowvalue, fpinfo);	
				
				//�޸��û�
				if("�޸�".equals(entity.getChangeModel())){
					
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
				//ӳ������
			
				entity = mappingOutJiaoy(rowvalue);	
				
				//ӳ���ļ�û����
				if(entity!=null){
					//�޸��û�
					if("�޸�".equals(entity.getChangeModel())){
						
						tOutUserJyinfoMapper.updateByPrimaryKey(entity);
						
					} else {
						
						if(entity!=null) {
							tOutUserJyinfoMapper.insert(entity);	
							
						}					
					}
				} else {
					logger.info("����ӳ�������⣬���߸���ʱû�ҵ���Ӧ������==========="+entity);
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
				//ӳ������
			
				entity = mappingOutJineng(rowvalue);	
				
				//ӳ���ļ�û����
				if(entity!=null){
					//�޸��û�
					if("�޸�".equals(entity.getChangeModel())){
						
						tOutUserJninfoMapper.updateByPrimaryKey(entity);
						
					} else {
						
						if(entity!=null) {
							tOutUserJninfoMapper.insert(entity);	
							
						}					
					}
				} else {
					logger.info("����ӳ�������⣬���߸���ʱû�ҵ���Ӧ������==========="+entity);
				}
	
			}	
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	
	/**
	 * ���ܶ���ӳ��
	 * @param rowvalue
	 * @return
	 */
	private TOutUserJninfo mappingOutJineng(String rowvalue) {
	    
	    
		//�����з���
		rowvalue+="#end";
		
	    String cellvalues[] = rowvalue.split("#");
	    
	    //���ݲ��Ϲ淶
	    if(cellvalues==null){	        
	        return null;
	    }
	    
	    
	    TOutUserJninfo  entity = new TOutUserJninfo();
	    
	    //`id` VARCHAR(50) NOT NULL COMMENT 'id', �Զ�����	 ���ʱ    
	    
	    entity.setChangeModel(cellvalues[0]);
	    
	    
	    //���Ҷ�Ӧ�û�
    	try {
	    	
    		OutUser tmpuser = getUserByCode(cellvalues[1]);
	    	entity.setUserid(tmpuser.getId()); 
    	} catch(Exception e){
    		
    		logger.error("���µ��û����󲻴��ڣ�����"+cellvalues[1]);	    		
    		return null;
    		
    	}	    
	    
	    
	    
	    if("�޸�".equals(cellvalues[0])){
	    	//У��ؼ���
	    	entity.setId(cellvalues[11]);	
	    } else {    	
	    	entity.setId(UUID.randomUUID().toString()); 
	    }
	    
	    
		 //��Ա���	����	�϶���ʼ����	�϶���ֹ����	�϶���λ
	    entity.setStartdate(DateUtil.parseDate(cellvalues[4]));
	    entity.setEnddate(DateUtil.parseDate(cellvalues[5]));
	    entity.setRdunit(cellvalues[6]);

        //�϶������ʸ�����	�϶������ʸ�ȼ�	������ע��	�Ƿ���Ҫ�϶�
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
				//ӳ������
			
				entity = mappingOutZhiye(rowvalue);	
				
				//ӳ���ļ�û����
				if(entity!=null){
					//�޸��û�
					if("�޸�".equals(entity.getChangeModel())){
						
						tOutUserZyinfoMapper.updateByPrimaryKey(entity);
						
					} else {
						
						if(entity!=null) {
							tOutUserZyinfoMapper.insert(entity);	
							
						}					
					}
				} else {
					logger.info("����ӳ�������⣬���߸���ʱû�ҵ���Ӧ������==========="+entity);
				}
	
			}	
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	/**
	 * ����רҵ��Ϣ
	 * @param rowvalue
	 * @return
	 */
	private TOutUserZyinfo mappingOutZhiye(String rowvalue) {
		//�����з���
		rowvalue+="#end";
		
	    String cellvalues[] = rowvalue.split("#");
	    
	    //���ݲ��Ϲ淶
	    if(cellvalues==null){	        
	        return null;
	    }
	    
	    
	    TOutUserZyinfo  entity = new TOutUserZyinfo();
	    
	    //`id` VARCHAR(50) NOT NULL COMMENT 'id', �Զ�����	 ���ʱ    
	    
	    entity.setChangeModel(cellvalues[0]);
	    
	    
	    //���Ҷ�Ӧ�û�
    	try {
	    	
    		OutUser tmpuser = getUserByCode(cellvalues[1]);
	    	entity.setUserid(tmpuser.getId()); 
    	} catch(Exception e){
    		
    		logger.error("���µ��û����󲻴��ڣ�����"+cellvalues[1]);	    		
    		return null;
    		
    	}	    
	    
	    
	    
	    if("�޸�".equals(cellvalues[0])){
	    	//У��ؼ���
	    	entity.setId(cellvalues[16]);	
	    } else {    	
	    	entity.setId(UUID.randomUUID().toString()); 
	    }
	    
	    
	    //��Ա���	����	רҵ�����ʸ�����	רҵ�����ʸ�����	רҵ����
	    entity.setXulie(cellvalues[4]);
	    entity.setName(cellvalues[5]);
	    entity.setZytype(cellvalues[6]);
	    

        //רҵ�ӷ���	������ע��	רҵ�����ʸ�֤����  ȡ���ʸ�����
	    entity.setSubtype(cellvalues[7]);
	    entity.setQt(cellvalues[8]);
	    entity.setZsnumber(cellvalues[9]);
	    entity.setGotdate(DateUtil.parseDate(cellvalues[10]));
	

        //ȡ���ʸ�;��	רҵ�����ʸ�ȼ�	������	�ʸ����赥λ	�Ƿ���Ҫרҵ�����ʸ� 
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
				//ӳ������
			
				entity = mappingOutLaodong(rowvalue);	
				
				//ӳ���ļ�û����
				if(entity!=null){
					//�޸��û�
					if("�޸�".equals(entity.getChangeModel())){
						
						tOutUserHtMapper.updateByPrimaryKey(entity);
						
					} else {
						
						if(entity!=null) {
							tOutUserHtMapper.insert(entity);	
							
						}					
					}
				} else {
					logger.info("����ӳ�������⣬���߸���ʱû�ҵ���Ӧ������==========="+entity);
				}
	
			}	
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	
	/**
	 * �Ͷ���ͬ��Ϣӳ��
	 * @param rowvalue
	 * @return
	 */
	private TOutUserHt mappingOutLaodong(String rowvalue) {
		//�����з���
		rowvalue+="#end";
		
	    String cellvalues[] = rowvalue.split("#");
	    
	    //���ݲ��Ϲ淶
	    if(cellvalues==null){	        
	        return null;
	    }
	    
	    
	    TOutUserHt  entity = new TOutUserHt();
	    
	    //`id` VARCHAR(50) NOT NULL COMMENT 'id', �Զ�����	 ���ʱ    
	    
	    entity.setChangeModel(cellvalues[0]);
	    
	    
	    //���Ҷ�Ӧ�û�
    	try {
	    	
    		OutUser tmpuser = getUserByCode(cellvalues[1]);
	    	entity.setUserid(tmpuser.getId()); 
    	} catch(Exception e){
    		
    		logger.error("���µ��û����󲻴��ڣ�����"+cellvalues[1]);	    		
    		return null;
    		
    	}	    
	    
	    
	    
	    if("�޸�".equals(cellvalues[0])){
	    	//У��ؼ���
	    	entity.setId(cellvalues[15]);	
	    } else {    	
	    	entity.setId(UUID.randomUUID().toString()); 
	    }
	    
	    
	    //��ͬ����	�Ͷ���ͬ���	��ͬ��������
	    entity.setContype(cellvalues[4]);
	    entity.setConnumber(cellvalues[5]);
	    entity.setConqxtype(cellvalues[6]);
		 
	    //�Ͷ���ͬ��ʼ����	�Ͷ���ͬ��ֹ����	�Ͷ���ͬ״̬	�Ͷ���ͬ����	�Ͷ���ͬǩ����λ	
	    entity.setStartdate(DateUtil.parseDate(cellvalues[7]));
	    entity.setEnddate(DateUtil.parseDate(cellvalues[8]));
	    
	    entity.setConstatus(cellvalues[9]);
	    entity.setQixian(cellvalues[10]);
	    entity.setUnit(cellvalues[11]);

	    //�����ͬǩ���ļ׷���˾	��Ӧ�����ͬ���	�����ͬ����
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
				//ӳ������
			
				entity = mappingOutJiechu(rowvalue);	
				
				//ӳ���ļ�û����
				if(entity!=null){
					//�޸��û�
					if("�޸�".equals(entity.getChangeModel())){
						
						tOutUserJcMapper.updateByPrimaryKey(entity);
						
					} else {
						
						if(entity!=null) {
							tOutUserJcMapper.insert(entity);	
							
						}					
					}
				} else {
					logger.info("����ӳ�������⣬���߸���ʱû�ҵ���Ӧ������==========="+entity);
				}
	
			}	
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * �Ͷ���ϵ���
	 * @param rowvalue
	 * @return
	 */
	private TOutUserJc mappingOutJiechu(String rowvalue) {
		//�����з���
		rowvalue+="#end";
		
	    String cellvalues[] = rowvalue.split("#");
	    
	    //���ݲ��Ϲ淶
	    if(cellvalues==null){	        
	        return null;
	    }
	    
	    
	    TOutUserJc  entity = new TOutUserJc();
	    
	    //`id` VARCHAR(50) NOT NULL COMMENT 'id', �Զ�����	 ���ʱ    
	    
	    entity.setChangeModel(cellvalues[0]);
	    
	    
	    //���Ҷ�Ӧ�û�
    	try {
	    	
    		OutUser tmpuser = getUserByCode(cellvalues[1]);
	    	entity.setUserid(tmpuser.getId()); 
    	} catch(Exception e){
    		
    		logger.error("���µ��û����󲻴��ڣ�����"+cellvalues[1]);	
    		//ɾ���Ķ��󣬱����¼
    		entity.setUserid(cellvalues[1]); 
    		//return null;
    		
    	}	    
	    
	    
	    
	    if("�޸�".equals(cellvalues[0])){
	    	//У��ؼ���
	    	entity.setId(cellvalues[8]);	
	    } else {    	
	    	entity.setId(UUID.randomUUID().toString()); 
	    }
	    
	    
	    //���ԭ��	��������ϵ����	������������	;��˵�����������˾���ƣ�
	    entity.setJcreason(cellvalues[4]);
	    entity.setJcdate((DateUtil.parseDate(cellvalues[5])));
	    entity.setGzenddate((DateUtil.parseDate(cellvalues[6])));
	    entity.setQt(cellvalues[7]);
	 	
	    
	    return entity;
	}

	/**
	 * 0#2# �������ַ�ӳ�䵽����
	 * ӳ������ֵ ԭʼ�۸��
	 * @param rowvalue
	 * @param outFenPei ������Ϣ���û�base��Ϣ��ȡ��
	 * @return
	 */
	private OutUser mappingOutUser(String rowvalue,TOutUserFpinfo outFenPei){
	    
	    
		//�����з���
		rowvalue+="#end";
		
	    String cellvalues[] = rowvalue.split("#");
	    
	    //���ݲ��Ϲ淶
	    if(cellvalues==null){	        
	        return null;
	    }
	    
	    
	    OutUser  entity = new OutUser();
	    
	    //`id` VARCHAR(50) NOT NULL COMMENT 'id', �Զ�����	 ���ʱ    
	    
	    entity.setChangeModel(cellvalues[0]);
	    
	    if("�޸�".equals(cellvalues[0])){
	    	//У��ؼ���
	    	//����codeȡidȻ�����
	    	
	    	try {
		    	OutUserExample example = new OutUserExample();
		    	
		    	OutUserExample.Criteria criteria = example.createCriteria();
				
				//ֻ��ѯ��ǰ�û�
				criteria.andCodeEqualTo(cellvalues[1]);
		    	
		    	example.createCriteria();		    	
		    	List<OutUser> tmpuserlist = outUserMapper.selectByExample(example);		    	
		    	entity.setId(tmpuserlist.get(0).getId()); 
		    	
			    //�����Ķ�������û�����ͨ�û�Ϊ0
			    entity.setUsertype(tmpuserlist.get(0).getUsertype());
			    
			    //���뱣�ֲ���
			    entity.setPwd(tmpuserlist.get(0).getPwd());
		    	
		    	
	    	} catch(Exception e){
	    		
	    		logger.error("���µ��û����󲻴��ڣ�����"+cellvalues[1]);	    		
	    		return null;
	    		
	    	}
	    	

	    	
	    } else {
	    	
	    	String userid = UUID.randomUUID().toString();
	    	entity.setId(userid);
	    	
	    	//û�и���ŵ��Զ����ɱ��
	    	if(StringUtils.isEmpty(cellvalues[1])){
	    		entity.setCode("SX" +new Date().getTime()); //����Զ�����
	    	} else {
	    		entity.setCode(cellvalues[1]);
	    	}
	    
		    //��ʼ����
		    entity.setPwd("111111");
		    //�����Ķ�������û�����ͨ�û�Ϊ0
		    entity.setUsertype("1");
		    
		    
		    //�з�������
		    if(cellvalues[25]!=null&&!"".equals(cellvalues[25].trim())) {
		    	
			    //���������Ϣ
			    if(outFenPei==null) outFenPei = new TOutUserFpinfo();
			    
			    outFenPei.setId(UUID.randomUUID().toString());
			    outFenPei.setUserid(userid);
			    
			    outFenPei.setGw(cellvalues[25]);
			    outFenPei.setGwtype(cellvalues[26]);
			    outFenPei.setGwjb(cellvalues[27]);
			    outFenPei.setKaohe(cellvalues[28]);
			    
//			    outFenPei.setQt1(cellvalues[29]);	 //��ϵͳ�������ݴ���		    
//			    outFenPei.setQt2(cellvalues[30]);
			    
			    outFenPei.setFzr(cellvalues[29]);
			    outFenPei.setIszuixiao(cellvalues[30]);
			    
			    outFenPei.setCompany(cellvalues[17]);
			    outFenPei.setConnumber(cellvalues[18]);
			    
			    outFenPei.setStartdate(DateUtil.parseDate(cellvalues[16]));
			    
			    //������
			    outFenPei.setFwdept(cellvalues[19]);
			    
		    }
		    
	    }
	    
	    

	    	   
	    //��Ա���	����	���֤����	�Ա�	    
	    entity.setName(cellvalues[2]);
	    entity.setIdnumber(cellvalues[3]);
	    entity.setSex(cellvalues[4]);
	    
	    
	    //��Ա����	��������	������ò	����	    
	    entity.setContype(cellvalues[5]);	    
	    entity.setBirthday(cellvalues[6]);
	    entity.setZhengzhi(cellvalues[7]);	 //������   
	    entity.setNationality(cellvalues[8]);
	    
	    
	    //�����ʼ���ַ	��������	�������ڵ�	����	    
	    entity.setMail(cellvalues[9]);
	    entity.setHukoutype(cellvalues[10]);
	    entity.setHukouaddress(cellvalues[11]);
	    entity.setMingz(cellvalues[12]);
	    
	    //�ƾӹ�����	���������λ����	������ͨҵ��ʼ����(�����޸�)	���俪ʼ����
	    entity.setIsout(cellvalues[13]);
	    entity.setInworkdate(DateUtil.parseDate(cellvalues[14]));
	    entity.setInunicomdate(DateUtil.parseDate(cellvalues[15]));
	    entity.setFenpeidate(DateUtil.parseDate(cellvalues[16]));//����
	    
	    
	    //���������˾	���������ͬ���	��֯����	�������ҵ������
	    entity.setCompanyid(cellvalues[17]);
	    entity.setConcode(cellvalues[18]);
	    entity.setUnit(cellvalues[19]);
	    entity.setYwtype(cellvalues[20]);

	    //������ͨ����;��	��Ա;��˵��	��˰��	�籣���ɵ�
	    entity.setYwtj(cellvalues[21]);
	    entity.setTjmark(cellvalues[22]);
	    entity.setNsaddress(cellvalues[23]);
	    entity.setSbaddress(cellvalues[24]);
	    
	    
	    
	    //��λ����	��λ����	�ο��ڼ�	������Ϣ	����1	����2
	    entity.setGwnumber(cellvalues[25]);
	    entity.setGwtype(cellvalues[26]);
	    entity.setGwdj(cellvalues[27]);
	    entity.setKaohei(cellvalues[28]);		    
	    
	    
	    entity.setBak1(cellvalues[29]);
	    entity.setBak2(cellvalues[30]);
	    

	    return entity;
	    
	}
	
	
	
	
	/**
	 * 0#2# �������ַ�ӳ�䵽����
	 * ӳ������ֵ ԭʼ�۸��
	 * @param rowvalue
	 * @param outFenPei ������Ϣ���û�base��Ϣ��ȡ��
	 * @return
	 */
	private TOutUserJyinfo mappingOutJiaoy(String rowvalue){
	    
	    
		//�����з���
		rowvalue+="#end";
		
	    String cellvalues[] = rowvalue.split("#");
	    
	    //���ݲ��Ϲ淶
	    if(cellvalues==null){	        
	        return null;
	    }
	    
	    
	    TOutUserJyinfo  entity = new TOutUserJyinfo();
	    
	    //`id` VARCHAR(50) NOT NULL COMMENT 'id', �Զ�����	 ���ʱ    
	    
	    entity.setChangeModel(cellvalues[0]);
	    
	    
	    //���Ҷ�Ӧ�û�
    	try {
	    	
    		OutUser tmpuser = getUserByCode(cellvalues[1]);
	    	entity.setUserid(tmpuser.getId()); 
    	} catch(Exception e){
    		
    		logger.error("���µ��û����󲻴��ڣ�����"+cellvalues[1]);	    		
    		return null;
    		
    	}	    
	    
	    
	    
	    if("�޸�".equals(cellvalues[0])){
	    	//У��ؼ���
	    	entity.setId(cellvalues[28]);	
	    } else {    	
	    	entity.setId(UUID.randomUUID().toString()); 
	    }
	    
	    
		 //��Ա���	����	ѧУ	��ѧʱ��	��ҵʱ��
	    entity.setSchool(cellvalues[4]);
	    entity.setStartdate(DateUtil.parseDate(cellvalues[5]));
	    entity.setEnddate(DateUtil.parseDate(cellvalues[6]));


        //ѧ��	ѧ��֤����	�Ƿ����ѧ��	�Ƿ�ȫ����ѧ��	ѧλ
	    entity.setXueli(cellvalues[7]);
	    entity.setXuelizsnumber(cellvalues[8]);
	    entity.setIsmaxxl(cellvalues[9]);
	    entity.setIsqrz(cellvalues[10]);
	    entity.setXuewei(cellvalues[11]);
	    
        
        
        //��һѧλ���	�ڶ�ѧλ���	ѧλ��������	ѧλ���赥λ	ѧλ֤����	�Ƿ����ѧλ
	    entity.setD1xwtype(cellvalues[12]);
	    entity.setD2xwtype(cellvalues[13]);
	    entity.setXwdate(DateUtil.parseDate(cellvalues[14]));
	    entity.setXwunit(cellvalues[15]);
	    entity.setXwzsnumber(cellvalues[16]);
	    entity.setIsmaxxw(cellvalues[17]);
	  
        //ͬ��ѧ��	�൱��ҵ	רҵ���	רҵ�����	��һרҵ	�ڶ�רҵ
	    entity.setTdxl(cellvalues[18]);
	    entity.setXdby(cellvalues[19]);
	    entity.setZytype(cellvalues[20]);
	    entity.setZysubtype(cellvalues[21]);
	    entity.setD1zy(cellvalues[22]);
	    entity.setD2zy(cellvalues[23]);
	    
        
        
        //ѧ��	ѧϰ��ʽ	ѧϰ���	��ע
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
	 * �����û�Ψһ�ؼ���ȡ�û�
	 * @param code
	 * @return
	 */
	@Override
	public OutUser getUserByCode(String code) {
		OutUserExample example = new OutUserExample();
		
		OutUserExample.Criteria criteria = example.createCriteria();
		
		//ֻ��ѯ��ǰ�û�
		criteria.andCodeEqualTo(code);
		
		example.createCriteria();		    	
		List<OutUser> tmpuserlist = outUserMapper.selectByExample(example);	
		
		if(tmpuserlist!=null && tmpuserlist.size()>0){
			return tmpuserlist.get(0);
		}
		return null;
	}


}
