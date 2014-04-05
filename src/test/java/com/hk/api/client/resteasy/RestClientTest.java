package com.hk.api.client.resteasy;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;

public class RestClientTest {

	@Test
	public void getVersion() throws Exception {
		// build client
		Client client = ClientBuilder.newClient();

		// build the target
		WebTarget target = client.target("http://127.0.0.1:10000/hello/version.hk");
		target.register(new AddAuthHeadersRequestFilter("wen", "123456"));

		Response response = target.request().accept(MediaType.APPLICATION_JSON).get();
		int statusCode = response.getStatus();

		// First validate the api status code
		if (statusCode != 200) {

			throw new RuntimeException("Failed with HTTP error code : " + statusCode);
		}

		// RESTEasy client automatically converts the response to desired objects.
		// This is how it is done.
		// Populate the response in String object
		String version = response.readEntity(String.class);

		// verify
		System.out.println();
        System.out.println("==================");
        System.out.println("Resteasy Client");
        System.out.println("==================");
		System.out.println(version);
		System.out.println("==================");
	}
}
