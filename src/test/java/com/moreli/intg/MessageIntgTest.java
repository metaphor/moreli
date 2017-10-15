package com.moreli.intg;

import com.moreli.MoreLiApplication;
import com.moreli.interfaces.facade.dto.QueryUserRepoRequest;
import com.moreli.interfaces.facade.dto.QueryUserReposResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static reactor.core.publisher.Flux.just;

@RunWith(SpringRunner.class)
@WebFluxTest
@ComponentScan(basePackageClasses = MoreLiApplication.class)
public class MessageIntgTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void should_reverse_message() throws Exception {

        QueryUserRepoRequest queryUserRepoRequest = new QueryUserRepoRequest();
        queryUserRepoRequest.setContent("abc");

        QueryUserReposResponse queryUserReposResponse = new QueryUserReposResponse();
        queryUserReposResponse.setContent("cba");

        webTestClient.post()
                .uri("/messages")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(just(queryUserRepoRequest), QueryUserRepoRequest.class)
                .exchange()
                .expectBodyList(QueryUserReposResponse.class)
                .hasSize(1)
                .contains(queryUserReposResponse);
    }
}
