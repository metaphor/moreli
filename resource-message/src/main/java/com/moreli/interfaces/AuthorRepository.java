package com.moreli.interfaces;

import org.springframework.stereotype.Repository;
import reactor.core.Exceptions;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

@Repository
public class AuthorRepository {

    Mono<String> someone() {
        return Mono.fromCallable(() -> {
            try {
                int timeout = 7;
                TimeUnit.SECONDS.sleep(timeout);
                return "authdsdsdsr " + timeout;
            } catch (InterruptedException e) {
                throw Exceptions.propagate(e);
            }
        });
    }
}
