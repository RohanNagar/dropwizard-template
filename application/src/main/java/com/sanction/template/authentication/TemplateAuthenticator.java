package com.sanction.template.authentication;

import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

public class TemplateAuthenticator implements Authenticator<BasicCredentials, Key> {
  private final List<Key> allKeys;

  @Inject
  public TemplateAuthenticator(List<Key> allKeys) {
    this.allKeys = allKeys;
  }

  @Override
  public Optional<Key> authenticate(BasicCredentials credentials) {
    // Construct a key from incoming credentials
    Key key = new Key(credentials.getUsername(), credentials.getPassword());

    // Check if that key exists in the list of approved keys
    if (!allKeys.contains(key)) {
      return Optional.empty();
    }

    return Optional.of(key);
  }
}
