package com.talkweb.ei.outmanager.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
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
import com.talkweb.ei.di.common.ExcelUtil;
import com.talkweb.ei.di.common.JsonUtil;
import com.talkweb.ei.di.common.PageResult;
import com.talkweb.ei.di.common.ViewExcel;
import com.talkweb.ei.outmanager.dao.TOutDutyMapper;
import com.talkweb.ei.outmanager.dao.TOutDutymapingMapper;
import com.talkweb.ei.outmanager.dao.VOutUserdutyMapper;
import com.talkweb.ei.outmanager.model.DocBean;
import com.talkweb.ei.outmanager.model.OutUser;
import com.talkweb.ei.outmanager.model.OutUserExample;
import com.talkweb.ei.outmanager.model.OutUser_S;
import com.talkweb.ei.outmanager.model.TOutDuty;
import com.talkweb.ei.outmanager.model.TOutDutyExample;
import com.talkweb.ei.outmanager.model.TOutDutymaping;
import com.talkweb.ei.outmanager.model.TOutDutymapingExample;
import com.talkweb.ei.outmanager.model.VOutUserduty;
import com.talkweb.ei.outmanager.model.VOutUserdutyExample;
import com.talkweb.ei.outmanager.service.IUserService;


@Controller
@RequestMapping("/system") 
public class SystemManagerController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IUserService userService;
	
	@Autowired
	private VOutUserdutyMapper vOutUserdutyMapper;
	
	@Autowired
	private TOutDutymapingMapper tOutDutymapingMapper;
	
	@Autowired
	private TOutDutyMapper tOutDutyMapper;
	
	
	
	
	
	/**********************************业务接口***************************start***********/
	
	
	/**
	 * 用户查询
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/userlist", method = RequestMethod.GET)
	private String userlist(Model model) {	
		return "/system/userlist";
	}	
	

	
	
	
	@RequestMapping(value = "/doclist", method = RequestMethod.GET)
	private String dutylist(Model model,String id) {
	
		return "system/doclist";
	}	
	

	@RequestMapping(value = "/dutyupload", method = RequestMethod.GET)
	
	//公司信息导入
	private String uploadCompany(Model model) {	
		return "/system/upload";
	}
	
	
	
	
	/**
	 * 分页查询用户
	 * @param limit
	 * @param offset
	 * @param type
	 * @param unit
	 * @param name
	 * @return
	 */
	
	@RequestMapping(value = "/user_list_json", method = RequestMethod.GET, produces = {
	"application/json; charset=utf-8" })
	@ResponseBody	
	private PageResult getUserList(int limit, int offset, String companyid,String unit,String name, String concode,String code,String date){
				

		//构建条件
		OutUserExample sample = new OutUserExample();
		
		
		OutUserExample.Criteria criteria = sample.createCriteria();
		
		//只查询管理用户
		criteria.andUsertypeEqualTo("0");
		
		
		if(StringUtils.isNotEmpty(companyid)){
			criteria.andCompanyidEqualTo(companyid);
		}
		
		if(StringUtils.isNotEmpty(code)){
			criteria.andCodeEqualTo(code);
		}

		if(StringUtils.isNotEmpty(unit)){
			criteria.andUnitLike("%"+unit+"%");
		}
	
		int total = userService.getUserSize(sample);
		List<OutUser> list = userService.getUserList(limit, offset, sample);
		
		
		List<OutUser_S> list_s =  toSimpleUser(list);
		
		
		//构建返回值
		PageResult ret = new PageResult(true,list_s,total);			
		System.out.println("----------"+ret);	
		return ret;
			
	}
	
	
	
	@RequestMapping(value = "/userduyt_list_json", method = RequestMethod.GET, produces = {
	"application/json; charset=utf-8" })
	@ResponseBody	
	private PageResult getUserDutyList(String uid){
				

	    System.out.println("---取用户的职务信息-------"+uid);
		
		//构建条件
		VOutUserdutyExample sample = new VOutUserdutyExample();
		
		
		VOutUserdutyExample.Criteria criteria = sample.createCriteria();
		
		//只查询一个用户
		criteria.andUseridEqualTo(uid);

		long total = vOutUserdutyMapper.countByExample(sample);
		List<VOutUserduty> list = vOutUserdutyMapper.selectByExample(sample);
		
		//构建返回值
		PageResult ret = new PageResult(true,list,Integer.parseInt(total+""));			
			
		return ret;
			
	}	
	
	
	/**
	 * 对象简化
	 * @param list
	 * @return
	 */
	List<OutUser_S> toSimpleUser(List<OutUser> list) {
		
		List<OutUser_S> ret_list = new ArrayList<OutUser_S>();
		
		OutUser_S tmp;
		for(OutUser user:list) {
			tmp = new OutUser_S();
			tmp.setId(user.getId());
			tmp.setName(user.getName());
			tmp.setCode(user.getCode());
			tmp.setUnit(user.getUnit());
			
			tmp.setCompanyid(user.getCompanyid());
			tmp.setConcode(user.getConcode());
			tmp.setYwtype(user.getYwtype());
			
			ret_list.add(tmp);
		}
		
		return ret_list;
		
	}
	
	
	
	/**
	 * 常用文档列表
	 * @param limit
	 * @param offset
	 * @return
	 */
	@RequestMapping(value = "/doc_list_json", method = RequestMethod.GET, produces = {
	"application/json; charset=utf-8" })
	@ResponseBody	
	private PageResult getDocList(int limit, int offset,HttpSession session){
				

		//根目录的时间路径
		String realPath = session.getServletContext().getRealPath("/");
		
		//模板路基
		realPath +="/filemodel";
		
		List<DocBean> list = new ArrayList<DocBean>();
		
		
		//列出目录下的所有文件
		Collection<File> collection = FileUtils.listFiles(new File(realPath), new String[]{"xls","doc"}, true);
		
		File file;
		DocBean docBean;
		for (Iterator iterator = collection.iterator(); iterator.hasNext();) {
			file = (File) iterator.next();
			docBean = new DocBean();
			docBean.setName(file.getName());
			docBean.setUrl("/outmanager/filemodel/"+file.getName());
			
			list.add(docBean);
		}
		
		//构建返回值
		PageResult ret = new PageResult(true,list,collection.size());			
		System.out.println("----------"+ret);	
		return ret;
			
	}	

	
	/**
     * 用户信息导入（多sheet导入）
     * @param file
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/import")
    
    //事务开启
    //@Transactional 
    public String importDuty(
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
        //基本信息
        List<String> exlValues = ExcelUtil.readFileExcel(rootPath+fileName,0,4,ExcelUtil.MAXEXPORTNUM+4,ExcelUtil.USER_COL_NUM);
       
        String result = "导入成功！";
        
        boolean ret = userService.importBase(exlValues);
        if(!ret) {
        	result = "导入失败！请检测数据文件格式！";
        }
           
//        boolean ret;
//        List<String> exlValues;
//        String result = "导入成功！";
        
        
        //教育信息
        exlValues = ExcelUtil.readFileExcel(rootPath+fileName,1,4,ExcelUtil.MAXEXPORTNUM+4,ExcelUtil.USER_COL_NUM_JY);
        ret = userService.importJiaoyu(exlValues);
        if(!ret) {
        	result = "导入失败！请检测数据文件格式！";
        }      
        
        //职业技能信息
        exlValues = ExcelUtil.readFileExcel(rootPath+fileName,2,4,ExcelUtil.MAXEXPORTNUM+4,ExcelUtil.USER_COL_NUM_ZY);
        ret = userService.importZhiye(exlValues);
        if(!ret) {
        	result = "导入失败！请检测数据文件格式！";
        }  
        
        
        //专业信息
        exlValues = ExcelUtil.readFileExcel(rootPath+fileName,3,4,ExcelUtil.MAXEXPORTNUM+4,ExcelUtil.USER_COL_NUM_ZZ);
        ret = userService.importZhuanye(exlValues);
        if(!ret) {
        	result = "导入失败！请检测数据文件格式！";
        }             
           
        //劳动关系
        exlValues = ExcelUtil.readFileExcel(rootPath+fileName,4,4,ExcelUtil.MAXEXPORTNUM+4,ExcelUtil.USER_COL_NUM_LD);
        ret = userService.importLaodong(exlValues);
        if(!ret) {
        	result = "导入失败！请检测数据文件格式！";
        }       
        
        //解除关系
        exlValues = ExcelUtil.readFileExcel(rootPath+fileName,5,4,ExcelUtil.MAXEXPORTNUM+4,ExcelUtil.USER_COL_NUM_JC);
        ret = userService.importJiechu(exlValues);
        if(!ret) {
        	result = "导入失败！请检测数据文件格式！";
        }          
        
        model.addAttribute("msg", result);
     
        return "/common/showmsg";
    } 	
	
    
    
      
	/**
	 * 添加或更新
	 * @param jsonstr
	 * @return
	 */
	@RequestMapping(value = "/duty_update", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody  
    public String updateDuty(@RequestBody String jsonstr) {
		
		
		logger.info("duty_update======="+jsonstr);   
			
		TOutDutymaping tOutGongzi = JsonUtil.gson.fromJson(jsonstr,TOutDutymaping.class);  
		
		if(tOutGongzi!=null){
			
			
			//职务id填充
			if(StringUtils.isEmpty(tOutGongzi.getDutyid())){
				TOutDutyExample example = new TOutDutyExample();
				TOutDutyExample.Criteria criteria = example.createCriteria();
				
				//只查询一个用户
				criteria.andNameEqualTo(tOutGongzi.getRemark());
				
				List<TOutDuty> list = tOutDutyMapper.selectByExample(example);
				
				if(list!=null&&list.size()>0){
					tOutGongzi.setDutyid(list.get(0).getId());
				}
				
				
			}
			
			//页面上没有ID说明是新增
			if(StringUtils.isEmpty(tOutGongzi.getId())){
				tOutGongzi.setId(UUID.randomUUID().toString());
								
				tOutDutymapingMapper.insert(tOutGongzi);
			} else {				
				tOutDutymapingMapper.updateByPrimaryKey(tOutGongzi);
			}
		}

		return "OK";
		
    }     
    
    
    
	@RequestMapping(value = "/duty_del", method = RequestMethod.POST)
    @ResponseBody  
    public String delUserDuty(@RequestBody String jsonstr) {
		
		
		logger.info("delUserDuty======="+jsonstr);   
	
		Gson gson = new Gson();
		List<VOutUserduty> retList = gson.fromJson(jsonstr,  
                new TypeToken<List<VOutUserduty>>() {  
                }.getType());  

		for(VOutUserduty element:retList)
        {
			
			TOutDutymapingExample example = new TOutDutymapingExample();
			TOutDutymapingExample.Criteria criteria = example.createCriteria();
			
			//只查询一个用户
			criteria.andUseridEqualTo(element.getUserid());
			criteria.andDutyidEqualTo(element.getDutyid());
			
			tOutDutymapingMapper.deleteByExample(example);
        }
		
		return "OK";
		
    } 
	
	
	
	
	
	
	/**
	 * 导出公司信息到excel
	 * @param type
	 * @param unit
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/export", method = RequestMethod.GET)
	private ModelAndView exportUserDutys(ModelMap model,String unit,String uid){
		

		//构建条件
		VOutUserdutyExample sample = new VOutUserdutyExample();
		
		VOutUserdutyExample.Criteria criteria = sample.createCriteria();
		
		if(StringUtils.isNotEmpty(unit)){
			criteria.andUnitidEqualTo(unit);
		}
		
		if(StringUtils.isNotEmpty(uid)){
			criteria.andUseridEqualTo(uid);
		}
		
		sample.setOrderByClause("userid asc");

		List<VOutUserduty> list = vOutUserdutyMapper.selectByExample(sample);
		
        //varList 数据（二维数据）
        List<List> varList =  new ArrayList<List>();
        List<String> line;
		for (VOutUserduty company:list) {
			 line = new ArrayList<String>();

			 line.add("");			 
	         line.add(company.getName());
	         line.add(company.getUserid());
	         line.add(company.getDuty());
	         line.add("是");
	         line.add(company.getId());
        
	         varList.add(line);
		}
		
		
		
		//titles  列标题
        List<String> titles = new ArrayList<String>();
        
        //基本信息
        titles.add("操作模式");
        titles.add("姓名");
        titles.add("员工编号");
        titles.add("职责");
        titles.add("控制值");
        titles.add("校验码");
        
        

        //数据传递
        model.put("name", "用户职责"); 
        model.put("titles", titles); 
        model.put("varList", varList);
        ViewExcel viewExcel = new ViewExcel();    
        return new ModelAndView(viewExcel, model);   
	
			
	}	
	

	
	/**********************************业务接口*****************************end*********/
	
}
