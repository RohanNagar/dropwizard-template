package com.sanction.template.resources;

import com.sanction.template.authentication.Key;

import io.dropwizard.auth.Auth;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class TemplateResource {
  private static final Logger LOG = LoggerFactory.getLogger(TemplateResource.class);

  @Inject
  public TemplateResource() {

  }

  @GET
  @Path("/test")
  public Response getTest(@Auth Key key) {
    return Response.ok("Sample Method").build();
  }
}
