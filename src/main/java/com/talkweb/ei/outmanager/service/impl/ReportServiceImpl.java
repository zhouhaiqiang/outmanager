package com.talkweb.ei.outmanager.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talkweb.ei.di.common.DateUtil;
import com.talkweb.ei.outmanager.dao.TOutReportMapper;
import com.talkweb.ei.outmanager.dao.TOutReportstMapper;
import com.talkweb.ei.outmanager.model.TOutReport;
import com.talkweb.ei.outmanager.model.TOutReportst;
import com.talkweb.ei.outmanager.model.TOutReportstExample;
import com.talkweb.ei.outmanager.service.IReportService;

@Service
public class ReportServiceImpl implements IReportService {

	
	//����λ�б�
	public static final String[] UNITS={
			"ɽ��ʡ�ֹ�˾",
			"ɽ��ʡ�ֹ�˾����",
			"̫ԭ�зֹ�˾",
			"�����зֹ�˾",
			"��ͬ�зֹ�˾",
			"˷���зֹ�˾",
			"�����зֹ�˾",
			"��Ȫ�зֹ�˾",
			"�����зֹ�˾",
			"�����зֹ�˾",
			"�����зֹ�˾",
			"�ٷ��зֹ�˾",
			"�˳��зֹ�˾"			
	};
	
	

	
	
	//��ȱ�����
	public static final String[] YEAR_REPORTNAME={
			"1-1��ȫ�ھ���Ա����ͳ��",
			"1-3��ʡ����˾�������ͳ�Ʊ�",
			"1-4�����й�˾�������ͳ�Ʊ�",
			"2-1����Ա����(һ)",
			"2-2����Ա����(��)",
			"3-4��������ҵ�������Ա��λ����",
			"3-5������������Առ�ȱ䶯���",
			"3-14��������ҵ�������Ա�ؼ�ְ�����",
			"4-3��������ҵ�������Աְλ�㼶�䶯����",
			"5-3��������ҵ�������Ա�����䶯",
			"7����Ա���ʽṹ�Ż�����"			
	};
	
	
	
	//ģ��ҳ�涨��
	public static  Map<String,String> YEAR_REPORTTMP = new HashMap<String,String>();
	static {
		for (int i = 0; i < YEAR_REPORTNAME.length; i++) {
			YEAR_REPORTTMP.put(YEAR_REPORTNAME[i], "z_yearexl"+i);
		}
				
	}

	//���ȱ�����
	public static final String[] SEC_REPORTNAME={
			"03������ҵ�������Ա�����䶯���ͳ�Ʊ�",
			"05��˾����������Ա���ͳ�Ʊ�",
			"07ʡ������Ա�������ͳ�Ʊ�",
			"08���б�����Ա�������ͳ�Ʊ�",
			"09��λռ�ȱ仯���ͳ�Ʊ�",
			"10��Աѧ���ṹ���ͳ�Ʊ�"
				
	};
	
	//ģ��ҳ�涨��
	public static  Map<String,String> SEC_REPORTTMP = new HashMap<String,String>();
	static {
		for (int i = 0; i < SEC_REPORTNAME.length; i++) {
			SEC_REPORTTMP.put(SEC_REPORTNAME[i], "z_secexl"+i);
		}
				
	}
	
	//�±�������
	public static final String[] MONTH_REPORTNAME={
			"1��Ա�������(�±�)",
			"2-3��������ҵ�������Ա�����䶯���(�±�)",
			"3��������Ϣͳ�Ʊ�(�±�)"
					
	}; 	
	
	//ģ��ҳ�涨��
	public static  Map<String,String> MONTH_REPORTTMP = new HashMap<String,String>();
	static {
		for (int i = 0; i < MONTH_REPORTNAME.length; i++) {
			MONTH_REPORTTMP.put(MONTH_REPORTNAME[i], "z_monthexl"+i);
		}
				
	}	
	
	
	@Autowired
	private TOutReportstMapper tOutReportstMapper;
	
