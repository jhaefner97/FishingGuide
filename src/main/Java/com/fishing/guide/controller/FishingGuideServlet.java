package com.fishing.guide.controller;

import java.io.*;
import java.time.*;
import java.util.Comparator;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import com.fishing.guide.entity.DailyForecast;
import com.fishing.guide.entity.UserData;
import com.fishing.guide.entity.UserSavedLocations;
import com.fishing.guide.entity.WeatherPackage;
import com.persistence.database.GenericDao;
import com.persistence.database.OpenWeatherDAO;
import com.persistence.openWeather.OpenWeatherGeo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(
        name = "guide",
        urlPatterns = { "/guide" }
)

public class FishingGuideServlet extends HttpServlet {
    GenericDao<UserData> userDataDao = new GenericDao<>(UserData.class);
    GenericDao<UserSavedLocations> locationsDAO = new GenericDao<>(UserSavedLocations.class);
    private final Logger logger = LogManager.getLogger(this.getClass());

    public WeatherPackage buildWeatherPackage(String zipCode) {
        OpenWeatherDAO openWeatherDao = new OpenWeatherDAO();
        OpenWeatherGeo responseData = null;
        String cityName = null;
        try {
            responseData = openWeatherDao.getLocationData(Integer.parseInt(zipCode));
            cityName = responseData.getName();
            logger.info("Show Results for " + responseData.getName());

            List<DailyForecast> fiveDayForecast = openWeatherDao.buildDailyForecasts(responseData.getLat(), responseData.getLon());
            if (fiveDayForecast != null) {
                fiveDayForecast.sort(Comparator.comparing(DailyForecast::getDate));
                logger.info("Five Day Forecast Set");
            }
            return new WeatherPackage(cityName, fiveDayForecast);

        } catch (Exception e) {
            logger.info("Errored rendering location data");
            throw new RuntimeException(e);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String userGuid = (String) session.getAttribute("userName");
        UserData user = userDataDao.getById(userGuid);

        String zipCodeToSearch;
        if (session.getAttribute("currentZipcode") == null || session.getAttribute("currentZipcode") == "") {
            zipCodeToSearch = user.getHomeZip();
            session.setAttribute("currentZipcode", zipCodeToSearch);
        } else {
            zipCodeToSearch = (String) session.getAttribute("currentZipcode");
        }

        WeatherPackage responseWeatherPackage = buildWeatherPackage(zipCodeToSearch);
        request.setAttribute("cityName", responseWeatherPackage.getCityName());
        request.setAttribute("fiveDayForecast", responseWeatherPackage.getFiveDayForecast());
        request.setAttribute("newLocation", false);
        String url = "/fishingGuide.jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        logger.info("Logging the Guide forward!");

        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Setup session and context
        HttpSession session = request.getSession();
        ServletContext context = getServletContext();

        // Grab zipCode from context
        String zipCode = request.getParameter("zipCode");
        String saveLocation = request.getParameter("saveLocation");
        if (zipCode != null && !zipCode.isEmpty()) {

            WeatherPackage responseWeatherPackage = buildWeatherPackage(zipCode);
            request.setAttribute("cityName", responseWeatherPackage.getCityName());
            request.setAttribute("fiveDayForecast", responseWeatherPackage.getFiveDayForecast());

            if (locationsDAO.findByPropertyEqual("zipCode", zipCode).size() == 0) {
                request.setAttribute("newLocation", true);
            }
            session.setAttribute("currentZipcode", zipCode);

            logger.info("Complete");
        }

        if (saveLocation != null && zipCode != null) {
            WeatherPackage responseWeatherPackage = buildWeatherPackage(zipCode);
            String userGuid = (String) session.getAttribute("userName");
            UserData user = userDataDao.getById(userGuid);
            UserSavedLocations newLocation = new UserSavedLocations();
            ZonedDateTime now = ZonedDateTime.now();
            newLocation.setZipCode(zipCode);
            newLocation.setUserId(user);
            newLocation.setDateSaved(now);
            newLocation.setLocationAlias(responseWeatherPackage.getCityName());
            if (locationsDAO.findByPropertyEqual("zipCode", zipCode).size() == 0) {
                locationsDAO.insert(newLocation);
            }
            // Java code in your servlet or controller
            request.setAttribute("newLocation", false);
            response.sendRedirect("guide");
        } else {

            String url = "/fishingGuide.jsp";

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
    }
}