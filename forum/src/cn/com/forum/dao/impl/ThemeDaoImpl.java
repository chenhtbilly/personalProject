package cn.com.forum.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.com.forum.dao.ThemeDao;
import cn.com.forum.pojo.Theme;
import cn.com.forum.util.HibernateSessionFactory;

public class ThemeDaoImpl implements ThemeDao {
	
	@Override
	public void save(Theme theme) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			session.save(theme);
			transaction.commit();
		} catch (Exception e) {
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
	public List<Theme> query() {
		Session session = HibernateSessionFactory.getSession();
		String hql = "SELECT DISTINCT t FROM Theme t LEFT JOIN FETCH t.replies ORDER BY t.lastptime DESC";
		List<Theme> themeList = session.createQuery(hql).list();
		if(session!=null){
			session.close();
		}
		return themeList;
	}

	@Override
	public Theme get(Long id) {
		Session session = HibernateSessionFactory.getSession();
		String hql = "FROM Theme t LEFT JOIN FETCH t.replies where t.id=:id";
		Theme theme = (Theme) session.createQuery(hql)//
				.setLong("id", id)//
				.uniqueResult();
		if(session!=null){
			session.close();
		}
		return theme;
	}

	@Override
	public void update(Theme theme) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			session.update(theme);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new RuntimeException(e);
		}finally{
			if(session!=null){
				session.close();
			}
		}
	}

}
