package com.fishing.guide.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.*;

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

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public UserData getUserId() {
        return userId;
    }

    public void setUserId(UserData userId) {
        this.userId = userId;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "UserSavedLocations{" +
                "Id=" + Id +
                ", userId=" + userId +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }

    public String getLocationAlias() {
        return locationAlias;
    }

    public void setLocationAlias(String locationAlias) {
        this.locationAlias = locationAlias;
    }

    public ZonedDateTime getDateSaved() {
        return dateSaved;
    }

    public void setDateSaved(ZonedDateTime dateSaved) {
        this.dateSaved = dateSaved;
    }
}
