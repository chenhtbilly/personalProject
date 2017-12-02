package cn.syslisten.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 监视信息JavaBean.
 */
public class MonitorInfo implements Serializable {

	private Integer id;
	
	/** 操作系统名. */
	private String osName;

	/** 系统内存使用率. */
	private int memoryPercent;
	
	/** 总的物理内存. */
	private long totalMemory;

	/** 已使用的物理内存. */
	private long usedMemory;

	/** cpu使用率. */
	private double cpuRatio;

	/** 硬盘总空间 */
	private long totalDiscSize;

	/** 硬盘空闲空间 */
	private long freeDiscSize;

	/** 硬盘使用率 */
	private int discUsage;
	
	/**接收到的总字节数 */
	private long rxBytes;
	
	/**发送的总字节数*/
	private long txBytes;
	
	/** 保存监视性能信息的时间*/
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
