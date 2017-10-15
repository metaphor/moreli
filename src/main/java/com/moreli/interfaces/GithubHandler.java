package com.moreli.interfaces;

import com.moreli.interfaces.facade.GitHubServiceFacade;
import com.moreli.interfaces.facade.dto.QueryUserRepoRequest;
import com.moreli.interfaces.facade.dto.QueryUserReposResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class GithubHandler {

    private final GitHubServiceFacade gitHubServiceFacade;

    @Autowired
    public GithubHandler(final GitHubServiceFacade gitHubServiceFacade) {
        this.gitHubServiceFacade = gitHubServiceFacade;
    }

    public Mono<ServerResponse> queryUserRepos(ServerRequest serverRequest) {
        Flux<QueryUserReposResponse> reverse = gitHubServiceFacade.userRepos(serverRequest.bodyToFlux(QueryUserRepoRequest.class));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(reverse, QueryUserReposResponse.class);
    }
}
