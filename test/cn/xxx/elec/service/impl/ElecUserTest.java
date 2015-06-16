package cn.xxx.elec.service.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

import cn.xxx.elec.dao.ElecTextDao;
import cn.xxx.elec.dao.ElecUserDao;
import cn.xxx.elec.domain.ElecUser;
import cn.xxx.elec.service.ElecTextService;
import cn.xxx.elec.service.ElecUserService;
import cn.xxx.elec.web.vo.ElecUserForm;

public class ElecUserTest {

	@Test
	public void test() {
		ApplicationContext atx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ElecUserService eus = (ElecUserService) atx.getBean(ElecUserService.SERVICE_NAME);
		System.out.println(eus);
		ElecUserForm ef = new ElecUserForm();
		ef.setLoginName("aaaa");
		ef.setLoginPwd("123456");
		ef.setJctID("1");
		eus.saveUser(ef);
	}
	@Test
	public void testFind() {
		ApplicationContext atx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ElecUserService eus = (ElecUserService) atx.getBean(ElecUserService.SERVICE_NAME);
		ElecUserForm ef = new ElecUserForm();
		ef.setUserID("1");
		ef = eus.findElecUser(ef);
		System.out.println(ef);
		
	}
	@Test
	public void testPopedom(){
		ApplicationContext atx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ElecUserService eus = (ElecUserService) atx.getBean(ElecUserService.SERVICE_NAME);
		String str = eus.findPopedomByloginName("ad");
		System.out.println(str);
	}
	@Test
	public void testRole(){
		ApplicationContext atx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ElecUserService eus = (ElecUserService) atx.getBean(ElecUserService.SERVICE_NAME);
		java.util.Hashtable<String, String> ht = eus.findRoleNameByLoginName("ad");
		System.out.println(ht);
	}
	@Test
	public void testChart(){
		ApplicationContext atx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ElecUserService eus = (ElecUserService) atx.getBean(ElecUserService.SERVICE_NAME);
		List<ElecUserForm> list = eus.findUserByChart();
		System.out.println(list.get(0).getJctname());
	}
	
}
