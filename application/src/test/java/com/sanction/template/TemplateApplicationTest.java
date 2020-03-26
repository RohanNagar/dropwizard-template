package com.sanction.template;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.sanction.template.resources.TaskResource;

import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Environment;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TemplateApplicationTest {
  private final Environment environment = mock(Environment.class);
  private final JerseyEnvironment jersey = mock(JerseyEnvironment.class);
  private final HealthCheckRegistry healthChecks = mock(HealthCheckRegistry.class);
  private final MetricRegistry metrics = mock(MetricRegistry.class);
  private final TemplateConfiguration config = mock(TemplateConfiguration.class);

  private final TemplateApplication application = new TemplateApplication();

  @Before
  public void setup() {
    when(environment.jersey()).thenReturn(jersey);
    when(environment.healthChecks()).thenReturn(healthChecks);
    when(environment.metrics()).thenReturn(metrics);

    // HurricaneConfiguration NotNull fields
    when(config.getApprovedKeys()).thenReturn(new ArrayList<>());
  }

  @Test
  public void testRun() {
    ArgumentCaptor<Object> captor = ArgumentCaptor.forClass(Object.class);

    application.run(config, environment);

    // Verify register was called on jersey and healthChecks
    verify(jersey, atLeastOnce()).register(captor.capture());

    // Make sure each class that should have been registered on jersey was registered
    List<Object> values = captor.getAllValues();

    assertEquals(1, values.stream().filter(v -> v instanceof AuthDynamicFeature).count());
    assertEquals(1, values.stream().filter(v -> v instanceof TaskResource).count());
  }
}
