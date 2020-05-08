package com.g1k.bilguide;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Simple java class
 */
public class UserProfile {

    public String userName;
    public String userEmail;
    public int bilcoin;
    public ArrayList<String> buildings;

    // constructors
    public UserProfile() {
    }

    public UserProfile(String userName, String userEmail) {
        this.userName = userName;
        this.userEmail = userEmail;
        bilcoin = 0;
        buildings = new ArrayList<String>( Arrays.asList("Where is Faculty of Economics, Administrative, and Social Sciences?",
                "Where is Faculty of Law?",
                "Where is Faculty of   Engineering?",
                "Where is Department of Electrical and Electronic Engineering?",
                "Where is Faculty of Art, Design and Architecture?",
                "Where is Faculty of Education?",
                "Where is Faculty of Science?",
                "Which building has the largest lecture halls on the campus?",
                "Finished"));
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

    /**
     * gets the undiscovered buildings array list
     * @return buildings
     */
    public ArrayList getBuildings() { return buildings; }

    public String getQuestion( int index )
    {
        return buildings.get( index );
    }

    public int getSizeOfBuildings()
    {
        return buildings.size();
    }

}
