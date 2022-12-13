package com.greenart.jpa_test.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.jpa_test.member.entity.MemberInfoVO;

@Repository
                                        //JpaRepository : sql 자동생성
public interface MemberRepository extends JpaRepository<MemberInfoVO, Long> { //Long : 기본키 타입은 정수다(Long을 권장)
    // 아래코드는 select count(*) from member_info where mi_id = ""; - 자동생성
    public Long countById(String id);


    // 아래코드는 select * from member_info where mi_id = "" and mi_pwd = ""; 와 같음
    public MemberInfoVO findByIdAndPwd(String id, String pwd); // 갯수가 0 아니면 1로 나옴
}
