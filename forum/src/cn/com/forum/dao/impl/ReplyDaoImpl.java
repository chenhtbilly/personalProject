package cn.com.forum.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.com.forum.dao.ReplyDao;
import cn.com.forum.pojo.Reply;
import cn.com.forum.util.HibernateSessionFactory;

public class ReplyDaoImpl implements ReplyDao {
	
	@Override
	public void save(Reply reply) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			session.save(reply);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			throw new RuntimeException(e);
		}finally{
			if(session!=null){
				session.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reply> query() {
		Session session = HibernateSessionFactory.getSession();
		String hql = "FROM Reply";
		List<Reply> replylist = session.createQuery(hql).list();
		if(session!=null){
			session.close();
		}
		return replylist;
		 
	}
}
