package com.sanctionco.template;

import com.sanctionco.template.authentication.Key;
import com.sanctionco.template.openapi.OpenApiBundle;
import com.sanctionco.template.openapi.OpenApiConfiguration;
import com.sanctionco.template.resources.SampleResource;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Starts up the Thunder application. The run method will add resources, health checks,
 * and authenticators to the Jersey servlet in order to start the application. See
 * {@code Application} in the {@code io.dropwizard} module for details on the base class. Also see
 * <a href="https://www.dropwizard.io/1.3.5/docs/manual/core.html#application">the Dropwizard
 * manual</a> to learn more.
 *
 * @see SampleResource UserResource
 */
public class SampleApplication extends Application<SampleConfiguration> {

  public static void main(String[] args) throws Exception {
    new SampleApplication().run(args);
  }

  @Override
  public void initialize(Bootstrap<SampleConfiguration> bootstrap) {
    bootstrap.addBundle(new OpenApiBundle<SampleConfiguration>() {
      @Override
      public OpenApiConfiguration getOpenApiConfiguration(SampleConfiguration configuration) {
        return configuration.getOpenApiConfiguration();
      }
    });
  }

  @Override
  public void run(SampleConfiguration config, Environment env) {
    SampleComponent component = DaggerSampleComponent.builder()
        .sampleModule(new SampleModule(env.metrics(), config))
        .build();

    // Authentication
    env.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<Key>()
            .setAuthenticator(component.getSampleAuthenticator())
            .setRealm("THUNDER - AUTHENTICATION")
            .buildAuthFilter()));

    env.jersey().register(new AuthValueFactoryProvider.Binder<>(Key.class));

    // HealthChecks


    // Resources
    env.jersey().register(component.getSampleResource());
  }
}
