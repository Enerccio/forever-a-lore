package com.en_circle.fas.ui;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class Main implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {
        initRootApplicationContext(servletContext);
    }

    private void addServlet(String servletName, String servletMapping, String configLocation, ServletContext servletContext) {
        XmlWebApplicationContext dispatcherContextDownload = new XmlWebApplicationContext();
        dispatcherContextDownload.setConfigLocations(configLocation);

        ServletRegistration.Dynamic dispatcherServletDownload = servletContext.addServlet(servletName, new DispatcherServlet(dispatcherContextDownload));
        dispatcherServletDownload.addMapping(servletMapping);
        dispatcherServletDownload.setLoadOnStartup(1);
    }

    private void initRootApplicationContext(ServletContext servletContext) {
        XmlWebApplicationContext rootContext = new XmlWebApplicationContext();
        rootContext.setConfigLocations("/WEB-INF/classes/META-INF/spring/application-context.xml");

        servletContext.addListener(new ContextLoaderListener(rootContext));
        servletContext.addListener(new RequestContextListener());
    }

}
