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
	
	// ע��Service����
	@Autowired
	private OutUserMapper outUserMapper;
	
	@Override
	public boolean auth(String userid, String pwd) {
		
		
		try {
		
			String dbpwd = outUserMapper.selectByPrimaryKey(userid).getPwd();
		
			//������ܺ�Ƚ�

            //doxxxxxx
			
			
			
			
			if(dbpwd.equals(pwd)){
				return true;
			}
		
		
		} catch(Exception e) {
			e.printStackTrace();
			
		}
		logger.info("��֤ʧ�ܣ�");
		return false;
	}

	@Override
	public boolean importBase(List<String> exldata) {
		try {
			OutUser entity;
			
			for(String rowvalue:exldata){			
				//ӳ������
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
	 * 0#2# �������ַ�ӳ�䵽����
	 * ӳ������ֵ ԭʼ�۸��
	 * @param rowvalue
	 * @return
	 */
	private OutUser mappingOutUser(String rowvalue){
	    
	    
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
	    	entity.setId(cellvalues[31]);  //�û�ģ����Ϊʲôû��У��λ���������ѵ���code��Ψһ�ؼ���ȥ���µģ�
	    } else {
	    	entity.setId(UUID.randomUUID().toString());
	    }
	    
	    

	    	   
	    //��Ա���	����	���֤����	�Ա�
	    entity.setCode(cellvalues[1]);
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
	    
	    //�����Ķ�������û�����ͨ�û�Ϊ0
	    entity.setUsertype("1");
	    //��ʼ����
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
