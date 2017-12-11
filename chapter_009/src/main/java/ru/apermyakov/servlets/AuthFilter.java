package ru.apermyakov.servlets;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter for authentication user.
 *
 * @author apermyakov
 * @version 1.0
 * @since 08.12.2017
 */
public class AuthFilter implements Filter{

    /**
     * Initial filter.
     *
     * @param filterConfig filter config
     * @throws ServletException servlet e
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * Method for show user authentication window.
     *
     * @param servletRequest servlet request
     * @param servletResponse servlet response
     * @param filterChain filter chain
     * @throws IOException io e
     * @throws ServletException servlet e
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (((HttpServletRequest) servletRequest).getRequestURI().contains("/signin")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            HttpSession session = request.getSession();
            synchronized (session) {
                if (session.getAttribute("login") == null) {
                    ((HttpServletResponse) servletResponse).sendRedirect(request.getContextPath() + "/signin");
                    return;
                }
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    /**
     * Destroy filter.
     */
    @Override
    public void destroy() {

    }
}
