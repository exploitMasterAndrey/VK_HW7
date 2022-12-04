package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        if (httpServletRequest.getMethod().equals("GET")){
            chain.doFilter(request, response);
            return;
        }

        if (httpServletRequest.getMethod().equals("POST")) {
            if (httpServletRequest.getParameter("productName") != null && httpServletRequest.getParameter("factoryName") != null && httpServletRequest.getParameter("count") != null) {
                try {
                    Integer.parseInt(httpServletRequest.getParameter("count"));
                    chain.doFilter(request, response);
                    return;
                } catch (NumberFormatException exception) {
                    exception.printStackTrace();
                }
            }
        }
        ((HttpServletResponse) response).sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Override
    public void destroy() {

    }
}
