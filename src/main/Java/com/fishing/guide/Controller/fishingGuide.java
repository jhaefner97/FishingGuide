package com.fishing.guide.Controller;

import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import com.fishing.guide.Entity.DailyForecast;
import com.fishing.guide.Entity.UserData;
import com.fishing.guide.Entity.UserSavedLocations;
import com.fishing.guide.Entity.WeatherPackage;
import com.persistence.database.GenericDao;
import com.persistence.database.OpenWeatherDAO;
import com.persistence.openWeather.OpenWeatherGeo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(
        name = "guide",
        urlPatterns = { "/guide" }
)

public class fishingGuide extends HttpServlet {
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
            if (saveLocation != null && zipCode != null) {
                String userGuid = (String) session.getAttribute("userName");
                UserData user = userDataDao.getById(userGuid);
                UserSavedLocations newLocation = new UserSavedLocations();
                newLocation.setZipCode(zipCode);
                newLocation.setUserId(user);
                if (locationsDAO.findByPropertyEqual("zipCode", zipCode).size() == 0) {
                    locationsDAO.insert(newLocation);
                    request.setAttribute("locationSaved", "Location has been saved!");
                }
                else {
                    request.setAttribute("locationError", "ZipCode has already been saved!");
                }
            }
            WeatherPackage responseWeatherPackage = buildWeatherPackage(zipCode);
            request.setAttribute("cityName", responseWeatherPackage.getCityName());
            request.setAttribute("fiveDayForecast", responseWeatherPackage.getFiveDayForecast());

            if (locationsDAO.findByPropertyEqual("zipCode", zipCode).size() == 0) {
                request.setAttribute("newLocation", true);
            }
            session.setAttribute("currentZipcode", zipCode);
            logger.info("Complete");
        }

        String url = "/fishingGuide.jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}