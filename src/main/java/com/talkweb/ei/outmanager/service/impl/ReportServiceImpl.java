package com.talkweb.ei.outmanager.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talkweb.ei.di.common.DateUtil;
import com.talkweb.ei.di.common.JsonUtil;
import com.talkweb.ei.outmanager.dao.OutUserReportMapper;
import com.talkweb.ei.outmanager.dao.TOutReportMapper;
import com.talkweb.ei.outmanager.dao.TOutReportstMapper;
import com.talkweb.ei.outmanager.model.ReportData;
import com.talkweb.ei.outmanager.model.TOutReport;
import com.talkweb.ei.outmanager.model.TOutReportst;
import com.talkweb.ei.outmanager.model.TOutReportstExample;
import com.talkweb.ei.outmanager.service.IDictory;
import com.talkweb.ei.outmanager.service.IReportService;

@Service
public class ReportServiceImpl implements IReportService {

	
	//报表数据生成
	@Autowired
	private OutUserReportMapper outUserReportMapper;	
	
	@Autowired
	private IDictory iDictory;
	
	//报表单位列表
	public static final String[] UNITS={
			"山西省分公司",
//			"山西省分公司本部",
//			"太原市分公司",
//			"晋中市分公司",
//			"大同市分公司",
//			"朔州市分公司",
//			"忻州市分公司",
//			"阳泉市分公司",
//			"长治市分公司",
//			"晋城市分公司",
//			"吕梁市分公司",
//			"临汾市分公司",
//			"运城市分公司"			
	};
	
	

	
	
	//年度表名字
	public static final String[] YEAR_REPORTNAME={
			"1-1、全口径人员总量统计",
			"1-3、省级公司本部情况统计表",
			"1-4、地市公司本部情况统计表",
			"2-1、人员分类(一)",
			"2-2、人员分类(二)",
			"3-4、紧密型业务外包人员岗位分类",
			"3-5、各岗序列人员占比变动情况",
			"3-14、紧密型业务外包人员关键职责分类",
			"4-3、紧密型业务外包人员职位层级变动分析",
			"5-3、紧密型业务外包人员增减变动",
			"7、人员素质结构优化分析"			
	};
	
	
	
	//模板页面定义
	public static  Map<String,String> YEAR_REPORTTMP = new HashMap<String,String>();
	static {
		for (int i = 0; i < YEAR_REPORTNAME.length; i++) {
			YEAR_REPORTTMP.put(YEAR_REPORTNAME[i], "z_yearexl"+i);
		}
				
	}

	//季度表名字
	public static final String[] SEC_REPORTNAME={
			"03紧密型业务外包人员增减变动情况统计表",
			"05公司主动辞退人员情况统计表",
			"07省本部人员流动情况统计表",
			"08地市本部人员流动情况统计表",
			"09岗位占比变化情况统计表",
			"10人员学历结构情况统计表"
				
	};
	
	//模板页面定义
	public static  Map<String,String> SEC_REPORTTMP = new HashMap<String,String>();
	static {
		for (int i = 0; i < SEC_REPORTNAME.length; i++) {
			SEC_REPORTTMP.put(SEC_REPORTNAME[i], "z_secexl"+i);
		}
				
	}
	
	//月报表名字
	public static final String[] MONTH_REPORTNAME={
			"1、员工情况表(月报)",
			"2-3、紧密型业务外包人员增减变动情况(月报)",
			"3、常用信息统计表(月报)"
					
	}; 	
	
	//模板页面定义
	public static  Map<String,String> MONTH_REPORTTMP = new HashMap<String,String>();
	static {
		for (int i = 0; i < MONTH_REPORTNAME.length; i++) {
			MONTH_REPORTTMP.put(MONTH_REPORTNAME[i], "z_monthexl"+i);
		}
				
	}	
	
	
	@Autowired
	private TOutReportstMapper tOutReportstMapper;
	
