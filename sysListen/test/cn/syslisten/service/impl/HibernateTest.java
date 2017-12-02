package cn.syslisten.service.impl;

import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class HibernateTest {
	@Test
	public void test01(){
		new Configuration().configure().buildSessionFactory();
	}
}
