package kr.co.springbootex.ecommerce.service;

import kr.co.springbootex.ecommerce.repository.HelloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    private final HelloRepository helloRepository;

    @Autowired
    public HelloService(HelloRepository helloRepository) {
        this.helloRepository = helloRepository;
    }

    public String getHello(){
        return helloRepository.getHello();
    }
}
