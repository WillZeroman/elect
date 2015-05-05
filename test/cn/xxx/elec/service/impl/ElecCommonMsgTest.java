package cn.xxx.elec.service.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.xxx.elec.service.ElecCommonMsgService;
import cn.xxx.elec.util.StringHelper;
import cn.xxx.elec.web.vo.ElecCommonMsgForm;

public class ElecCommonMsgTest {

	@Test
	public void test() {
		ApplicationContext atx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ElecCommonMsgService es = (ElecCommonMsgService)atx.getBean(ElecCommonMsgService.SERVICE_NAME);
		es.findElecComonMsgFormListByCurrentDate();
	}
	@Test
	public void testUtil(){
		StringHelper h = new StringHelper();
		java.sql.Date d = h.utilDateConvertSqlDate(new java.util.Date());
		System.out.println(d);
	}
	@Test 
	public void testFindByCurrentDate(){
		ApplicationContext atx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ElecCommonMsgService es = (ElecCommonMsgService)atx.getBean(ElecCommonMsgService.SERVICE_NAME);
		List<ElecCommonMsgForm> list = es.findElecComonMsgFormListByCurrentDate();
		List<ElecCommonMsgForm> list2 = es.findElecComonMsgFormListByCurrentDate();
		List<ElecCommonMsgForm> list3 = es.findElecComonMsgFormListByCurrentDate();
		for(int i=0;list!=null && i<list.size();i++){
			System.out.println(list.get(i));
		}
	}
}
