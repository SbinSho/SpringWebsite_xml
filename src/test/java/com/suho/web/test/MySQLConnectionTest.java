package com.suho.web.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;


public class MySQLConnectionTest {

	// DB연결정보
	// 드라이버, URL, ID, PW
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/spring_web?serverTimezone=Asia/Seoul";
	private static final String DBID = "spring";
	private static final String DBPW = "spring";
	
	
	@Test
	public void DB_연결테스트() throws Exception {
		try ( Connection con = DriverManager.getConnection(DBURL, DBID, DBPW); ) {
			
			System.out.println("디비 연결 객체 : " + con);
			
		} catch ( SQLException e ) {
			
			System.out.println("디비 연결 실패 : " + e.getMessage());
			
		}

	}
	

}
