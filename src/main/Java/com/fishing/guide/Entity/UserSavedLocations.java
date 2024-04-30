package com.fishing.guide.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.time.ZonedDateTime;

/**
 * Represents a user's saved location within the system, including details such as the associated user,
 * the ZIP code of the location, an alias for the location, and the date the location was saved.
 * @author joshhaefner
 */
@Entity
@Table(name = "UserSavedLocations")
public class UserSavedLocations {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int Id;

    @JoinColumn(name = "UserId")
    @ManyToOne
    private UserData userId;

    @Column(name = "ZipCode")
    private String zipCode;

    @Column(name = "LocationAlias")
    private String locationAlias;

    @Column(name = "DateSaved")
    private ZonedDateTime dateSaved;

    /**
     * Gets the identifier for this saved location.
     *
     * @return the unique identifier of the saved location.
     */
    public int getId() {
        return Id;
    }

    /**
     * Sets the identifier for this saved location.
     *
     * @param id the unique identifier to set for this saved location.
     */
    public void setId(int id) {
        this.Id = id;
    }

    /**
     * Gets the user data associated with this saved location.
     *
     * @return the associated {@link UserData}.
     */
    public UserData getUserId() {
        return userId;
    }

    /**
     * Sets the user data associated with this saved location.
     *
     * @param userId the {@link UserData} to associate with this saved location.
     */
    public void setUserId(UserData userId) {
        this.userId = userId;
    }

    /**
     * Gets the ZIP code for this saved location.
     *
     * @return the ZIP code as a String.
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Sets the ZIP code for this saved location.
     *
     * @param zipCode the ZIP code to set for this saved location.
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Gets the alias for this saved location.
     *
     * @return the location alias as a String.
     */
    public String getLocationAlias() {
        return locationAlias;
    }

    /**
     * Sets the alias for this saved location.
     *
     * @param locationAlias the alias to set for this saved location.
     */
    public void setLocationAlias(String locationAlias) {
        this.locationAlias = locationAlias;
    }

    /**
     * Gets the date and time when this location was saved.
     *
     * @return the date and time of saving as {@link ZonedDateTime}.
     */
    public ZonedDateTime getDateSaved() {
        return dateSaved;
    }

    /**
     * Sets the date and time when this location was saved.
     *
     * @param dateSaved the {@link ZonedDateTime} to set as the date and time of saving.
     */
    public void setDateSaved(ZonedDateTime dateSaved) {
        this.dateSaved = dateSaved;
    }

    /**
     * Provides a string representation of the user's saved locations,
     * including the ID, user ID, and ZIP code.
     *
     * @return a string representation of the saved location details.
     */
    @Override
    public String toString() {
        return "UserSavedLocations{" +
                "Id=" + Id +
                ", userId=" + userId +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
