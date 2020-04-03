package com.sanctionco.template;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import com.sanctionco.template.authentication.Key;

import io.dropwizard.configuration.YamlConfigurationFactory;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.jersey.validation.Validators;

import java.io.File;
import java.util.Collections;

import javax.validation.Validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SampleConfigurationTest {
  private final ObjectMapper mapper = Jackson.newObjectMapper();
  private final Validator validator = Validators.newValidator();
  private final YamlConfigurationFactory<SampleConfiguration> factory
      = new YamlConfigurationFactory<>(SampleConfiguration.class, validator, mapper, "dw");

  @Test
  void testFromYaml() throws Exception {
    SampleConfiguration configuration = factory.build(new File(Resources.getResource(
        "fixtures/configuration/thunder-config.yaml").toURI()));

    assertEquals(1, configuration.getApprovedKeys().size());
    assertEquals(
        Collections.singletonList(new Key("test-app", "test-secret")),
        configuration.getApprovedKeys());

    // This config should use the default OpenAPI configuration
    assertAll("OpenAPI configuration is correct",
        () -> assertTrue(configuration.getOpenApiConfiguration().isEnabled()),
        () -> assertEquals("Sample API", configuration.getOpenApiConfiguration().getTitle()));
  }

  @Test
  void testFromYamlDisabledEmail() throws Exception {
    SampleConfiguration configuration = factory.build(new File(Resources.getResource(
        "fixtures/configuration/thunder-config-disabled-email.yaml").toURI()));

    assertEquals(1, configuration.getApprovedKeys().size());
    assertEquals(
        Collections.singletonList(new Key("test-app", "test-secret")),
        configuration.getApprovedKeys());
  }

  @Test
  void testFromYamlDisabledOpenApi() throws Exception {
    SampleConfiguration configuration = factory.build(new File(Resources.getResource(
        "fixtures/configuration/thunder-config-disabled-openapi.yaml").toURI()));

    assertFalse(configuration.getOpenApiConfiguration().isEnabled());
  }
}
