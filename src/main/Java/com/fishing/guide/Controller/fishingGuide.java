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

        WeatherPackage responseWeatherPackage = buildWeatherPackage(user.getHomeZip());
        request.setAttribute("cityName", responseWeatherPackage.getCityName());
        request.setAttribute("fiveDayForecast", responseWeatherPackage.getFiveDayForecast());

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
        WeatherPackage responseWeatherPackage = buildWeatherPackage(zipCode);
        request.setAttribute("cityName", responseWeatherPackage.getCityName());
        request.setAttribute("fiveDayForecast", responseWeatherPackage.getFiveDayForecast());

        logger.info("Complete");

        String url = "/fishingGuide.jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}