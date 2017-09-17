package cn.com.forum.service.impl;

import java.util.List;

import cn.com.forum.dao.ReplyDao;
import cn.com.forum.dao.impl.ReplyDaoImpl;
import cn.com.forum.pojo.Reply;
import cn.com.forum.service.ReplyService;

public class ReplyServiceImpl implements ReplyService {

	private ReplyDao replyDao = new ReplyDaoImpl();
	@Override
	public void save(Reply reply) {
		replyDao.save(reply);
		
	}
	@Override
	public List<Reply> query() {
		List<Reply> replyList = replyDao.query();
		return replyList;
	}

}
