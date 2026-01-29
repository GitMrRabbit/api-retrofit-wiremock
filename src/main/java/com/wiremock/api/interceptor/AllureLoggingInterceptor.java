package com.wiremock.api.interceptor;

import io.qameta.allure.Allure;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import java.io.IOException;
import java.nio.charset.Charset;

public class AllureLoggingInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        // Capture request details
        String requestDetails = buildRequestDetails(request);
        Allure.addAttachment("Request", "text/plain", requestDetails, ".txt");

        Response response = chain.proceed(request);

        // Capture response details
        String responseDetails = buildResponseDetails(response);
        Allure.addAttachment("Response", "text/plain", responseDetails, ".txt");

        return response;
    }

    private String buildRequestDetails(Request request) {
        StringBuilder sb = new StringBuilder();
        sb.append("URL: ").append(request.url()).append("\n");
        sb.append("Method: ").append(request.method()).append("\n");
        sb.append("Headers:\n");
        request.headers().forEach(header -> sb.append("  ").append(header.getFirst()).append(": ").append(header.getSecond()).append("\n"));

        if (request.body() != null) {
            sb.append("Body:\n");
            try {
                Buffer buffer = new Buffer();
                request.body().writeTo(buffer);
                Charset charset = Charset.forName("UTF-8");
                MediaType contentType = request.body().contentType();
                if (contentType != null) {
                    charset = contentType.charset(charset);
                }
                sb.append(buffer.readString(charset));
            } catch (IOException e) {
                sb.append("Failed to read request body");
            }
        }

        return sb.toString();
    }

    private String buildResponseDetails(Response response) {
        StringBuilder sb = new StringBuilder();
        sb.append("Status Code: ").append(response.code()).append("\n");
        sb.append("Message: ").append(response.message()).append("\n");
        sb.append("URL: ").append(response.request().url()).append("\n");
        sb.append("Headers:\n");
        response.headers().forEach(header -> sb.append("  ").append(header.getFirst()).append(": ").append(header.getSecond()).append("\n"));

        if (response.body() != null) {
            sb.append("Body:\n");
            try {
                ResponseBody responseBody = response.peekBody(Long.MAX_VALUE);
                Charset charset = Charset.forName("UTF-8");
                MediaType contentType = responseBody.contentType();
                if (contentType != null) {
                    charset = contentType.charset(charset);
                }
                sb.append(responseBody.string());
            } catch (IOException e) {
                sb.append("Failed to read response body");
            }
        }

        return sb.toString();
    }
}
