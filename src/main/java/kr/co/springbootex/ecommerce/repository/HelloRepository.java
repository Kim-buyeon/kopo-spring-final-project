package kr.co.springbootex.ecommerce.repository;

import org.springframework.stereotype.Repository;

@Repository
public class HelloRepository {


    public String getHello(){
        return "이건 Spring Boot UI 페이지 입니다.";
    }
}
