package com.fishing.guide.Controller;

import java.io.*;
import java.time.ZonedDateTime;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import com.fishing.guide.Entity.UserData;
import com.fishing.guide.Entity.UserSavedLocations;
import com.fishing.guide.Entity.UserValidation;
import com.mysql.cj.util.StringUtils;
import com.persistence.database.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(
        name = "profile",
        urlPatterns = { "/profile" }
)

public class profileManager extends HttpServlet{
    GenericDao<UserData> userDataDao = new GenericDao<>(UserData.class);
    GenericDao<UserSavedLocations> locationDAO = new GenericDao<>(UserSavedLocations.class);
    private final Logger logger = LogManager.getLogger(this.getClass());

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String userGuid = (String) session.getAttribute("userName");
        UserData user = userDataDao.getById(userGuid);

        request.setAttribute("user", user);
        String url = "/profile.jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        logger.info("Logging the profile forward!");

        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Setup session and context
        HttpSession session = request.getSession();
        String locationId = request.getParameter("locationId");
        String editLocationId = request.getParameter("editLocationId");

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String zipCode = request.getParameter("homeZip");
        String action = request.getParameter("action");
        String userGuid = (String) session.getAttribute("userName");

        if (!StringUtils.isNullOrEmpty(locationId)) {
            UserSavedLocations locationToDelete = locationDAO.getById(locationId);
            locationDAO.delete(locationToDelete);
            logger.info("Deleted the location: " + locationId);
        }
        else if (!StringUtils.isNullOrEmpty(editLocationId)) {
            String newLocationName = request.getParameter("newLocationName");
            UserSavedLocations locationToUpdate = locationDAO.getById(editLocationId);
            locationToUpdate.setLocationAlias(newLocationName);
            locationDAO.update(locationToUpdate);
            logger.info("Updated the location: " + editLocationId);
        }
        else if (action.equals("updateProfile")) {
            UserData currentUserData = userDataDao.getById(userGuid);
            currentUserData.setFirstName(firstName);
            currentUserData.setLastName(lastName);
            currentUserData.setHomeZip(zipCode);
            userDataDao.update(currentUserData);
            logger.info("Updated the profile");
        }
        else {
            UserData user = userDataDao.getById(userGuid);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setHomeZip(zipCode);

            userDataDao.update(user);

            String url = "/index.jsp";

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            logger.info("New User Added!");

            dispatcher.forward(request, response);
        }
    }

}