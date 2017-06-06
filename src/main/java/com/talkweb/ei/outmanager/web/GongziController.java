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
import com.talkweb.ei.outmanager.model.TOutGongziExample;
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
	
	
	
//	/**
//	 * 集体费用
//	 * 分页查询分配信息
//	 * @param uid
//	 * @return
//	 */
//	
//	@RequestMapping(value = "/jtlist_json", method = RequestMethod.GET, produces = {
//	"application/json; charset=utf-8" })
//	@ResponseBody	
//	private PageResult getJtList(String uid){
//				
//
//		//构建条件
//		TOutUserFpinfoExample sample = new TOutUserFpinfoExample();
//		
//		
//		TOutUserFpinfoExample.Criteria criteria = sample.createCriteria();
//		
//		//只查询当前用户
//		criteria.andUseridEqualTo(uid);
//		
//		List<TOutUserFpinfo> list = tOutUserFpinfoMapper.selectByExample(sample);
//		
//				
//		//构建返回值
//		PageResult ret = new PageResult(true,list,list.size());			
//		System.out.println("----------"+ret);	
//		return ret;
//			
//	}	
//	
//	
//	
//	
//	
//	
//	/**
//	 * 添加或更新
//	 * @param jsonstr
//	 * @return
//	 */
//	@RequestMapping(value = "/grupdate", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
//    @ResponseBody  
//    public String updateGrgongzi(@RequestBody String jsonstr) {
//		
//		
//		logger.info("user_update======="+jsonstr);   
//			
//		TOutUserFpinfo outUser = JsonUtil.gson.fromJson(jsonstr,TOutUserFpinfo.class);  
//		
//		if(outUser!=null){
//			
//			//页面上没有ID说明是新增
//			if(StringUtils.isEmpty(outUser.getId())){
//				outUser.setId(UUID.randomUUID().toString());
//								
//				tOutUserFpinfoMapper.insert(outUser);
//			} else {				
//				tOutUserFpinfoMapper.updateByPrimaryKey(outUser);
//			}
//		}
//
//		return "OK";
//		
//    } 
//	
//	
//	
//	/**
//	 * 添加或更新
//	 * @param jsonstr
//	 * @return
//	 */
//	@RequestMapping(value = "/jtupdate", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
//    @ResponseBody  
//    public String updateJtgongzi(@RequestBody String jsonstr) {
//		
//		
//		logger.info("user_update======="+jsonstr);   
//			
//		TOutUserFpinfo outUser = JsonUtil.gson.fromJson(jsonstr,TOutUserFpinfo.class);  
//		
//		if(outUser!=null){
//			
//			//页面上没有ID说明是新增
//			if(StringUtils.isEmpty(outUser.getId())){
//				outUser.setId(UUID.randomUUID().toString());
//								
//				tOutUserFpinfoMapper.insert(outUser);
//			} else {				
//				tOutUserFpinfoMapper.updateByPrimaryKey(outUser);
//			}
//		}
//
//		return "OK";
//		
//    } 	
//	
//	
//	
//
//	@RequestMapping(value = "/grdel", method = RequestMethod.POST)
//    @ResponseBody  
//    public String delGr(@RequestBody String jsonstr) {
//		
//		
//		logger.info("del user======="+jsonstr);   
//	
//		
//		List<TOutUserFpinfo> retList = JsonUtil.gson.fromJson(jsonstr,  
//                new TypeToken<List<TOutUserFpinfo>>() {  
//                }.getType());  
//
//		for(TOutUserFpinfo element:retList)
//        {
//			tOutUserFpinfoMapper.deleteByPrimaryKey(element.getId());
//        }
//		
//		return "OK";
//		
//    } 
//	
//	
//	
//
//	@RequestMapping(value = "/jtdel", method = RequestMethod.POST)
//    @ResponseBody  
//    public String delJt(@RequestBody String jsonstr) {
//		
//		
//		logger.info("del user======="+jsonstr);   
//	
//		
//		List<TOutUserFpinfo> retList = JsonUtil.gson.fromJson(jsonstr,  
//                new TypeToken<List<TOutUserFpinfo>>() {  
//                }.getType());  
//
//		for(TOutUserFpinfo element:retList)
//        {
//			tOutUserFpinfoMapper.deleteByPrimaryKey(element.getId());
//        }
//		
//		return "OK";
//		
//    } 
//	
//	/**
//     * 用户信息导入（多sheet导入）
//     * @param file
//     * @param request
//     * @param model
//     * @return
//     */
//    @RequestMapping(value = "/grimport")
//    
//    //事务开启
//    //@Transactional 
//    public String grimport(
//            @RequestParam(value = "excelFile", required = false) MultipartFile file, ModelMap model)
//    {
//        // 创建根路径的保存变量
//        String rootPath;
//        SimpleDateFormat format = new SimpleDateFormat("yyyy\\MM\\dd\\");
//        String subpath = format.format(new Date());
//
//        rootPath = Const.TMPFILE_ROOT + subpath;
//
//        System.out.println("开始...");
//      
//        String fileName = file.getOriginalFilename();
//       
//       
//        File targetFile = new File(rootPath, fileName);
//        if (!targetFile.exists())
//        {
//            targetFile.mkdirs();
//        }
//
//        // 保存
//        try
//        {
//            file.transferTo(targetFile);
//            
//            System.out.println("文件上传ok...");
//        } catch (Exception e)
//        {
//            //e.printStackTrace();
//            System.out.println("文件上传err...");
//        }
//        
//        
//        
//        
//        //读取excel 的内容 防止内存溢出
//        //基本信息
//        List<String> exlValues = ExcelUtil.readFileExcel(rootPath+fileName,0,4,ExcelUtil.MAXEXPORTNUM+4,ExcelUtil.USER_COL_NUM);
//       
//        String result = "导入成功！";
//        
//        boolean ret =  false;//= userService.importBase(exlValues);
//        if(!ret) {
//        	result = "导入失败！请检测数据文件格式！";
//        }
//        
//        
//        return result;
//    }
//	
//	
//	
//	/**
//	 * 导出合同信息到excel
//	 * @param type 用户信息类型，比如基本信息等等
//	 * @return
//	 */
//	@RequestMapping(value = "/jtexport", method = RequestMethod.GET)
//	private ModelAndView jtexport(ModelMap model,String type){
//		
//		//构建条件
//		TOutUserJcExample sample = new TOutUserJcExample();
//		
//		
//		TOutUserJcExample.Criteria criteria = sample.createCriteria();
//		
//
//		List<TOutUserJc> list = tOutUserJcMapper.selectByExample(sample);
//
//        //varList 数据（二维数据）
//        List<List<?>> varList =  new ArrayList<List<?>>();
//        List<String> line;
//        
//        OutUser mapuser;
//        
//		for (TOutUserJc user:list) {
//			 line = new ArrayList<String>();
//			 
//			 //mapuser = //userService.getUser(user.getUserid());
//		 
//			 line.add("");
//			 			 
//			 //操作模式	人员编号	姓名	解除原因	解除外包关系日期	费用最终日期	途径说明（新外包公司名称）
//	         //line.add(mapuser.getCode());
//	        // line.add(mapuser.getName());
//	         line.add(user.getJcreason());
//	         
//	         line.add(DateUtil.format(user.getJcdate()));
//	         line.add(DateUtil.format(user.getGzenddate()));
//	         line.add(user.getQt());
//
//	         line.add(user.getId());
//	         	         
//	         varList.add(line);
//		}
//		
//		
//		
//		//titles  列标题
//        List<String> titles = new ArrayList<String>();
//        
//        //操作模式	人员编号	姓名	解除原因	解除外包关系日期	费用最终日期	途径说明（新外包公司名称）
//        titles.add("操作模式");
//        titles.add("人员编号");
//        titles.add("姓名"); 
//        titles.add("解除原因"); 
//        
//        titles.add("解除外包关系日期");
//        titles.add("费用最终日期");
//        titles.add("途径说明（新外包公司名称）");
//        
//        //额外系统关键字
//        titles.add("校验标识");
//
//        //数据传递
//        model.put("name", "外包人员解除关系信息"); 
//        model.put("titles", titles); 
//        model.put("varList", varList);
//        ViewExcel viewExcel = new ViewExcel();    
//        return new ModelAndView(viewExcel, model);
//	}
//	
//	
//	
//	/**
//	 * 导出excel
//	 * @param type 用户信息类型，比如基本信息等等
//	 * @return
//	 */
//	@RequestMapping(value = "/exportjt", method = RequestMethod.GET)
//	private ModelAndView exportjt(ModelMap model,String type){
//		
//		//构建条件
//		TOutUserJcExample sample = new TOutUserJcExample();
//		
//		
//		TOutUserJcExample.Criteria criteria = sample.createCriteria();
//		
//
//		List<TOutUserJc> list = tOutUserJcMapper.selectByExample(sample);
//
//        //varList 数据（二维数据）
//        List<List<?>> varList =  new ArrayList<List<?>>();
//        List<String> line;
//        
//        OutUser mapuser;
//        
//		for (TOutUserJc user:list) {
//			 line = new ArrayList<String>();
//			 
//			
//		 
//			 line.add("");
//			 			 
//			 //操作模式	人员编号	姓名	解除原因	解除外包关系日期	费用最终日期	途径说明（新外包公司名称）
//	         //line.add(mapuser.getCode());
//	         //line.add(mapuser.getName());
//	         line.add(user.getJcreason());
//	         
//	         line.add(DateUtil.format(user.getJcdate()));
//	         line.add(DateUtil.format(user.getGzenddate()));
//	         line.add(user.getQt());
//
//	         line.add(user.getId());
//	         	         
//	         varList.add(line);
//		}
//		
//		
//		
//		//titles  列标题
//        List<String> titles = new ArrayList<String>();
//        
//        //操作模式	人员编号	姓名	解除原因	解除外包关系日期	费用最终日期	途径说明（新外包公司名称）
//        titles.add("操作模式");
//        titles.add("人员编号");
//        titles.add("姓名"); 
//        titles.add("解除原因"); 
//        
//        titles.add("解除外包关系日期");
//        titles.add("费用最终日期");
//        titles.add("途径说明（新外包公司名称）");
//        
//        //额外系统关键字
//        titles.add("校验标识");
//        
//       	
//
//        //数据传递
//        model.put("name", "外包人员解除关系信息"); 
//        model.put("titles", titles); 
//        model.put("varList", varList);
//        ViewExcel viewExcel = new ViewExcel();    
//        return new ModelAndView(viewExcel, model);
//	}	
//	
//	
//	
//	
//	/***************分配部分***********************end***********/
	

	
	/**********************************业务接口*****************************end*********/
	
}
