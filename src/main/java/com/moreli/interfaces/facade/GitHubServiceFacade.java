package com.moreli.interfaces.facade;

import com.moreli.interfaces.facade.dto.QueryUserRepoRequest;
import com.moreli.interfaces.facade.dto.QueryUserReposResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.util.Map;

@Service
public class GitHubServiceFacade {

    private final WebClient webClient;

    public GitHubServiceFacade() {
        this.webClient = WebClient.create();
    }


    public Flux<QueryUserReposResponse> userRepos(Flux<QueryUserRepoRequest> requests) {
        return requests
                .flatMap(queryUserRepoRequest -> webClient.get()
                        .uri(URI.create(String.format("https://api.github.com/users/%s/repos", queryUserRepoRequest.getContent())))
                        .exchange()
                        .mergeWith(Flux.empty())
                        .flatMap(clientResponse -> clientResponse.bodyToFlux(Map.class))

                )
                .map(content -> content.get("name").toString())
                .map(QueryUserReposResponse::new);
    }
}
