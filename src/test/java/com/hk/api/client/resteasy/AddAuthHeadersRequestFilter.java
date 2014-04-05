package com.hk.api.client.resteasy;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

import org.apache.commons.codec.binary.Base64;

public class AddAuthHeadersRequestFilter implements ClientRequestFilter {

    private final String username;
    private final String password;

    public AddAuthHeadersRequestFilter(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        String token = username + ":" + password;
        byte[] base64Token = Base64.encodeBase64(token.getBytes(StandardCharsets.UTF_8));
        requestContext.getHeaders().add("Authorization", "Basic " + new String(base64Token));
    }
}
