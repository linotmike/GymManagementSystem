package com.ps.application.data;

import com.ps.application.models.Member;

import java.util.List;

public interface MemberDao {
    com.ps.application.models.Member getMemberById(int memberId);
    List<Member> getAllMembers();
    Member createMember(Member member);
    Member updateMember(Member member, int memberId);
    Member deleteMember(int memberId);
}
