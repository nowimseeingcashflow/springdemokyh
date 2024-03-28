package com.hello.demo2.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import com.hello.demo2.service.MemberService;
import com.hello.demo2.repository.MemberRepository;
import com.hello.demo2.repository.MemoryMemberRepository;

@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
