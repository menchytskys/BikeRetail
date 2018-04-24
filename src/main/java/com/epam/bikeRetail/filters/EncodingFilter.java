package com.epam.bikeRetail.filters;


import javax.servlet.*;
import java.io.IOException;

/**
 * Filter to encode parameters for utf-8.
 *
 * @author Stepan Menchytsky
 */
public class EncodingFilter implements Filter {

    private static final String ENCODING_TYPE_PARAMETER = "encodingType";

    private String code;

    /**
     * This method initialize filter object.
     *
     * @param filterConfig the filter config.
     * @throws ServletException object if execution of method is failed.
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        code = filterConfig.getInitParameter(ENCODING_TYPE_PARAMETER);
    }

    /**
     * The method does main logic of filter.
     *
     * @param servletRequest  the servlet request.
     * @param servletResponse the servlet response.
     * @param filterChain    the filter chain of responsibility.
     * @throws IOException      object if execution of method is failed.
     * @throws ServletException object if execution of method is failed.
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String codeRequest = servletRequest.getCharacterEncoding();
        if (code != null && !code.equalsIgnoreCase(codeRequest)) {
            servletRequest.setCharacterEncoding(code);
            servletResponse.setCharacterEncoding(code);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * The method destroys filter.
     */
    @Override
    public void destroy() {

    }
}
