package com.accendl.azeroth.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class AzAccountDTO implements Serializable {

    private String username;
    private String password;

    public AzAccountDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
