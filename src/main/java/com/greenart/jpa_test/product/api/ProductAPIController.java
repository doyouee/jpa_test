package com.greenart.jpa_test.product.api;

import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greenart.jpa_test.product.entity.ProductInfoVO;
import com.greenart.jpa_test.product.repository.ProductRepository;

@RestController
@RequestMapping("/api/product")
public class ProductAPIController {
    @Autowired ProductRepository repos;
    @PutMapping("")
    public ResponseEntity<Object> productJoinAPI(@RequestBody ProductInfoVO prd){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        // if(repos.countByName(prd.getName()) == 1) {
            //     map.put("status", false);
            //     map.put("message", prd.getName() + " 은/는 이미 저장된 제품입니다.");
            //     return new ResponseEntity<>(map, HttpStatus.NOT_ACCEPTABLE);
            // }
            repos.save(prd);
            map.put("status", true);
            map.put("message", "제품이 정상적으로 저장되었습니다.");
            return new ResponseEntity<>(map, HttpStatus.CREATED);
        }
        

        //http://localhost:8999/api/product/list?page=1&size=10
                                            //page=1 : 현재 페이지 1이 시작
                                            //size=10 : 10개씩 묶기
                                            //sort=seq,desc : seq를 기준으로 내림차순 정렬
        //http://localhost:8999/api/product/list?page=0&size=10&sort=seq,desc
        @GetMapping("/list")
            public ResponseEntity<Object> getProductList(Pageable pageable){  // Pageable (data.domain꺼) : 페이징 처리 
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            map.put("list", repos.findAll(pageable));
            return new ResponseEntity<>(map, HttpStatus.CREATED);
        } // 협업(미니프로젝트) 리스트 내보내고 상세정보 보기


        @GetMapping("/detail")
        public ResponseEntity<Object> getProductDetail(@RequestParam Integer prodNo) {
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            map.put("detail", repos.findBySeq(prodNo));
            return new ResponseEntity<>(map, HttpStatus.CREATED);
        } // 협업(미니프로젝트) 리스트 내보내고 상세정보 보기


        @DeleteMapping("")
        @Transactional //SQL 실행 시 오류가 발생했다면, 실행 이전의 상태로 되돌림
        public ResponseEntity<Object> deleteProduct(@RequestParam Integer prodNo) {
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            repos.deleteBySeq(prodNo); // delete from product_info where pi_seq = "";
            map.put("status", true);
            map.put("message", "제품 정보를 삭제했습니다.");
            return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
        }
        
        
        //http://localhost:8999/api/product/update/regDt?prodNo=3&value=20081013 131530
        @PatchMapping("/update/{type}")
        @Transactional
        public ResponseEntity<Object> updateProduct(@RequestParam Integer prodNo, @PathVariable String type, @RequestParam String value) {
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            ProductInfoVO data = repos.findBySeq(prodNo); // 번호에 해당하는 정보를 가져와서
            if(data == null) { //가져온 정보가 없다면
                map.put("status", false); // 실패 메시지를 설정하고
                map.put("message", "잘못된 제품 번호입니다.");
                return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST); //실패처리 한다.
            }
            else if(type.equals("name")) {  //PathVariable type 값이 name이라면
                data.setName(value); // 가져온 데이터에서 name을 변경하고
            }
            else if(type.equals("price")) {data.setPrice(Integer.parseInt(value));}
            else if(type.equals("discount")) {data.setDiscount(Double.parseDouble(value));}
            else if(type.equals("regDt")) {
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd HHmmss");
                try { data.setRegDt(format.parse(value)); }
                catch(Exception e) { e.printStackTrace(); }
            }
            else if(type.equals("status")){data.setStatus(Integer.parseInt(value));}
            else if(type.equals("stock")){data.setStock(Integer.parseInt(value));}
            else {
                map.put("status", false);
                map.put("message", "타입이 잘못되었습니다. [name, price, discount, regDt, status, stock]");
                return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
            }
            repos.save(data); //저장(Update)한다.
            map.put("status", true); // 성공 메시지를 설정한다.
            map.put("message", "제품 이름이 변경되었습니다.");
            return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
        }
}

