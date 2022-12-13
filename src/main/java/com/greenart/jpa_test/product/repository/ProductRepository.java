package com.greenart.jpa_test.product.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.jpa_test.product.entity.ProductInfoVO;

@Repository
public interface ProductRepository extends JpaRepository<ProductInfoVO, Long> {
    public Page<ProductInfoVO> findAll(Pageable pageable); // Pageable (data.domain꺼) : 페이징 처리 
    // public Long countByName(String name);
    public ProductInfoVO findBySeq(Integer seq);
    public void deleteBySeq(Integer seq);
}
