package com.login.dao;

import java.sql.SQLException;

import com.login.common.DBConnPool;

public class HelloDao extends DBConnPool{
	
	/**
	 *  DB로부터 현재 시간 조회 후 반환
	 */
	public String getTime() {
		String time = "";
		String sql = "select to_char(sysdate, 'hh:mm:ss') from dual";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				time = rs.getString(1);
			}
		} catch (SQLException e) {
			System.out.println("getTime() - 오류 발생");
			e.printStackTrace();

		
		}
		return time;
	}
	
}
