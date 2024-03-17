package com.fishing.guide.Controller;

import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import com.fishing.guide.Entity.DailyForecast;
import com.persistence.database.OpenWeatherDAO;
import com.persistence.openWeather.OpenWeatherGeo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(
        name = "guide",
        urlPatterns = { "/guide" }
)

public class fishingGuide extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
        OpenWeatherDAO openWeatherDao = new OpenWeatherDAO();
        OpenWeatherGeo responseData = null;
        try {
            responseData = openWeatherDao.getLocationData(Integer.parseInt(zipCode));
            request.setAttribute("cityName", responseData.getName());
            logger.info("Show Results for " + responseData.getName());

            List<DailyForecast> fiveDayForecast = openWeatherDao.buildDailyForecasts(responseData.getLat(), responseData.getLon());
            if (fiveDayForecast != null) {
                fiveDayForecast.sort(Comparator.comparing(DailyForecast::getDate));
                logger.info("Five Day Forecast Set");
                request.setAttribute("fiveDayForecast", fiveDayForecast);
            }

        } catch (Exception e) {
            logger.info("Errored rendering location data");
            throw new RuntimeException(e);
        }
        logger.info("Complete");

        String url = "/fishingGuide.jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}