package com.taurin190.androidchat.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
    private String userId;
    private String name;
    private String email;
}
