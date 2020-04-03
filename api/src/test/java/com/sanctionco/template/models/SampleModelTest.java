package com.sanctionco.template.models;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.dropwizard.jackson.Jackson;
import io.dropwizard.testing.FixtureHelpers;

import java.util.StringJoiner;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SampleModelTest {
  private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

  // Test objects should have the same values as in 'resources/fixtures/sample_model_*.json'
  private final SampleModel sampleModelOne = new SampleModel("One");
  private final SampleModel sampleModelTwo = new SampleModel("Two");

  @BeforeAll
  static void setup() {
  }

  @Test
  void testSampleModelOneJsonSerialization() throws Exception {
    String expected = MAPPER.writeValueAsString(MAPPER.readValue(
        FixtureHelpers.fixture("fixtures/sample_model_one.json"), SampleModel.class));

    assertEquals(expected, MAPPER.writeValueAsString(sampleModelOne));
  }

  @Test
  void testSampleModelOneJsonDeserialization() throws Exception {
    SampleModel fromJson = MAPPER.readValue(
        FixtureHelpers.fixture("fixtures/sample_model_one.json"), SampleModel.class);

    assertEquals(sampleModelOne, fromJson);
  }

  @Test
  void testSampleModelTwoJsonSerialization() throws Exception {
    String expected = MAPPER.writeValueAsString(MAPPER.readValue(
        FixtureHelpers.fixture("fixtures/sample_model_two.json"), SampleModel.class));

    assertEquals(expected, MAPPER.writeValueAsString(sampleModelTwo));
  }

  @Test
  void testSampleModelTwoJsonDeserialization() throws Exception {
    SampleModel fromJson = MAPPER.readValue(
        FixtureHelpers.fixture("fixtures/sample_model_two.json"), SampleModel.class);

    assertEquals(sampleModelTwo, fromJson);
  }

  @Test
  @SuppressWarnings({"ConstantConditions", "ObjectEqualsNull"})
  void testEquals() {
    assertAll("Basic equals properties",
        () -> assertTrue(!sampleModelOne.equals(null),
            "User must not be equal to null"),
        () -> assertTrue(!sampleModelOne.equals(new Object()),
            "User must not be equal to another type"),
        () -> assertEquals(sampleModelOne, sampleModelOne,
            "User must be equal to itself"));

    // Also test against an equal object
    SampleModel sameUser = new SampleModel("One");

    assertAll("Verify against other created objects",
        () -> assertNotEquals(sampleModelOne, sampleModelTwo),
        () -> assertEquals(sameUser, sampleModelOne));
  }

  @Test
  void testHashCodeSame() {
    SampleModel userOne = new SampleModel("One");
    SampleModel userTwo = new SampleModel("One");

    assertEquals(userOne.hashCode(), userTwo.hashCode());
  }

  @Test
  void testHashCodeDifferent() {
    SampleModel userOne = new SampleModel("One");
    SampleModel userTwo = new SampleModel("Two");

    assertNotEquals(userOne.hashCode(), userTwo.hashCode());
  }

  @Test
  void testToString() {
    String expected = new StringJoiner(", ", "User [", "]")
            .add(String.format("name=%s", "One"))
            .toString();

    assertEquals(expected, sampleModelOne.toString());
  }
}
