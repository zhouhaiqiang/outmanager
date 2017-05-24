package com.talkweb.ei.outmanager.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
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
import com.talkweb.ei.di.common.ViewExcel;
import com.talkweb.ei.outmanager.dao.OutUserMapper;
import com.talkweb.ei.outmanager.model.OutCompany;
import com.talkweb.ei.outmanager.model.OutCompanyExample;
import com.talkweb.ei.outmanager.model.OutCompanyExample.Criteria;
import com.talkweb.ei.outmanager.model.OutContract;
import com.talkweb.ei.outmanager.model.OutContractExample;
import com.talkweb.ei.outmanager.service.IDataService;
import com.talkweb.ei.outmanager.service.IUserService;


@Controller
@RequestMapping("/data") // url:/模块/资源/{id}/细分 /seckill/list
public class DataController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IUserService IUserService;
	
	@Autowired
	private OutUserMapper outUserMapper;
	
	
	//业务操作一般通过服务来实现
	@Autowired
	private IDataService IDataService;	
	
	
	@RequestMapping(value = "/company_list", method = RequestMethod.GET)
	
	//简单的实登录认证和跳转
	private String list(Model model) {	
		return "/company/list";
	}
	

	@RequestMapping(value = "/contract_list", method = RequestMethod.GET)
	
	//简单的实登录认证和跳转
	private String conlist(Model model) {	
		return "/contract/list";
	}	
	
	
	
	@RequestMapping(value = "/company_upload", method = RequestMethod.GET)
	
	//公司信息导入
	private String uploadCompany(Model model) {	
		return "/company/upload";
	}
	
	@RequestMapping(value = "/contract_upload", method = RequestMethod.GET)
	
	//公司信息导入
	private String uploadContract(Model model) {	
		return "/contract/upload";
	}	

	// ajax json
	@RequestMapping(value = "/company_list_json", method = RequestMethod.GET, produces = {
			"application/json; charset=utf-8" })
	@ResponseBody	
	
	/**
	 * 分页查询公司
	 * @param limit
	 * @param offset
	 * @param type
	 * @param unit
	 * @param name
	 * @return
	 */
	private PageResult getCompanyList(int limit, int offset, String type,String unit,String name){
		

		//构建条件
		OutCompanyExample sample = new OutCompanyExample();
		
		OutCompanyExample.Criteria criteria = sample.createCriteria();
		if(StringUtils.isNotEmpty(type)){
			criteria.andConTypeEqualTo(type);
		}
		if(StringUtils.isNotEmpty(unit)){
			criteria.andUnitLike("%"+unit+"%");
		}
		if(StringUtils.isNotEmpty(name)){
			criteria.andNameLike("%"+name+"%");
		}

		int total = IDataService.getCompanySize(sample);
		List<OutCompany> list = IDataService.getCompanyList(limit, offset, sample);
		
		
		//构建返回值
		PageResult ret = new PageResult(true,list,total);			
				
		return ret;
			
	}
	
	
	
	// ajax json
	@RequestMapping(value = "/contract_list_json", method = RequestMethod.GET, produces = {
			"application/json; charset=utf-8" })
	@ResponseBody	
	
	/**
	 * 分页查询合同
	 * @param limit
	 * @param offset
	 * @param type
	 * @param unit
	 * @param name
	 * @return
	 */
	private PageResult getContractList(int limit, int offset, String type,String unit,String name){
		

		//构建条件
		OutContractExample sample = new OutContractExample();
		
		OutContractExample.Criteria criteria = sample.createCriteria();
		if(StringUtils.isNotEmpty(type)){
			criteria.andConTypeEqualTo(type);
		}
		if(StringUtils.isNotEmpty(unit)){
			criteria.andUnitLike("%"+unit+"%");
		}
		if(StringUtils.isNotEmpty(name)){
			criteria.andNameLike("%"+name+"%");
		}

		int total = IDataService.getContractSize(sample);
		List<OutContract> list = IDataService.getContractList(limit, offset, sample);
		
		
		//构建返回值
		PageResult ret = new PageResult(true,list,total);			
		//System.out.println("----------"+ret);	
		return ret;
			
	}
	
	
	  
	/**
	 * 导出公司信息到excel
	 * @param type
	 * @param unit
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/exportcompany", method = RequestMethod.GET)
	private ModelAndView exportCompanyList(ModelMap model,String type,String unit,String name){
		

		//构建条件
		OutCompanyExample sample = new OutCompanyExample();
		
		Criteria criteria = sample.createCriteria();
		if(StringUtils.isNotEmpty(type)){
			criteria.andConTypeEqualTo(type);
		}
		if(StringUtils.isNotEmpty(unit)){
			criteria.andUnitLike("%"+unit+"%");
		}
		if(StringUtils.isNotEmpty(name)){
			criteria.andNameLike("%"+name+"%");
		}

		//int total = IDataService.getCompanySize(sample);
		List<OutCompany> list = IDataService.getCompanyList(ExcelUtil.MAXEXPORTNUM, 0, sample);
		
        //varList 数据（二维数据）
        List<List> varList =  new ArrayList<List>();
        List<String> line;
		for (OutCompany company:list) {
			 line = new ArrayList<String>();
	         line.add(company.getUnit());
	         line.add(company.getConType());
	         line.add(company.getName());
	         line.add(company.getZizhi());
	         line.add(company.getArea());
	         
	         line.add(company.getBoss());
	         line.add(company.getAddress());
	         line.add(company.getZijin());
	         line.add(company.getZhizhao());
	         line.add(company.getZizhidoc());	
	         	         
	         varList.add(line);
		}
		
		
		
		//titles  列标题
        List<String> titles = new ArrayList<String>();
        
        //基本信息
        titles.add("组织名称");
        titles.add("公司类型");
        titles.add("外包公司名称");
        titles.add("外包公司资质");
        titles.add("经营范围");
        
        titles.add("法定代表人");
        titles.add("注册地址");
        titles.add("注册资本(万元)");
        titles.add("营业执照复印件");
        titles.add("外包公司资质复印件");	        
        

        //数据传递
        model.put("name", "外包公司"); 
        model.put("titles", titles); 
        model.put("varList", varList);
        ViewExcel viewExcel = new ViewExcel();    
        return new ModelAndView(viewExcel, model);   
	
			
	}
	
	
	
	
	
	/**
	 * 导出合同信息到excel
	 * @param type
	 * @param unit
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/exportcontract", method = RequestMethod.GET)
	private ModelAndView exportContractList(ModelMap model,String type,String unit,String name){
		

		//构建条件
		OutContractExample sample = new OutContractExample();
		
		OutContractExample.Criteria criteria = sample.createCriteria();
		if(StringUtils.isNotEmpty(type)){
			criteria.andConTypeEqualTo(type);
		}
		if(StringUtils.isNotEmpty(unit)){
			criteria.andUnitLike("%"+unit+"%");
		}
		if(StringUtils.isNotEmpty(name)){
			criteria.andNameLike("%"+name+"%");
		}

		List<OutContract> list = IDataService.getContractList(ExcelUtil.MAXEXPORTNUM, 0, sample);
		
        //varList 数据（二维数据）
        List<List> varList =  new ArrayList<List>();
        List<String> line;
		for (OutContract company:list) {
			 line = new ArrayList<String>();
	         line.add(company.getUnit());
	         line.add(company.getConType());
	         line.add(company.getName());
	         line.add(company.getCompanyid());
	         line.add(company.getConCode());
	         
	         line.add(DateUtil.format(company.getStartdate()));
	         line.add(DateUtil.format(company.getEnddate()));
	         line.add(company.getQixian());
	         line.add(company.getAtts());
	         line.add(company.getBuchong());
	         
	         //合同涉及的业务	合同金额
	         line.add(company.getYewu());
	         line.add(company.getJine());	         
	         line.add(company.getId());
	         	         
	         varList.add(line);
		}
		
		
		
		//titles  列标题
        List<String> titles = new ArrayList<String>();
        
        //基本信息
        // 合同签署主体	合同类型	签订的外包合同名称	外包公司名称	合同编号
        titles.add("合同签署主体");
        titles.add("公司类型");
        titles.add("合同名称");
        titles.add("外包公司名称");        
        titles.add("合同编号");
        
        //开始日期	结束日期	期限	合同文本	补充合同协议	合同涉及的业务	合同金额	校验标识
        titles.add("开始日期");       
        titles.add("结束日期");
        titles.add("期限");
        titles.add("合同文本");
        titles.add("补充合同协议");
        titles.add("合同涉及的业务");
        titles.add("合同金额");
        titles.add("校验标识");
        
       	

        //数据传递
        model.put("name", "外包合同"); 
        model.put("titles", titles); 
        model.put("varList", varList);
        ViewExcel viewExcel = new ViewExcel();    
        return new ModelAndView(viewExcel, model);   
	
			
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/**
     * 上传公司信息
     * @param file
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/company_import")
    public String importCompany(
            @RequestParam(value = "excelFile", required = false) MultipartFile file, ModelMap model)
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
        
        
        
        
        //读取excel 的内容 防止内存溢出
        List<String> exlValues = ExcelUtil.readFileExcel(rootPath+fileName,0,5,ExcelUtil.MAXEXPORTNUM+5,ExcelUtil.GS_COL_NUM);
       
        String result = "导入成功！";
        
        boolean ret = IDataService.importCompany(exlValues);
        if(!ret) {
        	result = "导入失败！请检测数据文件格式！";
        }
             
        model.addAttribute("msg", result);
     

        return "/common/showmsg";
    } 
    
    
    
    
    
	/**
     * 上传公司信息
     * @param file
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/contract_import")
    public String importContract(
            @RequestParam(value = "excelFile", required = false) MultipartFile file, ModelMap model)
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
        
        
        
        
        //读取excel 的内容 防止内存溢出
        List<String> exlValues = ExcelUtil.readFileExcel(rootPath+fileName,0,5,ExcelUtil.MAXEXPORTNUM+5,ExcelUtil.CON_COL_NUM);
       
        String result = "导入成功！";
        
        boolean ret = IDataService.importContract(exlValues);
        if(!ret) {
        	result = "导入失败！请检测数据文件格式！";
        }
             
        model.addAttribute("msg", result);
     

        return "/common/showmsg";
    }   
    
    
        

	/**
	 * 添加或者更新一条记录
	 * @param jsonstr
	 * @return
	 */
	@RequestMapping(value = "/company_update", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody  
    public String updateCompany(@RequestBody String jsonstr) {
		
		
		logger.info("update company======="+jsonstr);   

		OutCompany company = JsonUtil.gson.fromJson(jsonstr,OutCompany.class);  
		
		if(company!=null){
			
			//页面上没有ID说明是新增
			if(StringUtils.isEmpty(company.getId())){
				company.setId(UUID.randomUUID().toString());
				IDataService.addCompany(company);
			} else {				
				IDataService.updateCompany(company);
			}
		}

		return "OK";
		
    } 	
	
	/**
	 * 添加或者更新一条记录
	 * @param jsonstr
	 * @return
	 */
	@RequestMapping(value = "/contract_update", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody  
    public String updateContract(@RequestBody String jsonstr) {
		
		
		logger.info("contract_update======="+jsonstr);   
			
		OutContract company = JsonUtil.gson.fromJson(jsonstr,OutContract.class);  
		
		if(company!=null){
			
			//页面上没有ID说明是新增
			if(StringUtils.isEmpty(company.getId())){
				company.setId(UUID.randomUUID().toString());
				IDataService.addContract(company);
			} else {				
				IDataService.updateContract(company);
			}
		}

		return "OK";
		
    } 	
	
	
	
	
	
	
	
	@RequestMapping(value = "/company_del", method = RequestMethod.POST)
    @ResponseBody  
    public String delCompany(@RequestBody String jsonstr) {
		
		
		logger.info("del company======="+jsonstr);   
	
		Gson gson = new Gson();
		List<OutCompany> retList = gson.fromJson(jsonstr,  
                new TypeToken<List<OutCompany>>() {  
                }.getType());  

		for(OutCompany element:retList)
        {
			IDataService.deleteCompany(element);
        }
		
		return "OK";
		
    } 
	
	@RequestMapping(value = "/contract_del", method = RequestMethod.POST)
    @ResponseBody  
    public String delContract(@RequestBody String jsonstr) {
		
		
		logger.info("delContract======="+jsonstr);   
	
		//Gson gson = new Gson();
		List<OutContract> retList = JsonUtil.gson.fromJson(jsonstr,  
                new TypeToken<List<OutContract>>() {  
                }.getType());  

		for(OutContract element:retList)
        {
			IDataService.deleteContract(element);
        }
		
		return "OK";
		
    } 	
	

}
