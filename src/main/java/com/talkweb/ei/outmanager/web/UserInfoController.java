package com.talkweb.ei.outmanager.web;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.talkweb.ei.di.common.JsonUtil;
import com.talkweb.ei.di.common.PageResult;
import com.talkweb.ei.outmanager.dao.TOutUserFpinfoMapper;
import com.talkweb.ei.outmanager.dao.TOutUserHtMapper;
import com.talkweb.ei.outmanager.dao.TOutUserJcMapper;
import com.talkweb.ei.outmanager.dao.TOutUserJninfoMapper;
import com.talkweb.ei.outmanager.dao.TOutUserJyinfoMapper;
import com.talkweb.ei.outmanager.dao.TOutUserZyinfoMapper;
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

/**
 * 用户管理的信息操作
 * @author zhq
 *
 */
@Controller
@RequestMapping("/userinfo") // url:/模块/资源/{id}/细分 /seckill/list
public class UserInfoController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

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
	
	

	/**********************************业务接口***************************start***********/
	
	
	/***************分配部分***********************start***********/
	/**
	 * 分页查询分配信息
	 * @param uid
	 * @return
	 */
	
	@RequestMapping(value = "/fenpei_list_json", method = RequestMethod.GET, produces = {
	"application/json; charset=utf-8" })
	@ResponseBody	
	private PageResult getFenpeiList(String uid){
				

		//构建条件
		TOutUserFpinfoExample sample = new TOutUserFpinfoExample();
		
		
		TOutUserFpinfoExample.Criteria criteria = sample.createCriteria();
		
		//只查询当前用户
		criteria.andUseridEqualTo(uid);
		
		List<TOutUserFpinfo> list = tOutUserFpinfoMapper.selectByExample(sample);
		
				
		//构建返回值
		PageResult ret = new PageResult(true,list,list.size());			
		System.out.println("----------"+ret);	
		return ret;
			
	}
	
	
	
	/**
	 * 添加或更新
	 * @param jsonstr
	 * @return
	 */
	@RequestMapping(value = "/fenpei_update", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody  
    public String updateFenpei(@RequestBody String jsonstr) {
		
		
		logger.info("user_update======="+jsonstr);   
			
		TOutUserFpinfo outUser = JsonUtil.gson.fromJson(jsonstr,TOutUserFpinfo.class);  
		
		if(outUser!=null){
			
			//页面上没有ID说明是新增
			if(StringUtils.isEmpty(outUser.getId())){
				outUser.setId(UUID.randomUUID().toString());
								
				tOutUserFpinfoMapper.insert(outUser);
			} else {				
				tOutUserFpinfoMapper.updateByPrimaryKey(outUser);
			}
		}

		return "OK";
		
    } 
	

	@RequestMapping(value = "/fenpei_del", method = RequestMethod.POST)
    @ResponseBody  
    public String delFenpei(@RequestBody String jsonstr) {
		
		
		logger.info("del user======="+jsonstr);   
	
		
		List<TOutUserFpinfo> retList = JsonUtil.gson.fromJson(jsonstr,  
                new TypeToken<List<TOutUserFpinfo>>() {  
                }.getType());  

		for(TOutUserFpinfo element:retList)
        {
			tOutUserFpinfoMapper.deleteByPrimaryKey(element.getId());
        }
		
		return "OK";
		
    } 
	/***************分配部分***********************end***********/
	
	
	/***************教育部分***********************start***********/
	/**
	 * 分页查询教育信息
	 * @param uid
	 * @return
	 */
	
	@RequestMapping(value = "/jiaoyu_list_json", method = RequestMethod.GET, produces = {
	"application/json; charset=utf-8" })
	@ResponseBody	
	private PageResult getJiaoyuList(String uid){
				

		//构建条件
		TOutUserJyinfoExample sample = new TOutUserJyinfoExample();
		
		
		TOutUserJyinfoExample.Criteria criteria = sample.createCriteria();
		
		//只查询当前用户
		criteria.andUseridEqualTo(uid);
		
		List<TOutUserJyinfo> list = tOutUserJyinfoMapper.selectByExample(sample);
		
				
		//构建返回值
		PageResult ret = new PageResult(true,list,list.size());			
		System.out.println("----------"+ret);	
		return ret;
			
	}
	
	
	
	/**
	 * 添加或更新
	 * @param jsonstr
	 * @return
	 */
	@RequestMapping(value = "/jiaoyu_update", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody  
    public String updateJiaoyu(@RequestBody String jsonstr) {
		
		
		logger.info("user_update======="+jsonstr);   
			
		TOutUserJyinfo outUser = JsonUtil.gson.fromJson(jsonstr,TOutUserJyinfo.class);  
		
		if(outUser!=null){
			
			//页面上没有ID说明是新增
			if(StringUtils.isEmpty(outUser.getId())){
				outUser.setId(UUID.randomUUID().toString());
								
				tOutUserJyinfoMapper.insert(outUser);
			} else {				
				tOutUserJyinfoMapper.updateByPrimaryKey(outUser);
			}
		}

		return "OK";
		
    } 
	

	/**
	 * 删除
	 * @param jsonstr
	 * @return
	 */
	@RequestMapping(value = "/jiaoyu_del", method = RequestMethod.POST)
    @ResponseBody  
    public String delJiaoyu(@RequestBody String jsonstr) {
		
		
		logger.info("del user======="+jsonstr);   
	
		
		List<TOutUserJyinfo> retList = JsonUtil.gson.fromJson(jsonstr,  
                new TypeToken<List<TOutUserJyinfo>>() {  
                }.getType());  

		for(TOutUserJyinfo element:retList)
        {
			tOutUserJyinfoMapper.deleteByPrimaryKey(element.getId());
        }
		
		return "OK";
		
    } 
	/***************教育部分***********************end***********/	
	
	
	
	
	/***************职业技能部分***********************start***********/
	/**
	 * 分页查询
	 * @param uid
	 * @return
	 */
	
	@RequestMapping(value = "/zhiye_list_json", method = RequestMethod.GET, produces = {
	"application/json; charset=utf-8" })
	@ResponseBody	
	private PageResult getZhiyeList(String uid){
				

		//构建条件
		TOutUserZyinfoExample sample = new TOutUserZyinfoExample();
		
		
		TOutUserZyinfoExample.Criteria criteria = sample.createCriteria();
		
		//只查询当前用户
		criteria.andUseridEqualTo(uid);
		
		List<TOutUserZyinfo> list = tOutUserZyinfoMapper.selectByExample(sample);
		
				
		//构建返回值
		PageResult ret = new PageResult(true,list,list.size());			
		System.out.println("----------"+ret);	
		return ret;
			
	}
	
	
	
	/**
	 * 添加或更新
	 * @param jsonstr
	 * @return
	 */
	@RequestMapping(value = "/zhiye_update", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody  
    public String updateZhiye(@RequestBody String jsonstr) {
		
		
		logger.info("user_update======="+jsonstr);   
			
		TOutUserZyinfo outUser = JsonUtil.gson.fromJson(jsonstr,TOutUserZyinfo.class);  
		
		if(outUser!=null){
			
			//页面上没有ID说明是新增
			if(StringUtils.isEmpty(outUser.getId())){
				outUser.setId(UUID.randomUUID().toString());
								
				tOutUserZyinfoMapper.insert(outUser);
			} else {				
				tOutUserZyinfoMapper.updateByPrimaryKey(outUser);
			}
		}

		return "OK";
		
    } 
	

	/**
	 * 删除
	 * @param jsonstr
	 * @return
	 */
	@RequestMapping(value = "/zhiye_del", method = RequestMethod.POST)
    @ResponseBody  
    public String delZhiye(@RequestBody String jsonstr) {
		
		
		logger.info("del user======="+jsonstr);   
	
		
		List<TOutUserZyinfo> retList = JsonUtil.gson.fromJson(jsonstr,  
                new TypeToken<List<TOutUserZyinfo>>() {  
                }.getType());  

		for(TOutUserZyinfo element:retList)
        {
			tOutUserZyinfoMapper.deleteByPrimaryKey(element.getId());
        }
		
		return "OK";
		
    } 
	/***************职业技能部分***********************end***********/		
	
	
	
	/***************技能技术部分***********************start***********/
	/**
	 * 分页查询
	 * @param uid
	 * @return
	 */
	
	@RequestMapping(value = "/jineng_list_json", method = RequestMethod.GET, produces = {
	"application/json; charset=utf-8" })
	@ResponseBody	
	private PageResult getjinengList(String uid){
				

		//构建条件
		TOutUserJninfoExample sample = new TOutUserJninfoExample();
		
		
		TOutUserJninfoExample.Criteria criteria = sample.createCriteria();
		
		//只查询当前用户
		criteria.andUseridEqualTo(uid);
		
		List<TOutUserJninfo> list = tOutUserJninfoMapper.selectByExample(sample);
		
				
		//构建返回值
		PageResult ret = new PageResult(true,list,list.size());			
		System.out.println("----------"+ret);	
		return ret;
			
	}
	
	
	
	/**
	 * 添加或更新
	 * @param jsonstr
	 * @return
	 */
	@RequestMapping(value = "/jineng_update", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody  
    public String updatejineng(@RequestBody String jsonstr) {
		
		
		logger.info("user_update======="+jsonstr);   
			
		TOutUserJninfo outUser = JsonUtil.gson.fromJson(jsonstr,TOutUserJninfo.class);  
		
		if(outUser!=null){
			
			//页面上没有ID说明是新增
			if(StringUtils.isEmpty(outUser.getId())){
				outUser.setId(UUID.randomUUID().toString());
								
				tOutUserJninfoMapper.insert(outUser);
			} else {				
				tOutUserJninfoMapper.updateByPrimaryKey(outUser);
			}
		}

		return "OK";
		
    } 
	

	/**
	 * 删除
	 * @param jsonstr
	 * @return
	 */
	@RequestMapping(value = "/jineng_del", method = RequestMethod.POST)
    @ResponseBody  
    public String deljineng(@RequestBody String jsonstr) {
		
		
		logger.info("del user======="+jsonstr);   
	
		
		List<TOutUserJninfo> retList = JsonUtil.gson.fromJson(jsonstr,  
                new TypeToken<List<TOutUserJninfo>>() {  
                }.getType());  

		for(TOutUserJninfo element:retList)
        {
			tOutUserJninfoMapper.deleteByPrimaryKey(element.getId());
        }
		
		return "OK";
		
    } 
	/***************技能部分***********************end***********/		
	
	
	/***************劳动关系部分***********************start***********/
	/**
	 * 分页查询
	 * @param uid
	 * @return
	 */
	
	@RequestMapping(value = "/laodong_list_json", method = RequestMethod.GET, produces = {
	"application/json; charset=utf-8" })
	@ResponseBody	
	private PageResult getlaodongList(String uid){
				

		//构建条件
		TOutUserHtExample sample = new TOutUserHtExample();
		
		
		TOutUserHtExample.Criteria criteria = sample.createCriteria();
		
		//只查询当前用户
		criteria.andUseridEqualTo(uid);
		
		List<TOutUserHt> list = tOutUserHtMapper.selectByExample(sample);
		
				
		//构建返回值
		PageResult ret = new PageResult(true,list,list.size());			
		System.out.println("----------"+ret);	
		return ret;
			
	}
	
	
	
	/**
	 * 添加或更新
	 * @param jsonstr
	 * @return
	 */
	@RequestMapping(value = "/laodong_update", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody  
    public String updatelaodong(@RequestBody String jsonstr) {
		
		
		logger.info("user_update======="+jsonstr);   
			
		TOutUserHt outUser = JsonUtil.gson.fromJson(jsonstr,TOutUserHt.class);  
		
		if(outUser!=null){
			
			//页面上没有ID说明是新增
			if(StringUtils.isEmpty(outUser.getId())){
				outUser.setId(UUID.randomUUID().toString());
								
				tOutUserHtMapper.insert(outUser);
			} else {				
				tOutUserHtMapper.updateByPrimaryKey(outUser);
			}
		}

		return "OK";
		
    } 
	

	/**
	 * 删除
	 * @param jsonstr
	 * @return
	 */
	@RequestMapping(value = "/laodong_del", method = RequestMethod.POST)
    @ResponseBody  
    public String dellaodong(@RequestBody String jsonstr) {
		
		
		logger.info("del user======="+jsonstr);   
	
		
		List<TOutUserHt> retList = JsonUtil.gson.fromJson(jsonstr,  
                new TypeToken<List<TOutUserHt>>() {  
                }.getType());  

		for(TOutUserHt element:retList)
        {
			tOutUserHtMapper.deleteByPrimaryKey(element.getId());
        }
		
		return "OK";
		
    } 
	/***************劳动关系部分***********************end***********/	
	
	
	
	
	/***************外包关系解除部分***********************start***********/
	/**
	 * 分页查询
	 * @param uid
	 * @return
	 */
	
	@RequestMapping(value = "/jiechu_list_json", method = RequestMethod.GET, produces = {
	"application/json; charset=utf-8" })
	@ResponseBody	
	private PageResult getjiechuList(String uid){
				

		//构建条件
		TOutUserJcExample sample = new TOutUserJcExample();
		
		
		TOutUserJcExample.Criteria criteria = sample.createCriteria();
		
		//只查询当前用户
		criteria.andUseridEqualTo(uid);
		
		List<TOutUserJc> list = tOutUserJcMapper.selectByExample(sample);
		
				
		//构建返回值
		PageResult ret = new PageResult(true,list,list.size());			
		System.out.println("----------"+ret);	
		return ret;
			
	}
	
	
	
	/**
	 * 添加或更新
	 * @param jsonstr
	 * @return
	 */
	@RequestMapping(value = "/jiechu_update", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody  
    public String updatejiechu(@RequestBody String jsonstr) {
		
		
		logger.info("user_update======="+jsonstr);   
			
		TOutUserJc outUser = JsonUtil.gson.fromJson(jsonstr,TOutUserJc.class);  
		
		if(outUser!=null){
			
			//页面上没有ID说明是新增
			if(StringUtils.isEmpty(outUser.getId())){
				outUser.setId(UUID.randomUUID().toString());
								
				tOutUserJcMapper.insert(outUser);
			} else {				
				tOutUserJcMapper.updateByPrimaryKey(outUser);
			}
		}

		return "OK";
		
    } 
	

	/**
	 * 删除
	 * @param jsonstr
	 * @return
	 */
	@RequestMapping(value = "/jiechu_del", method = RequestMethod.POST)
    @ResponseBody  
    public String deljiechu(@RequestBody String jsonstr) {
		
		
		logger.info("jiechu_del======="+jsonstr);   

		List<TOutUserJc> retList = JsonUtil.gson.fromJson(jsonstr,  
                new TypeToken<List<TOutUserJc>>() {  
                }.getType());  

		for(TOutUserJc element:retList)
        {
			tOutUserJcMapper.deleteByPrimaryKey(element.getId());
        }
		
		return "OK";
		
    } 
	/***************外包关系解除部分***********************end***********/	
	
	
	
	
	
	
	
	
	
	
	/**********************************业务接口*****************************end*********/
	
}
