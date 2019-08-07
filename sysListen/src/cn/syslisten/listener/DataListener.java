package cn.syslisten.listener;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.syslisten.util.DeleteInfoTimerTask;
import cn.syslisten.util.SaveInfoTimerTask;

/**
 * ���ݴ��������
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
		//�����ػ��߳�
		//5���ʼ��ÿ��30�뱣��һ����¼
		new Timer(true).schedule(saveInfoTimerTask, 1000*5,1000*30);
		//1���Ӻ�ʼ��ÿ��3Сʱɾ�����ڼ�¼
		new Timer(true).schedule(deleteInfoTimerTask, 1000*60,1000*60*60*3);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
