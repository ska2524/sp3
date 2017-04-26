package org.zerock.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.aop.persistence.MemberDAO;

@Service //target이다.(타켓의 메소드 - joinpoint (후보들 ) - 이중에 찍히는 것이 포인트 컷이다.)
//우리가 신경쓸 것은 트랜잭션이다.
public class SampleServiceImpl implements SampleService {
	
	@Inject
	private MemberDAO dao;
	
	
	/* (non-Javadoc)
	 * @see org.zerock.service.SampleService#doA()
	 */
	@Override
	public void doA(){
		System.out.println("doA.............................");
		System.out.println("doA.............................");
		System.out.println("doA.............................");
		System.out.println("doA.............................");
		System.out.println("doA.............................");
		System.out.println("doA.............................");
		
	}

	@Transactional //transaction ..
	@Override
	public void doB() {
		System.out.println("doB.............................");	
		
		dao.insertMember();
		dao.insertRole();
		
		System.out.println("doB.............................");		

	}

	@Override
	public void doC() {

		System.out.println("doC.............................");	
		
		System.out.println("doC.............................");		


	}
	
	
	
	
}
