package com.michaeldowden.jwf;

import io.dropwizard.Application;
import io.dropwizard.jersey.sessions.SessionFactoryProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.apache.derby.jdbc.EmbeddedConnectionPoolDataSource;
import org.apache.derby.jdbc.EmbeddedDataSource;
import org.eclipse.jetty.server.session.SessionHandler;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import com.bazaarvoice.dropwizard.assets.ConfiguredAssetsBundle;
import com.michaeldowden.jwf.config.RootConfiguration;
import com.michaeldowden.jwf.web.CartResource;
import com.michaeldowden.jwf.web.ItemResource;
import com.michaeldowden.jwf.web.OrderResource;

public class BourbonStore extends Application<RootConfiguration> {

	public static void main(String[] args) throws Exception {
		new BourbonStore().run(args);
	}

	private DBI dbi;

	@Override
	public String getName() {
		return "BourbonStore";
	}

	@Override
	public void initialize(Bootstrap<RootConfiguration> bootstrap) {
		bootstrap.addBundle(new ConfiguredAssetsBundle("/assets/", "/"));

		EmbeddedDataSource ds = new EmbeddedConnectionPoolDataSource();
		ds.setDatabaseName("memory:bourbon;create=true");
		dbi = new DBI(ds);
		try (Handle handle = dbi.open()) {
			handle.createScript("bourbon.sql").execute();
		}
	}

	@Override
	public void run(RootConfiguration configuration, Environment environment) {
		ItemResource itemResource = new ItemResource(dbi);
		CartResource cartResource = new CartResource(dbi);
		OrderResource orderResource = new OrderResource();

		environment.jersey().register(SessionFactoryProvider.class);
		environment.servlets().setSessionHandler(new SessionHandler());

		environment.jersey().register(itemResource);
		environment.jersey().register(cartResource);
		environment.jersey().register(orderResource);
	}

}
