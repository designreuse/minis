package net.minis.api.spring.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;

import com.google.common.base.Optional;

public class TicketAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public static final String DEFAULT_AUTHENTICATION_URL = "/login";

    public static final String DEFAULT_TICKET_NAME = "ticket";

    private String ticketName = DEFAULT_TICKET_NAME;

    public TicketAuthenticationFilter() {
        super(DEFAULT_AUTHENTICATION_URL);
    }

    public void setTicketParameterName(String ticketName) {
        this.ticketName = ticketName;
    }

    public void setAuthenticationUrl(String authenticationUrl) {
        RequestUrlMatcher requestUrlMatcher = new RequestUrlMatcher(authenticationUrl);
        this.setRequiresAuthenticationRequestMatcher(requestUrlMatcher);
    }

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        String ticket = Optional.fromNullable(obtainTokenId(request)).or("");
        TicketAuthenticationToken authRequest = new TicketAuthenticationToken(ticketName, ticket);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    protected String obtainTokenId(HttpServletRequest request) {
        return request.getParameter(ticketName);
    }

    private static final class RequestUrlMatcher implements RequestMatcher {

        private final String authenticationUrl;

        private RequestUrlMatcher(String authenticationUrl) {
            Assert.hasLength(authenticationUrl, "Authentication URL must be specified");
            Assert.isTrue(UrlUtils.isValidRedirectUrl(authenticationUrl), authenticationUrl + " isn't a valid redirect URL");
            this.authenticationUrl = authenticationUrl;
        }

        public boolean matches(HttpServletRequest request) {

            String uri = request.getRequestURI();
            int pathParamIndex = uri.indexOf(';');

            if (pathParamIndex > 0) {
                uri = uri.substring(0, pathParamIndex);
            }

            if ("".equals(request.getContextPath())) {
                return uri.endsWith(authenticationUrl);
            }

            return uri.endsWith(request.getContextPath() + authenticationUrl);
        }
    }

}
