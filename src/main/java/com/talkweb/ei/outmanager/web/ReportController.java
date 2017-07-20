package com.talkweb.ei.outmanager.web;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.talkweb.ei.di.common.Const;
import com.talkweb.ei.di.common.DateUtil;
import com.talkweb.ei.di.common.ExcelUtil;
import com.talkweb.ei.di.common.JsonUtil;
import com.talkweb.ei.di.common.PageResult;
import com.talkweb.ei.di.common.StringUtils;
import com.talkweb.ei.di.common.ViewExcel;
import com.talkweb.ei.outmanager.dao.TOutActionMapper;
import com.talkweb.ei.outmanager.dao.TOutGongziMapper;
import com.talkweb.ei.outmanager.dao.TOutJthyMapper;
import com.talkweb.ei.outmanager.dao.TOutReportMapper;
import com.talkweb.ei.outmanager.dao.TOutReportstMapper;
import com.talkweb.ei.outmanager.dao.TOutUserFpinfoMapper;
import com.talkweb.ei.outmanager.dao.TOutUserHtMapper;
import com.talkweb.ei.outmanager.dao.TOutUserJcMapper;
import com.talkweb.ei.outmanager.dao.TOutUserJninfoMapper;
import com.talkweb.ei.outmanager.dao.TOutUserJyinfoMapper;
import com.talkweb.ei.outmanager.dao.TOutUserZyinfoMapper;
import com.talkweb.ei.outmanager.dao.VOutUseractionMapper;
import com.talkweb.ei.outmanager.dao.VOutUsergzMapper;
import com.talkweb.ei.outmanager.model.OutUser;
import com.talkweb.ei.outmanager.model.OutUserExample;
import com.talkweb.ei.outmanager.model.OutUser_S;
import com.talkweb.ei.outmanager.model.TOutAction;
import com.talkweb.ei.outmanager.model.TOutGongzi;
import com.talkweb.ei.outmanager.model.TOutGongziExample;
import com.talkweb.ei.outmanager.model.TOutJthy;
import com.talkweb.ei.outmanager.model.TOutJthyExample;
import com.talkweb.ei.outmanager.model.TOutReport;
import com.talkweb.ei.outmanager.model.TOutReportExample;
import com.talkweb.ei.outmanager.model.TOutReportst;
import com.talkweb.ei.outmanager.model.TOutReportstExample;
import com.talkweb.ei.outmanager.model.TOutUserFpinfo;
import com.talkweb.ei.outmanager.model.TOutUserFpinfoExample;
import com.talkweb.ei.outmanager.model.TOutUserHt;
import com.talkweb.ei.outmanager.model.TOutUserHtExample;
import com.talkweb.ei.outmanager.model.TOutUserJc;
import com.talkweb.ei.outmanager.model.TOutUserJcExample;
import com.talkweb.ei.outmanager.model.TOutUserJninfo;
import com.talkweb.ei.outmanager.model.TOutUserJninfoExample;
import com.talkweb.ei.outmanager.model.TOutUserJyinfo;
import com.talkweb.ei.outmanager.model.TOutUserJyinfoExample;
import com.talkweb.ei.outmanager.model.TOutUserZyinfo;
import com.talkweb.ei.outmanager.model.TOutUserZyinfoExample;
import com.talkweb.ei.outmanager.model.VOutUseraction;
import com.talkweb.ei.outmanager.model.VOutUseractionExample;
import com.talkweb.ei.outmanager.model.VOutUsergz;
import com.talkweb.ei.outmanager.model.VOutUsergzExample;
import com.talkweb.ei.outmanager.service.IActionService;
import com.talkweb.ei.outmanager.service.IDictory;
import com.talkweb.ei.outmanager.service.IReportService;
import com.talkweb.ei.outmanager.service.IUserGz;
import com.talkweb.ei.outmanager.service.IUserService;

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

	
}
