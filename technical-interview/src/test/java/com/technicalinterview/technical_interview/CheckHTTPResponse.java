package com.technicalinterview.technical_interview;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

// End to end test, to check the application expected response and format

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CheckHTTPResponse {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldPassIfStringMatches() throws Exception {
        String requestBody = "5 5\n" +
                "1 2 N\n" +
                "LMLMLMLMM\n" +
                "3 3 E\n" +
                "MMRMMRMRRM";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        String response = restTemplate.postForObject("http://localhost:" + port + "/", request, String.class);

        assertThat(response).contains("1 3 N\n" + "5 1 E");
    }
}
