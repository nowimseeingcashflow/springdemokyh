package com.hello.demo2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.hello.demo2.domain.Member;
import com.hello.demo2.service.MemberService;

@Controller
public class MemberController {
        
    private final MemberService memServ;

    @Autowired
    public MemberController(MemberService ms){
        this.memServ = ms;
    }

    @GetMapping("members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memServ.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memServ.findMembers();
        model.addAttribute("members", members);
        
        return "members/memberList";
    }
}
