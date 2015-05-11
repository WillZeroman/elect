package cn.xxx.elec.service.impl;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.xxx.elec.service.ElecRoleService;
import cn.xxx.elec.service.ElecUserService;
import cn.xxx.elec.util.XmlObject;
import cn.xxx.elec.web.vo.ElecRoleForm;
import cn.xxx.elec.web.vo.ElecUserForm;

public class ElecRoleTest {
	private ApplicationContext atx = null;
	
	@Before
	public void before(){
		//atx = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
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
	public void testRole(){
		//List<ElecSystemDDLForm> systemList = elecSystemDDLService.findByKeyWord("角色类型");
		//ApplicationContext atx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ElecRoleService e = (ElecRoleService)atx.getBean(ElecRoleService.SERVICE_NAME);
		//List<XmlObject> list = e.readXml();
		//List<ElecUserForm> list = e.findUserListByRoleID("2");
		ElecRoleForm ef = new ElecRoleForm();
		ef.setRole("111");
		String[] str = {"a","b","c","d","e"};
		String[] us = {"1","2","4"};
		ef.setSelectoper(str);
		ef.setSelectuser(us);
        e.save(ef);
	}
	@Test
	public void testA(){
		String s = "f9b1eb324d41c17b014d41c1e5190000, f9b1eb324d41c17b014d41c479bf0001, 402881ef4d3d4ee3014d3d4f28be0000, 402881ef4d3d5960014d3d5a8c960000, 402881ef4d3d5960014d3d5b55ad0002, 402881ef4d3d5960014d3de1e0710003";
		
		String[] ids = s.trim().split(",");
		for(int i=0;i<ids.length;i++){
			System.out.println(ids[i]);
		}
	}
	
}
