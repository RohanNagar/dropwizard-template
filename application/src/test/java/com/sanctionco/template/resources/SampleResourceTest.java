package com.sanctionco.template.resources;

import com.sanctionco.template.authentication.Key;
import com.sanctionco.template.models.SampleModel;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;

class SampleResourceTest {
  SampleResource resource = new SampleResource();

  @Test
  void testGet() {
    Response result = resource.getTest(new Key("name", "secret"));

    SampleModel model = (SampleModel) result.getEntity();

    assertEquals("Success", model.getName());
  }
}
