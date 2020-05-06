package com.g1k.bilguide;

/**
 * Simple java class
 */
public class UserProfile {
    public String userName;
    public String userEmail;
    public int bilcoin;

    // constructors
    public UserProfile() {

    }

    public UserProfile(String userName, String userEmail) {
        this.userName = userName;
        this.userEmail = userEmail;
        bilcoin = 0;
    }

    // methods

    /**
     *  gets the userName
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * gets the bilcoin
     * @return bilcoin
     */
    public int getBilcoin()
    {
        return bilcoin;
    }

}
