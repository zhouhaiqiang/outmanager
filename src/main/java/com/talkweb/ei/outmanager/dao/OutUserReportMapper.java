package com.talkweb.ei.outmanager.dao;

import java.util.List;

import com.talkweb.ei.outmanager.model.ReportData;

public interface OutUserReportMapper {

	
	/**
	 * ���Ա�ͳ��
	 * @param unit
	 * @return
	 */
	List<ReportData> countBySex(String unit);
		
	
	/**
	 * ������ͳ��
	 * @param unit
	 * @return
	 */
	List<ReportData> countByAge(String unit);

	/**
	 * ������ͳ��
	 * @param unit
	 * @return
	 */
	List<ReportData> countByMingz(String unit);
	
	
	/**
	 * ������òͳ��
	 * @param unit
	 * @return
	 */
	List<ReportData> countByZhengz(String unit);
	
	
	
	
	/**
	 * ѧ��ͳ��
	 * @param unit
	 * @return
	 */
	List<ReportData> countByXueli(String unit);
	
	
	
	
	
	
	
	
	
	
	
}