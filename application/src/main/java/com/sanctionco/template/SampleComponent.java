package com.sanctionco.template;

import com.sanctionco.template.authentication.SampleAuthenticator;
import com.sanctionco.template.resources.SampleResource;

import dagger.Component;

import javax.inject.Singleton;

/**
 * Provides access to objects that need to be constructed through dependency injection. The
 * {@code Component} is a Dagger concept that uses multiple {@code Module} classes, including
 * {@link SampleModule}.
 * See {@code Component} in the {@code dagger} module for more information.
 */
@Singleton
@Component(modules = {SampleModule.class})
public interface SampleComponent {

  // Resources
  SampleResource getSampleResource();

  // ThunderAuthenticator
  SampleAuthenticator getSampleAuthenticator();
}
