package cn.com.forum.pojo;

import java.io.Serializable;
import java.util.Date;

public class Reply implements Serializable{
	private Long id;
	private String content;
	private String ip;
	private Date rtime;
	private Theme theme;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getRtime() {
		return rtime;
	}
	public void setRtime(Date rtime) {
		this.rtime = rtime;
	}
	public Theme getTheme() {
		return theme;
	}
	public void setTheme(Theme theme) {
		this.theme = theme;
	}
	
	@Override
	public String toString() {
		return "Reply [id=" + id + ", content=" + content + ", ip=" + ip
				+ ", rtime=" + rtime +"]";
	}
	
}
