package com.login.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.login.common.DBConnPool;
import com.login.dto.BoardDto;

public class BoardDao extends DBConnPool{
	
	
	/**
	 * 
	 * 리스트 조회 후 반환
	 *  + 페이징 처리
	 */
	public List<BoardDto> getList(int startNum, int endNum){
		List<BoardDto> list = new ArrayList<>();
//		String sql = "select num, title, id,\r\n"
//				+ "to_char(postdate, 'yyyy-mm-dd') postdate\r\n"
//				+ "from board";
		
		String sql = "select *\r\n"
				+ "from (\r\n"
				+ "    select rownum rnum, b.*\r\n"
				+ "    from (\r\n"
				+ "        select *\r\n"
				+ "        from board\r\n"
				+ "        order by num desc\r\n"
				+ "        ) b\r\n"
				+ "    )\r\n"
				+ "where rnum between ? and ?";
		BoardDto dto = null;
		try {
			pstmt = con.prepareStatement(sql);
			
			// 시작번호 = 끝번호 - (페이지당 게시물 수 - 1)
			pstmt.setInt(1, startNum);
			// 끝번호 = 페이지번호 * 페이지당 게시물 수
			pstmt.setInt(2, endNum);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String num = rs.getString("num");
				String title = rs.getString("title");
				//String content = rs.getString("content");
				String id = rs.getString("id");
				String postdate = rs.getString("postdate");
				//String visitcount = rs.getString("visitcount");
				dto = new BoardDto(num, title, "", id, postdate, "");
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	
	
	public BoardDto getOne(String no){
		String sql = "select * from board where num = ?";
		BoardDto dto = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String num = rs.getString(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				String id = rs.getString(4);
				String postdate = rs.getString(5);
				String visitcount = rs.getString(6);
				dto = new BoardDto(num, title, content, id, postdate, visitcount);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
		
	}
	
	public int visitCnt(String no){
		int res = 0;
		String sql = "update board set visitcount = visitcount+1\r\n"
				+ "where num = ?";
		//BoardDto dto = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, no);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
		
	}
	
	public int deleteOne(String no){
		int res = 0;
		String sql = "delete from board\r\n"
				+ "where num = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, no);
			res = pstmt.executeUpdate();
			System.out.println("deleteOne에서 " + res +"건 업데이트");


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
		
	}
	

}
