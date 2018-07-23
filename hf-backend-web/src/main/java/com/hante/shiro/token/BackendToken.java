package com.hante.shiro.token;

import org.apache.shiro.authc.UsernamePasswordToken;

public class BackendToken extends UsernamePasswordToken {

    public enum LoginType {
        STAFF, ROOT
    }

    private LoginType loginType;

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public BackendToken(String username, String password, LoginType type) {
        super(username, password);
        loginType = type;
    }

}