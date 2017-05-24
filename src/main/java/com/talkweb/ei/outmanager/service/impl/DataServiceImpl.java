package com.talkweb.ei.outmanager.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talkweb.ei.di.common.DateUtil;
import com.talkweb.ei.outmanager.dao.OutCompanyMapper;
import com.talkweb.ei.outmanager.dao.OutContractMapper;
import com.talkweb.ei.outmanager.model.OutCompany;
import com.talkweb.ei.outmanager.model.OutCompanyExample;
import com.talkweb.ei.outmanager.model.OutContract;
import com.talkweb.ei.outmanager.model.OutContractExample;
import com.talkweb.ei.outmanager.service.IDataService;

@Service
public class DataServiceImpl implements IDataService {
	
	
	@Autowired
	private OutCompanyMapper outCompanyMapper;		
	
	
	@Autowired
	private OutContractMapper outContractMapper;		

	@Override
	public List<OutCompany> getCompanyList(OutCompanyExample sample) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OutCompany> getCompanyList(int limit, int offset, OutCompanyExample sample) {

		sample.setLimit(offset+limit);
		sample.setOffset(offset+1);
		return outCompanyMapper.selectPageByExample(sample);
		
	}

	@Override
	public OutCompany getCompany(String ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateCompany(OutCompany sample) {
		try {
			outCompanyMapper.updateByPrimaryKey(sample);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		 
		return true;
	}

	@Override
	public boolean deleteCompany(OutCompany sample) {
		try {
			outCompanyMapper.deleteByPrimaryKey(sample.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		 
		return true;
	}

	@Override
	public List<OutContract> getContractList(int limit, int offset, OutContractExample sample) {
		sample.setLimit(offset+limit);
		sample.setOffset(offset+1);
		return outContractMapper.selectPageByExample(sample);
	}

	@Override
	public OutContract getContract(String ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateContract(OutContract sample) {
		try {
			outContractMapper.updateByPrimaryKey(sample);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		 
		return true;
	}

	@Override
	public boolean deleteContract(OutContract sample) {
				
		try {
			outContractMapper.deleteByPrimaryKey(sample.getId());
		} catch (Exception e) {			
			e.printStackTrace();
			return false;
		}
		 
		return true;
	}

	@Override
	public int getCompanySize(OutCompanyExample sample) {
		int ret = 0;
		try{
			ret = Integer.parseInt(outCompanyMapper.countByExample(sample)+"");
		} catch (Exception e) {
			return 0;
		}
		return  ret; 
		 
	}
	
	@Override
	public int getContractSize(OutContractExample sample) {
		int ret = 0;
		try{
			ret = Integer.parseInt(outContractMapper.countByExample(sample)+"");
		} catch (Exception e) {
			return 0;
		}
		return  ret; 
		 
	}	
	

	@Override
	public boolean addCompany(OutCompany sample) {
		try {
			outCompanyMapper.insert(sample);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		 
		return true;
	}

	@Override
	public boolean importCompany(List<String> exldata) {
		
		try {
			OutCompany entity;
			
			for(String rowvalue:exldata){			
				//ӳ������
				entity = mappingOutCompany(rowvalue);			
				outCompanyMapper.insert(entity);	
			}	
		} catch (Exception e) {
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
	private OutCompany mappingOutCompany(String rowvalue){
	    
	    
		//�����з���
		rowvalue+="#end";
		
	    String cellvalues[] = rowvalue.split("#");
	    
	    //���ݲ��Ϲ淶
	    if(cellvalues==null){	        
	        return null;
	    }
	    
	    
	    OutCompany  entity = new OutCompany();
	    
	    //`id` VARCHAR(50) NOT NULL COMMENT 'id', �Զ�����	 ���ʱ    
	    
	    entity.setChangeModel(cellvalues[0]);
	    
	    if("�޸�".equals(cellvalues[0])){
	    	//У��ؼ���
	    	entity.setId(cellvalues[11]);
	    } else {
	    	entity.setId(UUID.randomUUID().toString());
	    }
	    
	    
	    	   
	    entity.setUnit(cellvalues[1]);
	    entity.setConType(cellvalues[2]);
	    entity.setName(cellvalues[3]);
	    entity.setZizhi(cellvalues[4]);
	    entity.setArea(cellvalues[5]);
	    
	    entity.setBoss(cellvalues[6]);
	    entity.setAddress(cellvalues[7]);	    
	    entity.setZijin(cellvalues[8]);
	    entity.setZhizhao(cellvalues[9]);
	    entity.setZizhidoc(cellvalues[10]);

	    	    
	    
	    return entity;
	    
	}
	
	
	/**
	 * 0#2# �������ַ�ӳ�䵽����
	 * ӳ������ֵ ԭʼ�۸��
	 * @param rowvalue
	 * @return
	 */
	private OutContract mappingOutContract(String rowvalue){
	    
	    
		//�����з���
		rowvalue+="#end";
		
	    String cellvalues[] = rowvalue.split("#");
	    
	    //���ݲ��Ϲ淶
	    if(cellvalues==null){	        
	        return null;
	    }
	    
	    
	    OutContract  entity = new OutContract();
	    
	    //`id` VARCHAR(50) NOT NULL COMMENT 'id', �Զ�����	 ���ʱ    
	    
	    entity.setChangeModel(cellvalues[0]);
	    
	    if("�޸�".equals(cellvalues[0])){
	    	//У��ؼ���
	    	entity.setId(cellvalues[11]);
	    } else {
	    	entity.setId(UUID.randomUUID().toString());
	    }
	    
	    
	    	   
	    //��ͬǩ������	��ͬ����	ǩ���������ͬ����
	    entity.setUnit(cellvalues[1]);
	    entity.setConType(cellvalues[2]);
	    entity.setName(cellvalues[3]);
	    
	    //�����˾����	��ͬ���	��ʼ����	��������
	    entity.setCompanyid(cellvalues[4]);
	    entity.setConCode(cellvalues[5]);	    
	    entity.setStartdate(DateUtil.parseDate(cellvalues[6]));
	    entity.setEnddate(DateUtil.parseDate(cellvalues[7]));
	    
	    
	    //����	��ͬ�ı�	�����ͬЭ��	��ͬ�漰��ҵ��	��ͬ���		    
	    entity.setQixian(cellvalues[8]);
	    entity.setAtts(cellvalues[9]);
	    entity.setBuchong(cellvalues[10]);
	    entity.setYewu(cellvalues[11]);
	    entity.setJine(cellvalues[12]);

	    return entity;
	    
	}	
	
	
	

	@Override
	public boolean importContract(List<String> exldata) {
		try {
			OutContract entity;
			
			for(String rowvalue:exldata){			
				//ӳ������
				entity = mappingOutContract(rowvalue);			
				outContractMapper.insert(entity);	
	
			}	
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	@Override
	public List<OutCompany> getContractList(OutContractExample sample) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addContract(OutContract sample) {
		try {
			outContractMapper.insert(sample);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		 
		return true;
	}
	

}
