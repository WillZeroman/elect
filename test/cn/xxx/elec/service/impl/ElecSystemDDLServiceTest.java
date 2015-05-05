package cn.xxx.elec.service.impl;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.xxx.elec.dao.ElecSystemDDLDao;
import cn.xxx.elec.service.ElecSystemDDLService;
import cn.xxx.elec.web.vo.ElecSystemDDLForm;

public class ElecSystemDDLServiceTest {
	@Test
	public void testBeans() {
		ApplicationContext atx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ElecSystemDDLService es = (ElecSystemDDLService)atx.getBean(ElecSystemDDLService.SERVICE_NAME);
		System.out.println(es);
		ElecSystemDDLDao ed = (ElecSystemDDLDao)atx.getBean(ElecSystemDDLDao.SERVICE_NAME);
		System.out.println(ed);
	}
	 
	@Test
	public void testFindKeyWord(){
		ApplicationContext atx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ElecSystemDDLService es = (ElecSystemDDLService)atx.getBean(ElecSystemDDLService.SERVICE_NAME);
		List<ElecSystemDDLForm> list = es.findKeyWord();
		for(int i=0;list!=null && i<list.size();i++){
			System.out.println(list.get(i));
		}
	}
	@Test
	public void testFindByKeyWord(){
		ApplicationContext atx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ElecSystemDDLService es = (ElecSystemDDLService)atx.getBean(ElecSystemDDLService.SERVICE_NAME);
		List<ElecSystemDDLForm> list = es.findByKeyWord("������λ");
		for(int i=0;list!=null && i<list.size();i++){
			System.out.println(list.get(i));
		}
	}
	@Test
	public void testRole(){
		ApplicationContext atx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ElecSystemDDLService es = (ElecSystemDDLService)atx.getBean(ElecSystemDDLService.SERVICE_NAME);
		List<ElecSystemDDLForm> list = es.findByKeyWord("角色类型");
		for(int i=0;list!=null && i<list.size();i++){
			System.out.println(list.get(i));
		}
	}
}
