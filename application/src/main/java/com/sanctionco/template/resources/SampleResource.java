package com.sanctionco.template.resources;

import com.codahale.metrics.annotation.Metered;
import com.sanctionco.template.authentication.Key;
import com.sanctionco.template.models.SampleModel;

import io.dropwizard.auth.Auth;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provides API methods. The methods contained in this class are
 * available at the {@code /sample} endpoint, and return JSON in the response.
 */
@Path("/sample")
@Produces(MediaType.APPLICATION_JSON)
public class SampleResource {
  private static final Logger LOG = LoggerFactory.getLogger(SampleResource.class);

  /**
   * Constructs a new {@code SampleResource} with the given parameters.
   */
  @Inject
  public SampleResource() {
  }

  /**
   * Test endpoint.
   *
   * @param key the authentication key used to access the method
   * @return a sample model response
   */
  @GET
  @Operation(
      summary = "Tests the application",
      description = "Tests the application.",
      tags = { "test" },
      responses = {
          @ApiResponse(responseCode = "200",
              description = "The test was successful",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = SampleModel.class))),
          @ApiResponse(responseCode = "400",
              description = "The get request was malformed"),
          @ApiResponse(responseCode = "401",
              description = "The request was unauthorized")
      })
  @Metered(name = "get-requests")
  public Response getTest(@Parameter(hidden = true) @Auth Key key) {
    LOG.info("Successfully called GET method.");
    return Response.ok(new SampleModel("Success")).build();
  }
}
