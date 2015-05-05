package cn.xxx.elec.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.xxx.elec.dao.ElecTextDao;
import cn.xxx.elec.domain.ElecText;

public class ElecTextDaoImplTest {

	@Test
	public void testSave() {
		ElecText et = new ElecText();
		et.setTextName("测试hibernate");
		et.setTextDate(new Date());
		et.setTextRemark("测试hibernate备注");
		ElecTextDaoImpl etdi = new ElecTextDaoImpl();
		etdi.save(et);
	}
	@Test
	public void testUpdate() {
		ApplicationContext atx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ElecTextDao etd = (ElecTextDao) atx.getBean(ElecTextDao.SERVICE_NAME);		
		ElecText et = new ElecText();
		et.setTextID("ff8080814a424199014a42419ac80000");
		et.setTextName("测试update");
		et.setTextDate(new Date());
		et.setTextRemark("测试update 修改了第一个元素值");
		etd.update(et);
	}

	@Test
	public void testFindByID() {
		ApplicationContext atx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ElecTextDao etd = (ElecTextDao) atx.getBean(ElecTextDao.SERVICE_NAME);		
		ElecText et = new ElecText();
		Serializable id = "ff8080814a468f31014a468f33570000";
		et = etd.findObjectByID(id);
		System.out.println(et);
	}
	@Test
	public void testDeleteObjectByIDs() {
		ApplicationContext atx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ElecTextDao etd = (ElecTextDao) atx.getBean(ElecTextDao.SERVICE_NAME);		
		ElecText et = new ElecText();
		Serializable[] ids = {"ff8080814a424199014a42419ac80000","ff8080814a426688014a42668a500000"};
		etd.deleteObjectByIDs(ids);
		System.out.println(et);
	}
	@Test
	public void testDeleteObjectByCollection() {
		ApplicationContext atx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ElecTextDao etd = (ElecTextDao) atx.getBean(ElecTextDao.SERVICE_NAME);		
		List<ElecText> list = new ArrayList<ElecText>();
		ElecText et1 = new ElecText();
		et1.setTextID("4");
		ElecText et2 = new ElecText();
		et2.setTextID("3");
		list.add(et2);
		list.add(et1);
		Iterator<ElecText> iterator = list.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
		System.out.println("-------------------------");
		etd.deleteObjectByCollection(list);
 	}
}
