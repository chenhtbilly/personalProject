package cn.syslisten.util;

import java.util.TimerTask;

import org.apache.log4j.Logger;

import cn.syslisten.pojo.MonitorInfo;
import cn.syslisten.service.MonitorInfoService;


public class SaveInfoTimerTask extends TimerTask {
	
	private Logger log = Logger.getLogger(SaveInfoTimerTask.class);
	// 性能信息业务逻辑类
	private MonitorInfoService monitorInfoService;
	
	public void setMonitorInfoService(MonitorInfoService monitorInfoService) {
		this.monitorInfoService = monitorInfoService;
	}
	
	@Override
	public void run() {
		MonitorInfo monitorInfo;
		try {
			monitorInfo = monitorInfoService.getMonitorInfo();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		monitorInfoService.save(monitorInfo);
		log.info("定时保存性能信息");
	}

}
