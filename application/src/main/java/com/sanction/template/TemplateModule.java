package com.sanction.template;

import com.codahale.metrics.MetricRegistry;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanction.template.authentication.Key;

import dagger.Module;
import dagger.Provides;

import io.dropwizard.jackson.Jackson;

import java.util.List;

import javax.inject.Singleton;

@Module
public class TemplateModule {
  private final MetricRegistry metrics;
  private final TemplateConfiguration config;

  public TemplateModule(MetricRegistry metrics, TemplateConfiguration config) {
    this.metrics = metrics;
    this.config = config;
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
