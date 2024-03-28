package com.hello.demo2.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hello.demo2.domain.Member;
import com.hello.demo2.repository.MemoryMemberRepository;

public class MemberServiceTest {

    MemberService memService;
    MemoryMemberRepository memRep;

    @BeforeEach
    public void BeforeEach(){
        memRep = new MemoryMemberRepository();
        memService = new MemberService(memRep);
    }

    @AfterEach
    public void AfterEach(){
        memRep.clearStore();;
    }
    @Test
    void testFindMembers() {

    }

    @Test
    void testFindOne() {

    }

    @Test
    void testJoin() {
        Member member = new Member();
        member.setName("cute");

        Long savedId = memService.join(member);

        Member findMember = memService.findOne(savedId).get();
        Assertions.assertThat(findMember.getName()).isEqualTo(member.getName());

    }

    @Test
    public void testJoinDupCheck(){
        Member member1 = new Member();
        member1.setName("happy");

        Member member2 = new Member();
        member2.setName("happy");

        memService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, ()-> memService.join(member2));
        
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 있는 회원인데용");
        // try {
        //     memService.join(member2);
        //     fail();
        // } catch (Exception e) {
        //     // TODO: handle exception
        //     Assertions.assertThat(e.getMessage()).isEqualTo("이미 있는 회원인데용");
        // }
    }
}
