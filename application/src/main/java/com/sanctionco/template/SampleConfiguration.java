package com.sanctionco.template;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sanctionco.template.authentication.Key;
import com.sanctionco.template.openapi.OpenApiConfiguration;

import io.dropwizard.Configuration;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Provides Thunder configuration options that are defined in the configuration file. The
 * configuration objects are passed into the application's {@code Module} classes in order to
 * provide information necessary to construct the application dependencies. For more information
 * on Dropwizard Configuration, see the {@code Configuration} class in the {@code io.dropwizard}
 * module.
 *
 * @see SampleModule
 */
public class SampleConfiguration extends Configuration {

  @NotNull
  @Valid
  @JsonProperty("approvedKeys")
  private final List<Key> approvedKeys = null;

  List<Key> getApprovedKeys() {
    return approvedKeys;
  }

  /* Optional configuration options */

  @Valid
  @JsonProperty("openApi")
  private final OpenApiConfiguration openApiConfiguration = null;

  OpenApiConfiguration getOpenApiConfiguration() {
    return openApiConfiguration == null
        ? new OpenApiConfiguration()
        : openApiConfiguration;
  }
}
