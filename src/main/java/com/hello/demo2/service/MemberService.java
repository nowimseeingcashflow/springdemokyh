package com.hello.demo2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hello.demo2.domain.Member;
import com.hello.demo2.repository.MemberRepository;
//import com.hello.demo2.repository.MemoryMemberRepository;

@Service
public class MemberService {
    private final MemberRepository memRep;
    
    public MemberService(MemberRepository memRep){
        this.memRep = memRep;
    }

    public Long join(Member member){
        validateDupMem(member);
        memRep.save(member);
        return member.getId();

        
    }

    private void validateDupMem(Member mem){
        memRep.findByName(mem.getName())
        .ifPresent(m ->{
            throw new IllegalStateException("이미 있는 회원인데용");
        });
    }
    public List<Member> findMembers(){
        return memRep.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memRep.findById(memberId);
    }
}
