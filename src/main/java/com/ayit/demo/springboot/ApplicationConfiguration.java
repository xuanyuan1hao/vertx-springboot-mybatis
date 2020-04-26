
package com.ayit.demo.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * A configuration bean.
 * @author <a href="http://escoffier.me">Clement Escoffier</a>
 */
@Configuration
public class ApplicationConfiguration {

    @Value("${vertx.http.port}")
    private int httpPort;

    public int httpPort() {
        return httpPort;
    }
}
