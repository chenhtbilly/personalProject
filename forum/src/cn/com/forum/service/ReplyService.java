package cn.com.forum.service;

import java.util.List;

import cn.com.forum.pojo.Reply;

public interface ReplyService {

	public void save(Reply reply);

	public List<Reply> query();

}
