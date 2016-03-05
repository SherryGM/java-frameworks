package com.michaeldowden.jwf;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.apache.derby.jdbc.EmbeddedConnectionPoolDataSource;
import org.apache.derby.jdbc.EmbeddedDataSource;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import com.michaeldowden.jwf.config.RootConfiguration;
import com.michaeldowden.jwf.web.HelloWorldResource;
import com.michaeldowden.jwf.web.ItemResource;

public class BourbonStore extends Application<RootConfiguration> {

	public static void main(String[] args) throws Exception {
		new BourbonStore().run(args);
	}

	private DBI dbi;

	@Override
	public String getName() {
		return "hello-world";
	}

	@Override
	public void initialize(Bootstrap<RootConfiguration> bootstrap) {
		EmbeddedDataSource ds = new EmbeddedConnectionPoolDataSource();
		ds.setDatabaseName("memory:bourbon;create=true");
		dbi = new DBI(ds);
		try (Handle handle = dbi.open()) {
			handle.createScript("bourbon.sql").execute();
		}
	}

	@Override
	public void run(RootConfiguration configuration, Environment environment) {
		final HelloWorldResource resource = new HelloWorldResource(configuration.getTemplate(),
				configuration.getDefaultName());
		environment.jersey().register(resource);

		ItemResource itemResource = new ItemResource(dbi);
		environment.jersey().register(itemResource);
	}

}
