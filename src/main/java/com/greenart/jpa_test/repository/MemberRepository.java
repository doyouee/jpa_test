package com.greenart.jpa_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.jpa_test.entity.MemberInfoVO;

@Repository
public interface MemberRepository extends JpaRepository<MemberInfoVO, Long> { //Long : 기본키 타입은 정수다(Long을 권장)
    MemberInfoVO findById(String id); 
}
