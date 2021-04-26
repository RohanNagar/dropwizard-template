package com.sanctionco.template;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanctionco.template.models.SampleModel;

import io.dropwizard.jackson.Jackson;
import io.dropwizard.testing.junit5.DropwizardClientExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;

import java.io.IOException;
import java.util.Collections;

import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import okhttp3.ResponseBody;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(DropwizardExtensionsSupport.class)
class SampleClientTest {
  private static final SampleModel user = new SampleModel("Test");

  /**
   * Resource to be used as a test double. Requests from the SampleClient interface
   * will be directed here for the unit tests. Use this to verify that all parameters are
   * being set correctly.
   */
  @Path("/")
  @Produces(MediaType.APPLICATION_JSON)
  public static final class TestResource {

    /**
     * Sample getTest method.
     *
     * @return an OK response
     */
    @GET
    @Path("sample")
    public Response getTest() {
      return Response.status(Response.Status.OK)
          .entity(user).build();
    }
  }

  private static final DropwizardClientExtension extension =
      new DropwizardClientExtension(TestResource.class);

  private final SampleBuilder builder =
      new SampleBuilder(extension.baseUri().toString(), "userKey", "userSecret");
  private final SampleClient client = builder.newThunderClient();

  @Test
  @SuppressWarnings("ConstantConditions")
  void testGetTest() throws IOException {
    SampleModel response = client.getTest()
        .execute()
        .body();

    assertEquals("Test", response.getName());
  }
}
