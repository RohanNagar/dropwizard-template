package com.sanctionco.template.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Represents a sample model.
 */
public class SampleModel {
  private final String name;

  /**
   * Constructs a new sample model with the given properties.
   *
   * @param name the model's name.
   */
  @JsonCreator
  public SampleModel(@JsonProperty("name") String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof SampleModel)) {
      return false;
    }

    SampleModel other = (SampleModel) obj;
    return Objects.equals(this.name, other.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", "User [", "]")
        .add(String.format("name=%s", name))
        .toString();
  }
}
