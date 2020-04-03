package com.sanctionco.template;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Provides methods to build a new instance of {@link SampleClient}.
 *
 * @see SampleClient
 */
public class SampleBuilder {
  private final Retrofit retrofit;

  /**
   * Constructs a builder instance that will be connect to the specified endpoint and use the
   * specified API key information.
   *
   * @param endpoint the base URL of the API endpoint to connect to
   * @param apiUser the basic authentication username to use when connecting to the endpoint
   * @param apiSecret the basic authentication secret to use when connecting to the endpoint
   */
  public SampleBuilder(String endpoint, String apiUser, String apiSecret) {
    Objects.requireNonNull(endpoint);
    Objects.requireNonNull(apiUser);
    Objects.requireNonNull(apiSecret);

    retrofit = new Retrofit.Builder()
      .baseUrl(ensureTrailingSlashExists(endpoint))
      .addConverterFactory(JacksonConverterFactory.create())
      .client(buildHttpClient(apiUser, apiSecret))
      .build();
  }

  /**
   * Builds an instance of {@code ThunderClient}.
   *
   * @return the new {@code ThunderClient} instance
   */
  public SampleClient newThunderClient() {
    return retrofit.create(SampleClient.class);
  }

  /**
   * Creates a new HttpClient that injects basic authorization into incoming requests.
   *
   * @param user the basic authentication username to use when connecting to the endpoint
   * @param secret the basic authentication secret to use when connecting to the endpoint
   * @return the built OkHttpClient
   */
  private OkHttpClient buildHttpClient(String user, String secret) {
    Objects.requireNonNull(user);
    Objects.requireNonNull(secret);

    String token = Base64.getEncoder()
        .encodeToString(String.format("%s:%s", user, secret).getBytes(Charset.forName("UTF-8")));

    String authorization = "Basic " + token;

    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    httpClient.addInterceptor((chain) -> {
      Request original = chain.request();

      // Add the authorization header
      Request request = original.newBuilder()
          .header("Authorization", authorization)
          .method(original.method(), original.body())
          .build();

      return chain.proceed(request);
    });

    return httpClient.build();
  }

  /**
   * Ensures that the given URL ends with a trailing slash ('/').
   *
   * @param url the url to verify contains a trailing slash
   * @return the original url with a trailing slash added if necessary
   */
  static String ensureTrailingSlashExists(String url) {
    return url.endsWith("/")
        ? url
        : url + "/";
  }
}
