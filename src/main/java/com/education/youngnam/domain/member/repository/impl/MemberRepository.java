package com.education.youngnam.domain.member.repository.impl;

import com.education.youngnam.domain.member.model.entity.Member;

import java.sql.SQLException;

public interface MemberRepository {
    int save(Member member) throws SQLException; // 로 받을수 있게 추상메서드로

    Member findByEmail(String email) throws SQLException;

    Member LoginMember(Member member) throws SQLException;
}
