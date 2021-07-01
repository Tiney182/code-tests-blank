package com.interview.code.controller;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.mockito.internal.hamcrest.HamcrestArgumentMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InterviewControllerTest {

    @Autowired
    InterviewController interviewController;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads() {
        Assert.notNull(interviewController, "Interview controller can't be NULL");
    }

    @Test
    public void test_CallGetEndpointWithName_HelloNameReturned() {
        String response = restTemplate.getForObject(
            "http://localhost:" + port + "/get/{name}", String.class, "Andy");
        Assert.isTrue(response.equals("Hello Andy"));
    }
}
