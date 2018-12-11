package util;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class SuccessRedirectUrlHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse, Authentication authentication)
        throws IOException, ServletException {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        authorities.forEach(a -> {
                switch (a.getAuthority()) {
                    case "ROLE_USER":
                        try {
                            redirectStrategy
                                .sendRedirect(httpServletRequest, httpServletResponse, "/user");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "ROLE_ADMIN":
                        try {
                            redirectStrategy
                                .sendRedirect(httpServletRequest, httpServletResponse, "/admin");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        throw new IllegalThreadStateException();
                }
            }
        );
    }
}
