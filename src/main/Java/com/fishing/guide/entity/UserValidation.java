package com.fishing.guide.entity;

/**
 * Represents the validation status of a user within the system. This class
 * holds information necessary to identify if a user is new or existing based
 * on their unique identifier.
 * @author joshhaefner
 */
public class UserValidation {

    private final String userGuid;
    private final boolean newUser;

    /**
     * Constructs a new UserValidation object with the specified user GUID and new user status.
     *
     * @param userGuid the unique identifier for the user.
     * @param newUser a boolean indicating whether the user is new (true) or existing (false).
     */
    public UserValidation(String userGuid, boolean newUser) {
        this.userGuid = userGuid;
        this.newUser = newUser;
    }

    /**
     * Gets the unique identifier (GUID) of the user.
     *
     * @return the unique identifier of the user as a String.
     */
    public String getUserGuid() {
        return userGuid;
    }

    /**
     * Determines whether the user is a new user.
     *
     * @return true if the user is new, false otherwise.
     */
    public boolean isNewUser() {
        return newUser;
    }
}
