package cn.syslisten.util;

import java.util.TimerTask;

import org.apache.log4j.Logger;

import cn.syslisten.service.MonitorInfoService;


public class DeleteInfoTimerTask extends TimerTask {
	
	private Logger log = Logger.getLogger(DeleteInfoTimerTask.class);
	// ������Ϣҵ���߼���
	private MonitorInfoService monitorInfoService;
	
	public void setMonitorInfoService(MonitorInfoService monitorInfoService) {
		this.monitorInfoService = monitorInfoService;
	}
	
	@Override
	public void run() {
		int deleteId = monitorInfoService.getMaxId()-540;
		if(deleteId>0){
			monitorInfoService.deleteById(deleteId);
			log.info("ɾ��������Ϣ�ɹ�");
		}
		log.info("��ʱɾ��������Ϣ����");
	}

}
