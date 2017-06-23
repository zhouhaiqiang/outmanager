package com.talkweb.ei.outmanager.web;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
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
import com.talkweb.ei.outmanager.dao.TOutGongziMapper;
import com.talkweb.ei.outmanager.dao.TOutJthyMapper;
import com.talkweb.ei.outmanager.dao.TOutUserFpinfoMapper;
import com.talkweb.ei.outmanager.dao.TOutUserHtMapper;
import com.talkweb.ei.outmanager.dao.TOutUserJcMapper;
import com.talkweb.ei.outmanager.dao.TOutUserJninfoMapper;
import com.talkweb.ei.outmanager.dao.TOutUserJyinfoMapper;
import com.talkweb.ei.outmanager.dao.TOutUserZyinfoMapper;
import com.talkweb.ei.outmanager.dao.VOutUsergzMapper;
import com.talkweb.ei.outmanager.model.OutUser;
import com.talkweb.ei.outmanager.model.OutUserExample;
import com.talkweb.ei.outmanager.model.OutUser_S;
import com.talkweb.ei.outmanager.model.TOutGongzi;
import com.talkweb.ei.outmanager.model.TOutGongziExample;
import com.talkweb.ei.outmanager.model.TOutJthy;
import com.talkweb.ei.outmanager.model.TOutJthyExample;
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
import com.talkweb.ei.outmanager.model.VOutUsergz;
import com.talkweb.ei.outmanager.model.VOutUsergzExample;
import com.talkweb.ei.outmanager.service.IUserGz;

/**
 * 用户管理的信息操作
 * @author zhq
 *
 */
