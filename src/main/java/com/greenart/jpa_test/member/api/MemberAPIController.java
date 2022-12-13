package com.greenart.jpa_test.member.api;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenart.jpa_test.member.entity.MemberInfoVO;
import com.greenart.jpa_test.member.repository.MemberRepository;
import com.greenart.jpa_test.member.vo.LoginVO;

@RestController  // 데이터만 왔다갔다 할 용도
@RequestMapping("/api/member")
public class MemberAPIController {
    @Autowired MemberRepository repo;
    @PutMapping("/")
    public ResponseEntity<Object> memberJoinAPI(@RequestBody MemberInfoVO data){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        
        if(repo.countById(data.getId()) == 1) {
            map.put("status", false);
            map.put("message", data.getId()+" 은/는 이미 가입 된 아이디입니다.");
            return new ResponseEntity<>(map, HttpStatus.NOT_ACCEPTABLE);
        }
        
        repo.save(data);
        map.put("status", true);
        map.put("message", "정상적으로 가입되었습니다.");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
    

    @PostMapping("/login")
    public ResponseEntity<Object> userLogin(@RequestBody LoginVO data) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        MemberInfoVO loginUser = repo.findByIdAndPwd(data.getId(), data.getPwd());
        if(loginUser == null) {
            map.put("status", false);
            map.put("message", "아이디 또는 비밀번호 오류입니다.");
            return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
        }
        map.put("status", true);
        map.put("message", "로그인 되었습니다.");
        map.put("user", loginUser);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }
}
