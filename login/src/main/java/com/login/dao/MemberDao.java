package com.login.dao;

import java.sql.SQLException;

import com.login.common.DBConnPool;
import com.login.dto.MemberDto;

public class MemberDao extends DBConnPool{
	
	/**
	 * id, pw를 전달받아 DB에 등록된 사용자가 있는지 조회 후 MemberDto를 반환
	 * 
	 */
	
	public MemberDto login(String id, String pw) {

		MemberDto dto = null;
		String sql = "select * from member where id=? and pass=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				//String id = dto.setId(rs.getString("id"));
				String pass = rs.getString("pass");
				String regidate = rs.getString("regidate");
				//로그인 성공시 memberDto객체를 생성 후 반환
				dto = new MemberDto(id, pass, regidate);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dto;
		
		
		
	}

}
