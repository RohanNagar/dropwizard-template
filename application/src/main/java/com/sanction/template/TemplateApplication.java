package com.sanction.template;

import com.sanction.template.authentication.Key;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class TemplateApplication extends Application<TemplateConfiguration> {

  public static void main(String[] args) throws Exception {
    new TemplateApplication().run(args);
  }

  @Override
  public void initialize(Bootstrap<TemplateConfiguration> bootstrap) {

  }

  @Override
  public void run(TemplateConfiguration config, Environment env) {
    TemplateComponent component = DaggerTemplateComponent.builder()
        .templateModule(new TemplateModule(env.metrics(), config))
        .build();

    // Authentication
    env.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<Key>()
        .setAuthenticator(component.getHurricaneAuthenticator())
        .setRealm("HURRICANE - AUTHENTICATION")
        .buildAuthFilter()));

    env.jersey().register(new AuthValueFactoryProvider.Binder<>(Key.class));

    // Resources
    env.jersey().register(component.getTaskResource());
  }
}