	@Autowired
	private TOutReportMapper tOutReportMapper;	
	
	
	@Override
	public boolean checkReport(String type, String qdate) {
		//构建条件
		TOutReportstExample sample = new TOutReportstExample();
		
		
		TOutReportstExample.Criteria criteria = sample.createCriteria();

		//报表类型
		criteria.andReptypeEqualTo(type);
		

		//生成的日期
		Date reqdate = DateUtil.parseDate(qdate,DateUtil.C_DATE_PATTON_DEFAULT);
		
		criteria.andRepdateEqualTo(reqdate);


		//查询对应的记录
		try {
			List<TOutReportst> list =  tOutReportstMapper.selectByExample(sample);
			if(list!=null&&list.size()>0){
				
				return true;
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		return false;
		
	}

	@Override
	public boolean createReport(String type, String qdate) {
		
		
		//详细数据入库标志
		boolean inData = false;
		//年数据入库
		if("年报".equals(type)){
			inData = createYearData(qdate,type);
		}

		//季度数据入库
		if("季报".equals(type)){
			inData = createSecData(qdate,type);
		}		
		
		
		//月份数据入库
		if("月报".equals(type)){
			inData = createMonthrData(qdate,type);
		}		
		
		//状态表
		if(inData){
			TOutReportst record = new TOutReportst();
			record.setId(UUID.randomUUID().toString());
			record.setRepdate(DateUtil.parseDate(qdate,DateUtil.C_DATE_PATTON_DEFAULT));
			record.setIntf("不可传送");
			record.setIsrecreate("不可重新抽取");
			record.setReptype(type);
			record.setUnit("山西省分公司");						
			tOutReportstMapper.insert(record);
			
			return true;
		}
		
	
		return false;
	}
	
	
	/**
	 * 年报详细数据入库
	 * @param qdate
	 * @return
	 */
	private boolean createYearData(String qdate,String type){

		//分别插入
		for (int i = 0; i < UNITS.length; i++) {			
			for (int j = 0; j < YEAR_REPORTNAME.length; j++) {				
				createRcode(qdate,YEAR_REPORTNAME[j],type,UNITS[i],"",getYearReportJson(YEAR_REPORTNAME[j],UNITS[i],qdate));
				
			}
						
		}

		return true;
	}
	
	
	private boolean createRcode(String qdate, String name,String type,String unit,String code,String data){
		TOutReport record = new TOutReport();
		//公共的属性
		record.setId(UUID.randomUUID().toString());
		record.setRepdate(DateUtil.parseDate(qdate,DateUtil.C_DATE_PATTON_DEFAULT));
		record.setReptype(type);		
		
		//待处理....
		record.setIntf("不可传送");
		
		//报表名  待处理....
		record.setName(name);
		
		
		//单位属性 待处理....
		record.setUnit(unit);
		
		//详细数据json格式  待处理....
		record.setRepdata(data);
		
		//报表编号  待处理....
		record.setRepcode(code);

		//插入记录
		try {
			if (tOutReportMapper.insert(record)>0) {
				return true;
			};
			
		} catch (Exception e) {
			return false;
		}
		return false;
	}
	
	
	/**
	 * 季度报详细数据入库
	 * @param qdate
	 * @return
	 */
	private boolean createSecData(String qdate,String type){
		
		
		//分别插入
		for (int i = 0; i < UNITS.length; i++) {			
			for (int j = 0; j < SEC_REPORTNAME.length; j++) {				
				createRcode(qdate,SEC_REPORTNAME[j],type,UNITS[i],"",getSeaReportJson(SEC_REPORTNAME[j],UNITS[i],qdate));
				
			}
						
		}

		return true;
	}
	/**
	 * 月报详细数据入库
	 * @param qdate
	 * @return
	 */
	private boolean createMonthrData(String qdate,String type){
		
		//分别插入
		for (int i = 0; i < UNITS.length; i++) {			
			for (int j = 0; j < MONTH_REPORTNAME.length; j++) {				
				createRcode(qdate,MONTH_REPORTNAME[j],type,UNITS[i],"",getMonthReportJson(MONTH_REPORTNAME[j],UNITS[i],qdate));
				
			}
						
		}

		return true;
	}
	
	
	/**
	 * 去详细报表数据
	 * @param name
	 * @param unit
	 * @param qdate
	 * @return
	 */
	private String getYearReportJson(String name,String unit,String qdate){
		
		return  "[{}]";
		
	}
	/**
	 * 去详细报表数据
	 * @param name
	 * @param unit
	 * @param qdate
	 * @return
	 */
	private String getSeaReportJson(String name,String unit,String qdate){
		
		return  "[{}]";
		
	}
	/**
	 * 去详细报表数据
	 * @param name
	 * @param unit
	 * @param qdate
	 * @return
	 */	
	private String getMonthReportJson(String name,String unit,String qdate){
		
		
		
		//返回列表
		List<ReportData> retlist = new ArrayList<ReportData>();
		
		
		//省公司特殊处理
		if(unit.equals(UNITS[0])){
			
			//不做查询条件
			unit = null;
		} else {
			//处理地市公司
			unit = iDictory.getUnitNameByName(unit);
			
			if(unit!=null&&unit.length()>2000){				
				unit = unit.substring(0, 2000);				
			}
			
		}
		
		//3、常用信息统计表(月报)
		if(MONTH_REPORTNAME[2].equals(name)){
			

			//1按性别统计
			List<ReportData> list1 = outUserReportMapper.countBySex(unit);
			
			//小统计1
			retlist.addAll(countSub(list1,"（一）性别"));
			
			//2按性别统计
			List<ReportData> list2 = outUserReportMapper.countByAge(unit);
			
			//倒数量行不参与统计	
			
			
			List<ReportData> tmplist = new ArrayList<ReportData>();
			List<ReportData> tmplist1 = new ArrayList<ReportData>();
			
			//复制
			for (int i=0; i<8;i++) {
				tmplist.add(list2.get(i));
			}
			for (int i=0; i<2;i++) {
				tmplist1.add(list2.get(8+i));
			}
		
			
			//小统计1
			retlist.addAll(countSub(tmplist,"（二）年龄"));
			retlist.addAll(tmplist1);
			
			//3按民族统计
			List<ReportData> list3 = outUserReportMapper.countByMingz(unit);
			
			//小统计
			retlist.addAll(countSub(list3,"（三）民族"));	
			

			//4政治面貌统计
			List<ReportData> list4 = outUserReportMapper.countByZhengz(unit);
			
			//小统计
			retlist.addAll(countSub(list4,"（四）政治面貌"));	
			
			
			//5学历统计统计
			/******************5学历统计统计****************************start**************/
			List<ReportData> list5 = outUserReportMapper.countByXueli(unit);
			
			tmplist = new ArrayList<ReportData>();
			tmplist1 = new ArrayList<ReportData>();
		
			
			//复制
			for (int i=0; i<list5.size(); i++) {
				
				if(i!=0){
					tmplist1.add(list5.get(i));
				} 
				
			}

			//小统计1			
			tmplist = countSub(tmplist1,"（五）学历");
			tmplist.add(1, list5.get(0));
			
			retlist.addAll(tmplist);
           			
			/******************5学历统计统计****************************end**************/
			
			
		}
		
		
		//转成json格式的string
		String jsonstr = "[{}]";
		try {
			jsonstr =JsonUtil.toJson(retlist);		
		} catch (Exception e) {
			e.printStackTrace();
			return  "[{}]";
		}
		System.out.println("报表数据============="+jsonstr);
		return jsonstr;
		
	}
	
	
	/**
	 * 小统计
	 * @param list
	 * @param name  合并数据的第一列名称
	 * @return
	 */
	private List<ReportData> countSub(List<ReportData> list,String name){
				
		//统计工作
		ReportData sumData = new ReportData();
		//列举属性
		Field[] fs = ReportData.class.getDeclaredFields();
		
		int intvalue = 0;
		int intvalueall = 0;
		for(int i = 0 ; i < fs.length; i++){
			 Field f = fs[i];
			 f.setAccessible(true);
			 for (ReportData reportData : list) {
				 try {
					 
					 //取属性值
					 Object val = f.get(reportData);
					 Object valall = f.get(sumData);
					 //转换成整数
					 intvalue = Integer.valueOf((String)val);					 
					 intvalueall = Integer.valueOf((String)valall);
					 //统计
					 intvalueall+=intvalue;
					 
					 //设置统计值
					 f.set(sumData, intvalueall+"");
					 
					
				} catch (Exception e) {
					
					//属性的属性做为类型
					try {
						f.set(sumData, name);
					} catch (Exception e1) {
						// 设置name出错
						e1.printStackTrace();
					} 
					
					
				}
				
					
			 }
		 }
		
		
		//添加统计条目第一条
		list.add(0,sumData);
		
		return list;
		
	}
	

}
