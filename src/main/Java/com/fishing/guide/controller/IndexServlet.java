package com.fishing.guide.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet responsible for handling the initial landing (home) page redirection.
 * @author joshhaefner
 */
@WebServlet(
        name = "index",
        urlPatterns = { "/home" }
)
public class IndexServlet extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Processes GET requests by forwarding them to the index.jsp page.
     * Logs the action for debugging and monitoring purposes.
     *
     * @param request the HttpServletRequest object that contains the client's request.
     * @param response the HttpServletResponse object that contains the servlet's response.
     * @throws ServletException if an input or output error is detected when the servlet handles the GET request.
     * @throws IOException if the request for the GET could not be handled.
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Define the URL to forward requests to the index JSP page.
        String url = "/index.jsp";

        // Log the forwarding action for maintenance and debugging.
        logger.info("Forwarding to home page.");

        // Forward the request to the index JSP page.
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
