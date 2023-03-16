package com.accendl.account.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wuqingjian
 * @since 2023-02-18
 */
@Data
@Builder
public class UserDTO implements Serializable {

    private String base32Key;

    private Integer id;

    private String phone;

    private String email;

    private String username;

    private String password;

    private String secret;

    private String answer;

    @Builder.Default private Boolean enabled = true;

    public UserDTO(String base32Key, String username) {
        this.base32Key = base32Key;
        this.username = username;
    }

    private UserDTO(String base32Key, Integer id, String phone, String email, String username, String password, String secret, String answer, Boolean enabled) {
        this.base32Key = base32Key;
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.password = password;
        this.secret = secret;
        this.answer = answer;
        this.enabled = enabled;
    }
}
