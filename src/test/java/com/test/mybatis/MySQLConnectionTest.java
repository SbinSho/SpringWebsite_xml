package com.test.mybatis;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= "file:src/main/webapp/WEB-INF/spring/root-context.xml" )
public class MySQLConnectionTest {

	@Autowired
	private DataSource ds;
	
	@Test
	public void testConnection() throws Exception {
		
		try ( Connection con = ds.getConnection() ) {
			System.out.println("db 연결 성공: + " + con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
