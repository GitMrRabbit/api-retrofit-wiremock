package com.wiremock.api.tests;

import com.wiremock.api.service.ReqresApiService;
import com.wiremock.api.mock.WireMockSetup;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    protected static ReqresApiService reqresApiService;

    @BeforeAll
    public static void setUp() {
        WireMockSetup.startServer();
        WireMockSetup.setupStubs();
        reqresApiService = WireMockSetup.createApiService();
    }

    @AfterAll
    public static void tearDown() {
        WireMockSetup.stopServer();
    }
}