@Controller
@RequestMapping("/gongzi") // url:/模块/资源/{id}/细分 /seckill/list
public class GongziController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private VOutUsergzMapper vOutUsergzMapper;
	
	
	@Autowired
	private IUserGz iUserGz;
	
	
	

	@Autowired
	private TOutGongziMapper tOutGongziMapper;	
	
	@Autowired
	private TOutJthyMapper tOutJthyMapper;		

	/**********************************业务接口***************************start***********/
	
	
	/***************费用部分***********************start***********/
	
	/**
	 * 个人费用维护
	 */
	@RequestMapping(value = "/grlist", method = RequestMethod.GET)
	private String grlist(Model model) {			
		return "gongzi/listgr";
	}	
	
	/**
	 * 集体费用维护
	 */	
	@RequestMapping(value = "/jtlist", method = RequestMethod.GET)
	private String jtlist(Model model) {		
		return "gongzi/listjt";
	}	
	
	
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	
	//公司信息导入
	private String uploadCompany(Model model) {	
		return "/gongzi/upload";
	}
	
	
	

	/**
	 * 分页查询工人工资
	 * @param limit
	 * @param offset
	 * @param companyid
	 * @param unit
	 * @param username
	 * @param usercode
	 * @param concode
	 * @param startmonth
	 * @param endmonth
	 * @return
	 */
	@RequestMapping(value = "/grlist_json", method = RequestMethod.GET, produces = {
	"application/json; charset=utf-8" })
	@ResponseBody	
	private PageResult getGrList(int limit, int offset, String companyid,String unit,String username, String usercode,String concode,String startmonth,String endmonth){
				

		//构建条件
		VOutUsergzExample sample = new VOutUsergzExample();
		
		
		VOutUsergzExample.Criteria criteria = sample.createCriteria();

		if(StringUtils.isNotEmpty(companyid)){
			criteria.andCompanyidEqualTo(companyid);
		}
		if(StringUtils.isNotEmpty(unit)){
			criteria.andUnitEqualTo(unit);
		}
		if(StringUtils.isNotEmpty(username)){
			criteria.andNameEqualTo(username);
		}
		
		if(StringUtils.isNotEmpty(usercode)){
			criteria.andCodeEqualTo(usercode);
		}
		
		if(StringUtils.isNotEmpty(concode)){
			criteria.andConcodeEqualTo(concode);
		}
		
		//时间段的查询
		if(StringUtils.isNotEmpty(startmonth)&&StringUtils.isNotEmpty(endmonth)){
			criteria.andMonthBetween(startmonth, endmonth);
		}		
	

		
		sample.setLimit(offset+limit);
		sample.setOffset(offset+1);
		List<VOutUsergz> list =  iUserGz.getUserGzList(limit, offset, sample);				
		int total = iUserGz.getUserGzSize(sample);
		
		//构建返回值
		PageResult ret = new PageResult(true,list,total);			
		System.out.println("----------"+ret);	
		return ret;
			
	}
	
	
	
	/**
	 * 集体费用
	 * 分页查询分配信息
	 * @param uid
	 * @return
	 */
	
	@RequestMapping(value = "/jtlist_json", method = RequestMethod.GET, produces = {
	"application/json; charset=utf-8" })
	@ResponseBody	
	private PageResult getJtList(int limit, int offset, String companyid,String unit,String username, String usercode,String concode,String startmonth,String endmonth){
				

		//构建条件
		TOutJthyExample sample = new TOutJthyExample();
		
		
		TOutJthyExample.Criteria criteria = sample.createCriteria();
		
		if(StringUtils.isNotEmpty(companyid)){
			criteria.andCompanyidEqualTo(companyid);
		}
		if(StringUtils.isNotEmpty(unit)){
			criteria.andUnitEqualTo(unit);
		}
		
		
		if(StringUtils.isNotEmpty(concode)){
			criteria.andConcodeEqualTo(concode);
		}
		
		//时间段的查询
		if(StringUtils.isNotEmpty(startmonth)&&StringUtils.isNotEmpty(endmonth)){
			criteria.andMonthBetween(startmonth, endmonth);
		}		

		sample.setLimit(offset+limit);
		sample.setOffset(offset+1);		
		
		
	
		long total = tOutJthyMapper.countByExample(sample);
		List<TOutJthy> list = tOutJthyMapper.selectPageByExample(sample);
						
		//构建返回值
		PageResult ret = new PageResult(true,list,Integer.parseInt(total+""));			
		System.out.println("----------"+ret);	
		return ret;
			
	}	
	

	
	/**
	 * 添加或更新
	 * @param jsonstr
	 * @return
	 */
	@RequestMapping(value = "/grupdate", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody  
    public String updateGrgongzi(@RequestBody String jsonstr) {
		
		
		logger.info("grgz_update======="+jsonstr);   
			
		TOutGongzi tOutGongzi = JsonUtil.gson.fromJson(jsonstr,TOutGongzi.class);  
		
		if(tOutGongzi!=null){
			
			//页面上没有ID说明是新增
			if(StringUtils.isEmpty(tOutGongzi.getId())){
				tOutGongzi.setId(UUID.randomUUID().toString());
								
				tOutGongziMapper.insert(tOutGongzi);
			} else {				
				tOutGongziMapper.updateByPrimaryKey(tOutGongzi);
			}
		}

		return "OK";
		
    } 
	
	
	
	/**
	 * 添加或更新
	 * @param jsonstr
	 * @return
	 */
	@RequestMapping(value = "/jtupdate", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody  
    public String updateJtgongzi(@RequestBody String jsonstr) {
		
		
		logger.info("user_update======="+jsonstr);   
			
		TOutJthy outUser = JsonUtil.gson.fromJson(jsonstr,TOutJthy.class);  
		
		if(outUser!=null){
			
			//页面上没有ID说明是新增
			if(StringUtils.isEmpty(outUser.getId())){
				outUser.setId(UUID.randomUUID().toString());
								
				tOutJthyMapper.insert(outUser);
			} else {				
				tOutJthyMapper.updateByPrimaryKey(outUser);
			}
		}

		return "OK";
		
    } 	
	
	
	

	@RequestMapping(value = "/grdel", method = RequestMethod.POST)
    @ResponseBody  
    public String delGr(@RequestBody String jsonstr) {
		
		
		logger.info("del user======="+jsonstr);   
	
		
		List<TOutGongzi> retList = JsonUtil.gson.fromJson(jsonstr,  
                new TypeToken<List<TOutGongzi>>() {  
                }.getType());  

		for(TOutGongzi element:retList)
        {
			tOutGongziMapper.deleteByPrimaryKey(element.getId());
        }
		
		return "OK";
		
    } 
	
	
	

	@RequestMapping(value = "/jtdel", method = RequestMethod.POST)
    @ResponseBody  
    public String delJt(@RequestBody String jsonstr) {
		
		
		logger.info("del user======="+jsonstr);   
	
		
		List<TOutJthy> retList = JsonUtil.gson.fromJson(jsonstr,  
                new TypeToken<List<TOutJthy>>() {  
                }.getType());  

		for(TOutJthy element:retList)
        {
			tOutJthyMapper.deleteByPrimaryKey(element.getId());
        }
		
		return "OK";
		
    } 
	
	/**
     * 用户信息导入（多sheet导入）
     * @param file
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/grimport")
    
    //事务开启
    //@Transactional 
    public String grimport(
            @RequestParam(value = "excelFile", required = false) MultipartFile file, @RequestParam("modeltype") String modeltype, ModelMap model)
    {
    	
        // 创建根路径的保存变量
        String rootPath;
        SimpleDateFormat format = new SimpleDateFormat("yyyy\\MM\\dd\\");
        String subpath = format.format(new Date());

        rootPath = Const.TMPFILE_ROOT + subpath;

        System.out.println("开始...");
      
        String fileName = file.getOriginalFilename();
       
       
        File targetFile = new File(rootPath, fileName);
        if (!targetFile.exists())
        {
            targetFile.mkdirs();
        }

        // 保存
        try
        {
            file.transferTo(targetFile);
            
            System.out.println("文件上传ok...");
        } catch (Exception e)
        {
            //e.printStackTrace();
            System.out.println("文件上传err...");
        }
        List<String> exlValues;
        boolean ret =  false;
        //读取数据和导入到db
        if("0".equals(modeltype)){
        	exlValues = ExcelUtil.readFileExcel(rootPath+fileName,0,5,ExcelUtil.MAXEXPORTNUM+5,ExcelUtil.HY_COL_NUM_GR);
        	ret = iUserGz.importGrhy(exlValues);
        } else {
        	exlValues = ExcelUtil.readFileExcel(rootPath+fileName,1,5,ExcelUtil.MAXEXPORTNUM+5,ExcelUtil.HY_COL_NUM_JT);
        	ret = iUserGz.importJthy(exlValues);
        }

        String result = "导入成功！";

        if(!ret) {
        	result = "导入失败！请检测数据文件格式！";
        }
        
        model.addAttribute("msg", result);
        return "/common/showmsg";
    }
	
	
	
	/**
	 * 导出合同信息到excel
	 * @return
	 */
	@RequestMapping(value = "/grexport", method = RequestMethod.GET)
	private ModelAndView grexport(ModelMap model){
		
		//构建条件
		VOutUsergzExample sample = new VOutUsergzExample();
		
		
		VOutUsergzExample.Criteria criteria = sample.createCriteria();
		
		int limit = iUserGz.getUserGzSize(sample);

		List<VOutUsergz> list = iUserGz.getUserGzList(limit, 0, sample);

        //varList 数据（二维数据）
        List<List<?>> varList =  new ArrayList<List<?>>();
        List<String> line;
        
        OutUser mapuser;
        
		for (VOutUsergz user:list) {
			 line = new ArrayList<String>();
			 
			 BigDecimal xj = new BigDecimal("0");
		 
			 line.add("");
			 			 
			 //操作模式	人员编号	姓名	身份证号码	归属外包公司名称
	         line.add(user.getCode());
	         line.add(user.getName());	         
	         line.add(user.getIdnumber());	         
	         line.add(user.getCompanyid());


	         
	         //发薪月度	固定工资	绩效工资	津贴补贴	过节费
	         line.add(user.getMonth());
	         line.add(StringUtils.formatBigDecimal(user.getJiben()));
	         xj = xj.add(user.getJiben());
	         
	         line.add(StringUtils.formatBigDecimal(user.getJixiao()));
	         xj = xj.add(user.getJixiao());
	         
	         line.add(StringUtils.formatBigDecimal(user.getJintie()));
	         xj = xj.add(user.getJintie());
	         
	         line.add(StringUtils.formatBigDecimal(user.getGuojie()));
	         xj = xj.add(user.getGuojie());
	         
	         //加班工资	其他工资性支出	应发金额	税前扣款项	税后扣款项
	         line.add(StringUtils.formatBigDecimal(user.getJiaban()));
	         xj = xj.add(user.getJiaban());
	         
	         line.add(StringUtils.formatBigDecimal(user.getQtgz()));
	         xj = xj.add(user.getQtgz());
	         
	         line.add(StringUtils.formatBigDecimal(user.getYfa()));	  
	         xj = xj.add(user.getYfa());
	         
	         line.add(StringUtils.formatBigDecimal(user.getSxkk()));
	         xj = xj.add(user.getSxkk());
	         
	         line.add(StringUtils.formatBigDecimal(user.getShkk()));
	         xj = xj.add(user.getShkk());
	         
	         
	         //社保公积金扣减额	个人所得税扣缴额	实发金额	养老保险	生育保险
	         line.add(StringUtils.formatBigDecimal(user.getGongji()));
	         xj = xj.add(user.getGongji());
	         
	         line.add(StringUtils.formatBigDecimal(user.getGeren()));
	         xj = xj.add(user.getGeren());
	         
	         line.add(StringUtils.formatBigDecimal(user.getShifa()));	  
	         xj = xj.add(user.getShifa());
	         
	         line.add(StringUtils.formatBigDecimal(user.getYanglao()));
	         xj = xj.add(user.getYanglao());
	         
	         line.add(StringUtils.formatBigDecimal(user.getShengyu()));	
	         xj = xj.add(user.getShengyu());
	         
	         ///失业保险	医疗保险	工伤保险	公积金	小计
	         line.add(StringUtils.formatBigDecimal(user.getShiye()));
	         xj = xj.add(user.getShiye());
	         
	         line.add(StringUtils.formatBigDecimal(user.getYiliao()));	
	         xj = xj.add(user.getYiliao());
	         
	         line.add(StringUtils.formatBigDecimal(user.getGongshang()));
	         xj = xj.add(user.getGongshang());
	         
	         line.add(StringUtils.formatBigDecimal(user.getGongji()));
	         xj = xj.add(user.getGongji());
	         
	         //小计
	         line.add(StringUtils.formatBigDecimal(xj));	         
	         
	         //为本企业服务起始日期	在本企业缴纳社会保险起始日期	工会会费	管理费	税金	其他人工支出项目	备注
	         line.add(DateUtil.format(user.getStartfwdate()));
	         line.add(DateUtil.format(user.getStartbxdate()));	         
	          
	         line.add(StringUtils.formatBigDecimal(user.getGonghui()));
	         line.add(StringUtils.formatBigDecimal(user.getGuanli()));	  
	         line.add(StringUtils.formatBigDecimal(user.getShuijin()));
	         line.add(StringUtils.formatBigDecimal(user.getQtjine()));	  	         
	         line.add(user.getRemark());	       
	         
	         line.add(user.getId());
	         	         
	         varList.add(line);
		}
		
		
		
		//titles  列标题
        List<String> titles = new ArrayList<String>();
        
        //操作模式	人员编号	姓名	身份证号码	归属外包公司名称	

        titles.add("操作模式");
        titles.add("人员编号");
        titles.add("姓名"); 
        titles.add("身份证号码");        
        titles.add("归属外包公司名称");
        
        //发薪月度	固定工资	绩效工资	津贴补贴	过节费	
        titles.add("发薪月度");
        titles.add("固定工资");
        titles.add("绩效工资");
        titles.add("津贴补贴");
        titles.add("过节费");
        
        //加班工资	其他工资性支出	应发金额	税前扣款项	税后扣款项	
        titles.add("加班工资");
        titles.add("其他工资性支出");
        titles.add("应发金额");
        titles.add("税前扣款项");
        titles.add("税后扣款项");
        
        //社保公积金扣减额	个人所得税扣缴额	实发金额	养老保险	生育保险
        titles.add("社保公积金扣减额");
        titles.add("个人所得税扣缴额");
        titles.add("实发金额");
        titles.add("养老保险");
        titles.add("生育保险");       

        
        //失业保险	医疗保险	工伤保险	公积金	小计	
        titles.add("失业保险");
        titles.add("医疗保险");
        titles.add("工伤保险");
        titles.add("公积金");
        titles.add("小计");          
        
        //为本企业服务起始日期	在本企业缴纳社会保险起始日期	工会会费	管理费	税金	其他人工支出项目	备注        
        titles.add("为本企业服务起始日期");
        titles.add("在本企业缴纳社会保险起始日期");
        titles.add("工会会费");
        titles.add("管理费");
        titles.add("税金");            
        titles.add("其他人工支出项目");
        titles.add("备注");         
        
        titles.add("校验标识");
        
        //数据传递
        model.put("name", "外包人员个人费用信息"); 
        model.put("titles", titles); 
        model.put("varList", varList);
        ViewExcel viewExcel = new ViewExcel();    
        return new ModelAndView(viewExcel, model);
	}
	
	
	
	/**
	 * 导出excel
	 * @param type 用户信息类型，比如基本信息等等
	 * @return
	 */
	@RequestMapping(value = "/jtexport", method = RequestMethod.GET)
	private ModelAndView exportjt(ModelMap model,String type){
		
		//构建条件
		TOutJthyExample sample = new TOutJthyExample();
		
		
		TOutJthyExample.Criteria criteria = sample.createCriteria();
		

		List<TOutJthy> list = tOutJthyMapper.selectByExample(sample);

        //varList 数据（二维数据）
        List<List<?>> varList =  new ArrayList<List<?>>();
        List<String> line;
        
        OutUser mapuser;
        
		for (TOutJthy user:list) {
			 line = new ArrayList<String>();
			 
			
		 
			 line.add("");		 			 
			 //操作模式	发生费用所属组织	外包公司名称	外包合同编号	期间	
	         line.add(user.getUnit());
	         line.add(user.getCompanyid());
	         line.add(user.getConcode());
	         line.add(user.getMonth());
	         
	         
	         //工会会费	管理费	税金	其他人工支出项目	离职前人工费用	备注
	         line.add(StringUtils.formatBigDecimal(user.getGhhy()));
	         line.add(StringUtils.formatBigDecimal(user.getGlh()));
	         line.add(StringUtils.formatBigDecimal(user.getShuijin()));
	         line.add(StringUtils.formatBigDecimal(user.getQt()));
	         line.add(StringUtils.formatBigDecimal(user.getLzrhy()));
	         line.add(user.getRemark());

	         line.add(user.getId());
	         	         
	         varList.add(line);
		}
		
		
		
		//titles  列标题
        List<String> titles = new ArrayList<String>();
        
        //操作模式	发生费用所属组织	外包公司名称	外包合同编号	期间	
        titles.add("操作模式");
        titles.add("发生费用所属组织");
        titles.add("外包公司名称"); 
        titles.add("外包合同编号");         
        titles.add("期间");
        
        //工会会费	管理费	税金	其他人工支出项目	离职前人工费用	备注
        titles.add("工会会费");
        titles.add("管理费");
        titles.add("税金");
        titles.add("其他人工支出项目");
        titles.add("离职前人工费用");
        titles.add("备注");
        
        //额外系统关键字
        titles.add("校验标识");
               	
        //数据传递
        model.put("name", "外包集体费用信息"); 
        model.put("titles", titles); 
        model.put("varList", varList);
        ViewExcel viewExcel = new ViewExcel();    
        return new ModelAndView(viewExcel, model);
	}	
	
	/**********************************业务接口*****************************end*********/

	
}
