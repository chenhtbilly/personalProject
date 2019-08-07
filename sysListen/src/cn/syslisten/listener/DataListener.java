package cn.syslisten.listener;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.syslisten.util.DeleteInfoTimerTask;
import cn.syslisten.util.SaveInfoTimerTask;

/**
 * 数据处理监听器
 *
 */
public class DataListener implements ServletContextListener {

	private SaveInfoTimerTask saveInfoTimerTask;
	private DeleteInfoTimerTask deleteInfoTimerTask;
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		ApplicationContext context=WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		saveInfoTimerTask=(SaveInfoTimerTask) context.getBean("saveInfoTimerTask");
		deleteInfoTimerTask=(DeleteInfoTimerTask) context.getBean("deleteInfoTimerTask");
		//两个守护线程
		//5秒后开始，每隔30秒保存一条记录
		new Timer(true).schedule(saveInfoTimerTask, 1000*5,1000*30);
		//1分钟后开始，每隔3小时删除过期记录
		new Timer(true).schedule(deleteInfoTimerTask, 1000*60,1000*60*60*3);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
