package cn.syslisten.util;

import java.util.TimerTask;

import org.apache.log4j.Logger;

import cn.syslisten.service.MonitorInfoService;


public class DeleteInfoTimerTask extends TimerTask {
	
	private Logger log = Logger.getLogger(DeleteInfoTimerTask.class);
	// 性能信息业务逻辑类
	private MonitorInfoService monitorInfoService;
	
	public void setMonitorInfoService(MonitorInfoService monitorInfoService) {
		this.monitorInfoService = monitorInfoService;
	}
	
	@Override
	public void run() {
		int deleteId = monitorInfoService.getMaxId()-540;
		if(deleteId>0){
			monitorInfoService.deleteById(deleteId);
			log.info("删除过期信息成功");
		}
		log.info("定时删除过期信息任务");
	}

}
