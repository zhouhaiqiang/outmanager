package com.talkweb.ei.outmanager.web;

import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.reflect.TypeToken;
import com.talkweb.ei.di.common.DateUtil;
import com.talkweb.ei.di.common.JsonUtil;
import com.talkweb.ei.di.common.PageResult;
import com.talkweb.ei.di.common.StringUtils;
import com.talkweb.ei.outmanager.dao.TOutReportMapper;
import com.talkweb.ei.outmanager.dao.TOutReportstMapper;
import com.talkweb.ei.outmanager.model.ReportData;
import com.talkweb.ei.outmanager.model.TOutReport;
import com.talkweb.ei.outmanager.model.TOutReportExample;
import com.talkweb.ei.outmanager.model.TOutReportst;
import com.talkweb.ei.outmanager.model.TOutReportstExample;
import com.talkweb.ei.outmanager.service.IReportService;
import com.talkweb.ei.outmanager.service.impl.ReportServiceImpl;

/**
 * 报表操作
 * @author zhq
 *
 */
@Controller
@RequestMapping("/report") // url:/模块/资源/{id}/细分 /seckill/list
public class ReportController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Autowired
	private TOutReportstMapper tOutReportstMapper;

	
	@Autowired
	private TOutReportMapper tOutReportMapper;	
	
	@Autowired
	private IReportService reportService;	
	


	/**********************************业务接口***************************start***********/
	
	/**
	 * 年度数据抽取
	 */
	@RequestMapping(value = "/yearcreate", method = RequestMethod.GET)
	private String yearcreate(Model model) {			
		return "report/yearcreate";
	}	
	
	/**
	 * 月度数据抽取
	 */
	@RequestMapping(value = "/monthcreate", method = RequestMethod.GET)
	private String monthcreate(Model model) {			
		return "report/monthcreate";
	}	
	
	/**
	 * 季度数据抽取
	 */
	@RequestMapping(value = "/seacreate", method = RequestMethod.GET)
	private String seacreate(Model model) {			
		return "report/seacreate";
	}	
	
	
	
	/**
	 * 年度查询
	 */
	@RequestMapping(value = "/yearquery", method = RequestMethod.GET)
	private String yearquery(Model model) {			
		return "report/yearquery";
	}
	
	/**
	 * 季度查询
	 */
	@RequestMapping(value = "/seaquery", method = RequestMethod.GET)
	private String seaquery(Model model) {			
		return "report/seaquery";
	}
	
	/**
	 * 月度查询
	 */
	@RequestMapping(value = "/monthquery", method = RequestMethod.GET)
	private String monthquery(Model model) {			
		return "report/monthquery";
	}	
	



	/**
	 * 年度报表生成记录
	 * @param limit
	 * @param offset
	 * @param unit
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/recode_list_json", method = RequestMethod.GET, produces = {
	"application/json; charset=utf-8" })
	@ResponseBody	
	private PageResult getRecodeList(int limit, int offset,String type){
				

		//构建条件
		TOutReportstExample sample = new TOutReportstExample();
		
		sample.setOrderByClause("REPDATE DESC");
		
		TOutReportstExample.Criteria criteria = sample.createCriteria();

		if(StringUtils.isNotEmpty(type)){					
			criteria.andReptypeEqualTo(type);		
		}		

		//分页条件
		sample.setLimit(offset+limit);
		sample.setOffset(offset+1);		

		long total = tOutReportstMapper.countByExample(sample);
		List<TOutReportst> list =  tOutReportstMapper.selectPageByExample(sample);
						
		//构建返回值
		PageResult ret = new PageResult(true,list,Integer.parseInt(total+""));			
		System.out.println("----------"+ret);	

		return ret;
			
	}
	
	
	
	
	
	
	/**
	 * 报告详细数据
	 * @param limit
	 * @param offset
	 * @param name 报表名字
	 * @param reqdate
	 * @param type 报表类型
	 * @return
	 */
	@RequestMapping(value = "/data_list_json", method = RequestMethod.GET, produces = {
	"application/json; charset=utf-8" })
	@ResponseBody	
	private PageResult getDataList(int limit, int offset,String unit,String name,String reqdate,String type){
				

		//构建条件
		TOutReportExample sample = new TOutReportExample();
		
		//sample.setOrderByClause("REPDATE DESC");
		
		TOutReportExample.Criteria criteria = sample.createCriteria();

		

		if(StringUtils.isNotEmpty(name)){					
			criteria.andNameEqualTo(name);
		
		}
		
		
		if(StringUtils.isNotEmpty(unit)){					
			criteria.andUnitEqualTo(unit);
		
		}		

		if(StringUtils.isNotEmpty(type)){					
			criteria.andReptypeEqualTo(type);
		
		}
		
		if(StringUtils.isNotEmpty(reqdate)){
			Date rdate = DateUtil.parseDate(reqdate,DateUtil.C_DATE_PATTON_DEFAULT);
			criteria.andRepdateEqualTo(rdate);
			
		}

		
		//分页条件
		sample.setLimit(offset+limit);
		sample.setOffset(offset+1);		

		long total = tOutReportMapper.countByExample(sample);
		List<TOutReport> list =  tOutReportMapper.selectPageByExample(sample);
						
		//构建返回值
		PageResult ret = new PageResult(true,list,Integer.parseInt(total+""));			
		System.out.println("----------"+ret);	

		return ret;
			
	}
	
	
	

	

	
	/**
	 * 年度报表生产
	 * @param jsonstr
	 * @return
	 */
	@RequestMapping(value = "/createyear", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody  
    public String createYearReport(@RequestBody String qdate) {
		
		
		logger.info("createYearReport======="+qdate); 
		
		
		//检测之前是否生成过
		if(reportService.checkReport("年报", qdate)){
			return "数据已被抽取！";
		}
		
		
		//创建
		if(reportService.createReport("年报", qdate)){
			return "数据已被抽取,创建年度报表成功！";
		}

		return "抽取数据失败！";
		
    }
	
	
	
	
	/**
	 * 年度报表生产
	 * @param jsonstr
	 * @return
	 */
	@RequestMapping(value = "/createsea", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody  
    public String createSeaReport(@RequestBody String qdate) {
		
		
		logger.info("createSeaReport======="+qdate); 
		
		
		//检测之前是否生成过
		if(reportService.checkReport("季报", qdate)){
			return "数据已被抽取！";
		}
		
		
		//创建
		if(reportService.createReport("季报", qdate)){
			return "数据已被抽取,创建季度报表成功！";
		}

		return "抽取数据失败！";
		
    } 
	
	
	
	/**
	 * 年度报表生产
	 * @param jsonstr
	 * @return
	 */
	@RequestMapping(value = "/createmonth", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody  
    public String createMonthReport(@RequestBody String qdate) {
		
		
		logger.info("createYearReport======="+qdate); 
		
		
		//检测之前是否生成过
		if(reportService.checkReport("月报", qdate)){
			return "数据已被抽取！";
		}
		
		
		//创建
		if(reportService.createReport("月报", qdate)){
			return "数据已被抽取,创建月报表成功！";
		}

		return "抽取数据失败！";
		
    } 	
	
	
	
	
	
	
	
	
	

	/**********************************业务接口*****************************end*********/
	
	
	
	
	
	/**********************************报表展示*****************************start*********/
	
	@RequestMapping(value = "/showreport", method = RequestMethod.GET)
	private String showReport(Model model,  @RequestParam("id") String id) {	
		
		//取出报表对应的模板	
		TOutReport report = tOutReportMapper.selectByPrimaryKey(id);
		String repName = report.getName();
		String repType = report.getReptype();
		String jsfile = "";
		if("年报".equals(repType)){
			jsfile = ReportServiceImpl.YEAR_REPORTTMP.get(repName);
		} else if("季报".equals(repType)){
			jsfile = ReportServiceImpl.SEC_REPORTTMP.get(repName);
		} else {
			jsfile = ReportServiceImpl.MONTH_REPORTTMP.get(repName);
		}

		//id传到页面
		model.addAttribute("report", report);
		model.addAttribute("jsfile", jsfile);
		model.addAttribute("repdate", DateUtil.format(report.getRepdate()));
		return "report/z_tmp_exl";
	}
	
	
		
	/**
	 * 报告详细数据(单个报表）
	 * @param limit
	 * @param offset
	 * @param name 报表名字
	 * @param reqdate
	 * @param type 报表类型
	 * @return
	 */
	@RequestMapping(value = "/getexl_json", method = RequestMethod.GET, produces = {
	"application/json; charset=utf-8" })
	@ResponseBody	
	private PageResult getExlList(String id){
		
		//直接把表中对应的json数据返回
		TOutReport tOutReport =  tOutReportMapper.selectByPrimaryKey(id);
		
		//构建返回
		String jsondata = tOutReport.getRepdata();
		logger.info("报表数据==========="+jsondata);
		
		//转成对象数组
		List<ReportData> list = JsonUtil.gson.fromJson(jsondata,  new TypeToken<List<ReportData>>(){}.getType());

		//数据列编号
		//list.add(0, new ReportData());
				
		//构建返回值
		return  new PageResult(true,list,list.size());	
			
	}	
	
	/**********************************报表展示*****************************end*********/
}
