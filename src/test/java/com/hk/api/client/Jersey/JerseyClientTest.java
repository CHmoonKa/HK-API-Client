package com.hk.api.client.Jersey;

import org.junit.Assert;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class JerseyClientTest {

	@Test
	public void getVersion(){
		Client client = Client.create();
        WebResource webResource = client
           .resource("http://127.0.0.1:10000/hello/version.hk");
        
        client.addFilter(new HTTPBasicAuthFilter("wen", "123456"));
 
        ClientResponse response = webResource.accept("application/json")
                   .get(ClientResponse.class);

        Assert.assertEquals(200, response.getStatus());
 
        String output = response.getEntity(String.class);
        // verify
        System.out.println();
        System.out.println("==================");
        System.out.println("Jersey Client");
        System.out.println("==================");
     	System.out.println(output);
     	System.out.println("==================");
	}
}
