package ru.apermyakov.servlets;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter for check role of user when post.
 *
 * @author apermyakov
 * @version 1.0
 * @since 11.12.2017
 */
public class PostRoleFilter implements Filter {

    /**
     * Field for user store object.
     */
    private final UserStore users = UserStore.getInstance();

    /**
     * Method for initial filter.
     *
     * @param filterConfig filter config
     * @throws ServletException servlet e
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * Method for post if user is admin.
     *
     * @param servletRequest servlet req
     * @param servletResponse servlet resp
     * @param filterChain filter chain
     * @throws IOException io e
     * @throws ServletException servlet e
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String role = users.getRole(request);
        if (("administrator").equals(role)) {
            response.sendRedirect(request.getContextPath() + "/user/post");
        } else {
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }

    /**
     * Method for destroy filter.
     */
    @Override
    public void destroy() {

    }
}
