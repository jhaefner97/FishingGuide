package com.fishing.guide.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "UserData")
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

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    List<UserSavedLocations> savedLocations = new ArrayList<>();

    public List<UserSavedLocations> getSavedLocations() {
        return savedLocations;
    }

    public void setSavedLocations(List<UserSavedLocations> savedLocations) {
        this.savedLocations = savedLocations;
    }

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
