package com.backpac.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserAccount extends User {

    private Member member;

    public UserAccount(Member member) {
        super(member.getNickname(), member.getPassword(), member.getAuthorities());
        this.member = member;
    }
}
