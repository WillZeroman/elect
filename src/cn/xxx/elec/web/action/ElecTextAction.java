package cn.xxx.elec.web.action;


import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.xxx.elec.domain.ElecText;
import cn.xxx.elec.service.ElecTextService;
import cn.xxx.elec.util.StringHelper;
import cn.xxx.elec.web.vo.ElecTextForm;

import com.opensymphony.xwork2.ModelDriven;


@Component("elecTextAction_*")
@SuppressWarnings("serial")
public class ElecTextAction extends BaseAction implements ModelDriven<ElecTextForm>{
	
	private ElecTextService ets;
	private ElecTextForm elecTextForm = new ElecTextForm();
	
	public ElecTextForm getElecTextForm() {
		return elecTextForm;
	}

	public void setElecTextForm(ElecTextForm elecTextForm) {
		this.elecTextForm = elecTextForm;
	}

	public ElecTextService getEts() {
		return ets;
	}

	@Resource(name=ElecTextService.SERVICE_NAME)
	public void setEts(ElecTextService ets) {
		this.ets = ets;
	}



	public String save(){
	//	System.out.println("elec text action--------");
		ElecText et = new ElecText();
		et.setTextName(elecTextForm.getTextName());
		et.setTextDate(StringHelper.stringConvertDate(elecTextForm.getTextDate()));
		et.setTextRemark(elecTextForm.getTextRemark());
		this.ets.saveElecText(et);
		return "save";
	}


	@Override
	public ElecTextForm getModel() {
		return this.elecTextForm;
	}
}
