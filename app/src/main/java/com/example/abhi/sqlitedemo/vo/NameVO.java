package com.example.abhi.sqlitedemo.vo;

/**
 * Created by Abhi on 27-06-2017.
 */

public class NameVO {
    private int id;
    private String firstName;
    private String lastName;

    /**
     * Default constructor
     */
    public NameVO(){
        this.id=1;
        this.firstName="XX";
        this.lastName="YY";
    }

    /**
     * Parameterized constructor
     * @param id
     * @param firstName
     * @param lastName
     */
    public NameVO(final int id,final String firstName,final String lastName){
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}


