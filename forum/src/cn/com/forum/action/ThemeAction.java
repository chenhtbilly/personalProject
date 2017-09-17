package cn.com.forum.action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import cn.com.forum.pojo.Theme;

import com.opensymphony.xwork2.ModelDriven;

public class ThemeAction extends BaseAction implements ModelDriven<Theme>{
	
	private Theme model;
	
	@Override
	public Theme getModel() {
		model = new Theme();
		return model;
	}
	//查询所以主题帖
	public String query(){
		List<Theme> themeList = themeService.query();
		request.put("themeSize", themeList.size());
		request.put("themeList", themeList);
		return "theme";
	}
	
	//保存一个主题帖
	public String save(){
		String ip = ServletActionContext.getRequest().getRemoteAddr();
		System.out.println(ip);
		model.setIp(ip);
		Date date = new Date();
		model.setPtime(date);
		model.setLastptime(date);
		System.out.println(model);
		themeService.save(model);
		List<Theme> themeList = themeService.query();
		//System.out.println("themeList:" + themeList);
		//存储主题帖个数和主题帖集合到request域
		request.put("themeSize", themeList.size());
		request.put("themeList", themeList);
		return "theme";
	}
	
	//根据id获取主题帖
	public String get(){
		
		Theme theme = themeService.get(model.getId());
		/**
		 * 注：此处打印时两个类的toString方法不能相互打印对方，否则抛出
		 * java.lang.reflect.InvocationTargetException，java.lang.StackOverflowError 错误
		 * 原因是陷入了无休止的递归调用之中
		 */
		System.out.println(theme);
		System.out.println("theme.getReplies().size():" + theme.getReplies().size());
		
		request.put("theme", theme);
		return "reply";
	}
}
