package com.sanctionco.template.authentication;

import io.dropwizard.auth.basic.BasicCredentials;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SampleAuthenticatorTest {
  private static final Key KEY = new Key("application", "secret");
  private static final List<Key> KEYS = Collections.singletonList(KEY);

  private final SampleAuthenticator authenticator = new SampleAuthenticator(KEYS);

  @Test
  void testNullConstructorArgumentThrows() {
    assertThrows(NullPointerException.class,
        () -> new SampleAuthenticator(null));
  }

  @Test
  @SuppressWarnings("OptionalGetWithoutIsPresent")
  void testAuthenticateWithValidCredentials() {
    BasicCredentials credentials = new BasicCredentials("application", "secret");

    Optional<Key> result = authenticator.authenticate(credentials);

    assertAll("Assert authentication success",
        () -> assertTrue(result::isPresent),
        () -> assertEquals(KEY, result.get()));
  }

  @Test
  void testAuthenticateWithNullCredentials() {
    Optional<Key> result = authenticator.authenticate(null);

    assertFalse(result::isPresent);
  }

  @Test
  void testAuthenticateWithInvalidCredentials() {
    BasicCredentials credentials = new BasicCredentials("invalidApplication", "secret");

    Optional<Key> result = authenticator.authenticate(credentials);

    assertFalse(result::isPresent);
  }
}
