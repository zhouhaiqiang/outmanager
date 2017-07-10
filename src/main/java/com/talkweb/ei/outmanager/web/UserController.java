package com.talkweb.ei.outmanager.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresUser;
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
import com.talkweb.ei.outmanager.dao.TOutUserFpinfoMapper;
import com.talkweb.ei.outmanager.dao.TOutUserHtMapper;
import com.talkweb.ei.outmanager.dao.TOutUserJcMapper;
import com.talkweb.ei.outmanager.dao.TOutUserJninfoMapper;
import com.talkweb.ei.outmanager.dao.TOutUserJyinfoMapper;
import com.talkweb.ei.outmanager.dao.TOutUserZyinfoMapper;
import com.talkweb.ei.outmanager.model.OutCompany;
import com.talkweb.ei.outmanager.model.OutUser;
import com.talkweb.ei.outmanager.model.OutUserExample;
import com.talkweb.ei.outmanager.model.OutUser_S;
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
import com.talkweb.ei.outmanager.service.IUserService;
import com.talkweb.ei.shiro.CryptographyUtil;


@Controller
@RequestMapping("/user") // url:/模块/资源/{id}/细分 /seckill/list
public class UserController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IUserService userService;
	
	@Autowired
	private OutUserMapper outUserMapper;	
		
	@Autowired
	private TOutUserFpinfoMapper tOutUserFpinfoMapper;

	@Autowired
	private TOutUserHtMapper tOutUserHtMapper;
	
	@Autowired
	private TOutUserJcMapper tOutUserJcMapper;	
	
	@Autowired
	private TOutUserJyinfoMapper tOutUserJyinfoMapper;
	
	@Autowired
	private TOutUserJninfoMapper tOutUserJninfoMapper;	
	
	@Autowired
	private TOutUserZyinfoMapper tOutUserZyinfoMapper;	
	

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	private String login(Model model) {
		//List<Book> list = bookService.getList();
		//model.addAttribute("list", list);
		// list.jsp + model = ModelAndView		
		return "user/login";// WEB-INF/jsp/"list".jsp
	}
	
	
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	private String logout(Model model) {
		
		userService.logout();
		return "user/login";
	}
	
	
	@RequestMapping(value = "/chgpwd", method = RequestMethod.GET)
	
	@RequiresUser
	private String chgpwd(Model model) {
		
		return "user/chgpwd";
	}	
	
	
	@RequestMapping(value = "/updatepwd", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody  
    public String updatePwd(@RequestBody String jsonstr) {
	
		
		
	   Map<String,String> par = JsonUtil.gson.fromJson(jsonstr,HashMap.class);
	   
	   OutUser loginuser = (OutUser)SecurityUtils.getSubject().getPrincipal();

	   loginuser.setPwd(CryptographyUtil.md5(par.get("password"),"java1234"));
	
	   userService.updateUser(loginuser);
		
		return "OK";
	}
	
	/**********************************认证接口************************start**************/
	/**
	 * 认证
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	private String auth( @RequestParam("userId") String userid,@RequestParam("password") String pwd, ModelMap modelMap,HttpServletRequest request) {
		

		boolean ret = userService.auth(userid, pwd);
		
		if(ret){

			//检查session信息
			Iterator<Object> atts = SecurityUtils.getSubject().getSession().getAttributeKeys().iterator();						
		    while (atts.hasNext()) {
				String key = (String)atts.next();
				
				System.out.println(key+"=key==========value="+SecurityUtils.getSubject().getSession().getAttribute(key));
				
			}			

			return "user/index";

		}
		
//		Result result = new Result();
//		result.setError("用户密码认证失败!");
//		
//		model.("result",result);
		modelMap.addAttribute("msg", "用户密码认证失败!");

		return "user/login";
	}	
	
	
	@RequestMapping(value = "/showuser", method = RequestMethod.GET)
	private String showuser(Model model,String id) {
		
		OutUser user = userService.getUser(id);		
		model.addAttribute("user", user);			
		return "user/userinfo";
	}	
	
	
	/**
	 * 清除登录信息
	 * 
	 * @param request
	 */
	private void removeLoginInfo(HttpServletRequest request) {
		request.getSession(true).removeAttribute(Const.SESSION_USER);
		request.getSession(true).removeAttribute(Const.SESSION_NAME);		
	}
	
	/**
	 * 设置登录信息
	 * 
	 * @param request
	 * @param map
	 */
	private void insertLoginInfo(HttpServletRequest request,
			Map<String, Object> map) {
		Set<String> set = map.keySet();
		Iterator<String> iterator = set.iterator();
		HttpSession session = request.getSession(true);
		while (iterator.hasNext()) {
			String key = iterator.next();
			session.setAttribute(key, map.get(key));
		}
	}
	/**********************************认证接口**************************end************/
	
	
	
	/**********************************业务接口***************************start***********/
	
	/**
	 * 用户查询
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	private String conlist(Model model) {	
		return "/user/list";
	}	
	
	
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	
	//公司信息导入
	private String uploadCompany(Model model) {	
		return "/user/upload";
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
		
		//只查询外包用户
		criteria.andUsertypeEqualTo("1");
		
		if(StringUtils.isNotEmpty(companyid)){
			criteria.andCompanyidEqualTo(companyid);
		}
		if(StringUtils.isNotEmpty(concode)){
			criteria.andConcodeEqualTo(concode);
		}
		if(StringUtils.isNotEmpty(code)){
			criteria.andCodeEqualTo(code);
		}
		
		
		
		
		if(StringUtils.isNotEmpty(unit)){
			criteria.andUnitLike("%"+unit+"%");
		}
		if(StringUtils.isNotEmpty(name)){
			criteria.andNameLike("%"+name+"%");
		}

		int total = userService.getUserSize(sample);
		List<OutUser> list = userService.getUserList(limit, offset, sample);
		
		
		List<OutUser_S> list_s =  toSimpleUser(list);
		
		
		//构建返回值
		PageResult ret = new PageResult(true,list_s,total);			
		System.out.println("----------"+ret);	
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
	 * 添加或者更新一条记录
	 * @param jsonstr
	 * @return
	 */
	@RequestMapping(value = "/user_update", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody  
    public String updateContract(@RequestBody String jsonstr) {
		
		
		logger.info("user_update======="+jsonstr);   
			
		OutUser outUser = JsonUtil.gson.fromJson(jsonstr,OutUser.class);  
		
		if(outUser!=null){
			
			//页面上没有ID说明是新增
			if(StringUtils.isEmpty(outUser.getId())){
				outUser.setId(UUID.randomUUID().toString());
				
				//外包用户
				outUser.setUsertype("1");
				
			    logger.info(new Date().getTime()+"");
				
				//登录id
				outUser.setCode("SX" +new Date().getTime() );
				
				//默认密码
				outUser.setPwd(CryptographyUtil.md5("111111", "java1234"));
				
				userService.addUser(outUser);
			} else {				
				userService.updateUser(outUser);
			}
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
    @RequestMapping(value = "/import")
    
    //事务开启
    //@Transactional 
    public String importUser(
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
	
    
    
    
    
    
    
	@RequestMapping(value = "/user_del", method = RequestMethod.POST)
    @ResponseBody  
    public String delCompany(@RequestBody String jsonstr) {
		
		
		logger.info("del user======="+jsonstr);   
	
		Gson gson = new Gson();
		List<OutUser> retList = gson.fromJson(jsonstr,  
                new TypeToken<List<OutUser>>() {  
                }.getType());  

		for(OutUser element:retList)
        {
			userService.deleteUser(element);
        }
		
		return "OK";
		
    } 
    
    
	/**
	 * 导出合同信息到excel
	 * @param type 用户信息类型，比如基本信息等等
	 * @return
	 */
	@RequestMapping(value = "/export", method = RequestMethod.GET)
	private ModelAndView exportUserList(ModelMap model,String type){


		if("基本信息".equals(type)){			
			return exportBase(model); 
		} else if ("教育信息".equals(type)) {
			return exportJiaoyu(model); 
		} else if ("分配信息".equals(type)) {
			return exportFenpei(model); 
		} else if ("职业技能".equals(type)) {
			return exportZhiye(model); 
		} else if ("专业技术".equals(type)) {
			return exportZhuanye(model); 
		} else if ("劳动关系".equals(type)) {
			return exportLaodong(model); 
		} else if ("解除外包关系".equals(type)) {
			return exportJiechu(model); 
		}
		  
		logger.info("前台选取的数据类型不对=========="+type);
		return null;
			
	}

	/**
	 * 导出外包人员基本信息
	 * @param model
	 * @return
	 */
	private ModelAndView exportBase(ModelMap model) {
		//构建条件
		OutUserExample sample = new OutUserExample();
		
		
		OutUserExample.Criteria criteria = sample.createCriteria();
		
		//只查询外包用户
		criteria.andUsertypeEqualTo("1");
				
		int total = userService.getUserSize(sample);
		List<OutUser> list = userService.getUserList(ExcelUtil.MAXEXPORTNUM, 0, sample);

        //varList 数据（二维数据）
        List<List<?>> varList =  new ArrayList<List<?>>();
        List<String> line;
		for (OutUser user:list) {
			 line = new ArrayList<String>();
			 
		 
			 
	         line.add(user.getCode());
	         line.add(user.getName());
	         line.add(user.getIdnumber());
	         line.add(user.getSex());

	         line.add(user.getContype());
	         line.add(user.getBirthday());
	         line.add(user.getZhengzhi());
	         line.add(user.getNationality());
	         line.add(user.getMail());
	         
	         
	         //户口类型	户口所在地	民族	移居国外者	进入外包单位日期	
	         line.add(user.getHukoutype());
	         line.add(user.getHukouaddress());	
	         line.add(user.getMingz());
	         line.add(user.getIsout());		         
	         line.add(DateUtil.format(user.getInworkdate()));
	         
	         //从事联通业务开始日期(不可修改)	分配开始日期	所属外包公司	所属外包合同编号	组织名称
	         line.add(DateUtil.format(user.getInunicomdate()));	
	         line.add(DateUtil.format(user.getFenpeidate()));
	         line.add(user.getCompanyid());
	         line.add(user.getConcode());
	         line.add(user.getUnit());
	         
	         
	         //从事外包业务类型	从事联通服务途径	增员途径说明	纳税地	社保缴纳地	
	         line.add(user.getYwtype());
	         line.add(user.getYwtj());
	         line.add(user.getTjmark());
	         line.add(user.getNsaddress());
	         line.add(user.getSbaddress());
	         
	         
	         //岗位序列	岗位分类	参考岗级	考核信息	备用1	备用2
	         line.add(user.getGwnumber());
	         line.add(user.getGwtype());
	         line.add(user.getGwdj());
	         line.add(user.getKaohei());
	         line.add(user.getBak1());
	         line.add(user.getBak2());
	         
	         line.add(user.getId());
	         	         
	         varList.add(line);
		}
		
		
		
		//titles  列标题
        List<String> titles = new ArrayList<String>();
        
        //基本信息
        // 人员编号	姓名	身份证号码	性别

        titles.add("人员编号");
        titles.add("姓名");
        titles.add("身份证号码");        
        titles.add("性别");
        
        
        titles.add("人员类型");       
        titles.add("出生日期");
        titles.add("政治面貌");
        titles.add("国籍");
        titles.add("电子邮件地址");
        
        //户口类型	户口所在地	民族	移居国外者	进入外包单位日期	
        titles.add("户口类型");
        titles.add("户口所在地");
        titles.add("民族");
        titles.add("移居国外者");       
        titles.add("进入外包单位日期");  
        
        //从事联通业务开始日期(不可修改)	分配开始日期	所属外包公司	所属外包合同编号	组织名称	
        titles.add("从事联通业务开始日期");
        titles.add("分配开始日期");
        titles.add("所属外包公司");
        titles.add("所属外包合同编号");       
        titles.add("组织名称");    

        //从事外包业务类型	从事联通服务途径	增员途径说明	纳税地	社保缴纳地	
        titles.add("从事外包业务类型");
        titles.add("从事联通服务途径");
        titles.add("增员途径说明");
        titles.add("纳税地");       
        titles.add("社保缴纳地");  
        
        //岗位序列	岗位分类	参考岗级	考核信息	备用1	备用2
        
        titles.add("岗位序列");
        titles.add("岗位分类");
        titles.add("参考岗级");
        titles.add("考核信息");       
        titles.add("备用1	"); 
        titles.add("备用2"); 
        
        //额外系统关键字
        titles.add("校验标识");
        
       	

        //数据传递
        model.put("name", "外包人员基本信息"); 
        model.put("titles", titles); 
        model.put("varList", varList);
        ViewExcel viewExcel = new ViewExcel();    
        return new ModelAndView(viewExcel, model);
	} 
    
	/**
	 * 导出外包人员教育信息
	 * @param model
	 * @return
	 */
	private ModelAndView exportJiaoyu(ModelMap model) {
		//构建条件
		TOutUserJyinfoExample sample = new TOutUserJyinfoExample();
		
		
		//TOutUserJyinfoExample.Criteria criteria = sample.createCriteria();
		

		List<TOutUserJyinfo> list = tOutUserJyinfoMapper.selectByExample(sample);

        //varList 数据（二维数据）
        List<List<?>> varList =  new ArrayList<List<?>>();
        List<String> line;
        
        OutUser mapuser;
        
		for (TOutUserJyinfo user:list) {
			 line = new ArrayList<String>();
			 
			 mapuser = userService.getUser(user.getUserid());
		 
			 line.add("");
			 			 
			 //人员编号	姓名	学校	入学时间	毕业时间
	         line.add(mapuser.getCode());
	         line.add(mapuser.getName());
	         line.add(user.getSchool());
	         line.add(DateUtil.format(user.getStartdate()));
	         line.add(DateUtil.format(user.getEnddate()));

	         //学历	学历证书编号	是否最高学历	是否全日制学历	学位
	         line.add(user.getXueli());
	         line.add(user.getXuelizsnumber());
	         line.add(user.getIsmaxxl());
	         line.add(user.getIsqrz());
	         line.add(user.getXuewei());
	         
	         
	         //第一学位类别	第二学位类别	学位授予日期	学位授予单位	学位证书编号	是否最高学位
	         line.add(user.getD1xwtype());
	         line.add(user.getD2xwtype());	
	         line.add(DateUtil.format(user.getXwdate()));
	         line.add(user.getXwunit());		         
	         line.add(user.getXwzsnumber());
	         line.add(user.getIsmaxxw());
	         
	         //同等学历	相当毕业	专业类别	专业子类别	第一专业	第二专业
	         line.add(user.getTdxl());	
	         line.add(user.getXdby());
	         line.add(user.getZytype());
	         line.add(user.getZysubtype());
	         line.add(user.getD1zy());
	         line.add(user.getD2zy());
	         
	         
	         //学制	学习形式	学习情况	备注
	         line.add(user.getXuezhi());
	         line.add(user.getXxxs());
	         line.add(user.getXxqk());
	         line.add(user.getQt());
	        
	         line.add(user.getId());
	         	         
	         varList.add(line);
		}
		
		
		
		//titles  列标题
        List<String> titles = new ArrayList<String>();
        
        //教育信息
        //人员编号	姓名	学校	入学时间	毕业时间	
        titles.add("操作模式");
        titles.add("人员编号");
        titles.add("姓名");
        titles.add("学校");        
        titles.add("入学时间");
        titles.add("毕业时间");
        
        //学历	学历证书编号	是否最高学历	是否全日制学历	学位	
        titles.add("学历");       
        titles.add("学历证书编号");
        titles.add("是否最高学历");
        titles.add("是否全日制学历");
        titles.add("学位");
        
        //第一学位类别	第二学位类别	学位授予日期	学位授予单位	学位证书编号	是否最高学位
        titles.add("第二学位类别");
        titles.add("学位授予日期");
        titles.add("学位授予单位");       
        titles.add("学位证书编号");        
        titles.add("是否最高学位");  
        
        //同等学历	相当毕业	专业类别	专业子类别	第一专业	第二专业	
        titles.add("同等学历");	
        titles.add("相当毕业");
        titles.add("专业类别");
        titles.add("专业子类别");
        titles.add("第一专业");       
        titles.add("第二专业");    

        //学制	学习形式	学习情况	备注
        titles.add("学制");
        titles.add("学习形式");
        titles.add("学习情况");
        titles.add("备注");       

        //额外系统关键字
        titles.add("校验标识");
        
       	

        //数据传递
        model.put("name", "外包人员教育信息"); 
        model.put("titles", titles); 
        model.put("varList", varList);
        ViewExcel viewExcel = new ViewExcel();    
        return new ModelAndView(viewExcel, model);
	} 
	
	
	
	/**
	 * 导出外包人员分配信息
	 * @param model
	 * @return
	 */
	private ModelAndView exportFenpei(ModelMap model) {
		return null;
	}
	
	/**
	 * 导出外包人员专业信息
	 * @param model
	 * @return
	 */
	private ModelAndView exportZhuanye(ModelMap model) {

		//构建条件
		TOutUserZyinfoExample sample = new TOutUserZyinfoExample();
		
		
		TOutUserZyinfoExample.Criteria criteria = sample.createCriteria();
		

		List<TOutUserZyinfo> list = tOutUserZyinfoMapper.selectByExample(sample);

        //varList 数据（二维数据）
        List<List<?>> varList =  new ArrayList<List<?>>();
        List<String> line;
        
        OutUser mapuser;
        
		for (TOutUserZyinfo user:list) {
			 line = new ArrayList<String>();
			 
			 mapuser = userService.getUser(user.getUserid());
		 
			 line.add("");
			 			 
			 //人员编号	姓名	专业技术资格序列	专业技术资格名称	专业分类	
	         line.add(mapuser.getCode());
	         line.add(mapuser.getName());
	         
	         line.add(user.getXulie());
	         line.add(user.getName());
	         line.add(user.getZytype());
	         
	         //专业子分类	其它请注明	专业技术资格证书编号  取得资格日期
	         line.add(user.getSubtype());
	         line.add(user.getQt());
	         line.add(user.getZsnumber());
	         line.add(DateUtil.format(user.getGotdate()));

	         
	         //取得资格途径	专业技术资格等级	到期日	资格授予单位	是否主要专业技术资格 
	         line.add(user.getGotway());
	         line.add(user.getDengji());	         
	         line.add(DateUtil.format(user.getOutdate()));
	         line.add(user.getShareunit());
	         line.add(user.getIsmain());
	         
	         	         
	         line.add(user.getId());
	         	         
	         varList.add(line);
		}
		
		
		
		//titles  列标题
        List<String> titles = new ArrayList<String>();
        
        //人员编号	姓名	专业技术资格序列	专业技术资格名称	专业分类	
        titles.add("操作模式");
        titles.add("人员编号");
        titles.add("姓名");       
        titles.add("专业技术资格序列");
        titles.add("专业技术资格名称");
        titles.add("专业分类");
        
        //专业子分类	其它请注明	专业技术资格证书编号  取得资格日期		
        titles.add("专业子分类");       
        titles.add("其它请注明");
        titles.add("专业技术资格证书编号");
        titles.add("取得资格日期");
       
        
        //取得资格途径	专业技术资格等级	到期日	资格授予单位	是否主要专业技术资格        
        titles.add("取得资格途径");       
        titles.add("专业技术资格等级");
        titles.add("到期日");
        titles.add("资格授予单位"); 
        titles.add("是否主要专业技术资格");
        
        //额外系统关键字
        titles.add("校验标识");
        
       	

        //数据传递
        model.put("name", "外包人员职业技能信息"); 
        model.put("titles", titles); 
        model.put("varList", varList);
        ViewExcel viewExcel = new ViewExcel();    
        return new ModelAndView(viewExcel, model);
	}
	
	
	/**
	 * 导出外包人员职业信息
	 * @param model
	 * @return
	 */
	private ModelAndView exportZhiye(ModelMap model) {
		
		
		//构建条件
		TOutUserJninfoExample sample = new TOutUserJninfoExample();
		
		
		TOutUserJninfoExample.Criteria criteria = sample.createCriteria();
		

		List<TOutUserJninfo> list = tOutUserJninfoMapper.selectByExample(sample);

        //varList 数据（二维数据）
        List<List<?>> varList =  new ArrayList<List<?>>();
        List<String> line;
        
        OutUser mapuser;
        
		for (TOutUserJninfo user:list) {
			 line = new ArrayList<String>();
			 
			 mapuser = userService.getUser(user.getUserid());
		 
			 line.add("");
			 			 
			 //人员编号	姓名	认定开始日期	认定终止日期	认定单位
	         line.add(mapuser.getCode());
	         line.add(mapuser.getName());
	         line.add(DateUtil.format(user.getStartdate()));
	         line.add(DateUtil.format(user.getEnddate()));
	         line.add(user.getRdunit());
	         
	         //认定技能资格名称	认定技能资格等级	其他请注明	是否主要认定
	         line.add(user.getRdname());
	         line.add(user.getRddengji());
	         line.add(user.getQt());
	         line.add(user.getIsmain());

	         line.add(user.getId());
	         	         
	         varList.add(line);
		}
		
		
		
		//titles  列标题
        List<String> titles = new ArrayList<String>();
        
        //操作模式	人员编号	姓名	认定开始日期	认定终止日期	认定单位	
        titles.add("操作模式");
        titles.add("人员编号");
        titles.add("姓名");       
        titles.add("认定开始日期");
        titles.add("认定终止日期");
        titles.add("认定单位");
        
        //认定技能资格名称	认定技能资格等级	其他请注明	是否主要认定
        titles.add("认定技能资格名称");       
        titles.add("认定技能资格等级");
        titles.add("其他请注明");
        titles.add("是否主要认定");
       
        //额外系统关键字
        titles.add("校验标识");
        
       	

        //数据传递
        model.put("name", "外包人员职业技能信息"); 
        model.put("titles", titles); 
        model.put("varList", varList);
        ViewExcel viewExcel = new ViewExcel();    
        return new ModelAndView(viewExcel, model);
	}
	
	/**
	 * 导出外包人员劳动合同信息
	 * @param model
	 * @return
	 */
	private ModelAndView exportLaodong(ModelMap model) {
		//构建条件
		TOutUserHtExample sample = new TOutUserHtExample();
		
		
		TOutUserHtExample.Criteria criteria = sample.createCriteria();
		

		List<TOutUserHt> list = tOutUserHtMapper.selectByExample(sample);

        //varList 数据（二维数据）
        List<List<?>> varList =  new ArrayList<List<?>>();
        List<String> line;
        
        OutUser mapuser;
        
		for (TOutUserHt user:list) {
			 line = new ArrayList<String>();
			 
			 mapuser = userService.getUser(user.getUserid());
		 
			 line.add("");
			 			 
			 //操作模式	人员编号	姓名	合同类型	劳动合同编号	合同期限类型
	         line.add(mapuser.getCode());
	         line.add(mapuser.getName());
	         line.add(user.getContype());
	         line.add(user.getConnumber());
	         line.add(user.getConqxtype());
	         
	         //劳动合同起始日期	劳动合同终止日期	劳动合同状态	劳动合同期限	劳动合同签订单位	
	         line.add(DateUtil.format(user.getStartdate()));
	         line.add(DateUtil.format(user.getEnddate()));
	         line.add(user.getConstatus());
	         line.add(user.getQixian());
	         line.add(user.getUnit());
	       

	         //劳务合同签订的甲方公司	对应劳务合同编号	劳务合同名称
	         line.add(user.getUnit());	         
	         line.add(user.getNwconnumber());
	         line.add(user.getLwconname());
	         
	         line.add(user.getId());
	         	         
	         varList.add(line);
		}
		
		
		
		//titles  列标题
        List<String> titles = new ArrayList<String>();
        
        //操作模式	人员编号	姓名	合同类型	劳动合同编号	合同期限类型		
        titles.add("操作模式");
        titles.add("人员编号");
        titles.add("姓名");       
        titles.add("合同类型");
        titles.add("劳动合同编号");
        titles.add("合同期限类型");
        
        //劳动合同起始日期	劳动合同终止日期	劳动合同状态	劳动合同期限	劳动合同签订单位	
        titles.add("劳动合同起始日期");       
        titles.add("劳动合同终止日期");
        titles.add("劳动合同状态");
        titles.add("劳动合同期限");
        titles.add("劳动合同签订单位");
       
        
        //劳务合同签订的甲方公司	对应劳务合同编号	劳务合同名称
        titles.add("劳务合同签订的甲方公司");       
        titles.add("对应劳务合同编号");
        titles.add("劳务合同名称");
        
        //额外系统关键字
        titles.add("校验标识");
        
       	

        //数据传递
        model.put("name", "外包人员劳动合同信息"); 
        model.put("titles", titles); 
        model.put("varList", varList);
        ViewExcel viewExcel = new ViewExcel();    
        return new ModelAndView(viewExcel, model);
	}
	
	/**
	 * 导出外包人员解除关系信息
	 * @param model
	 * @return
	 */
	private ModelAndView exportJiechu(ModelMap model) {
		//构建条件
		TOutUserJcExample sample = new TOutUserJcExample();
		
		
		TOutUserJcExample.Criteria criteria = sample.createCriteria();
		

		List<TOutUserJc> list = tOutUserJcMapper.selectByExample(sample);

        //varList 数据（二维数据）
        List<List<?>> varList =  new ArrayList<List<?>>();
        List<String> line;
        
        OutUser mapuser;
        
		for (TOutUserJc user:list) {
			 line = new ArrayList<String>();
			 
			 mapuser = userService.getUser(user.getUserid());
		 
			 line.add("");
			 			 
			 //操作模式	人员编号	姓名	解除原因	解除外包关系日期	费用最终日期	途径说明（新外包公司名称）
	         line.add(mapuser.getCode());
	         line.add(mapuser.getName());
	         line.add(user.getJcreason());
	         
	         line.add(DateUtil.format(user.getJcdate()));
	         line.add(DateUtil.format(user.getGzenddate()));
	         line.add(user.getQt());

	         line.add(user.getId());
	         	         
	         varList.add(line);
		}
		
		
		
		//titles  列标题
        List<String> titles = new ArrayList<String>();
        
        //操作模式	人员编号	姓名	解除原因	解除外包关系日期	费用最终日期	途径说明（新外包公司名称）
        titles.add("操作模式");
        titles.add("人员编号");
        titles.add("姓名"); 
        titles.add("解除原因"); 
        
        titles.add("解除外包关系日期");
        titles.add("费用最终日期");
        titles.add("途径说明（新外包公司名称）");
        
        //额外系统关键字
        titles.add("校验标识");
        
       	

        //数据传递
        model.put("name", "外包人员解除关系信息"); 
        model.put("titles", titles); 
        model.put("varList", varList);
        ViewExcel viewExcel = new ViewExcel();    
        return new ModelAndView(viewExcel, model);
	}	
	
	
	/**********************************业务接口*****************************end*********/
	
}
