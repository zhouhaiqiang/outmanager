package com.talkweb.ei.outmanager.service;

/**
 * �û��ӿ�
 * @author zhq
 *
 */
public interface IReportService {
	


	/**
	 * ��ⱨ���Ƿ��Ѿ�����
	 * @param type [�걨���±�������]
	 * @param qdate
	 * @return
	 */
	boolean checkReport(String type,String qdate);


	/**
	 * ��������
	 * @param type
	 * @param qdate
	 * @return
	 */
	boolean createReport(String type,String qdate);
	
}
