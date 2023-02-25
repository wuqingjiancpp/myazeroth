package com.accendl.azeroth.service;

public interface AzAccountService {

    /**
     * .account create $account $password
     *
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    boolean create(String username, String password) throws Exception;

    /**
     * .account set password $account $password $password
     *
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    boolean resetPassword(String username, String password) throws Exception;

    /**
     * .account password $old_password $new_password $new_password
     *
     * @param username
     * @param oldPassword
     * @param newPassword
     * @return
     * @throws Exception
     */
    boolean updatePassword(String username, String oldPassword, String newPassword) throws Exception;
}
