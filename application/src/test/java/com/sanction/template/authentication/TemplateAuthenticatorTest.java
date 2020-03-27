package com.sanction.template.authentication;

import com.google.common.collect.Lists;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.basic.BasicCredentials;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TemplateAuthenticatorTest {
  private final Key key = new Key("application", "secret");
  private final List<Key> keys = Lists.newArrayList(key);

  private TemplateAuthenticator authenticator;

  @Before
  public void setup() {
    authenticator = new TemplateAuthenticator(keys);
  }

  @Test
  public void testAuthenticateWithValidCredentials() {
    BasicCredentials credentials = new BasicCredentials("application", "secret");

    Optional<Key> result = authenticator.authenticate(credentials);

    assertTrue(result.isPresent());
    assertEquals(key, result.get());
  }

  @Test
  public void testAuthenticateWithInvalidCredentials() {
    BasicCredentials credentials = new BasicCredentials("invalidApplication", "secret");

    Optional<Key> result = authenticator.authenticate(credentials);

    assertFalse(result.isPresent());
  }
}
