package com.accendl.mail.service;

public interface IUserService {

    void sendQROfGoogleAuthenticator(String email, String secret) throws Exception;

}
