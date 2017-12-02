package cn.syslisten.service.impl;

import org.junit.Test;

import cn.syslisten.pojo.MonitorInfo;

public class MonitorInfoServiceImplTest {
	/**
	 * 测试方法.
	 * @param args
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {
		MonitorInfoServiceImpl service = new MonitorInfoServiceImpl();
		MonitorInfo monitorInfo = service.getMonitorInfo();
		System.out.println("cpu占有率=" + monitorInfo.getCpuRatio());

		System.out.println("操作系统=" + monitorInfo.getOsName());
		System.out.println("总的物理内存=" + monitorInfo.getTotalMemory() + "kb");
		System.out.println("已使用的物理内存=" + monitorInfo.getUsedMemory() + "kb");
		System.out.println("系统内存使用率=" + monitorInfo.getMemoryPercent() + "%");
		System.out.println("磁盘总空间=" + monitorInfo.getTotalDiscSize() + "kb");
		System.out.println("磁盘剩余空间=" + monitorInfo.getFreeDiscSize() + "kb");
		System.out.println("磁盘使用率=" + monitorInfo.getDiscUsage() + "%");
		System.out.println("接收字节数=" + monitorInfo.getRxBytes() + "b");
		System.out.println("发送字节数=" + monitorInfo.getTxBytes() + "b");
	}

}
