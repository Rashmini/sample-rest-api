package org.rashmini.SampleRestAPI.model;

public class User {

    private String id;
    private String surname;
    private String firstName;
    private String email;

    public User() {

    }

    public User(String id, String surname, String firstName, String email) {
        this.id = id;
        this.surname = surname;
        this.firstName = firstName;
        this.email = email;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public String getSurname() {

        return surname;
    }

    public void setSurname(String surname) {

        this.surname = surname;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }
}
