package cn.syslisten.service.impl;

import org.junit.Test;

import cn.syslisten.pojo.MonitorInfo;

public class MonitorInfoServiceImplTest {
	/**
	 * ���Է���.
	 * @param args
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {
		MonitorInfoServiceImpl service = new MonitorInfoServiceImpl();
		MonitorInfo monitorInfo = service.getMonitorInfo();
		System.out.println("cpuռ����=" + monitorInfo.getCpuRatio());

		System.out.println("����ϵͳ=" + monitorInfo.getOsName());
		System.out.println("�ܵ������ڴ�=" + monitorInfo.getTotalMemory() + "kb");
		System.out.println("��ʹ�õ������ڴ�=" + monitorInfo.getUsedMemory() + "kb");
		System.out.println("ϵͳ�ڴ�ʹ����=" + monitorInfo.getMemoryPercent() + "%");
		System.out.println("�����ܿռ�=" + monitorInfo.getTotalDiscSize() + "kb");
		System.out.println("����ʣ��ռ�=" + monitorInfo.getFreeDiscSize() + "kb");
		System.out.println("����ʹ����=" + monitorInfo.getDiscUsage() + "%");
		System.out.println("�����ֽ���=" + monitorInfo.getRxBytes() + "b");
		System.out.println("�����ֽ���=" + monitorInfo.getTxBytes() + "b");
	}

}
