package com.talkweb.ei.outmanager.service;

/**
 * 用户接口
 * @author zhq
 *
 */
public interface IReportService {
	


	/**
	 * 检测报表是否已经生成
	 * @param type [年报，月报，季报]
	 * @param qdate
	 * @return
	 */
	boolean checkReport(String type,String qdate);


	/**
	 * 生产报表
	 * @param type
	 * @param qdate
	 * @return
	 */
	boolean createReport(String type,String qdate);
	
}
