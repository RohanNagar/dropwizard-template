package com.sanction.template;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sanction.template.authentication.Key;

import io.dropwizard.Configuration;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class TemplateConfiguration extends Configuration {

  @NotNull
  @Valid
  @JsonProperty("approved-keys")
  private final List<Key> approvedKeys = null;

  List<Key> getApprovedKeys() {
    return approvedKeys;
  }
}
