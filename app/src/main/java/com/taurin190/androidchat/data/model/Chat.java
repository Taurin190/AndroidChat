package com.taurin190.androidchat.data.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Chat implements Serializable {
    private String message;
}
