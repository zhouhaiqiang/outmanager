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
	
	
	
	/**
	 * �������
	 * ��ҳ��ѯ������Ϣ
	 * @param uid
	 * @return
	 */
	
	@RequestMapping(value = "/jtlist_json", method = RequestMethod.GET, produces = {
	"application/json; charset=utf-8" })
	@ResponseBody	
	private PageResult getJtList(int limit, int offset, String companyid,String unit,String username, String usercode,String concode,String startmonth,String endmonth){
				

		//��������
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
		
		//ʱ��εĲ�ѯ
		if(StringUtils.isNotEmpty(startmonth)&&StringUtils.isNotEmpty(endmonth)){
			criteria.andMonthBetween(startmonth, endmonth);
		}		

		sample.setLimit(offset+limit);
		sample.setOffset(offset+1);		
		
		
	
		long total = tOutJthyMapper.countByExample(sample);
		List<TOutJthy> list = tOutJthyMapper.selectPageByExample(sample);
						
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
	@RequestMapping(value = "/grupdate", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody  
    public String updateGrgongzi(@RequestBody String jsonstr) {
		
		
		logger.info("grgz_update======="+jsonstr);   
			
		TOutGongzi tOutGongzi = JsonUtil.gson.fromJson(jsonstr,TOutGongzi.class);  
		
		if(tOutGongzi!=null){
			
			//ҳ����û��ID˵��������
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
	 * ��ӻ����
	 * @param jsonstr
	 * @return
	 */
	@RequestMapping(value = "/jtupdate", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody  
    public String updateJtgongzi(@RequestBody String jsonstr) {
		
		
		logger.info("user_update======="+jsonstr);   
			
		TOutJthy outUser = JsonUtil.gson.fromJson(jsonstr,TOutJthy.class);  
		
		if(outUser!=null){
			
			//ҳ����û��ID˵��������
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
	 * ������ͬ��Ϣ��excel
	 * @return
	 */
	@RequestMapping(value = "/grexport", method = RequestMethod.GET)
	private ModelAndView grexport(ModelMap model){
		
		//��������
		VOutUsergzExample sample = new VOutUsergzExample();
		
		
		VOutUsergzExample.Criteria criteria = sample.createCriteria();
		
		int limit = iUserGz.getUserGzSize(sample);

		List<VOutUsergz> list = iUserGz.getUserGzList(limit, 0, sample);

        //varList ���ݣ���ά���ݣ�
        List<List<?>> varList =  new ArrayList<List<?>>();
        List<String> line;
        
        OutUser mapuser;
        
		for (VOutUsergz user:list) {
			 line = new ArrayList<String>();
			 
			 BigDecimal xj = new BigDecimal("0");
		 
			 line.add("");
			 			 
			 //����ģʽ	��Ա���	����	���֤����	���������˾����
	         line.add(user.getCode());
	         line.add(user.getName());	         
	         line.add(user.getIdnumber());	         
	         line.add(user.getCompanyid());


	         
	         //��н�¶�	�̶�����	��Ч����	��������	���ڷ�
	         line.add(user.getMonth());
	         line.add(StringUtils.formatBigDecimal(user.getJiben()));
	         xj = xj.add(user.getJiben());
	         
	         line.add(StringUtils.formatBigDecimal(user.getJixiao()));
	         xj = xj.add(user.getJixiao());
	         
	         line.add(StringUtils.formatBigDecimal(user.getJintie()));
	         xj = xj.add(user.getJintie());
	         
	         line.add(StringUtils.formatBigDecimal(user.getGuojie()));
	         xj = xj.add(user.getGuojie());
	         
	         //�Ӱ๤��	����������֧��	Ӧ�����	˰ǰ�ۿ���	˰��ۿ���
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
	         
	         
	         //�籣������ۼ���	��������˰�۽ɶ�	ʵ�����	���ϱ���	��������
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
	         
	         ///ʧҵ����	ҽ�Ʊ���	���˱���	������	С��
	         line.add(StringUtils.formatBigDecimal(user.getShiye()));
	         xj = xj.add(user.getShiye());
	         
	         line.add(StringUtils.formatBigDecimal(user.getYiliao()));	
	         xj = xj.add(user.getYiliao());
	         
	         line.add(StringUtils.formatBigDecimal(user.getGongshang()));
	         xj = xj.add(user.getGongshang());
	         
	         line.add(StringUtils.formatBigDecimal(user.getGongji()));
	         xj = xj.add(user.getGongji());
	         
	         //С��
	         line.add(StringUtils.formatBigDecimal(xj));	         
	         
	         //Ϊ����ҵ������ʼ����	�ڱ���ҵ������ᱣ����ʼ����	������	�����	˰��	�����˹�֧����Ŀ	��ע
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
		
		
		
		//titles  �б���
        List<String> titles = new ArrayList<String>();
        
        //����ģʽ	��Ա���	����	���֤����	���������˾����	

        titles.add("����ģʽ");
        titles.add("��Ա���");
        titles.add("����"); 
        titles.add("���֤����");        
        titles.add("���������˾����");
        
        //��н�¶�	�̶�����	��Ч����	��������	���ڷ�	
        titles.add("��н�¶�");
        titles.add("�̶�����");
        titles.add("��Ч����");
        titles.add("��������");
        titles.add("���ڷ�");
        
        //�Ӱ๤��	����������֧��	Ӧ�����	˰ǰ�ۿ���	˰��ۿ���	
        titles.add("�Ӱ๤��");
        titles.add("����������֧��");
        titles.add("Ӧ�����");
        titles.add("˰ǰ�ۿ���");
        titles.add("˰��ۿ���");
        
        //�籣������ۼ���	��������˰�۽ɶ�	ʵ�����	���ϱ���	��������
        titles.add("�籣������ۼ���");
        titles.add("��������˰�۽ɶ�");
        titles.add("ʵ�����");
        titles.add("���ϱ���");
        titles.add("��������");       

        
        //ʧҵ����	ҽ�Ʊ���	���˱���	������	С��	
        titles.add("ʧҵ����");
        titles.add("ҽ�Ʊ���");
        titles.add("���˱���");
        titles.add("������");
        titles.add("С��");          
        
        //Ϊ����ҵ������ʼ����	�ڱ���ҵ������ᱣ����ʼ����	������	�����	˰��	�����˹�֧����Ŀ	��ע        
        titles.add("Ϊ����ҵ������ʼ����");
        titles.add("�ڱ���ҵ������ᱣ����ʼ����");
        titles.add("������");
        titles.add("�����");
        titles.add("˰��");            
        titles.add("�����˹�֧����Ŀ");
        titles.add("��ע");         
        
        titles.add("У���ʶ");
        
        //���ݴ���
        model.put("name", "�����Ա���˷�����Ϣ"); 
        model.put("titles", titles); 
        model.put("varList", varList);
        ViewExcel viewExcel = new ViewExcel();    
        return new ModelAndView(viewExcel, model);
	}
	
	
	
	/**
	 * ����excel
	 * @param type �û���Ϣ���ͣ����������Ϣ�ȵ�
	 * @return
	 */
	@RequestMapping(value = "/jtexport", method = RequestMethod.GET)
	private ModelAndView exportjt(ModelMap model,String type){
		
		//��������
		TOutJthyExample sample = new TOutJthyExample();
		
		
		TOutJthyExample.Criteria criteria = sample.createCriteria();
		

		List<TOutJthy> list = tOutJthyMapper.selectByExample(sample);

        //varList ���ݣ���ά���ݣ�
        List<List<?>> varList =  new ArrayList<List<?>>();
        List<String> line;
        
        OutUser mapuser;
        
		for (TOutJthy user:list) {
			 line = new ArrayList<String>();
			 
			
		 
			 line.add("");		 			 
			 //����ģʽ	��������������֯	�����˾����	�����ͬ���	�ڼ�	
	         line.add(user.getUnit());
	         line.add(user.getCompanyid());
	         line.add(user.getConcode());
	         line.add(user.getMonth());
	         
	         
	         //������	�����	˰��	�����˹�֧����Ŀ	��ְǰ�˹�����	��ע
	         line.add(StringUtils.formatBigDecimal(user.getGhhy()));
	         line.add(StringUtils.formatBigDecimal(user.getGlh()));
	         line.add(StringUtils.formatBigDecimal(user.getShuijin()));
	         line.add(StringUtils.formatBigDecimal(user.getQt()));
	         line.add(StringUtils.formatBigDecimal(user.getLzrhy()));
	         line.add(user.getRemark());

	         line.add(user.getId());
	         	         
	         varList.add(line);
		}
		
		
		
		//titles  �б���
        List<String> titles = new ArrayList<String>();
        
        //����ģʽ	��������������֯	�����˾����	�����ͬ���	�ڼ�	
        titles.add("����ģʽ");
        titles.add("��������������֯");
        titles.add("�����˾����"); 
        titles.add("�����ͬ���");         
        titles.add("�ڼ�");
        
        //������	�����	˰��	�����˹�֧����Ŀ	��ְǰ�˹�����	��ע
        titles.add("������");
        titles.add("�����");
        titles.add("˰��");
        titles.add("�����˹�֧����Ŀ");
        titles.add("��ְǰ�˹�����");
        titles.add("��ע");
        
        //����ϵͳ�ؼ���
        titles.add("У���ʶ");
               	
        //���ݴ���
        model.put("name", "������������Ϣ"); 
        model.put("titles", titles); 
        model.put("varList", varList);
        ViewExcel viewExcel = new ViewExcel();    
        return new ModelAndView(viewExcel, model);
	}	
	
	/**********************************ҵ��ӿ�*****************************end*********/

	
}
