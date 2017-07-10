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
@RequestMapping("/user") // url:/ģ��/��Դ/{id}/ϸ�� /seckill/list
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
	
	/**********************************��֤�ӿ�************************start**************/
	/**
	 * ��֤
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	private String auth( @RequestParam("userId") String userid,@RequestParam("password") String pwd, ModelMap modelMap,HttpServletRequest request) {
		

		boolean ret = userService.auth(userid, pwd);
		
		if(ret){

			//���session��Ϣ
			Iterator<Object> atts = SecurityUtils.getSubject().getSession().getAttributeKeys().iterator();						
		    while (atts.hasNext()) {
				String key = (String)atts.next();
				
				System.out.println(key+"=key==========value="+SecurityUtils.getSubject().getSession().getAttribute(key));
				
			}			

			return "user/index";

		}
		
//		Result result = new Result();
//		result.setError("�û�������֤ʧ��!");
//		
//		model.("result",result);
		modelMap.addAttribute("msg", "�û�������֤ʧ��!");

		return "user/login";
	}	
	
	
	@RequestMapping(value = "/showuser", method = RequestMethod.GET)
	private String showuser(Model model,String id) {
		
		OutUser user = userService.getUser(id);		
		model.addAttribute("user", user);			
		return "user/userinfo";
	}	
	
	
	/**
	 * �����¼��Ϣ
	 * 
	 * @param request
	 */
	private void removeLoginInfo(HttpServletRequest request) {
		request.getSession(true).removeAttribute(Const.SESSION_USER);
		request.getSession(true).removeAttribute(Const.SESSION_NAME);		
	}
	
	/**
	 * ���õ�¼��Ϣ
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
	/**********************************��֤�ӿ�**************************end************/
	
	
	
	/**********************************ҵ��ӿ�***************************start***********/
	
	/**
	 * �û���ѯ
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	private String conlist(Model model) {	
		return "/user/list";
	}	
	
	
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	
	//��˾��Ϣ����
	private String uploadCompany(Model model) {	
		return "/user/upload";
	}
	
	
	
	
	/**
	 * ��ҳ��ѯ�û�
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
				

		//��������
		OutUserExample sample = new OutUserExample();
		
		
		OutUserExample.Criteria criteria = sample.createCriteria();
		
		//ֻ��ѯ����û�
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
		
		
		//��������ֵ
		PageResult ret = new PageResult(true,list_s,total);			
		System.out.println("----------"+ret);	
		return ret;
			
	}
	
	
	/**
	 * �����
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
	 * ��ӻ��߸���һ����¼
	 * @param jsonstr
	 * @return
	 */
	@RequestMapping(value = "/user_update", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody  
    public String updateContract(@RequestBody String jsonstr) {
		
		
		logger.info("user_update======="+jsonstr);   
			
		OutUser outUser = JsonUtil.gson.fromJson(jsonstr,OutUser.class);  
		
		if(outUser!=null){
			
			//ҳ����û��ID˵��������
			if(StringUtils.isEmpty(outUser.getId())){
				outUser.setId(UUID.randomUUID().toString());
				
				//����û�
				outUser.setUsertype("1");
				
			    logger.info(new Date().getTime()+"");
				
				//��¼id
				outUser.setCode("SX" +new Date().getTime() );
				
				//Ĭ������
				outUser.setPwd(CryptographyUtil.md5("111111", "java1234"));
				
				userService.addUser(outUser);
			} else {				
				userService.updateUser(outUser);
			}
		}

		return "OK";
		
    } 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
     * �û���Ϣ���루��sheet���룩
     * @param file
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/import")
    
    //������
    //@Transactional 
    public String importUser(
            @RequestParam(value = "excelFile", required = false) MultipartFile file, ModelMap model)
    {
        // ������·���ı������
        String rootPath;
        SimpleDateFormat format = new SimpleDateFormat("yyyy\\MM\\dd\\");
        String subpath = format.format(new Date());

        rootPath = Const.TMPFILE_ROOT + subpath;

        System.out.println("��ʼ...");
      
        String fileName = file.getOriginalFilename();
       
       
        File targetFile = new File(rootPath, fileName);
        if (!targetFile.exists())
        {
            targetFile.mkdirs();
        }

        // ����
        try
        {
            file.transferTo(targetFile);
            
            System.out.println("�ļ��ϴ�ok...");
        } catch (Exception e)
        {
            //e.printStackTrace();
            System.out.println("�ļ��ϴ�err...");
        }
        
        
        
        
        //��ȡexcel ������ ��ֹ�ڴ����
        //������Ϣ
        List<String> exlValues = ExcelUtil.readFileExcel(rootPath+fileName,0,4,ExcelUtil.MAXEXPORTNUM+4,ExcelUtil.USER_COL_NUM);
       
        String result = "����ɹ���";
        
        boolean ret = userService.importBase(exlValues);
        if(!ret) {
        	result = "����ʧ�ܣ����������ļ���ʽ��";
        }
           
//        boolean ret;
//        List<String> exlValues;
//        String result = "����ɹ���";
        
        
        //������Ϣ
        exlValues = ExcelUtil.readFileExcel(rootPath+fileName,1,4,ExcelUtil.MAXEXPORTNUM+4,ExcelUtil.USER_COL_NUM_JY);
        ret = userService.importJiaoyu(exlValues);
        if(!ret) {
        	result = "����ʧ�ܣ����������ļ���ʽ��";
        }      
        
        //ְҵ������Ϣ
        exlValues = ExcelUtil.readFileExcel(rootPath+fileName,2,4,ExcelUtil.MAXEXPORTNUM+4,ExcelUtil.USER_COL_NUM_ZY);
        ret = userService.importZhiye(exlValues);
        if(!ret) {
        	result = "����ʧ�ܣ����������ļ���ʽ��";
        }  
        
        
        //רҵ��Ϣ
        exlValues = ExcelUtil.readFileExcel(rootPath+fileName,3,4,ExcelUtil.MAXEXPORTNUM+4,ExcelUtil.USER_COL_NUM_ZZ);
        ret = userService.importZhuanye(exlValues);
        if(!ret) {
        	result = "����ʧ�ܣ����������ļ���ʽ��";
        }             
           
        //�Ͷ���ϵ
        exlValues = ExcelUtil.readFileExcel(rootPath+fileName,4,4,ExcelUtil.MAXEXPORTNUM+4,ExcelUtil.USER_COL_NUM_LD);
        ret = userService.importLaodong(exlValues);
        if(!ret) {
        	result = "����ʧ�ܣ����������ļ���ʽ��";
        }       
        
        //�����ϵ
        exlValues = ExcelUtil.readFileExcel(rootPath+fileName,5,4,ExcelUtil.MAXEXPORTNUM+4,ExcelUtil.USER_COL_NUM_JC);
        ret = userService.importJiechu(exlValues);
        if(!ret) {
        	result = "����ʧ�ܣ����������ļ���ʽ��";
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
	 * ������ͬ��Ϣ��excel
	 * @param type �û���Ϣ���ͣ����������Ϣ�ȵ�
	 * @return
	 */
	@RequestMapping(value = "/export", method = RequestMethod.GET)
	private ModelAndView exportUserList(ModelMap model,String type){


		if("������Ϣ".equals(type)){			
			return exportBase(model); 
		} else if ("������Ϣ".equals(type)) {
			return exportJiaoyu(model); 
		} else if ("������Ϣ".equals(type)) {
			return exportFenpei(model); 
		} else if ("ְҵ����".equals(type)) {
			return exportZhiye(model); 
		} else if ("רҵ����".equals(type)) {
			return exportZhuanye(model); 
		} else if ("�Ͷ���ϵ".equals(type)) {
			return exportLaodong(model); 
		} else if ("��������ϵ".equals(type)) {
			return exportJiechu(model); 
		}
		  
		logger.info("ǰ̨ѡȡ���������Ͳ���=========="+type);
		return null;
			
	}

	/**
	 * ���������Ա������Ϣ
	 * @param model
	 * @return
	 */
	private ModelAndView exportBase(ModelMap model) {
		//��������
		OutUserExample sample = new OutUserExample();
		
		
		OutUserExample.Criteria criteria = sample.createCriteria();
		
		//ֻ��ѯ����û�
		criteria.andUsertypeEqualTo("1");
				
		int total = userService.getUserSize(sample);
		List<OutUser> list = userService.getUserList(ExcelUtil.MAXEXPORTNUM, 0, sample);

        //varList ���ݣ���ά���ݣ�
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
	         
	         
	         //��������	�������ڵ�	����	�ƾӹ�����	���������λ����	
	         line.add(user.getHukoutype());
	         line.add(user.getHukouaddress());	
	         line.add(user.getMingz());
	         line.add(user.getIsout());		         
	         line.add(DateUtil.format(user.getInworkdate()));
	         
	         //������ͨҵ��ʼ����(�����޸�)	���俪ʼ����	���������˾	���������ͬ���	��֯����
	         line.add(DateUtil.format(user.getInunicomdate()));	
	         line.add(DateUtil.format(user.getFenpeidate()));
	         line.add(user.getCompanyid());
	         line.add(user.getConcode());
	         line.add(user.getUnit());
	         
	         
	         //�������ҵ������	������ͨ����;��	��Ա;��˵��	��˰��	�籣���ɵ�	
	         line.add(user.getYwtype());
	         line.add(user.getYwtj());
	         line.add(user.getTjmark());
	         line.add(user.getNsaddress());
	         line.add(user.getSbaddress());
	         
	         
	         //��λ����	��λ����	�ο��ڼ�	������Ϣ	����1	����2
	         line.add(user.getGwnumber());
	         line.add(user.getGwtype());
	         line.add(user.getGwdj());
	         line.add(user.getKaohei());
	         line.add(user.getBak1());
	         line.add(user.getBak2());
	         
	         line.add(user.getId());
	         	         
	         varList.add(line);
		}
		
		
		
		//titles  �б���
        List<String> titles = new ArrayList<String>();
        
        //������Ϣ
        // ��Ա���	����	���֤����	�Ա�

        titles.add("��Ա���");
        titles.add("����");
        titles.add("���֤����");        
        titles.add("�Ա�");
        
        
        titles.add("��Ա����");       
        titles.add("��������");
        titles.add("������ò");
        titles.add("����");
        titles.add("�����ʼ���ַ");
        
        //��������	�������ڵ�	����	�ƾӹ�����	���������λ����	
        titles.add("��������");
        titles.add("�������ڵ�");
        titles.add("����");
        titles.add("�ƾӹ�����");       
        titles.add("���������λ����");  
        
        //������ͨҵ��ʼ����(�����޸�)	���俪ʼ����	���������˾	���������ͬ���	��֯����	
        titles.add("������ͨҵ��ʼ����");
        titles.add("���俪ʼ����");
        titles.add("���������˾");
        titles.add("���������ͬ���");       
        titles.add("��֯����");    

        //�������ҵ������	������ͨ����;��	��Ա;��˵��	��˰��	�籣���ɵ�	
        titles.add("�������ҵ������");
        titles.add("������ͨ����;��");
        titles.add("��Ա;��˵��");
        titles.add("��˰��");       
        titles.add("�籣���ɵ�");  
        
        //��λ����	��λ����	�ο��ڼ�	������Ϣ	����1	����2
        
        titles.add("��λ����");
        titles.add("��λ����");
        titles.add("�ο��ڼ�");
        titles.add("������Ϣ");       
        titles.add("����1	"); 
        titles.add("����2"); 
        
        //����ϵͳ�ؼ���
        titles.add("У���ʶ");
        
       	

        //���ݴ���
        model.put("name", "�����Ա������Ϣ"); 
        model.put("titles", titles); 
        model.put("varList", varList);
        ViewExcel viewExcel = new ViewExcel();    
        return new ModelAndView(viewExcel, model);
	} 
    
	/**
	 * ���������Ա������Ϣ
	 * @param model
	 * @return
	 */
	private ModelAndView exportJiaoyu(ModelMap model) {
		//��������
		TOutUserJyinfoExample sample = new TOutUserJyinfoExample();
		
		
		//TOutUserJyinfoExample.Criteria criteria = sample.createCriteria();
		

		List<TOutUserJyinfo> list = tOutUserJyinfoMapper.selectByExample(sample);

        //varList ���ݣ���ά���ݣ�
        List<List<?>> varList =  new ArrayList<List<?>>();
        List<String> line;
        
        OutUser mapuser;
        
		for (TOutUserJyinfo user:list) {
			 line = new ArrayList<String>();
			 
			 mapuser = userService.getUser(user.getUserid());
		 
			 line.add("");
			 			 
			 //��Ա���	����	ѧУ	��ѧʱ��	��ҵʱ��
	         line.add(mapuser.getCode());
	         line.add(mapuser.getName());
	         line.add(user.getSchool());
	         line.add(DateUtil.format(user.getStartdate()));
	         line.add(DateUtil.format(user.getEnddate()));

	         //ѧ��	ѧ��֤����	�Ƿ����ѧ��	�Ƿ�ȫ����ѧ��	ѧλ
	         line.add(user.getXueli());
	         line.add(user.getXuelizsnumber());
	         line.add(user.getIsmaxxl());
	         line.add(user.getIsqrz());
	         line.add(user.getXuewei());
	         
	         
	         //��һѧλ���	�ڶ�ѧλ���	ѧλ��������	ѧλ���赥λ	ѧλ֤����	�Ƿ����ѧλ
	         line.add(user.getD1xwtype());
	         line.add(user.getD2xwtype());	
	         line.add(DateUtil.format(user.getXwdate()));
	         line.add(user.getXwunit());		         
	         line.add(user.getXwzsnumber());
	         line.add(user.getIsmaxxw());
	         
	         //ͬ��ѧ��	�൱��ҵ	רҵ���	רҵ�����	��һרҵ	�ڶ�רҵ
	         line.add(user.getTdxl());	
	         line.add(user.getXdby());
	         line.add(user.getZytype());
	         line.add(user.getZysubtype());
	         line.add(user.getD1zy());
	         line.add(user.getD2zy());
	         
	         
	         //ѧ��	ѧϰ��ʽ	ѧϰ���	��ע
	         line.add(user.getXuezhi());
	         line.add(user.getXxxs());
	         line.add(user.getXxqk());
	         line.add(user.getQt());
	        
	         line.add(user.getId());
	         	         
	         varList.add(line);
		}
		
		
		
		//titles  �б���
        List<String> titles = new ArrayList<String>();
        
        //������Ϣ
        //��Ա���	����	ѧУ	��ѧʱ��	��ҵʱ��	
        titles.add("����ģʽ");
        titles.add("��Ա���");
        titles.add("����");
        titles.add("ѧУ");        
        titles.add("��ѧʱ��");
        titles.add("��ҵʱ��");
        
        //ѧ��	ѧ��֤����	�Ƿ����ѧ��	�Ƿ�ȫ����ѧ��	ѧλ	
        titles.add("ѧ��");       
        titles.add("ѧ��֤����");
        titles.add("�Ƿ����ѧ��");
        titles.add("�Ƿ�ȫ����ѧ��");
        titles.add("ѧλ");
        
        //��һѧλ���	�ڶ�ѧλ���	ѧλ��������	ѧλ���赥λ	ѧλ֤����	�Ƿ����ѧλ
        titles.add("�ڶ�ѧλ���");
        titles.add("ѧλ��������");
        titles.add("ѧλ���赥λ");       
        titles.add("ѧλ֤����");        
        titles.add("�Ƿ����ѧλ");  
        
        //ͬ��ѧ��	�൱��ҵ	רҵ���	רҵ�����	��һרҵ	�ڶ�רҵ	
        titles.add("ͬ��ѧ��");	
        titles.add("�൱��ҵ");
        titles.add("רҵ���");
        titles.add("רҵ�����");
        titles.add("��һרҵ");       
        titles.add("�ڶ�רҵ");    

        //ѧ��	ѧϰ��ʽ	ѧϰ���	��ע
        titles.add("ѧ��");
        titles.add("ѧϰ��ʽ");
        titles.add("ѧϰ���");
        titles.add("��ע");       

        //����ϵͳ�ؼ���
        titles.add("У���ʶ");
        
       	

        //���ݴ���
        model.put("name", "�����Ա������Ϣ"); 
        model.put("titles", titles); 
        model.put("varList", varList);
        ViewExcel viewExcel = new ViewExcel();    
        return new ModelAndView(viewExcel, model);
	} 
	
	
	
	/**
	 * ���������Ա������Ϣ
	 * @param model
	 * @return
	 */
	private ModelAndView exportFenpei(ModelMap model) {
		return null;
	}
	
	/**
	 * ���������Աרҵ��Ϣ
	 * @param model
	 * @return
	 */
	private ModelAndView exportZhuanye(ModelMap model) {

		//��������
		TOutUserZyinfoExample sample = new TOutUserZyinfoExample();
		
		
		TOutUserZyinfoExample.Criteria criteria = sample.createCriteria();
		

		List<TOutUserZyinfo> list = tOutUserZyinfoMapper.selectByExample(sample);

        //varList ���ݣ���ά���ݣ�
        List<List<?>> varList =  new ArrayList<List<?>>();
        List<String> line;
        
        OutUser mapuser;
        
		for (TOutUserZyinfo user:list) {
			 line = new ArrayList<String>();
			 
			 mapuser = userService.getUser(user.getUserid());
		 
			 line.add("");
			 			 
			 //��Ա���	����	רҵ�����ʸ�����	רҵ�����ʸ�����	רҵ����	
	         line.add(mapuser.getCode());
	         line.add(mapuser.getName());
	         
	         line.add(user.getXulie());
	         line.add(user.getName());
	         line.add(user.getZytype());
	         
	         //רҵ�ӷ���	������ע��	רҵ�����ʸ�֤����  ȡ���ʸ�����
	         line.add(user.getSubtype());
	         line.add(user.getQt());
	         line.add(user.getZsnumber());
	         line.add(DateUtil.format(user.getGotdate()));

	         
	         //ȡ���ʸ�;��	רҵ�����ʸ�ȼ�	������	�ʸ����赥λ	�Ƿ���Ҫרҵ�����ʸ� 
	         line.add(user.getGotway());
	         line.add(user.getDengji());	         
	         line.add(DateUtil.format(user.getOutdate()));
	         line.add(user.getShareunit());
	         line.add(user.getIsmain());
	         
	         	         
	         line.add(user.getId());
	         	         
	         varList.add(line);
		}
		
		
		
		//titles  �б���
        List<String> titles = new ArrayList<String>();
        
        //��Ա���	����	רҵ�����ʸ�����	רҵ�����ʸ�����	רҵ����	
        titles.add("����ģʽ");
        titles.add("��Ա���");
        titles.add("����");       
        titles.add("רҵ�����ʸ�����");
        titles.add("רҵ�����ʸ�����");
        titles.add("רҵ����");
        
        //רҵ�ӷ���	������ע��	רҵ�����ʸ�֤����  ȡ���ʸ�����		
        titles.add("רҵ�ӷ���");       
        titles.add("������ע��");
        titles.add("רҵ�����ʸ�֤����");
        titles.add("ȡ���ʸ�����");
       
        
        //ȡ���ʸ�;��	רҵ�����ʸ�ȼ�	������	�ʸ����赥λ	�Ƿ���Ҫרҵ�����ʸ�        
        titles.add("ȡ���ʸ�;��");       
        titles.add("רҵ�����ʸ�ȼ�");
        titles.add("������");
        titles.add("�ʸ����赥λ"); 
        titles.add("�Ƿ���Ҫרҵ�����ʸ�");
        
        //����ϵͳ�ؼ���
        titles.add("У���ʶ");
        
       	

        //���ݴ���
        model.put("name", "�����Աְҵ������Ϣ"); 
        model.put("titles", titles); 
        model.put("varList", varList);
        ViewExcel viewExcel = new ViewExcel();    
        return new ModelAndView(viewExcel, model);
	}
	
	
	/**
	 * ���������Աְҵ��Ϣ
	 * @param model
	 * @return
	 */
	private ModelAndView exportZhiye(ModelMap model) {
		
		
		//��������
		TOutUserJninfoExample sample = new TOutUserJninfoExample();
		
		
		TOutUserJninfoExample.Criteria criteria = sample.createCriteria();
		

		List<TOutUserJninfo> list = tOutUserJninfoMapper.selectByExample(sample);

        //varList ���ݣ���ά���ݣ�
        List<List<?>> varList =  new ArrayList<List<?>>();
        List<String> line;
        
        OutUser mapuser;
        
		for (TOutUserJninfo user:list) {
			 line = new ArrayList<String>();
			 
			 mapuser = userService.getUser(user.getUserid());
		 
			 line.add("");
			 			 
			 //��Ա���	����	�϶���ʼ����	�϶���ֹ����	�϶���λ
	         line.add(mapuser.getCode());
	         line.add(mapuser.getName());
	         line.add(DateUtil.format(user.getStartdate()));
	         line.add(DateUtil.format(user.getEnddate()));
	         line.add(user.getRdunit());
	         
	         //�϶������ʸ�����	�϶������ʸ�ȼ�	������ע��	�Ƿ���Ҫ�϶�
	         line.add(user.getRdname());
	         line.add(user.getRddengji());
	         line.add(user.getQt());
	         line.add(user.getIsmain());

	         line.add(user.getId());
	         	         
	         varList.add(line);
		}
		
		
		
		//titles  �б���
        List<String> titles = new ArrayList<String>();
        
        //����ģʽ	��Ա���	����	�϶���ʼ����	�϶���ֹ����	�϶���λ	
        titles.add("����ģʽ");
        titles.add("��Ա���");
        titles.add("����");       
        titles.add("�϶���ʼ����");
        titles.add("�϶���ֹ����");
        titles.add("�϶���λ");
        
        //�϶������ʸ�����	�϶������ʸ�ȼ�	������ע��	�Ƿ���Ҫ�϶�
        titles.add("�϶������ʸ�����");       
        titles.add("�϶������ʸ�ȼ�");
        titles.add("������ע��");
        titles.add("�Ƿ���Ҫ�϶�");
       
        //����ϵͳ�ؼ���
        titles.add("У���ʶ");
        
       	

        //���ݴ���
        model.put("name", "�����Աְҵ������Ϣ"); 
        model.put("titles", titles); 
        model.put("varList", varList);
        ViewExcel viewExcel = new ViewExcel();    
        return new ModelAndView(viewExcel, model);
	}
	
	/**
	 * ���������Ա�Ͷ���ͬ��Ϣ
	 * @param model
	 * @return
	 */
	private ModelAndView exportLaodong(ModelMap model) {
		//��������
		TOutUserHtExample sample = new TOutUserHtExample();
		
		
		TOutUserHtExample.Criteria criteria = sample.createCriteria();
		

		List<TOutUserHt> list = tOutUserHtMapper.selectByExample(sample);

        //varList ���ݣ���ά���ݣ�
        List<List<?>> varList =  new ArrayList<List<?>>();
        List<String> line;
        
        OutUser mapuser;
        
		for (TOutUserHt user:list) {
			 line = new ArrayList<String>();
			 
			 mapuser = userService.getUser(user.getUserid());
		 
			 line.add("");
			 			 
			 //����ģʽ	��Ա���	����	��ͬ����	�Ͷ���ͬ���	��ͬ��������
	         line.add(mapuser.getCode());
	         line.add(mapuser.getName());
	         line.add(user.getContype());
	         line.add(user.getConnumber());
	         line.add(user.getConqxtype());
	         
	         //�Ͷ���ͬ��ʼ����	�Ͷ���ͬ��ֹ����	�Ͷ���ͬ״̬	�Ͷ���ͬ����	�Ͷ���ͬǩ����λ	
	         line.add(DateUtil.format(user.getStartdate()));
	         line.add(DateUtil.format(user.getEnddate()));
	         line.add(user.getConstatus());
	         line.add(user.getQixian());
	         line.add(user.getUnit());
	       

	         //�����ͬǩ���ļ׷���˾	��Ӧ�����ͬ���	�����ͬ����
	         line.add(user.getUnit());	         
	         line.add(user.getNwconnumber());
	         line.add(user.getLwconname());
	         
	         line.add(user.getId());
	         	         
	         varList.add(line);
		}
		
		
		
		//titles  �б���
        List<String> titles = new ArrayList<String>();
        
        //����ģʽ	��Ա���	����	��ͬ����	�Ͷ���ͬ���	��ͬ��������		
        titles.add("����ģʽ");
        titles.add("��Ա���");
        titles.add("����");       
        titles.add("��ͬ����");
        titles.add("�Ͷ���ͬ���");
        titles.add("��ͬ��������");
        
        //�Ͷ���ͬ��ʼ����	�Ͷ���ͬ��ֹ����	�Ͷ���ͬ״̬	�Ͷ���ͬ����	�Ͷ���ͬǩ����λ	
        titles.add("�Ͷ���ͬ��ʼ����");       
        titles.add("�Ͷ���ͬ��ֹ����");
        titles.add("�Ͷ���ͬ״̬");
        titles.add("�Ͷ���ͬ����");
        titles.add("�Ͷ���ͬǩ����λ");
       
        
        //�����ͬǩ���ļ׷���˾	��Ӧ�����ͬ���	�����ͬ����
        titles.add("�����ͬǩ���ļ׷���˾");       
        titles.add("��Ӧ�����ͬ���");
        titles.add("�����ͬ����");
        
        //����ϵͳ�ؼ���
        titles.add("У���ʶ");
        
       	

        //���ݴ���
        model.put("name", "�����Ա�Ͷ���ͬ��Ϣ"); 
        model.put("titles", titles); 
        model.put("varList", varList);
        ViewExcel viewExcel = new ViewExcel();    
        return new ModelAndView(viewExcel, model);
	}
	
	/**
	 * ���������Ա�����ϵ��Ϣ
	 * @param model
	 * @return
	 */
	private ModelAndView exportJiechu(ModelMap model) {
		//��������
		TOutUserJcExample sample = new TOutUserJcExample();
		
		
		TOutUserJcExample.Criteria criteria = sample.createCriteria();
		

		List<TOutUserJc> list = tOutUserJcMapper.selectByExample(sample);

        //varList ���ݣ���ά���ݣ�
        List<List<?>> varList =  new ArrayList<List<?>>();
        List<String> line;
        
        OutUser mapuser;
        
		for (TOutUserJc user:list) {
			 line = new ArrayList<String>();
			 
			 mapuser = userService.getUser(user.getUserid());
		 
			 line.add("");
			 			 
			 //����ģʽ	��Ա���	����	���ԭ��	��������ϵ����	������������	;��˵�����������˾���ƣ�
	         line.add(mapuser.getCode());
	         line.add(mapuser.getName());
	         line.add(user.getJcreason());
	         
	         line.add(DateUtil.format(user.getJcdate()));
	         line.add(DateUtil.format(user.getGzenddate()));
	         line.add(user.getQt());

	         line.add(user.getId());
	         	         
	         varList.add(line);
		}
		
		
		
		//titles  �б���
        List<String> titles = new ArrayList<String>();
        
        //����ģʽ	��Ա���	����	���ԭ��	��������ϵ����	������������	;��˵�����������˾���ƣ�
        titles.add("����ģʽ");
        titles.add("��Ա���");
        titles.add("����"); 
        titles.add("���ԭ��"); 
        
        titles.add("��������ϵ����");
        titles.add("������������");
        titles.add(";��˵�����������˾���ƣ�");
        
        //����ϵͳ�ؼ���
        titles.add("У���ʶ");
        
       	

        //���ݴ���
        model.put("name", "�����Ա�����ϵ��Ϣ"); 
        model.put("titles", titles); 
        model.put("varList", varList);
        ViewExcel viewExcel = new ViewExcel();    
        return new ModelAndView(viewExcel, model);
	}	
	
	
	/**********************************ҵ��ӿ�*****************************end*********/
	
}
