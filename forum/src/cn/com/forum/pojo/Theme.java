package cn.com.forum.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Theme implements Serializable {
	private Long id;
	private String title;
	private String content;
	private String ip;
	private Date ptime;
	private Date lastptime;
	public Date getLastptime() {
		return lastptime;
	}
	public void setLastptime(Date lastptime) {
		this.lastptime = lastptime;
	}
	private Set<Reply> replies = new HashSet<Reply>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public Set<Reply> getReplies() {
		return replies;
	}
	public void setReplies(Set<Reply> replies) {
		this.replies = replies;
	}
	public Date getPtime() {
		return ptime;
	}
	public void setPtime(Date ptime) {
		this.ptime = ptime;
	}
	
	@Override
	public String toString() {
		return "Theme [id=" + id + ", title=" + title + ", content=" + content
				+ ", ip=" + ip + ", ptime=" + ptime + ", lastptime="
				+ lastptime + ", replies=" + replies + "]";
	}
	
}
