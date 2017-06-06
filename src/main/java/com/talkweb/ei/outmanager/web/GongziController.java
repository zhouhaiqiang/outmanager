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
 * �û��������Ϣ����
 * @author zhq
 *
 */
@Controller
@RequestMapping("/gongzi") // url:/ģ��/��Դ/{id}/ϸ�� /seckill/list
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

	/**********************************ҵ��ӿ�***************************start***********/
	
	
	/***************���ò���***********************start***********/
	
	/**
	 * ���˷���ά��
	 */
	@RequestMapping(value = "/grlist", method = RequestMethod.GET)
	private String grlist(Model model) {			
		return "gongzi/listgr";
	}	
	
	/**
	 * �������ά��
	 */	
	@RequestMapping(value = "/jtlist", method = RequestMethod.GET)
	private String jtlist(Model model) {		
		return "gongzi/listjt";
	}	
	
	
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	
	//��˾��Ϣ����
	private String uploadCompany(Model model) {	
		return "/gongzi/upload";
	}
	
	
	

	/**
	 * ��ҳ��ѯ���˹���
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
				

		//��������
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
		
		//ʱ��εĲ�ѯ
		if(StringUtils.isNotEmpty(startmonth)&&StringUtils.isNotEmpty(endmonth)){
			criteria.andMonthBetween(startmonth, endmonth);
		}		
	

		
		sample.setLimit(offset+limit);
		sample.setOffset(offset+1);
		List<VOutUsergz> list =  iUserGz.getUserGzList(limit, offset, sample);				
		int total = iUserGz.getUserGzSize(sample);
		
		//��������ֵ
		PageResult ret = new PageResult(true,list,total);			
		System.out.println("----------"+ret);	
		return ret;
			
	}
	
	
	
//	/**
//	 * �������
//	 * ��ҳ��ѯ������Ϣ
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
//		//��������
//		TOutUserFpinfoExample sample = new TOutUserFpinfoExample();
//		
//		
//		TOutUserFpinfoExample.Criteria criteria = sample.createCriteria();
//		
//		//ֻ��ѯ��ǰ�û�
//		criteria.andUseridEqualTo(uid);
//		
//		List<TOutUserFpinfo> list = tOutUserFpinfoMapper.selectByExample(sample);
//		
//				
//		//��������ֵ
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
//	 * ��ӻ����
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
//			//ҳ����û��ID˵��������
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
//	 * ��ӻ����
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
//			//ҳ����û��ID˵��������
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
//     * �û���Ϣ���루��sheet���룩
//     * @param file
//     * @param request
//     * @param model
//     * @return
//     */
//    @RequestMapping(value = "/grimport")
//    
//    //������
//    //@Transactional 
//    public String grimport(
//            @RequestParam(value = "excelFile", required = false) MultipartFile file, ModelMap model)
//    {
//        // ������·���ı������
//        String rootPath;
//        SimpleDateFormat format = new SimpleDateFormat("yyyy\\MM\\dd\\");
//        String subpath = format.format(new Date());
//
//        rootPath = Const.TMPFILE_ROOT + subpath;
//
//        System.out.println("��ʼ...");
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
//        // ����
//        try
//        {
//            file.transferTo(targetFile);
//            
//            System.out.println("�ļ��ϴ�ok...");
//        } catch (Exception e)
//        {
//            //e.printStackTrace();
//            System.out.println("�ļ��ϴ�err...");
//        }
//        
//        
//        
//        
//        //��ȡexcel ������ ��ֹ�ڴ����
//        //������Ϣ
//        List<String> exlValues = ExcelUtil.readFileExcel(rootPath+fileName,0,4,ExcelUtil.MAXEXPORTNUM+4,ExcelUtil.USER_COL_NUM);
//       
//        String result = "����ɹ���";
//        
//        boolean ret =  false;//= userService.importBase(exlValues);
//        if(!ret) {
//        	result = "����ʧ�ܣ����������ļ���ʽ��";
//        }
//        
//        
//        return result;
//    }
//	
//	
//	
//	/**
//	 * ������ͬ��Ϣ��excel
//	 * @param type �û���Ϣ���ͣ����������Ϣ�ȵ�
//	 * @return
//	 */
//	@RequestMapping(value = "/jtexport", method = RequestMethod.GET)
//	private ModelAndView jtexport(ModelMap model,String type){
//		
//		//��������
//		TOutUserJcExample sample = new TOutUserJcExample();
//		
//		
//		TOutUserJcExample.Criteria criteria = sample.createCriteria();
//		
//
//		List<TOutUserJc> list = tOutUserJcMapper.selectByExample(sample);
//
//        //varList ���ݣ���ά���ݣ�
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
//			 //����ģʽ	��Ա���	����	���ԭ��	��������ϵ����	������������	;��˵�����������˾���ƣ�
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
//		//titles  �б���
//        List<String> titles = new ArrayList<String>();
//        
//        //����ģʽ	��Ա���	����	���ԭ��	��������ϵ����	������������	;��˵�����������˾���ƣ�
//        titles.add("����ģʽ");
//        titles.add("��Ա���");
//        titles.add("����"); 
//        titles.add("���ԭ��"); 
//        
//        titles.add("��������ϵ����");
//        titles.add("������������");
//        titles.add(";��˵�����������˾���ƣ�");
//        
//        //����ϵͳ�ؼ���
//        titles.add("У���ʶ");
//
//        //���ݴ���
//        model.put("name", "�����Ա�����ϵ��Ϣ"); 
//        model.put("titles", titles); 
//        model.put("varList", varList);
//        ViewExcel viewExcel = new ViewExcel();    
//        return new ModelAndView(viewExcel, model);
//	}
//	
//	
//	
//	/**
//	 * ����excel
//	 * @param type �û���Ϣ���ͣ����������Ϣ�ȵ�
//	 * @return
//	 */
//	@RequestMapping(value = "/exportjt", method = RequestMethod.GET)
//	private ModelAndView exportjt(ModelMap model,String type){
//		
//		//��������
//		TOutUserJcExample sample = new TOutUserJcExample();
//		
//		
//		TOutUserJcExample.Criteria criteria = sample.createCriteria();
//		
//
//		List<TOutUserJc> list = tOutUserJcMapper.selectByExample(sample);
//
//        //varList ���ݣ���ά���ݣ�
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
//			 //����ģʽ	��Ա���	����	���ԭ��	��������ϵ����	������������	;��˵�����������˾���ƣ�
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
//		//titles  �б���
//        List<String> titles = new ArrayList<String>();
//        
//        //����ģʽ	��Ա���	����	���ԭ��	��������ϵ����	������������	;��˵�����������˾���ƣ�
//        titles.add("����ģʽ");
//        titles.add("��Ա���");
//        titles.add("����"); 
//        titles.add("���ԭ��"); 
//        
//        titles.add("��������ϵ����");
//        titles.add("������������");
//        titles.add(";��˵�����������˾���ƣ�");
//        
//        //����ϵͳ�ؼ���
//        titles.add("У���ʶ");
//        
//       	
//
//        //���ݴ���
//        model.put("name", "�����Ա�����ϵ��Ϣ"); 
//        model.put("titles", titles); 
//        model.put("varList", varList);
//        ViewExcel viewExcel = new ViewExcel();    
//        return new ModelAndView(viewExcel, model);
//	}	
//	
//	
//	
//	
//	/***************���䲿��***********************end***********/
	

	
	/**********************************ҵ��ӿ�*****************************end*********/
	
}
