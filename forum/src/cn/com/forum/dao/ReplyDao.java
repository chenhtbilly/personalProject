package cn.com.forum.dao;

import java.util.List;

import cn.com.forum.pojo.Reply;

public interface ReplyDao {

	public void save(Reply reply);

	public List<Reply> query();

}
