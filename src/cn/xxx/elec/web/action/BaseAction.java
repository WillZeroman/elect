package cn.xxx.elec.web.action;

import java.util.Map;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class BaseAction extends ActionSupport implements RequestAware,ApplicationAware,SessionAware{

	/**
	 * 继承ActionSupport，可供其他antion继承，
	 * 实现了三个接口，能实现web元素的取值
	 
	 */
	
	protected Map<String,Object> request = null;
	protected Map<String,Object> application = null;
	protected Map<String,Object> session = null;
	
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;	
	}

	@Override
	public void setApplication(Map<String, Object> application) {
		this.application = application;		
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;	
	}

}
