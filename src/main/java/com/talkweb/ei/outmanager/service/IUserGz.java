package com.talkweb.ei.outmanager.service;

import java.util.List;

import com.talkweb.ei.outmanager.model.VOutUsergz;
import com.talkweb.ei.outmanager.model.VOutUsergzExample;


/**
 * �û��ӿ�
 * @author zhq
 *
 */
public interface IUserGz {
	
	
	//������
	int getUserGzSize(VOutUsergzExample sample);
	
	
	
	/**
	 * ��ҳ����
	 * @param limit ���ݼ������±�
	 * @param offset ���ݽӿ�ʼ�±�
	 * @param sample ��ѯ����
	 * @return
	 */
	List<VOutUsergz>getUserGzList(int limit,int offset, VOutUsergzExample sample);
	

	
	/**
	 * ���˷�����Ϣ����
	 * 
	 * @param  List<String> exldata
	 * @return
	 */
	boolean importGrhy(List<String> exldata);	
	
	
	/**
	 * ���˷�����Ϣ����
	 * 
	 * @param  List<String> exldata
	 * @return
	 */
	boolean importJthy(List<String> exldata);		
	
	
	
	
	
	
	
}
