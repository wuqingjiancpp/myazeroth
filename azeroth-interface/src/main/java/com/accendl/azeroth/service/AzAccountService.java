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
     * .account set password crazywu@126.com 123456 123456
     * The password was changed
     *
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    boolean resetPassword(String username, String password) throws Exception;


    /**
     * .account set addon [$account] #addon
     * .account set addon crazywu@126.com 2
     *
     * @param username
     * @return
     * @throws Exception
     */
    boolean accountSetAddon(String username) throws Exception;
}
