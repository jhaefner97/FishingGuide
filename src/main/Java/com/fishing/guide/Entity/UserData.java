package com.fishing.guide.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class UserData {

    @Id
    private String userId;

    @Column(name = "email")
    private String email;

    @Column(name= "firstName")
    private String firstName;

    @Column(name= "lastName")
    private String lastName;

    @Column(name= "homeZip")
    private String homeZip;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getHomeZip() {
        return homeZip;
    }

    public void setHomeZip(String homeZip) {
        this.homeZip = homeZip;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return "UserData{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                '}';
    }
}
