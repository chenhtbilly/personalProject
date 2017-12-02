package cn.syslisten.service;

import java.util.List;

import cn.syslisten.pojo.MonitorInfo;

/**
 * ��ȡϵͳ��Ϣ�Ľӿ�.
 */
public interface MonitorInfoService {

	/**
	 * ��õ�ǰ�ļ�ض���.
	 * 
	 * @return ���ع���õļ�ض���
	 * @throws Exception
	 */
	public MonitorInfo getMonitorInfo() throws Exception;

	public void save(MonitorInfo monitorInfo);
	
	public int getMaxId();
	
	public List<MonitorInfo> query();

	public void deleteById(int deleteId);

}
