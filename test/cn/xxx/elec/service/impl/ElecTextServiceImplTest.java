package cn.xxx.elec.service.impl;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.xxx.elec.dao.ElecTextDao;
import cn.xxx.elec.domain.ElecText;
import cn.xxx.elec.service.ElecTextService;
import cn.xxx.elec.web.vo.ElecTextForm;

public class ElecTextServiceImplTest {

	@Test
	public void testBeans() {
		ApplicationContext atx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ElecTextService ets = (ElecTextService)atx.getBean(ElecTextService.SERVICE_NAME);
		System.out.println(ets);
		ElecTextDao etd = (ElecTextDao)atx.getBean(ElecTextDao.SERVICE_NAME);
		System.out.println(etd);
	}
	
	@Test
	public void testSave(){
		ApplicationContext atx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ElecTextService ets = (ElecTextService)atx.getBean(ElecTextService.SERVICE_NAME);
		System.out.println(ets);
		ElecText et = new ElecText();
		et.setTextName("≤‚ ‘sevice");
		et.setTextDate(new Date());
		et.setTextRemark("≤‚ ‘service±∏◊¢");
		ets.saveElecText(et);
	}
	
	@Test
	public void testFindCollection(){
		ApplicationContext atx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ElecTextService ets = (ElecTextService)atx.getBean(ElecTextService.SERVICE_NAME);
		System.out.println(ets);
		ElecTextForm et = new ElecTextForm();
		et.setTextName("≤‚ ‘");
		et.setTextRemark("service");
		List<ElecText> list = ets.findCollectionByNoPage(et);
		System.out.println("----------------");
		for(int i=0;list!=null && i<list.size();i++){
			System.out.println(list.get(i));
		}
	}

}
