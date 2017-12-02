package cn.syslisten.dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import cn.syslisten.dao.MonitorInfoDao;
import cn.syslisten.pojo.MonitorInfo;

/**
 * 性能信息Dao实现类
 */
@SuppressWarnings("unchecked")
public class MonitorInfoDaoImpl implements MonitorInfoDao {
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void save(MonitorInfo monitorInfo) {
		Session session = sessionFactory.getCurrentSession();
		System.out.println("------->save");
		session.save(monitorInfo);
	}

	@Override
	public int getMaxId() {
		String hql = "SELECT MAX(m.id) FROM MonitorInfo m";
		Session session = sessionFactory.getCurrentSession();
		int result = (Integer) session.createQuery(hql).uniqueResult();
		return result;
	}

	@Override
	public List<MonitorInfo> query() {
		String hql = "FROM MonitorInfo m  ORDER BY m.savedTime DESC";
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery(hql)//
				.setFirstResult(0) //
				.setMaxResults(90)//
				.list();
	}

	@Override
	public void deleteById(int deleteId) {
		String hql = "DELETE FROM MonitorInfo m  WHERE m.id<:deleteId";
		Session session = sessionFactory.getCurrentSession();
		session.createQuery(hql)//
				.setInteger("deleteId", deleteId)//
				.executeUpdate();
	}	
	
}
