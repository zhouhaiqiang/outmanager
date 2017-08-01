package com.talkweb.ei.outmanager.dao;

import java.util.List;

import com.talkweb.ei.outmanager.model.ReportData;

public interface OutUserReportMapper {

	
	/**
	 * 按性别统计
	 * @param unit
	 * @return
	 */
	List<ReportData> countBySex(String unit);
		
	
	/**
	 * 按年龄统计
	 * @param unit
	 * @return
	 */
	List<ReportData> countByAge(String unit);

	/**
	 * 按民族统计
	 * @param unit
	 * @return
	 */
	List<ReportData> countByMingz(String unit);
	
	
	/**
	 * 政治面貌统计
	 * @param unit
	 * @return
	 */
	List<ReportData> countByZhengz(String unit);
	
	
	
	
	/**
	 * 学历统计
	 * @param unit
	 * @return
	 */
	List<ReportData> countByXueli(String unit);
	
	
	
	
	
	
	
	
	
	
	
}