	@Autowired
	private TOutReportMapper tOutReportMapper;	
	
	
	@Override
	public boolean checkReport(String type, String qdate) {
		//��������
		TOutReportstExample sample = new TOutReportstExample();
		
		
		TOutReportstExample.Criteria criteria = sample.createCriteria();

		//��������
		criteria.andReptypeEqualTo(type);
		

		//���ɵ�����
		Date reqdate = DateUtil.parseDate(qdate,DateUtil.C_DATE_PATTON_DEFAULT);
		
		criteria.andRepdateEqualTo(reqdate);


		//��ѯ��Ӧ�ļ�¼
		try {
			List<TOutReportst> list =  tOutReportstMapper.selectByExample(sample);
			if(list!=null&&list.size()>0){
				
				return true;
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		return false;
		
	}

	@Override
	public boolean createReport(String type, String qdate) {
		
		
		//��ϸ��������־
		boolean inData = false;
		//���������
		if("�걨".equals(type)){
			inData = createYearData(qdate,type);
		}

		//�����������
		if("����".equals(type)){
			inData = createSecData(qdate,type);
		}		
		
		
		//�·��������
		if("�±�".equals(type)){
			inData = createMonthrData(qdate,type);
		}		
		
		//״̬��
		if(inData){
			TOutReportst record = new TOutReportst();
			record.setId(UUID.randomUUID().toString());
			record.setRepdate(DateUtil.parseDate(qdate,DateUtil.C_DATE_PATTON_DEFAULT));
			record.setIntf("���ɴ���");
			record.setIsrecreate("�������³�ȡ");
			record.setReptype(type);
			record.setUnit("ɽ��ʡ�ֹ�˾");						
			tOutReportstMapper.insert(record);
			
			return true;
		}
		
	
		return false;
	}
	
	
	/**
	 * �걨��ϸ�������
	 * @param qdate
	 * @return
	 */
	private boolean createYearData(String qdate,String type){

		//�ֱ����
		for (int i = 0; i < UNITS.length; i++) {			
			for (int j = 0; j < YEAR_REPORTNAME.length; j++) {				
				createRcode(qdate,YEAR_REPORTNAME[j],type,UNITS[i],"","[{},{}]");
				
			}
						
		}

		return true;
	}
	
	
	private boolean createRcode(String qdate, String name,String type,String unit,String code,String data){
		TOutReport record = new TOutReport();
		//����������
		record.setId(UUID.randomUUID().toString());
		record.setRepdate(DateUtil.parseDate(qdate,DateUtil.C_DATE_PATTON_DEFAULT));
		record.setReptype(type);		
		
		//������....
		record.setIntf("���ɴ���");
		
		//������  ������....
		record.setName(name);
		
		
		//��λ���� ������....
		record.setUnit(unit);
		
		//��ϸ����json��ʽ  ������....
		record.setRepdata(data);
		
		//������  ������....
		record.setRepcode(code);

		//�����¼
		try {
			if (tOutReportMapper.insert(record)>0) {
				return true;
			};
			
		} catch (Exception e) {
			return false;
		}
		return false;
	}
	
	
	/**
	 * ���ȱ���ϸ�������
	 * @param qdate
	 * @return
	 */
	private boolean createSecData(String qdate,String type){
		
		
		//�ֱ����
		for (int i = 0; i < UNITS.length; i++) {			
			for (int j = 0; j < SEC_REPORTNAME.length; j++) {				
				createRcode(qdate,SEC_REPORTNAME[j],type,UNITS[i],"","[{},{}]");
				
			}
						
		}

		return true;
	}
	/**
	 * �±���ϸ�������
	 * @param qdate
	 * @return
	 */
	private boolean createMonthrData(String qdate,String type){
		
		//�ֱ����
		for (int i = 0; i < UNITS.length; i++) {			
			for (int j = 0; j < MONTH_REPORTNAME.length; j++) {				
				createRcode(qdate,MONTH_REPORTNAME[j],type,UNITS[i],"","[{},{}]");
				
			}
						
		}

		return true;
	}
			

}
