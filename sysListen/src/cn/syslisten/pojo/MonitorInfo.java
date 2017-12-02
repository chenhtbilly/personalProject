package cn.syslisten.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * ������ϢJavaBean.
 */
public class MonitorInfo implements Serializable {

	private Integer id;
	
	/** ����ϵͳ��. */
	private String osName;

	/** ϵͳ�ڴ�ʹ����. */
	private int memoryPercent;
	
	/** �ܵ������ڴ�. */
	private long totalMemory;

	/** ��ʹ�õ������ڴ�. */
	private long usedMemory;

	/** cpuʹ����. */
	private double cpuRatio;

	/** Ӳ���ܿռ� */
	private long totalDiscSize;

	/** Ӳ�̿��пռ� */
	private long freeDiscSize;

	/** Ӳ��ʹ���� */
	private int discUsage;
	
	/**���յ������ֽ��� */
	private long rxBytes;
	
	/**���͵����ֽ���*/
	private long txBytes;
	
	/** �������������Ϣ��ʱ��*/
	private Timestamp savedTime;
	
	public MonitorInfo() {
	}
	
	public MonitorInfo(Timestamp savedTime) {
		this.savedTime = savedTime;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public int getMemoryPercent() {
		return memoryPercent;
	}

	public void setMemoryPercent(int memoryPercent) {
		this.memoryPercent = memoryPercent;
	}

	public long getTotalMemory() {
		return totalMemory;
	}

	public void setTotalMemory(long totalMemory) {
		this.totalMemory = totalMemory;
	}

	public long getUsedMemory() {
		return usedMemory;
	}

	public void setUsedMemory(long usedMemory) {
		this.usedMemory = usedMemory;
	}

	public double getCpuRatio() {
		return cpuRatio;
	}

	public void setCpuRatio(double cpuRatio) {
		this.cpuRatio = cpuRatio;
	}

	public long getTotalDiscSize() {
		return totalDiscSize;
	}

	public void setTotalDiscSize(long totalDiscSize) {
		this.totalDiscSize = totalDiscSize;
	}

	public long getFreeDiscSize() {
		return freeDiscSize;
	}

	public void setFreeDiscSize(long freeDiscSize) {
		this.freeDiscSize = freeDiscSize;
	}

	public int getDiscUsage() {
		return discUsage;
	}

	public void setDiscUsage(int discUsage) {
		this.discUsage = discUsage;
	}

	public long getRxBytes() {
		return rxBytes;
	}

	public void setRxBytes(long rxBytes) {
		this.rxBytes = rxBytes;
	}

	public long getTxBytes() {
		return txBytes;
	}

	public void setTxBytes(long txBytes) {
		this.txBytes = txBytes;
	}
	
	public Timestamp getSavedTime() {
		return savedTime;
	}
	
	public void setSavedTime(Timestamp savedTime) {
		this.savedTime = savedTime;
	}
}
