package com.ps.application.controllers;

import com.ps.application.data.MemberDao;
import com.ps.application.models.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/members")
public class MemberController {
    private MemberDao memberDao;

    @Autowired
    public MemberController(MemberDao memberDao){
        this.memberDao = memberDao;
    }

    @GetMapping("{memberId}")
    public ResponseEntity<?>getMembersById(@PathVariable int memberId){
        Member member = memberDao.getMemberById(memberId);
        return ResponseEntity.ok(member);
    }
}
