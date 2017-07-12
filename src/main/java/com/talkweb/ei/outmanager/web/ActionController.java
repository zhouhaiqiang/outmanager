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
import com.talkweb.ei.outmanager.dao.TOutActionMapper;
import com.talkweb.ei.outmanager.dao.TOutGongziMapper;
import com.talkweb.ei.outmanager.dao.TOutJthyMapper;
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
import com.talkweb.ei.outmanager.service.IUserGz;

/**
 * 用户管理的信息操作
 * @author zhq
 *
 */
@Controller
@RequestMapping("/action") // url:/模块/资源/{id}/细分 /seckill/list
public class ActionController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private VOutUseractionMapper vOutUseractionMapper;
	
	@Autowired
	private TOutActionMapper tOutActionMapper;

	
	@Autowired
	private IUserGz iUserGz;
	
	
	

	@Autowired
	private TOutGongziMapper tOutGongziMapper;	
	
	@Autowired
	private TOutJthyMapper tOutJthyMapper;		

	/**********************************业务接口***************************start***********/
	
	
	
	
	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	private String grlist(Model model) {			
		return "action/list";
	}	
	

	/**
	 * 批量添加
	 */
	@RequestMapping(value = "/padd", method = RequestMethod.GET)
	private String padd(Model model) {			
		return "action/padd";
	}
	
	/**
	 * 批量创建
	 */
	@RequestMapping(value = "/pcreate", method = RequestMethod.GET)
	private String pcreate(Model model) {			
		return "action/pcreate";
	}	
	
	
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	
	//信息导入
	private String uploadCompany(Model model) {	
		return "/action/upload";
	}
	
	
	



	/**
	 * 查询
	 * @param limit
	 * @param offset
	 * @param unit
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/list_json", method = RequestMethod.GET, produces = {
	"application/json; charset=utf-8" })
	@ResponseBody	
	private PageResult getActionList(int limit, int offset,String unit,String username){
				

		//构建条件
		VOutUseractionExample sample = new VOutUseractionExample();
		
		
		VOutUseractionExample.Criteria criteria = sample.createCriteria();


		if(StringUtils.isNotEmpty(unit)){
			criteria.andUnitEqualTo(unit);
		}
		if(StringUtils.isNotEmpty(username)){
			criteria.andNameLike(username);
		}

		
		//分页条件
		sample.setLimit(offset+limit);
		sample.setOffset(offset+1);		

		long total = vOutUseractionMapper.countByExample(sample);
		List<VOutUseraction> list =  vOutUseractionMapper.selectPageByExample(sample);
						
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
	@RequestMapping(value = "/update", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody  
    public String updateGrgongzi(@RequestBody String jsonstr) {
		
		
		logger.info("action_update======="+jsonstr);   
			
		VOutUseraction tOutGongzi = JsonUtil.gson.fromJson(jsonstr,VOutUseraction.class);  
		
		if(tOutGongzi!=null){
			
			//查找老记录
			TOutAction old =  tOutActionMapper.selectByPrimaryKey(tOutGongzi.getId());
			
			
			//页面能修改的三个属性
			old.setStartdate(tOutGongzi.getStartdate());			
			old.setYwline(tOutGongzi.getYwline());
			old.setYwaction(tOutGongzi.getYwaction());
					
			tOutActionMapper.updateByPrimaryKey(old);
		
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
	 * 导出活动归属信息到excel
	 * @return
	 */
	@RequestMapping(value = "/export", method = RequestMethod.GET)
	private ModelAndView export(ModelMap model){
		
		//构建条件
		VOutUseractionExample sample = new VOutUseractionExample();
		
		
		VOutUseractionExample.Criteria criteria = sample.createCriteria();

		long total = vOutUseractionMapper.countByExample(sample);
		
		//分页条件
		sample.setLimit(Integer.parseInt(total+""));
		sample.setOffset(1);		
		
		List<VOutUseraction> list =  vOutUseractionMapper.selectPageByExample(sample);


        //varList 数据（二维数据）
        List<List<?>> varList =  new ArrayList<List<?>>();
        List<String> line;
        
        OutUser mapuser;
        
		for (VOutUseraction user:list) {
			 line = new ArrayList<String>();
			 
			 BigDecimal xj = new BigDecimal("0");
		 
			 line.add("");
			 			 
			 //操作模式   组织   人员编号	姓名	业务线  业务活动 是否计入成本  开始日期
			 line.add(user.getUnit());
	         line.add(user.getCode());
	         line.add(user.getName());	         
	         line.add(user.getYwline());	         
	         line.add(user.getYwaction());
	         line.add(user.getIscb());

	         line.add(DateUtil.format(user.getStartdate()));
          
	         line.add(user.getId());
	         	         
	         varList.add(line);
		}
		
		
		
		//titles  列标题
        List<String> titles = new ArrayList<String>();
        
        //操作模式   组织   人员编号	姓名	业务线  业务活动 是否计入成本  开始日期

        titles.add("操作模式");
        
        titles.add("组织");
        
        titles.add("人员编号");
        titles.add("姓名"); 
        titles.add("业务线 ");        
        titles.add("业务活动");
        
        titles.add("是否计入成本");
        titles.add("开始日期");
        
        titles.add("校验标识");
        
        //数据传递
        model.put("name", "人员活动归属信息"); 
        model.put("titles", titles); 
        model.put("varList", varList);
        ViewExcel viewExcel = new ViewExcel();    
        return new ModelAndView(viewExcel, model);
	}
	
	

	
	/**********************************业务接口*****************************end*********/

	
}
