package com.wiremock.api.mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.wiremock.api.service.ReqresApiService;
import com.wiremock.api.interceptor.AllureLoggingInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class WireMockSetup {
    private static WireMockServer wireMockServer;
    private static ReqresApiService reqresApiService;

    public static void startServer() {
        wireMockServer = new WireMockServer(wireMockConfig().port(9090));
        wireMockServer.start();
        WireMock.configureFor("localhost", 9090);
    }

    public static void setupStubs() {
        stubFor(get(urlEqualTo("/users?page=1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(ApiMocks.getUsersResponse())));

        stubFor(get(urlEqualTo("/users?page=2"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(ApiMocks.getUsersResponsePage2())));

        stubFor(get(urlEqualTo("/users/1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(ApiMocks.getSingleUserResponse())));

        stubFor(get(urlEqualTo("/users/2"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(ApiMocks.getSingleUserResponse2())));

        stubFor(get(urlEqualTo("/users/3"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(ApiMocks.getSingleUserResponse3())));

        stubFor(post(urlEqualTo("/users"))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader("Content-Type", "application/json")
                        .withBody(ApiMocks.createUserResponse())));

        stubFor(put(urlEqualTo("/users/1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(ApiMocks.updateUserResponse())));

        stubFor(patch(urlEqualTo("/users/1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(ApiMocks.patchUserResponse())));

        stubFor(delete(urlEqualTo("/users/1"))
                .willReturn(aResponse()
                        .withStatus(204)));

        // Negative test stubs
        stubFor(get(urlEqualTo("/users/999"))
                .willReturn(aResponse()
                        .withStatus(404)
                        .withHeader("Content-Type", "application/json")
                        .withBody(ApiMocks.getErrorResponse())));

        stubFor(put(urlEqualTo("/users/999"))
                .willReturn(aResponse()
                        .withStatus(404)
                        .withHeader("Content-Type", "application/json")
                        .withBody(ApiMocks.getErrorResponse())));

        stubFor(delete(urlEqualTo("/users/999"))
                .willReturn(aResponse()
                        .withStatus(404)));
    }

    public static ReqresApiService createApiService() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(new AllureLoggingInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:9090/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        reqresApiService = retrofit.create(ReqresApiService.class);
        return reqresApiService;
    }

    public static void stopServer() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }
}
