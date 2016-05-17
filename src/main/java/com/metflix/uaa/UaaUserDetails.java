package com.metflix.uaa;

import org.springframework.security.core.authority.AuthorityUtils;

public class UaaUserDetails extends org.springframework.security.core.userdetails.User {
    private final User user;

    public UaaUserDetails(User user) {
        super(user.getUsername(), user.getPassword(), AuthorityUtils
                .createAuthorityList("ROLE_USER"));
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
