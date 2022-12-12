package com.greenart.jpa_test;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.greenart.jpa_test.entity.MemberInfoVO;
import com.greenart.jpa_test.repository.MemberRepository;

@SpringBootTest
class JpaTestApplicationTests {
	@Autowired MemberRepository repo; //Autowired : DI(Dependency Injection) _멤버 의존성 주입 _MemberRepository에 bin을 찾아서 리포지토리에 주입해라
	@Test
	@Transactional // 아래 코드에서 수행한 insert를 수행 이전으로 되돌린다. 한번 실행하고나서 실행한 데이터를 삭제한다. (insert가 제대로 되는지만을 확인하겠다.)
	public void testMemberAdd(){
		MemberInfoVO data = new MemberInfoVO();
		data.setId("userId");
		data.setPwd("1234");
		data.setName("사용자");
		data.setNickname("닉네임");
		data.setRegDt(new Date());
		data.setStatus(1);
		repo.save(data);
	}

	@Test
	public void testSelectMember(){
		MemberInfoVO data = repo.findById("userid1");
		// List<MemberInfoVO> list = repo.findAll(); // findAll : select * from 과 같음
		// for(MemberInfoVO m : list){
		// 	System.out.println(m);
		// }		
	}
}
