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
 * �������
 * @author zhq
 *
 */
@Controller
@RequestMapping("/report") // url:/ģ��/��Դ/{id}/ϸ�� /seckill/list
public class ReportController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Autowired
	private TOutReportstMapper tOutReportstMapper;

	
	@Autowired
	private TOutReportMapper tOutReportMapper;	
	
	@Autowired
	private IReportService reportService;	
	


	/**********************************ҵ��ӿ�***************************start***********/
	
	/**
	 * ������ݳ�ȡ
	 */
	@RequestMapping(value = "/yearcreate", method = RequestMethod.GET)
	private String yearcreate(Model model) {			
		return "report/yearcreate";
	}	
	
	/**
	 * �¶����ݳ�ȡ
	 */
	@RequestMapping(value = "/monthcreate", method = RequestMethod.GET)
	private String monthcreate(Model model) {			
		return "report/monthcreate";
	}	
	
	/**
	 * �������ݳ�ȡ
	 */
	@RequestMapping(value = "/seacreate", method = RequestMethod.GET)
	private String seacreate(Model model) {			
		return "report/seacreate";
	}	
	
	
	
	/**
	 * ��Ȳ�ѯ
	 */
	@RequestMapping(value = "/yearquery", method = RequestMethod.GET)
	private String yearquery(Model model) {			
		return "report/yearquery";
	}
	
	/**
	 * ���Ȳ�ѯ
	 */
	@RequestMapping(value = "/seaquery", method = RequestMethod.GET)
	private String seaquery(Model model) {			
		return "report/seaquery";
	}
	
	/**
	 * �¶Ȳ�ѯ
	 */
	@RequestMapping(value = "/monthquery", method = RequestMethod.GET)
	private String monthquery(Model model) {			
		return "report/monthquery";
	}	
	



	/**
	 * ��ȱ������ɼ�¼
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
				

		//��������
		TOutReportstExample sample = new TOutReportstExample();
		
		sample.setOrderByClause("REPDATE DESC");
		
		TOutReportstExample.Criteria criteria = sample.createCriteria();

		if(StringUtils.isNotEmpty(type)){					
			criteria.andReptypeEqualTo(type);		
		}		

		//��ҳ����
		sample.setLimit(offset+limit);
		sample.setOffset(offset+1);		

		long total = tOutReportstMapper.countByExample(sample);
		List<TOutReportst> list =  tOutReportstMapper.selectPageByExample(sample);
						
		//��������ֵ
		PageResult ret = new PageResult(true,list,Integer.parseInt(total+""));			
		System.out.println("----------"+ret);	

		return ret;
			
	}
	
	
	
	
	
	
	/**
	 * ������ϸ����
	 * @param limit
	 * @param offset
	 * @param name ��������
	 * @param reqdate
	 * @param type ��������
	 * @return
	 */
	@RequestMapping(value = "/data_list_json", method = RequestMethod.GET, produces = {
	"application/json; charset=utf-8" })
	@ResponseBody	
	private PageResult getDataList(int limit, int offset,String unit,String name,String reqdate,String type){
				

		//��������
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

		
		//��ҳ����
		sample.setLimit(offset+limit);
		sample.setOffset(offset+1);		

		long total = tOutReportMapper.countByExample(sample);
		List<TOutReport> list =  tOutReportMapper.selectPageByExample(sample);
						
		//��������ֵ
		PageResult ret = new PageResult(true,list,Integer.parseInt(total+""));			
		System.out.println("----------"+ret);	

		return ret;
			
	}
	
	
	

	

	
	/**
	 * ��ȱ�������
	 * @param jsonstr
	 * @return
	 */
	@RequestMapping(value = "/createyear", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody  
    public String createYearReport(@RequestBody String qdate) {
		
		
		logger.info("createYearReport======="+qdate); 
		
		
		//���֮ǰ�Ƿ����ɹ�
		if(reportService.checkReport("�걨", qdate)){
			return "�����ѱ���ȡ��";
		}
		
		
		//����
		if(reportService.createReport("�걨", qdate)){
			return "�����ѱ���ȡ,������ȱ���ɹ���";
		}

		return "��ȡ����ʧ�ܣ�";
		
    }
	
	
	
	
	/**
	 * ��ȱ�������
	 * @param jsonstr
	 * @return
	 */
	@RequestMapping(value = "/createsea", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody  
    public String createSeaReport(@RequestBody String qdate) {
		
		
		logger.info("createSeaReport======="+qdate); 
		
		
		//���֮ǰ�Ƿ����ɹ�
		if(reportService.checkReport("����", qdate)){
			return "�����ѱ���ȡ��";
		}
		
		
		//����
		if(reportService.createReport("����", qdate)){
			return "�����ѱ���ȡ,�������ȱ���ɹ���";
		}

		return "��ȡ����ʧ�ܣ�";
		
    } 
	
	
	
	/**
	 * ��ȱ�������
	 * @param jsonstr
	 * @return
	 */
	@RequestMapping(value = "/createmonth", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody  
    public String createMonthReport(@RequestBody String qdate) {
		
		
		logger.info("createYearReport======="+qdate); 
		
		
		//���֮ǰ�Ƿ����ɹ�
		if(reportService.checkReport("�±�", qdate)){
			return "�����ѱ���ȡ��";
		}
		
		
		//����
		if(reportService.createReport("�±�", qdate)){
			return "�����ѱ���ȡ,�����±���ɹ���";
		}

		return "��ȡ����ʧ�ܣ�";
		
    } 	
	
	
	
	
	
	
	
	
	

	/**********************************ҵ��ӿ�*****************************end*********/
	
	
	
	
	
	/**********************************����չʾ*****************************start*********/
	
	@RequestMapping(value = "/showreport", method = RequestMethod.GET)
	private String showReport(Model model,  @RequestParam("id") String id) {	
		
		//ȡ�������Ӧ��ģ��	
		TOutReport report = tOutReportMapper.selectByPrimaryKey(id);
		String repName = report.getName();
		String repType = report.getReptype();
		String jsfile = "";
		if("�걨".equals(repType)){
			jsfile = ReportServiceImpl.YEAR_REPORTTMP.get(repName);
		} else if("����".equals(repType)){
			jsfile = ReportServiceImpl.SEC_REPORTTMP.get(repName);
		} else {
			jsfile = ReportServiceImpl.MONTH_REPORTTMP.get(repName);
		}

		//id����ҳ��
		model.addAttribute("report", report);
		model.addAttribute("jsfile", jsfile);
		model.addAttribute("repdate", DateUtil.format(report.getRepdate()));
		return "report/z_tmp_exl";
	}
	
	
		
	/**
	 * ������ϸ����(��������
	 * @param limit
	 * @param offset
	 * @param name ��������
	 * @param reqdate
	 * @param type ��������
	 * @return
	 */
	@RequestMapping(value = "/getexl_json", method = RequestMethod.GET, produces = {
	"application/json; charset=utf-8" })
	@ResponseBody	
	private PageResult getExlList(String id){
		
		//ֱ�Ӱѱ��ж�Ӧ��json���ݷ���
		TOutReport tOutReport =  tOutReportMapper.selectByPrimaryKey(id);
		
		//��������
		String jsondata = tOutReport.getRepdata();
		logger.info("��������==========="+jsondata);
		
		//ת�ɶ�������
		List<ReportData> list = JsonUtil.gson.fromJson(jsondata,  new TypeToken<List<ReportData>>(){}.getType());

		//�����б��
		//list.add(0, new ReportData());
				
		//��������ֵ
		return  new PageResult(true,list,list.size());	
			
	}	
	
	/**********************************����չʾ*****************************end*********/
}
