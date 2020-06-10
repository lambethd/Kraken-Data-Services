package lambethd.kraken.application.security;

import lambethd.kraken.application.exception.UnauthorizedException;
import lambethd.kraken.application.interfaces.IAuthService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends GenericFilterBean {
    private final IAuthService authService;

    public JwtTokenFilter(IAuthService authService) {
        this.authService = authService;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {
        try {
            String token = authService.resolveToken((HttpServletRequest) req);
            if ((token != null) && authService.validate(token)) {
                Authentication auth = authService.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (UnauthorizedException e) {
            e.printStackTrace();
            HttpServletResponse response = (HttpServletResponse) res;
            response.sendError(e.getHttpStatusCode().value(), e.getMessage());
            return;
        }
        filterChain.doFilter(req, res);
    }
}
