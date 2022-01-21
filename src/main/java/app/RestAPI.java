package app;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;


import lombok.extern.log4j.Log4j2;
import mqtt.Consumer;

@ApplicationPath("api")
@Log4j2
public class RestAPI extends ResourceConfig {

	public RestAPI() throws Exception {
		packages("resource");
		//Consumer consumer = new Consumer();
	}

}

