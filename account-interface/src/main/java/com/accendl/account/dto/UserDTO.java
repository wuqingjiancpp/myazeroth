package com.accendl.account.dto;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wuqingjian
 * @since 2023-02-18
 */
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    public UserDTO(){}

    public UserDTO(Integer id, String phone, String email, String username,
                   String password, String secret, String answer, Boolean enabled) {
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.password = password;
        this.secret = secret;
        this.answer = answer;
        this.enabled = enabled;
    }

    private String base32Key;

    public String getBase32Key() {
        return base32Key;
    }

    public void setBase32Key(String base32Key) {
        this.base32Key = base32Key;
    }

    private Integer id;

    private String phone;

    private String email;

    private String username;

    private String password;

    private String secret;

    private String answer;

    private Boolean enabled;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "User{" +
        "id = " + id +
        ", phone = " + phone +
        ", email = " + email +
        ", username = " + username +
        ", password = " + password +
        ", secret = " + secret +
        ", answer = " + answer +
        ", enabled = " + enabled +
        ", base32Key = " + base32Key +
        "}";
    }
}
