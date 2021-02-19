package com.suho.web.test;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= "file:src/main/webapp/WEB-INF/spring/root-context.xml" )
public class MyBatisTest {

	@Autowired
	private SqlSessionFactory sqlFactroy;
	
	@Test
	public void testSqlSessionFactory() throws Exception {
		
		// try-with 구문 사용
		try ( SqlSession session = sqlFactroy.openSession() ) {
			
			System.out.println(" sqlFactroy 사용 디비 연결");
			System.out.println(" @@@@ 연결된 객체 정보 : " + session );
			
		} catch (Exception e) {

			System.out.println(" testSqlSessionFactory 테스트 실패 " + e.getMessage());
		}
	}
	

}
