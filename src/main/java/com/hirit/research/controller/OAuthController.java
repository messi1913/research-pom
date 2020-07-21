package com.hirit.research.controller;

import java.security.Principal;

import com.hirit.research.entity.Account;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuthController {

    @GetMapping("/admin")
    public Account getAdminInfo() {
        return Account.builder()
            .age(30)
            .email("admin@admin.com")
            .userName("admin")
            .build();

    }

    @GetMapping("/user")
    public Account getUserInfo(Principal principal) {
        System.out.println("principal = " + principal);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return Account.builder()
            .age(30)
            .email("user@user.com")
            .userName("user")
            .build();
    }
}
