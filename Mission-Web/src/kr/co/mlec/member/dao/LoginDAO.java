package kr.co.mlec.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.co.mlec.member.vo.LoginVO;
import kr.co.mlec.util.ConnectionFactory;

public class LoginDAO {
	/*
	 * 로그인 서비스
	 * @param loginVO (사용자가 입력한 id,password 저장)
	 * @return userVO ( id, password로 조회된 회원정보)
	 * 조회된 정보가 없다면 null 반환 
	 * 
	 * 
	 * 
	 */

	public LoginVO login(LoginVO loginVO) {
		
		LoginVO userVO =null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("select id, name, password, type ");
		sql.append("  from tbl_member2 ");
		sql.append("where id =? and password = ? ");
		
		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());

		) // 1.7버전 :()안에 autocloaseable 변수 ->finally안하려고
		{
          pstmt.setString(1, loginVO.getId());
          pstmt.setString(2, loginVO.getPassword());
          
          ResultSet rs =pstmt.executeQuery();
          if(rs.next()) {
        	  userVO = new LoginVO();
        	  userVO.setId(rs.getString("id"));
        	  userVO.setPassword(rs.getString("password"));
        	  userVO.setType(rs.getString("type"));
          }
          
		} catch (Exception e) {
			e.printStackTrace();
		}
         return userVO;
	}

}
