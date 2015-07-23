/**
 * This software is being provided for technology demonstration purposes only.
 * Use of Vaadin Touchkit Add-on API are provided via Affero General Public License
 * (APGL 3.0).  Please refer the APGL 3.0 at www.gnu.org for further details.
 *
 * Items outside of the use of Vaadin Touchkit Add-on API are being provided per
 * FARS 52.227-14 Rights in Data - General.  Any redistribution or request for
 * copyright requires written consent by the Department of Veterans Affairs.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.appsonfhir.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mdufel
 */
public class AdminContextFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest)request).getSession(true);
        
        AdminContext ctx = (AdminContext) session.getAttribute("adminContext");
        if(ctx == null){
            ctx = new AdminContext();
            session.setAttribute("adminContext", ctx);
        }
        AdminContext.setContext(ctx);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
    
}
