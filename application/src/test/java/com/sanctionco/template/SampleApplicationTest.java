package com.sanctionco.template;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.sanctionco.template.openapi.OpenApiBundle;
import com.sanctionco.template.openapi.OpenApiConfiguration;
import com.sanctionco.template.resources.SampleResource;

import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.clearInvocations;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SampleApplicationTest {
  private static final Environment environment = mock(Environment.class);
  private static final JerseyEnvironment jersey = mock(JerseyEnvironment.class);
  private static final HealthCheckRegistry healthChecks = mock(HealthCheckRegistry.class);
  private static final MetricRegistry metrics = mock(MetricRegistry.class);
  private static final SampleConfiguration config = mock(SampleConfiguration.class);

  @SuppressWarnings("unchecked")
  private final Bootstrap<SampleConfiguration> bootstrap = mock(Bootstrap.class);

  private final SampleApplication application = new SampleApplication();

  @BeforeAll
  static void setup() {
    when(environment.jersey()).thenReturn(jersey);
    when(environment.healthChecks()).thenReturn(healthChecks);
    when(environment.metrics()).thenReturn(metrics);

    // ThunderConfiguration NotNull fields
    when(config.getApprovedKeys()).thenReturn(new ArrayList<>());
    when(config.getOpenApiConfiguration()).thenReturn(new OpenApiConfiguration());
  }

  @AfterEach
  void reset() {
    clearInvocations(jersey, healthChecks);
  }

  @Test
  @SuppressWarnings("unchecked")
  void testInitialize() {
    ArgumentCaptor<OpenApiBundle> captor = ArgumentCaptor.forClass(OpenApiBundle.class);

    application.initialize(bootstrap);

    // Verify OpenApiBundle was added
    verify(bootstrap, times(1)).addBundle(captor.capture());

    // Verify getOpenApiConfiguration works
    OpenApiConfiguration openApiConfiguration = captor.getValue().getOpenApiConfiguration(config);
    assertTrue(openApiConfiguration.isEnabled());
  }

  @Test
  void testRun() {
    ArgumentCaptor<Object> captor = ArgumentCaptor.forClass(Object.class);

    application.run(config, environment);

    // Verify register was called on jersey and healthChecks
    verify(jersey, atLeastOnce()).register(captor.capture());

    // Make sure each class that should have been registered on jersey was registered
    List<Object> values = captor.getAllValues();

    assertAll("Assert all objects were registered to Jersey",
        () -> assertEquals(1,
            values.stream().filter(v -> v instanceof AuthDynamicFeature).count()),
        () -> assertEquals(1,
            values.stream().filter(v -> v instanceof SampleResource).count()));
  }
}
