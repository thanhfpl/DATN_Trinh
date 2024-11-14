package com.demo;




import com.demo.repositories.LichHenRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
      var ctx=  SpringApplication.run(DemoApplication.class, args);
        LichHenRepository repo = ctx.getBean(LichHenRepository.class);
        System.out.println(repo.findAll());

    }

}
