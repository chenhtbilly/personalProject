import java.util.Date;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import cn.com.forum.util.HibernateSessionFactory;


public class TestHibernate {
		
	public static void main(String[] args) {
		System.out.println(new Date());
	}
	
	@Test
	public void test(){
		//创建表结构
		new Configuration().configure().buildSessionFactory();
		Session session = HibernateSessionFactory.getSession();
		System.out.println(session);
	}
}
