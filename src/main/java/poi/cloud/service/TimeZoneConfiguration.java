package poi.cloud.service;

import java.util.TimeZone;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.annotation.Configuration;

@Configuration
public class TimeZoneConfiguration implements ServletContextListener{
    public void contextInitialized(ServletContextEvent event) {
        System.setProperty("user.timezone", "GMT+08:00");
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+08:00"));
    }

    public void contextDestroyed(ServletContextEvent event) {}
}
