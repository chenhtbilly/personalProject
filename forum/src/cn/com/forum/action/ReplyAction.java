package cn.com.forum.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;

import cn.com.forum.pojo.Reply;
import cn.com.forum.pojo.Theme;

import com.opensymphony.xwork2.ModelDriven;

public class ReplyAction extends BaseAction implements ModelDriven<Reply>{
	
	private Long tid;
	
	public void setTid(String tid) {
		this.tid = Long.parseLong(tid);
	}
	private Reply model;
	
	@Override
	public Reply getModel() {
		model = new Reply();
		return model;
	}
	//回帖，设置关联的主题帖，并更新主题帖的最后更新时间
	public String save(){
		Theme theme = themeService.get(tid);
		//
		System.out.println("添加前：" + theme);
		String ip = ServletActionContext.getRequest().getRemoteAddr();
		System.out.println(ip);
		model.setIp(ip);
		model.setRtime(new Date());
		model.setTheme(theme);
		System.out.println("回帖：" + model);
		replyService.save(model);
		theme.setLastptime(model.getRtime());
		themeService.update(theme);
		theme = themeService.get(tid);
		System.out.println("添加后：" + theme);
		request.put("theme", theme);
		return "reply";
	}
	
}
