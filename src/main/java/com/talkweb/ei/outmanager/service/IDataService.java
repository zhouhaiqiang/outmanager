package com.talkweb.ei.outmanager.service;

import java.util.List;

import com.talkweb.ei.outmanager.model.OutCompany;
import com.talkweb.ei.outmanager.model.OutCompanyExample;
import com.talkweb.ei.outmanager.model.OutContract;
import com.talkweb.ei.outmanager.model.OutContractExample;

/**
 * ���ݷ���ӿڣ��ۺ��˾��󲿷ֵķ���ӿ�
 * @author zhq
 *
 */
public interface IDataService {
	
	/********��˾�ӿ�*******************************************start******************/
	/**
	 * ȡ��˾�б�
	 * @param sample
	 * @return
	 */
	List<OutCompany>getCompanyList(OutCompanyExample sample);
	
	//������
	int getCompanySize(OutCompanyExample sample);
	
	//������
	int getContractSize(OutContractExample sample);

	
	/**
	 * ��ҳ����
	 * @param limit ���ݼ������±�
	 * @param offset ���ݽӿ�ʼ�±�
	 * @param sample ��ѯ����
	 * @return
	 */
	List<OutCompany>getCompanyList(int limit,int offset, OutCompanyExample sample);
	
	
	/**
	 * ȡһ��
	 * @param ID
	 * @return
	 */
	OutCompany getCompany(String ID);
	
	
	/**
	 * ���һ��
	 * @param sample
	 * @return
	 */
	boolean addCompany(OutCompany sample);

	
	/**
	 * ����һ��
	 * @param sample
	 * @return
	 */
	boolean updateCompany(OutCompany sample);
	
	
	/**
	 * ɾ��һ��
	 * @param sample
	 * @return
	 */
	boolean deleteCompany(OutCompany sample);
	
	
	/**
	 * ������������
	 * @param sample
	 * @return
	 */
	boolean importCompany(List<String> exldata);	
	
	

	/********��˾�ӿ�************************************************end*************/
	
	
	
	/********��ͬ�ӿ�*******************************************start******************/
	/**
	 * ȡ��˾�б�
	 * @param sample
	 * @return
	 */
	List<OutCompany>getContractList(OutContractExample sample);
	
	
	/**
	 * ��ҳ����
	 * @param limit ���ݼ������±�
	 * @param offset ���ݽӿ�ʼ�±�
	 * @param sample ��ѯ����
	 * @return
	 */
	List<OutContract>getContractList(int limit,int offset, OutContractExample sample);
	
	
	/**
	 * ȡһ��
	 * @param ID
	 * @return
	 */
	OutContract getContract(String ID);
	
	
	
	/**
	 * ����һ��
	 * @param sample
	 * @return
	 */
	boolean updateContract(OutContract sample);
	
	/**
	 * ����һ��
	 * @param sample
	 * @return
	 */
	boolean addContract(OutContract sample);

	
	/**
	 * ɾ��һ��
	 * @param sample
	 * @return
	 */
	boolean deleteContract(OutContract sample);
	
	
	
	/**
	 * ������������
	 * @param sample
	 * @return
	 */
	boolean importContract(List<String> exldata);	

	/********��ͬ�ӿ�************************************************end*************/	
	
	
	
}
