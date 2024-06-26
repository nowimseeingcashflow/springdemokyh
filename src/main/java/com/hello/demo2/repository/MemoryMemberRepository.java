package com.hello.demo2.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.hello.demo2.domain.Member;

@Repository
public class MemoryMemberRepository implements MemberRepository{

private static Map<Long, Member> store = new HashMap<>();
private static long sequence = 0L;

    @Override
    public List<Member> findAll() {
        // TODO Auto-generated method stub
        return new ArrayList<>(store.values());
    }
    @Override
    public Optional<Member> findById(Long id) {
        // TODO Auto-generated method stub
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // TODO Auto-generated method stub
        return store.values().stream()
            .filter(member -> member.getName().equals(name))
            .findAny();
    }

    @Override
    public Member save(Member member) {
        // TODO Auto-generated method stub

        member.setId(++sequence);
        store.put(member.getId(), member);

        return member;
    }
    
    public void clearStore(){
        store.clear();
    }
}
