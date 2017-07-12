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
 * �û��������Ϣ����
 * @author zhq
 *
 */
@Controller
@RequestMapping("/action") // url:/ģ��/��Դ/{id}/ϸ�� /seckill/list
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

	/**********************************ҵ��ӿ�***************************start***********/
	
	
	
	
	/**
	 * �б�
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	private String grlist(Model model) {			
		return "action/list";
	}	
	

	/**
	 * �������
	 */
	@RequestMapping(value = "/padd", method = RequestMethod.GET)
	private String padd(Model model) {			
		return "action/padd";
	}
	
	/**
	 * ��������
	 */
	@RequestMapping(value = "/pcreate", method = RequestMethod.GET)
	private String pcreate(Model model) {			
		return "action/pcreate";
	}	
	
	
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	
	//��Ϣ����
	private String uploadCompany(Model model) {	
		return "/action/upload";
	}
	
	
	



	/**
	 * ��ѯ
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
				

		//��������
		VOutUseractionExample sample = new VOutUseractionExample();
		
		
		VOutUseractionExample.Criteria criteria = sample.createCriteria();


		if(StringUtils.isNotEmpty(unit)){
			criteria.andUnitEqualTo(unit);
		}
		if(StringUtils.isNotEmpty(username)){
			criteria.andNameLike(username);
		}

		
		//��ҳ����
		sample.setLimit(offset+limit);
		sample.setOffset(offset+1);		

		long total = vOutUseractionMapper.countByExample(sample);
		List<VOutUseraction> list =  vOutUseractionMapper.selectPageByExample(sample);
						
		//��������ֵ
		PageResult ret = new PageResult(true,list,Integer.parseInt(total+""));			
		System.out.println("----------"+ret);	

		return ret;
			
	}
	
	
	

	

	
	/**
	 * ��ӻ����
	 * @param jsonstr
	 * @return
	 */
	@RequestMapping(value = "/update", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody  
    public String updateGrgongzi(@RequestBody String jsonstr) {
		
		
		logger.info("action_update======="+jsonstr);   
			
		VOutUseraction tOutGongzi = JsonUtil.gson.fromJson(jsonstr,VOutUseraction.class);  
		
		if(tOutGongzi!=null){
			
			//�����ϼ�¼
			TOutAction old =  tOutActionMapper.selectByPrimaryKey(tOutGongzi.getId());
			
			
			//ҳ�����޸ĵ���������
			old.setStartdate(tOutGongzi.getStartdate());			
			old.setYwline(tOutGongzi.getYwline());
			old.setYwaction(tOutGongzi.getYwaction());
					
			tOutActionMapper.updateByPrimaryKey(old);
		
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
    @RequestMapping(value = "/grimport")
    
    //������
    //@Transactional 
    public String grimport(
            @RequestParam(value = "excelFile", required = false) MultipartFile file, @RequestParam("modeltype") String modeltype, ModelMap model)
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
        List<String> exlValues;
        boolean ret =  false;
        //��ȡ���ݺ͵��뵽db
        if("0".equals(modeltype)){
        	exlValues = ExcelUtil.readFileExcel(rootPath+fileName,0,5,ExcelUtil.MAXEXPORTNUM+5,ExcelUtil.HY_COL_NUM_GR);
        	ret = iUserGz.importGrhy(exlValues);
        } else {
        	exlValues = ExcelUtil.readFileExcel(rootPath+fileName,1,5,ExcelUtil.MAXEXPORTNUM+5,ExcelUtil.HY_COL_NUM_JT);
        	ret = iUserGz.importJthy(exlValues);
        }

        String result = "����ɹ���";

        if(!ret) {
        	result = "����ʧ�ܣ����������ļ���ʽ��";
        }
        
        model.addAttribute("msg", result);
        return "/common/showmsg";
    }
	
	
	
	/**
	 * �����������Ϣ��excel
	 * @return
	 */
	@RequestMapping(value = "/export", method = RequestMethod.GET)
	private ModelAndView export(ModelMap model){
		
		//��������
		VOutUseractionExample sample = new VOutUseractionExample();
		
		
		VOutUseractionExample.Criteria criteria = sample.createCriteria();

		long total = vOutUseractionMapper.countByExample(sample);
		
		//��ҳ����
		sample.setLimit(Integer.parseInt(total+""));
		sample.setOffset(1);		
		
		List<VOutUseraction> list =  vOutUseractionMapper.selectPageByExample(sample);


        //varList ���ݣ���ά���ݣ�
        List<List<?>> varList =  new ArrayList<List<?>>();
        List<String> line;
        
        OutUser mapuser;
        
		for (VOutUseraction user:list) {
			 line = new ArrayList<String>();
			 
			 BigDecimal xj = new BigDecimal("0");
		 
			 line.add("");
			 			 
			 //����ģʽ   ��֯   ��Ա���	����	ҵ����  ҵ�� �Ƿ����ɱ�  ��ʼ����
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
		
		
		
		//titles  �б���
        List<String> titles = new ArrayList<String>();
        
        //����ģʽ   ��֯   ��Ա���	����	ҵ����  ҵ�� �Ƿ����ɱ�  ��ʼ����

        titles.add("����ģʽ");
        
        titles.add("��֯");
        
        titles.add("��Ա���");
        titles.add("����"); 
        titles.add("ҵ���� ");        
        titles.add("ҵ��");
        
        titles.add("�Ƿ����ɱ�");
        titles.add("��ʼ����");
        
        titles.add("У���ʶ");
        
        //���ݴ���
        model.put("name", "��Ա�������Ϣ"); 
        model.put("titles", titles); 
        model.put("varList", varList);
        ViewExcel viewExcel = new ViewExcel();    
        return new ModelAndView(viewExcel, model);
	}
	
	

	
	/**********************************ҵ��ӿ�*****************************end*********/

	
}
