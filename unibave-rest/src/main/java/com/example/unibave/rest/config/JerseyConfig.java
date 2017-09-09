package com.example.unibave.rest.config;

import com.example.unibave.rest.resource.AlunoResourceImpl;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        register(AlunoResourceImpl.class);
        register(WebApplicationExceptionMapper.class);
    }

}
