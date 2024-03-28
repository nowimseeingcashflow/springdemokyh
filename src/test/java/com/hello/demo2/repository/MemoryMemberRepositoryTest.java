package com.hello.demo2.repository;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.hello.demo2.domain.Member;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afternoon(){
        repository.clearStore();
    }
    
    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");
        
        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        //System.out.println("result = "+(result==member));
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member_again = new Member();
        member_again.setName("spring_again");
        repository.save(member_again);

        Member member_again_again = new Member();
        member_again_again.setName("spring_again_again");
        repository.save(member_again_again);

        Member result2 = repository.findByName("spring_again").get();

        Assertions.assertThat(member_again).isEqualTo(result2);
    }

    @Test
    public void findAll(){
        Member member_loop = new Member();
        member_loop.setName("spring_loop");
        repository.save(member_loop);

        Member member_again_loop = new Member();
        member_again_loop.setName("spring_again_loop");
        repository.save(member_again_loop);

        List<Member> result3 = repository.findAll();
        
        Assertions.assertThat(result3.size()).isEqualTo(2);
    }
}
