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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;
import com.talkweb.ei.di.common.Const;
import com.talkweb.ei.di.common.DateUtil;
import com.talkweb.ei.di.common.ExcelUtil;
import com.talkweb.ei.di.common.JsonUtil;
import com.talkweb.ei.di.common.PageResult;
import com.talkweb.ei.di.common.ViewExcel;
import com.talkweb.ei.outmanager.dao.OutUserMapper;
import com.talkweb.ei.outmanager.model.OutCompany;
import com.talkweb.ei.outmanager.model.OutContract;
import com.talkweb.ei.outmanager.model.OutContractExample;
import com.talkweb.ei.outmanager.model.OutUser;
import com.talkweb.ei.outmanager.model.OutUserExample;
import com.talkweb.ei.outmanager.model.OutUser_S;
import com.talkweb.ei.outmanager.service.IUserService;


@Controller
@RequestMapping("/user") // url:/ģ��/��Դ/{id}/ϸ�� /seckill/list
public class UserController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IUserService userService;
	
	@Autowired
	private OutUserMapper outUserMapper;	

	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	private String login(Model model) {
		//List<Book> list = bookService.getList();
		//model.addAttribute("list", list);
		// list.jsp + model = ModelAndView		
		return "user/login";// WEB-INF/jsp/"list".jsp
	}
	
	/**********************************��֤�ӿ�************************start**************/
	/**
	 * ��֤
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	private String auth( @RequestParam("userId") String userid,@RequestParam("password") String pwd, ModelMap modelMap,HttpServletRequest request) {
		
		userid = "di";
		pwd = "wwwwww";
		
		boolean ret = userService.auth(userid, pwd);
		
		if(ret){
			
			// ���õ�¼��Ϣ
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(Const.SESSION_USER, userid);
			
			String userName = outUserMapper.selectByPrimaryKey(userid).getName();
			map.put(Const.SESSION_NAME, userName);
			insertLoginInfo(request, map);
			
			modelMap.addAttribute("userName", userName);
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
				outUser.setPwd("111111");
				
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
        List<String> exlValues = ExcelUtil.readFileExcel(rootPath+fileName,0,5,ExcelUtil.MAXEXPORTNUM+5,ExcelUtil.USER_COL_NUM);
       
        String result = "����ɹ���";
        
        boolean ret = userService.importBase(exlValues);
        if(!ret) {
        	result = "����ʧ�ܣ����������ļ���ʽ��";
        }
           
        //������Ϣ
        exlValues = ExcelUtil.readFileExcel(rootPath+fileName,1,5,ExcelUtil.MAXEXPORTNUM+5,ExcelUtil.USER_COL_NUM_JY);
        ret = userService.importJiaoyu(exlValues);
        if(!ret) {
        	result = "����ʧ�ܣ����������ļ���ʽ��";
        }      
        
//        //ְҵ��Ϣ
//        exlValues = ExcelUtil.readFileExcel(rootPath+fileName,2,5,ExcelUtil.MAXEXPORTNUM+5,ExcelUtil.USER_COL_NUM_ZY);
//        ret = userService.importZhiye(exlValues);
//        if(!ret) {
//        	result = "����ʧ�ܣ����������ļ���ʽ��";
//        }  
//        
//        
//        //רҵ��Ϣ
//        exlValues = ExcelUtil.readFileExcel(rootPath+fileName,3,5,ExcelUtil.MAXEXPORTNUM+5,ExcelUtil.USER_COL_NUM_ZZ);
//        ret = userService.importZhuanye(exlValues);
//        if(!ret) {
//        	result = "����ʧ�ܣ����������ļ���ʽ��";
//        }             
//           
//        //�Ͷ���ϵ
//        exlValues = ExcelUtil.readFileExcel(rootPath+fileName,4,5,ExcelUtil.MAXEXPORTNUM+5,ExcelUtil.USER_COL_NUM_LD);
//        ret = userService.importLaodong(exlValues);
//        if(!ret) {
//        	result = "����ʧ�ܣ����������ļ���ʽ��";
//        }       
//        
//        //�����ϵ
//        exlValues = ExcelUtil.readFileExcel(rootPath+fileName,5,5,ExcelUtil.MAXEXPORTNUM+5,ExcelUtil.USER_COL_NUM_JC);
//        ret = userService.importJiechu(exlValues);
//        if(!ret) {
//        	result = "����ʧ�ܣ����������ļ���ʽ��";
//        }          
        
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
        List<List> varList =  new ArrayList<List>();
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
		OutUserExample sample = new OutUserExample();
		
		
		OutUserExample.Criteria criteria = sample.createCriteria();
		
		//ֻ��ѯ����û�
		criteria.andUsertypeEqualTo("1");
				
		int total = userService.getUserSize(sample);
		List<OutUser> list = userService.getUserList(ExcelUtil.MAXEXPORTNUM, 0, sample);

        //varList ���ݣ���ά���ݣ�
        List<List> varList =  new ArrayList<List>();
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
	private ModelAndView exportFenpei(ModelMap model) {
		return null;
	}
	
	/**
	 * ���������Աרҵ��Ϣ
	 * @param model
	 * @return
	 */
	private ModelAndView exportZhuanye(ModelMap model) {
		return null;
	}
	
	
	/**
	 * ���������Աְҵ��Ϣ
	 * @param model
	 * @return
	 */
	private ModelAndView exportZhiye(ModelMap model) {
		return null;
	}
	
	/**
	 * ���������Ա�Ͷ���Ϣ
	 * @param model
	 * @return
	 */
	private ModelAndView exportLaodong(ModelMap model) {
		return null;
	}
	
	/**
	 * ���������Ա�����ϵ��Ϣ
	 * @param model
	 * @return
	 */
	private ModelAndView exportJiechu(ModelMap model) {
		return null;
	}	
	
	
	/**********************************ҵ��ӿ�*****************************end*********/
	
}
