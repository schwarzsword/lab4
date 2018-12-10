package config;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class WebApplicationInitialiser extends AbstractDispatcherServletInitializer {

    Logger log = LogManager.getLogger(WebApplicationInitialiser.class);

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);

    }

    @Override
    protected WebApplicationContext createServletApplicationContext() {

        AnnotationConfigWebApplicationContext servletConfig = new AnnotationConfigWebApplicationContext();
        servletConfig.register(config.web.WebConfig.class);

        return servletConfig;
    }

    @Override
    protected WebApplicationContext createRootApplicationContext() {
        AnnotationConfigWebApplicationContext rootConfig = new AnnotationConfigWebApplicationContext();
        rootConfig.register(RootConfig.class);

        return rootConfig;
    }


    @Override
    protected String[] getServletMappings() {

        return new String[]{"/"};
    }
}