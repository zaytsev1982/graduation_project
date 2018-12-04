package config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

public class CustomWebInitializerConfig implements WebApplicationInitializer {

    private static final String DISPATCHER_WEB = "web";
    private static final String SECURITY_FILTER = "springSecurityFilterChain";

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext webApplicationContext =
            new AnnotationConfigWebApplicationContext();

        webApplicationContext.register(CustomWebConfig.class);
        servletContext.addListener(new ContextLoaderListener(webApplicationContext));

        ServletRegistration.Dynamic webRegistration = servletContext
            .addServlet(DISPATCHER_WEB, new DispatcherServlet(webApplicationContext));

        webRegistration.setLoadOnStartup(1);
        webRegistration.addMapping("/");
        webRegistration.setAsyncSupported(true);


        FilterRegistration.Dynamic filterRegistration = servletContext
            .addFilter(SECURITY_FILTER, new DelegatingFilterProxy());
        filterRegistration.addMappingForUrlPatterns(null, false, "/*");

    }
}
