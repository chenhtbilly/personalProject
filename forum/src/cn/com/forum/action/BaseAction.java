package cn.com.forum.action;

import java.util.Map;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import cn.com.forum.service.ReplyService;
import cn.com.forum.service.ThemeService;
import cn.com.forum.service.impl.ReplyServiceImpl;
import cn.com.forum.service.impl.ThemeServiceImpl;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements RequestAware,SessionAware,ApplicationAware {
	protected Map<String, Object> application;

	protected Map<String, Object> session;

	protected Map<String, Object> request;
	
	protected ThemeService themeService = new ThemeServiceImpl();
	protected ReplyService replyService = new ReplyServiceImpl();
	
	
	@Override
	public void setApplication(Map<String, Object> application) {
		this.application = application;
		
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	
}
