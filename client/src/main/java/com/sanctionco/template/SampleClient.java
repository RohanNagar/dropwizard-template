package com.sanctionco.template;

import com.sanctionco.template.models.SampleModel;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Provides methods to interact with the REST API over HTTP requests. Construct an instance
 * of this class using {@link SampleBuilder}.
 */
public interface SampleClient {

  /**
   * Gets the test endpoint.
   *
   * @return the Call object that holds the response after the request completes
   */
  @GET("sample")
  Call<SampleModel> getTest();
}
