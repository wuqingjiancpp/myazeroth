package com.accendl.account.entity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wuqingjian
 * @since 2023-02-18
 */
public class Authority implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String authority;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "Authority{" +
        "username = " + username +
        ", authority = " + authority +
        "}";
    }
}
