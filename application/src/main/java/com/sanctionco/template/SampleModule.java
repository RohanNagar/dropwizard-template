package com.sanctionco.template;

import com.codahale.metrics.MetricRegistry;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanctionco.template.authentication.Key;

import dagger.Module;
import dagger.Provides;

import io.dropwizard.jackson.Jackson;

import java.util.List;
import java.util.Objects;

import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provides object dependencies used by objects constructed in the {@link SampleComponent}.
 */
@Module
class SampleModule {
  private static final Logger LOG = LoggerFactory.getLogger(SampleModule.class);

  private final MetricRegistry metrics;
  private final SampleConfiguration config;

  /**
   * Constructs a new {@code ThunderModule} with the given metrics and configuration.
   *
   * @param metrics the metrics that will be used to build meters and counters
   * @param config the Thunder configuration that provides information to build objects
   */
  SampleModule(MetricRegistry metrics, SampleConfiguration config) {
    this.metrics = Objects.requireNonNull(metrics);
    this.config = Objects.requireNonNull(config);
  }

  @Singleton
  @Provides
  ObjectMapper provideObjectMapper() {
    return Jackson.newObjectMapper();
  }

  @Singleton
  @Provides
  MetricRegistry provideMetricRegistry() {
    return metrics;
  }

  @Singleton
  @Provides
  List<Key> provideApprovedKeys() {
    return config.getApprovedKeys();
  }
}
