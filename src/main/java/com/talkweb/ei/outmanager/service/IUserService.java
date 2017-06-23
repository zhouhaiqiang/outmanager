package com.talkweb.ei.outmanager.service;

import java.util.List;

import com.talkweb.ei.outmanager.model.OutUserExample;
import com.talkweb.ei.outmanager.model.OutUser;

/**
 * �û��ӿ�
 * @author zhq
 *
 */
public interface IUserService {
	
	/**
	 * ��֤
	 * 
	 * @param  String userid, String pwd
	 * @return
	 */
	boolean auth(String userid, String pwd);
	
	
	/**
	 * �û�������Ϣ����
	 * 
	 * @param  List<String> exldata
	 * @return
	 */
	boolean importBase(List<String> exldata);	
	

	
	/**
	 * ������Ϣ����
	 * 
	 * @param  List<String> exldata
	 * @return
	 */
	boolean importFenpei(List<String> exldata);		
	
	
	/**
	 * ������Ϣ����
	 * 
	 * @param  List<String> exldata
	 * @return
	 */
	boolean importJiaoyu(List<String> exldata);		
	
	
	
	/**
	 * ְҵ������Ϣ����
	 * 
	 * @param  List<String> exldata
	 * @return
	 */
	boolean importZhiye(List<String> exldata);		
	
	/**
	 * רҵ��Ϣ����
	 * 
	 * @param  List<String> exldata
	 * @return
	 */
	boolean importZhuanye(List<String> exldata);
	
	
	/**
	 * �Ͷ���ϵ����
	 * 
	 * @param  List<String> exldata
	 * @return
	 */
	boolean importLaodong(List<String> exldata);	
	
	/**
	 * �����Ϣ����
	 * 
	 * @param  List<String> exldata
	 * @return
	 */
	boolean importJiechu(List<String> exldata);		
	
	
	
	
	
	/*******************************���ýӿ�****************************************/
	//������
	int getUserSize(OutUserExample sample);
	
	/**
	 * ȡ��˾�б�
	 * @param sample
	 * @return
	 */
	List<OutUser>getUserList(OutUserExample sample);
	
	
	/**
	 * ��ҳ����
	 * @param limit ���ݼ������±�
	 * @param offset ���ݽӿ�ʼ�±�
	 * @param sample ��ѯ����
	 * @return
	 */
	List<OutUser>getUserList(int limit,int offset, OutUserExample sample);
	
	
	/**
	 * ȡһ��
	 * @param ID
	 * @return
	 */
	OutUser getUser(String ID);
	

	/**
	 * ȡһ���û����û����Ψһ��
	 * @return
	 */
	OutUser getUserByCode(String code);	
	
	
	/**
	 * ����һ��
	 * @param sample
	 * @return
	 */
	boolean updateUser(OutUser sample);
	
	/**
	 * ����һ��
	 * @param sample
	 * @return
	 */
	boolean addUser(OutUser sample);

	
	/**
	 * ɾ��һ��
	 * @param sample
	 * @return
	 */
	boolean deleteUser(OutUser sample);	
	/*******************************���ýӿ�**************************end**************/
	
	
	
	
	
	
}
