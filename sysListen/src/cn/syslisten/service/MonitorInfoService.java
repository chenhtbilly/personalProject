package cn.syslisten.service;

import java.util.List;

import cn.syslisten.pojo.MonitorInfo;

/**
 * 获取系统信息的接口.
 */
public interface MonitorInfoService {

	/**
	 * 获得当前的监控对象.
	 * 
	 * @return 返回构造好的监控对象
	 * @throws Exception
	 */
	public MonitorInfo getMonitorInfo() throws Exception;

	public void save(MonitorInfo monitorInfo);
	
	public int getMaxId();
	
	public List<MonitorInfo> query();

	public void deleteById(int deleteId);

}
