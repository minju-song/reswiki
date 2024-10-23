package com.teddybear.reswiki.member.entity;

public enum Role {
    ROLE_PENDING,
    ROLE_USER,
    ;

    public boolean canBeLoggedIn() { return this == ROLE_USER; }
}
