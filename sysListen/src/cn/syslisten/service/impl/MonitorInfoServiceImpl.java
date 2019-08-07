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
 * ��ȡϵͳ��Ϣ��ҵ���߼�ʵ����.
 * 
 */
public class MonitorInfoServiceImpl implements MonitorInfoService {

	private Logger log = Logger.getLogger(MonitorInfoServiceImpl.class);

	/**
	 * ��õ�ǰ�ļ�ض���.
	 * 
	 * @return ���ع���õļ�ض���
	 * @throws Exception
	 */
	@Override
	public MonitorInfo getMonitorInfo() throws Exception {
		int kb = 1024;

		OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory
				.getOperatingSystemMXBean();

		// ����ϵͳ
		String osName = System.getProperty("os.name");
		// �ܵ������ڴ�
		long totalMemory = osmxb.getTotalPhysicalMemorySize() / kb;
		// ��ʹ�õ������ڴ�
		long usedMemory = (osmxb.getTotalPhysicalMemorySize() - osmxb
				.getFreePhysicalMemorySize()) / kb;
		int memoryPercent = (int) ((double) usedMemory / (double) totalMemory * 100);
		double cpuRatio = getCpuRatio();
		// ������Ϣ����
		Map discMap = discInfo();
		// �����ܴ�С
		long totalDiscSize = (Long) discMap.get("totalDiscSize");
		// ����ʣ���С
		long freeDiscSize = (Long) discMap.get("freeDiscSize");
		// ����ʹ����
		int discUsage = (Integer) discMap.get("discUsage");
		// ������Ϣ����
		Map netMap = getNetwork();
		long rxBytes = (Long) netMap.get("rxBytes");
		long txBytes = (Long) netMap.get("txBytes");
		// ���췵�ض���
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
	 * ���CPUʹ����.
	 * 
	 * @return ����cpuʹ����
	 * @throws SigarException 
	 */
	private double getCpuRatio() throws SigarException {
		Sigar sigar = new Sigar();
		// CPU��������λ������
		int cpuLength = sigar.getCpuInfoList().length;
		log.info("cpu������" + cpuLength);

		// CPU����������λ��HZ����CPU�������Ϣ
		CpuInfo infos[] = sigar.getCpuInfoList();
		for (int i = 0; i < infos.length; i++) {// �����ǵ���CPU���Ƕ�CPU������
			CpuInfo info = infos[i];
		}

		// CPU���û�ʹ������ϵͳʹ��ʣ�������ܵ�ʣ�������ܵ�ʹ��ռ�����ȣ���λ��100%�� **/
		// �����ǵ���CPU���Ƕ�CPU������
		CpuPerc cpuList[] = null;
		try {
			cpuList = sigar.getCpuPercList();
		} catch (SigarException e) {
			e.printStackTrace();
		}
		double cpuRatio = 0.0;
//		System.out.println("cpu������"+ cpuList.length);
		for (int i = 0; i < cpuList.length; i++) {
			cpuRatio = cpuRatio+(1-cpuList[i].getIdle());
		}
		DecimalFormat df = new DecimalFormat("0.0");
		cpuRatio =Double.parseDouble(df.format(cpuRatio*100/cpuList.length));
		return cpuRatio;
	}

	/**
	 * ��ȡc����Ϣ
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
	 * ��ȡ�����̵�ʹ����
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
				String discUseStr = c + ":�� ��ʹ�� " + discUse.intValue() + "%";
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

	// ɾ��idС��ĳ��ֵ�ļ�¼
	@Override
	public void deleteById(int deleteId) {
		monitorInfoDao.deleteById(deleteId);
	}
}
