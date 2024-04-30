package com.fishing.guide.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

import com.fishing.guide.Entity.UserData;
import com.fishing.guide.Entity.UserSavedLocations;
import com.persistence.database.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.mysql.cj.util.StringUtils;

/**
 * Servlet responsible for managing user profiles and saved location data.
 * This includes viewing and updating profile information, and adding or deleting saved locations.
 */
@WebServlet(name = "profile", urlPatterns = {"/profile"})
public class ProfileManagerServlet extends HttpServlet {
    private GenericDao<UserData> userDataDao = new GenericDao<>(UserData.class);
    private GenericDao<UserSavedLocations> locationDao = new GenericDao<>(UserSavedLocations.class);
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Handles the GET request to display the user's profile page.
     * @param request the HttpServletRequest object that contains the client's request
     * @param response the HttpServletResponse object that contains the servlet's response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String userGuid = (String) session.getAttribute("userName");
        UserData user = userDataDao.getById(userGuid);

        request.setAttribute("user", user);
        String url = "/profile.jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        logger.info("Forwarding to the profile page for user: {}", userGuid);
        dispatcher.forward(request, response);
    }

    /**
     * Handles POST requests for updating user profile information or managing saved locations.
     * @param request the HttpServletRequest object that contains the client's request
     * @param response the HttpServletResponse object that contains the servlet's response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String userGuid = (String) session.getAttribute("userName");
        handleActions(request, response, userGuid);
    }

    /**
     * Handles specific actions based on user input, such as updating profiles or modifying saved locations.
     * @param request the HttpServletRequest object that contains the client's request
     * @param response the HttpServletResponse object that contains the servlet's response
     * @param userGuid the unique identifier of the user to perform actions for
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private void handleActions(HttpServletRequest request, HttpServletResponse response, String userGuid)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        switch (action) {
            case "deleteLocation":
                deleteLocation(request.getParameter("locationId"));
                response.sendRedirect("profile");
                break;
            case "editLocation":
                editLocation(request.getParameter("editLocationId"), request.getParameter("newLocationName"));
                response.sendRedirect("profile");
                break;
            case "updateProfile":
                updateProfile(request, response, userGuid);
                break;
            default:
                logger.error("Unrecognized action: {}", action);
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action not recognized");
                break;
        }
    }

    /**
     * Deletes a saved location identified by the given ID.
     * @param locationId the ID of the location to delete
     */
    private void deleteLocation(String locationId) {
        if (!StringUtils.isNullOrEmpty(locationId)) {
            UserSavedLocations locationToDelete = locationDao.getById(locationId);
            locationDao.delete(locationToDelete);
            logger.info("Deleted location with ID: {}", locationId);
        }
    }

    /**
     * Edits a saved location, updating its alias based on user input.
     * @param locationId the ID of the location to edit
     * @param newLocationName the new alias for the location
     */
    private void editLocation(String locationId, String newLocationName) {
        if (!StringUtils.isNullOrEmpty(locationId)) {
            UserSavedLocations locationToUpdate = locationDao.getById(locationId);
            locationToUpdate.setLocationAlias(newLocationName);
            locationDao.update(locationToUpdate);
            logger.info("Updated location with ID: {}", locationId);
        }
    }

    /**
     * Updates the profile information for the user identified by userGuid.
     * @param request the HttpServletRequest object that contains the client's request
     * @param response the HttpServletResponse object that contains the servlet's response
     * @param userGuid the unique identifier of the user whose profile is being updated
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private void updateProfile(HttpServletRequest request, HttpServletResponse response, String userGuid)
            throws ServletException, IOException {
        UserData user = userDataDao.getById(userGuid);
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setHomeZip(request.getParameter("homeZip"));
        userDataDao.update(user);
        logger.info("Profile updated for user: {}", userGuid);

        request.getRequestDispatcher("/profile.jsp").forward(request, response);
    }
}
