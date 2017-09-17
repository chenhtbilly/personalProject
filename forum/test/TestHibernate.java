import java.util.Date;

import org.hibernate.Session;
import org.junit.Test;

import cn.com.forum.util.HibernateSessionFactory;


public class TestHibernate {
		
	public static void main(String[] args) {
		System.out.println(new Date());
	}
	
	@Test
	public void test(){
		//new Configuration().configure().buildSessionFactory();
		Session session = HibernateSessionFactory.getSession();
		System.out.println(session);
	}
}
