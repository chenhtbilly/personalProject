package cn.syslisten.service.impl;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import cn.syslisten.dao.MonitorInfoDao;
import cn.syslisten.pojo.MonitorInfo;
import cn.syslisten.service.MonitorInfoService;

import com.sun.management.OperatingSystemMXBean;

/**
 * 获取系统信息的业务逻辑实现类.
 * 
 */
public class MonitorInfoServiceImpl implements MonitorInfoService {

	private Logger log = Logger.getLogger(MonitorInfoServiceImpl.class);

	/**
	 * 获得当前的监控对象.
	 * 
	 * @return 返回构造好的监控对象
	 * @throws Exception
	 */
	@Override
	public MonitorInfo getMonitorInfo() throws Exception {
		int kb = 1024;

		OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory
				.getOperatingSystemMXBean();

		// 操作系统
		String osName = System.getProperty("os.name");
		// 总的物理内存
		long totalMemory = osmxb.getTotalPhysicalMemorySize() / kb;
		// 已使用的物理内存
		long usedMemory = (osmxb.getTotalPhysicalMemorySize() - osmxb
				.getFreePhysicalMemorySize()) / kb;
		int memoryPercent = (int) ((double) usedMemory / (double) totalMemory * 100);
		double cpuRatio = getCpuRatio();
		// 磁盘信息集合
		Map discMap = discInfo();
		// 磁盘总大小
		long totalDiscSize = (Long) discMap.get("totalDiscSize");
		// 磁盘剩余大小
		long freeDiscSize = (Long) discMap.get("freeDiscSize");
		// 磁盘使用率
		int discUsage = (Integer) discMap.get("discUsage");
		// 网络信息集合
		Map netMap = getNetwork();
		long rxBytes = (Long) netMap.get("rxBytes");
		long txBytes = (Long) netMap.get("txBytes");
		// 构造返回对象
		MonitorInfo monitorInfo = new MonitorInfo();
		monitorInfo.setOsName(osName);
		monitorInfo.setMemoryPercent(memoryPercent);
		monitorInfo.setTotalMemory(totalMemory);
		monitorInfo.setUsedMemory(usedMemory);
		monitorInfo.setCpuRatio(cpuRatio);
		monitorInfo.setTotalDiscSize(totalDiscSize / kb);
		monitorInfo.setFreeDiscSize(freeDiscSize / kb);
		monitorInfo.setDiscUsage(discUsage);
		monitorInfo.setRxBytes(rxBytes);
		monitorInfo.setTxBytes(txBytes);
		// monitorInfo.setSavedTime(savedTime);
		return monitorInfo;
	}

	/**
	 * 获得CPU使用率.
	 * 
	 * @return 返回cpu使用率
	 * @throws SigarException 
	 */
	private double getCpuRatio() throws SigarException {
		Sigar sigar = new Sigar();
		// CPU数量（单位：个）
		int cpuLength = sigar.getCpuInfoList().length;
		log.info("cpu个数：" + cpuLength);

		// CPU的总量（单位：HZ）及CPU的相关信息
		CpuInfo infos[] = sigar.getCpuInfoList();
		for (int i = 0; i < infos.length; i++) {// 不管是单块CPU还是多CPU都适用
			CpuInfo info = infos[i];
		}

		// CPU的用户使用量、系统使用剩余量、总的剩余量、总的使用占用量等（单位：100%） **/
		// 不管是单块CPU还是多CPU都适用
		CpuPerc cpuList[] = null;
		try {
			cpuList = sigar.getCpuPercList();
		} catch (SigarException e) {
			e.printStackTrace();
		}
		double cpuRatio = 0.0;
//		System.out.println("cpu个数："+ cpuList.length);
		for (int i = 0; i < cpuList.length; i++) {
			cpuRatio = cpuRatio+(1-cpuList[i].getIdle());
		}
		DecimalFormat df = new DecimalFormat("0.0");
		cpuRatio =Double.parseDouble(df.format(cpuRatio*100/cpuList.length));
		return cpuRatio;
	}

	/**
	 * 获取c盘信息
	 * 
	 * @return
	 */
	private Map discInfo() {
		Map discMap = new HashMap();
		String dirName = "C:/";
		File disc = new File(dirName);
		if (disc.exists()) {
			long total = (long) disc.getTotalSpace();
			long free = (long) disc.getFreeSpace();
			Double discUse = (Double) (1 - free * 1.0 / total) * 100;
			discMap.put("totalDiscSize", total);
			discMap.put("freeDiscSize", free);
			discMap.put("discUsage", discUse.intValue());
		}
		return discMap;
	}

	/**
	 * 获取所有盘的使用率
	 * 
	 * @return
	 */
	private List<String> getDiscInfo() {
		List<String> list = new ArrayList<String>();
		for (char c = 'A'; c <= 'Z'; c++) {
			String dirName = c + ":/";
			File disc = new File(dirName);
			if (disc.exists()) {
				long total = (long) disc.getTotalSpace();
				long free = (long) disc.getFreeSpace();
				Double discUse = (Double) (1 - free * 1.0 / total) * 100;
				String discUseStr = c + ":盘 已使用 " + discUse.intValue() + "%";
				list.add(discUseStr);
			}
		}
		return list;
	}

	private Map getNetwork() throws Exception {
		Map netMap = new HashMap();
		Sigar sigar = new Sigar();
		Long rxBytes = 0L;
		Long txBytes = 0L;
		String ifNames[] = sigar.getNetInterfaceList();
		for (int i = 0; i < ifNames.length; i++) {
			String name = ifNames[i];
			NetInterfaceConfig ifconfig = sigar.getNetInterfaceConfig(name);
			if ((ifconfig.getFlags() & 1L) <= 0L) {
				// System.out.println("!IFF_UP...skipping getNetInterfaceStat");
				continue;
			}
			try {
				NetInterfaceStat ifstat = sigar.getNetInterfaceStat(name);
				if (ifstat.getRxBytes() > 0L) {
					rxBytes = ifstat.getRxBytes();
				}
				if (ifstat.getTxBytes() > 0L) {
					txBytes = ifstat.getTxBytes();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		netMap.put("rxBytes", rxBytes);
		netMap.put("txBytes", txBytes);
		return netMap;
	}

	private MonitorInfoDao monitorInfoDao;

	public void setMonitorInfoDao(MonitorInfoDao monitorInfoDao) {
		this.monitorInfoDao = monitorInfoDao;
	}

	@Override
	public void save(MonitorInfo monitorInfo) {
		monitorInfoDao.save(monitorInfo);
	}

	@Override
	public int getMaxId() {
		int maxId = monitorInfoDao.getMaxId();
		return maxId;
	}

	@Override
	public List<MonitorInfo> query() {
		return monitorInfoDao.query();
	}

	// 删除id小于某个值的记录
	@Override
	public void deleteById(int deleteId) {
		monitorInfoDao.deleteById(deleteId);
	}
}
