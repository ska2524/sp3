package org.zerock.aop.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements MemberDAO{
	
	@Inject
	SqlSession session;
	
	private static final String namespace ="org.zerock.test";
	
	
	@Override
	public void insertMember() {
		
		Map<String, String> memberMap = new HashMap<>();
		
		memberMap.put("uid", "z2");
		memberMap.put("upw", "z2");
		memberMap.put("uname", "z사용자 2");
	
		session.insert(namespace+".insertMember",memberMap);  //마이타비스에서는 이게 단일 트랜잭션 입니ㅣ다 
		
	}

	@Override
	public void insertRole() {
		
		Map<String, String> roleMap = new HashMap<>();
		
		roleMap.put("rname", "Admin");
		roleMap.put("uid", "z2"); //프라이머리키 외래키 참조 무결성 위반 z1..zz1
		
		session.insert(namespace+".insertRole",roleMap);
		
	}
	
	
}
