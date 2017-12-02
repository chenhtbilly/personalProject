package cn.syslisten.dao;

import java.util.List;

import cn.syslisten.pojo.MonitorInfo;

/**
 * 性能信息Dao接口 
 */
public interface MonitorInfoDao {

	public void save(MonitorInfo monitorInfo);

	public int getMaxId();

	public List<MonitorInfo> query();

	public void deleteById(int deleteId);
	
}
