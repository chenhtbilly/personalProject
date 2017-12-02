package cn.syslisten.dao;

import java.util.List;

import cn.syslisten.pojo.MonitorInfo;

/**
 * ������ϢDao�ӿ� 
 */
public interface MonitorInfoDao {

	public void save(MonitorInfo monitorInfo);

	public int getMaxId();

	public List<MonitorInfo> query();

	public void deleteById(int deleteId);
	
}
