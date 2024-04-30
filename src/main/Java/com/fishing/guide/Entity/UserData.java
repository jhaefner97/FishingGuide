package com.fishing.guide.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user's data within the system, containing personal information and associated saved locations.
 * @author joshhaefner
 */
@Entity
@Table(name = "UserData")
public class UserData {

    @Id
    private String userId;

    @Column(name = "email")
    private String email;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "homeZip")
    private String homeZip;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<UserSavedLocations> savedLocations = new ArrayList<>();

    /**
     * Gets the list of user's saved locations.
     *
     * @return a list of {@link UserSavedLocations}.
     */
    public List<UserSavedLocations> getSavedLocations() {
        return savedLocations;
    }

    /**
     * Sets the list of user's saved locations.
     *
     * @param savedLocations the list of {@link UserSavedLocations} to be set.
     */
    public void setSavedLocations(List<UserSavedLocations> savedLocations) {
        this.savedLocations = savedLocations;
    }

    /**
     * Gets the user's first name.
     *
     * @return the user's first name as a String.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the user's first name.
     *
     * @param firstName the first name to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the user's last name.
     *
     * @return the user's last name as a String.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the user's last name.
     *
     * @param lastName the last name to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the user's home ZIP code.
     *
     * @return the home ZIP code as a String.
     */
    public String getHomeZip() {
        return homeZip;
    }

    /**
     * Sets the user's home ZIP code.
     *
     * @param homeZip the ZIP code to set.
     */
    public void setHomeZip(String homeZip) {
        this.homeZip = homeZip;
    }

    /**
     * Gets the user's unique identifier.
     *
     * @return the user ID as a String.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the user's unique identifier.
     *
     * @param userId the user ID to set.
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Gets the user's email address.
     *
     * @return the email address as a String.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email address.
     *
     * @param email the email address to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns a string representation of the user data including user ID and email.
     *
     * @return a string representation of the user data.
     */
    @Override
    public String toString() {
        return "UserData{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                '}';
    }
}
