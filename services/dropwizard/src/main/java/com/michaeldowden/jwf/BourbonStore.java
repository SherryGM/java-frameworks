package com.michaeldowden.jwf;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import com.michaeldowden.jwf.config.RootConfiguration;
import com.michaeldowden.jwf.web.HelloWorldController;

public class BourbonStore extends Application<RootConfiguration> {
	public static void main(String[] args) throws Exception {
		new BourbonStore().run(args);
	}

	@Override
	public String getName() {
		return "hello-world";
	}

	@Override
	public void initialize(Bootstrap<RootConfiguration> bootstrap) {
		// nothing to do yet
	}

	@Override
	public void run(RootConfiguration configuration, Environment environment) {
		final HelloWorldController resource = new HelloWorldController(configuration.getTemplate(),
				configuration.getDefaultName());
		environment.jersey().register(resource);
	}

}
