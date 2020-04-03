package com.sanctionco.template;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SampleBuilderTest {

  @Test
  void testEnsureTrailingSlashExistsNoChange() {
    String url = "https://www.template.com/";
    String result = SampleBuilder.ensureTrailingSlashExists(url);

    assertEquals(url, result);
  }

  @Test
  void testEnsureTrailingSlashExistsNoSlash() {
    String url = "https://www.template.com";
    String result = SampleBuilder.ensureTrailingSlashExists(url);

    assertNotEquals(url, result);
    assertEquals("https://www.template.com/", result);
  }
}
